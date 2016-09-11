package eNews.db;

import eNews.common.DataBaseHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	public DbOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DbOpenHelper(Context context) {
		super(context, "database.db", null, 4);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String createChanelItem = "create table if not exists "
				+ DataBaseHelper.ChanelItemTb + " (" + DataBaseHelper._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + DataBaseHelper.ID
				+ "   INTEGER , " + DataBaseHelper.Name + " TEXT ,"
				+ DataBaseHelper.Selected + "  TEXT)";

		String createCollect = "create table if not exists "
				+ DataBaseHelper.collectTb + " (" + DataBaseHelper._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ DataBaseHelper.collectType + " TEXT ,"
				+ DataBaseHelper.collectTitle + " TEXT ,"
				+ DataBaseHelper.collectUrl + " TEXT ,"
				+ DataBaseHelper.collectDesc + " TEXT ,"
				+ DataBaseHelper.collectImgUrl + " TEXT,"
				+ DataBaseHelper.collectId + " TEXT,"
				+ DataBaseHelper.collectUserId + " TEXT)";

		System.out.println(createChanelItem);
		db.execSQL(createChanelItem);
		db.execSQL(createCollect);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
