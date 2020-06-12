package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetSubmissionsOptions extends BaseOptions {

    private Integer objectId;
    private Integer userId;
    private Integer assignmentId;

    public enum Include {
        ASSIGNMENT, COURSE, USER, VISIBILITY;

        public String toString() {
            return name().toLowerCase();
        }
    }
	
	public enum EnrollmentState{
        ACTIVE, CONCLUDED;

        @Override
        public String toString() { return name().toLowerCase(); }
    }
	
	public enum WorkflowState{
        SUBMITTED, UNSUBMITTED, GRADED, PENDING_REVIEW;

        @Override
        public String toString() { return name().toLowerCase(); }
    }
	
	/**
     * Construct options class with required parameters to retrieve a list of Submissions from courses or sections
     * @param objectId Required: ID of the course or subject to query.
     */
    public GetSubmissionsOptions(Integer objectId) {
        this.objectId = objectId;
    }

    /**
     * Construct options class with required parameters to retrieve a list of Submissions from courses or sections
     * @param objectId Required: ID of the course or subject to query.
     * @param assignmentId The Canvas ID of the assignment to query for submissions
     */
    public GetSubmissionsOptions(Integer objectId, Integer assignmentId) {
        this.objectId = objectId;
        this.assignmentId = assignmentId;
    }

    /*
     * Construct options class with required parameters to retrieve a single user's Submission from a course or section
     * @param courseId Required: ID of the course to get. May be of the form sis_course_id:abc123
     * @param assignmentId The Canvas ID of the assignment
     * @param userId The Canvas ID of the user to query (or SIS user ID with the appropriate prefix)
     */
    /*public GetSubmissionsOptions(Integer courseId, Integer assignmentId, Integer userId) {
        this.courseId = courseId;
        this.assignmentId = assignmentId;
        this.userId = userId;
    }*/

    /**
     * Optionally include more information with the returned assignment Submission objects.
     * @param includes List of optional includes
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions includes(final List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * When set to true, response will be grouped by student groups.
     * Only valid for Submission lists, not individual submission queries.
     * @param grouped Whether to group submissions by student group
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions grouped(Boolean grouped) {
        addSingleItem("grouped", Boolean.toString(grouped));
        return this;
    }

    /**
     * Uses special "student_ids[]=all" when listing all submissions
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions studentIdsAll() {
        addSingleItem("student_ids[]", "all");
        return this;
    }

    /**
     * Filter users by enrollment state
     * @param enrollmentStates List of enrollment states to filter submissions by
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions enrollmentState(List<EnrollmentState> enrollmentStates) {
        addEnumList("enrollment_state[]", enrollmentStates);
        return this;
    }
	
	 /**
     * Filter users by enrollment state
     * @param workflowStates List of worlflow states to filter submissions by
     * @return This object to allow adding more options
     */
    public GetSubmissionsOptions workflowState(List<WorkflowState> workflowStates) {
        addEnumList("workflow_state[]", workflowStates);
        return this;
    }
	
	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}
	
}
