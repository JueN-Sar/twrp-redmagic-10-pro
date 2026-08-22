package cn.nubia.gamelauncher.redmagicplanet.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.media3.common.C;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer;
import cn.nubia.gamelauncher.redmagicplanet.VideoFile;
import cn.nubia.gamelauncher.redmagicplanet.util.CommonUtil;
import cn.nubia.gamelauncher.redmagicplanet.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.WorkThread;
import cn.nubia.plug.PlugUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class RedMagicVideoPlayerController extends CommonVideoPlayerController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final int MSG_NEXT_VIEW_PAGER = 1;
    private static final String REDMAGIC_HIGH_KEY_ACTION = "cn.nubia.gamecenter.settings.action.GAME_CENTER_RADMAGICTIME_DETAIL";
    private static final String TAG = "RedMagicVideoPlayerController";
    private static final int VIEW_PAGER_TIME = 3000;
    private boolean addTextureView;
    private boolean mAutoScrollNext;
    private HashMap<String, Bitmap> mBitmapPools;
    private LinearLayout mBottom;
    private RelativeLayout mCenterStartLayout;
    private Context mContext;
    private int mCurPlayPosition;
    private LinearLayout mError;
    private ImageView mFullScreen;
    private ImageView mFullScreenExit;
    private LinearLayout mFullScreenLayout;
    private FullScreenOutlineProvider mFullScreenOutLine;
    private SeekBar mFullScreenPlayBackSeekBar;
    private TextView mFullScreenPosition;
    private ImageView mFullScreenRestartPause;
    private final Handler mHandler;
    private ImageView mImage;
    private TextView mLoadText;
    private LinearLayout mLoading;
    private TextView mNoVideoTipsText;
    private PagerAdapter mPageAdapter;
    private String mPkgName;
    private SeekBar mPlayBackSeekBar;
    private TextView mPosition;
    private double mPriview_h;
    private double mPriview_w;
    private TextView mRedMagicGameTitleText;
    private RelativeLayout mRedMagicMoreLayout;
    private ImageView mRestartPause;
    private TextView mRetry;
    private View mRoot;
    private boolean mStartTracking;
    private String mTitle;
    private List<VideoFile> mVideoFileList;
    private RedMagicViewOutlineProvider mVideoViewOutlineProvider;
    private LinearLayout planet_preview_indicator;
    private ViewPager video_pager_preview;

    /* renamed from: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController$3, reason: invalid class name */
    class AnonymousClass3 extends PagerAdapter {
        AnonymousClass3() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            View view = (View) obj;
            if (((Boolean) view.getTag()).booleanValue() && !RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.isFullScreen()) {
                LogUtil.d("RedMagicVideoPlayer", "destroyItem : " + i);
                RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.stop();
            }
            viewGroup.removeView(view);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return RedMagicVideoPlayerController.this.mVideoFileList.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            if (RedMagicVideoPlayerController.this.addTextureView && i == RedMagicVideoPlayerController.this.mCurPlayPosition) {
                viewGroup.addView(RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.getmContainer());
                LogUtil.d("RedMagicVideoPlayer", "add texture to " + i);
                RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.getmContainer().setTag(Boolean.valueOf(RedMagicVideoPlayerController.this.addTextureView));
                return RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.getmContainer();
            }
            View inflate = LayoutInflater.from(RedMagicVideoPlayerController.this.mContext).inflate(R.layout.redmagic_video_viewpager_item, (ViewGroup) null, false);
            final ImageView imageView = (ImageView) inflate.findViewById(R.id.image_cover);
            inflate.findViewById(R.id.iv_center_start).setOnClickListener(RedMagicVideoPlayerController.this);
            final String uri = ((VideoFile) RedMagicVideoPlayerController.this.mVideoFileList.get(i)).getUri().toString();
            if (RedMagicVideoPlayerController.this.mBitmapPools.get(uri) != null) {
                imageView.setImageBitmap((Bitmap) RedMagicVideoPlayerController.this.mBitmapPools.get(uri));
            } else {
                WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        final Bitmap videoFirstFrameImage = CommonUtil.getVideoFirstFrameImage(RedMagicVideoPlayerController.this.mContext, uri);
                        if (videoFirstFrameImage != null) {
                            double width = videoFirstFrameImage.getWidth();
                            double height = videoFirstFrameImage.getHeight();
                            if (width <= height) {
                                imageView.post(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController.3.1.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        imageView.setImageBitmap(videoFirstFrameImage);
                                        RedMagicVideoPlayerController.this.mBitmapPools.put(uri, videoFirstFrameImage);
                                    }
                                });
                                return;
                            }
                            if (RedMagicVideoPlayerController.this.mPriview_w > 0.0d && RedMagicVideoPlayerController.this.mPriview_h > 0.0d) {
                                width = RedMagicVideoPlayerController.this.mPriview_w;
                                height = RedMagicVideoPlayerController.this.mPriview_h;
                            }
                            final Bitmap zoomImage = BitmapUtils.getZoomImage(videoFirstFrameImage, width, height, false);
                            imageView.post(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController.3.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    imageView.setImageBitmap(zoomImage);
                                    RedMagicVideoPlayerController.this.mBitmapPools.put(uri, zoomImage);
                                }
                            });
                        }
                    }
                });
            }
            viewGroup.addView(inflate);
            inflate.setTag(false);
            return inflate;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public RedMagicVideoPlayerController(Context context) {
        super(context);
        this.mStartTracking = false;
        this.mAutoScrollNext = false;
        this.mHandler = new Handler() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1 || RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.isPreparing() || RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.isPrepared() || RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.isPlaying() || RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.isPaused()) {
                    return;
                }
                int currentItem = RedMagicVideoPlayerController.this.video_pager_preview.getCurrentItem() + 1;
                if (currentItem >= RedMagicVideoPlayerController.this.mPageAdapter.getCount()) {
                    currentItem = 0;
                }
                RedMagicVideoPlayerController.this.video_pager_preview.setCurrentItem(currentItem, true);
            }
        };
        this.mCurPlayPosition = -1;
        this.mContext = context;
        this.mBitmapPools = new HashMap<>();
        init();
    }

    private void init() {
        this.mRoot = LayoutInflater.from(this.mContext).inflate(R.layout.redmagic_video_palyer_controller_new, (ViewGroup) this, true);
        this.mVideoViewOutlineProvider = new RedMagicViewOutlineProvider(this.mContext.getResources().getDimensionPixelSize(R.dimen.red_magic_default_bg_radius));
        this.mFullScreenOutLine = new FullScreenOutlineProvider(this.mContext.getResources().getDimensionPixelSize(R.dimen.red_magic_default_bg_radius));
        this.mRoot.setOutlineProvider(this.mVideoViewOutlineProvider);
        this.mRoot.setClipToOutline(true);
        this.mCenterStartLayout = (RelativeLayout) findViewById(R.id.center_start_layout);
        this.mImage = (ImageView) findViewById(R.id.image);
        ViewPager viewPager = (ViewPager) findViewById(R.id.video_pager_preview);
        this.video_pager_preview = viewPager;
        viewPager.setPageMargin(30);
        this.planet_preview_indicator = (LinearLayout) findViewById(R.id.planet_preview_indicator);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bottom);
        this.mBottom = linearLayout;
        linearLayout.setVisibility(8);
        this.mRestartPause = (ImageView) findViewById(R.id.restart_or_pause);
        this.mPosition = (TextView) findViewById(R.id.position);
        this.mPlayBackSeekBar = (SeekBar) findViewById(R.id.play_back_seek);
        this.mFullScreen = (ImageView) findViewById(R.id.full_screen);
        this.mLoading = (LinearLayout) findViewById(R.id.loading);
        this.mLoadText = (TextView) findViewById(R.id.load_text);
        this.mError = (LinearLayout) findViewById(R.id.error);
        this.mRetry = (TextView) findViewById(R.id.retry);
        this.mRestartPause.setOnClickListener(this);
        this.mFullScreen.setOnClickListener(this);
        this.mRetry.setOnClickListener(this);
        this.mPlayBackSeekBar.setOnSeekBarChangeListener(this);
        setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.red_magic_more_layout);
        this.mRedMagicMoreLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.mNoVideoTipsText = (TextView) findViewById(R.id.no_video_warning_text);
        if (ControlPanelFeatureHelper.getZtFeatureGameRandomRecord().booleanValue()) {
            this.mNoVideoTipsText.setText(R.string.no_video_waring_text_new);
        } else {
            this.mNoVideoTipsText.setText(R.string.no_video_waring_text);
        }
        if (!cn.nubia.common.util.CommonUtil.isNubia() && !PlugUtil.isRedMagic7()) {
            if (ControlPanelFeatureHelper.getZtFeatureGameRandomRecord().booleanValue()) {
                this.mNoVideoTipsText.setText(R.string.no_video_waring_text_zte_new);
            } else {
                this.mNoVideoTipsText.setText(R.string.no_video_waring_text_zte);
            }
        }
        if (CommonUtil.isInternalVersion()) {
            if (ControlPanelFeatureHelper.getZtFeatureGameRandomRecord().booleanValue()) {
                this.mNoVideoTipsText.setText(R.string.no_video_waring_text_inter_new);
            } else {
                this.mNoVideoTipsText.setText(R.string.no_video_waring_text_inter);
            }
        }
        this.mFullScreenLayout = (LinearLayout) findViewById(R.id.red_magic_fullscreen_layout);
        ImageView imageView = (ImageView) findViewById(R.id.fullscreen_restart_or_pause);
        this.mFullScreenRestartPause = imageView;
        imageView.setOnClickListener(this);
        this.mFullScreenPosition = (TextView) findViewById(R.id.fullscreen_position);
        SeekBar seekBar = (SeekBar) findViewById(R.id.fullscreen_play_back_seek);
        this.mFullScreenPlayBackSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        ImageView imageView2 = (ImageView) findViewById(R.id.fullscreen_exit);
        this.mFullScreenExit = imageView2;
        imageView2.setOnClickListener(this);
        this.mRedMagicGameTitleText = (TextView) findViewById(R.id.red_magic_video_title);
        setTitle(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewPager() {
        this.video_pager_preview.clearOnPageChangeListeners();
        this.mPageAdapter = null;
        this.video_pager_preview.setAdapter(null);
        this.video_pager_preview.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                RedMagicVideoPlayerController.this.updatePageIndicator(i);
                RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.setViewPagerPosition(i);
                RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.setUp(((VideoFile) RedMagicVideoPlayerController.this.mVideoFileList.get(i)).getUri().toString(), null);
                RedMagicVideoPlayerController redMagicVideoPlayerController = RedMagicVideoPlayerController.this;
                redMagicVideoPlayerController.setTitle(CommonUtil.getGameNameByVideoFileTile(redMagicVideoPlayerController.mContext, ((VideoFile) RedMagicVideoPlayerController.this.mVideoFileList.get(i)).getTitle()));
                RedMagicVideoPlayerController redMagicVideoPlayerController2 = RedMagicVideoPlayerController.this;
                redMagicVideoPlayerController2.setPkgName(CommonUtil.getPackageNameByPath(((VideoFile) redMagicVideoPlayerController2.mVideoFileList.get(i)).getPath()));
                if (RedMagicVideoPlayerController.this.mAutoScrollNext) {
                    RedMagicVideoPlayerController.this.mHandler.removeMessages(1);
                    RedMagicVideoPlayerController.this.mHandler.sendEmptyMessageDelayed(1, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                }
                if (i == RedMagicVideoPlayerController.this.mCurPlayPosition && RedMagicVideoPlayerController.this.addTextureView) {
                    RedMagicVideoPlayerController.this.mBottom.setVisibility(0);
                    RedMagicVideoPlayerController.this.mFullScreenLayout.setVisibility(8);
                } else {
                    RedMagicVideoPlayerController.this.mBottom.setVisibility(8);
                    RedMagicVideoPlayerController.this.mFullScreenLayout.setVisibility(8);
                    RedMagicVideoPlayerController.this.mError.setVisibility(8);
                }
            }
        });
        PagerAdapter pagerAdapter = this.mPageAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
            return;
        }
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        this.mPageAdapter = anonymousClass3;
        this.video_pager_preview.setAdapter(anonymousClass3);
    }

    private void setIndicatorVisible(int i) {
        if (i == 0 && this.mVideoFileList.size() < 2) {
            i = 8;
        }
        this.planet_preview_indicator.setVisibility(i);
        if (i != 0) {
            this.mAutoScrollNext = false;
            this.mHandler.removeMessages(1);
        } else {
            this.mAutoScrollNext = true;
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        }
    }

    private void setTopBottomVisible(boolean z) {
        if (this.mRedMagicVideoPlayer.isFullScreen()) {
            this.mFullScreenLayout.setVisibility(z ? 0 : 8);
        } else {
            this.mBottom.setVisibility(z ? 0 : 8);
        }
    }

    private void startRedMagicHighActivity(Context context, String str) {
        LogUtil.d(TAG, " startRedMagicHighActivity  packageName : " + str);
        Intent intent = new Intent(REDMAGIC_HIGH_KEY_ACTION);
        intent.putExtra("package_name", str);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    private void updateLayoutParams(int i) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (i == 10) {
            layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.red_magic_preview_video_normal_height);
        } else if (i == 11) {
            layoutParams.height = -1;
        }
        this.mCenterStartLayout.setLayoutParams(layoutParams);
        this.mLoading.setLayoutParams(layoutParams);
        this.mError.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePageIndicator(int i) {
        int childCount = this.planet_preview_indicator.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.planet_preview_indicator.getChildAt(i2);
            if (i2 < this.mVideoFileList.size()) {
                childAt.setVisibility(0);
                childAt.setBackgroundResource(i2 == i ? R.drawable.shape_redmagic_indicator_select : R.drawable.shape_redmagic_indicator_unselect);
            } else {
                childAt.setVisibility(8);
            }
            i2++;
        }
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void addTextureView(int i) {
        this.mCurPlayPosition = i;
        this.addTextureView = true;
        PagerAdapter pagerAdapter = this.mPageAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void fragmentOnPause() {
        this.mAutoScrollNext = false;
        this.mHandler.removeMessages(1);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void fragmentOnResume() {
        List<VideoFile> list = this.mVideoFileList;
        if (list == null || list.size() <= 1) {
            return;
        }
        this.mAutoScrollNext = true;
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public int getPagerSize() {
        List<VideoFile> list = this.mVideoFileList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    protected boolean getPlayBackSeekBarIsTracking() {
        return this.mStartTracking;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.full_screen /* 2131362279 */:
                LogUtil.d(TAG, "onClick: mFullScreen");
                if (this.mRedMagicVideoPlayer.isNormal()) {
                    this.mRedMagicVideoPlayer.enterFullScreen();
                    break;
                }
                break;
            case R.id.fullscreen_exit /* 2131362280 */:
                LogUtil.d(TAG, "onClick: mFullScreenExit");
                if (this.mRedMagicVideoPlayer.isFullScreen()) {
                    this.mRedMagicVideoPlayer.exitFullScreen();
                    break;
                }
                break;
            case R.id.fullscreen_restart_or_pause /* 2131362284 */:
            case R.id.restart_or_pause /* 2131363131 */:
                LogUtil.d(TAG, "onClick: mRestartPause");
                if (!this.mRedMagicVideoPlayer.isPlaying()) {
                    if (this.mRedMagicVideoPlayer.isPaused()) {
                        this.mRedMagicVideoPlayer.restart();
                        break;
                    }
                } else {
                    this.mRedMagicVideoPlayer.pause();
                    break;
                }
                break;
            case R.id.iv_center_start /* 2131362637 */:
                LogUtil.d(TAG, "onClick: mCenterStart");
                if (this.mRedMagicVideoPlayer.isIdle()) {
                    this.mRedMagicVideoPlayer.start();
                } else if (this.mRedMagicVideoPlayer.isCompleted() || this.mRedMagicVideoPlayer.isStopExit() || this.mRedMagicVideoPlayer.isError()) {
                    this.mRedMagicVideoPlayer.restart();
                } else if (this.mRedMagicVideoPlayer.isPlaying() || this.mRedMagicVideoPlayer.isPaused()) {
                    this.mRedMagicVideoPlayer.stop();
                    this.mRedMagicVideoPlayer.restart();
                }
                if (!cn.nubia.gamelauncher.util.CommonUtil.isInternalVersion()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("app_name", this.mTitle);
                    bundle.putString("package_name", this.mPkgName);
                    NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_preview", bundle);
                    break;
                }
                break;
            case R.id.red_magic_more_layout /* 2131363114 */:
                LogUtil.d(TAG, "onClick: more");
                Context context = this.mContext;
                startRedMagicHighActivity(context, context.getPackageName());
                if (!cn.nubia.gamelauncher.util.CommonUtil.isInternalVersion()) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("app_name", this.mTitle);
                    bundle2.putString("package_name", this.mPkgName);
                    NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "redmagic_time_more", bundle2);
                    break;
                }
                break;
            case R.id.retry /* 2131363132 */:
                LogUtil.d(TAG, "onClick: mRetry");
                this.mRedMagicVideoPlayer.restart();
                break;
            default:
                LogUtil.d(TAG, "onClick: this");
                break;
        }
    }

    public void onDestroy() {
        for (Bitmap bitmap : this.mBitmapPools.values()) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
        this.mBitmapPools.clear();
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    protected void onPlayModeChanged(int i) {
        LogUtil.d(TAG, "onPlayModeChanged: playMode ：" + i);
        if (i == 10) {
            this.mRoot.setOutlineProvider(this.mVideoViewOutlineProvider);
            this.mBottom.setVisibility(0);
            this.mRedMagicMoreLayout.setVisibility(0);
            this.mFullScreen.setImageResource(R.drawable.red_magic_player_enlarge_selector);
            this.mFullScreen.setVisibility(0);
            this.mFullScreenLayout.setVisibility(8);
            this.video_pager_preview.setVisibility(0);
            setIndicatorVisible(0);
        } else if (i == 11) {
            this.mRoot.setOutlineProvider(this.mFullScreenOutLine);
            this.mRedMagicMoreLayout.setVisibility(8);
            this.mFullScreenLayout.setVisibility(0);
            this.mBottom.setVisibility(8);
            this.video_pager_preview.setVisibility(8);
            setIndicatorVisible(8);
        }
        updateLayoutParams(i);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    protected void onPlayStateChanged(int i) {
        LogUtil.d(TAG, "onPlayStateChanged: playState ：" + i);
        if (i == -1) {
            setTopBottomVisible(false);
            this.mError.setVisibility(0);
            return;
        }
        if (i == 1) {
            this.mImage.setVisibility(8);
            this.mLoading.setVisibility(0);
            this.mError.setVisibility(8);
            this.mBottom.setVisibility(8);
            return;
        }
        if (i == 3) {
            this.mLoading.setVisibility(8);
            if (this.mRedMagicVideoPlayer.isFullScreen()) {
                this.mFullScreenLayout.setVisibility(0);
                this.mBottom.setVisibility(8);
                this.mFullScreenRestartPause.setImageResource(R.drawable.red_magic_full_screen_player_pause_selector);
            } else {
                this.mBottom.setVisibility(0);
                this.mFullScreenLayout.setVisibility(8);
                this.mRestartPause.setImageResource(R.drawable.red_magic_player_pause_selector);
            }
            this.mError.setVisibility(8);
            return;
        }
        if (i == 4) {
            this.mLoading.setVisibility(8);
            if (this.mRedMagicVideoPlayer.isFullScreen()) {
                this.mFullScreenRestartPause.setImageResource(R.drawable.red_magic_fullscreen_player_start_selector);
                return;
            } else {
                this.mRestartPause.setImageResource(R.drawable.red_magic_player_start_selector);
                return;
            }
        }
        if (i != 5) {
            if (i != 6) {
                return;
            }
            if (this.mRedMagicVideoPlayer.isFullScreen()) {
                setTopBottomVisible(true);
                return;
            } else {
                this.mCurPlayPosition = -1;
                this.addTextureView = false;
                return;
            }
        }
        LogUtil.d(TAG, "onPlayStateChanged: isFullScreen() : " + this.mRedMagicVideoPlayer.isFullScreen());
        if (this.mRedMagicVideoPlayer.isFullScreen()) {
            this.mRedMagicVideoPlayer.restart();
            setTopBottomVisible(true);
        } else {
            updateDefaultImage();
            setTopBottomVisible(false);
            removeTextureView();
        }
        this.mRestartPause.setImageResource(R.drawable.red_magic_player_start_selector);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        this.mStartTracking = true;
        LogUtil.d(TAG, "onStartTrackingTouch: ");
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        LogUtil.d(TAG, "onStopTrackingTouch: ");
        if (this.mRedMagicVideoPlayer != null && this.mRedMagicVideoPlayer.isPaused()) {
            this.mRedMagicVideoPlayer.restart();
        }
        this.mRedMagicVideoPlayer.seekTo(seekBar.getProgress());
        this.mStartTracking = false;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void removeTextureView() {
        this.mCurPlayPosition = -1;
        this.addTextureView = false;
        PagerAdapter pagerAdapter = this.mPageAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    protected void reset() {
        LogUtil.d(TAG, "reset: ");
        this.mPlayBackSeekBar.setProgress(0);
        this.mPlayBackSeekBar.setSecondaryProgress(0);
        updateDefaultImage();
        this.mBottom.setVisibility(8);
        this.mFullScreen.setImageResource(R.drawable.red_magic_player_enlarge_selector);
        this.mLoading.setVisibility(8);
        this.mError.setVisibility(8);
        this.mFullScreenLayout.setVisibility(8);
        this.mFullScreenPlayBackSeekBar.setProgress(0);
        this.mFullScreenPlayBackSeekBar.setSecondaryProgress(0);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void setImage(int i) {
        this.mImage.setImageResource(i);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void setLength(long j) {
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void setPkgName(String str) {
        this.mPkgName = str;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    protected void setPlayBackSeekBarToMax(int i) {
        SeekBar seekBar = this.mPlayBackSeekBar;
        if (seekBar != null) {
            seekBar.setMax(i);
            this.mFullScreenPlayBackSeekBar.setMax(i);
        }
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void setRedMagicVideoPlayer(IRedMagicVideoPlayer iRedMagicVideoPlayer) {
        super.setRedMagicVideoPlayer(iRedMagicVideoPlayer);
        LogUtil.d(TAG, "setNiceVideoPlayer:  url : " + CommonUtil.DEFAULT_VIDEO_RUL);
        this.mRedMagicVideoPlayer.setUp(CommonUtil.DEFAULT_VIDEO_RUL, null);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void setTitle(String str) {
        this.mTitle = str;
        if (TextUtils.isEmpty(str)) {
            if (cn.nubia.common.util.CommonUtil.isNubia() || PlugUtil.isRedMagic7()) {
                this.mRedMagicGameTitleText.setText(getResources().getString(R.string.mode_redmiagic_time));
                return;
            } else {
                this.mRedMagicGameTitleText.setText(getResources().getString(R.string.mode_wonderful_time));
                return;
            }
        }
        if (cn.nubia.common.util.CommonUtil.isNubia() || PlugUtil.isRedMagic7()) {
            this.mRedMagicGameTitleText.setText(getResources().getString(R.string.red_magic_name_title, str));
        } else {
            this.mRedMagicGameTitleText.setText(getResources().getString(R.string.wonderful_time_title, str));
        }
    }

    public void setVideoFileLists(List<VideoFile> list) {
        List<VideoFile> list2 = this.mVideoFileList;
        if (list2 != null) {
            list2.clear();
        }
        this.mVideoFileList = list;
        PagerAdapter pagerAdapter = this.mPageAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.notifyDataSetChanged();
        }
        updateDefaultImage();
        List<VideoFile> list3 = this.mVideoFileList;
        if (list3 != null && list3.size() > 0) {
            this.video_pager_preview.post(new Runnable() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController.4
                @Override // java.lang.Runnable
                public void run() {
                    RedMagicVideoPlayerController.this.mPriview_w = r0.video_pager_preview.getWidth();
                    RedMagicVideoPlayerController.this.mPriview_h = r0.video_pager_preview.getHeight();
                    RedMagicVideoPlayerController.this.initViewPager();
                    RedMagicVideoPlayerController.this.updatePageIndicator(0);
                    RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.setViewPagerPosition(0);
                    RedMagicVideoPlayerController.this.mRedMagicVideoPlayer.setUp(((VideoFile) RedMagicVideoPlayerController.this.mVideoFileList.get(0)).getUri().toString(), null);
                    RedMagicVideoPlayerController redMagicVideoPlayerController = RedMagicVideoPlayerController.this;
                    redMagicVideoPlayerController.setTitle(CommonUtil.getGameNameByVideoFileTile(redMagicVideoPlayerController.mContext, ((VideoFile) RedMagicVideoPlayerController.this.mVideoFileList.get(0)).getTitle()));
                    RedMagicVideoPlayerController redMagicVideoPlayerController2 = RedMagicVideoPlayerController.this;
                    redMagicVideoPlayerController2.setPkgName(CommonUtil.getPackageNameByPath(((VideoFile) redMagicVideoPlayerController2.mVideoFileList.get(0)).getPath()));
                }
            });
            return;
        }
        this.mRedMagicVideoPlayer.setUp(null, null);
        setTitle(null);
        setImage(R.drawable.red_magic_default_background);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    public void updateDefaultImage() {
        List<VideoFile> list = this.mVideoFileList;
        if (list == null || list.size() == 0) {
            this.mRedMagicMoreLayout.setVisibility(0);
            this.mNoVideoTipsText.setVisibility(0);
            this.mImage.setVisibility(0);
            this.video_pager_preview.setVisibility(8);
            setIndicatorVisible(8);
            return;
        }
        this.mRedMagicMoreLayout.setVisibility(0);
        this.video_pager_preview.setVisibility(0);
        setIndicatorVisible(0);
        this.mImage.setVisibility(8);
        this.mNoVideoTipsText.setVisibility(8);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.ui.CommonVideoPlayerController
    protected void updateProgress() {
        long currentPosition = this.mRedMagicVideoPlayer.getCurrentPosition();
        long duration = this.mRedMagicVideoPlayer.getDuration();
        if (this.mRedMagicVideoPlayer.isFullScreen()) {
            this.mFullScreenPlayBackSeekBar.setProgress((int) currentPosition);
            this.mFullScreenPosition.setText(CommonUtil.formatTime(currentPosition) + "/" + CommonUtil.formatTime(duration));
        } else {
            this.mPlayBackSeekBar.setProgress((int) currentPosition);
            this.mPosition.setText(CommonUtil.formatTime(currentPosition) + "/" + CommonUtil.formatTime(duration));
        }
    }
}
