package com.android.music.Util.Main;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.music.Model.Model;
import com.android.music.Model.PlayerController;
import com.android.music.R;
import com.android.music.SQLite.SQLiteDBHelper;
import com.android.music.Util.Home.HomeFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.pre)
    ImageButton pre;
    @BindView(R.id.play)
    ImageButton play;
    @BindView(R.id.next)
    ImageButton next;
    @BindView(R.id.order)
    ImageButton order;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;

    @Inject
    Model model;
    PlayerController playerController;
    HomeFragment homeFragment;
    SQLiteDBHelper dbHelper;
    SQLiteDatabase musicListDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        name.setSelected(true);

        //權限
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        while (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                break;
            } else {
                break;
            }
        }

        model = new Model();
        playerController = new PlayerController(model);

        dbHelper = new SQLiteDBHelper(this);
        musicListDB = dbHelper.getWritableDatabase();

        SharedPreferences userStatus = getSharedPreferences("startSong", 0);
        SharedPreferences.Editor userStatusEditor = userStatus.edit();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        model.setFragmentLayout(linearLayout1);
        model.setMainActivity(this);
        model.setFragmentManager(fragmentManager);
        model.setNameText(name);
        model.setPreButton(pre);
        model.setPlayButton(play);
        model.setNextButton(next);
        model.setOrderButton(order);
        model.setPlayerController(playerController);
        model.setUserStatusEditor(userStatusEditor);
        model.setMusicListDB(musicListDB);

        homeFragment = new HomeFragment(model);
        transaction.add(R.id.linearLayout1, homeFragment);
        transaction.commit();


    }

    @OnClick({R.id.pre, R.id.play, R.id.next, R.id.order})
    public void controllerBtn(View view) {
        switch (view.getId()) {
            case R.id.pre:
                playerController.musicPre();
                break;
            case R.id.play:
                playerController.musicStartOrStop();
                break;
            case R.id.next:
                playerController.musicNext();
                break;
            case R.id.order:
                playerController.musicOrder();
                break;
        }
    }
}