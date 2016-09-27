package com.example.administrator.musicbox.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.administrator.musicbox.Widget.MusicPlayer;

/**
 * Created by Administrator on 2016-09-24.
 */

public class PlayLocalMusicService extends Service implements MediaPlayer.OnPreparedListener {
    MusicPlayer mMediaplay;
    String musicPath = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mMediaplay.isPlaying()) {
            stop();
        }
        musicPath = intent.getStringExtra("url");
        int cmd = intent.getIntExtra("cmd", 0);
        switch (cmd) {
            case 0:
                play(0);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (mMediaplay != null) {
            mMediaplay.stop();
            mMediaplay.release();
        }
    }

    private void play(int position) {
        try {
          //  mMediaplay.reset();
            mMediaplay.setMusicPath(musicPath);
            mMediaplay.prepare();
          //  mMediaplay.setOnPreparedListener(this);
        } catch (Exception e) {

        }
    }

    private void pause() {
        if (mMediaplay != null && mMediaplay.isPlaying()) {
            mMediaplay.pause();

        }
    }

    private void stop() {
        if (mMediaplay != null) {
            mMediaplay.stop();
            //mMediaplay.prepare();
        }
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
