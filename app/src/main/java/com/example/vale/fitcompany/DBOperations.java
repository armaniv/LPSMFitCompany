package com.example.vale.fitcompany;

/**
 * Created by Vale on 24/07/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/*
import com.example.lesson03.lesson03.Entities.Article;
import com.example.lesson03.lesson03.R;*/

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DBOperations {

    private SQLiteDatabase mDb;
    private DBHelper dbHelper;
    // private static String DB_PATH = null;
    private static String DB_PATH = "/data/data/com.example.vale/databases/";
    private static final String DB_NAME = "GymDB.db";
    private static final int DB_VERSION = 1;

    private static DBOperations instance = null;

    private DBOperations(Context ctx) {
        dbHelper = new DBHelper(ctx, DB_NAME, null, DB_VERSION);
    }

    public static DBOperations getInstance(Context ctx) {
        if (instance == null) {
            instance = new DBOperations(ctx);
            // DB_PATH =
            // ctx.getFilesDir().getPath()+"data/android.lesson02/databases/";
        }
        return instance;
    }

    public void eraseDB(String Param) {
        mDb.execSQL("DROP TABLE IF EXISTS "+ Param);
    }

    public void populateDB(Context ctx)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(
                    new InputStreamReader(ctx.getAssets().open("Insert.sql"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null)
            {
                mDb.execSQL(mLine);
            }
        } catch (Exception e)
        {
            Log.e("Errore", "Descrizione: " +e.toString() );
        } finally
        {
            if (reader != null) {
                try
                {
                    reader.close();
                } catch (Exception e)
                {
                    Log.e("Errore", "Descrizione: " +e.toString() );
                }
            }
        }
    }
    
    /*

    public ArrayList<Article> fetchArticle() {
        ArrayList<Article> res = new ArrayList<Article>();

        Cursor c = mDb.query(DBMetadata.ARTICLE_TABLE, null, null, null, null, null, null);
        int idCol = c.getColumnIndex(DBMetadata.ARTICLE_ID);
        int descCol = c.getColumnIndex(DBMetadata.ARTICLE_DESCRIPTION);
        int imageCol = c.getColumnIndex(DBMetadata.ARTICLE_IMAGE);

        if (c.moveToFirst()) {
            do {
                Article article = new Article();
                article.setId(c.getInt(idCol));
                article.setArticleName(c.getString(descCol));

                if (c.getBlob(imageCol) != null) {
                    InputStream is = new ByteArrayInputStream(c.getBlob(imageCol), 0, c.getBlob(imageCol).length);
                    article.setImage(BitmapFactory.decodeStream(is));
                }

                res.add(article);
            } while (c.moveToNext());
        }
        return res;
    }

    public ArrayList<Article> fetchArticleWithName(String name) {
        ArrayList<Article> res = new ArrayList<Article>();

        String whereClause = DBMetadata.ARTICLE_DESCRIPTION + "==" + name;
        Cursor c = mDb.query(DBMetadata.ARTICLE_TABLE, null, whereClause,
                null, null, null, null);
        int descCol = c.getColumnIndex(DBMetadata.ARTICLE_DESCRIPTION);
        int imageCol = c.getColumnIndex(DBMetadata.ARTICLE_IMAGE);

        if (c.moveToFirst()) {
            do {
                Article article = new Article();
                article.setArticleName(c.getString(descCol));

                if (c.getBlob(imageCol) != null) {
                    InputStream is = new ByteArrayInputStream(
                            c.getBlob(imageCol), 0, c.getBlob(imageCol).length);
                    article.setImage(BitmapFactory.decodeStream(is));
                }

                res.add(article);
            } while (c.moveToNext());
        }
        return res;
    }


    */

    public void open() {
        mDb = dbHelper.getWritableDatabase();
    }

    public void close() {
        mDb.close();
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {

            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

}