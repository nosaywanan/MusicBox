package com.example.administrator.musicbox.Widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.musicbox.R;
import com.example.administrator.musicbox.TimeUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2016-09-24.
 */

public class MusicPlayer extends RelativeLayout implements MediaPlayer.OnPreparedListener,SeekBar.OnSeekBarChangeListener {
    private MediaPlayer mMediaplayer;

    public MusicPlayer(Context context) {
        super(context);
        init();
    }
    public MusicPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        mMediaplayer = new MediaPlayer();
        initMediaPlayer();
    }


    private ImageView mPlayBtn;
    private SeekBar mSeekBar;
    private TextView mStartTimeTv;
    private TextView mEndTimeTv;
    private void initMediaPlayer() {
        View mediaView= LayoutInflater.from(getContext()).inflate(R.layout.media_controler,null);
        addView(mediaView,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        mPlayBtn= (ImageView) mediaView.findViewById(R.id.controller_play);
        mSeekBar= (SeekBar) mediaView.findViewById(R.id.controller_seekBar);
        mStartTimeTv= (TextView) mediaView.findViewById(R.id.controller_start_time);
        mEndTimeTv= (TextView) mediaView.findViewById(R.id.controller_end_time);
        mMediaplayer.setOnPreparedListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    public void setMusicPath(String path)throws IOException {
        mMediaplayer.setDataSource(path);
    }
    public  boolean isMusicPlaying(){
        return mMediaplayer.isPlaying();
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        mStartTimeTv.setText("00:00");
        mEndTimeTv.setText(TimeUtils.getFormatTime(mp.getDuration()));
        mSeekBar.setMax(mp.getDuration());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mStartTimeTv.setText(TimeUtils.getFormatTime(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    public boolean isPlaying(){
        return mMediaplayer.isPlaying();
    }
    public  void  prepare() throws IOException {
        mMediaplayer.prepare();
    }
    public void start(){
        mMediaplayer.start();
    }
    public void pause(){
        mMediaplayer.pause();
    }
    public void stop(){
        mMediaplayer.stop();
    }
    public void release(){
        mMediaplayer.release();
    }
    class MusicThread extends Thread{
        @Override
        public void run() {
            while (mMediaplayer.isPlaying()){
                Message message=new Message();
                message.what=0;
                message.arg1= mMediaplayer.getCurrentPosition();
                mHandler.sendMessage(message);
            }
        }
    }
    Handler mHandler=new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case 0:
                    mSeekBar.setProgress(msg.arg1);
                    break;
            }
        }
    };
}
