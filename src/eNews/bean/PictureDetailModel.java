package eNews.bean;

import android.graphics.Bitmap;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 图片详情实体类
 */
public class PictureDetailModel {

	private String kpic;
	private String alt;
	private String size;

	private Bitmap picBitmap;

	public Bitmap getPicBitmap() {
		return picBitmap;
	}

	public void setPicBitmap(Bitmap picBitmap) {
		this.picBitmap = picBitmap;
	}

	public String getKpic() {
		return kpic;
	}

	public void setKpic(String kpic) {
		this.kpic = kpic;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
