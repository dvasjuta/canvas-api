package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.assignment.QuizQuestionGroup;

public interface QuizQuestionGroupReader extends CanvasReader<QuizQuestionGroup, QuizQuestionGroupReader>{
    /**
     * Retrieve a specific quiz question group from Canvas by its course/quiz/Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param quizId     The Canvas ID of the quiz
     * @param quizQuestionGroupId     The Canvas ID of the quiz question group
     * @return The QuizQuestionGroup returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<QuizQuestionGroup> getSingleQuizQuestionGroup(String courseId, Integer quizId, Integer quizQuestionGroupId) throws IOException;

}