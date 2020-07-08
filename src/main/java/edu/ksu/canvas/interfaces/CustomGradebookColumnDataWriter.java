package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CustomGradebookColumnData;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author dvasjuta
 */
public interface CustomGradebookColumnDataWriter  extends CanvasWriter<CustomGradebookColumnData, CustomGradebookColumnDataWriter> {
    
 
    /**
     * Update a custom gradebook column data.
     * @param courseId id of course.
     * @param columnData an object containing the information needed 
	 * @param allowDeletion if true and content is blank, delete the column data
     * @return the updated Custom gradebook column data
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CustomGradebookColumnData> updateCustomGradebookColumnData(int courseId, CustomGradebookColumnData columnData, boolean allowDeletion) throws IOException;


    /**
     * Bulk update a custom gradebook column data.
     * @param courseId id of course.
     * @param columnData a list containing the information needed 
	 * @param allowDeletion if true and content is empty/null, delete the column data
     * @return true if the operation was successful
     * @throws IOException When there is an error communicating with Canvas
     */
    boolean updateCustomGradebookColumnData(int courseId, List<CustomGradebookColumnData> columnData, boolean allowDeletion) throws IOException;
}

