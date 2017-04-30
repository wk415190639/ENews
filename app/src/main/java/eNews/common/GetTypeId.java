package eNews.common;

import eNews.url.Url;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 获取资源ID类
 */
public class GetTypeId {

	public static String getTypeId(String typedesc) {

		switch (typedesc) {

		case "头条":
			return Url.TopId;

		case "新闻详情":

			return Url.NewDetail;

		case "足球":
			return Url.FootId;

		case "娱乐":
			return Url.YuLeId;
		case "移动":
			return Url.YiDongId;

		case "体育":
			return Url.TiYuId;

		case "财经":
			return Url.CaiJingId;

		case "科技":
			return Url.KeJiId;

		case "电影":
			return Url.DianYingId;

		case "汽车":
			return Url.QiChiId;

		case "笑话":
			return Url.XiaoHuaId;

		case "时尚":
			return Url.ShiShangId;

		case "情感":
			return Url.QingGanId;

		case "精选":
			return Url.JingXuanId;

		case "电台":
			return Url.DianTaiId;

		case "NBA":
			return Url.NBAId;
		case "CBA":
			return Url.CBAId;

		case "数码":
			return Url.ShuMaId;

		case "彩票":
			return Url.CaiPiaoId;

		case "教育":
			return Url.JiaoYuId;
		case "论坛":
			return Url.LunTanId;

		case "旅游":
			return Url.LvYouId;

		case "手机":
			return Url.ShouJiId;

		case "博客":
			return Url.BoKeId;

		case "社会":
			return Url.SheHuiId;

		case "家居":
			return Url.JiaJuId;

		case "暴雪":
			return Url.BaoXueId;
		case "亲子":
			return Url.QinZiId;

		case "消息":
			return Url.XiaoHuaId;

		case "游戏":
			return Url.YouXiId;

		case "热点视频":
			return Url.VideoReDianId;

		case "娱乐视频":
			return Url.VideoYuLeId;
		case "搞笑视频":
			return Url.VideoGaoXiaoId;

		case "精品视频":
			return Url.VideoJingPinId;

		default:
			return "null";
		}
	}

	public static boolean isBvNews(String docId) {

		String capture = docId.substring(0, 1);
		if (capture.equals("P"))
			return false;

		return true;
	}

}
