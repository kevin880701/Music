package com.android.music.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.music.Model.MusicInfo;
import com.android.music.R;
import com.android.music.Util.FolderContain.FolderContainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    ArrayList<MusicInfo> musicInfoList;
    FolderContainPresenter folderContainPresenter;

    public MusicListAdapter(ArrayList musicInfoList, FolderContainPresenter folderContainPresenter) {
        this.musicInfoList = musicInfoList;
        this.folderContainPresenter = folderContainPresenter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.no)
        TextView no;
        @BindView(R.id.musicName)
        TextView musicName;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    folderContainPresenter.musicClick(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.no.setText(Integer.toString(position+1));
        holder.musicName.setText(musicInfoList.get(position).getMusicName());

    }

    @Override
    public int getItemCount() {
        return musicInfoList.size();
    }
}
