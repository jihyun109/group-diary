Cypress.Commands.add('login', () => {
  cy.visit('/');
  cy.url().should('include', '/logIn');

  cy.get('input[type="email"]').type(Cypress.env('TEST_EMAIL'));
  cy.get('input[type="password"]').type(Cypress.env('TEST_PASSWORD'));

  cy.get('button[type="button"]').click();

  cy.url().should('include', '/main');
});

// 임의의 계정으로 로그인
Cypress.Commands.add('loginWith', (email, password) => {
  cy.visit('/');
  cy.url().should('include', '/logIn');

  cy.get('input[type="email"]').clear().type(email);
  cy.get('input[type="password"]').clear().type(password);

  cy.get('button[type="button"]').click();

  cy.url().should('include', '/main');
});

// 세션 초기화 후 로그인 페이지로 이동
Cypress.Commands.add('logout', () => {
  cy.get('i.material-icons.logout-icon').contains('logout').click();
  cy.url().should('include', '/logIn');
});
