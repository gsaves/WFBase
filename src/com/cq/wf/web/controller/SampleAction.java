package com.cq.wf.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;

import com.cq.wf.web.base.WFAction;


public class SampleAction extends WFAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String execute() throws Exception {
        
        ProcessEngine eng = ProcessEngines.getDefaultProcessEngine();
        TaskService ts = eng.getTaskService();
        List<Task> tskLst = ts.createTaskQuery().taskAssignee("kermit").list();
        
        HttpServletRequest req = ServletActionContext.getRequest();
        req.setAttribute("list", tskLst);
        return SUCCESS;
    }

}
