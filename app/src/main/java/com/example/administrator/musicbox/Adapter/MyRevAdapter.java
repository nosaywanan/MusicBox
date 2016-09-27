package com.example.administrator.musicbox.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.musicbox.Entry.Music;
import com.example.administrator.musicbox.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2016-09-24.
 */

public class MyRevAdapter extends RecyclerView.Adapter<MyRevAdapter.MusicViewHolder>  {
    private LayoutInflater mInflater;
    private List<Music> musicList;
    public MyRevAdapter(Context context,List<Music> data){
        mInflater=LayoutInflater.from(context);
        musicList=data;
    }
    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int vie) {
        View view=mInflater.inflate(R.layout.music_item_v2,parent,false);
        return new MusicViewHolder(view,onRevItemClickListener);
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, int position) {
        holder.title.setText(musicList.get(position).getName());
        SimpleDateFormat formatter=new SimpleDateFormat("mm:ss");

        holder.duration.setText(formatter.format(musicList.get(position).getTime()));
        holder.artist.setText(musicList.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }
    OnRevItemClickListener onRevItemClickListener=null;
    public void setOnRevItemClickListener(OnRevItemClickListener onRevItemClickListener) {
        this.onRevItemClickListener = onRevItemClickListener;
    }

    public interface OnRevItemClickListener{
        void onClick(View view,int position);
    }
    class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView play,more;
        TextView title,artist,duration;
        OnRevItemClickListener onListener;
        public MusicViewHolder(View itemView,OnRevItemClickListener ls) {
            super(itemView);
            onListener=ls;
            title= (TextView) itemView.findViewById(R.id.item_title);
            artist= (TextView) itemView.findViewById(R.id.item_artist);
            duration= (TextView) itemView.findViewById(R.id.item_duration);
            play= (ImageView) itemView.findViewById(R.id.item_play);
            more= (ImageView) itemView.findViewById(R.id.item_more);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (onListener!=null)
                onListener.onClick(v,getLayoutPosition());
        }
    }

}
