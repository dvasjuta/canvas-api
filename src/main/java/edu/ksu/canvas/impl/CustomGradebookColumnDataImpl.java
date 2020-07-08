/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CustomGradebookColumnDataReader;
import edu.ksu.canvas.interfaces.CustomGradebookColumnDataWriter;
import edu.ksu.canvas.model.CustomGradebookColumn;
import edu.ksu.canvas.model.CustomGradebookColumnData;
import edu.ksu.canvas.model.Group;
import edu.ksu.canvas.model.status.Delete;
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
public class CustomGradebookColumnDataImpl extends BaseImpl<CustomGradebookColumnData, CustomGradebookColumnDataReader, CustomGradebookColumnDataWriter> implements CustomGradebookColumnDataReader,
    CustomGradebookColumnDataWriter {

	private static final Logger LOG = Logger.getLogger(CustomGradebookColumnDataReader.class);

    public CustomGradebookColumnDataImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
        int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
            paginationPageSize, serializeNulls);
    }

    @Override
    protected Class<CustomGradebookColumnData> objectType() {
        return CustomGradebookColumnData.class;
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<CustomGradebookColumnData>>() {
        }.getType();
    }

	@Override
	public List<CustomGradebookColumnData> listCustomGradebookColumnData(int courseId, int columnId) throws IOException {
        LOG.debug("Looking up custom_gradebook_columns data for course " + courseId + " and column " + columnId);
        String url = buildCanvasUrl(String.format("courses/%s/custom_gradebook_columns/%s/data", courseId, columnId), Collections.emptyMap());
        return getListFromCanvas(url);
	}

	@Override
	public Optional<CustomGradebookColumnData> updateCustomGradebookColumnData(int courseId, CustomGradebookColumnData columnData, boolean allowDeletion) throws IOException {
        LOG.debug("updating course " + courseId + " custom_gradebook_column " + (columnData != null ? columnData.getColumnId() : "uh oh"));
        if (columnData == null) {
            throw new IllegalArgumentException("Required id value is invalid.");
        }
        if (!allowDeletion && columnData.isContentBlank()) {
            throw new IllegalArgumentException("Deletion of column data not allowed (allowDeletion is false and content is blank)");
        }
        String url = buildCanvasUrl("courses/" + courseId + "/custom_gradebook_columns/" + columnData.getColumnId() + "/data/" + columnData.getUserId(), Collections.emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, columnData.toJsonObject(serializeNulls));
        return responseParser.parseToObject(CustomGradebookColumnData.class, response);
	}
	
	@Override
	public boolean updateCustomGradebookColumnData(int courseId, List<CustomGradebookColumnData> columnData, boolean allowDeletion) throws IOException {
        LOG.debug("updating course " + courseId + " custom_gradebook_column_data " + (columnData != null ? columnData.size(): "delete all data"));
        if (!allowDeletion && (columnData == null || columnData.isEmpty())) {
            throw new IllegalArgumentException("Deletion of column data not allowed (allowDeletion is false and content is null/empty)");
        }
        String url = buildCanvasUrl("courses/" + courseId + "/custom_gradebook_column_data", Collections.emptyMap());
		Response response = null;
		if (allowDeletion && (columnData == null || columnData.isEmpty())) {
	        response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, new JsonObject());
		} else {
			JsonObject column_data = new JsonObject();
			JsonArray array = new JsonArray();
			for (CustomGradebookColumnData data : columnData) {
			//	JsonObject object = data.toJsonObject(Boolean.FALSE);
				JsonObject transposed = new JsonObject();
				transposed.addProperty("user_id",  data.getUserId()); // //object.get("user_id").getAsBigInteger() );
				transposed.addProperty("column_id", data.getColumnId()); // object.get("column_id").getAsBigInteger() );
				transposed.addProperty("content", data.getContent()); // object.get("content").getAsString() );
				array.add(transposed);
			}
			column_data.add("column_data",  array); //.getAsJsonArray());
	        response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, column_data); //.toJsonObject(serializeNulls));
		}			
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            LOG.debug("Failed to update custom_gradebook_column data, error message: " + response.toString());
            return false;
        }
		return true;
	}

}
