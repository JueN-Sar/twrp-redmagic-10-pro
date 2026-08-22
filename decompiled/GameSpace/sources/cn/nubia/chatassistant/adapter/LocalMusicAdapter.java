package cn.nubia.chatassistant.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.chatassistant.bean.Song;
import cn.nubia.chatassistant.ui.MusicLineView;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.MusicManagerUtils;
import cn.nubia.chatassistant.util.TimeUtils;
import cn.nubia.gamelauncher.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class LocalMusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "LocalMusicAdapter";
    private int clickPosition;
    private List<Song> list;
    private Context mContext;
    private OnSelectedListener onSelectedListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        private TextView duration;
        private TextView fileSize;
        private Timer mTimer;
        private TextView name;
        private ImageView playButton;
        private MusicLineView playProcess;
        private View rootLayout;
        private TextView time;

        public MyViewHolder(View view) {
            super(view);
            initView(view);
        }

        private void initView(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
            this.time = (TextView) view.findViewById(R.id.time);
            this.button = (Button) view.findViewById(R.id.addBtn);
            this.playButton = (ImageView) view.findViewById(R.id.play_btn);
            this.rootLayout = view.findViewById(R.id.local_music_item);
            this.fileSize = (TextView) view.findViewById(R.id.file_size);
            this.duration = (TextView) view.findViewById(R.id.duration);
            this.playProcess = (MusicLineView) view.findViewById(R.id.play_process_line);
        }

        private void noSelectLayout() {
            this.name.setAlpha(0.6f);
            this.time.setAlpha(0.3f);
            this.fileSize.setAlpha(0.3f);
            this.duration.setAlpha(0.3f);
            this.playButton.setAlpha(0.6f);
            this.button.setBackgroundResource(R.mipmap.chat_assistant_local_add_no_select);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void selectLayout() {
            this.name.setAlpha(0.85f);
            this.time.setAlpha(0.5f);
            this.fileSize.setAlpha(0.5f);
            this.duration.setAlpha(0.5f);
            this.playButton.setAlpha(1.0f);
            this.button.setBackgroundResource(R.mipmap.chat_assistant_local_add_select);
        }

        public void setData(final Song song, final int i) {
            String str;
            this.name.setText(song.getName());
            this.time.setText(TimeUtils.timestampToTime(song.getTime()));
            this.fileSize.setText((song.getSize() / 1000) + "KB");
            int duration = song.getDuration() / 1000;
            if (duration < 60) {
                str = "00:" + duration;
            } else {
                int i2 = duration / 60;
                str = i2 < 9 ? "0" + i2 + ":" + (duration % 60) : i2 + ":" + (duration % 60);
            }
            this.duration.setText(str);
            this.button.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.LocalMusicAdapter.MyViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (LocalMusicAdapter.this.onSelectedListener != null) {
                        LocalMusicAdapter.this.onSelectedListener.onItemSelected(song);
                    }
                }
            });
            if (i == LocalMusicAdapter.this.clickPosition) {
                if (MusicManagerUtils.getInstance().isPlay()) {
                    this.playProcess.reStart(MusicManagerUtils.getInstance().getCurrentPosition(), MusicManagerUtils.getInstance().getDuration());
                    Timer timer = this.mTimer;
                    if (timer != null) {
                        timer.cancel();
                    }
                    Timer timer2 = new Timer();
                    this.mTimer = timer2;
                    timer2.schedule(new TimerTask() { // from class: cn.nubia.chatassistant.adapter.LocalMusicAdapter.MyViewHolder.2
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            if (MusicManagerUtils.getInstance().isPlay()) {
                                MyViewHolder.this.playProcess.startupdateProcess();
                            } else {
                                MyViewHolder.this.mTimer.cancel();
                            }
                        }
                    }, 0L, 50L);
                    this.playButton.setBackgroundResource(R.drawable.chat_assistant_stop);
                } else {
                    LogUtils.d(LocalMusicAdapter.TAG, "!MusicManagerUtils.getInstance().isPlay()");
                    this.playProcess.setState(MusicManagerUtils.getInstance().getCurrentPosition(), MusicManagerUtils.getInstance().getDuration());
                    this.playButton.setBackgroundResource(R.drawable.chat_assistant_play);
                }
                selectLayout();
                this.playProcess.setVisibility(0);
            } else {
                noSelectLayout();
                this.playButton.setBackgroundResource(R.drawable.chat_assistant_play);
                this.playProcess.setVisibility(8);
                this.playProcess.reset();
            }
            this.rootLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.LocalMusicAdapter.MyViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (i != LocalMusicAdapter.this.clickPosition) {
                        MusicManagerUtils.getInstance().startPlay(song.getPath(), new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.chatassistant.adapter.LocalMusicAdapter.MyViewHolder.3.1
                            @Override // android.media.MediaPlayer.OnCompletionListener
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                LogUtils.d(LocalMusicAdapter.TAG, "position != clickPosition   + position : " + i + " ,clickPosition : " + LocalMusicAdapter.this.clickPosition);
                                MyViewHolder.this.playButton.setBackgroundResource(R.drawable.chat_assistant_play);
                                LocalMusicAdapter.this.notifyItemChanged(LocalMusicAdapter.this.clickPosition);
                            }
                        });
                        MyViewHolder.this.playButton.setBackgroundResource(R.drawable.chat_assistant_stop);
                        MyViewHolder.this.playProcess.setDuration(song.getDuration());
                        if (MyViewHolder.this.mTimer != null) {
                            MyViewHolder.this.mTimer.cancel();
                        }
                        MyViewHolder.this.mTimer = new Timer();
                        MyViewHolder.this.mTimer.schedule(new TimerTask() { // from class: cn.nubia.chatassistant.adapter.LocalMusicAdapter.MyViewHolder.3.2
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                if (MusicManagerUtils.getInstance().isPlay()) {
                                    MyViewHolder.this.playProcess.startupdateProcess();
                                } else {
                                    MyViewHolder.this.mTimer.cancel();
                                }
                            }
                        }, 0L, 50L);
                        MyViewHolder.this.selectLayout();
                        int i3 = LocalMusicAdapter.this.clickPosition;
                        LocalMusicAdapter.this.clickPosition = i;
                        if (i3 != -1) {
                            LocalMusicAdapter.this.notifyItemChanged(i3);
                        }
                    } else if (MusicManagerUtils.getInstance().isPlay()) {
                        MusicManagerUtils.getInstance().pause();
                        MyViewHolder.this.playButton.setBackgroundResource(R.drawable.chat_assistant_play);
                        MyViewHolder.this.mTimer.cancel();
                    } else {
                        if (MusicManagerUtils.getInstance().getCurrentPosition() <= 0 || MusicManagerUtils.getInstance().getCurrentPosition() >= MusicManagerUtils.getInstance().getDuration()) {
                            MusicManagerUtils.getInstance().startPlay(song.getPath(), new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.chatassistant.adapter.LocalMusicAdapter.MyViewHolder.3.3
                                @Override // android.media.MediaPlayer.OnCompletionListener
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    MyViewHolder.this.playButton.setBackgroundResource(R.drawable.chat_assistant_play);
                                    LocalMusicAdapter.this.notifyItemChanged(LocalMusicAdapter.this.clickPosition);
                                    LogUtils.d(LocalMusicAdapter.TAG, "position == clickPosition");
                                }
                            });
                            MyViewHolder.this.playProcess.setDuration(song.getDuration());
                        } else {
                            MusicManagerUtils.getInstance().reStartPlay();
                            MyViewHolder.this.playProcess.reStart(MusicManagerUtils.getInstance().getCurrentPosition(), MusicManagerUtils.getInstance().getDuration());
                        }
                        MyViewHolder.this.playButton.setBackgroundResource(R.drawable.chat_assistant_stop);
                        if (MyViewHolder.this.mTimer != null) {
                            MyViewHolder.this.mTimer.cancel();
                        }
                        MyViewHolder.this.mTimer = new Timer();
                        MyViewHolder.this.mTimer.schedule(new TimerTask() { // from class: cn.nubia.chatassistant.adapter.LocalMusicAdapter.MyViewHolder.3.4
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                if (MusicManagerUtils.getInstance().isPlay()) {
                                    MyViewHolder.this.playProcess.startupdateProcess();
                                } else {
                                    MyViewHolder.this.mTimer.cancel();
                                }
                            }
                        }, 0L, 50L);
                    }
                    MyViewHolder.this.playProcess.setVisibility(0);
                }
            });
        }
    }

    public interface OnSelectedListener {
        void onItemSelected(Song song);
    }

    public LocalMusicAdapter(List<Song> list, Context context) {
        new ArrayList();
        this.clickPosition = -1;
        this.list = list;
        this.mContext = context;
    }

    public void addAllData(List<Song> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            ((MyViewHolder) viewHolder).setData(this.list.get(i), i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recy_list, viewGroup, false));
    }

    public void refreshData(List<Song> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
