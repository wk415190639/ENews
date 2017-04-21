package eNews.bean;

import java.util.List;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 图片实体类
 */
public class ImageModel {

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	private String docid;

	private String title;

	private List<String> imgList;

}
