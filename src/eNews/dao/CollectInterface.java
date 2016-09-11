package eNews.dao;

import eNews.bean.CollectModel;

public interface CollectInterface {

	long insertCollect(CollectModel collectModel);

	int deleteCollect(String title);

}
