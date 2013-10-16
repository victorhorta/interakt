package me.interakt.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NodeDBHelper extends SQLiteOpenHelper {

	public static final String TABLE_TITLE = "nodes";
	public static final String COLUMN_ID = "_id";
	public static final String PARENT_ID = "parent";
	public static final String LABEL = "label";
	public static final String COLOR_HEX_STRING = "color";
	public static final String FILENAME = "filename";
	public static final String FILEPATH = "filepath";

	private static final String DATABASE_NAME = "nodes.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table " + TABLE_TITLE + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ PARENT_ID + " integer not null, "
				+ LABEL + " text not null, "
				+ COLOR_HEX_STRING + " text not null, "
				+ FILENAME + " text not null, "
				+ FILEPATH + " text not null "
				+");";

	public NodeDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(NodeDBHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TITLE);
	    onCreate(db);
	  }

}
