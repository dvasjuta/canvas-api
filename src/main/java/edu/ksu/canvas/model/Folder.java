package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

@CanvasObject(postKey = "folder")
public class Folder extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String fullName;
    private Date createdAt;
    private Date updatedAt;
	
    private Integer contextId;
    private String contextType;
	
	
	private Integer parentFolderId; //parent_folder_id
	private Boolean hidden;
	private Boolean locked;
	
	
	private Integer foldersCount;
	private Integer filesCount;
	private Integer position;

	private Boolean lockedForUser;
    private String lockInfo;

    @CanvasField(postKey = "id")
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

    @CanvasField(postKey = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

    @CanvasField(postKey = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @CanvasField(postKey = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    @CanvasField(postKey = "context_id")
	public Integer getContextId() {
		return contextId;
	}

	public void setContextId(Integer contextId) {
		this.contextId = contextId;
	}

    @CanvasField(postKey = "context_type")
	public String getContextType() {
		return contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
	}

    @CanvasField(postKey = "parent_folder_id")
	public Integer getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(Integer parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

    @CanvasField(postKey = "hidden")
	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

    @CanvasField(postKey = "locked")
	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

    @CanvasField(postKey = "folders_count")
	public Integer getFoldersCount() {
		return foldersCount;
	}

	public void setFoldersCount(Integer foldersCount) {
		this.foldersCount = foldersCount;
	}

    @CanvasField(postKey = "files_count")
	public Integer getFilesCount() {
		return filesCount;
	}

	public void setFilesCount(Integer filesCount) {
		this.filesCount = filesCount;
	}

    @CanvasField(postKey = "position")
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

    public Boolean getLockedForUser() {
        return lockedForUser;
    }

    public void setLockedForUser(Boolean lockedForUser) {
        this.lockedForUser = lockedForUser;
    }

    public String getLockInfo() {
        return lockInfo;
    }

    public void setLockInfo(String lockInfo) {
        this.lockInfo = lockInfo;
    }
}
