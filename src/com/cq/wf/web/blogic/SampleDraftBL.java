package com.cq.wf.web.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.ibatis.session.SqlSession;

import com.cq.wf.db.dao.SampleDao;
import com.cq.wf.web.base.blogic.WFBlogic;
import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.model.SampleModel;
import com.cq.wf.web.utils.WFUtils;

public class SampleDraftBL extends WFBlogic<SampleModel> {

	protected String makeBlogic(SampleModel model, SqlSession ses)
			throws WFBusinessException, Exception {
		// get wf user list
		List<User> users = WFUtils.getIdentityService().createUserQuery()
				.list();
		List<HashMap<String, String>> usrList = new ArrayList<HashMap<String, String>>();
		for (User usr : users) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("id", usr.getId());
			tmp.put("name", usr.getFirstName() + " " + usr.getLastName());
			usrList.add(tmp);
		}
		model.setUsrList(usrList);

		log.info("draft start");
		RuntimeService rs = WFUtils.getRuntimeService();
		ProcessInstance ps = rs.startProcessInstanceByKey("sampleProcess");
		log.info("ActivityId: " + ps.getActivityId());
		log.info("ProcessInstanceID: " + ps.getId());
		TaskService taskService = WFUtils.getTaskService();

		Task tasks = taskService.createTaskQuery()
				.processInstanceId(ps.getId()).singleResult();

		taskService.claim(tasks.getId(), "fozzie");

		// draft
		// insert content to db
		SampleDao dao = ses.getMapper(SampleDao.class);
		model.setId(ps.getId());
		dao.sampleInsert(model);
		taskService.complete(tasks.getId());

		// push task to gonzo
		tasks = taskService.createTaskQuery().processInstanceId(ps.getId())
				.singleResult();
		taskService.claim(tasks.getId(), "gonzo");

		return "success";
	}

}
