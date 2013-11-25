package com.cq.wf.web.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cq.wf.web.base.action.WFAction;
import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.base.exception.WFSystemException;
import com.cq.wf.web.blogic.SampleBL;
import com.cq.wf.web.model.SampleModel;
import com.cq.wf.web.utils.WFUtils;
import com.cq.wf.web.utils.constant.WFConst;

@ExceptionMappings({ @ExceptionMapping(exception = WFConst.WF_SYSEXCEPTION, result = WFConst.RS_ERROR) })
@Results({ @Result(name = "success", location = "/jsp/sample.jsp"),
        @Result(name = "detail", location = "/jsp/sampledetail.jsp") })
public class SampleAction extends WFAction {

    private static Logger log = LoggerFactory.getLogger(WFAction.class);
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private SampleModel sampleModel;

    @Action("sample")
    public String init() throws WFSystemException, WFBusinessException {

        SampleBL bl = new SampleBL();
        sampleModel = new SampleModel();

        sampleModel.setUserId("kermit");
        bl.setModel(sampleModel);

        return bl.excuteBlogic();
    }

    @Action("sampleSearch")
    public String doSearch() throws WFSystemException, WFBusinessException {

        log.info("userid:" + sampleModel.getUserId());
        // get selected user's task list
        List<Task> tskList = WFUtils.getTaskService().createTaskQuery()
                .taskAssignee(sampleModel.getUserId()).list();
        log.info("task count:" + tskList.size());
        sampleModel.setTskList(tskList);
        List<User> users = WFUtils.getIdentityService().createUserQuery()
                .list();
        List<HashMap<String, String>> usrList = new ArrayList<HashMap<String, String>>();
        for (User usr : users) {
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put("id", usr.getId());
            tmp.put("name", usr.getFirstName() + " " + usr.getLastName());
            usrList.add(tmp);
        }
        sampleModel.setUsrList(usrList);

        return "success";
    }

    @Action("draft")
    public String doDraft() throws WFSystemException, WFBusinessException {

        List<User> users = WFUtils.getIdentityService().createUserQuery()
                .list();
        List<HashMap<String, String>> usrList = new ArrayList<HashMap<String, String>>();
        for (User usr : users) {
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put("id", usr.getId());
            tmp.put("name", usr.getFirstName() + " " + usr.getLastName());
            usrList.add(tmp);
        }
        sampleModel.setUsrList(usrList);

        log.info("draft start");
        RuntimeService rs = WFUtils.getRuntimeService();
        ProcessInstance ps = rs.startProcessInstanceByKey("sampleProcess");
        log.info("ActivityId: " + ps.getActivityId());
        log.info("ProcessInstanceID: " + ps.getId());
        TaskService taskService = WFUtils.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateUser("fozzie").list();
        if (tasks.size() > 0) {
            taskService.claim(tasks.get(0).getId(), "fozzie");
        }

        // draft
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("memo", sampleModel.getContent());
        taskVariables.put("comment", sampleModel.getComment());

        taskService.complete(tasks.get(0).getId(), taskVariables);

        //push task to gonzo
        tasks = taskService.createTaskQuery()
                .taskCandidateUser("gonzo").list();
        if (tasks.size() > 0) {
            taskService.claim(tasks.get(0).getId(), "gonzo");
        }
        
        return "success";
    }

    @Action("sampleDetail")
    public String getDetail() throws WFSystemException, WFBusinessException {

        log.info("taskID:" + sampleModel.getTaskId());
        TaskService taskService = WFUtils.getTaskService();
        Task tsk = taskService.createTaskQuery()
                .taskId(sampleModel.getTaskId()).singleResult();
        Map<String, Object> var = tsk.getProcessVariables();
        sampleModel.setComment(var.get("comment")==null ? "":var.get("comment").toString());
        sampleModel.setContent(var.get("memo")==null ? "":var.get("memo").toString());
        return "detail";
    }

    /**
     * @return the sampleModel
     */
    public SampleModel getSampleModel() {
        return sampleModel;
    }

    /**
     * @param sampleModel
     *            the sampleModel to set
     */
    public void setSampleModel(SampleModel sampleModel) {
        this.sampleModel = sampleModel;
    }
}
