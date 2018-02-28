package cad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.User;

/**
 * Created by Aprendiz on 20/02/2018.
 */

public class database extends SQLiteOpenHelper {
    public database(Context context) {
        super(context, dbconstants.DB, null, dbconstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(database.SQL_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+dbconstants.User.TABLE);
        onCreate(db);
    }

    public static final String SQL_USER = "CREATE TABLE "+ dbconstants.User.TABLE + "(" +
            dbconstants.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            dbconstants.User.COLUMN_NAME + " TEXT," +
            dbconstants.User.COLUMN_DOCUMENT + " TEXT," +
            dbconstants.User.COLUMN_EMAIL + " TEXT UNIQUE," +
            dbconstants.User.COLUMN_PASSWORD + " TEXT NOT NULL" +
            ")";

    public int newUser(User user){
        if (checkUser(user)){
            return 2;
        }

        ContentValues cv = new ContentValues();
        cv.put(dbconstants.User.COLUMN_NAME, user.getName());
        cv.put(dbconstants.User.COLUMN_DOCUMENT, user.getDocument());
        cv.put(dbconstants.User.COLUMN_EMAIL, user.getEmail());
        cv.put(dbconstants.User.COLUMN_PASSWORD, user.getPassword());

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(dbconstants.User.TABLE,null,cv)>0?0:1;
    }

    public boolean deleteUser(User user){
        ContentValues cv = new ContentValues();
        cv.put(dbconstants.User.COLUMN_EMAIL,user.getEmail());

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(dbconstants.User.TABLE,dbconstants.User.COLUMN_EMAIL+"=?",new String[]{user.getEmail()})>0;
    }

    public boolean checkUser(User u){
        ContentValues cv = new ContentValues();

        SQLiteDatabase db = getReadableDatabase();
        String[] s = {dbconstants.User.COLUMN_EMAIL,"asdf"};
        Cursor c = db.query(dbconstants.User.TABLE,new String[]{dbconstants.User.COLUMN_EMAIL},dbconstants.User.COLUMN_EMAIL + "=?",new String[]{u.getEmail()},null,null,null);

        return c.getCount()>0;
    }
    public String m = "";
    public User searchUser(String email){
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor data = db.rawQuery("select * from "+dbconstants.User.TABLE + " where email='"+email+"'",null);
            if (data.moveToNext()){
                User u = new User();
                u.setDocument(data.getString(data.getColumnIndex(dbconstants.User.COLUMN_DOCUMENT)));
                u.setName(data.getString(data.getColumnIndex(dbconstants.User.COLUMN_NAME)));
                u.setPassword(data.getString(data.getColumnIndex(dbconstants.User.COLUMN_PASSWORD)));
                u.setEmail(email);
                u.set_id(data.getLong(data.getColumnIndex(dbconstants.User._ID)));
                return u;
            }
            else{
                return null;
            }
        }catch (Exception e){
            m = e.getMessage();
            return null;
        }
    }

    public int updateUser(User user) {
        try {
            if (checkUser(user)) {
                return 2;
            }

            ContentValues cv = new ContentValues();
            cv.put(dbconstants.User.COLUMN_NAME, user.getName());
            cv.put(dbconstants.User.COLUMN_DOCUMENT, user.getDocument());
            cv.put(dbconstants.User.COLUMN_EMAIL, user.getEmail());
            cv.put(dbconstants.User.COLUMN_PASSWORD, user.getPassword());

            SQLiteDatabase db = getWritableDatabase();
            return db.update(dbconstants.User.TABLE,cv,dbconstants.User._ID+" = ?",new String[]{user.get_id()+""}) > 0 ? 0 : 1;
        }
        catch (Exception e){
            m = e.getMessage();
            return Integer.parseInt(null);
        }
    }

    public Cursor listAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(dbconstants.User.TABLE,)
    }
}
