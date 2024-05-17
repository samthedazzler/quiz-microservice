package com.sam.quizservice.models;

import lombok.Data;

@Data
public class QuizDTO {
    private String category;
    private int numQ;
    private String title;
}
