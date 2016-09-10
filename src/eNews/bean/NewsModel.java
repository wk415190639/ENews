package eNews.bean;

import java.io.Serializable;

/*
 * url_3w	:	http://news.163.com/16/0823/01/BV491BRN00014AED.html

 postid	:	BV491BRN00014AED

 votecount	:	57683

 replyCount	:	61678

 ltitle	:	Υ��ͣ��������ǹ�ִ����Χ �ǹ�Ҳ�������ˣ�

 digest	:	�¾���Ѷ���й���ִ����ǰ�������ܲ��źͷ����ƶȵ�ȱλ��һ������Ƶ�������������ס��������ȷΪ����ȫ�����й����������ܲ��ź��ڽ��շ��������й���ִ���취������

 url	:	http://3g.163.com/news/16/0823/01/BV491BRN00014AED.html

 docid	:	BV491BRN00014AED

 title	:	Υ��ͣ��������ǹ�ִ����Χ �ǹ�Ҳ�������ˣ�

 source	:	�¾���

 priority	:	110

 lmodify	:	2016-08-23 09:12:18

 imgsrc	:	http://cms-bucket.nosdn.127.net/074d1cf6111f4871b77d4a9d191aedc120160823030438.jpeg

 subtitle	:	

 boardid	:	news3_bbs

 ptime	:	2016-08-23 01:26:55

 5		{16}

 url_3w	:	http://news.163.com/16/0823/01/BV47IKFL00011229.html

 postid	:	BV47IKFL00011229

 votecount	:	18324

 replyCount	:	19579

 ltitle	:	̽���пƴ������\"���\":�������˶���ѧ��

 digest	:	�����������ң���û��ô��������ֻ�Ǹ��ڷܡ�8��9�գ���Ϧ������6�㣬������ͷ�����³�Ⱥ��ӽ�����Ű����ơ�һ�һ��ݲ˹ݣ��ҵĶ���������λ������꣬�������ڵ���

 url	:	http://3g.163.com/news/16/0823/01/BV47IKFL00011229.html

 docid	:	BV47IKFL00011229

 title	:	̽���пƴ������\"���\":�������˶���ѧ��

 source	:	�϶���ʱ��

 priority	:	108

 lmodify	:	2016-08-23 09:12:29

 imgsrc	:	http://cms-bucket.nosdn.127.net/d5eec9dbea6241be92b5981d4bd28d2120160823010954.jpeg

 subtitle	:	

 boardid	:	news_shehui7_bbs

 ptime	:	2016-08-23 01:01:27

 */


public class NewsModel implements Serializable {

	/**
	 * 
	 */
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
