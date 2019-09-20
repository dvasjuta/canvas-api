package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent Canvas group membership. See
 * <a href="https://canvas.instructure.com/doc/api/groups.html">Canvas
 * group membership</a> documentation.
 */
@CanvasObject(postKey = "memberships")
public class GroupMembership extends BaseCanvasModel implements Serializable {

	public static final long serialVersionUID = 1L;

	private Integer id;


	private Integer groupId;

	private Integer userId;

	//   // The current state of the membership. Current possible values are 'accepted', 'invited', and 'requested'
	private String workflowState;

	private Boolean moderator;
	private Boolean justCreated;

	private Integer sisImportId;



	public GroupMembership() {
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@CanvasField(postKey = "sis_import_id")
	public Integer getSisImportId() {
		return sisImportId;
	}

	public void setSisImportId(Integer sisImportId) {
		this.sisImportId = sisImportId;
	}

	@CanvasField(postKey = "group_id")
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@CanvasField(postKey = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@CanvasField(postKey = "workflow_state")
	public String getWorkflowState() {
		return workflowState;
	}

	public void setWorkflowState(String workflowState) {
		this.workflowState = workflowState;
	}

	@CanvasField(postKey = "moderator")
	public Boolean getModerator() {
		return moderator;
	}

	public void setModerator(Boolean moderator) {
		this.moderator = moderator;
	}

	@CanvasField(postKey = "just_created")
	public Boolean getJustCreated() {
		return justCreated;
	}

	public void setJustCreated(Boolean justCreated) {
		this.justCreated = justCreated;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 83 * hash + Objects.hashCode(this.id);
		hash = 83 * hash + Objects.hashCode(this.groupId);
		hash = 83 * hash + Objects.hashCode(this.userId);
		hash = 83 * hash + Objects.hashCode(this.workflowState);
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
		final GroupMembership other = (GroupMembership) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.groupId, other.groupId)) {
			return false;
		}
		if (!Objects.equals(this.userId, other.userId)) {
			return false;
		}
		return true;
	}


}