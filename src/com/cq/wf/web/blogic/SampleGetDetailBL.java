package com.cq.wf.web.blogic;

import org.apache.ibatis.session.SqlSession;

import com.cq.wf.db.dao.SampleDao;
import com.cq.wf.web.base.blogic.WFBlogic;
import com.cq.wf.web.base.exception.WFBusinessException;
import com.cq.wf.web.model.SampleModel;

public class SampleGetDetailBL extends WFBlogic<SampleModel> {

	protected String makeBlogic(SampleModel model, SqlSession ses)
			throws WFBusinessException, Exception {


		// draft
		// insert content to db
		SampleDao dao = ses.getMapper(SampleDao.class);
		SampleModel result = dao.getDetail(model);
		model.setComment(result.getComment());
		model.setContent(result.getContent());

		return "success";
	}

}
