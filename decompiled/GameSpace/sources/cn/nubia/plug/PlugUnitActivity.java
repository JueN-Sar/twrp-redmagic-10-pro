package cn.nubia.plug;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.VideoView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.plug.fragment.PlugFragment;

/* loaded from: classes.dex */
public class PlugUnitActivity extends FragmentActivity implements View.OnClickListener, GameKeyObserver.Callback {
    private static final String TAG = "PlugUnitActivity";
    private PlugAdapter mBaseAdapter;
    private LinearLayout mCcontentContainer;
    private Fragment mCurrentFragment;
    private AnimatorSet mEntryAnimationSet;
    private int mEntryExitAnimatorOffset;
    private AnimatorSet mExitAnimationSet;
    private FrameLayout mFragmentContainer;
    private GridView mGridView;
    private ImageView mPlay;
    private ImageView mPreView;
    private VideoView mVideoView;

    private void calcDetailFragmentUI() {
        int calcDimensionWidth = PlugUtil.calcDimensionWidth(this);
        int calcDetailAreaWidth = PlugUtil.calcDetailAreaWidth(this);
        Log.i(TAG, "calcDetailFragmentUI dimensionWidth: " + calcDimensionWidth + ", detailWidth: " + calcDetailAreaWidth);
        PlugFragment.setIsDimensionNarrow(calcDetailAreaWidth > 0 && calcDimensionWidth > 0 && calcDimensionWidth > calcDetailAreaWidth);
    }

    private void clearData() {
        PlugAdapter plugAdapter = this.mBaseAdapter;
        if (plugAdapter != null) {
            plugAdapter.clearData();
        }
    }

    private Fragment getFragmentByTag(String str) {
        PlugAdapter plugAdapter = this.mBaseAdapter;
        return PlugFragment.newInstance(plugAdapter.getItem(plugAdapter.getSelectedPosition()));
    }

    private String getTagByPosition(int i) {
        return this.mBaseAdapter.getItem(i).getTag();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBackPressed() {
        super.onBackPressed();
    }

    private void initView() {
        this.mCcontentContainer = (LinearLayout) findViewById(R.id.content_container);
        this.mGridView = (GridView) findViewById(R.id.grid_view);
        this.mVideoView = (VideoView) findViewById(R.id.video_view);
        this.mPlay = (ImageView) findViewById(R.id.play);
        this.mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        this.mPreView = (ImageView) findViewById(R.id.pre_view);
    }

    private void plugEntryAnimation() {
        this.mEntryAnimationSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mFragmentContainer, (Property<FrameLayout, Float>) View.TRANSLATION_X, -this.mEntryExitAnimatorOffset, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mFragmentContainer, (Property<FrameLayout, Float>) View.ALPHA, 0.0f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mGridView, (Property<GridView, Float>) View.TRANSLATION_X, this.mEntryExitAnimatorOffset, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mGridView, (Property<GridView, Float>) View.ALPHA, 0.0f, 1.0f);
        PlugAdapter plugAdapter = this.mBaseAdapter;
        if (plugAdapter.getItem(plugAdapter.getSelectedPosition()).isVideoPre()) {
            this.mEntryAnimationSet.playTogether(ObjectAnimator.ofFloat(this.mVideoView, (Property<VideoView, Float>) View.TRANSLATION_X, -this.mEntryExitAnimatorOffset, 0.0f), ObjectAnimator.ofFloat(this.mVideoView, (Property<VideoView, Float>) View.ALPHA, 0.0f, 1.0f), ofFloat, ofFloat2, ofFloat3, ofFloat4);
        } else {
            this.mEntryAnimationSet.playTogether(ObjectAnimator.ofFloat(this.mPreView, (Property<ImageView, Float>) View.TRANSLATION_X, -this.mEntryExitAnimatorOffset, 0.0f), ObjectAnimator.ofFloat(this.mPreView, (Property<ImageView, Float>) View.ALPHA, 0.0f, 1.0f), ofFloat, ofFloat2, ofFloat3, ofFloat4);
        }
        this.mEntryAnimationSet.setDuration(300L);
        this.mEntryAnimationSet.setInterpolator(new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f));
        this.mEntryAnimationSet.start();
    }

    private void plugExitAnimation() {
        AnimatorSet animatorSet = this.mEntryAnimationSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mEntryAnimationSet.cancel();
        }
        if (this.mExitAnimationSet != null) {
            return;
        }
        this.mExitAnimationSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mFragmentContainer, (Property<FrameLayout, Float>) View.TRANSLATION_X, 0.0f, -this.mEntryExitAnimatorOffset);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mFragmentContainer, (Property<FrameLayout, Float>) View.ALPHA, 1.0f, 0.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mGridView, (Property<GridView, Float>) View.TRANSLATION_X, 0.0f, this.mEntryExitAnimatorOffset);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mGridView, (Property<GridView, Float>) View.ALPHA, 1.0f, 0.0f);
        PlugAdapter plugAdapter = this.mBaseAdapter;
        if (plugAdapter.getItem(plugAdapter.getSelectedPosition()).isVideoPre()) {
            this.mExitAnimationSet.playTogether(ObjectAnimator.ofFloat(this.mVideoView, (Property<VideoView, Float>) View.TRANSLATION_X, 0.0f, -this.mEntryExitAnimatorOffset), ObjectAnimator.ofFloat(this.mVideoView, (Property<VideoView, Float>) View.ALPHA, 1.0f, 0.0f), ofFloat, ofFloat2, ofFloat3, ofFloat4);
        } else {
            this.mExitAnimationSet.playTogether(ObjectAnimator.ofFloat(this.mPreView, (Property<ImageView, Float>) View.TRANSLATION_X, 0.0f, -this.mEntryExitAnimatorOffset), ObjectAnimator.ofFloat(this.mPreView, (Property<ImageView, Float>) View.ALPHA, 1.0f, 0.0f), ofFloat, ofFloat2, ofFloat3, ofFloat4);
        }
        this.mExitAnimationSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.plug.PlugUnitActivity.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                PlugUnitActivity.this.handleBackPressed();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                PlugUnitActivity.this.setUseAlphaVideoView();
                PlugUnitActivity.this.mPlay.setVisibility(4);
                if (Build.VERSION.SDK_INT <= 33) {
                    PlugUnitActivity.this.mCcontentContainer.setBackgroundColor(0);
                }
            }
        });
        this.mExitAnimationSet.setDuration(250L);
        this.mExitAnimationSet.setInterpolator(new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f));
        this.mExitAnimationSet.start();
    }

    private void setGridViewAdapter() {
        PlugAdapter plugAdapter = new PlugAdapter(this, R.layout.plug_gridview_item, PlugUtil.getPlugList(this));
        this.mBaseAdapter = plugAdapter;
        this.mGridView.setAdapter((ListAdapter) plugAdapter);
    }

    private void setGridViewItemClickListener() {
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cn.nubia.plug.PlugUnitActivity.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedPosition = PlugUnitActivity.this.mBaseAdapter.getSelectedPosition();
                if (i == selectedPosition) {
                    Log.d(PlugUnitActivity.TAG, "position not changed " + i);
                    return;
                }
                int firstVisiblePosition = PlugUnitActivity.this.mGridView.getFirstVisiblePosition();
                int i2 = i - firstVisiblePosition;
                if (i2 >= 0) {
                    PlugUnitActivity.this.mBaseAdapter.updateViewSelected(PlugUnitActivity.this.mGridView.getChildAt(i2), i);
                }
                int i3 = selectedPosition - firstVisiblePosition;
                if (i3 >= 0) {
                    PlugUnitActivity.this.mBaseAdapter.updateViewNormal(PlugUnitActivity.this.mGridView.getChildAt(i3), i, selectedPosition);
                }
                PlugUnitActivity.this.mBaseAdapter.notifyItemChanged(i);
                Log.d(PlugUnitActivity.TAG, "visiblePosition:" + firstVisiblePosition + " position:" + i);
                PlugUnitActivity plugUnitActivity = PlugUnitActivity.this;
                plugUnitActivity.setVideoViewURI(plugUnitActivity.mBaseAdapter.getItem(i));
                PlugUnitActivity.this.switchFragment(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUseAlphaVideoView() {
        try {
            Class.forName("android.view.SurfaceView").getMethod("setUseAlpha", new Class[0]).invoke(this.mVideoView, new Object[0]);
        } catch (Exception e) {
            Log.d(TAG, "setUseAlpha err:" + e);
            e.printStackTrace();
        }
        this.mVideoView.setTag("plug_videoview");
    }

    private void setVideoViewListener() {
        this.mVideoView.setAudioFocusRequest(0);
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: cn.nubia.plug.PlugUnitActivity.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.d(PlugUnitActivity.TAG, "videoview onPrepared");
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(0.0f, 0.0f);
                }
                PlugUnitActivity.this.startVideoView();
            }
        });
        this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.plug.PlugUnitActivity.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                PlugUnitActivity.this.mPlay.setVisibility(0);
                Log.d(PlugUnitActivity.TAG, "videoview setOnCompletionListener");
            }
        });
        this.mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: cn.nubia.plug.PlugUnitActivity.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.e(PlugUnitActivity.TAG, "videoview setOnErrorListener");
                if (i != 100 || PlugUnitActivity.this.mPlay == null) {
                    return false;
                }
                PlugUnitActivity.this.mPlay.setVisibility(4);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoViewURI(PlugData plugData) {
        if (!plugData.isVideoPre()) {
            this.mPreView.setBackgroundResource(plugData.getPreId());
            this.mPreView.setVisibility(0);
            this.mPlay.setVisibility(4);
            this.mVideoView.setVisibility(4);
            return;
        }
        this.mPreView.setVisibility(4);
        String str = "android.resource://" + getPackageName() + "/" + plugData.getPreId();
        this.mVideoView.setVisibility(0);
        try {
            this.mVideoView.setVideoURI(Uri.parse(str));
        } catch (IllegalStateException e) {
            Log.e(TAG, "setVideoURI:" + str + ", error:" + e);
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVideoView() {
        this.mPlay.setVisibility(4);
        this.mVideoView.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchFragment(int i) {
        String tagByPosition = getTagByPosition(i);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        Fragment fragment = this.mCurrentFragment;
        if (fragment != null) {
            beginTransaction.hide(fragment);
        }
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(tagByPosition);
        this.mCurrentFragment = findFragmentByTag;
        if (findFragmentByTag == null) {
            Fragment fragmentByTag = getFragmentByTag(tagByPosition);
            this.mCurrentFragment = fragmentByTag;
            beginTransaction.add(R.id.fragment_container, fragmentByTag, tagByPosition);
        } else {
            beginTransaction.show(findFragmentByTag);
        }
        beginTransaction.commitNow();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        plugExitAnimation();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.play) {
            PlugAdapter plugAdapter = this.mBaseAdapter;
            setVideoViewURI(plugAdapter.getItem(plugAdapter.getSelectedPosition()));
        } else if (view.getId() == R.id.plug_back_arrow) {
            onBackPressed();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        setContentView(R.layout.plug_unit_activity);
        getWindow().getDecorView().setSystemUiVisibility(5126);
        this.mEntryExitAnimatorOffset = getResources().getInteger(R.integer.plug_entry_exit_animator_offset);
        initView();
        setGridViewAdapter();
        setVideoViewListener();
        setGridViewItemClickListener();
        int selectedPosition = this.mBaseAdapter.getSelectedPosition();
        setVideoViewURI(this.mBaseAdapter.getItem(selectedPosition));
        calcDetailFragmentUI();
        switchFragment(selectedPosition);
        plugEntryAnimation();
        GameKeyObserver.getInstance(this).addCallback(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        VideoView videoView = this.mVideoView;
        if (videoView != null) {
            videoView.suspend();
        }
        clearData();
        super.onDestroy();
        GameKeyObserver.getInstance(this).removeCallback(this);
    }

    @Override // cn.nubia.common.GameKeyObserver.Callback
    public void onGameKeyChanged(boolean z) {
        if (CommonUtil.isZte()) {
            finish();
        }
        if (z) {
            finish();
        }
    }
}
