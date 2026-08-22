package cn.nubia.gamelauncher.gamecontrolpanel.virtual;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.media3.common.C;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameFunctionAllocationView;
import cn.nubia.gamelauncher.util.LogUtil;
import java.io.IOException;

/* loaded from: classes.dex */
public class HandleHelpView extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "HandleHelpView";
    private ImageView mBtnExit;
    private LinearLayout mBtnLayout;
    private ImageView mBtnPlayAndPause;
    private int mCode;
    private Context mContext;
    private TextView mCurrentTime;
    private ImageView mFirstFrame;
    Handler mHandler;
    private int mScreenHeight;
    private int mScreenWidth;
    private SeekBar mSeekBar;
    private GameFunctionAllocationView mSettingTypeFragment;
    private TextView mTotalTime;
    Runnable mUpdateProgress;
    Runnable mUpdateVisibility;
    private MyVideoView mVideoView;

    public HandleHelpView(Context context) {
        super(context);
        this.mCode = 0;
        this.mHandler = new Handler();
        this.mUpdateProgress = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.1
            @Override // java.lang.Runnable
            public void run() {
                HandleHelpView.this.mSeekBar.setProgress((HandleHelpView.this.mVideoView.getCurrentPosition() / 1000) + 1);
                HandleHelpView.this.mHandler.postDelayed(this, 1000L);
            }
        };
        this.mUpdateVisibility = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.2
            @Override // java.lang.Runnable
            public void run() {
                HandleHelpView.this.mBtnLayout.setVisibility(4);
                HandleHelpView.this.mBtnExit.setVisibility(4);
            }
        };
        this.mContext = context;
        getScreenParams();
    }

    public HandleHelpView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCode = 0;
        this.mHandler = new Handler();
        this.mUpdateProgress = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.1
            @Override // java.lang.Runnable
            public void run() {
                HandleHelpView.this.mSeekBar.setProgress((HandleHelpView.this.mVideoView.getCurrentPosition() / 1000) + 1);
                HandleHelpView.this.mHandler.postDelayed(this, 1000L);
            }
        };
        this.mUpdateVisibility = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.2
            @Override // java.lang.Runnable
            public void run() {
                HandleHelpView.this.mBtnLayout.setVisibility(4);
                HandleHelpView.this.mBtnExit.setVisibility(4);
            }
        };
        this.mContext = context;
        getScreenParams();
    }

    public HandleHelpView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCode = 0;
        this.mHandler = new Handler();
        this.mUpdateProgress = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.1
            @Override // java.lang.Runnable
            public void run() {
                HandleHelpView.this.mSeekBar.setProgress((HandleHelpView.this.mVideoView.getCurrentPosition() / 1000) + 1);
                HandleHelpView.this.mHandler.postDelayed(this, 1000L);
            }
        };
        this.mUpdateVisibility = new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.2
            @Override // java.lang.Runnable
            public void run() {
                HandleHelpView.this.mBtnLayout.setVisibility(4);
                HandleHelpView.this.mBtnExit.setVisibility(4);
            }
        };
        this.mContext = context;
        getScreenParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentTime(int i) {
        int i2 = (i / 1000) % 60;
        int i3 = (i / 60000) % 60;
        return (i3 <= 9 || i2 <= 9) ? i3 > 9 ? i3 + ":0" + i2 : i2 > 9 ? "0" + i3 + ":" + i2 : "0" + i3 + ":0" + i2 : i3 + ":" + i2;
    }

    private void getScreenParams() {
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        this.mScreenWidth = point.x;
        this.mScreenHeight = point.y;
    }

    private String getTotalTime(String str) {
        long parseLong = Long.parseLong(str) / 1000;
        long j = parseLong % 60;
        long j2 = (parseLong / 60) % 60;
        return (j2 <= 9 || j <= 9) ? j2 > 9 ? j2 + ":0" + j : j > 9 ? "0" + j2 + ":" + j : "0" + j2 + ":0" + j : j2 + ":" + j;
    }

    private void initVideo() {
        String str = "android.resource://" + this.mContext.getPackageName() + "/2131820552";
        this.mVideoView.setVideoPath(str);
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.5
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false);
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.5.1
                    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                    public void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
                        HandleHelpView.this.mVideoView.getHolder().setFixedSize(HandleHelpView.this.mScreenWidth, HandleHelpView.this.mScreenHeight);
                        HandleHelpView.this.mVideoView.setMeasure(HandleHelpView.this.mScreenWidth, HandleHelpView.this.mScreenHeight);
                        HandleHelpView.this.mVideoView.requestLayout();
                    }
                });
            }
        });
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(this.mContext, Uri.parse(str));
                String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
                this.mTotalTime.setText(getTotalTime(extractMetadata));
                this.mSeekBar.setMax(Integer.parseInt(extractMetadata) / 1000);
                this.mSeekBar.setMin(0);
                this.mCurrentTime.setText("00:00");
                this.mFirstFrame.setImageBitmap(mediaMetadataRetriever.getFrameAtTime(1L));
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e2) {
                LogUtil.e(TAG, "init video : " + e2);
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException e3) {
                    throw new RuntimeException(e3);
                }
            }
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
                throw th;
            } catch (IOException e4) {
                throw new RuntimeException(e4);
            }
        }
    }

    public void exitView() {
        if (this.mCode == 1) {
            this.mSettingTypeFragment.dismissView();
            this.mCode = 0;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.nubia_handle_help_exit) {
            exitView();
            release();
            return;
        }
        if (id != R.id.nubia_handle_help_play_pause) {
            return;
        }
        if (this.mFirstFrame.getVisibility() == 0) {
            this.mFirstFrame.setVisibility(8);
        }
        if (!this.mVideoView.isPlaying()) {
            this.mVideoView.start();
            setSystemUiVisibility(4);
            this.mBtnPlayAndPause.setBackground(getResources().getDrawable(R.drawable.nubia_handle_help_btn_pause));
            this.mHandler.postDelayed(this.mUpdateProgress, 1000L);
            this.mHandler.postDelayed(this.mUpdateVisibility, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
            return;
        }
        if (this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
            this.mBtnPlayAndPause.setBackground(getResources().getDrawable(R.drawable.nubia_handle_help_btn_play));
            this.mHandler.removeCallbacks(this.mUpdateProgress);
            this.mHandler.removeCallbacks(this.mUpdateVisibility);
            this.mBtnLayout.setVisibility(0);
            this.mBtnExit.setVisibility(0);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBtnLayout = (LinearLayout) findViewById(R.id.nubia_handle_help_btn_layout);
        this.mBtnExit = (ImageView) findViewById(R.id.nubia_handle_help_exit);
        this.mBtnPlayAndPause = (ImageView) findViewById(R.id.nubia_handle_help_play_pause);
        this.mVideoView = (MyVideoView) findViewById(R.id.nubia_handle_help_video_view);
        this.mFirstFrame = (ImageView) findViewById(R.id.nubia_handle_help_first_frame);
        this.mCurrentTime = (TextView) findViewById(R.id.nubia_handle_help_current_time);
        this.mTotalTime = (TextView) findViewById(R.id.nubia_handle_help_total_time);
        this.mSeekBar = (SeekBar) findViewById(R.id.nubia_handle_help_seekbar);
        this.mVideoView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HandleHelpView.this.mHandler.removeCallbacks(HandleHelpView.this.mUpdateVisibility);
                if (HandleHelpView.this.mBtnLayout.getVisibility() == 4) {
                    HandleHelpView.this.mBtnLayout.setVisibility(0);
                    HandleHelpView.this.mBtnExit.setVisibility(0);
                    HandleHelpView.this.mHandler.postDelayed(HandleHelpView.this.mUpdateVisibility, 5000L);
                } else if (HandleHelpView.this.mBtnLayout.getVisibility() == 0) {
                    HandleHelpView.this.mBtnLayout.setVisibility(4);
                    HandleHelpView.this.mBtnExit.setVisibility(4);
                }
            }
        });
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.virtual.HandleHelpView.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                HandleHelpView.this.mCurrentTime.setText(HandleHelpView.this.getCurrentTime(i * 1000));
                if (seekBar.getMax() == i) {
                    HandleHelpView.this.mHandler.removeCallbacks(HandleHelpView.this.mUpdateVisibility);
                    HandleHelpView.this.mBtnLayout.setVisibility(0);
                    HandleHelpView.this.mBtnExit.setVisibility(0);
                    HandleHelpView.this.mBtnPlayAndPause.setBackground(HandleHelpView.this.getResources().getDrawable(R.drawable.nubia_handle_help_btn_play));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                HandleHelpView.this.mHandler.removeCallbacks(HandleHelpView.this.mUpdateVisibility);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                HandleHelpView.this.mVideoView.seekTo(seekBar.getProgress() * 1000);
                HandleHelpView.this.mHandler.postDelayed(HandleHelpView.this.mUpdateVisibility, 5000L);
            }
        });
        this.mBtnExit.setOnClickListener(this);
        this.mBtnPlayAndPause.setOnClickListener(this);
        initVideo();
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || !keyEvent.isTracking() || keyEvent.isCanceled()) {
            return super.onKeyUp(i, keyEvent);
        }
        exitView();
        release();
        return true;
    }

    public void release() {
        this.mHandler.removeCallbacks(this.mUpdateProgress);
        this.mHandler.removeCallbacks(this.mUpdateVisibility);
        MyVideoView myVideoView = this.mVideoView;
        if (myVideoView != null) {
            myVideoView.suspend();
            this.mVideoView = null;
        }
    }

    public void setViewCtrl(GameFunctionAllocationView gameFunctionAllocationView, int i) {
        this.mSettingTypeFragment = gameFunctionAllocationView;
        this.mCode = i;
    }
}
