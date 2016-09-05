package eNews.common;
import eNews.app.R;
  
public class GetWeatherBackground {

	public static int getBackgroundId(String typedesc) {

		switch (typedesc) {
		case "Çç":
			return R.drawable.sun;
		case "¶àÔÆ":
			return R.drawable.duoyun;
		case "Òõ":
			return R.drawable.yin;
		case "ÕóÓê":
			return R.drawable.rain;
		case "À×ÕóÓê":
			return R.drawable.rain;
		case "Óê¼ĞÑ©":
			return R.drawable.default_bg;
		case "Ğ¡Óê":
			return R.drawable.rain;
		case "ÖĞÓê":
			return R.drawable.rain;
		case "´óÓê":
			return R.drawable.rain;
		case "±©Óê":
			return R.drawable.rain;
		case "ÌØ´ó±©Óê":
			return R.drawable.rain;
		case "Ğ¡Ñ©":
			return R.drawable.snow;
		case "ÖĞÑ©":
			return R.drawable.snow;
		case "´óÑ©":
			return R.drawable.snow;
		case "±©Ñ©":
			return R.drawable.snow;
		case "Îí":
			return R.drawable.wu;
		case "¶³Óê":
			return R.drawable.default_bg;
		case "É³³¾±©":
			return R.drawable.yangsha;
		case "¸¡³¾":
			return R.drawable.yangsha;
		case "ÑïÉ³":
			return R.drawable.yangsha;
		case "Ç¿É³³¾±©":
			return R.drawable.yangsha;
		case "ö²":
			return R.drawable.wu;
		case "±ù±¢":
			return R.drawable.binbao;
		default:
			return -1;
		}
	}
}
