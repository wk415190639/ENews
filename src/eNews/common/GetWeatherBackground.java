package eNews.common;
import eNews.app.R;
  
public class GetWeatherBackground {

	public static int getBackgroundId(String typedesc) {

		switch (typedesc) {
		case "��":
			return R.drawable.sun;
		case "����":
			return R.drawable.duoyun;
		case "��":
			return R.drawable.yin;
		case "����":
			return R.drawable.rain;
		case "������":
			return R.drawable.rain;
		case "���ѩ":
			return R.drawable.default_bg;
		case "С��":
			return R.drawable.rain;
		case "����":
			return R.drawable.rain;
		case "����":
			return R.drawable.rain;
		case "����":
			return R.drawable.rain;
		case "�ش���":
			return R.drawable.rain;
		case "Сѩ":
			return R.drawable.snow;
		case "��ѩ":
			return R.drawable.snow;
		case "��ѩ":
			return R.drawable.snow;
		case "��ѩ":
			return R.drawable.snow;
		case "��":
			return R.drawable.wu;
		case "����":
			return R.drawable.default_bg;
		case "ɳ����":
			return R.drawable.yangsha;
		case "����":
			return R.drawable.yangsha;
		case "��ɳ":
			return R.drawable.yangsha;
		case "ǿɳ����":
			return R.drawable.yangsha;
		case "��":
			return R.drawable.wu;
		case "����":
			return R.drawable.binbao;
		default:
			return -1;
		}
	}
}
