package com.example.demo.request;

public class CreateTeamRequest {
   String team_name;
   int user_id;
   int team_id;
   int status;
   int inviter_id;
   String member_id;

   public CreateTeamRequest() {
   }

   public CreateTeamRequest(String team_name, int user_id, int team_id, int status, int inviter_id, String member_id) {
      this.team_name = team_name;
      this.user_id = user_id;
      this.team_id = team_id;
      this.status = status;
      this.inviter_id = inviter_id;
      this.member_id = member_id;
   }

   public String getTeam_name() {
      return team_name;
   }

   public void setTeam_name(String team_name) {
      this.team_name = team_name;
   }

   public int getUser_id() {
      return user_id;
   }

   public void setUser_id(int user_id) {
      this.user_id = user_id;
   }

   public int getTeam_id() {
      return team_id;
   }

   public void setTeam_id(int team_id) {
      this.team_id = team_id;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public int getInviter_id() {
      return inviter_id;
   }

   public void setInviter_id(int inviter_id) {
      this.inviter_id = inviter_id;
   }

   public String getMember_id() {
      return member_id;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }
}
