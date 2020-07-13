package com.android.music.Util.FolderContain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.music.Adapter.MusicListAdapter;
import com.android.music.Model.FolderInfo;
import com.android.music.Model.Model;
import com.android.music.R;
import com.android.music.Util.Home.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FolderContainFragment extends Fragment {

    @BindView(R.id.folderName)
    TextView folderName;
    @BindView(R.id.list)
    RecyclerView list;

    FolderInfo folderInfo;
    MusicListAdapter musicListAdapter;
    FolderContainPresenter folderContainPresenter;
    Model model;

    public FolderContainFragment(FolderInfo folderInfo, Model model){
        this.folderInfo = folderInfo;
        this.model = model;
//        this.model.setFolderInfo(folderInfo);
        this.model.setFolderContainFragment(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_folder_contain, container, false);
        ButterKnife.bind(this, rootView);

        folderContainPresenter = new FolderContainPresenter(model,folderInfo);
        folderName.setText(folderInfo.getFolderName());

        list.setLayoutManager(new LinearLayoutManager(this.getActivity().getApplicationContext()));
        list.addItemDecoration(new DividerItemDecoration(this.getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));

        musicListAdapter = new MusicListAdapter(folderInfo.getMusicInfo(),folderContainPresenter);
        list.setAdapter(musicListAdapter);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                FragmentTransaction fragmentTransaction;
                fragmentTransaction=model.getFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment(model);
                fragmentTransaction.replace(model.getFragmentLayout().getId(),homeFragment).commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(
                this, callback);

        return rootView;
    }
}