package com.example.demo.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeamDiariesResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String color;
    private String initial;
    private String writtenDate;
    private String diaryTitle;
}
