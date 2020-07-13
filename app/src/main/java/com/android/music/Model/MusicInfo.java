package com.android.music.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class MusicInfo implements Serializable {

    public String musicName;

    public String musicPath;

    public String musicUri;

    public long musicLong;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getMusicUri() {
        return musicUri;
    }

    public void setMusicUri(String musicUri) {
        this.musicUri = musicUri;
    }

    public long getMusicLong() {
        return musicLong;
    }

    public void setMusicLong(long musicLong) {
        this.musicLong = musicLong;
    }
}

