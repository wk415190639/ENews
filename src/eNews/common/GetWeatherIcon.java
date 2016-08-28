package eNews.common;

import eNews.app.R;

public class GetWeatherIcon {

	static public int get(String iconName) {

		switch (iconName) {
		case "":
			return 1;

		default:
			return R.drawable.p3;

		}

	}
}
