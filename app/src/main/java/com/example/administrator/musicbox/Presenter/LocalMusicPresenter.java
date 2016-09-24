package com.example.administrator.musicbox.Presenter;

import android.content.Context;

import com.example.administrator.musicbox.CallBack.LocalMusicCallback;
import com.example.administrator.musicbox.Entry.Music;
import com.example.administrator.musicbox.Model.MusicModel;
import com.example.administrator.musicbox.View.ILocalMusicView;

import java.util.List;

/**
 * Created by Administrator on 2016-09-24.
 */

public class LocalMusicPresenter  implements LocalMusicCallback{
    ILocalMusicView iLocalMusicView;
    MusicModel mMusicModel;
    public LocalMusicPresenter(ILocalMusicView view){
        mMusicModel=new MusicModel(this);
        iLocalMusicView=view;
    }
   public void getLocalMp3s(Context ctx){
       mMusicModel.getLoaclMp3s(ctx);
    }
    @Override
    public void getLocalMusicSuccess(List<Music> musics) {
        iLocalMusicView.getMusicSuccess(musics);
    }

    @Override
    public void getLocalMusicFailed(String error) {
        iLocalMusicView.getMusicFailed(error);
    }
}
