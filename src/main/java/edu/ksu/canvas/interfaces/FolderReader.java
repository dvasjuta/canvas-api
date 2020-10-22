package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.Folder;

public interface FolderReader extends CanvasReader<Folder, FolderReader>{
    /**
     * Retrieve a specific folder from Canvas by its course and folder Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param folderId     The Canvas ID of the folder
     * @return The folder returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Folder> getSingleFolder(int courseId, int folderId) throws IOException;

    /**
     * Retrieve a list of folders from Canvas by its course Canvas ID number
     * @param courseId   The Canvas ID of the course
     * @return List of folders in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Folder> getFoldersInCourse(int courseId) throws IOException;
}