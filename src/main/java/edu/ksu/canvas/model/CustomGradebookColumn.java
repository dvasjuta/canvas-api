package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

/**
 * Class to represent Custom Grade Column Canvas.
 * See https://canvas.instructure.com/doc/api/custom_gradebook_columns.html
 */
@CanvasObject(postKey = "column")
public class CustomGradebookColumn extends BaseCanvasModel implements Serializable {

    public static final long serialVersionUID = 1L;


    private Integer id;
    private Integer courseId;
    private Integer position;
    private String title;
    private boolean hidden;
    private boolean readOnly;
    private String teacherNotes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    @CanvasField(postKey = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @CanvasField(postKey = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @CanvasField(postKey = "hidden")
    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @CanvasField(postKey = "read_only")
    public boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @CanvasField(postKey = "teacher_notes")
    public String getTeacherNotes() {
        return teacherNotes;
    }

    public void setTeacherNotes(String teacherNotes) {
        this.teacherNotes = teacherNotes;
    }
}