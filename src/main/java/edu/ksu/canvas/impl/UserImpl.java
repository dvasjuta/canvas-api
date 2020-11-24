package edu.ksu.canvas.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.interfaces.UserWriter;
import edu.ksu.canvas.model.User;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.CreateUserOptions;
import edu.ksu.canvas.requestOptions.GetUserCustomDataOptions;
import edu.ksu.canvas.requestOptions.GetUsersInAccountOptions;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserImpl extends BaseImpl<User, UserReader, UserWriter> implements UserReader, UserWriter{
    private static final Logger LOG = Logger.getLogger(UserImpl.class);

    public UserImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                    int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<User> createUser(User user) throws InvalidOauthTokenException, IOException {
        return createUser(user, new CreateUserOptions());
    }

    @Override
    public Optional<User> createUser(User user, CreateUserOptions options) throws InvalidOauthTokenException, IOException {
        String createdUrl = buildCanvasUrl("accounts/" + CanvasConstants.ACCOUNT_ID + "/users", Collections.emptyMap());
        LOG.debug("create URl for user creation : " + createdUrl);
        Map<String, List<String>> parameterMap = options.getOptionsMap();
        parameterMap.putAll(user.toPostMap(serializeNulls));
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, parameterMap);
        if (response.getErrorHappened() || (response.getResponseCode() != 200)) {
            LOG.debug("Failed to create user, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(User.class, response);
    }

    @Override
    public Optional<User> updateUser(User user) throws InvalidOauthTokenException, IOException {
        if(user == null || user.getId() == 0) {
            throw new IllegalArgumentException("User to update must not be null and have a Canvas ID assigned");
        }
        LOG.debug("updating user in Canvas: " + user.getId());
        String url = buildCanvasUrl("users/" + String.valueOf(user.getId()), Collections.emptyMap());

        //I tried to use the JSON POST method but Canvas throws odd permission errors when trying to serialize the user
        //object, even for an admin user. I suspect some field within the User object can't be updated and instead of
        //ignoring it like most calls, the user call throws a permission error. So I had to fall back to sending a form POST
        //since it only serializes fields with a @CanvasField annotation
        Response response = canvasMessenger.putToCanvas(oauthToken, url, user.toPostMap(serializeNulls));
        return responseParser.parseToObject(User.class, response);
    }

    @Override
    public List<User> getUsersInCourse(GetUsersInCourseOptions options) throws IOException {
        LOG.debug("Retrieving users in course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/users", options.getOptionsMap());
        LOG.debug("Url being used: " + url);
        return getListFromCanvas(url);
    }

    @Override
    public Optional<User> showUserDetails(String userIdentifier) throws IOException {
        LOG.debug("Retrieving user details");
        String url = buildCanvasUrl("users/" + userIdentifier, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(User.class, response);
    }

    @Override
    public List<User> getUsersInAccount(GetUsersInAccountOptions options) throws IOException {
        LOG.debug("Retrieving users for account " + options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/users", options.getOptionsMap());
        return getListFromCanvas(url);
    }
    
    @Override
    public Optional<String> getUserCustomData(GetUserCustomDataOptions options) throws IOException {
	if(options.getUserId() == null || options.getUserId() == 0) {
            throw new IllegalArgumentException("User to update must not be null and have a Canvas ID assigned");
        }
        LOG.debug("Geting custom data for user in Canvas: " + options.getUserId());
	String baseUrl = "users/" + String.valueOf(options.getUserId()) + "/custom_data";
	baseUrl += (options.getScope() != null && options.getScope().length() > 0) ? "/"+options.getScope() : "";
        String url = buildCanvasUrl(baseUrl, options.getOptionsMap());
	Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return Optional.ofNullable(parseCustomData(response));
    }
    
    private String parseCustomData(final Response response) {
        JsonObject jsonObject = GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), JsonObject.class);
        return (!jsonObject.get("data").isJsonNull()) ? jsonObject.get("data").getAsJsonObject().toString() : null;
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<User>>() {
        }.getType();
    }

    @Override
    protected Class<User> objectType() {
        return User.class;
    }
}