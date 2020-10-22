package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.FolderReader;
import edu.ksu.canvas.interfaces.FolderWriter;
import edu.ksu.canvas.model.Folder;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.log4j.Logger;

/**
 *
 * @author dvasjuta
 */
public class FolderImpl extends BaseImpl<Folder, FolderReader, FolderWriter> implements FolderReader,
        FolderWriter {

    private static final Logger LOG = Logger.getLogger(FolderImpl.class);

    public FolderImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Folder>>(){}.getType();
    }

    @Override
    protected Class<Folder> objectType() {
        return Folder.class;
    }

	@Override
	public Optional<Folder> getSingleFolder(int courseId, int folderId) throws IOException {
        LOG.debug("Retrieving single folder " + folderId + " in course " + courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/folders/" + folderId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(Folder.class, response);
	}

	@Override
	public List<Folder> getFoldersInCourse(int courseId) throws IOException {
		LOG.debug("Looking up folders for course " + courseId);
		String url = buildCanvasUrl(String.format("courses/%s/folders", courseId), Collections.emptyMap());
		return getListFromCanvas(url);
	}

	/*@Override
	public Optional<Folder> createCourseFolder(Folder folder, int courseId) throws IOException {
        LOG.debug("Creating folder in course " + courseId);
        Objects.requireNonNull(folder.getName(), "name must be set to create a folder.");
        String url = buildCanvasUrl("courses/" + courseId + "/folders", Collections.emptyMap());
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject groupJson = gson.toJsonTree(folder).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, groupJson);
        return responseParser.parseToObject(Folder.class, response);
	}*/

	@Override
	public Optional<Folder> createFolder(Folder folder, int folderId) throws IOException {
        LOG.debug("Creating folder in folder " + folderId);
        Objects.requireNonNull(folder.getName(), "name must be set to create a folder.");
        String url = buildCanvasUrl("folders/" + folderId + "/folders", Collections.emptyMap());
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject groupJson = gson.toJsonTree(folder).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, groupJson);
        return responseParser.parseToObject(Folder.class, response);

	}
	
	@Override
	public Optional<Folder> updateFolder(Folder folder) throws IOException {
        LOG.debug("Updating folder " + folder.getId());
        String url = buildCanvasUrl("folders/" + folder.getId(), Collections.emptyMap());
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject groupJson = gson.toJsonTree(folder).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, groupJson);
        return responseParser.parseToObject(Folder.class, response);
	}	
}