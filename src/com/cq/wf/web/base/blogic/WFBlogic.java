package com.cq.wf.web.base.blogic;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.base.exception.WFSystemException;
import com.cq.wf.web.base.model.WFBaseModel;
import com.cq.wf.web.utils.DBUtils;

public abstract class WFBlogic<T extends WFBaseModel> {

    protected T model;

    protected static Logger log;
    
    protected Connection con = null;

   

    public String excuteBlogic() throws WFSystemException, WFBusinessException {
        log = LoggerFactory.getLogger(this.getClass());
        con = DBUtils.getConn();
        try {
            log.info("excuteBl function start");
            // TODO transaction start
            log.info("doBl function start");
            String resultStr = makeBlogic(model);
            log.info("doBl function end");
            log.info("excuteBl function end");
            return resultStr;
        } catch (WFBusinessException be) {
            // TODO log write here
            throw be;
        } catch (Exception e) {
            // TODO log write here
            throw new WFSystemException();
        } finally {

        }

    }

    protected abstract String makeBlogic(T model) throws WFBusinessException,
            Exception;

    /**
     * @return the model
     */
    public T getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(T model) {
        this.model = model;
    }
}
