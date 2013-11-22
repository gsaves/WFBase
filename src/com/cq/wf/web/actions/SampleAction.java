package com.cq.wf.web.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Result;

import com.cq.wf.web.base.action.WFAction;
import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.base.exception.WFSystemException;
import com.cq.wf.web.blogic.SampleBL;
import com.cq.wf.web.model.SampleModel;
import com.cq.wf.web.utils.constant.WFConst;

@ExceptionMappings({ @ExceptionMapping(exception = WFConst.WF_SYSEXCEPTION, result = WFConst.RS_ERROR) })
public class SampleAction extends WFAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private SampleModel sampleModel;
    


    @Action(value = "sample", results = { @Result(name = "success", location = "/jsp/sample.jsp") })
    public String execute() throws WFSystemException ,WFBusinessException{

        SampleBL bl = new SampleBL();
        sampleModel = new SampleModel();
        
        sampleModel.setUserId("kermit");
        bl.setModel(sampleModel);
        
        return bl.excuteBlogic();
    }

    /**
     * @return the sampleModel
     */
    public SampleModel getSampleModel() {
        return sampleModel;
    }


    /**
     * @param sampleModel the sampleModel to set
     */
    public void setSampleModel(SampleModel sampleModel) {
        this.sampleModel = sampleModel;
    }
}
