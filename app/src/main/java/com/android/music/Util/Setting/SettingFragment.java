package com.android.music.Util.Setting;

import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.android.music.Model.Model;
import com.android.music.R;
import com.android.music.SQLite.FeedReaderContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingFragment extends Fragment {

    @BindView(R.id.btn1)
    Button btn1;

    Model model;
    SettingPresenter settingPresenter;

    public SettingFragment(Model model){
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, rootView);

        settingPresenter = new SettingPresenter(model,this);
        return rootView;
    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        settingPresenter.dropDB();
        settingPresenter.createDB();
    }
}