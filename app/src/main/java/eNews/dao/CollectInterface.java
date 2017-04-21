package eNews.dao;

import java.util.ArrayList;

import eNews.bean.CollectModel;

public interface CollectInterface {

	long insertCollect(CollectModel collectModel);

	int deleteCollect(CollectModel collectModel);
	
	int deleteCollectSet(String type);

	ArrayList<CollectModel> query(String type,String openID);

}
