package eNews.httpContent;

import eNews.app.R;

public class GetTypeId {

	public static int getTypeId(String typedesc) {

		switch (typedesc) {

		case "��":
			return R.drawable.qing_1;
		case "����":
			return R.drawable.duoyun_1;
		case "��":
			return R.drawable.yin_1;
		case "����":
			return R.drawable.zhenyu_1;
		case "������":
			return R.drawable.leizhenyu_1;
		case "���ѩ":
			return R.drawable.yujiaxue_1;
		case "С��":
			return R.drawable.xiaoyu_1;
		case "����":
			return R.drawable.zhongyu_1;
		case "����":
			return R.drawable.dayu_1;
		case "����":
			return R.drawable.baoyu_1;
		case "�ش���":
			return R.drawable.tedabaoyu_1;
		case "Сѩ":
			return R.drawable.xiaoxue_1;
		case "��ѩ":
			return R.drawable.zhongxue_1;
		case "��ѩ":
			return R.drawable.daxue_1;
		case "��ѩ":
			return R.drawable.baoxue_1;
		case "��":
			return R.drawable.wu_1;
		case "����":
			return R.drawable.dongyu_1;
		case "ɳ����":
			return R.drawable.shachenbao_1;
		case "����":
			return R.drawable.fuchen_1;
		case "��ɳ":
			return R.drawable.yangsha_1;
		case "ǿɳ����":
			return R.drawable.qiangshachenbao_1;
		case "��":
			return R.drawable.mai_1;
		case "����":
			return R.drawable.bingbao_1;
		default:
			return -1;
		}
	}

}
