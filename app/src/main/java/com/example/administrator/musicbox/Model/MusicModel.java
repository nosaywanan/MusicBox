package com.example.administrator.musicbox.Model;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.administrator.musicbox.CallBack.LocalMusicCallback;
import com.example.administrator.musicbox.Entry.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-09-24.
 */

public class MusicModel implements IMusicModel{
    LocalMusicCallback musicCallback;
    public MusicModel(LocalMusicCallback callback){
        musicCallback=callback;
    }
    @Override
    public void getLoaclMp3s(Context ctx) {
        List<Music> musicList=new ArrayList<Music>();
        Cursor cursor = ctx.
                getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        for (int i=0;i<cursor.getCount();i++){
            if (cursor.moveToNext()){
                Music music=new Music();
                music.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                music.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                int duration=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                music.setTime(duration);
                music.setUrl(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                int isMusic=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                if (isMusic==0&&duration/1000>60){
                    musicList.add(music);
                }
            }
        }
        if (musicList.size()>0)
            musicCallback.getLocalMusicSuccess(musicList);
        else
            musicCallback.getLocalMusicFailed("暂无本地音乐");
    }
}
