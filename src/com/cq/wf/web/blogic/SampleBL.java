package com.cq.wf.web.blogic;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

import com.cq.wf.web.base.blogic.WFBlogic;
import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.model.SampleModel;

public class SampleBL extends WFBlogic<SampleModel> {

    protected String makeBlogic(SampleModel model) throws WFBusinessException,
            Exception {
        String userId = model.getUserId();
        ProcessEngine eng = ProcessEngines.getDefaultProcessEngine();
        String email = eng.getIdentityService().createUserQuery()
                .userId(userId).singleResult().getEmail();
        model.setEmail(email);
        return "success";
    }

}
