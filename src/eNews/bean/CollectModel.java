package eNews.bean;

import java.util.ArrayList;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 收藏实体类
 */
public class CollectModel {

	private String _id;
	private String type;
	private String title;
	private String url;
	private String desc;
	private String imgurl;
	private String id;
	private String userId;

	ArrayList<String> pics;

	public ArrayList<String> getPics() {
		return pics;
	}

	public void setPics(ArrayList<String> pics) {
		this.pics = pics;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
