package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

/**
 * Class to represent Communication Channel in Canvas.
 * See https://canvas.instructure.com/doc/api/communication_channels.html
 */
@CanvasObject(postKey = "communication_channel")
public class CommunicationChannel extends BaseCanvasModel implements Serializable {

    public static final long serialVersionUID = 1L;


    private String id;
    private Integer userId;

    private String address;
    private String type;
    private String token;
    private boolean skipConfirmation;

    private String workflowState;
    private Integer position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @CanvasField(postKey = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @CanvasField(postKey = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @CanvasField(postKey = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @CanvasField(postKey = "skip_confirmation")
    public boolean getHidden() {
        return skipConfirmation;
    }

    public void setSkipConfirmation(boolean skipConfirmation) {
        this.skipConfirmation = skipConfirmation;
    }

    @CanvasField(postKey = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
     
    @CanvasField(postKey = "workflow_state")
    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }
    
    
    @CanvasField(postKey = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}