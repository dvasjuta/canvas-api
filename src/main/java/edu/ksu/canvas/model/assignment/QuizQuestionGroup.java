package edu.ksu.canvas.model.assignment;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;
import java.util.Objects;

@CanvasObject(postKey = "quiz_groups")
public class QuizQuestionGroup extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer quizId;
    private String name;
    private Integer pickCount;
	private Integer questionPoints;
	private Integer assessmentQuestionBankId;
	private Integer position;
	
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")//, array = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "position")//, array = false)
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @CanvasField(postKey = "quiz_id")//, array = false)
	public Integer getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

    @CanvasField(postKey = "pick_count")//, array = false)
	public Integer getPickCount() {
		return pickCount;
	}

	public void setPickCount(Integer pickCount) {
		this.pickCount = pickCount;
	}

    @CanvasField(postKey = "question_points")//, array = false)
	public Integer getQuestionPoints() {
		return questionPoints;
	}

	public void setQuestionPoints(Integer questionPoints) {
		this.questionPoints = questionPoints;
	}

    @CanvasField(postKey = "assessment_question_bank_id")//, array = false)
	public Integer getAssessmentQuestionBankId() {
		return assessmentQuestionBankId;
	}

	public void setAssessmentQuestionBankId(Integer assessmentQuestionBankId) {
		this.assessmentQuestionBankId = assessmentQuestionBankId;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 67 * hash + Objects.hashCode(this.id);
		hash = 67 * hash + Objects.hashCode(this.quizId);
		hash = 67 * hash + Objects.hashCode(this.name);
		hash = 67 * hash + Objects.hashCode(this.pickCount);
		hash = 67 * hash + Objects.hashCode(this.questionPoints);
		hash = 67 * hash + Objects.hashCode(this.assessmentQuestionBankId);
		hash = 67 * hash + Objects.hashCode(this.position);
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
		final QuizQuestionGroup other = (QuizQuestionGroup) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.quizId, other.quizId)) {
			return false;
		}
		return true;
	}
}