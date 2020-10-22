package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.FileReader;
import edu.ksu.canvas.interfaces.FileWriter;
import edu.ksu.canvas.model.Enrollment;
import edu.ksu.canvas.model.File;
import edu.ksu.canvas.model.Folder;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.log4j.Logger;

/**
 *
 * @author dvasjuta
 */
public class FileImpl extends BaseImpl<File, FileReader, FileWriter> implements FileReader,
        FileWriter {

    private static final Logger LOG = Logger.getLogger(FileImpl.class);

    public FileImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<File>>(){}.getType();
    }

    @Override
    protected Class<File> objectType() {
        return File.class;
    }

	@Override
	public Optional<File> getSingleFile(int courseId, int fileId) throws IOException {
        LOG.debug("Retrieving single file " + fileId + " in course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/files/" + fileId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(File.class, response);
	}

	@Override
	public Optional<File> getSingleFile(int fileId) throws IOException {
        LOG.debug("Retrieving single file " + fileId);
        String url = buildCanvasUrl("files/" + fileId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(File.class, response);
	}

	@Override
	public List<File> getFilesInCourse(int courseId) throws IOException {
		LOG.debug("Looking up files for course " + courseId);
		String url = buildCanvasUrl(String.format("courses/%s/files", courseId), Collections.emptyMap());
		return getListFromCanvas(url);
	}
	
	@Override
    public List<File> getFilesInFolder(int folderId) throws IOException {
		LOG.debug("Looking up files for folder " + folderId);
		String url = buildCanvasUrl(String.format("folders/%s/files", folderId), Collections.emptyMap());
		return getListFromCanvas(url);
		
	}
	@Override
	public Optional<File> copyFile(int sourceFileId, int destinationFolderId) throws IOException {
        Map<String, List<String>> postParams = new HashMap<>();
        postParams.put("on_duplicate", Collections.singletonList("overwrite"));
        postParams.put("source_file_id", Collections.singletonList(String.valueOf(sourceFileId)));
        String url = buildCanvasUrl("folders/" + destinationFolderId + "/copy_file", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, postParams);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.error("Failed to copy file, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(File.class, response);
	}	
}