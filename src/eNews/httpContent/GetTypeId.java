package eNews.httpContent;

import eNews.app.R;

public class GetTypeId {

	public static int getTypeId(String typedesc) {

		switch (typedesc) {

		case "«Á":
			return R.drawable.qing_1;
		case "∂‡‘∆":
			return R.drawable.duoyun_1;
		case "“ı":
			return R.drawable.yin_1;
		case "’Û”Í":
			return R.drawable.zhenyu_1;
		case "¿◊’Û”Í":
			return R.drawable.leizhenyu_1;
		case "”Íº–—©":
			return R.drawable.yujiaxue_1;
		case "–°”Í":
			return R.drawable.xiaoyu_1;
		case "÷–”Í":
			return R.drawable.zhongyu_1;
		case "¥Û”Í":
			return R.drawable.dayu_1;
		case "±©”Í":
			return R.drawable.baoyu_1;
		case "Ãÿ¥Û±©”Í":
			return R.drawable.tedabaoyu_1;
		case "–°—©":
			return R.drawable.xiaoxue_1;
		case "÷–—©":
			return R.drawable.zhongxue_1;
		case "¥Û—©":
			return R.drawable.daxue_1;
		case "±©—©":
			return R.drawable.baoxue_1;
		case "ŒÌ":
			return R.drawable.wu_1;
		case "∂≥”Í":
			return R.drawable.dongyu_1;
		case "…≥≥æ±©":
			return R.drawable.shachenbao_1;
		case "∏°≥æ":
			return R.drawable.fuchen_1;
		case "—Ô…≥":
			return R.drawable.yangsha_1;
		case "«ø…≥≥æ±©":
			return R.drawable.qiangshachenbao_1;
		case "ˆ≤":
			return R.drawable.mai_1;
		case "±˘±¢":
			return R.drawable.bingbao_1;
		default:
			return -1;
		}
	}

}
