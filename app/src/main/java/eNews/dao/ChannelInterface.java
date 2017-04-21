package eNews.dao;

import eNews.bean.ChannelItemModel;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 频道管理接口
 */
public interface ChannelInterface {

	long inserToOther(ChannelItemModel otherChannel);

	long inserToUser(ChannelItemModel userChannel);

	int updateUser(String name, String id);

	int updateOther(String name, String id);

}
