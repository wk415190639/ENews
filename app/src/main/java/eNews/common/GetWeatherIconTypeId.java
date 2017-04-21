package eNews.common;

import eNews.app.R;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 获取天气图标类
 */
public class GetWeatherIconTypeId {

	public static int getTypeId(String typedesc) {

		switch (typedesc) {

		case "晴":
			return R.drawable.qing_0;
		case "多云":
			return R.drawable.duoyun_0;
		case "阴":
			return R.drawable.yin_0;
		case "阵雨":
			return R.drawable.zhenyu_0;
		case "雷阵雨":
			return R.drawable.leizhenyu_0;
		case "雨夹雪":
			return R.drawable.yujiaxue_0;
		case "小雨":
			return R.drawable.xiaoyu_0;
		case "中雨":
			return R.drawable.zhongyu_0;
		case "大雨":
			return R.drawable.dayu_0;
		case "暴雨":
			return R.drawable.baoyu_0;
		case "特大暴雨":
			return R.drawable.tedabaoyu_0;
		case "小雪":
			return R.drawable.xiaoxue_0;
		case "中雪":
			return R.drawable.zhongxue_0;
		case "大雪":
			return R.drawable.daxue_0;
		case "暴雪":
			return R.drawable.baoxue_0;
		case "雾":
			return R.drawable.wu_0;
		case "冻雨":
			return R.drawable.dongyu_0;
		case "沙尘暴":
			return R.drawable.shachenbao_0;
		case "浮尘":
			return R.drawable.fuchen_0;
		case "扬沙":
			return R.drawable.yangsha_0;
		case "强沙尘暴":
			return R.drawable.qiangshachenbao_0;
		case "霾":
			return R.drawable.mai_0;
		case "冰雹":
			return R.drawable.bingbao_0;
		default:
			return -1;
		}
	}

}
