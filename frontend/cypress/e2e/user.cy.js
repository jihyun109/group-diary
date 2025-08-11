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
    cy.get('input[name="favcolor"]').type('#0011FF');

    cy.contains('button', 'Sign up').click();

    // API 응답 대기 및 사용자 ID 추출
    cy.wait('@signUp').then((interception) => {
      // 회원가입 API 응답에서 사용자 ID 추출
      if (interception.response && interception.response.body) {
        cy.log(`전체 응답 본문: ${JSON.stringify(interception.response.body)}`);
        
        // 다양한 응답 구조에서 사용자 ID 추출 시도
        let createdUserId = null;
        
        // 1. 직접 id 또는 userId 필드 확인
        if (interception.response.body.id) {
          createdUserId = interception.response.body.id;
        } else if (interception.response.body.userId) {
          createdUserId = interception.response.body.userId;
        }
        // 2. data 객체 내부 확인
        else if (interception.response.body.data && interception.response.body.data.id) {
          createdUserId = interception.response.body.data.id;
        }
        // 3. result 객체 내부 확인
        else if (interception.response.body.result && interception.response.body.result.id) {
          createdUserId = interception.response.body.result.id;
        }
        
        // 4. 이메일로 사용자 ID 조회 (새로운 백엔드 API 사용)
        if (!createdUserId) {
          cy.log('이메일로 사용자 ID 조회 시도...');
          
          // 방법 1: 직접 이메일 경로로 조회
          cy.request({
            method: 'GET',
            url: `http://localhost:8080/users/search?email=test%40gmail.com`,
            failOnStatusCode: false
          }).then((userResponse) => {
            cy.log(`이메일 조회 응답 상태: ${userResponse.status}`);
            cy.log(`이메일 조회 응답 본문: ${JSON.stringify(userResponse.body)}`);
            
            if (userResponse.status === 200 && userResponse.body) {
              createdUserId = userResponse.body;
              cy.log(`이메일로 찾은 사용자 ID: ${createdUserId}`);
              
              // 사용자 삭제 실행
              if (createdUserId) {
                cy.log(`테스트 완료 후 사용자 ID ${createdUserId} 삭제 시작`);
                
                cy.request({
                  method: 'DELETE',
                  url: `http://localhost:8080/users/${createdUserId}`,
                  failOnStatusCode: false
                }).then((deleteResponse) => {
                  if (deleteResponse.status === 200) {
                    cy.log(`사용자 ID ${createdUserId} 삭제 성공`);
                  } else {
                    cy.log(`사용자 ID ${createdUserId} 삭제 실패: ${deleteResponse.status}`);
                  }
                });
              }
            } else if (userResponse.status === 400) {
              cy.log('400 에러 발생. 다른 방법으로 시도...');
              
              // 방법 2: 쿼리 파라미터로 이메일 전달
              cy.request({
                method: 'GET',
                url: 'http://localhost:8080/users',
                qs: { email: 'test@gmail.com' },
                failOnStatusCode: false
              }).then((userResponse2) => {
                cy.log(`쿼리 파라미터 조회 응답 상태: ${userResponse2.status}`);
                cy.log(`쿼리 파라미터 조회 응답 본문: ${JSON.stringify(userResponse2.body)}`);
                
                if (userResponse2.status === 200 && userResponse2.body) {
                  if (Array.isArray(userResponse2.body)) {
                    const user = userResponse2.body.find(u => u.email === 'test@gmail.com');
                    if (user && user.id) {
                      createdUserId = user.id;
                      cy.log(`쿼리 파라미터로 찾은 사용자 ID: ${createdUserId}`);
                      
                      // 사용자 삭제 실행
                      if (createdUserId) {
                        cy.log(`테스트 완료 후 사용자 ID ${createdUserId} 삭제 시작`);
                        
                        cy.request({
                          method: 'DELETE',
                          url: `http://localhost:8080/users/${createdUserId}`,
                          failOnStatusCode: false
                        }).then((deleteResponse) => {
                          if (deleteResponse.status === 200) {
                            cy.log(`사용자 ID ${createdUserId} 삭제 성공`);
                          } else {
                            cy.log(`사용자 ID ${createdUserId} 삭제 실패: ${deleteResponse.status}`);
                          }
                        });
                      }
                    }
                  }
                }
              });
            } else {
              cy.log(`사용자 ID 조회 실패: ${userResponse.status}`);
            }
          });
        }
        
        cy.log(`추출된 사용자 ID: ${createdUserId}`);
        
        // 회원가입 테스트 완료 후 바로 사용자 삭제 (이메일 조회로 ID를 찾지 못한 경우)
        if (createdUserId && !createdUserId.toString().includes('@')) {
          cy.log(`테스트 완료 후 사용자 ID ${createdUserId} 삭제 시작`);
          
          // 백엔드 API 직접 호출하여 사용자 삭제
          cy.request({
            method: 'DELETE',
            url: `http://localhost:8080/users/${createdUserId}`,
            failOnStatusCode: false // 삭제 실패 시에도 테스트 중단하지 않음
          }).then((response) => {
            if (response.status === 200) {
              cy.log(`사용자 ID ${createdUserId} 삭제 성공`);
            } else {
              cy.log(`사용자 ID ${createdUserId} 삭제 실패: ${response.status}`);
            }
          });
        }
      }
    });

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
