package com.android.music.Util.Setting;

import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.android.music.Model.FolderInfo;
import com.android.music.Model.Model;
import com.android.music.SQLite.FeedReaderContract;

import java.util.ArrayList;

public class SettingPresenter {
    SettingFragment settingFragment;
    Model model;
    SQLiteDatabase musicListDB;
    ArrayList<FolderInfo> FolderInfoList;

    public SettingPresenter(Model model, SettingFragment settingFragment){
        this.model = model;
        this.settingFragment = settingFragment;
    }

    public void dropDB(){
        musicListDB = model.getMusicListDB();

        // 清空資料庫
        ArrayList tableName = new ArrayList();
        String query = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;";
        Cursor c = musicListDB.rawQuery(query, null);
        while (c.moveToNext()) {
            if(!(c.getString(0).equals("sqlite_sequence") || c.getString(0).equals("android_metadata"))){
                musicListDB.execSQL("DROP TABLE '" + c.getString(0) + "';");
            }
        }
    }

    public void createDB(){
        FolderInfoList = new ArrayList();
        Cursor cursor = settingFragment.getActivity().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        for (int i = 1; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            long musicLong = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); //音樂長度
            int musicId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            Uri musicUri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, musicId);

            final String column = "_data";
            final int column_index = cursor.getColumnIndexOrThrow(column);

            String musicPath = cursor.getString(column_index);
            String musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

            //取得存放的資料夾
            int a = musicPath.lastIndexOf("/");
            int b = musicPath.substring(0,a-1).lastIndexOf("/");
            String musicFolderName = musicPath.substring(b+1,a);
            String musicFolderPath = musicPath.substring(0,a+1);

            String createTable = "CREATE TABLE IF NOT EXISTS '" + musicFolderName + "'( " +
                    FeedReaderContract.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FeedReaderContract.folderName + " VARCHAR(250), " +
                    FeedReaderContract.folderPath + " VARCHAR(250), " +
                    FeedReaderContract.musicName + " VARCHAR(250)," +
                    FeedReaderContract.musicPath + " VARCHAR(250)," +
                    FeedReaderContract.musicUri + " VARCHAR(250)," +
                    FeedReaderContract.musicLong + " LONG" +
                    ");";

            String sql = "INSERT into '" + musicFolderName + "' ( '" + FeedReaderContract.folderName
                    + "','" + FeedReaderContract.folderPath
                    + "','" + FeedReaderContract.musicName
                    + "','" + FeedReaderContract.musicPath
                    + "','" + FeedReaderContract.musicUri
                    + "','" + FeedReaderContract.musicLong + "' ) " +
                    "VALUES (?,?,?,?,?,?)";
            Object[] mValue = new Object[]{musicFolderName,musicFolderPath,musicName,musicPath,musicUri,musicLong};

            musicListDB.execSQL(createTable);
            musicListDB.execSQL(sql,mValue);
        }
    }
}
