package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.Folder;

public interface FolderWriter extends CanvasWriter<Folder, FolderWriter> {
    /**
     * Create folder inside course root
     * @param folder The folder to create
     * @param courseId The course ID in which the folder lives
     * @return The new folder object
     * @throws IOException When there is an error communicating with Canvas
     */
    //Optional<Folder> createCourseFolder(Folder folder, int courseId) throws IOException;

    /**
     * Create a folder 
     * @param folder The folder to create
     * @param folderId The folder ID in which the folder lives
     * @return The new folder object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Folder> createFolder(Folder folder, int folderId) throws IOException;

	
    /**
     * Update
     * @param folder The folder to update
     * @return The updated folder object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Folder> updateFolder(Folder folder) throws IOException;
}
