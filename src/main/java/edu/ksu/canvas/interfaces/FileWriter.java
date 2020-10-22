package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.File;

public interface FileWriter extends CanvasWriter<File, FileWriter> {
    /**
     * Create
     * @param sourceFileId The file to create
     * @param destinationFolderId The course ID in which the folder lives
     * @return The new folderobject
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<File> copyFile(int sourceFileId, int destinationFolderId) throws IOException;
}
