package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.ContentMigrationReader;
import edu.ksu.canvas.interfaces.ContentMigrationWriter;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.model.Progress;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;
import edu.ksu.canvas.requestOptions.MultipleSubmissionsOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ContentMigrationImpl extends BaseImpl<ContentMigration, ContentMigrationReader, ContentMigrationWriter> implements ContentMigrationReader, ContentMigrationWriter {

    private static final Logger LOG = Logger.getLogger(ContentMigrationWriter.class);

    public ContentMigrationImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public  Optional<ContentMigration> getCourseContentMigration(String courseId, Integer id) throws IOException {
        LOG.info("listing a content migration for the course");
        String url = buildCanvasUrl("courses/" + courseId + "/content_migrations/" + id.toString(), Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    public Optional<ContentMigration> createCourseContentMigration(CreateCourseContentMigrationOptions options) throws IOException {
        LOG.debug("creating course content migration");
        String url = buildCanvasUrl("courses/" + options.getDestinationCourseId() + "/content_migrations", Collections.emptyMap());
		Response response = canvasMessenger.sendToCanvas(oauthToken, url, options.getOptionsMap());
		return responseParser.parseToObject(ContentMigration.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Course>>(){}.getType();
    }

    @Override
    protected Class<ContentMigration> objectType() {
        return ContentMigration.class;
    }

	@Override
    public Optional<Progress> getProgress(Long progressId) throws IOException {
		LOG.debug("get course content migration progress id = " + progressId);
        String url = buildCanvasUrl("progress/" + (progressId != null ? progressId : -1), Collections.emptyMap());
		Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject jsonObject = new JsonObject();
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		Progress progress = parseSubmissionResponse(response);
        return Optional.of(progress);
    } 
	
	@Override
    public Optional<Progress> getProgress(String progressUrl) throws IOException {
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject jsonObject = new JsonObject();
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, progressUrl);
		Progress progress = parseSubmissionResponse(response);
        LOG.debug("ProgressId from contentMigration: " + progress.getId());
        return Optional.of(progress);
    }

    private Progress parseSubmissionResponse(final Response response) {
        return GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), Progress.class);
    }
}