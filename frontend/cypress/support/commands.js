Cypress.Commands.add('login', () => {
    cy.visit('/');
    cy.url().should('include', '/logIn');

    cy.get('input[type="email"]').type(Cypress.env('TEST_EMAIL'));
    cy.get('input[type="password"]').type(Cypress.env('TEST_PASSWORD'));

    cy.get('button[type="button"]').click();

    cy.url().should('include', '/main');
})