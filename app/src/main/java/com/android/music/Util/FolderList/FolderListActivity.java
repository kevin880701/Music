package com.android.music.Util.FolderList;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.music.Adapter.FolderListAdapter;
import com.android.music.Model.FolderInfo;
import com.android.music.Model.Model;
import com.android.music.R;
import com.android.music.Util.Main.MainActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FolderListActivity extends Fragment {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.fra)
    FrameLayout fra;

    ArrayList<String> folderList;
    ArrayList<String> musicFolderPathList;

    FolderListAdapter folderListAdapter;
    ArrayList<FolderInfo> FolderInfoList;
    Model model;

    public FolderListActivity(ArrayList<FolderInfo> FolderInfoList,Model model){
        this.FolderInfoList = FolderInfoList;
        this.model = model;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_folder_list, container, false);
        ButterKnife.bind(this, rootView);

        folderList = new ArrayList();
        musicFolderPathList = new ArrayList();

        list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        list.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));

        folderListAdapter = new FolderListAdapter(FolderInfoList,model);
        list.setAdapter(folderListAdapter);

        return rootView;
    }
}