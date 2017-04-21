package eNews.bean;

import java.io.Serializable;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 新闻实体类
 * 
 */
public class NewsModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String postid;
	private String votecount;

	private String replyCount;

	private String ltitle;
	private String digest;
	private String docid;
	private String source;
	private String lmodify;
	private String imagesrc;
	private String subtitle;
	private String url_3w;

	public String getUrl_3w() {
		return url_3w;
	}

	public void setUrl_3w(String url_3w) {
		this.url_3w = url_3w;
	}

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String boardid;

	private String ptime;

	private ImageModel imageModel;

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public String getVotecount() {
		return votecount;
	}

	public void setVotecount(String votecount) {
		this.votecount = votecount;
	}

	public String getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(String replyCount) {
		this.replyCount = replyCount;
	}

	public String getLtitle() {
		return ltitle;
	}

	public void setLtitle(String ltitle) {
		this.ltitle = ltitle;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLmodify() {
		return lmodify;
	}

	public void setLmodify(String lmodify) {
		this.lmodify = lmodify;
	}

	public String getImagesrc() {
		return imagesrc;
	}

	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getBoardid() {
		return boardid;
	}

	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public ImageModel getImageModel() {
		return imageModel;
	}

	public void setImageModel(ImageModel imageModel) {
		this.imageModel = imageModel;
	}

}
