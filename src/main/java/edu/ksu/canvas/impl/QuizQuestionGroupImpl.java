package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.interfaces.QuizQuestionGroupReader;
import edu.ksu.canvas.interfaces.QuizQuestionGroupWriter;
import edu.ksu.canvas.model.BaseCanvasModel;
import edu.ksu.canvas.model.assignment.QuizQuestionGroup;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class QuizQuestionGroupImpl extends BaseImpl<QuizQuestionGroup, QuizQuestionGroupReader, QuizQuestionGroupWriter> implements QuizQuestionGroupReader, QuizQuestionGroupWriter {

	private static final Logger LOG = Logger.getLogger(QuizQuestionGroupReader.class);

	public QuizQuestionGroupImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
		int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
		super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
			paginationPageSize, serializeNulls);
	}

	@Override
	public Optional<QuizQuestionGroup> getSingleQuizQuestionGroup(String courseId, Integer quizId, Integer quizQuestionGroupId) throws IOException {
		LOG.debug("Retrieving single quiz question group " + quizQuestionGroupId + " inside " + quizId + " in course " + courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quizId + "/groups/" + quizQuestionGroupId, Collections.emptyMap());
		Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		return responseParser.parseToObject(QuizQuestionGroup.class, response);
	}

	@Override
	public Optional<QuizQuestionGroup> createQuizQuestionGroup(QuizQuestionGroup quizQuestionGroup, Integer courseId, Integer quizId) throws IOException {
		LOG.debug("Creating quiz question group in course " + courseId + " quiz " + quizId);
		String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quizId + "/groups", Collections.emptyMap());

		//java.util.logging.Logger.getAnonymousLogger().info("RA JSON: " + quizQuestionGroup.toJsonObject(serializeNulls).toString());
		//java.util.logging.Logger.getAnonymousLogger().info("JSON converted: " + convertToJsonObject(quizQuestionGroup).toString());
		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, convertToJsonObject(quizQuestionGroup));
//		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, quizQuestionGroup.toJsonObject(serializeNulls));

		Optional<TransformedQuizQuestionGroup> group = responseParser.parseToObject(TransformedQuizQuestionGroup.class, response);
		//java.util.logging.Logger.getAnonymousLogger().info("RESULT FOUND: " + group.isPresent());
		Optional<QuizQuestionGroup> result = Optional.empty();
		if (group.isPresent()) {
			//java.util.logging.Logger.getAnonymousLogger().info("RESULT: " + group.get().toString());
			if (group.get().getQuizGroups() != null) {
				java.util.ArrayList<HashMap> quizGroups = group.get().getQuizGroups();
				if (!quizGroups.isEmpty()) {
					//java.util.logging.Logger.getAnonymousLogger().info("Convert this: " + quizGroups.get(0));
					QuizQuestionGroup res = convertFromMap(quizGroups.get(0));
					if (res != null) {
						//java.util.logging.Logger.getAnonymousLogger().info("Converted to: " + res.toJsonObject(serializeNulls));
						result = Optional.of(res); //Optional.of(convertFromMap(quizGroups.get(0)));
					}
				}
			}

		}
		return result;
	}

	@Override
	public Optional<QuizQuestionGroup> updateQuizQuestionGroup(QuizQuestionGroup quizQuestionGroup, Integer courseId, Integer quizId) throws IOException {
		LOG.debug("Update quiz question group in course " + courseId + " quiz " + quizId);
		String url = buildCanvasUrl("courses/" + courseId + "/quizzes/" + quizId + "/groups/" + quizQuestionGroup.getId(), Collections.emptyMap());

		//java.util.logging.Logger.getAnonymousLogger().info("U RAW JSON: " + quizQuestionGroup.toJsonObject(serializeNulls).toString());
		//java.util.logging.Logger.getAnonymousLogger().info("U JSON converted: " + convertToJsonObject(quizQuestionGroup).toString());
		Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, convertToJsonObject(quizQuestionGroup));
//		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, quizQuestionGroup.toJsonObject(serializeNulls));

		Optional<TransformedQuizQuestionGroup> group = responseParser.parseToObject(TransformedQuizQuestionGroup.class, response);
		//java.util.logging.Logger.getAnonymousLogger().info("U RESULT FOUND: " + group.isPresent());
		Optional<QuizQuestionGroup> result = Optional.empty();
		if (group.isPresent()) {
			//java.util.logging.Logger.getAnonymousLogger().info("U RESULT: " + group.get().toString());
			if (group.get().getQuizGroups() != null) {
				java.util.ArrayList<HashMap> quizGroups = group.get().getQuizGroups();
				if (!quizGroups.isEmpty()) {
					//java.util.logging.Logger.getAnonymousLogger().info("U Convert this: " + quizGroups.get(0));
					QuizQuestionGroup res = convertFromMap(quizGroups.get(0));
					if (res != null) {
						//java.util.logging.Logger.getAnonymousLogger().info("U Converted to: " + res.toJsonObject(serializeNulls));					 
						result = Optional.of(res); //convertFromMap(quizGroups.get(0)));
					}
				}
			}

		}
		return result;
	}

	private JsonObject convertToJsonObject(QuizQuestionGroup quizQuestionGroup) {
		HashMap result = new HashMap();
		if (quizQuestionGroup == null) {
			return null;
		}
		if (StringUtils.isNotBlank(quizQuestionGroup.getName())) {
			result.put("name", quizQuestionGroup.getName());
		}
		if (quizQuestionGroup.getPosition() != null) {
			result.put("position", quizQuestionGroup.getPosition());
		}
		if (quizQuestionGroup.getPickCount() != null) {
			result.put("pick_count", quizQuestionGroup.getPickCount());
		}
		if (quizQuestionGroup.getQuestionPoints() != null) {
			result.put("question_points", quizQuestionGroup.getQuestionPoints());
		}
		if (quizQuestionGroup.getAssessmentQuestionBankId() != null) {
			result.put("assessment_question_bank_id", quizQuestionGroup.getAssessmentQuestionBankId());
		}
		JsonObject object = new JsonObject();
		java.util.ArrayList array = new java.util.ArrayList<>();
		array.add(result);

		object.add("quiz_groups", new Gson().toJsonTree(array));
		return object; //new Gson().toJsonTree(result).getAsJsonArray();
	}

	private QuizQuestionGroup convertFromMap(HashMap map) {
		QuizQuestionGroup result = new QuizQuestionGroup();
		if (map == null || map.isEmpty()) {
			return null;
		}
		if (StringUtils.isNotBlank((String) map.get("name"))) {
			result.setName((String) map.get("name"));
		}
//		if (result.containsKey("position") && StringUtils.isNotBlank( ((Double) result.get("position")).toString()  ))   {
		if (map.containsKey("position") && isDoubleValid(map.get("position"))) {
			result.setPosition(((Double) map.get("position")).intValue());
		}
		if (map.containsKey("id") && isDoubleValid(map.get("id"))) {
			result.setId(((Double) map.get("id")).intValue());
		}
		if (map.containsKey("quiz_id") && isDoubleValid(map.get("quiz_id"))) {
			result.setQuizId(((Double) map.get("quiz_id")).intValue());
		}
		if (map.containsKey("pick_count") && isDoubleValid(map.get("pick_count"))) {
			result.setPickCount(((Double) map.get("pick_count")).intValue());
		}
		if (map.containsKey("question_points") && isDoubleValid(map.get("question_points"))) {
			result.setQuestionPoints(((Double) map.get("question_points")).intValue());
		}
		if (map.containsKey("assessment_question_bank_id") && isDoubleValid(map.get("assessment_question_bank_id"))) { // StringUtils.isNotBlank( ((Double) result.get("assessment_question_bank_id")).toString()  ))   {
			result.setAssessmentQuestionBankId(((Double) map.get("assessment_question_bank_id")).intValue());
		}
		return result;
	}

	private boolean isDoubleValid(Object object) {
		return (object != null && object instanceof Double && !((Double) object).isNaN());
	}

	@Override
	protected Type listType() {
		return new TypeToken<List<QuizQuestionGroup>>() {
		}.getType();
	}

	@Override
	protected Class<QuizQuestionGroup> objectType() {
		return QuizQuestionGroup.class;
	}

}

/**
 * Used for JSON response 
 */
@CanvasObject(postKey = "quiz_groups")
class TransformedQuizQuestionGroup extends BaseCanvasModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private java.util.ArrayList<HashMap> quizGroups;

	public ArrayList<HashMap> getQuizGroups() {
		return quizGroups;
	}

	public void setQuizGroups(ArrayList<HashMap> quizGroups) {
		this.quizGroups = quizGroups;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.quizGroups);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TransformedQuizQuestionGroup other = (TransformedQuizQuestionGroup) obj;
		if (!Objects.equals(this.quizGroups, other.quizGroups)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TransformedQuizQuestionGroup{" + "quizGroups=" + quizGroups + '}';
	}
}
