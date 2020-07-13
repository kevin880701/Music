package com.android.music.Model;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.android.music.R;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerController {

    Model model;
    MediaPlayer mediaPlayer = new MediaPlayer();
    MusicInfo musicInfo;
    ArrayList<Integer> ranList = new ArrayList<Integer>();
    int position;
    int ranPosition = 0;

    public PlayerController(Model model){
        this.model = model;
        mediaPlayer.setOnCompletionListener(new InnerOnCompletionListener());

    }

    public void musicSelect(int position,FolderInfo folderInfo){
        this.position = position;
        this.model.setFolderInfo(folderInfo);
        musicPlay();
    }

    public void musicStartOrStop(){
        try {
            if(mediaPlayer.isPlaying()){
                Log.v("777","STOP");
                mediaPlayer.pause();
                model.getPlayButton().setImageDrawable(model.getMainActivity().getResources().getDrawable(R.drawable.play));
            } else {
                Log.v("777","START");
                mediaPlayer.start();
                model.getPlayButton().setImageDrawable(model.getMainActivity().getResources().getDrawable(R.drawable.stop));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void musicPre(){
        if(model.getOrderButton().getDrawable().getCurrent().getConstantState()
                .equals(model.getMainActivity().getResources().getDrawable(R.drawable.ran).getConstantState())){
            ranPosition--;
            if (ranPosition < 0){
                ranPosition = ranList.size()-1;
            }
            position =  ranList.get(ranPosition);
            musicPlay();
        }else{
            position--;
            if (position<0){
                position = model.getFolderInfo().getMusicInfo().size()-1;
            }
            musicPlay();
        }
    }

    public void musicNext(){
        if(model.getOrderButton().getDrawable().getCurrent().getConstantState()
                .equals(model.getMainActivity().getResources().getDrawable(R.drawable.ran).getConstantState())){
            ranPosition++;
            if (ranPosition == ranList.size()){
                ranPosition = 0;
            }
            position = ranList.get(ranPosition);
            musicPlay();
        }else{
            position++;
            if (position == model.getFolderInfo().getMusicInfo().size()-1){
                position = 0;
            }
            musicPlay();
        }
    }

    //自動換首
    private final class InnerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if(model.getFolderInfo() == null){
                Log.v("7777","7777777777777777");

            }
            musicNext();
        }
    }

    public void musicOrder(){
        if (model.getOrderButton().getDrawable().getCurrent().getConstantState()
                .equals(model.getMainActivity().getResources().getDrawable(R.drawable.nor).getConstantState())){
            model.getOrderButton().setImageResource(R.drawable.one);
        }
        else if(model.getOrderButton().getDrawable().getCurrent().getConstantState()
                .equals(model.getMainActivity().getResources().getDrawable(R.drawable.one).getConstantState())){
            model.getOrderButton().setImageResource(R.drawable.ran);
            setRanList();
        }
        else if(model.getOrderButton().getDrawable().getCurrent().getConstantState()
                .equals(model.getMainActivity().getResources().getDrawable(R.drawable.ran).getConstantState())){
            model.getOrderButton().setImageResource(R.drawable.nor);
            ranPosition = 0;
        }
    }

    public void setRanList(){
        ranList = new ArrayList<Integer>();
        for (int i=0; i < 5000;i++){
            if(i==0){
                ranList.add(position);
            }else {
                ranList.add((int)(Math.random()* model.getFolderInfo().getMusicInfo().size()));
            }
        }
    }

    public void musicPlay(){
        mediaPlayer.reset();
        this.musicInfo = model.getFolderInfo().getMusicInfo().get(position);
        model.getNameText().setText(musicInfo.getMusicName());
        try {
            mediaPlayer.setDataSource(model.getMainActivity(), Uri.parse(musicInfo.getMusicUri()));
            mediaPlayer.prepare();
            mediaPlayer.start();
            model.getUserStatusEditor().putString("uri",musicInfo.getMusicUri());
            model.getPlayButton().setImageDrawable(model.getMainActivity().getResources().getDrawable(R.drawable.stop));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rememberStatus(){
        model.getUserStatusEditor().putString("startSong", musicInfo.getMusicUri());
        model.getUserStatusEditor().putString("ord", "");
    }

    public void noSelect(){
    }
}
