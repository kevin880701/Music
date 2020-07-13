package com.android.music.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class FolderInfo implements Serializable{

    public String folderName;

    public String folderPath;

    public ArrayList<MusicInfo> MusicInfo;

    public FolderInfo(String folderName){
        this.folderName = folderName;
//        this.folderPath = folderPath;
        MusicInfo = new ArrayList();
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public ArrayList<com.android.music.Model.MusicInfo> getMusicInfo() {
        return MusicInfo;
    }

    public void setMusicInfo(ArrayList<com.android.music.Model.MusicInfo> musicInfo) {
        MusicInfo = musicInfo;
    }
}

