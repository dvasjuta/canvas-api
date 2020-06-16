package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import java.beans.Transient;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * Class to represent Custom Grade Column Data Canvas.
 * See https://canvas.instructure.com/doc/api/custom_gradebook_columns.html
 */
@CanvasObject(postKey = "column_data")
public class CustomGradebookColumnData extends BaseCanvasModel implements Serializable {
	
	private Integer userId;
	private Integer columnId;
	private String content;

    @CanvasField(postKey = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

    @CanvasField(postKey = "column_id")
	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

    @CanvasField(postKey = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Transient
	public boolean isContentBlank() {
		return StringUtils.isBlank(getContent());
	}

	@Transient
	public boolean isContentNotBlank() {
		return StringUtils.isNotBlank(getContent());
	}
}
