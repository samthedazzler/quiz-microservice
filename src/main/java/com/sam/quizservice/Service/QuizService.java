package com.sam.quizservice.Service;

import com.sam.quizservice.Dao.QuizDao;
import com.sam.quizservice.Feign.QuizInterface;
import com.sam.quizservice.models.QuestionWrapper;
import com.sam.quizservice.models.Quiz;
import com.sam.quizservice.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions=quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int qid) {
        Quiz quiz=quizDao.findById(qid).get();
        List<Integer> questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
//        System.out.println(questionFromdb.toString());
//        List<QuestionWrapper> questionsforUser = new ArrayList<>();
//        for(Question q:questionFromdb){
//            QuestionWrapper qs=new QuestionWrapper(q.getQid(),q.getQuestion(),q.getOption_1(),q.getOption_2(),q.getOption_3(),q.getOption_4());
//            questionsforUser.add(qs);
//        }


        return questions;

    }

    public ResponseEntity<Integer> submitQuizQuestions(List<Response> response) {
        ResponseEntity<Integer> score = quizInterface.getScore(response);
        return score;
        }
    }

