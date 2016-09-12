package eNews.bean;

import java.util.ArrayList;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 详细新闻实体类
 * 
 */
public class NewsDetailModel {

	private String postId;

	private String title;

	private String body;

	private String ptime;

	private String shareLink;

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	private ArrayList<String> img;

	public NewsDetailModel() {
		// TODO Auto-generated constructor stub

		img = new ArrayList<String>();
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ArrayList<String> getImg() {
		return img;
	}

	public void setImg(ArrayList<String> img) {
		this.img = img;
	}

}
