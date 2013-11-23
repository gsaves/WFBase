package com.cq.wf.web.base.blogic;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.base.exception.WFSystemException;
import com.cq.wf.web.base.model.WFBaseModel;
import com.cq.wf.web.utils.DBUtils;

public abstract class WFBlogic<T extends WFBaseModel> {

	protected T model;

	protected static Logger log;


	public String excuteBlogic() throws WFSystemException, WFBusinessException {
		log = LoggerFactory.getLogger(this.getClass());
		SqlSession ses = DBUtils.getSqlSession();

		try {
			log.info("excuteBlogic function start");

			log.info("makeBlogic function start");
			
			String resultStr = makeBlogic(model,ses);
			ses.commit();
			log.info("makeBlogic function end");
			log.info("excuteBlogic function end");
			return resultStr;
		} catch (WFBusinessException be) {
			if (ses != null) {
				try {
					ses.rollback();
				} catch (Exception e) {
					log.error(e.getLocalizedMessage());
				}
			}
			log.info(be.getLocalizedMessage());
			throw be;
		} catch (Exception e) {
			if (ses != null) {
				try {
					ses.rollback();
				} catch (Exception ex) {
					log.error("error:",ex);
				}
			}
			log.error("error:",e);
			throw new WFSystemException(e.getMessage());
		} finally {
			if (ses != null) {
				ses.close();
			}
		}

	}

	protected abstract String makeBlogic(T model,SqlSession sqlSession) throws WFBusinessException,
			Exception;

	/**
	 * @return the model
	 */
	public T getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(T model) {
		this.model = model;
	}
}
