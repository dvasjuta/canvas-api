package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent Canvas groups. See
 * <a href="https://canvas.instructure.com/doc/api/groups.html">Canvas
 * groups</a> documentation.
 */
@CanvasObject(postKey = "group")
public class Group extends BaseCanvasModel implements Serializable {

	public static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String description;
	private Boolean isPublic;
	private Boolean isFollowedByUser;
	// join_level -- ['parent_context_auto_join' or 'parent_context_request' or 'invitation_only']
	private String joinLevel;
	private Integer membersCount;
	private Integer defaultStorageQuotaMb;

	private String avatarUrl;
	private String contextType;

	private Integer courseId;
	private String role;

	private Integer groupCategoryId;

	private String sisGroupId;
	private Integer sisImportId;

	// permissions
	// private {CLASS} permission
	//  "permissions": {"create_discussion_topic":true,"create_announcement":true}

	// BELOW ARE NOT DOCUMENTED IN API
	private Boolean hasSubmission;
	private Boolean concluded;
	private Integer maxMembership;

	//  "has_submission": false,
//  "concluded": false
	//max_membership


	public Group() {
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

	@CanvasField(postKey = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@CanvasField(postKey = "join_level")
	public String getJoinLevel() {
		return joinLevel;
	}

	public void setJoinLevel(String joinLevel) {
		this.joinLevel = joinLevel;
	}

	@CanvasField(postKey = "members_count")
	public Integer getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(Integer membersCount) {
		this.membersCount = membersCount;
	}

	@CanvasField(postKey = "is_public")
	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@CanvasField(postKey = "followed_by_user")
	public Boolean getIsFollowedByUser() {
		return isFollowedByUser;
	}

	public void setIsFollowedByUser(Boolean isFollowedByUser) {
		this.isFollowedByUser = isFollowedByUser;
	}

	@CanvasField(postKey = "default_storage_quota_mb")
	public Integer getDefaultStorageQuotaMb() {
		return defaultStorageQuotaMb;
	}

	public void setDefaultStorageQuotaMb(Integer defaultStorageQuotaMb) {
		this.defaultStorageQuotaMb = defaultStorageQuotaMb;
	}

	@CanvasField(postKey = "avatar_url")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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

	@CanvasField(postKey = "group_category_id")
	public Integer getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(Integer groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}

	@CanvasField(postKey = "sis_group_id")
	public String getSisGroupId() {
		return sisGroupId;
	}

	public void setSisGroupId(String sisGroupId) {
		this.sisGroupId = sisGroupId;
	}

	@CanvasField(postKey = "sis_import_id")
	public Integer getSisImportId() {
		return sisImportId;
	}

	public void setSisImportId(Integer sisImportId) {
		this.sisImportId = sisImportId;
	}

	@CanvasField(postKey = "has_submission")
	public Boolean getHasSubmission() {
		return hasSubmission;
	}

	public void setHasSubmission(Boolean hasSubmission) {
		this.hasSubmission = hasSubmission;
	}

	public Boolean getConcluded() {
		return concluded;
	}

	public void setConcluded(Boolean concluded) {
		this.concluded = concluded;
	}

	@CanvasField(postKey = "max_membership")
	public Integer getMaxMembership() {
		return maxMembership;
	}

	public void setMaxMembership(Integer maxMembership) {
		this.maxMembership = maxMembership;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + Objects.hashCode(this.id);
		hash = 97 * hash + Objects.hashCode(this.name);
		hash = 97 * hash + Objects.hashCode(this.description);
		hash = 97 * hash + Objects.hashCode(this.contextType);
		hash = 97 * hash + Objects.hashCode(this.courseId);
		hash = 97 * hash + Objects.hashCode(this.groupCategoryId);
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
		final Group other = (Group) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.courseId, other.courseId)) {
			return false;
		}
		if (!Objects.equals(this.groupCategoryId, other.groupCategoryId)) {
			return false;
		}
		return true;
	}



}
