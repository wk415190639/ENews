package eNews.common;

import eNews.url.Url;

public class GetTypeId {

	public static String getTypeId(String typedesc) {

		switch (typedesc) {

		case "ͷ��":
			return Url.TopId;

		case "��������":
			return Url.NewDetail;

		case "����":
			return Url.FootId;

		case "����":
			return Url.YuLeId;
		case "�ƶ�":
			return Url.YiDongId;

		case "����":
			return Url.TiYuId;

		case "�ƾ�":
			return Url.CaiJingId;

		case "�Ƽ�":
			return Url.KeJiId;

		case "��Ӱ":
			return Url.DianYingId;

		case "����":
			return Url.QiChiId;

		case "Ц��":
			return Url.XiaoHuaId;

		case "ʱ��":
			return Url.ShiShangId;

		case "���":
			return Url.QingGanId;

		case "��ѡ":
			return Url.JingXuanId;

		case "��̨":
			return Url.DianTaiId;

		case "NBA":
			return Url.NBAId;
		case "CBA":
			return Url.CBAId;

		case "����":
			return Url.ShuMaId;

		case "��Ʊ":
			return Url.CaiPiaoId;

		case "����":
			return Url.JiaoYuId;
		case "��̳":
			return Url.LunTanId;

		case "����":
			return Url.LvYouId;

		case "�ֻ�":
			return Url.ShouJiId;

		case "����":
			return Url.BoKeId;

		case "���":
			return Url.SheHuiId;

		case "�Ҿ�":
			return Url.JiaJuId;

		case "��ѩ":
			return Url.BaoXueId;
		case "����":
			return Url.QinZiId;

		case "��Ϣ":
			return Url.XiaoHuaId;
		case "����":
			return Url.BeiJingId;

		case "����":
			return Url.FangChanId;
		case "����":
			return Url.FangChanId;
		case "��Ϸ":
			return Url.YouXiId;

		case "ͼ��":
			return Url.TuJi;
		case "�ȵ�":
			return Url.TuPianReDian;

		case "����":
			return Url.TuPianDuJia;
		case "����":
			return Url.TuPianMeiTu;

		case "��ͼ":
			return Url.TuPianMeiTu;

		case "�ȵ���Ƶ":
			return Url.VideoReDianId;

		case "������Ƶ":
			return Url.VideoYuLeId;
		case "��Ц��Ƶ":
			return Url.VideoGaoXiaoId;

		case "��Ʒ��Ƶ":
			return Url.VideoJingPinId;

		default:
			return "null";
		}
	}

}
