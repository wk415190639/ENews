package eNews.common;

import eNews.app.R;

public class GetWeatherIconTypeId {

	public static int getTypeId(String typedesc) {

		switch (typedesc) {

		case "«Á":
			return R.drawable.qing_0;
		case "∂‡‘∆":
			return R.drawable.duoyun_0;
		case "“ı":
			return R.drawable.yin_0;
		case "’Û”Í":
			return R.drawable.zhenyu_0;
		case "¿◊’Û”Í":
			return R.drawable.leizhenyu_0;
		case "”Íº–—©":
			return R.drawable.yujiaxue_0;
		case "–°”Í":
			return R.drawable.xiaoyu_0;
		case "÷–”Í":
			return R.drawable.zhongyu_0;
		case "¥Û”Í":
			return R.drawable.dayu_0;
		case "±©”Í":
			return R.drawable.baoyu_0;
		case "Ãÿ¥Û±©”Í":
			return R.drawable.tedabaoyu_0;
		case "–°—©":
			return R.drawable.xiaoxue_0;
		case "÷–—©":
			return R.drawable.zhongxue_0;
		case "¥Û—©":
			return R.drawable.daxue_0;
		case "±©—©":
			return R.drawable.baoxue_0;
		case "ŒÌ":
			return R.drawable.wu_0;
		case "∂≥”Í":
			return R.drawable.dongyu_0;
		case "…≥≥æ±©":
			return R.drawable.shachenbao_0;
		case "∏°≥æ":
			return R.drawable.fuchen_0;
		case "—Ô…≥":
			return R.drawable.yangsha_0;
		case "«ø…≥≥æ±©":
			return R.drawable.qiangshachenbao_0;
		case "ˆ≤":
			return R.drawable.mai_0;
		case "±˘±¢":
			return R.drawable.bingbao_0;
		default:
			return -1;
		}
	}

}
