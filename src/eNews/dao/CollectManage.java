package eNews.dao;

import java.util.Currency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eNews.bean.CollectModel;
import eNews.common.DataBaseHelper;
import eNews.db.DbOpenHelper;

public class CollectManage implements CollectInterface {

	private DbOpenHelper dbOpenHelper;

	static CollectManage instance;

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
	public int deleteCollect(String id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		return db
				.delete(DataBaseHelper.collectTb, "_id=?", new String[] { id });
	}

}
