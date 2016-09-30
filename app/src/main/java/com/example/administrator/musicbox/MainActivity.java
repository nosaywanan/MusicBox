package com.example.administrator.musicbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.musicbox.Adapter.MyRevAdapter;
import com.example.administrator.musicbox.Entry.Music;
import com.example.administrator.musicbox.Presenter.LocalMusicPresenter;
import com.example.administrator.musicbox.Service.PlayLocalMusicService;
import com.example.administrator.musicbox.View.ILocalMusicView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ILocalMusicView, MyRevAdapter.OnRevItemClickListener {

    @Bind(R.id.music_list)
    RecyclerView mMusicLisView;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    LocalMusicPresenter mLocalMusicPresenter;
    @Bind(R.id.ctr_head_img)
    ImageView ctrHeadImg;
//    @Bind(R.id.music_player)
//    MusicPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // initView();
        getMusic();
    }

    private void getMusic() {
        mLocalMusicPresenter = new LocalMusicPresenter(this);
        mLocalMusicPresenter.getLocalMp3s(this);
    }

    private MyRevAdapter mMusicAdapter;
    private List<Music> mMusicList;

    private void initView() {
        //mMusicList=new ArrayList<Music>();
        mMusicAdapter = new MyRevAdapter(this, mMusicList);
        mMusicAdapter.setOnRevItemClickListener(this);
        mMusicLisView.setLayoutManager(new LinearLayoutManager(this));
        mMusicLisView.setAdapter(mMusicAdapter);
    }

    public void hidePorgressbar() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void getMusicSuccess(List<Music> musics) {
        hidePorgressbar();
        mMusicList = musics;
        initView();
    }

    @Override
    public void getMusicFailed(String error) {
        hidePorgressbar();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view, int position) {
        Log.e("播放音频url:", mMusicList.get(position).getUrl());
        Intent intent = new Intent(MainActivity.this, PlayLocalMusicService.class);
        intent.setAction("com.example.administrator.musicbox.Service.PlayLocalMusicService");
        intent.putExtra("url", mMusicList.get(position).getUrl());
        intent.putExtra("cmd", 0);
        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(this,ctrHeadImg,"headimg");
        startActivity(new Intent(MainActivity.this, MusicPlayActivity.class),optionsCompat.toBundle());
        //       startService(intent);
        //musicPlayer.setMusicPath(mMusicList.get(position).getUrl());
    }
}
