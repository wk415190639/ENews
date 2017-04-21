package eNews.common;

import eNews.app.R;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 获取天气预报背景图片ID类
 */
public class GetWeatherBackground {

	public static int getBackgroundId(String typedesc) {

		switch (typedesc) {
		case "晴":
			return R.drawable.sun;
		case "多云":
			return R.drawable.duoyun;
		case "阴":
			return R.drawable.yin;
		case "阵雨":
			return R.drawable.rain;
		case "雷阵雨":
			return R.drawable.rain;
		case "雨夹雪":
			return R.drawable.default_bg;
		case "小雨":
			return R.drawable.rain;
		case "中雨":
			return R.drawable.rain;
		case "大雨":
			return R.drawable.rain;
		case "暴雨":
			return R.drawable.rain;
		case "特大暴雨":
			return R.drawable.rain;
		case "小雪":
			return R.drawable.snow;
		case "中雪":
			return R.drawable.snow;
		case "大雪":
			return R.drawable.snow;
		case "暴雪":
			return R.drawable.snow;
		case "雾":
			return R.drawable.wu;
		case "冻雨":
			return R.drawable.default_bg;
		case "沙尘暴":
			return R.drawable.yangsha;
		case "浮尘":
			return R.drawable.yangsha;
		case "扬沙":
			return R.drawable.yangsha;
		case "强沙尘暴":
			return R.drawable.yangsha;
		case "霾":
			return R.drawable.wu;
		case "冰雹":
			return R.drawable.binbao;
		default:
			return -1;
		}
	}
}
