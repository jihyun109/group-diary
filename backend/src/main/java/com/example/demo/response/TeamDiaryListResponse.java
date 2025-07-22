package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDiaryListResponse {
    private long id; // user
    private String first_name;    // user

    private String last_name;
    private String initial;
    private String color;
    private String written_date;
    private String diary_title;
}
