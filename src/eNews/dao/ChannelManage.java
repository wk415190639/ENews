package eNews.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eNews.bean.ChannelItemModel;
import eNews.common.DataBaseHelper;
import eNews.db.DbOpenHelper;

public class ChannelManage implements ChannelInterface {

	private ArrayList<ChannelItemModel> defaultUserChannelsList;
	private ArrayList<ChannelItemModel> defaultOtherChannelsList;
	private DbOpenHelper dbOpenHelper;

	private static ChannelManage newInstance;

	public ArrayList<ChannelItemModel> getDefaultUserChannelsList() {

		return defaultUserChannelsList;
	}

	public ArrayList<ChannelItemModel> getDefaultOtherChannelsList() {

		return defaultOtherChannelsList;
	}

	public boolean isExistSQl() {

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		Cursor ChanelItems = db.query(DataBaseHelper.ChanelItemTb,
				new String[] { DataBaseHelper._ID }, null, null, null, null,
				null);

		if (ChanelItems.getCount() > 0) {

			Cursor userChannelSets = db.query(DataBaseHelper.ChanelItemTb,
					null, DataBaseHelper.Selected + "=?",
					new String[] { DataBaseHelper.SelectedUser }, null, null,
					"id");
			if (defaultUserChannelsList.size() <= 0
					&& defaultOtherChannelsList.size() <= 0) {
				while (userChannelSets.moveToNext()) {

					defaultUserChannelsList
							.add(new ChannelItemModel(
									userChannelSets.getInt(1), userChannelSets
											.getString(2),
									DataBaseHelper.SelectedUser));
				}

				Cursor otherChannelSets = db.query(DataBaseHelper.ChanelItemTb,
						null, DataBaseHelper.Selected + "=?",
						new String[] { DataBaseHelper.SelectedOther }, null,
						null, "id");

				while (otherChannelSets.moveToNext()) {
					defaultOtherChannelsList
							.add(new ChannelItemModel(otherChannelSets
									.getInt(1), otherChannelSets.getString(2),
									DataBaseHelper.SelectedOther));

				}
			}
			db.close();
			return true;
		}

		return false;
	}

	private ChannelManage(Context context) {
		dbOpenHelper = new DbOpenHelper(context);
		defaultUserChannelsList = new ArrayList<ChannelItemModel>();
		defaultOtherChannelsList = new ArrayList<ChannelItemModel>();
		if (!isExistSQl()) {
			setdefaultChannelsList();
			System.out.println("no founf database");
		}
	}

	public static ChannelManage getInstance(Context context) {

		if (newInstance == null)
			newInstance = new ChannelManage(context);

		return newInstance;
	}

	private void setdefaultChannelsList() {

		defaultUserChannelsList.add(new ChannelItemModel(1, "头条",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(2, "足球",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(3, "娱乐",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(4, "体育",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(5, "财经",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(6, "科技",
				DataBaseHelper.SelectedUser));

		defaultOtherChannelsList.add(new ChannelItemModel(7, "CBA",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(8, "笑话",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(9, "汽车",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(10, "时尚",

		DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(13, "房产",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(14, "游戏",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(15, "精选",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(16, "电台",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(17, "情感",
				DataBaseHelper.SelectedOther));

		defaultUserChannelsList.add(new ChannelItemModel(18, "电影",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(19, "NBA",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(20, "数码",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(21, "移动",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(22, "彩票",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(23, "教育",
				DataBaseHelper.SelectedUser));
		defaultUserChannelsList.add(new ChannelItemModel(24, "论坛",
				DataBaseHelper.SelectedUser));

		defaultOtherChannelsList.add(new ChannelItemModel(25, "旅游",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(26, "手机",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(27, "博客",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(28, "社会",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(29, "家居",
				DataBaseHelper.SelectedOther));
		defaultOtherChannelsList.add(new ChannelItemModel(30, "暴雪",
				DataBaseHelper.SelectedOther));

		defaultOtherChannelsList.add(new ChannelItemModel(31, "亲子",
				DataBaseHelper.SelectedOther));

		for (ChannelItemModel userChannel : defaultUserChannelsList) {
			inserToUser(userChannel);
		}
		for (ChannelItemModel otherChannel : defaultOtherChannelsList) {
			inserToOther(otherChannel);
		}

	}

	@Override
	public long inserToOther(ChannelItemModel otherChannel) {

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.ID, otherChannel.getId());
		values.put(DataBaseHelper.Name, otherChannel.getName());
		values.put(DataBaseHelper.Selected, DataBaseHelper.SelectedOther);
		return db.insert(DataBaseHelper.ChanelItemTb, null, values);

	}

	@Override
	public long inserToUser(ChannelItemModel userChannel) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.ID, userChannel.getId());
		values.put(DataBaseHelper.Name, userChannel.getName());
		values.put(DataBaseHelper.Selected, DataBaseHelper.SelectedUser);
		return db.insert(DataBaseHelper.ChanelItemTb, null, values);
	}

	@Override
	public int updateUser(String name, String id) {
		// TODO Auto-generated method stub

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(DataBaseHelper.ID, id);
		contentValues.put(DataBaseHelper.Selected, DataBaseHelper.SelectedUser);
		return db.update(DataBaseHelper.ChanelItemTb, contentValues,
				DataBaseHelper.Name + "=?", new String[] { name });
	}

	@Override
	public int updateOther(String name, String id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(DataBaseHelper.ID, id);
		contentValues
				.put(DataBaseHelper.Selected, DataBaseHelper.SelectedOther);

		return db.update(DataBaseHelper.ChanelItemTb, contentValues,
				DataBaseHelper.Name + "=?", new String[] { name });
	}

}
