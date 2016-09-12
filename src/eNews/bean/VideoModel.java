package eNews.bean;

import java.io.Serializable;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 视频实体类
 */
public class VideoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String topicImg;
	private String mp4Hd_url;
	private String mp4_url;
	private String topicDesc;
	private String videosource;
	private String length;

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopicImg() {
		return topicImg;
	}

	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}

	public String getMp4Hd_url() {
		return mp4Hd_url;
	}

	public void setMp4Hd_url(String mp4Hd_url) {
		this.mp4Hd_url = mp4Hd_url;
	}

	public String getMp4_url() {
		return mp4_url;
	}

	public void setMp4_url(String mp4_url) {
		this.mp4_url = mp4_url;
	}

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public String getVideosource() {
		return videosource;
	}

	public void setVideosource(String videosource) {
		this.videosource = videosource;
	}

}
