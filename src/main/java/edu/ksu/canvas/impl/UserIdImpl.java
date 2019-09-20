/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.UserIdReader;
import edu.ksu.canvas.interfaces.UserIdWriter;
import edu.ksu.canvas.model.UserId;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetUsersInCourseOptions;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * UserIdImpl
 * -- Description --
 *
 * @version $Revision$
 * @author David V (last $Author$)
 * $Id$
 */
public class UserIdImpl extends BaseImpl<UserId, UserIdReader, UserIdWriter> implements UserIdReader, UserIdWriter {
    private static final Logger LOG = Logger.getLogger(UserImpl.class);

    public UserIdImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                    int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }


    @Override
    public List<UserId> getUsersInCourse(GetUsersInCourseOptions options) throws IOException {
        LOG.debug("Retrieving users in course " + options.getCourseId());
        String url = buildCanvasUrl("courses/" + options.getCourseId() + "/users", options.getOptionsMap());
		java.util.logging.Logger.getAnonymousLogger().info("URL for lookup: " + url);
        return getListFromCanvas(url);
    }


    @Override
    protected Type listType() {
        return new TypeToken<List<UserId>>() {
        }.getType();
    }

    @Override
    protected Class<UserId> objectType() {
        return  UserId.class;
    }
}
