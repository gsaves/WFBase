package com.cq.wf.web.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.task.Task;

import com.cq.wf.web.base.model.WFBaseModel;

public class SampleModel extends WFBaseModel {
    
   
	private String userId;
    private String email;
    private Date testDate;
    private String content;
    private String comment;
    private List<HashMap<String, String>> usrList;
    private List<Task> tskList;
    private String taskId;
    private String id;
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
    /**
     * @return the tskList
     */
    public List<Task> getTskList() {
        return tskList;
    }
    /**
     * @param tskList the tskList to set
     */
    public void setTskList(List<Task> tskList) {
        this.tskList = tskList;
    }
    /**
     * @return the usrList
     */
    public List<HashMap<String, String>> getUsrList() {
        return usrList;
    }
    /**
     * @param usrList the usrList to set
     */
    public void setUsrList(List<HashMap<String, String>> usrList) {
        this.usrList = usrList;
    }
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }
    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }
    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
