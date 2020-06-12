package edu.ksu.canvas.impl;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.SubmissionReader;
import edu.ksu.canvas.interfaces.SubmissionWriter;
import edu.ksu.canvas.model.Progress;
import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions;
import edu.ksu.canvas.requestOptions.MultipleSubmissionsOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class SubmissionImpl extends BaseImpl<Submission, SubmissionReader, SubmissionWriter> implements SubmissionReader, SubmissionWriter{
    private static final Logger LOG = Logger.getLogger(SubmissionImpl.class);

    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl      The base URL of your canvas instance
     * @param apiVersion         The version of the Canvas API (currently 1)
     * @param oauthToken         OAuth token to use when executing API calls
     * @param restClient         a RestClient implementation to use when talking to Canvas
     * @param connectTimeout     Timeout in seconds to use when connecting
     * @param readTimeout        Timeout in seconds to use when waiting for data to come back from an open connection
     * @param paginationPageSize How many objects to request per page on paginated requests
     * @param serializeNulls     Whether or not to include null fields in the serialized JSON. Defaults to false if null
     */
    public SubmissionImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                          int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<Progress> gradeMultipleSubmissionsBySection(MultipleSubmissionsOptions options) throws IOException {

        LOG.debug("assignment submission for section/" + options.getObjectId());
        String url = buildCanvasUrl("sections/" + options.getObjectId() + "/assignments/" + options.getAssignmentId() + "/submissions/update_grades", options.getOptionsMap());

        return gradeMultipleSubmissions(options, url);
    }

    @Override
    public Optional<Progress> gradeMultipleSubmissionsByCourse(MultipleSubmissionsOptions options) throws IOException {

        LOG.debug("assignment submission for course/" + options.getObjectId());
        String url = buildCanvasUrl("courses/" + options.getObjectId() + "/assignments/" + options.getAssignmentId() + "/submissions/update_grades", options.getOptionsMap());

        return gradeMultipleSubmissions(options, url);
    }

    @Override
    public List<Submission> listCourseSubmissions(final GetSubmissionsOptions options) throws IOException {
        if (options.getObjectId() == null || options.getAssignmentId() == null) {
            throw new IllegalArgumentException("Required CourseId/AssignmentId not found.");
        }
        LOG.debug(String.format("Listing assignment submissions for course %s, assignment %d", options.getObjectId(), options.getAssignmentId()));
        final String url = buildCanvasUrl(String.format("courses/%s/assignments/%d/submissions", options.getObjectId(), options.getAssignmentId()), options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Submission> listSectionSubmissions(final GetSubmissionsOptions options) throws IOException {
        if(options.getObjectId() == null || options.getAssignmentId() == null) {
            throw new IllegalArgumentException("Required SectionId/AssignmentId not found.");
        }
        LOG.debug(String.format("Listing assignment submissions for section %s, assignment %d", options.getObjectId(), options.getAssignmentId()));
        final String url = buildCanvasUrl(String.format("sections/%s/assignments/%d/submissions", options.getObjectId(), options.getAssignmentId()), options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<Submission> getSingleCourseSubmission(final GetSubmissionsOptions options) throws IOException {
        if(options.getObjectId()== null || options.getUserId()== null || options.getAssignmentId() == null) {
            throw new IllegalArgumentException("Required CourseId/AssignmentId/UserId not found.");
        }
        LOG.debug(String.format("Getting submission for course %s, assignment %d, user %s", options.getObjectId(), options.getAssignmentId(), options.getUserId()));
        final String url = buildCanvasUrl(String.format("courses/%s/assignments/%d/submissions/%s", options.getObjectId(), options.getAssignmentId(), options.getUserId()), options.getOptionsMap());
        return getFromCanvas(url);
    }

    @Override
    public Optional<Submission> getSingleSectionSubmission(final GetSubmissionsOptions options) throws IOException {
        if(options.getObjectId() == null || options.getUserId() == null || options.getAssignmentId() == null) {
            throw new IllegalArgumentException("Required SectionId/AssignmentId/UserId not found.");
        }
        LOG.debug(String.format("Getting submission for section %s, assignment %d, user %s", options.getObjectId(), options.getAssignmentId(), options.getUserId()));
        final String url = buildCanvasUrl(String.format("sections/%s/assignments/%d/submissions/%s", options.getObjectId(), options.getAssignmentId(), options.getUserId()), options.getOptionsMap());
        return getFromCanvas(url);
    }


    private Progress parseProgressResponse(final Response response) {
        return GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), Progress.class);
    }	

    @Override
    protected Type listType() {
        return new TypeToken<List<Submission>>(){}.getType();
    }

    @Override
    protected Class<Submission> objectType() {
        return Submission.class;
    }
	
    private Optional<Progress> gradeMultipleSubmissions(MultipleSubmissionsOptions options, String url) throws IOException {
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("grade_data", gson.toJsonTree(options.getStudentSubmissionOptionMap()));

        Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, jsonObject);

        Progress progress = parseSubmissionResponse(response);
        LOG.debug("ProgressId from assignment section submission response: " + progress.getId());

        return Optional.of(progress);
    }

    private Progress parseSubmissionResponse(final Response response) {
        return GsonResponseParser.getDefaultGsonParser(serializeNulls).fromJson(response.getContent(), Progress.class);
    }	
}