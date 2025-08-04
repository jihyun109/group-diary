describe('일기 공유 시나리오', () => {
  it('successfully loads', () => {
    cy.visit('http://localhost:5173')

    cy.url().should('include', '/logIn')

    // 이메일, 비밀번호 입력
    cy.get('input[type="email"]').type(Cypress.env('TEST_EMAIL'))
    cy.get('input[type="password"]').type(Cypress.env('TEST_PASSWORD'))

    // 로그인 버튼 클릭
    cy.get('button[type="button"]').click()

    // 로그인 성공 후 URL이 /main으로 리디렉션되었는지 확인
    cy.url().should('include', '/main')

    // 일기 작성 버튼 클릭
    cy.contains('Write diary').click()

    // 일기 작성
    cy.get('input[type="date"]').type('2025-07-31')
    cy.get('input[type="text"]').type('test')
    cy.get('textarea').type('testtexts')
    
    // 팀 선택
    cy.get('#navbarDropdownMenuLink2').click()

    // 드롭다운 항목 중 첫 번째 클릭 (filteredTeamData의 첫 팀)
    cy.get('.dropdown-menu .dropdown-item').first().click()

    cy.contains('button', 'write diary').click()

    cy.intercept('POST', '/api/diary').as('createDiary')

    // 일기 작성 완료 후 메인 페이지로 돌아가기
    cy.url().should('include', '/main')

    // 작성된 일기 클릭하여 상세 페이지로 이동
    cy.contains('test').click()

    // 일기 상세 페이지에서 삭제 버튼 클릭
    cy.contains('button', '삭제').click()

    // 삭제 확인 모달에서 확인 버튼 클릭
    cy.contains('Yes').click()

    // 삭제 후 메인 페이지로 돌아가서 일기가 삭제되었는지 확인
    cy.url().should('include', '/main')
    cy.contains('test').should('not.exist')
  })
})