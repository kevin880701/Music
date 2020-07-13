package com.android.music.Model;


import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.android.music.Util.FolderContain.FolderContainFragment;
import com.android.music.Util.Home.HomeFragment;
import com.android.music.Util.Main.MainActivity;

public class Model {
    public MainActivity mainActivity;
    public FragmentManager fragmentManager;
    public LinearLayout FragmentLayout;
    public TextView nameText;
    public ImageButton preButton;
    public ImageButton playButton;
    public ImageButton nextButton;
    public ImageButton orderButton;
    public PlayerController playerController;
    public FolderInfo folderInfo;
    public FolderContainFragment folderContainFragment;
    public HomeFragment homeFragment;
    public SharedPreferences.Editor userStatusEditor;
    public SQLiteDatabase musicListDB;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public LinearLayout getFragmentLayout() {
        return FragmentLayout;
    }

    public void setFragmentLayout(LinearLayout fragmentLayout) {
        FragmentLayout = fragmentLayout;
    }

    public TextView getNameText() {
        return nameText;
    }

    public void setNameText(TextView nameText) {
        this.nameText = nameText;
    }

    public ImageButton getPreButton() {
        return preButton;
    }

    public void setPreButton(ImageButton preButton) {
        this.preButton = preButton;
    }

    public ImageButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(ImageButton playButton) {
        this.playButton = playButton;
    }

    public ImageButton getNextButton() {
        return nextButton;
    }

    public void setNextButton(ImageButton nextButton) {
        this.nextButton = nextButton;
    }

    public ImageButton getOrderButton() {
        return orderButton;
    }

    public void setOrderButton(ImageButton orderButton) {
        this.orderButton = orderButton;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public FolderInfo getFolderInfo() {
        return folderInfo;
    }

    public void setFolderInfo(FolderInfo folderInfo) {
        this.folderInfo = folderInfo;
    }

    public FolderContainFragment getFolderContainFragment() {
        return folderContainFragment;
    }

    public void setFolderContainFragment(FolderContainFragment folderContainFragment) {
        this.folderContainFragment = folderContainFragment;
    }

    public HomeFragment getHomeFragment() {
        return homeFragment;
    }

    public void setHomeFragment(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    public SharedPreferences.Editor getUserStatusEditor() {
        return userStatusEditor;
    }

    public void setUserStatusEditor(SharedPreferences.Editor userStatusEditor) {
        this.userStatusEditor = userStatusEditor;
    }

    public SQLiteDatabase getMusicListDB() {
        return musicListDB;
    }

    public void setMusicListDB(SQLiteDatabase musicListDB) {
        this.musicListDB = musicListDB;
    }
}
