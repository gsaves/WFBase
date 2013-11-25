package com.cq.wf.web.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.User;
import org.apache.ibatis.session.SqlSession;

import com.cq.wf.db.dao.SampleDao;
import com.cq.wf.web.base.blogic.WFBlogic;
import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.model.SampleModel;
import com.cq.wf.web.utils.DBUtils;
import com.cq.wf.web.utils.WFUtils;

public class SampleBL extends WFBlogic<SampleModel> {

    protected String makeBlogic(SampleModel model,SqlSession ses) throws WFBusinessException,
            Exception {
        //get wf user list
        List<User> users = WFUtils.getIdentityService().createUserQuery().list();
        List<HashMap<String, String>> usrList = new ArrayList<HashMap<String, String>>();
        for(User usr : users){
            HashMap<String, String> tmp = new HashMap<String, String>();
            tmp.put("id", usr.getId());
            tmp.put("name", usr.getFirstName() + " " +usr.getLastName());
            usrList.add(tmp);
        }
        model.setUsrList(usrList);
        //JDBC sample
        String sql = "select CURRENT_DATE as testdate";//"select id_ as userid,email_ as email from ACT_ID_USER";
        DBUtils dbUtil = new DBUtils(DBUtils.getConn());
        List<SampleModel> rst = dbUtil.getQueryList(sql, null, SampleModel.class);
        for(SampleModel re : rst){
        	log.info(re.getTestDate().toString());
        }
        //ibatis sample
        SampleDao dao = ses.getMapper(SampleDao.class);
        rst = dao.getUserList();
        for(SampleModel re : rst){
        	log.info(re.getUserId());
        	log.info(re.getEmail());
        }
        return "success";
    }

}
