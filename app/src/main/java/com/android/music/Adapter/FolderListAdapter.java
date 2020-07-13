package com.android.music.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.music.Model.FolderInfo;
import com.android.music.Model.Model;
import com.android.music.R;
import com.android.music.Util.FolderContain.FolderContainFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.ViewHolder> {

    ArrayList<FolderInfo> FolderInfoList;
    Model model;

    public FolderListAdapter(ArrayList FolderInfoList,Model model)
    {
        this.FolderInfoList = FolderInfoList;
        this.model = model;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.folderName)
        TextView folderName;
        @BindView(R.id.folderPath)
        TextView folderPath;
        @BindView(R.id.fileCount)
        TextView fileCount;
        @BindView(R.id.image)
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction;
                    fragmentTransaction=model.getFragmentManager().beginTransaction();
                    FolderContainFragment folderContainFragment = new FolderContainFragment(FolderInfoList.get(getAdapterPosition()),model);
                    fragmentTransaction.replace(model.getFragmentLayout().getId(),folderContainFragment).commit();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_folder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.folderName.setText(FolderInfoList.get(position).getFolderName());

    }

    @Override
    public int getItemCount() {
        return FolderInfoList.size();
    }
}
