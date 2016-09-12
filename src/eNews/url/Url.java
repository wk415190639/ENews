package eNews.url;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 数据网址
 */

public class Url {
	public static final String host = "http://c.m.163.com/";
	public static final String endUrl = "-20.html";
	public static final String endDetailUrl = "/full.html";

	// 头条
	public static final String TopUrl = host + "nc/article/headline/";
	public static final String TopId = "T1348647909107";

	// 新闻详情
	public static final String NewDetail = host + "/nc/article/";

	public static final String CommonUrl = host + "nc/article/list/";
	// 足球
	public static final String FootId = "T1399700447917";
	// 娱乐
	public static final String YuLeId = "T1348648517839";
	// 体育
	public static final String TiYuId = "T1348649079062";
	// 财经
	public static final String CaiJingId = "T1348648756099";
	// 科技
	public static final String KeJiId = "T1348649580692";
	// 电影
	public static final String DianYingId = "T1348648650048";
	// 汽车
	public static final String QiChiId = "T1348654060988";
	// 笑话
	public static final String XiaoHuaId = "T1350383429665";
	// 笑话
	public static final String YouXiId = "T1348654151579";
	// 时尚
	public static final String ShiShangId = "T1348650593803";
	// 情感
	public static final String QingGanId = "T1348650839000";
	// 精选
	public static final String JingXuanId = "T1370583240249";
	// 电台
	public static final String DianTaiId = "T1379038288239";
	// nba
	public static final String NBAId = "T1348649145984";
	// 数码
	public static final String ShuMaId = "T1348649776727";
	// 数码
	public static final String YiDongId = "T1351233117091";
	// 彩票
	public static final String CaiPiaoId = "T1356600029035";
	// 教育
	public static final String JiaoYuId = "T1348654225495";
	// 论坛
	public static final String LunTanId = "T1349837670307";
	// 旅游
	public static final String LvYouId = "T1348654204705";
	// 手机
	public static final String ShouJiId = "T1348649654285";
	// 博客
	public static final String BoKeId = "T1349837698345";
	// 社会
	public static final String SheHuiId = "T1348648037603";
	// 家居
	public static final String JiaJuId = "T1348654105308";
	// 暴雪游戏
	public static final String BaoXueId = "T1397016069906";
	// 亲子
	public static final String QinZiId = "T1397116135282";
	// CBA
	public static final String CBAId = "T1348649475931";
	// 消息
	public static final String MsgId = "T1371543208049";

	public static final String FangChanId = "5YyX5Lqs";

	// 美 图
	public static final String TuPianMeitu = "http://c.m.163.com/recommend/getChanRecomNews?channel=T1456112189138&size=20&offset=";
	// 体育

	// 体坛 42262.json
	public static final String TuPianTiTanInit = host
			+ "photo/api/morelist/0096/54GM0096/42262.json";
	// 初始图集
	public static final String TuJiInit = host
			+ "photo/api/list/0096/54GI0096.json";// 42358.json

	public static final String TuJi = host
			+ "photo/api/morelist/0096/54GI0096/";// 42358.json

	// 图集end
	public static final String TuJiEnd = ".json";

	// 热点42577
	public static final String TuPianReDian = host
			+ "photo/api/morelist/0096/54GI0096/42577.json";
	// 独家42010
	public static final String TuPianDuJia = host
			+ "photo/api/morelist/0096/54GJ0096/";
	// 明星 42599.json
	public static final String TuPianMingXing = host
			+ "photo/api/morelist/0096/54GK0096/";

	// http://c.m.163.com/recommend/getChanRecomNews?channel=T1456112189138
	// &size=20&offset=40&fn=1&passport=&devId=1W%2FpqPI0eimZMOMbswN2gw%3D%3D&lat=w7Ifc7bHs%2Bfw2bqO4TuUNw%3D%3D&lon=KuBH1jAZjiNo60aoq4e1Ag%3D%3D&version=14.2&net=wifi&ts=1472895595&sign=sU4j0lqP9jfsfmwV9dwTTAGpFOwYk2mWrXTRAA3nHPh48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=meizu_store2014_news&mac=oHWFn0cEzVi3yidKr0L43U8dvF5D%2FPPigcKX35%2FLlyc%3D

	// http://c.m.163.com/recommend/getChanRecomNews?channel=T1456112189138

	// 视频 http://c.3g.163.com/nc/video/list/00850FRB/n/10-10.html
	public static final String Video = host + "nc/video/list/";
	public static final String VideoCenter = "/n/";
	public static final String videoEndUrl = "-20.html";
	// 热点视频
	public static final String VideoReDianId = "V9LG4B3A0";
	// 娱乐视频
	public static final String VideoYuLeId = "V9LG4CHOR";
	// 搞笑视频
	public static final String VideoGaoXiaoId = "V9LG4E6VR";
	// 精品视频
	public static final String VideoJingPinId = "00850FRB";

	// 天气预报url
	public static final String WeatherHost = "http://wthrcdn.etouch.cn/weather_mini?city=";
	// http://v.juhe.cn/weather/index?cityname=
	//
	// http://api.k780.com:88/?app=weather.city&format=json 获取城市列表

	public static final String WeatherKey = "&key=1734f933d24634331a24aaadc1cb088f";
	// 地址反编
	public static final String GeocoderUrl = "http://maps.google.cn/maps/api/geocode/json?language=zh-CN&sensor=true&";// latlng=38.92,121.62&sensor=false

}
