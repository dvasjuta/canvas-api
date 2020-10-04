package edu.ksu.canvas.interfaces;

import java.io.IOException;

import edu.ksu.canvas.model.assignment.QuizQuestion;
import java.util.Optional;

public interface QuizQuestionWriter extends CanvasWriter<QuizQuestion, QuizQuestionWriter> {

    /**
     * Create a quiz question
     * @param courseId id of course
     * @param quizQuestion question
     * @param quizId Quiz ID for question
     * @return The created Question object
     * @throws IOException if there is an error communicating with Canvas
     */
	public Optional<QuizQuestion> createQuizQuestion(String courseId, QuizQuestion quizQuestion, Integer quizId) throws IOException;
		
    /**
     * Delete a quiz question
     * @param courseId Course ID containing the quiz to modify
     * @param quizId Quiz ID with question to delete
     * @param questionId Question ID of question to delete
     * @return true if deletion was successful (API returned HTTP 204), false otherwise
     * @throws IOException if there is an error communicating with Canvas
     */
    public boolean deleteQuizQuestion(String courseId, Integer quizId, Integer questionId) throws IOException;
}
