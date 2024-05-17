package com.sam.quizservice.Controller;

import com.sam.quizservice.Service.QuizService;
import com.sam.quizservice.models.QuestionWrapper;
import com.sam.quizservice.models.QuizDTO;
import com.sam.quizservice.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNumQ(),quizDTO.getTitle());
    }

    @GetMapping("get/{qid}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int qid){
        return quizService.getQuizQuestions(qid);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> submitQuizQuestions(@RequestBody List<Response> response){
        return quizService.submitQuizQuestions(response);
    }
}
