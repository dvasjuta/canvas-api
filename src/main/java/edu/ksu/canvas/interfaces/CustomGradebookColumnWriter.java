package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CustomGradebookColumn;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dvasjuta
 */
public interface CustomGradebookColumnWriter  extends CanvasWriter<CustomGradebookColumn, CustomGradebookColumnWriter> {
    
    /**
     * Create a custom gradebook column.
     * @param customGradebookColumn an object containing the information needed to create
     *         the Custom Gradebook Column in Canvas.
     * @return the newly created Custom gradebook column.
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CustomGradebookColumn> createCustomGradebookColumns(CustomGradebookColumn customGradebookColumn) throws IOException;


    /**
     * Update a custom gradebook column.
     * @param customGradebookColumn an object containing the information needed to create
     *         the Custom Gradebook Column in Canvas.
     * @return the updated Custom gradebook column
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CustomGradebookColumn> updateCustomGradebookColumns(CustomGradebookColumn customGradebookColumn) throws IOException;

    /**
     * Delete a custom gradebook column.
     * @param courseId the id of the course.
     * @param customGradebookColumnId the id of the Custom Gradebook Column in Canvas.
     * @return true if the operation was successful
     * @throws IOException When there is an error communicating with Canvas
     */
    boolean deleteCustomGradebookColumns(int courseId, int customGradebookColumnId) throws IOException;

}

