package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.GroupReader;
import edu.ksu.canvas.interfaces.GroupWriter;

import edu.ksu.canvas.model.Group;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


public class GroupImpl extends BaseImpl<Group, GroupReader, GroupWriter> implements GroupReader,
        GroupWriter {

    private static final Logger LOG = Logger.getLogger(GroupImpl.class);

    public GroupImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

	@Override
	public List<Group> listGroups(String courseId) throws IOException {
        LOG.debug("Looking up groups for course " + courseId);
        String url = buildCanvasUrl(String.format("courses/%s/groups", courseId), Collections.emptyMap());
        return getListFromCanvas(url);
	}

	@Override
	public List<Group> listGroupsInGroupCategory(Integer groupCategoryId) throws IOException {
        LOG.debug("Looking up groups in group category " + groupCategoryId);
        String url = buildCanvasUrl(String.format("group_categories/%s/groups", groupCategoryId), Collections.emptyMap());
        return getListFromCanvas(url);
	}

    @Override
    protected Type listType() {
        return new TypeToken<List<Group>>(){}.getType();
    }

    @Override
    protected Class<Group> objectType() {
        return Group.class;
    }

	@Override
	public Optional<Group> getGroup(Integer id) throws IOException {
        LOG.info("Getting group: "+ id);
        String url = buildCanvasUrl("/groups/"+ id.toString(), Collections.emptyMap());
        return getFromCanvas(url);
	}

	@Override
	public Optional<Group> createGroup(String courseId, Group group) throws IOException {
        LOG.debug("creating group for course " + courseId);
		if (group.getCourseId() == null || group.getCourseId() <= 0) {
            throw new IllegalArgumentException("Required name in courseId was not found.");
		}
		if (courseId == null) {
            throw new IllegalArgumentException("CourseId is not found.");
        }

        Objects.requireNonNull(group.getName(), "name must be set to create a group.");
        String createdUrl = buildCanvasUrl("groups/" + courseId, Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, group.toPostMap(serializeNulls));

        return responseParser.parseToObject(Group.class, response);
	}

	@Override
    public Optional<Group> createGroup(String courseId, String groupCategoryId, Group group) throws IOException {
        LOG.debug("creating group for course " + courseId + " in group category: " + groupCategoryId);
		if (group.getCourseId() == null || group.getCourseId() <= 0) {
            throw new IllegalArgumentException("Required name in courseId was not found.");
		}
		if (courseId == null) {
            throw new IllegalArgumentException("CourseId is not found.");
        }
        Objects.requireNonNull(group.getName(), "name must be set to create a group.");
        //Map<String, List<String>> params = new HashMap<>();
        String url = buildCanvasUrl("group_categories/" + groupCategoryId + "/groups", Collections.emptyMap());
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject groupJson = gson.toJsonTree(group).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, groupJson);
        return responseParser.parseToObject(Group.class, response);
    }


    @Override
    public Optional<Group> updateGroup(Group group) throws IOException {
        LOG.debug("updating group  " + group.getId());
        boolean useJson = true;
        if (useJson) {
            String url = buildCanvasUrl("/groups/" + group.getId(), Collections.emptyMap());
	        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
		    JsonObject groupJson = gson.toJsonTree(group).getAsJsonObject();
                     java.util.logging.Logger.getAnonymousLogger().info("json: " + groupJson.toString());
			Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, groupJson);
            return responseParser.parseToObject(Group.class, response);
        } else {
            String url = buildCanvasUrl("groups/" + group.getId(), Collections.emptyMap());
            Map<String, List<String>> parameters = group.toPostMap(false);
            Response response = canvasMessenger.putToCanvas(oauthToken, url, parameters);
            return responseParser.parseToObject(Group.class, response);
        }
    }

	@Override
	public Optional<Group> deleteGroup(String groupId) throws IOException {
        LOG.debug("deleting group " + groupId);
        String url = buildCanvasUrl("/groups/" + groupId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        LOG.debug("response " + response.toString());
        if(response.getErrorHappened() || response.getResponseCode() != 200){
            LOG.debug("Failed to delete group, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(Group.class, response);
	}
}