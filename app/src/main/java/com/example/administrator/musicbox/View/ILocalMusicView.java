package com.example.administrator.musicbox.View;

import com.example.administrator.musicbox.Entry.Music;

import java.util.List;

/**
 * Created by Administrator on 2016-09-24.
 */

public interface ILocalMusicView {
    void getMusicSuccess(List<Music> musics);
    void getMusicFailed(String error);
}
