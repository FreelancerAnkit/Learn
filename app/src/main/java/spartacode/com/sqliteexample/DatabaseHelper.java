package spartacode.com.sqliteexample;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "cityDatabase.db";
    private static final String TABLE_NAME = "city_table";
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "_cityname";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_TITLE+" TEXT"+")";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(DROP_TABLE);
        //db.execSQL("DELETE FROM " + TABLE_NAME);
        Log.d("OldVersion:",oldVersion+",NewVers:"+newVersion);
    }

    public void clearDatabase()
    {
        db.execSQL("DELETE FROM city_table"); //delete all rows in a table
        db.close();
    }

    public void addCity() {
        db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_ID, "1");
            values.put(KEY_TITLE, "City ");
            db.insert(TABLE_NAME, null, values);
            db.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    public int getNewsCount() {
        int num = 0;
        db = this.getReadableDatabase();
        try{
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return 0;
    }
}

