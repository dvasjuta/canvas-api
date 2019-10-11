package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.model.Role;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListRolesOptions;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class RoleImpl extends BaseImpl<Role, RoleReader, RoleWriter> implements RoleReader, RoleWriter {

	private static final Logger LOG = Logger.getLogger(RoleImpl.class);

	public RoleImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
			int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
		super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
				paginationPageSize, serializeNulls);
	}

	@Override
	public List<Role> listRoles(ListRolesOptions options) throws IOException {
		LOG.debug("Retrieving roles for account " + options.getAccountId());
		String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/roles", options.getOptionsMap());
		return getListFromCanvas(url);
	}

	@Override
	public Optional<Role> getRole(Integer accountId, Long id) throws IOException {
		LOG.info("Getting role: " + id);
		String url = buildCanvasUrl("accounts/" + accountId.toString() + "/roles/" + id.toString(), Collections.emptyMap());
//		parseRoleList(canvasMessenger.getSingleResponseFromCanvas(oauthToken, url));
		Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		JsonElement root = new JsonParser().parse(response.getContent());
		JsonObject object = root.getAsJsonObject().get("permissions").getAsJsonObject();

		//Iterate over this map
		Gson gson = new Gson();
		Map<String, Role.PermissionBreakdown> permissions = new HashMap<>();
		for (Entry<String, JsonElement> entry : object.entrySet()) {
			Role.PermissionBreakdown shoppingList = gson.fromJson(entry.getValue(), Role.PermissionBreakdown.class);
			permissions.put(entry.getKey(), shoppingList);
		}

		Optional<Role> result = getFromCanvas(url);
		if (result.isPresent()) {
			result.get().setPermissions(permissions);
		}
		return result;
	}

	@Override
	protected Type listType() {
		return new TypeToken<List<Role>>() {
		}.getType();
	}

	@Override
	protected Class<Role> objectType() {
		return Role.class;
	}

}
