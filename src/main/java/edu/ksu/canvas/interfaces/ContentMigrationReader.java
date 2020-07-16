package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.model.Progress;

import java.io.IOException;
import java.util.Optional;

public interface ContentMigrationReader extends CanvasReader<ContentMigration, ContentMigrationReader> {
    /**
     * Returns a content migration.
     * @param courseId The course ID for this API call
     * @return A ContentMigration object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<ContentMigration> getCourseContentMigration(String courseId, Integer id) throws IOException;
	
	/**
	 * 
	 * @param progressId
	 * @return
	 * @throws IOException 
	 */
	Optional<Progress> getProgress(Long progressId) throws IOException;
	
	/**
	 * 
	 * @param progressUrl
	 * @return
	 * @throws IOException 
	 */
	Optional<Progress> getProgress(String progressUrl) throws IOException;
}