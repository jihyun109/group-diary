import '../support/commands';

describe('팀 생성', () => {
  it('새로운 팀을 성공적으로 생성할 수 있어야 한다', () => {
    cy.login();
    const teamName = `테스트팀_${Date.now()}`;

    // 팀 생성 버튼 클릭
    cy.get('[data-bs-target="#createGroup-form"]').click();

    // 팀 이름 입력
    cy.get('#createGroup-form input[placeholder="Group Name"]').type(teamName);

    // 팀 생성 완료 후 페이지 새로고침 또는 API 응답 대기
    cy.intercept('GET', '/members/userTeamList/*').as('getTeams');

    // 팀 생성 확인 버튼 클릭
    cy.get('#createGroup-form .modal-footer button').contains('Create').click();

    // 모달이 닫히는지 확인
    cy.get('#createGroup-form').should('not.be.visible');

    // team list 안에 팀명이 나타날 때까지 기다린 후 확인
    cy.get('ul.team-list-scroll')
      .should('exist')
      .within(() => {
        cy.contains('a.list-group-item.list-group-item-action', teamName, {
          timeout: 1000,
        })
          .scrollIntoView()
          .should('be.visible');
      });
  });
});

describe('사용자 초대', () => {
  it('기존 팀에 사용자를 초대할 수 있어야 한다 (초대 알림 확인 포함)', () => {
    cy.login();

    const inviteeEmail = Cypress.env('TEST_EMAIL2') || 'test2';
    const inviteePassword = Cypress.env('TEST_PASSWORD2') || 'test2';

    // 첫 번째 팀 선택 (좌측 팀 리스트) 및 team_name 추출 → alias 저장
    cy.get('.team-list-scroll .list-group-item')
      .first()
      .then(($team) => {
        const name = $team.text().trim();
        cy.wrap(name).as('selectedTeamName');
        cy.wrap($team).click();
      });

    // 멤버 추가 버튼 클릭
    cy.get('[data-bs-target="#inviteUser-form"]').click();

    // 사용자 검색
    const searchUser = inviteeEmail;
    cy.get('#inviteUser-form input[placeholder="Search"]').type(searchUser);
    cy.get('#inviteUser-form #searchBtn').click();

    // 검색된 사용자 선택
    cy.get('#inviteUser-form .list-group-item').first().click();

    // 초대 버튼 클릭
    cy.get('#inviteUser-form .modal-footer button').contains('invite').click();

    // 모달이 닫히는지 확인
    cy.get('#inviteUser-form').should('not.be.visible');

    // 초대 보낸 후, 로그아웃 후 test2 계정으로 로그인
    cy.logout();
    cy.loginWith(inviteeEmail, inviteePassword);

    // 헤더 알림 드롭다운 열기 (alarm 아이콘)
    cy.get('nav .dropdown a.nav-link[data-bs-toggle="dropdown"]').click();

    // 초대 알림 목록에서 inviter(first_name)가 'test1'이고 team_name이 선택한 팀명과 일치하는 항목 존재 확인
    cy.get('@selectedTeamName').then((teamName) => {
      cy.get('ul.dropdown-menu ul.notification-list li.list-group-item')
        .filter((i, el) => {
          const $el = Cypress.$(el);
          const firstName = $el.find('strong').eq(0).text().trim();
          const invitedTeamName = $el.find('strong').eq(1).text().trim();
          return firstName === 'test1' && invitedTeamName === teamName;
        })
        .should('have.length.at.least', 1);
    });
  });
});

describe('초대 수락/거절', () => {
  it('받은 초대를 수락할 수 있어야 한다', () => {
    cy.loginWith('test2', 'test2');

    // 헤더 알림 드롭다운 열기 (alarm 아이콘)
    cy.get('nav .dropdown a.nav-link[data-bs-toggle="dropdown"]').click();

    // 수락 API와 팀목록 갱신 API 대기 설정
    cy.intercept('PUT', '/members/*').as('acceptInvite');
    cy.intercept('GET', '/members/userTeamList/*').as('getTeamsAfterAccept');

    // 첫 번째 초대의 팀 이름 추출 후 수락
    cy.get('ul.dropdown-menu ul.notification-list li.list-group-item')
      .first()
      .within(() => {
        cy.get('strong')
          .eq(1)
          .invoke('text')
          .then((t) => {
            cy.wrap(t.trim()).as('acceptedTeamName');
          });
        cy.contains('button', 'accept').click();
      });

    // 수락 완료 및 팀 목록 갱신 대기
    cy.wait('@acceptInvite');
    cy.wait('@getTeamsAfterAccept');

    // 초대 목록에서 해당 항목이 사라졌는지 확인
    cy.get('.invitation-item').should('have.length.lessThan', 1);

    // 팀 리스트에서 해당 팀명 확인
    cy.get('@acceptedTeamName').then((teamName) => {
      cy.get('.team-list-scroll .list-group-item')
        .contains(teamName)
        .scrollIntoView()
        .should('be.visible');
    });
  });
});
