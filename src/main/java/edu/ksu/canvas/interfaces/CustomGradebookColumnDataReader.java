package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CustomGradebookColumnData;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author dvasjuta
 */
public interface CustomGradebookColumnDataReader  extends CanvasReader<CustomGradebookColumnData, CustomGradebookColumnDataReader> {
    
    /**
     * Return list of all custom gradebook column data for a given column.
     * @param courseId id of course.
     * @param columnId id of column.
     * @return List of custom gradebook column data in canvas.
     * @throws IOException When there is an error communicating with Canvas
     */
    List<CustomGradebookColumnData> listCustomGradebookColumnData(int courseId, int columnId) throws IOException;
}
