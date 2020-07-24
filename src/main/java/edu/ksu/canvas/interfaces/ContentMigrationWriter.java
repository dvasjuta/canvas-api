package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;

import java.io.IOException;

import java.util.Optional;

public interface ContentMigrationWriter extends CanvasWriter<ContentMigration, ContentMigrationWriter>{

	/**
	 * 
	 * @param destinationCourseId
	 * @param contentMigration
	 * @param options configure select[] values
	 * @return
	 * @throws IOException 
	 */
    Optional<ContentMigration> createCourseContentMigration(int destinationCourseId, ContentMigration contentMigration, CreateCourseContentMigrationOptions options) throws IOException;
}
