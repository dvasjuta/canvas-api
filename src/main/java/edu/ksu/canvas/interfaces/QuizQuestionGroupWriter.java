package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.assignment.QuizQuestionGroup;
import java.util.ArrayList;
import java.util.HashMap;

public interface QuizQuestionGroupWriter extends CanvasWriter<QuizQuestionGroup, QuizQuestionGroupWriter> {
    /**
     * Create a quiz question group
     * @param quizQuestionGroup The quiz question group to create
     * @param courseId The course ID in which the qq group resides
     * @param quizId The quiz ID in which the qq group resides
     * @return true if created
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<QuizQuestionGroup> createQuizQuestionGroup(QuizQuestionGroup quizQuestionGroup, Integer courseId, Integer quizId) throws IOException;

     Optional<QuizQuestionGroup> updateQuizQuestionGroup(QuizQuestionGroup quizQuestionGroup, Integer courseId, Integer quizId) throws IOException;

}
