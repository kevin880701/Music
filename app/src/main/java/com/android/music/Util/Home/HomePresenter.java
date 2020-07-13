package com.android.music.Util.Home;

import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.viewpager.widget.ViewPager;

import com.android.music.Adapter.ViewPagerAdapter;
import com.android.music.Model.FolderInfo;
import com.android.music.Model.Model;
import com.android.music.Model.MusicInfo;
import com.android.music.SQLite.FeedReaderContract;
import com.android.music.Util.FolderList.FolderListActivity;
import com.android.music.Util.Setting.SettingFragment;
import com.android.music.Util.Single.Single;

import java.util.ArrayList;

public class HomePresenter {
    ViewPagerAdapter pagerAdapter;
    HomeFragment homeFragment;
    ArrayList<FolderInfo> FolderInfoList;
    Model model;
    SQLiteDatabase musicListDB;

    public HomePresenter(Model model,HomeFragment homeFragment){
        this.model = model;
        this.homeFragment = homeFragment;
        this.musicListDB = model.getMusicListDB();
    }


    public void createDB(){

            String createTable = "CREATE TABLE IF NOT EXISTS '" + "setting" + "'( " +
                    FeedReaderContract.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FeedReaderContract.folderName + " VARCHAR(250), " +
                    FeedReaderContract.folderPath + " VARCHAR(250), " +
                    FeedReaderContract.musicName + " VARCHAR(250)," +
                    FeedReaderContract.musicPath + " VARCHAR(250)," +
                    FeedReaderContract.musicUri + " VARCHAR(250)," +
                    FeedReaderContract.musicLong + " LONG" +
                    ");";

            musicListDB.execSQL(createTable);

    }

    public void getMusicInfo() {
        FolderInfoList = new ArrayList();
        // 獲取所有資料表
        Cursor tableCursor = musicListDB.query("sqlite_sequence",     // 資料表名字
                new String[]{"name" ,"seq"},  // 要取出的欄位資料
                null,                                              // 查詢條件式
                null,                                              // 查詢條件值字串陣列
                null,                                              // Group By字串語法
                null,                                              // Having字串法
                "name",                                            // Order By字串語法(排序)
                null);                                             // Limit字串語法
        while(tableCursor.moveToNext()){
            FolderInfo createFolder = new FolderInfo(tableCursor.getString(tableCursor.getColumnIndex("name")));
            FolderInfoList.add(createFolder);
        }
        try {
            for(int i = 0 ; i < FolderInfoList.size() ; i++) {
                //Cursor cursor = musicListDB.query(FolderInfoList.get(i).getFolderName(),  // 資料表名字
                //        new String[]{FeedReaderContract.id,
                //                FeedReaderContract.folderName,
                //                FeedReaderContract.folderPath,
                //                FeedReaderContract.musicName,
                //                FeedReaderContract.musicPath,
                //                FeedReaderContract.musicUri,
                //                FeedReaderContract.musicLong},  // 要取出的欄位資料
                //        null,                                              // 查詢條件式
                //        null,                                              // 查詢條件值字串陣列
                //        null,                                              // Group By字串語法
                //        null,                                              // Having字串法
                //        FeedReaderContract.id,                                            // Order By字串語法(排序)
                //        null);                                             // Limit字串語法
                //String selectSQL = "SELECT '" + FeedReaderContract.id + "','" +
                //        FeedReaderContract.folderName + "','" +
                //        FeedReaderContract.folderPath + "','" +
                //        FeedReaderContract.musicName + "','" +
                //        FeedReaderContract.musicPath + "','" +
                //        FeedReaderContract.musicUri + "','" +
                //        FeedReaderContract.musicLong + "' FROM '" + FolderInfoList.get(i).getFolderName() + "'";
                String selectSQL = "SELECT * FROM '" + FolderInfoList.get(i).getFolderName() + "' ORDER BY '" + FeedReaderContract.musicName + "' ASC";
                Cursor cursor = musicListDB.rawQuery(selectSQL,null);
                while (cursor.moveToNext()) {
                    String folderName = cursor.getString(cursor.getColumnIndex(FeedReaderContract.folderName));
                    String folderPath = cursor.getString(cursor.getColumnIndex(FeedReaderContract.folderPath));
                    String musicName = cursor.getString(cursor.getColumnIndex(FeedReaderContract.musicName));
                    String musicPath = cursor.getString(cursor.getColumnIndex(FeedReaderContract.musicPath));
                    String musicUri = cursor.getString(cursor.getColumnIndex(FeedReaderContract.musicUri));
                    Long musicLong = cursor.getLong(cursor.getColumnIndex(FeedReaderContract.musicLong)); //音樂長度

                    MusicInfo newMusicInfo = new MusicInfo();
                    newMusicInfo.setMusicName(musicName);
                    newMusicInfo.setMusicPath(musicPath);
                    newMusicInfo.setMusicUri(musicUri);
                    newMusicInfo.setMusicLong(musicLong);
                    FolderInfoList.get(i).setFolderPath(folderPath);
                    FolderInfoList.get(i).getMusicInfo().add(newMusicInfo);
                }
            }
        }catch (Exception e){
            Log.v("ERROR","" + e);
        }

    }

    public void setupViewPager(ViewPager viewPager) {
        pagerAdapter.addFragment(new FolderListActivity(FolderInfoList,model), "資料夾");
        pagerAdapter.addFragment(new Single(), "歌單");
        pagerAdapter.addFragment(new SettingFragment(model), "設定");

        viewPager.setAdapter(pagerAdapter);
    }

    public void setPagerAdapter(ViewPagerAdapter pagerAdapter){
        this.pagerAdapter = pagerAdapter;
    }
}
