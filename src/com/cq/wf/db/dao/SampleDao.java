package com.cq.wf.db.dao;

import java.util.List;

import com.cq.wf.web.model.SampleModel;

public interface SampleDao {

	public List<SampleModel> getUserList();
	
	public int sampleInsert(SampleModel model);
	
	public SampleModel getDetail(SampleModel model);
	
}
