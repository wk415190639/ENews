package eNews.common;

import eNews.app.R;

/**
 * 
 * @author Íõ¿­
 * @date 2016-9-12 »ñÈ¡ÌìÆøÍ¼±êÀà
 */
public class GetWeatherIconTypeId {

	public static int getTypeId(String typedesc) {

		switch (typedesc) {

		case "Çç":
			return R.drawable.qing_0;
		case "¶àÔÆ":
			return R.drawable.duoyun_0;
		case "Òõ":
			return R.drawable.yin_0;
		case "ÕóÓê":
			return R.drawable.zhenyu_0;
		case "À×ÕóÓê":
			return R.drawable.leizhenyu_0;
		case "Óê¼ĞÑ©":
			return R.drawable.yujiaxue_0;
		case "Ğ¡Óê":
			return R.drawable.xiaoyu_0;
		case "ÖĞÓê":
			return R.drawable.zhongyu_0;
		case "´óÓê":
			return R.drawable.dayu_0;
		case "±©Óê":
			return R.drawable.baoyu_0;
		case "ÌØ´ó±©Óê":
			return R.drawable.tedabaoyu_0;
		case "Ğ¡Ñ©":
			return R.drawable.xiaoxue_0;
		case "ÖĞÑ©":
			return R.drawable.zhongxue_0;
		case "´óÑ©":
			return R.drawable.daxue_0;
		case "±©Ñ©":
			return R.drawable.baoxue_0;
		case "Îí":
			return R.drawable.wu_0;
		case "¶³Óê":
			return R.drawable.dongyu_0;
		case "É³³¾±©":
			return R.drawable.shachenbao_0;
		case "¸¡³¾":
			return R.drawable.fuchen_0;
		case "ÑïÉ³":
			return R.drawable.yangsha_0;
		case "Ç¿É³³¾±©":
			return R.drawable.qiangshachenbao_0;
		case "ö²":
			return R.drawable.mai_0;
		case "±ù±¢":
			return R.drawable.bingbao_0;
		default:
			return -1;
		}
	}

}
