package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

@CanvasObject(postKey = "content_migration")
public class ContentMigration extends BaseCanvasModel implements Serializable {
    private Integer id;
    private String migrationType;
    private String name;
    private String workflowState;
    private Integer userId;
    private String migrationIssuesUrl;
    private Integer migrationIssuesCount;
    private String progressUrl;
    private Date startedAt;
    private Date finishedAt;

    @CanvasField(postKey = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
    @CanvasField(postKey = "migration_type")
    public String getMigrationType() {
        return migrationType;
    }

    public void setMigrationType(String migrationType) {
        this.migrationType = migrationType;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "workflow_state")
    public String getWorksflowState() {
        return workflowState;
    }

    public void setWorksflowState(String workflowState) {
        this.workflowState = workflowState;
    }
	
    @CanvasField(postKey = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }	

    @CanvasField(postKey = "migration_issues_url")
	public String getMigrationIssuesUrl() {
		return migrationIssuesUrl;
	}

	public void setMigrationIssuesUrl(String migrationIssuesUrl) {
		this.migrationIssuesUrl = migrationIssuesUrl;
	}

    @CanvasField(postKey = "migration_issues_count")
	public Integer getMigrationIssuesCount() {
		return migrationIssuesCount;
	}

	public void setMigrationIssuesCount(Integer migrationIssuesCount) {
		this.migrationIssuesCount = migrationIssuesCount;
	}

    @CanvasField(postKey = "progress_url")
	public String getProgressUrl() {
		return progressUrl;
	}

	public void setProgressUrl(String progressUrl) {
		this.progressUrl = progressUrl;
	}	

    @CanvasField(postKey = "started_at")
	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

    @CanvasField(postKey = "finished_at")
	public Date getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}
	
	/**
	 * 
	 * @return id from progressUrl (null if not available)
	 */
	public Long getProgressId() {
		if (StringUtils.contains(getProgressUrl(), "/progress/")) {
			final Path urlPath = Paths.get(getProgressUrl());
			final Path lastSegment = urlPath.getName(urlPath.getNameCount() - 1);
			return Long.parseLong(lastSegment.getFileName().toString());
		}
		return null;
	}
}