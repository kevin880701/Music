package com.android.music.Util.FolderContain;

import com.android.music.Model.FolderInfo;
import com.android.music.Model.Model;
import com.android.music.Model.PlayerController;

public class FolderContainPresenter {

    Model model;
    PlayerController playerController;
    FolderInfo folderInfo;

    public FolderContainPresenter(Model model, FolderInfo folderInfo){
        this.model = model;
        this.folderInfo = folderInfo;
        playerController = model.getPlayerController();
    }

    public void musicClick(int position){
        playerController.musicSelect(position,folderInfo);
    }


}
