package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.File;

public interface FileReader extends CanvasReader<File, FileReader>{
    /**
     * Retrieve a specific file from Canvas by its course and file Canvas ID numbers
     * @param courseId   The Canvas ID of the course
     * @param fileId     The Canvas ID of the file
     * @return The file returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<File> getSingleFile(int courseId, int fileId) throws IOException;

    /**
     * Retrieve a specific file from Canvas by its file Canvas ID numbers
     * @param fileId     The Canvas ID of the file
     * @return The file returned by Canvas or an empty Optional
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<File> getSingleFile(int fileId) throws IOException;

    /**
     * Retrieve a list of files from Canvas by its course Canvas ID number
     * @param courseId   The Canvas ID of the course
     * @return List of files in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<File> getFilesInCourse(int courseId) throws IOException;

    /**
     * Retrieve a list of files from Canvas by its Folder Canvas ID number
     * @param folderId   The Canvas ID of the course
     * @return List of files in the folder with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<File> getFilesInFolder(int folderId) throws IOException;
}