package cn.nubia.chatassistant.customchat;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import cn.nubia.chatassistant.adapter.LocalMusicAdapter;
import cn.nubia.chatassistant.bean.Song;
import cn.nubia.chatassistant.util.ExternalFilesUtils;
import cn.nubia.chatassistant.util.LocalMusicUtils;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.MusicManagerUtils;
import cn.nubia.gamelauncher.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class LocalMusicActivity extends Activity implements LocalMusicAdapter.OnSelectedListener {
    private static final String TAG = "LocalMusicActivity";
    private List<Song> list = new ArrayList();
    private LocalMusicAdapter localMusicAdapter;
    private RecyclerView recyclerView;

    private void getExternalMusic() {
        AsyncTask.execute(new Runnable() { // from class: cn.nubia.chatassistant.customchat.LocalMusicActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LocalMusicActivity.this.list.addAll(LocalMusicUtils.getMusic(LocalMusicActivity.this));
                LocalMusicActivity.this.list.addAll(ExternalFilesUtils.getExternalFilesMusic(LocalMusicActivity.this));
                Collections.sort(LocalMusicActivity.this.list, new Comparator<Song>() { // from class: cn.nubia.chatassistant.customchat.LocalMusicActivity.1.1
                    @Override // java.util.Comparator
                    public int compare(Song song, Song song2) {
                        return (int) (Long.parseLong(song2.getTime()) - Long.parseLong(song.getTime()));
                    }
                });
                LocalMusicActivity.this.runOnUiThread(new Runnable() { // from class: cn.nubia.chatassistant.customchat.LocalMusicActivity.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalMusicActivity.this.localMusicAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initData() {
        LocalMusicAdapter localMusicAdapter = new LocalMusicAdapter(this.list, this);
        this.localMusicAdapter = localMusicAdapter;
        localMusicAdapter.setOnSelectedListener(this);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) this.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.recyclerView.setAdapter(this.localMusicAdapter);
        getExternalMusic();
    }

    private void initView() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void setShortEdges() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.i(TAG, "onCreate : ");
        setShortEdges();
        requestWindowFeature(1);
        getWindow().addFlags(263968);
        setContentView(R.layout.activity_local_music);
        initView();
        initData();
    }

    @Override // cn.nubia.chatassistant.adapter.LocalMusicAdapter.OnSelectedListener
    public void onItemSelected(Song song) {
        Intent intent = new Intent();
        intent.putExtra("song", song);
        setResult(-1, intent);
        finish();
    }

    public void onPageBack(View view) {
        finish();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        MusicManagerUtils.getInstance().stopPlay();
    }
}
