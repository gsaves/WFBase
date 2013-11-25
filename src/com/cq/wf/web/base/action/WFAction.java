package com.cq.wf.web.base.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "error", location = "/jsp/error/error.jsp") })
public abstract class WFAction extends ActionSupport {

   
    /**
     * Default Serial VersionID
     */
    private static final long serialVersionUID = 1L;

}
