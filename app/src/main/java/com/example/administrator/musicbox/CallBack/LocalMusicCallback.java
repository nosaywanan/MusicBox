package com.example.administrator.musicbox.CallBack;

import com.example.administrator.musicbox.Entry.Music;

import java.util.List;

/**
 * Created by Administrator on 2016-09-24.
 */

public interface LocalMusicCallback {
    void getLocalMusicSuccess(List<Music> musics);
    void getLocalMusicFailed(String  error);
}
