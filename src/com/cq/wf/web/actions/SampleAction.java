package com.cq.wf.web.actions;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.identity.User;
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
import com.cq.wf.web.blogic.SampleDraftBL;
import com.cq.wf.web.blogic.SampleGetDetailBL;
import com.cq.wf.web.model.SampleModel;
import com.cq.wf.web.utils.DBUtils;
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

		Connection con = DBUtils.getConn();
		try {
			sampleModel = new SampleModel();
			SampleBL bl = new SampleBL();

			bl.setModel(sampleModel);

			bl.excuteBlogic();

			Statement st = con.createStatement();
			String sql = "drop table wf_sample;";
			st.addBatch(sql);
			sql = "create table wf_sample (" + "content varchar(300),"
					+ "comment varchar(300)," + "id varchar(64),"
					+ "primary key (id)" + ");";
			st.addBatch(sql);
			st.executeBatch();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;

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

		SampleDraftBL bl = new SampleDraftBL();
		bl.setModel(sampleModel);

		return bl.excuteBlogic();
	}

	@Action("sampleDetail")
	public String getDetail() throws WFSystemException, WFBusinessException {

		SampleGetDetailBL bl = new SampleGetDetailBL();
		bl.setModel(sampleModel);
		bl.excuteBlogic();
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
