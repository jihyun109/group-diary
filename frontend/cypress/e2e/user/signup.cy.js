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
});
