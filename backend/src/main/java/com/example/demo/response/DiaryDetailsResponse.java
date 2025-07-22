package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDetailsResponse {
    private long id;
    private String diary_title;
    private String written_date;
    private String details;
    private long team_id;
    private String team_name;

    private String first_name;
    private String last_name;
}
