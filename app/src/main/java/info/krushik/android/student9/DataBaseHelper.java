package info.krushik.android.student9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "Students";
    public static final String TABLE_NAME_GROUP = "Groups";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ID_GROUP = "_idGroup";
    public static final String COLUMN_FIRST_NAME = "FirstName";
    public static final String COLUMN_LAST_NAME = "LastName";
    public static final String COLUMN_AGE = "Age";

    public static final String COLUMN_NUMBER = "Number";

    public DataBaseHelper(Context context) {//конструктор создания БД
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_GROUP + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NUMBER + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + COLUMN_ID_GROUP + " INTEGER NOT NULL,"
                + COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                + COLUMN_LAST_NAME + " TEXT NOT NULL,"
                + COLUMN_AGE + " INTEGER NOT NULL);");
//                + "FOREIGN KEY(" + COLUMN_ID_GROUP + ") REFERENCES " + TABLE_NAME_GROUP + "(id));");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveStudent(Student student){//сохранение студента
        SQLiteDatabase db = getWritableDatabase();
        long id=0;

        try {
            ContentValues values = new ContentValues();

            values.put(COLUMN_FIRST_NAME, student.FirstName);
            values.put(COLUMN_LAST_NAME, student.LastName);
            values.put(COLUMN_AGE, student.Age);

            id = db.insert(TABLE_NAME, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public Student getStudent(long id){//чтение студента
        SQLiteDatabase db = getWritableDatabase();
        Student student = null;
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_NAME, null, COLUMN_ID + "=" + id, null, null, null, null);//ищем указанного студента
            if (cursor.moveToFirst()) {//проверяем что что-то нашло
                student = new Student();// заполняем студента

                student.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                student.FirstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
                student.LastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
                student.Age = cursor.getLong(cursor.getColumnIndex(COLUMN_AGE));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return  student;
    }

    public long saveGroup(Group group){//сохранение группы
        SQLiteDatabase db = getWritableDatabase();
        long id=0;

        try {
            ContentValues values = new ContentValues();

            values.put(COLUMN_NUMBER, group.Number);

            id = db.insert(TABLE_NAME_GROUP, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public Group getGroup(long id){//чтение группы
        SQLiteDatabase db = getWritableDatabase();
        Group group = null;
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_NAME_GROUP, null, COLUMN_ID + "=" + id, null, null, null, null);//ищем указанную группу
            if (cursor.moveToFirst()) {//проверяем что что-то нашло
                group = new Group();// заполняем группу

                group.id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                group.Number = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return  group;
    }
}
