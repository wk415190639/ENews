package eNews.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eNews.bean.CollectModel;
import eNews.common.DataBaseHelper;
import eNews.db.DbOpenHelper;

/**
 * 
 * @author 王凯
 * @date 2016-9-12 我的收藏数据库封装类
 */
public class CollectManage implements CollectInterface {

	private DbOpenHelper dbOpenHelper;

	static CollectManage instance;
	String picTags[] = { DataBaseHelper.collectPic1,
			DataBaseHelper.collectPic2, DataBaseHelper.collectPic3 };

	private CollectManage(Context context) {
		// TODO Auto-generated constructor stub
		dbOpenHelper = new DbOpenHelper(context);

	}

	public static CollectManage getInstance(Context context) {
		if (instance == null)
			instance = new CollectManage(context);

		return instance;
	}

	@Override
	public long insertCollect(CollectModel collectModel) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.collectId, collectModel.getId());
		values.put(DataBaseHelper.collectDesc, collectModel.getDesc());
		values.put(DataBaseHelper.collectImgUrl, collectModel.getImgurl());
		values.put(DataBaseHelper.collectTitle, collectModel.getTitle());
		values.put(DataBaseHelper.collectType, collectModel.getType());
		values.put(DataBaseHelper.collectUrl, collectModel.getUrl());
		values.put(DataBaseHelper.collectUserId, collectModel.getUserId());

		if (collectModel.getPics() != null && collectModel.getPics().size() > 0) {

			for (int i = 0; i < collectModel.getPics().size(); i++) {
				values.put(picTags[i], collectModel.getPics().get(i));
			}

		}

		Cursor cursor = db.query(DataBaseHelper.collectTb, null,
				DataBaseHelper.collectTitle + "=? and "
						+ DataBaseHelper.collectType + "=?", new String[] {
						collectModel.getTitle(), collectModel.getType() },
				null, null, null, null);

		if (cursor.getCount() <= 0)
			return db.insert(DataBaseHelper.collectTb, null, values);
		else
			return -1;
	}

	@Override
	public int deleteCollect(CollectModel collectModel) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		String where = DataBaseHelper.collectType + "=? and "
				+ DataBaseHelper.collectTitle + "=? and "
				+ DataBaseHelper.collectUserId + " =?";
		String whereArgs[] = new String[] { collectModel.getType(),
				collectModel.getTitle(), collectModel.getUserId() };

		return db.delete(DataBaseHelper.collectTb, where, whereArgs);

	}

	@Override
	public ArrayList<CollectModel> query(String type, String openID) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ArrayList<CollectModel> arrayList = new ArrayList<CollectModel>();
		CollectModel collectModel;
		Cursor cursor;
		if (type != null) {
			if (type.equals(DataBaseHelper.PICTURE)
					|| type.equals(DataBaseHelper.MEITU)) {
				cursor = db.query(DataBaseHelper.collectTb, null,
						"type in (?,?) and " + DataBaseHelper.collectUserId
								+ "=?", new String[] { DataBaseHelper.PICTURE,
								DataBaseHelper.MEITU, openID }, null, null,
						null, null);
			} else
				cursor = db.query(DataBaseHelper.collectTb, null,
						"type =? and " + DataBaseHelper.collectUserId + "=?",
						new String[] { type, openID }, null, null, null, null);

		} else {

			cursor = db.query(DataBaseHelper.collectTb, null,
					DataBaseHelper.collectUserId + "=?",
					new String[] { openID }, null, null, "type", null);
		}

		while (cursor.moveToNext()) {
			collectModel = new CollectModel();

			collectModel.setType(cursor.getString(1));
			collectModel.setTitle(cursor.getString(2));
			collectModel.setUrl(cursor.getString(3));
			collectModel.setDesc(cursor.getString(4));
			collectModel.setImgurl(cursor.getString(5));
			collectModel.setId(cursor.getString(6));
			collectModel.setUserId(cursor.getString(7));

			if (type != null && type.equals(DataBaseHelper.PICTURE)) {
				ArrayList<String> picList = new ArrayList<String>();
				String tmpStr;
				for (int i = 8; i <= 10; i++) {
					tmpStr = cursor.getString(i);
					if (tmpStr != null && !tmpStr.isEmpty()) {
						picList.add(tmpStr);
					}
					tmpStr = "";
				}
				collectModel.setPics(picList);
			}

			arrayList.add(collectModel);

		}

		return arrayList;
	}

	@Override
	public int deleteCollectSet(String type) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		if (type == null)
			return db.delete(DataBaseHelper.collectTb, null, null);
		else

			return db.delete(DataBaseHelper.collectTb, "type=?",
					new String[] { type });
	}
}
