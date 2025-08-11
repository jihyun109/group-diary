import '../support/commands';

describe('일기 작성 시나리오', () => {
  beforeEach(() => {
    cy.login();
  });

  it('일기 작성', () => {
    // 일기 작성 API 인터셉트 설정
    cy.intercept('POST', '/v2/diaries').as('createDiary');

    // 일기 작성 버튼 클릭
    cy.contains('Write diary').click();

    // 일기 작성
    cy.get('input[type="date"]').type('2025-07-31');
    cy.get('input[type="text"]').type('test');
    cy.get('textarea').type('testtexts');

    // 팀 선택
    cy.get('#navbarDropdownMenuLink2').click();
    cy.get('.dropdown-menu .dropdown-item').first().click();
    cy.get('#navbarDropdownMenuLink2').click();
    cy.get('.dropdown-menu .dropdown-item').first().click();

    cy.contains('button', 'write diary').click();

    // API 응답 대기
    cy.wait('@createDiary');

    // 일기 작성 완료 후 메인 페이지로 돌아가기
    cy.url().should('include', '/main');

    // 작성된 일기 확인
    cy.contains('test').should('be.visible');
  });

  it('일기 삭제', () => {
    // 일기 삭제 API 인터셉트 설정
    cy.intercept('DELETE', '/diaries/*').as('deleteDiary');

    // 작성된 일기 클릭하여 상세 페이지로 이동
    cy.contains('test').click();

    // 일기 상세 페이지에서 삭제 버튼 클릭
    cy.contains('button', '삭제').click();

    // 삭제 확인 모달에서 확인 버튼 클릭
    cy.contains('Yes').click();

    // API 응답 대기
    cy.wait('@deleteDiary');

    // 삭제 후 메인 페이지로 돌아가서 일기가 삭제되었는지 확인
    cy.url().should('include', '/main');
    cy.contains('test').should('not.exist');
  });

  it('일기 수정', () => {
    // 다이어리 리스트가 로드될 때까지 대기
    cy.get('table tbody tr').should('have.length.greaterThan', 0);

    // 첫 번째 다이어리 제목 클릭
    cy.get('table tbody tr').eq(0).find('td h6 a').click();

    // 상세 페이지 URL 확인
    cy.url().should('include', '/diaryDetail?diary=');

    // 수정 버튼 클릭
    cy.contains('수정').click();

    // 수정 페이지 url 확인
    cy.url().should('include', '/editDiary?diaryId=');

    // 일기 수정
    cy.get('input[type="date"]').type('2025-08-11');
    cy.get('input[placeholder="제목').clear().type('Edited title');

    cy.get('#navbarDropdownMenuLink2').click();
    cy.get('.dropdown-menu .dropdown-item').first().click();
    cy.get('#recipient_input_list .badge').first().within(() => {
      cy.get('a').click();
    });
    cy.get('textarea[name="message"]').clear().type('edited details');

     // 일기 수정 API 인터셉트 설정
     cy.intercept('PUT', '/v2/diaries/edit').as('editDiary');

     // edit 버튼 클릭
     cy.get('button').contains('edite').click();

     // API 응답 대기
     cy.wait('@editDiary');

     // 일기 detail 페이지로 갔는지 확인
     cy.url().should('include', '/diaryDetail?diary=');
     
     // 수정된 일기 내용이 제대로 반영되었는지 확인
     cy.contains('Edited title').should('be.visible');
     cy.contains('edited details').should('be.visible');
     
     // 수정된 날짜가 제대로 표시되는지 확인 (날짜 형식에 따라 조정 필요)
     cy.contains('2025-08-11').should('be.visible');
  })
});
