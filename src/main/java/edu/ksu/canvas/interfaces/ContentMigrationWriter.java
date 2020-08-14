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
	 * @throws IllegalArgumentException if incorrect values passed in 
	 */
    Optional<ContentMigration> createCourseContentMigration(int destinationCourseId, ContentMigration contentMigration, CreateCourseContentMigrationOptions options) throws IOException, IllegalArgumentException;

	/**
	 * Update Content Migration
	 * IF selectiveImport == true && workflowState == "waiting_for_select" - a new "COPY" key/array is generated
	 * 
	 * @param destinationCourseId
	 * @param contentMigration
	 * @param options configure select[] values
	 * @return
	 * @throws IOException 
	 * @throws IllegalArgumentException if incorrect values passed in 
	 */
    Optional<ContentMigration> updateCourseContentMigration(int destinationCourseId, ContentMigration contentMigration, CreateCourseContentMigrationOptions options) throws IOException, IllegalArgumentException;
}
