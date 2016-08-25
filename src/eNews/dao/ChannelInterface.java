package eNews.dao;

import eNews.bean.ChannelItemModel;

public interface ChannelInterface {

	long inserToOther(ChannelItemModel otherChannel);

	long inserToUser(ChannelItemModel userChannel);

	int update_moveToOther(String name,int id);

	int update_moveToUser(String name,int id);
}
