import '../support/commands';

describe('사용자 관련 시나리오', () => {
  it('회원 가입', () => {
    cy.visit('/');

    // 회원가입 페이지로 이동
    cy.contains('Sign Up').click();
    cy.url().should('include', '/signUp');

    // 회원가입 API 인터셉트 설정
    cy.intercept('POST', '/users').as('signUp');

    cy.get('input[placeholder="First Name"]').type('FirstName');
    cy.get('input[placeholder="Last Name"]').type('LastName');
    cy.get('input[placeholder="Email Address"]').type('test@gmail.com');
    cy.get('input[placeholder="Password"]').type('password');
    cy.get('input[name="favcolor').type('#0011FF');

    cy.contains('button', 'Sign up').click();

    // API 응답 대기
    cy.wait('@signUp');

    // 회원가입 후 로그인 페이지로 돌아갔는지 확인
    cy.url().should('include', '/logIn');
  });

  it('회원 정보 수정', () => {
    // 로그인
    cy.login();

    // 회원정보 페이지로 이동
    cy.get('a[href="/userInfo"]').click();
    cy.url().should('include', '/userInfo');

    // 수정 버튼 클릭해 회원 정보 수정 페이지로 이동
    cy.contains('button', 'edite').click();
    cy.url().should('include', '/userInfoEdit');

    // 데이터 변경
    cy.get('input[placeholder="First Name"]').clear().type('FirstName(edited)');
    cy.get('input[placeholder="Last Name"]').clear().type('LastName(edited)');
    cy.get('input[placeholder="Email Address"]')
      .clear()
      .type('testEdited@gmail.com');
    cy.get('input[placeholder="Password"]').clear().type('test');
    cy.get('input#favcolor')
      .invoke('val', '#ff0000') // 원하는 색상으로 값 설정 (빨간색)
      .trigger('input') // v-model이 반응하도록 input 이벤트 발생
      .should('have.value', '#ff0000');

    // 회원정보 수정 API 인터셉트 설정
    cy.intercept('PUT', /\/users\/\d+/).as('updateUser');

    // 저장 버튼 클릭
    cy.contains('button', 'Save').click();
    
    // API 응답 대기
    cy.wait('@updateUser');

    cy.url().should('include', '/userInfo');

    // 데이터 복원
    cy.contains('button', 'edite').click();
    cy.url().should('include', '/userInfoEdit');

    cy.get('input');
    cy.get('input[placeholder="First Name"]').clear().type('FirstName');
    cy.get('input[placeholder="Last Name"]').clear().type('LastName');
    cy.get('input[placeholder="Email Address"]').clear().type('test');
    cy.get('input[placeholder="Password"]').clear().type('test');
    cy.get('input#favcolor')
      .invoke('val', '#0011ff')
      .trigger('input')
      .should('have.value', '#0011ff');
    cy.contains('button', 'Save').click();
    cy.url().should('include', '/userInfo');
  });
});
