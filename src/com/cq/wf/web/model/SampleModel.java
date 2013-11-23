package com.cq.wf.web.model;

import java.util.Date;

import com.cq.wf.web.base.model.WFBaseModel;

public class SampleModel extends WFBaseModel {
    
   
	private String userId;
    private String email;
    private Date testDate;
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
}
