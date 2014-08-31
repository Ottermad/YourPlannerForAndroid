package net.attwoodthomas.yourplanner.app.database.helper;

import java.util.HashMap;

import net.attwoodthomas.yourplanner.app.FourthActivity;
import net.attwoodthomas.yourplanner.app.MainActivity;
import net.attwoodthomas.yourplanner.app.SeventhActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Member Variables
	
	// Base Functions

    public DatabaseHelper(Context paramContext)
    {
        super(paramContext, "lessons", null, 1);
    }

    // Create Tables and insert data if need be
    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
        // Create Table lessons to store people's timetable
        paramSQLiteDatabase.execSQL("CREATE TABLE lessons (Week VARCHAR(1), Day VARCHAR(10), Period1 VACHAR(20),Period2 VARCHAR(20),Period3 VARCHAR(20),Period4 VARCHAR(20),Period5 VARCHAR(20),Period6 VARCHAR(20), Period7 VARCHAR(20));");

        // Create Table homework to store people's homework
        paramSQLiteDatabase.execSQL("CREATE TABLE homework (Subject VARCHAR(50), DateDue VARCHAR(50), Description VARCHAR(100));");

        // Create Table past_homework to store people's completed homework
        paramSQLiteDatabase.execSQL("CREATE TABLE past_homework (Subject VARCHAR(50), DateDue VARCHAR(50), Description VARCHAR(100));");

        // Create Table merits to store people's merits
        paramSQLiteDatabase.execSQL("CREATE TABLE merits (total_merits VARCHAR(5), used_merits VARCHAR(5));");

        // Create Table codes to store the teacher's codes they enter to add merits
        paramSQLiteDatabase.execSQL("CREATE TABLE codes (code VARCHAR(10));");

        // Create Table periods to store how many periods the user has in  a day
        paramSQLiteDatabase.execSQL("CREATE TABLE periods (periods VARCHAR(10));");

        // Create Table name
        paramSQLiteDatabase.execSQL("CREATE table name (name VARCHAR(50));");

        // Create Table photo
        paramSQLiteDatabase.execSQL("CREATE table photo (path VARCHAR(500));");

        // Check if lessons table is populated if not insert dummy data
        if (paramSQLiteDatabase.rawQuery("SELECT * FROM lessons", null).moveToFirst()) {
            Log.d("DatabaseHelper", "not emtpy");
            return;
        } else {
            Log.d("DatabaseHelper", "emtpy");
            paramSQLiteDatabase.execSQL("INSERT INTO lessons VALUES ('A', 'Monday', 'PE', 'PE', 'SE', 'Reading', 'Drama','Drama', 'Music'), ('A', 'Tuesday', 'PE', 'PE', 'SE', 'Reading','Drama', 'Drama', 'Music'),('A', 'Wednesday','Drama', 'PE', 'PE', 'SE', 'Reading', 'Drama', 'Music'),('A', 'Thursday', 'PE', 'PE', 'Drama','SE', 'Reading', 'Drama', 'Music'),('A', 'Friday', 'PE', 'PE', 'SE','SE', 'Reading', 'Drama', 'Music'),('B', 'Monday', 'PE', 'PE', 'SE', 'Drama','Reading', 'Drama', 'Music'),('B', 'Tuesday', 'PE', 'Drama','PE', 'SE', 'Reading', 'Drama', 'Music'),('B', 'Wednesday', 'PE', 'PE', 'SE', 'Drama', 'Reading', 'Drama', 'Music'), ('B', 'Thursday', 'PE', 'PE','Drama', 'SE', 'Reading', 'Drama', 'Music'),('B', 'Friday', 'PE', 'PE', 'SE','Drama', 'Reading', 'Drama', 'Music');");
        }

        // Check if homwork is not populated if so insert dummy data
        if (!paramSQLiteDatabase.rawQuery("SELECT * FROM homework", null).moveToFirst()) {
            paramSQLiteDatabase.execSQL("INSERT INTO homework VALUES ('Maths', '08.03.04', 'Finish p38');");
        }

        // Check if merits is not populated if so insert dummy data
        if (!paramSQLiteDatabase.rawQuery("SELECT * FROM merits", null).moveToFirst()) {
            paramSQLiteDatabase.execSQL("INSERT INTO merits VALUES ('0', '0');");
        }

        // Check if codes is not populated if so insert dummy data
        if (!paramSQLiteDatabase.rawQuery("SELECT * FROM codes", null).moveToFirst()) {
            paramSQLiteDatabase.execSQL("INSERT INTO codes VALUES ('ABCDEFGHIJ');");
        }


    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        // Remove then recreate tables
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE lessons (Week VARCHAR(1), Day VARCHAR(10), Period1 VACHAR(20),Period2 VARCHAR(20),Period3 VARCHAR(20),Period4 VARCHAR(20),Period5 VARCHAR(20),Period6 VARCHAR(20), Period7 VARCHAR(20));");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE homework (Subject VARCHAR(50), DateDue VARCHAR(50), Description VARCHAR(100));");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE past_homework (Subject VARCHAR(50), DateDue VARCHAR(50), Description VARCHAR(100));");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE merits (total_merits VARCHAR(5), used_merits VARCHAR(5));");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE codes (code VARCHAR(10));");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE TABLE periods (periods VARCHAR(10));");
        onCreate(paramSQLiteDatabase);
    }

    // Homework Functions

    public void getHomework() {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] tblName  = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM homework",tblName);

        localCursor.moveToFirst();
        while (localCursor.isAfterLast() == false)
        {

            HashMap<String, String> homework = new HashMap<String, String>();
            homework.put("Subject", localCursor.getString(0));
            homework.put("DueDate", localCursor.getString(1));
            FourthActivity.mHomeworkDue.add(homework);
            localCursor.moveToNext();
        }


    }

    public void getCompletedHomework() {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] tblName  = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM past_homework",tblName);

        localCursor.moveToFirst();
        while (localCursor.isAfterLast() == false)
        {

            HashMap<String, String> homework = new HashMap<String, String>();
            homework.put("Subject", localCursor.getString(0));
            homework.put("DueDate", localCursor.getString(1));
            SeventhActivity.mHomeworkDue.add(homework);
            localCursor.moveToNext();
        }


    }

    public int getNumberOfHomework() {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] tblName  = {};
        int total = 0;
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM homework",tblName);

        localCursor.moveToFirst();
        while (localCursor.isAfterLast() == false)
        {
            total = total + 1;
            localCursor.moveToNext();
        }
        return total;
    }

    public int getNumberOfCompletedHomework() {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] tblName  = {};
        int total = 0;
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM past_homework",tblName);

        localCursor.moveToFirst();
        while (localCursor.isAfterLast() == false)
        {
            total = total + 1;
            localCursor.moveToNext();
        }
        return total;
    }

    public String[] returnAllValues(String subject, String datedue) {
        String[] data = {"", "", ""};
        String[] args = {subject, datedue};
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM homework WHERE Subject = ? AND DateDue = ?", args);
        localCursor.moveToFirst();

        data[0] = localCursor.getString(0);
        data[1] = localCursor.getString(1);
        data[2] = localCursor.getString(2);
        return data;

    }

    public void addHomework(String paramString1, String paramString2, String paramString3) {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "INSERT INTO homework VALUES ('" + paramString1 + "', '" + paramString2 + "', '" + paramString3 + "');";
        localSQLiteDatabase.execSQL(str1);
    }

    public void deleteHomework(String[] conditions) {

        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        localSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS past_homework (Subject VARCHAR(50), DateDue VARCHAR(50), Description VARCHAR(100));");
        String str1 = "DELETE FROM homework WHERE Subject = '" + conditions[0] + "' AND DateDue = '" + conditions[1] + "' AND Description  = '" + conditions[2] + "';";

        localSQLiteDatabase.execSQL(str1);

        String str2 = "INSERT INTO past_homework VALUES ('" + conditions[0] + "', '" + conditions[1] + "', '" + conditions[2] + "');";

        localSQLiteDatabase.execSQL(str2);

    }

    public void deleteHomeworkForever(String[] conditions) {

        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "DELETE FROM past_homework WHERE Subject = '" + conditions[0] + "' AND DateDue = '" + conditions[1] + "' AND Description  = '" + conditions[2] + "';";

        localSQLiteDatabase.execSQL(str1);


    }

    public String[] returnAllDoneValues(String subject, String datedue) {
        String[] data = {"", "", ""};
        String[] args = {subject, datedue};
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM past_homework WHERE Subject = ? AND DateDue = ?", args);
        localCursor.moveToFirst();

        data[0] = localCursor.getString(0);
        data[1] = localCursor.getString(1);
        data[2] = localCursor.getString(2);
        return data;

    }

    // Lesson / Timetable Functions

    public void getLessons()
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString = {MainActivity.mWeek, MainActivity.mDay};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT Period1, Period2, Period3, Period4, Period5, Period6, Period7 FROM lessons WHERE Week = ? AND day = ?", arrayOfString);
        Log.d("DatabaseHelper", "1");
        localCursor.moveToNext();
        Log.d("DatabaseHelper", "2");
        net.attwoodthomas.yourplanner.app.SecondActivity.period1 = localCursor.getString(localCursor.getColumnIndex("Period1"));
        Log.d("DatabaseHelper", "3");
        net.attwoodthomas.yourplanner.app.SecondActivity.period2 = localCursor.getString(localCursor.getColumnIndex("Period2"));
        Log.d("DatabaseHelper", "4");
        net.attwoodthomas.yourplanner.app.SecondActivity.period3 = localCursor.getString(localCursor.getColumnIndex("Period3"));
        Log.d("DatabaseHelper", "5");
        net.attwoodthomas.yourplanner.app.SecondActivity.period4 = localCursor.getString(localCursor.getColumnIndex("Period4"));
        Log.d("DatabaseHelper", "6");
        net.attwoodthomas.yourplanner.app.SecondActivity.period5 = localCursor.getString(localCursor.getColumnIndex("Period5"));
        Log.d("DatabaseHelper", "7");
        net.attwoodthomas.yourplanner.app.SecondActivity.period6 = localCursor.getString(localCursor.getColumnIndex("Period6"));
        Log.d("DatabaseHelper", "8");
        net.attwoodthomas.yourplanner.app.SecondActivity.period7 = localCursor.getString(localCursor.getColumnIndex("Period7"));
        Log.d("DatabaseHelper", "9");
        localCursor.close();
    }

    public void updateLessons(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "DELETE FROM lessons WHERE week = '" + paramString1 + "' AND day = '" + paramString2 + "';";
        String str2 = "INSERT INTO lessons VALUES ('" + paramString1 + "', '" + paramString2 + "', '" + paramString3 + "', '" + paramString4 + "', '" + paramString5 + "', '" + paramString6 + "', '" + paramString7 + "', '" + paramString8 + "', '" + paramString9 + "');";
        localSQLiteDatabase.execSQL(str1);
        localSQLiteDatabase.execSQL(str2);
    }

    // Merit Functions

    public String[] getMerits() {
        String[] merits = {"", ""};
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM merits", arrayOfString);
        localCursor.moveToNext();
        merits[0] = localCursor.getString(0);
        merits[1] = localCursor.getString(1);
        return merits;


    }

    public boolean addMerits(String code, String amount) {

        String total_merits = "";
        String used_merits = "";
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM merits", arrayOfString);
        localCursor.moveToNext();
        total_merits = localCursor.getString(localCursor.getColumnIndex("total_merits"));
        used_merits = localCursor.getString(localCursor.getColumnIndex("used_merits"));

        boolean valid_code = false;
        localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM codes", arrayOfString);
        localCursor.moveToFirst();
        while (localCursor.isAfterLast() == false) {

            if (code.equals(localCursor.getString(0))) {
                valid_code = true;
                break;
            } else {
                localCursor.moveToNext();
            }
        }

        System.out.println(code);

        try {
            System.out.println(localCursor.getString(0));

        } catch (Exception e) {
            localSQLiteDatabase.execSQL("INSERT INTO codes VALUES ('ABCDEFGHIJ');");
        }

        System.out.println(code.equals(localCursor.getString(0)));
        Log.d("TAG", total_merits);
        Log.d("TAG", used_merits);

        if (valid_code == true) {
            int total_merits_num = Integer.parseInt(total_merits);
            int amount_num = Integer.parseInt(amount);
            String total = Integer.toString(total_merits_num + amount_num);
            Log.d("db", total);
            localSQLiteDatabase.execSQL("DELETE FROM merits WHERE total_merits = '" + total_merits + "';");
            localSQLiteDatabase.execSQL("INSERT INTO merits VALUES ('" + total + "', '" + used_merits + "');");
        }



        return valid_code;

    }

    public boolean spendMerits(String amount) {

        String total_merits = "";
        String used_merits = "";
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM merits", arrayOfString);
        localCursor.moveToNext();
        total_merits = localCursor.getString(localCursor.getColumnIndex("total_merits"));
        used_merits = localCursor.getString(localCursor.getColumnIndex("used_merits"));

        Log.d("TAG2", total_merits);
        Log.d("TAG2", used_merits);

        int total_merits_num = Integer.parseInt(total_merits);
        int used_merits_num = Integer.parseInt(used_merits);
        int amount_num = Integer.parseInt(amount);
        if (total_merits_num - used_merits_num >= 0 && total_merits_num > 0) {
            String total = Integer.toString(used_merits_num + amount_num);
            localSQLiteDatabase.execSQL("DELETE FROM merits WHERE used_merits = '" + used_merits + "';");
            localSQLiteDatabase.execSQL("INSERT INTO merits VALUES ('" + total_merits + "', '" + total + "');");
            return true;
        } else {
            return false;
        }





    }




    // Number of Period Functions

    public boolean checkPeriodNumber() {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] tblName  = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM periods",tblName);

        localCursor.moveToFirst();
        String numberOfPeriods = localCursor.getString(0);
        if (numberOfPeriods.equals("0")) {
           return false;
        } else {
            return true;
        }

    }

    public void updateNumberOfPeriods (String numberOfPeriods) {

        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "INSERT INTO periods VALUES ('" + numberOfPeriods + "');";
        localSQLiteDatabase.execSQL(str1);


    }

    // Name Functions
    public boolean checkName() {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        // Check if name is not populated if so return false
        if (localSQLiteDatabase.rawQuery("SELECT * FROM name", null).moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public void updateName(String name) {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        if (localSQLiteDatabase.rawQuery("SELECT * FROM name", null).moveToFirst()) {
            localSQLiteDatabase.execSQL("DELETE FROM name");
        }
        localSQLiteDatabase.execSQL("INSERT INTO name VALUES ('" + name + "');");


    }

    public String getName() {

        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM name", arrayOfString);
        localCursor.moveToNext();
        String name = localCursor.getString(localCursor.getColumnIndex("name"));
        return name;

    }

    // Photo Functions

    public void insertPhoto (String path) {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String str1 = "DELETE FROM photo";
        String str2 = "INSERT INTO photo VALUES ('" + path + "');";
        localSQLiteDatabase.execSQL(str1);
        localSQLiteDatabase.execSQL(str2);
    }

    public String getPhotoPath() {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString = {};
        Cursor localCursor = localSQLiteDatabase.rawQuery("SELECT * FROM photo", arrayOfString);
        localCursor.moveToNext();
        String name = localCursor.getString(localCursor.getColumnIndex("path"));
        return name;
    }

}
