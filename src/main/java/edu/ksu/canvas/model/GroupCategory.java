package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent Canvas Group Categories. See
 * <a href="https://canvas.instructure.com/doc/api/group_categories.html">Canvas
 * Group Category</a> documentation.
 */
@CanvasObject(postKey = "group_categories")
public class GroupCategory  extends BaseCanvasModel implements Serializable {

	public static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	// Currently, these include: 'communities', 'student_organized', and 'imported'. Regular course/account group categories have a role of null.
	private String role;

	//  'restricted' or 'enabled' or null
	private String selfSignup;

	//  'first' or 'random' or null
	private String autoLeader;

	private String contextType;


	private Integer groupLimit;

//	private Integer groupCategoryId;

	private String sisGroupCategoryId;
	private Integer sisImportId;

	// progress
	// private Progress progress
	// "progress": null

	// BELOW ARE NOT DOCUMENTED IN API
	private Integer courseId;
	private Boolean allowsMultipleMemberships;
	// because we cant use 'protected'
	private Boolean isProtected;

	private Boolean isMember;


	public GroupCategory() {
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


	@CanvasField(postKey = "self_signup")
	public String getSelfSignup() {
		return selfSignup;
	}

	public void setSelfSignup(String selfSignup) {
		this.selfSignup = selfSignup;
	}

	@CanvasField(postKey = "auto_leader")
	public String getAutoLeader() {
		return autoLeader;
	}

	public void setAutoLeader(String autoLeader) {
		this.autoLeader = autoLeader;
	}


	@CanvasField(postKey = "group_limit")
	public Integer getGroupLimit() {
		return groupLimit;
	}

	public void setGroupLimit(Integer groupLimit) {
		this.groupLimit = groupLimit;
	}


	@CanvasField(postKey = "context_type")
	public String getContextType() {
		return contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
	}

	@CanvasField(postKey = "course_id")
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/*@CanvasField(postKey = "group_category_id")
	public Integer getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(Integer groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}*/

	@CanvasField(postKey = "sis_group_category_id")
	public String getSisGroupCategoryId() {
		return sisGroupCategoryId;
	}

	public void setSisGroupCategoryId(String sisGroupCategoryId) {
		this.sisGroupCategoryId = sisGroupCategoryId;
	}

	@CanvasField(postKey = "sis_import_id")
	public Integer getSisImportId() {
		return sisImportId;
	}

	public void setSisImportId(Integer sisImportId) {
		this.sisImportId = sisImportId;
	}

	@CanvasField(postKey = "allows_multiple_memberships")
	public Boolean getAllowsMultipleMemberships() {
		return allowsMultipleMemberships;
	}

	public void setAllowsMultipleMemberships(Boolean allowsMultipleMemberships) {
		this.allowsMultipleMemberships = allowsMultipleMemberships;
	}



	@CanvasField(postKey = "protected")
	public Boolean getIsProtected() {
		return isProtected;
	}

	public void setIsProtected(Boolean isProtected) {
		this.isProtected = isProtected;
	}


	@CanvasField(postKey = "is_member")
	public Boolean getIsMember() {
		return isMember;
	}

	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + Objects.hashCode(this.id);
		hash = 59 * hash + Objects.hashCode(this.name);
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
		final GroupCategory other = (GroupCategory) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}


}
