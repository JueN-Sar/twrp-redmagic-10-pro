package cn.nubia.gamelauncher.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.redmagicplanet.VideoFile;
import cn.nubia.gamelauncher.redmagicplanet.ui.BehaviorLearnedActivity;
import cn.nubia.gamelauncher.redmagicplanet.ui.GameAgentActivity;
import cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer;
import cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayerController;
import cn.nubia.gamelauncher.redmagicplanet.util.CommonUtil;
import cn.nubia.gamelauncher.redmagicplanet.util.LogUtil;
import cn.nubia.gamelauncher.redmagicplanet.util.RedMagicVideoPlayerManager;
import cn.nubia.gamelauncher.redmagicplanet.util.VideoListUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.xgravitation.XGravitationActivity;
import cn.nubia.plug.PlugUtil;
import cn.nubia.resourcelibrary.util.SmallRedPointerHelper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RedMagicPlanetFragment extends Fragment implements View.OnClickListener {
    private static final String ACTION_DELETE_FILE = "cn.nubia.gamecenter.action.DELETE_HIGH_LIGHTS_FILE";
    private static final int MSG_CHECK_RESOURCE_LIB_UPDATE = 2;
    private static final int MSG_VIDEO = 1;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE"};
    private static final int PRIVIEW_VIEWPAGER_MAX_COUNT = 5;
    private static final int REQUEST_PERMISSION_EXTERNAL_STORAGE = 2777;
    private static final String START_RESOURCELIB_ACTION = "cn.nubia.resourcelibrary.action.startResourceLib";
    private static final String TAG = "RedMagicPlanet";
    private RelativeLayout game_agent_layout;
    private ImageView inter_plugin_bg;
    private RelativeLayout learned_behavior_layout;
    private ArrayList<String> mCheckResourceLibUpdateList;
    private Context mContext;
    public RedMagicVideoPlayerController mController;
    private BroadcastReceiver mDelFileReceiver;
    private Handler mHandler;
    private RelativeLayout mInternationalPlugUnitLayout;
    private RelativeLayout mPlugUnitLayout;
    private FrameLayout mPlugUnitParentLayout;
    private RelativeLayout mRedMagicBroadcastLayout;
    private ImageView mResourceLibHasUpdateImage;
    private RelativeLayout mResourcePoolLayout;
    public RedMagicVideoPlayer mVideoPlayer;
    private RelativeLayout mXGravitationLayout;
    private RelativeLayout multi_screen_layout;
    private HandlerThread mHandlerThread = new HandlerThread("check-update");
    private Handler mMainHandler = new Handler() { // from class: cn.nubia.gamelauncher.fragment.RedMagicPlanetFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                RedMagicPlanetFragment.this.mController.setVideoFileLists((List) message.obj);
            } else {
                if (i != 2) {
                    return;
                }
                LogUtil.d("RedMagicPlanet", "handleMessage: MSG_CHECK_RESOURCE_LIB_UPDATE");
                if (RedMagicPlanetFragment.this.mCheckResourceLibUpdateList == null || RedMagicPlanetFragment.this.mCheckResourceLibUpdateList.size() <= 0) {
                    RedMagicPlanetFragment.this.mResourceLibHasUpdateImage.setVisibility(8);
                } else {
                    RedMagicPlanetFragment.this.mResourceLibHasUpdateImage.setVisibility(0);
                }
            }
        }
    };
    Runnable mGetVideoPhotoAndTimeRunnable = new Runnable() { // from class: cn.nubia.gamelauncher.fragment.RedMagicPlanetFragment.2
        @Override // java.lang.Runnable
        public void run() {
            try {
                Log.d("RedMagicPlanet", "****** mGetVideoPhotoAndTimeRunnable mMp4OutPath");
                List<VideoFile> queryVideoFiles = VideoListUtil.queryVideoFiles(RedMagicPlanetFragment.this.mContext, FeatureUtil.planetVideoBannerEnable() ? 5 : 1);
                Message obtainMessage = RedMagicPlanetFragment.this.mMainHandler.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = queryVideoFiles;
                RedMagicPlanetFragment.this.mMainHandler.sendMessage(obtainMessage);
            } catch (Exception e) {
                LogUtil.e("RedMagicPlanet", "Exception", e);
            }
        }
    };
    Runnable mCheckResourceLibUpdateRunnable = new Runnable() { // from class: cn.nubia.gamelauncher.fragment.RedMagicPlanetFragment.3
        @Override // java.lang.Runnable
        public void run() {
            try {
                LogUtil.d("RedMagicPlanet", "****** mCheckResourceLibUpdateRunnable");
                RedMagicPlanetFragment redMagicPlanetFragment = RedMagicPlanetFragment.this;
                redMagicPlanetFragment.mCheckResourceLibUpdateList = SmallRedPointerHelper.getInstance(redMagicPlanetFragment.mContext).executeRequest();
                Message obtainMessage = RedMagicPlanetFragment.this.mMainHandler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = RedMagicPlanetFragment.this.mCheckResourceLibUpdateList;
                RedMagicPlanetFragment.this.mMainHandler.sendMessage(obtainMessage);
            } catch (Exception e) {
                LogUtil.e("RedMagicPlanet", "Exception", e);
            }
        }
    };

    private void checkPermission() {
        if (this.mContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            requestPermissions(PERMISSIONS_STORAGE, 2777);
        } else {
            checkVideoAndResourceLibUpdate();
        }
    }

    private void checkResourceLibUpdate() {
        LogUtil.d("RedMagicPlanet", "checkResourceLibUpdate: ");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mCheckResourceLibUpdateRunnable);
            this.mHandler.post(this.mCheckResourceLibUpdateRunnable);
        }
    }

    private boolean hasReadStoragePermission() {
        return this.mContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    private void initViews(View view) {
        this.mVideoPlayer = (RedMagicVideoPlayer) view.findViewById(R.id.preview_video_layout);
        setController(new RedMagicVideoPlayerController(getActivity()));
        this.mPlugUnitParentLayout = (FrameLayout) view.findViewById(R.id.plug_unit_parent_layout);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.plug_unit_layout);
        this.mPlugUnitLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.source_pool_layout);
        this.mResourcePoolLayout = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.moji_broadcast_layout);
        this.mRedMagicBroadcastLayout = relativeLayout3;
        relativeLayout3.setOnClickListener(this);
        this.game_agent_layout = (RelativeLayout) view.findViewById(R.id.game_agent_layout);
        this.learned_behavior_layout = (RelativeLayout) view.findViewById(R.id.learned_behavior_layout);
        if (CommonUtil.isInternalVersion() || !FeatureUtil.gameAgentEnable()) {
            this.game_agent_layout.setVisibility(8);
            if (FeatureUtil.behaviorLearnedEnable()) {
                this.learned_behavior_layout.setVisibility(0);
                this.learned_behavior_layout.setOnClickListener(this);
            }
        } else {
            this.game_agent_layout.setOnClickListener(this);
        }
        this.multi_screen_layout = (RelativeLayout) view.findViewById(R.id.multi_screen_layout);
        if (FeatureUtil.multiScreenEnable()) {
            this.multi_screen_layout.setOnClickListener(this);
        } else {
            this.multi_screen_layout.setVisibility(8);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.resource_pool_has_update_waring);
        this.mResourceLibHasUpdateImage = imageView;
        imageView.setVisibility(8);
        this.mXGravitationLayout = (RelativeLayout) view.findViewById(R.id.x_gravitation_layout);
        if (FeatureUtil.isXGravitationEnable()) {
            this.mXGravitationLayout.setOnClickListener(this);
        } else {
            this.mXGravitationLayout.setVisibility(8);
        }
        RelativeLayout relativeLayout4 = (RelativeLayout) view.findViewById(R.id.international_plugin_unit_layout);
        this.mInternationalPlugUnitLayout = relativeLayout4;
        relativeLayout4.setOnClickListener(this);
        this.inter_plugin_bg = (ImageView) view.findViewById(R.id.inter_plugin_bg);
        updateUI(CommonUtil.isInternalVersion());
    }

    private void registReceiver() {
        if (this.mDelFileReceiver != null) {
            getContext().unregisterReceiver(this.mDelFileReceiver);
            this.mDelFileReceiver = null;
        }
        this.mDelFileReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamelauncher.fragment.RedMagicPlanetFragment.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (RedMagicPlanetFragment.ACTION_DELETE_FILE.equals(intent.getAction())) {
                    Log.d("RedMagicPlanet", "onReceive");
                    RedMagicPlanetFragment.this.loadAndCheckVideo();
                }
            }
        };
        getContext().registerReceiver(this.mDelFileReceiver, new IntentFilter(ACTION_DELETE_FILE), 2);
    }

    private void setNoVideoUI() {
        CommonUtil.setDefaultVideoRul(null);
        RedMagicVideoPlayer redMagicVideoPlayer = this.mVideoPlayer;
        if (redMagicVideoPlayer != null) {
            redMagicVideoPlayer.setUp(null, null);
        }
        this.mController.setTitle(null);
        this.mController.setImage(R.drawable.red_magic_default_background);
    }

    public static void showWarningDialog(final Activity activity) {
        LogUtil.d("RedMagicPlanet", "showWarningDialog: ");
        AlertDialog create = new AlertDialog.Builder(activity, 2131952382).setView((LinearLayout) LayoutInflater.from(activity).inflate(R.layout.request_permission_custom_dialog, (ViewGroup) null)).create();
        create.setButton(-2, activity.getString(android.R.string.cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.RedMagicPlanetFragment.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.setButton(-1, activity.getString(R.string.go_permission_setting), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.fragment.RedMagicPlanetFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                activity.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + activity.getPackageName())));
            }
        });
        create.show();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
    }

    private void updateUI(boolean z) {
        LogUtil.d("RedMagicPlanet", "updateUI: isInternational : " + z);
        this.mRedMagicBroadcastLayout.setVisibility(FeatureUtil.moraEnable() ? 0 : 8);
        if (!z && FeatureUtil.resourceLibEnable()) {
            this.mPlugUnitParentLayout.setVisibility(0);
            this.mPlugUnitLayout.setVisibility(0);
            this.mInternationalPlugUnitLayout.setVisibility(8);
            this.mResourcePoolLayout.setVisibility(0);
            return;
        }
        this.mPlugUnitParentLayout.setVisibility(8);
        this.mPlugUnitLayout.setVisibility(8);
        this.mInternationalPlugUnitLayout.setVisibility(0);
        this.mResourcePoolLayout.setVisibility(8);
        if (FeatureUtil.isXGravitationEnable()) {
            this.mInternationalPlugUnitLayout.getLayoutParams().width = (int) this.mContext.getResources().getDimension(R.dimen.x_gravitation_layout_width_size);
            this.inter_plugin_bg.setImageResource(R.drawable.international_plug_narrow_selector);
            this.mInternationalPlugUnitLayout.requestLayout();
        } else {
            this.mInternationalPlugUnitLayout.getLayoutParams().width = (int) this.mContext.getResources().getDimension(R.dimen.inter_plug_layout_width_big);
            this.inter_plugin_bg.setImageResource(R.drawable.international_plug_selector);
            this.mInternationalPlugUnitLayout.requestLayout();
        }
    }

    public void checkVideoAndResourceLibUpdate() {
        loadAndCheckVideo();
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        checkResourceLibUpdate();
    }

    public void loadAndCheckVideo() {
        LogUtil.d("RedMagicPlanet", "loadAndCheckVideo: ");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mGetVideoPhotoAndTimeRunnable);
            this.mHandler.post(this.mGetVideoPhotoAndTimeRunnable);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        LogUtil.d("RedMagicPlanet", "---->onActivityCreated()");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str = "";
        switch (view.getId()) {
            case R.id.game_agent_layout /* 2131362331 */:
                this.mContext.startActivity(new Intent(getActivity(), (Class<?>) GameAgentActivity.class));
                break;
            case R.id.international_plugin_unit_layout /* 2131362615 */:
            case R.id.plug_unit_layout /* 2131363041 */:
                LobbySoundPoolHelper.getInstance().play();
                PlugUtil.startPlugUnit(getActivity().getApplicationContext());
                str = "plugin_library_entrance_click";
                break;
            case R.id.learned_behavior_layout /* 2131362677 */:
                this.mContext.startActivity(new Intent(getActivity(), (Class<?>) BehaviorLearnedActivity.class));
                break;
            case R.id.moji_broadcast_layout /* 2131362792 */:
                new Intent("cn.nubia.elvelsbroadcast.mainset1");
                try {
                    Intent intent = new Intent();
                    intent.setAction("intent.action.redmagickyi.main");
                    intent.setFlags(268435456);
                    this.mContext.startActivity(intent);
                } catch (Exception e) {
                    LogUtil.e("RedMagicPlanet", " start redmagicapp error  ", e);
                }
                LobbySoundPoolHelper.getInstance().play();
                str = "game_broadcast_entrance_click";
                break;
            case R.id.multi_screen_layout /* 2131362829 */:
                try {
                    Intent intent2 = new Intent();
                    intent2.setAction("cn.nubia.gameassist.action.START_MULTI_SUB_SCREEN_SOURCE_ACTIVITY");
                    intent2.setPackage("cn.nubia.gameassist");
                    intent2.addFlags(268435456);
                    if (intent2.resolveActivity(this.mContext.getPackageManager()) != null) {
                        this.mContext.startActivity(intent2);
                        break;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    break;
                }
                break;
            case R.id.source_pool_layout /* 2131363266 */:
                LobbySoundPoolHelper.getInstance().play();
                try {
                    str = "resource_library_entrance_click";
                    Intent intent3 = new Intent();
                    intent3.setAction(START_RESOURCELIB_ACTION);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("result", this.mCheckResourceLibUpdateList);
                    intent3.putExtras(bundle);
                    intent3.addFlags(268468224);
                    getActivity().startActivity(intent3);
                    break;
                } catch (ActivityNotFoundException e3) {
                    LogUtil.e("RedMagicPlanet", " start resourcelib error  ", e3);
                    break;
                }
            case R.id.x_gravitation_layout /* 2131363710 */:
                LobbySoundPoolHelper.getInstance().play();
                startActivity(new Intent(getActivity(), (Class<?>) XGravitationActivity.class));
                str = "xgravity_superbase";
                break;
        }
        if (CommonUtil.isInternalVersion() || TextUtils.isEmpty(str)) {
            return;
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", str);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d("RedMagicPlanet", "---->onCreate()");
        this.mContext = getActivity();
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        checkVideoAndResourceLibUpdate();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.d("RedMagicPlanet", "---->onCreateView()");
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.redmagic_planet_layout, viewGroup, false);
        initViews(inflate);
        registReceiver();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.d("RedMagicPlanet", "onDestroyView: ");
        super.onDestroyView();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(null);
            this.mHandler = null;
        }
        Handler handler2 = this.mMainHandler;
        if (handler2 != null) {
            handler2.removeCallbacks(null);
            this.mMainHandler = null;
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        RedMagicVideoPlayerController redMagicVideoPlayerController = this.mController;
        if (redMagicVideoPlayerController != null) {
            redMagicVideoPlayerController.onDestroy();
        }
        if (this.mDelFileReceiver != null) {
            getContext().unregisterReceiver(this.mDelFileReceiver);
            this.mDelFileReceiver = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        RedMagicVideoPlayerController redMagicVideoPlayerController;
        super.onHiddenChanged(z);
        if (!z || (redMagicVideoPlayerController = this.mController) == null) {
            return;
        }
        redMagicVideoPlayerController.fragmentOnPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        LogUtil.d("RedMagicPlanet", "---->onInflate()");
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.d("RedMagicPlanet", "onPause()");
        if (RedMagicVideoPlayerManager.instance().getCurrentRedMagicVideoPlayer() != null) {
            if (RedMagicVideoPlayerManager.instance().getCurrentRedMagicVideoPlayer().isFullScreen()) {
                RedMagicVideoPlayerManager.instance().suspendRedMagicVideoPlayer();
            } else {
                RedMagicVideoPlayerManager.instance().releaseRedMagicVideoPlayer();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 2777) {
            if (iArr == null || iArr.length <= 0 || iArr[0] != 0) {
                boolean shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE");
                LogUtil.d("RedMagicPlanet", "External Storage permissions need to be granted ! request : " + shouldShowRequestPermissionRationale);
                if (!shouldShowRequestPermissionRationale && !CommonUtil.showPermissionWaringDialog(this.mContext)) {
                    CommonUtil.setDisplayPermissionDialog(this.mContext);
                    return;
                }
            } else {
                LogUtil.d("RedMagicPlanet", "permission granted!");
                checkVideoAndResourceLibUpdate();
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (RedMagicVideoPlayerManager.instance().getCurrentRedMagicVideoPlayer() == null) {
            RedMagicVideoPlayerManager.instance().setCurrentRedMagicVideoPlayer(this.mVideoPlayer);
        }
        LogUtil.d("RedMagicPlanet", "onResume: isNormal : " + RedMagicVideoPlayerManager.instance().getCurrentRedMagicVideoPlayer().isNormal());
        if (!CommonUtil.isInternalVersion()) {
            checkResourceLibUpdate();
        }
        this.mController.fragmentOnResume();
    }

    public void setController(RedMagicVideoPlayerController redMagicVideoPlayerController) {
        this.mController = redMagicVideoPlayerController;
        this.mVideoPlayer.setController(redMagicVideoPlayerController);
    }
}
