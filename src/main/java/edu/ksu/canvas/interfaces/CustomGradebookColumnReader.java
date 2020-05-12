package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CustomGradebookColumn;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author dvasjuta
 */
public interface CustomGradebookColumnReader  extends CanvasReader<CustomGradebookColumn, CustomGradebookColumnReader> {
    
    /**
     * Return list of all custom gradebook columns for a course.
     * @param courseId id of course.
     * @return List of custom gradebook columns in canvas.
     * @throws IOException When there is an error communicating with Canvas
     */
    List<CustomGradebookColumn> listCustomGradebookColumns(int courseId) throws IOException;
}
