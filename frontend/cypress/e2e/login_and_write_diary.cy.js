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

    // 작성된 일기 확인 및 클릭
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
});
