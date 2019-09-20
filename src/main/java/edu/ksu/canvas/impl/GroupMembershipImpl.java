package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.GroupMembershipReader;
import edu.ksu.canvas.interfaces.GroupMembershipWriter;
import edu.ksu.canvas.model.GroupMembership;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import java.io.IOException;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class GroupMembershipImpl extends BaseImpl<GroupMembership, GroupMembershipReader, GroupMembershipWriter> implements GroupMembershipReader,
        GroupMembershipWriter {

    private static final Logger LOG = Logger.getLogger(GroupMembershipImpl.class);

    public GroupMembershipImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<GroupMembership>>(){}.getType();
    }

    @Override
    protected Class<GroupMembership> objectType() {
        return GroupMembership.class;
    }

	@Override
	public List<GroupMembership> listGroupMembership(Integer groupId) throws IOException {
        LOG.debug("Looking up group membership for group " + groupId);
        String url = buildCanvasUrl(String.format("groups/%s/memberships", groupId), Collections.emptyMap());
        return getListFromCanvas(url);
	}

	@Override
	public List<GroupMembership> listGroupMemebershipInGroupCategory(Integer groupCategoryId) throws IOException {
        LOG.debug("Looking up group membership for group category " + groupCategoryId);
        String url = buildCanvasUrl(String.format("group_categories/%s/user", groupCategoryId), Collections.emptyMap());
        return getListFromCanvas(url);
	}

	@Override
	public Optional<GroupMembership> enrollUserInGroup(GroupMembership groupMembership) throws IllegalArgumentException, IOException {
        if (groupMembership.getGroupId() == null) {
            throw new IllegalArgumentException("Required groupId in GroupMembership was not found.");
        }
        if (groupMembership.getUserId() == null) {
            throw new IllegalArgumentException("Required userId in GroupMembership was not found.");
        }

		String createdUrl =  buildCanvasUrl("groups/" + groupMembership.getGroupId() + "/memberships", Collections.emptyMap());
        LOG.debug("create URl for course group membership: "+ createdUrl);


		Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject groupJson = gson.toJsonTree(groupMembership).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, createdUrl, groupJson);
        if (response.getErrorHappened() ||  response.getResponseCode() != 200) {
            LOG.error("Failed to add users to group. Error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(GroupMembership.class,response);
	}

	@Override
	public Optional<GroupMembership> enrollUserInGroupCategory(Integer groupCategoryId, GroupMembership groupMembership) throws IllegalArgumentException, IOException {
        if (groupMembership.getGroupId() == null) {
            throw new IllegalArgumentException("Required groupId in GroupMembership was not found.");
        }
        if (groupMembership.getUserId()== null) {
            throw new IllegalArgumentException("Required userId in GroupMembership was not found.");
        }
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean dropGroupMembership(String groupMembershipId, String groupId) throws IOException, IllegalArgumentException {
        LOG.debug("deleting group membership " + groupMembershipId + " for group " + groupId);
        if (groupMembershipId == null) {
            throw new IllegalArgumentException("Required groupMembershipId in GroupMembership was not found.");
        }
        if (groupId == null) {
            throw new IllegalArgumentException("Required groupId in GroupMembership was not found.");
        }
		String url = buildCanvasUrl("/groups/" + groupId + "/memberships/" + groupMembershipId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        if (response.getErrorHappened() || response.getResponseCode() != 200){
            LOG.debug("Failed to delete group membership, Error message: " + response.toString());
            return false;
        }
		return true;
	}

	//@Override
	public Optional<GroupMembership> dropGroupMembershipInCategory(String groupCategoryId, String groupMembershipId) throws IOException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Optional<GroupMembership> updateGroupMembership(GroupMembership groupMembership) throws IllegalArgumentException, IOException {
        if (groupMembership.getId()== null) {
            throw new IllegalArgumentException("Required id in GroupMembership was not found.");
        }
        if (groupMembership.getGroupId() == null) {
            throw new IllegalArgumentException("Required groupId in GroupMembership was not found.");
        }
        if (groupMembership.getUserId()== null) {
            throw new IllegalArgumentException("Required userId in GroupMembership was not found.");
        }

        LOG.debug("Updating group membership for group " + groupMembership.getId());
        String url = buildCanvasUrl("groups/" + groupMembership.getGroupId() + "/memberships/" + groupMembership.getId() , Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, groupMembership.toJsonObject(serializeNulls));
        return responseParser.parseToObject(GroupMembership.class, response);
	}
}