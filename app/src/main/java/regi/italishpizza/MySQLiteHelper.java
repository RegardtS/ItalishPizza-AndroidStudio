package regi.italishpizza;

/**
 * Created by Regi on 2014/07/02.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;


public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ItalishDB";

    // Staff Table Name
    private static final String TABLE_STAFF = "STAFF";

    // Staff Table Columns names
    private static final String KEY_STAFF_ID = "id";
    private static final String KEY_STAFF_NAME = "name";
    private static final String KEY_STAFF_PASSWORD = "password";
    private static final String KEY_STAFF_AUTHORITY = "authority";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_STAFF + " ( "
                + KEY_STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_STAFF_NAME + " TEXT, " + KEY_STAFF_PASSWORD + " TEXT,"
                + KEY_STAFF_AUTHORITY + " TEXT)";

        // create books table
        db.execSQL(CREATE_STAFF_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);

        // create fresh books table
        this.onCreate(db);
    }

    public void addStaffMember(String name, String password,
                               String authorityLevel) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_STAFF_NAME, name);
        values.put(KEY_STAFF_PASSWORD, password);
        values.put(KEY_STAFF_AUTHORITY, authorityLevel);

        // 3. insert
        db.insert(TABLE_STAFF, // table
                null, // nullColumnHack
                values); // key/value -> keys = column names/ values = column
        // values

        // 4. close
        db.close();
    }

    public List<String> getAllStaffUsernames() {
        List<String> staff = new LinkedList<String>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_STAFF;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        if (cursor.moveToFirst()) {
            do {
                staff.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return staff;
    }

    public boolean verifyUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_STAFF + " WHERE "
                + KEY_STAFF_NAME + "='" + username + "' AND "
                + KEY_STAFF_PASSWORD + " ='" + password + "'";
        Cursor cursor = db.rawQuery(QUERY, null);
        if (cursor.getCount() <= 0) {
            return false;
        }
        return true;
    }
}