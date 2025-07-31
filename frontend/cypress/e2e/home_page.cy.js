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

    // // recipient_input_list에 해당 팀 이름이 나타났는지 확인
    // cy.get('#recipient_input_list')
    //   .find('.badge') // 추가된 팀 표시 뱃지
    //   .should('have.length.at.least', 1)

    cy.contains('button', 'write diary').click()

    cy.intercept('POST', '/api/diary').as('createDiary')
  })
})