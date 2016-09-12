package eNews.dao;

import eNews.bean.ChannelItemModel;

/**
 * 
 * @author ����
 * @date 2016-9-12 Ƶ������ӿ�
 */
public interface ChannelInterface {

	long inserToOther(ChannelItemModel otherChannel);

	long inserToUser(ChannelItemModel userChannel);

	int updateUser(String name, String id);

	int updateOther(String name, String id);

}
