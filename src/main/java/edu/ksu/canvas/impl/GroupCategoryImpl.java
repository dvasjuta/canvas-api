package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.GroupCategoryReader;
import edu.ksu.canvas.interfaces.GroupCategoryWriter;

import edu.ksu.canvas.model.GroupCategory;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class GroupCategoryImpl extends BaseImpl<GroupCategory, GroupCategoryReader, GroupCategoryWriter> implements GroupCategoryReader,
        GroupCategoryWriter {

    private static final Logger LOG = Logger.getLogger(GroupCategoryImpl.class);

    public GroupCategoryImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

	@Override
	public List<GroupCategory> listGroupCategories(String courseId) throws IOException {
		LOG.debug("Looking up group categories for course " + courseId);
		String url = buildCanvasUrl(String.format("courses/%s/group_categories", courseId), Collections.emptyMap());
		return getListFromCanvas(url);
	}

	@Override
	public Optional<GroupCategory> getGroupCategory(Integer id) throws IOException {
		LOG.info("Getting group: " + id);
		String url = buildCanvasUrl("/group_categories/" + id.toString(), Collections.emptyMap());
		return getFromCanvas(url);

	}

    @Override
    protected Type listType() {
        return new TypeToken<List<GroupCategory>>(){}.getType();
    }

    @Override
    protected Class<GroupCategory> objectType() {
        return GroupCategory.class;
    }


	@Override
	public Optional<GroupCategory> createGroupCategory(String courseId, GroupCategory groupCategory) throws IOException {
        LOG.debug("creating group category for course " + courseId);
        if (courseId == null) {
            throw new IllegalArgumentException("CourseId is not found.");
        }
		//if (groupCategory.getName() == null) {
        //    throw new IllegalArgumentException("Required name in GroupCategory was not found.");
        //}
		if (groupCategory.getCourseId() == null || groupCategory.getCourseId() <= 0) {
            throw new IllegalArgumentException("Required name in courseId was not found.");
		}
        Objects.requireNonNull(groupCategory.getName(), "name must be set to create a group category.");
        String url = buildCanvasUrl("courses/" + courseId + "/group_categories", Collections.emptyMap());
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject groupJson = gson.toJsonTree(groupCategory).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, groupJson);
        return responseParser.parseToObject(GroupCategory.class, response);
	}

	@Override
	public Optional<GroupCategory> updateGroupCategory(GroupCategory groupCategory) throws IOException {
        LOG.debug("updating group category " + groupCategory.getId());
        String url = buildCanvasUrl("/group_categories/" + groupCategory.getId(), Collections.emptyMap());
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject groupJson = gson.toJsonTree(groupCategory).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, groupJson);
        return responseParser.parseToObject(GroupCategory.class, response);
	}

	@Override
	public Optional<GroupCategory> deleteGroupCategory(String groupCategoryId) throws IOException {
        LOG.debug("deleting group category " + groupCategoryId);
        String url = buildCanvasUrl("group_categories/" + groupCategoryId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        LOG.debug("response " + response.toString());
        if(response.getErrorHappened() || response.getResponseCode() != 200){
            LOG.debug("Failed to delete group category, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(GroupCategory.class, response);
	}
}
