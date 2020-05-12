/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CustomGradebookColumnReader;
import edu.ksu.canvas.interfaces.CustomGradebookColumnWriter;
import edu.ksu.canvas.model.CustomGradebookColumn;
import edu.ksu.canvas.model.Group;
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
public class CustomGradebookColumnImpl extends BaseImpl<CustomGradebookColumn, CustomGradebookColumnReader, CustomGradebookColumnWriter> implements CustomGradebookColumnReader,
    CustomGradebookColumnWriter {

    private static final Logger LOG = Logger.getLogger(CustomGradebookColumnReader.class);

    public CustomGradebookColumnImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
            paginationPageSize, serializeNulls);
    }

    @Override
    protected Class<CustomGradebookColumn> objectType() {
        return CustomGradebookColumn.class;
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<CustomGradebookColumn>>() {
        }.getType();
    }

    @Override
    public List<CustomGradebookColumn> listCustomGradebookColumns(int courseId) throws IOException {
        LOG.debug("Looking up custom_gradebook_columns for course " + courseId);
        String url = buildCanvasUrl(String.format("courses/%s/custom_gradebook_columns", courseId), Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<CustomGradebookColumn> createCustomGradebookColumns(CustomGradebookColumn customGradebookColumn) throws IOException {
        LOG.debug("creating custom_gradebook_columns for course " + (customGradebookColumn != null ? customGradebookColumn.getCourseId() : "uh oh"));
        if (customGradebookColumn.getCourseId() == null || customGradebookColumn.getCourseId() <= 0) {
            throw new IllegalArgumentException("Required name in courseId was not found.");
        }
        LOG.debug("creating custom_gradebook_columns for course " + customGradebookColumn.getCourseId());

        Objects.requireNonNull(customGradebookColumn.getTitle(), "title must be set to create a custom gradebook column.");
        String createdUrl = buildCanvasUrl("courses/" + customGradebookColumn.getCourseId() + "/custom_gradebook_columns", Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, createdUrl, customGradebookColumn.toPostMap(serializeNulls));

        return responseParser.parseToObject(CustomGradebookColumn.class, response);
    }

    @Override
    public Optional<CustomGradebookColumn> updateCustomGradebookColumns(CustomGradebookColumn customGradebookColumn) throws IOException {
        LOG.debug("updating custom_gradebook_column" + (customGradebookColumn != null ? customGradebookColumn.getCourseId() : "uh oh"));
        if (customGradebookColumn == null || customGradebookColumn.getId() == null) {
            throw new IllegalArgumentException("Required id value is invalid.");
        }
        if (customGradebookColumn == null || customGradebookColumn.getCourseId() == null) {
            throw new IllegalArgumentException("Required value in courseId was not found.");
        }
        String url = buildCanvasUrl("courses/" + customGradebookColumn.getCourseId() + "/custom_gradebook_columns/" + customGradebookColumn.getId(), Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, customGradebookColumn.toJsonObject(serializeNulls));
        return responseParser.parseToObject(CustomGradebookColumn.class, response);
    }

    @Override
    public boolean deleteCustomGradebookColumns(int courseId, int customGradebookColumnId) throws IOException {
        LOG.debug("deleting custom_gradebook_column " + customGradebookColumnId);
        String url = buildCanvasUrl("courses/" + courseId + "/custom_gradebook_columns/" + customGradebookColumnId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        LOG.debug("response " + response.toString());
        if(response.getErrorHappened() || response.getResponseCode() != 200){
            LOG.debug("Failed to delete custom_gradebook_column, error message: " + response.toString());
            return false;
        }
        return true;
    }

}
