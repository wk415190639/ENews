package eNews.dao;

import eNews.bean.ChannelItemModel;

public interface ChannelInterface {

	long inserToOther(ChannelItemModel otherChannel);

	long inserToUser(ChannelItemModel userChannel);

	int updateUser(String name, String id);

	int updateOther(String name, String id);

}
