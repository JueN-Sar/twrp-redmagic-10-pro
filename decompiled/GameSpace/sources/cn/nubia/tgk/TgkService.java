package cn.nubia.tgk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.media3.common.PlaybackException;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.tgk.TgkGuideView;
import cn.nubia.tgk.TgkMapView;
import cn.nubia.tgk.data.TgkData;
import cn.nubia.tgk.data.TgkGameInfo;
import cn.nubia.tgk.proxy.InputManagerProxy;
import cn.nubia.tgk.util.TgkFeatureUtil;
import cn.nubia.tgk.util.TgkFileHelper;
import cn.nubia.tgk.util.TgkLampHelper;
import cn.nubia.tgk.util.TgkUtils;
import cn.nubia.tgk.util.ToastUtil;
import cn.nubia.tgk.widget.TgkCaseListShowView;
import cn.nubia.tgk.widget.TgkCaseShowView;
import cn.nubia.tgk.widget.TgkShowCaseListViewAdapter;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public class TgkService extends Service {
    public static final String ACTION_IS_ORIENTATION_CHANGE = "isOrientationChange";
    public static final String ACTION_PACKAGE = "packagename";
    public static final String ACTION_TYPE = "type";
    private static final String DB_GAME_SCENE = "nubia_game_scene";
    private static boolean DEBUG = true;
    private static final int MSG_CLOSE_TGK_FUNCTION = 13;
    private static final int MSG_CLOSE_TGK_VIEW = 11;
    private static final int MSG_DISABLED_CASE_SHOW_VIEW = 6;
    private static final int MSG_DISABLE_TGK = 3;
    private static final int MSG_DISMISS_TGK_GUIDE_VIEW = 10;
    private static final int MSG_DISMISS_VIEW = 1;
    private static final int MSG_DISMISS_VIEW_FROM_BTN = 5;
    private static final int MSG_ENABLE_TGK = 2;
    private static final int MSG_HIDE_TGK_CASE_VIEW = 8;
    private static final int MSG_NOTIFY_IME_STATE = 14;
    private static final int MSG_OPEN_TGK_FUNCTION = 12;
    private static final int MSG_REQUEST_COLOR_FUL_LIGHT = 100;
    private static final int MSG_SHOW_TGK_CASE_VIEW = 7;
    private static final int MSG_SHOW_TGK_GUIDE_VIEW = 9;
    private static final int MSG_SHOW_TOAST = 4;
    private static final int MSG_SHOW_VIEW = 0;
    private static final int MSG_STOP_SERVICE = 1000;
    public static final String TAG = "TgkService";
    private Context mContext;
    private float mDownPointX;
    private float mDownPointY;
    private long mEventEndTime;
    private long mEventMoveTime;
    private long mEventStartTime;
    private Handler mHandler;
    private InputManagerProxy mInputManagerProxy;
    private float mLastMovePointX;
    private float mLastMovePointY;
    private int mTgkCaseShowViewHeight;
    private int mTgkCaseShowViewWidth;
    private Rect mTgkCaseViewBottonRect;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private TgkMapView mTgkMapView = null;
    private TgkGuideView mTgkGuideView = null;
    private Handler mWorkHandler = null;
    private HandlerThread mWorkHandlerThread = null;
    private volatile TgkCaseShowView mTgkCaseShowView = null;
    private volatile TgkCaseListShowView mTgkCaseListShowView = null;
    private volatile TgkGameInfo mTgkGameInfo = null;
    private String mPackageName = null;
    private final Object mTgkCaseShowViewLock = new Object();
    private final Object mTgkCaseListShowViewLock = new Object();
    private int mIsLandscape = 0;
    private TgkGuideView.ITgkGuideViewClickListener mTgkGuideViewCloseListener = new TgkGuideView.ITgkGuideViewClickListener() { // from class: cn.nubia.tgk.TgkService.1
        @Override // cn.nubia.tgk.TgkGuideView.ITgkGuideViewClickListener
        public void doClose() {
            Settings.System.putInt(TgkService.this.mContext.getContentResolver(), TgkHelper.TGK_GUIDE_USE_STATUS, 1);
            TgkService.this.dismissTgkGuideView();
            TgkService.this.mContext.sendBroadcast(new Intent(TgkHelper.ACTION_GAME_DUAL));
            TgkService.this.sendMsgDelayed(1000, (Object) null, 1000);
        }
    };
    private TgkMapView.ITgkMapViewClickListener mViewCloseListener = new TgkMapView.ITgkMapViewClickListener() { // from class: cn.nubia.tgk.TgkService.2
        @Override // cn.nubia.tgk.TgkMapView.ITgkMapViewClickListener
        public void doClose(TgkGameInfo tgkGameInfo) {
            TgkService.this.sendMsgDelayed(5, tgkGameInfo != null ? tgkGameInfo.m339clone() : null, 0);
        }

        @Override // cn.nubia.tgk.TgkMapView.ITgkMapViewClickListener
        public void requestColorfulLight(int i) {
            if (TgkService.this.mWorkHandler != null) {
                Message obtainMessage = TgkService.this.mWorkHandler.obtainMessage();
                obtainMessage.what = 100;
                obtainMessage.arg1 = i;
                TgkService.this.mWorkHandler.sendMessage(obtainMessage);
            }
        }

        @Override // cn.nubia.tgk.TgkMapView.ITgkMapViewClickListener
        public void showHelp() {
            TgkService.this.dismissViewForCloseBtn();
            TgkService.this.removeMessages(4);
            TgkService.this.removeMessages(7);
            TgkService.this.sendMsgDelayed(8, (Object) null, 0);
            TgkService.this.sendMsgDelayed(9, (Object) null, 0);
        }

        @Override // cn.nubia.tgk.TgkMapView.ITgkMapViewClickListener
        public void showToast(String str) {
            TgkService.this.sendMsgDelayed(4, str, 0);
        }
    };
    private TgkShowCaseListViewAdapter.onDataChangeListener tgkCaseListViewAdapterListener = new TgkShowCaseListViewAdapter.onDataChangeListener() { // from class: cn.nubia.tgk.TgkService.3
        @Override // cn.nubia.tgk.widget.TgkShowCaseListViewAdapter.onDataChangeListener
        public void onChanged(int i, int i2) {
            if (TgkService.this.mTgkGameInfo == null || TgkService.this.mTgkGameInfo.getSelectedCaseData() == null) {
                return;
            }
            TgkService.this.mTgkGameInfo.selectedTableId = i;
            TgkService.this.mTgkGameInfo.selectedCasePosition = i2;
            TgkService.this.tgkCaseListPopViewScrollToPosition(i, i2);
            if (TgkService.this.mTgkCaseShowView != null) {
                TgkService.this.mTgkCaseShowView.setTgkCaseTvTitle(TgkService.this.mTgkGameInfo.getSelectedCaseData().showName);
            }
            TgkHelper.setTgkParaToNative(TgkService.this.mInputManagerProxy, TgkService.this.mTgkGameInfo.getSelectedCaseData(), TgkService.this.mTgkGameInfo, TgkService.this.mContext.getResources().getConfiguration().orientation == 2 ? 1 : 0);
            TgkHelper.reportClickTgkCaseButton(TgkService.this.mContext, TgkService.this.mTgkGameInfo.getSelectedCaseData().packageName);
        }

        @Override // cn.nubia.tgk.widget.TgkShowCaseListViewAdapter.onDataChangeListener
        public void onTgkDataChanged(int i, TgkData tgkData) {
            UiAsyncTask uiAsyncTask = new UiAsyncTask(TgkService.this);
            uiAsyncTask.setTableData(i, tgkData);
            uiAsyncTask.execute(new Void[0]);
        }
    };
    private final ContentObserver mNubiaGameSceneObserver = new ContentObserver(this.mWorkHandler) { // from class: cn.nubia.tgk.TgkService.5
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            int i = Settings.Global.getInt(TgkService.this.mContext.getContentResolver(), "nubia_game_scene", 0);
            Log.i(TgkService.TAG, "mNubiaGameSceneObserver: gameScene = " + i);
            if (i == 0) {
                TgkService tgkService = TgkService.this;
                tgkService.sendMsgDelayed(11, tgkService.mPackageName, 0);
            }
        }
    };
    private final ContentObserver mNotifyShowSoftInput = new ContentObserver(this.mWorkHandler) { // from class: cn.nubia.tgk.TgkService.6
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            int i = Settings.Global.getInt(TgkService.this.mContext.getContentResolver(), TgkHelper.NOTIFY_SHOW_SOFT_INPUT, 0);
            Log.i(TgkService.TAG, "mNotifyShowSoftInput: state = " + i);
            TgkService.this.sendMsgDelayed(14, i, i == 1 ? 100 : 50);
        }
    };

    private static class DismissViewAsyncTask extends AsyncTask<Long, Integer, Integer> {
        private TgkGameInfo mTgkGameInfo;
        private WeakReference<TgkService> tgkServiceWeakReference;

        protected DismissViewAsyncTask(TgkService tgkService) {
            this.tgkServiceWeakReference = new WeakReference<>(tgkService);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Long... lArr) {
            TgkGameInfo tgkGameInfo;
            TgkService tgkService = this.tgkServiceWeakReference.get();
            if (tgkService != null && (tgkGameInfo = this.mTgkGameInfo) != null) {
                if (tgkGameInfo.getSelectedCaseData() != null) {
                    String str = this.mTgkGameInfo.getSelectedCaseData().uniqueId;
                    Log.i(TgkService.TAG, "mTgkGameInfo.getSelectedCaseData() = " + this.mTgkGameInfo.getSelectedCaseData());
                    this.mTgkGameInfo.getSelectedCaseData().setIsLandscape(this.mTgkGameInfo.getIsLandscape());
                    String genUniqueId = TgkUtils.genUniqueId(this.mTgkGameInfo.getSelectedCaseData());
                    Log.i(TgkService.TAG, "DismissViewAsyncTask oldUniqueId =" + str + ";newUniqueId=" + genUniqueId);
                    if (!TextUtils.isEmpty(genUniqueId) && !genUniqueId.equals(str)) {
                        this.mTgkGameInfo.getSelectedCaseData().uniqueId = genUniqueId;
                        this.mTgkGameInfo.getSelectedCaseData().change = 1;
                        this.mTgkGameInfo.getSelectedCaseData().updateTime = System.currentTimeMillis();
                        String str2 = this.mTgkGameInfo.getSelectedCaseData().shotPicture;
                        this.mTgkGameInfo.getSelectedCaseData().shotPicture = TgkFileHelper.getBgPreviewPictureFilePath(tgkService.getApplicationContext(), str2);
                        Log.i(TgkService.TAG, "DismissViewAsyncTask oldShotPicture =" + str2 + ";newShotPicture=" + this.mTgkGameInfo.getSelectedCaseData().shotPicture);
                    }
                }
                TgkHelper.updateGameInfo(tgkService.getApplicationContext().getContentResolver(), this.mTgkGameInfo);
                if (this.mTgkGameInfo.getSelectedCaseData().mainSw) {
                    TgkHelper.reportTgkData(tgkService.getApplicationContext(), this.mTgkGameInfo);
                    TgkHelper.reportClickMoreButton(tgkService.getApplicationContext(), this.mTgkGameInfo.gameName);
                    if (TgkHelper.isSmallWindowMode(tgkService.getApplicationContext())) {
                        TgkHelper.disableTgkMap(tgkService.getApplicationContext(), this.mTgkGameInfo.gameName);
                    } else {
                        tgkService.mTgkGameInfo = this.mTgkGameInfo;
                        Log.i(TgkService.TAG, "DismissViewAsyncTask mPackageName =" + tgkService.mPackageName + ";mTgkGameInfo.gameName =" + this.mTgkGameInfo.gameName);
                        if (!TextUtils.isEmpty(tgkService.mPackageName) && tgkService.mPackageName.equals(this.mTgkGameInfo.gameName) && TgkHelper.getTgkCaseShowStates(tgkService.getApplicationContext(), this.mTgkGameInfo.gameName)) {
                            tgkService.sendMsgDelayed(7, this.mTgkGameInfo.gameName, 0);
                        }
                    }
                }
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Integer num) {
            TgkService tgkService = this.tgkServiceWeakReference.get();
            if (tgkService != null) {
                Log.i(TgkService.TAG, "MSG_DISMISS_VIEW_FROM_BTN onPostExecute!");
                tgkService.dismissViewForCloseBtn();
                tgkService.sendMsgDelayed(1000, (Object) null, 1000);
            }
        }

        public void setTgkGameInfo(TgkGameInfo tgkGameInfo) {
            this.mTgkGameInfo = tgkGameInfo;
        }
    }

    private static class UiAsyncTask extends AsyncTask<Void, Void, Void> {
        private int mTableId;
        private TgkData mTgkData;
        private WeakReference<TgkService> tgkServiceWeakReference;

        protected UiAsyncTask(TgkService tgkService) {
            this.tgkServiceWeakReference = new WeakReference<>(tgkService);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            TgkService tgkService = this.tgkServiceWeakReference.get();
            if (tgkService == null) {
                return null;
            }
            TgkHelper.updateTgkCaseExceptPicture(tgkService.mContext.getContentResolver(), this.mTableId, this.mTgkData);
            return null;
        }

        public void setTableData(int i, TgkData tgkData) {
            this.mTableId = i;
            this.mTgkData = tgkData;
        }
    }

    private class WorkHandler extends Handler {
        public WorkHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (TgkService.DEBUG) {
                Log.e(TgkService.TAG, "MSG msg.what = " + message.what + ", msg.obj=" + message.obj);
            }
            int i = message.what;
            if (i == 100) {
                TgkHelper.requestColorfulLight(message.arg1);
            } else if (i != 1000) {
                switch (i) {
                    case 0:
                        int i2 = TgkService.this.mContext.getResources().getConfiguration().orientation;
                        Log.d(TgkService.TAG, "orientation=" + i2);
                        if (!TgkFeatureUtil.isTgkSupportPortrait().booleanValue() && i2 != 2) {
                            TgkService tgkService = TgkService.this;
                            tgkService.showToast(tgkService.getApplicationContext().getResources().getString(R.string.nubia_hand_shank_touch_key_toast));
                            TgkService.this.sendMsgDelayed(1000, (Object) null, 5000);
                            break;
                        } else if (!Settings.canDrawOverlays(TgkService.this.getApplicationContext())) {
                            TgkService tgkService2 = TgkService.this;
                            tgkService2.showToast(tgkService2.getApplicationContext().getResources().getString(R.string.floating_permission_dialog_message));
                            TgkService.this.sendMsgDelayed(1000, (Object) null, 2000);
                            break;
                        } else {
                            if (TgkService.this.mHandler != null) {
                                TgkService.this.mHandler.removeMessages(7);
                            }
                            TgkService.this.sendMsgDelayed(8, (Object) null, 0);
                            if (!TgkHelper.disableTgkFunction((String) message.obj)) {
                                if (TgkService.this.mTgkMapView == null) {
                                    String str = (String) message.obj;
                                    TgkService.this.mTgkMapView = new TgkMapView(TgkService.this.getApplicationContext());
                                    TgkService.this.mTgkMapView.initTgkMapView(str);
                                    TgkService.this.mWindowManager.addView(TgkService.this.mTgkMapView, TgkService.this.getViewPopParam());
                                    TgkService.this.mTgkMapView.setFocusableInTouchMode(true);
                                    TgkService.this.mTgkMapView.requestFocus();
                                    TgkService.this.mTgkMapView.setSystemUiVisibility(2);
                                    TgkService.this.mTgkMapView.setCloseListener(TgkService.this.mViewCloseListener);
                                    break;
                                } else {
                                    Log.d(TgkService.TAG, "tgk setting view is show.");
                                    break;
                                }
                            } else {
                                Log.d(TgkService.TAG, "tgk setting disableTgkFunction.");
                                break;
                            }
                        }
                    case 1:
                        TgkService.this.dismissView(true);
                        TgkService.this.sendMsgDelayed(1000, (Object) null, 1000);
                        break;
                    case 2:
                        TgkService.this.enableTgkMap((String) message.obj, message.arg1);
                        break;
                    case 3:
                        TgkService.this.dismissTgkGuideView();
                        TgkService.this.dismissView(true);
                        TgkService.this.disableTgkMap((String) message.obj);
                        break;
                    case 4:
                        TgkService.this.showToast((String) message.obj);
                        break;
                    case 5:
                        if (message.obj != null) {
                            DismissViewAsyncTask dismissViewAsyncTask = new DismissViewAsyncTask(TgkService.this);
                            dismissViewAsyncTask.setTgkGameInfo((TgkGameInfo) message.obj);
                            dismissViewAsyncTask.execute(new Long[0]);
                            break;
                        }
                        break;
                    case 6:
                        if (TgkService.this.mTgkCaseShowView != null && TgkService.this.mTgkCaseListShowView == null) {
                            TgkService.this.mTgkCaseShowView.setTgkCaseNormal(false);
                            break;
                        }
                        break;
                    case 7:
                        TgkService.this.showTgkCaseView((String) message.obj);
                        break;
                    case 8:
                        TgkService.this.hideTgkCaseView();
                        break;
                    case 9:
                        if (!Settings.canDrawOverlays(TgkService.this.getApplicationContext())) {
                            TgkService tgkService3 = TgkService.this;
                            tgkService3.showToast(tgkService3.getApplicationContext().getResources().getString(R.string.floating_permission_dialog_message));
                            TgkService.this.sendMsgDelayed(1000, (Object) null, 2000);
                            break;
                        } else if (TgkService.this.mTgkGuideView == null) {
                            TgkService.this.mTgkGuideView = new TgkGuideView(TgkService.this.getApplicationContext());
                            TgkService.this.mWindowManager.addView(TgkService.this.mTgkGuideView, TgkService.this.getTgkGuideViewPopParam());
                            TgkService.this.mTgkGuideView.setFocusableInTouchMode(true);
                            TgkService.this.mTgkGuideView.requestFocus();
                            TgkService.this.mTgkGuideView.setSystemUiVisibility(2);
                            TgkService.this.mTgkGuideView.setCloseListener(TgkService.this.mTgkGuideViewCloseListener);
                            break;
                        } else {
                            Log.d(TgkService.TAG, "tgk guide view is show.");
                            break;
                        }
                    case 10:
                        TgkService.this.dismissTgkGuideView();
                        TgkService.this.hideTgkCaseView();
                        TgkService.this.sendMsgDelayed(1000, (Object) null, 1000);
                        break;
                    case 11:
                        TgkService.this.dismissTgkGuideView();
                        TgkService.this.dismissView(true);
                        removeMessages(4);
                        removeMessages(7);
                        TgkService.this.sendMsgDelayed(8, (Object) null, 0);
                        TgkService.this.sendMsgDelayed(1000, (Object) null, 5000);
                        break;
                    case 12:
                        TgkService.this.openTgkMap((String) message.obj);
                        break;
                    case 13:
                        TgkService.this.closeTgkMap((String) message.obj);
                        break;
                    case 14:
                        TgkService.this.updateRenameView(message.arg1 == 1);
                        break;
                    default:
                        if (TgkService.this.mTgkMapView == null && TgkService.this.mHandler == null) {
                            TgkService.this.stopSelf();
                            break;
                        }
                        break;
                }
            } else {
                TgkService.this.stopService();
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeTgkMap(String str) {
        dismissTgkGuideView();
        dismissView(true);
        Log.i(TAG, "closeTgkMap packageName= " + str);
        try {
            TgkHelper.disableTgkMap(getApplicationContext(), str, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        removeMessages(4);
        removeMessages(7);
        sendMsgDelayed(8, (Object) null, 0);
        sendMsgDelayed(1000, (Object) null, 2000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableTgkMap(String str) {
        if (DEBUG) {
            Log.i(TAG, "disableTgkMap");
        }
        try {
            TgkHelper.disableTgkMap(getApplicationContext(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        removeMessages(4);
        removeMessages(7);
        sendMsgDelayed(8, (Object) null, 0);
        sendMsgDelayed(1000, (Object) null, 2000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissTgkGuideView() {
        TgkGuideView tgkGuideView = this.mTgkGuideView;
        if (tgkGuideView != null) {
            this.mWindowManager.removeView(tgkGuideView);
            this.mTgkGuideView = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView(boolean z) {
        TgkMapView tgkMapView = this.mTgkMapView;
        if (tgkMapView != null) {
            tgkMapView.dismiss(z);
            this.mWindowManager.removeView(this.mTgkMapView);
            this.mTgkMapView = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissViewForCloseBtn() {
        TgkMapView tgkMapView = this.mTgkMapView;
        if (tgkMapView != null) {
            this.mWindowManager.removeView(tgkMapView);
            this.mTgkMapView = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableTgkMap(String str, int i) {
        if (DEBUG) {
            Log.i(TAG, "enableTgkMap isOrientationChangeFlge=" + i);
        }
        int i2 = this.mContext.getResources().getConfiguration().orientation == 2 ? 1 : 0;
        this.mTgkGameInfo = TgkHelper.enableTgkMap(getApplicationContext(), str, i2);
        TgkData selectedCaseData = this.mTgkGameInfo.getSelectedCaseData();
        if (selectedCaseData == null || !selectedCaseData.mainSw) {
            removeMessages(4);
            removeMessages(7);
            sendMsgDelayed(8, (Object) null, 0);
            sendMsgDelayed(1000, (Object) null, 1000);
            return;
        }
        if (!TgkFeatureUtil.isTgkSupportPortrait().booleanValue()) {
            showToastAndCaseView(str, i == 0);
            return;
        }
        if (!TgkFeatureUtil.isSupportTgkPortraitLandscapeEnable().booleanValue()) {
            showToastAndCaseView(str, i == 0);
            return;
        }
        if (selectedCaseData.getIsLandscape() == i2) {
            showToastAndCaseView(str, true);
            return;
        }
        removeMessages(4);
        removeMessages(7);
        sendMsgDelayed(8, (Object) null, 0);
        sendMsgDelayed(1000, (Object) null, 1000);
    }

    private WindowManager.LayoutParams getTgkCaseListPopViewAParam(int i, int i2, int i3, int i4) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.setTitle("TgkCaseListPopView");
        layoutParams.flags = 263976;
        layoutParams.screenOrientation = 3;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.gravity = 51;
        layoutParams.x = i;
        layoutParams.y = i2;
        layoutParams.width = i3;
        layoutParams.height = i4;
        layoutParams.format = 1;
        return layoutParams;
    }

    private WindowManager.LayoutParams getTgkCaseViewPopParam(String str) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.setTitle("TgkCaseViewPopView1");
        layoutParams.type = PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE;
        layoutParams.flags = 40;
        layoutParams.screenOrientation = 3;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.gravity = 51;
        int[] tgkCaseViewPostion = TgkHelper.getTgkCaseViewPostion(this.mContext, str);
        int i = tgkCaseViewPostion[0];
        if (i == 0 && tgkCaseViewPostion[1] == 0) {
            layoutParams.x = getApplication().getResources().getDimensionPixelSize(R.dimen.tgk_case_show_view_margin_left);
            layoutParams.y = getApplication().getResources().getDimensionPixelSize(R.dimen.tgk_case_show_view_margin_top);
        } else {
            layoutParams.x = i;
            layoutParams.y = tgkCaseViewPostion[1];
        }
        layoutParams.width = this.mTgkCaseShowViewWidth;
        layoutParams.height = this.mTgkCaseShowViewHeight;
        layoutParams.format = -2;
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowManager.LayoutParams getTgkGuideViewPopParam() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.setTitle("TgkGuideDialog");
        layoutParams.type = 2038;
        layoutParams.flags = 263968;
        layoutParams.screenOrientation = 3;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.gravity = 51;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.format = 1;
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowManager.LayoutParams getViewPopParam() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.setTitle("touchgamekeyv4");
        layoutParams.type = 2038;
        layoutParams.flags = 263968;
        layoutParams.screenOrientation = 3;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.gravity = 51;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.format = 1;
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleActionMove(MotionEvent motionEvent, View view, int i) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    this.mEventMoveTime = System.currentTimeMillis();
                    this.mLastMovePointX = motionEvent.getRawX();
                    this.mLastMovePointY = motionEvent.getRawY();
                    if (this.mEventMoveTime - this.mEventStartTime > 150) {
                        updateViewPosition(view, i);
                    }
                } else if (action != 3) {
                    if (action == 4) {
                        this.mHandler.removeMessages(6);
                        sendMsgDelayed(6, (Object) null, 5000);
                        hideTgkCaseListPopView();
                    }
                }
            }
            this.mEventEndTime = System.currentTimeMillis();
            this.mLastMovePointX = motionEvent.getRawX();
            this.mLastMovePointY = motionEvent.getRawY();
            if (this.mEventEndTime - this.mEventStartTime <= 150) {
                WindowManager.LayoutParams layoutParams = this.mWindowParams;
                if (layoutParams != null) {
                    if (isInView(this.mTgkCaseViewBottonRect, layoutParams.x, this.mWindowParams.y, (int) this.mLastMovePointX, (int) this.mLastMovePointY)) {
                        onTgkCaseViewBottonClick(i);
                    } else {
                        this.mHandler.removeMessages(6);
                        sendMsgDelayed(6, (Object) null, 5000);
                    }
                }
            } else {
                updateViewPosition(view, i);
                this.mHandler.removeMessages(6);
                sendMsgDelayed(6, (Object) null, 5000);
            }
        } else {
            this.mEventStartTime = System.currentTimeMillis();
            this.mDownPointX = motionEvent.getX();
            this.mDownPointY = motionEvent.getY();
            if (this.mTgkCaseShowView != null) {
                this.mTgkCaseViewBottonRect = new Rect(0, 0, 0, 0);
                if (this.mTgkCaseShowView != null) {
                    this.mTgkCaseShowView.getGlobalVisibleRect(this.mTgkCaseViewBottonRect);
                }
            }
            this.mHandler.removeMessages(6);
            this.mTgkCaseShowView.setTgkCaseNormal(true);
        }
        return true;
    }

    private void hideTgkCaseListPopView() {
        Log.d(TAG, "hideTgkCaseListShowView");
        if (this.mTgkCaseShowView != null) {
            this.mTgkCaseShowView.setTgkCaseStates(false);
        }
        synchronized (this.mTgkCaseListShowViewLock) {
            if (this.mTgkCaseListShowView != null) {
                this.mWindowManager.removeView(this.mTgkCaseListShowView);
                this.mTgkCaseListShowView = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTgkCaseView() {
        Log.d(TAG, "hideTgkCaseView");
        hideTgkCaseListPopView();
        synchronized (this.mTgkCaseShowViewLock) {
            if (this.mTgkCaseShowView != null) {
                Log.d(TAG, "hideTgkCaseView mTgkCaseShowView != null");
                try {
                    if (this.mWindowParams != null && this.mTgkGameInfo != null) {
                        TgkHelper.setTgkCaseViewPostion(this.mContext, this.mTgkGameInfo.gameName, new int[]{this.mWindowParams.x, this.mWindowParams.y});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.mWindowManager.removeView(this.mTgkCaseShowView);
                this.mTgkCaseShowView = null;
            }
        }
    }

    private boolean isInView(Rect rect, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = i3 - i;
        return i6 > rect.left && i6 < rect.right && (i5 = i4 - i2) > rect.top && i5 < rect.bottom;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openTgkMap(String str) {
        Log.i(TAG, "openTgkMap packageName= " + str);
        int i = this.mContext.getResources().getConfiguration().orientation;
        Log.d(TAG, "orientation=" + i);
        if (TgkFeatureUtil.isTgkSupportPortrait().booleanValue() || i == 2) {
            int i2 = i == 2 ? 1 : 0;
            this.mTgkGameInfo = TgkHelper.enableTgkMap(getApplicationContext(), str, true, i2);
            TgkData selectedCaseData = this.mTgkGameInfo.getSelectedCaseData();
            if (TgkFeatureUtil.isTgkSupportPortrait().booleanValue()) {
                if (!TgkFeatureUtil.isSupportTgkPortraitLandscapeEnable().booleanValue()) {
                    showToastAndCaseView(str, true);
                    return;
                } else if (selectedCaseData.getIsLandscape() == i2) {
                    showToastAndCaseView(str, true);
                    return;
                } else {
                    sendMsgDelayed(1000, (Object) null, PathInterpolatorCompat.MAX_NUM_POINTS);
                    return;
                }
            }
            if (i != 2) {
                showToast(getApplicationContext().getResources().getString(R.string.nubia_hand_shank_touch_key_toast));
                sendMsgDelayed(1000, (Object) null, 5000);
            } else if (selectedCaseData != null) {
                showToastAndCaseView(str, true);
            }
        }
    }

    private void registerSettingsObserver() {
        try {
            Log.d(TAG, "registerSettingsObserver: ");
            this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("nubia_game_scene"), false, this.mNubiaGameSceneObserver);
            this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor(TgkHelper.NOTIFY_SHOW_SOFT_INPUT), false, this.mNotifyShowSoftInput);
        } catch (Exception e) {
            Log.d(TAG, "registerSettingsObserver: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeMessages(int i) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgDelayed(int i, int i2, int i3) {
        this.mHandler.removeMessages(1000);
        this.mHandler.removeMessages(i);
        Message obtainMessage = this.mHandler.obtainMessage(i);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessageDelayed(obtainMessage, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgDelayed(int i, Object obj, int i2) {
        this.mHandler.removeMessages(1000);
        this.mHandler.removeMessages(i);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(i, obj), i2);
    }

    private void sendMsgDelayed(int i, Object obj, int i2, int i3) {
        this.mHandler.removeMessages(1000);
        this.mHandler.removeMessages(i);
        Message obtainMessage = this.mHandler.obtainMessage(i, obj);
        obtainMessage.arg1 = i3;
        this.mHandler.sendMessageDelayed(obtainMessage, i2);
    }

    private void showTgkCaseListShowView(int i) {
        Log.d(TAG, "showTgkCaseListShowView");
        this.mTgkCaseShowView.setTgkCaseStates(true);
        if (this.mTgkGameInfo != null) {
            synchronized (this.mTgkCaseListShowViewLock) {
                if (this.mTgkCaseListShowView == null && this.mWindowParams != null) {
                    this.mTgkCaseListShowView = new TgkCaseListShowView(getApplicationContext());
                    TgkShowCaseListViewAdapter tgkShowCaseListViewAdapter = new TgkShowCaseListViewAdapter(this.mContext, this.mTgkGameInfo.presetTableList, this.mTgkGameInfo.importTableList);
                    tgkShowCaseListViewAdapter.setListener(this.tgkCaseListViewAdapterListener);
                    this.mTgkCaseListShowView.setAdapter(tgkShowCaseListViewAdapter);
                    int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.tgk_case_show_view_margin_right);
                    int i2 = this.mWindowParams.x + this.mWindowParams.width + dimensionPixelSize;
                    int i3 = this.mWindowParams.y;
                    int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.tgk_case_list_view_width);
                    int dimensionPixelSize3 = this.mContext.getResources().getDimensionPixelSize(R.dimen.tgk_case_list_view_height);
                    if (i2 + dimensionPixelSize2 > (i == 1 ? TgkHelper.mScreenWidthInLandscape : TgkHelper.mScreenHeightInLandscape)) {
                        i2 = (this.mWindowParams.x - dimensionPixelSize2) - dimensionPixelSize;
                    }
                    if (i3 + dimensionPixelSize3 > (i == 1 ? TgkHelper.mScreenHeightInLandscape : TgkHelper.mScreenWidthInLandscape)) {
                        i3 = (this.mWindowParams.y - dimensionPixelSize3) + this.mWindowParams.height;
                    }
                    this.mWindowManager.addView(this.mTgkCaseListShowView, getTgkCaseListPopViewAParam(i2, i3, dimensionPixelSize2, dimensionPixelSize3));
                    tgkCaseListPopViewScrollToPosition(this.mTgkGameInfo.selectedTableId, this.mTgkGameInfo.selectedCasePosition);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTgkCaseView(String str) {
        this.mIsLandscape = this.mContext.getResources().getConfiguration().orientation == 2 ? 1 : 0;
        Log.d(TAG, "showTgkCaseView mIsLandscape =" + this.mIsLandscape);
        if (this.mTgkGameInfo == null || this.mTgkGameInfo.getSelectedCaseData() == null) {
            return;
        }
        if (!Settings.canDrawOverlays(getApplicationContext())) {
            showToast(getApplicationContext().getResources().getString(R.string.floating_permission_dialog_message));
            sendMsgDelayed(1000, (Object) null, 2000);
            return;
        }
        synchronized (this.mTgkCaseShowViewLock) {
            if (this.mTgkCaseShowView == null) {
                this.mTgkCaseShowView = new TgkCaseShowView(getApplicationContext());
                this.mTgkCaseShowView.setTgkCaseTvTitle(this.mTgkGameInfo.getSelectedCaseData().showName);
                this.mWindowParams = getTgkCaseViewPopParam(str);
                this.mWindowManager.addView(this.mTgkCaseShowView, this.mWindowParams);
                this.mTgkCaseShowView.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.tgk.TgkService.4
                    @Override // android.view.View.OnTouchListener
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        TgkService tgkService = TgkService.this;
                        return tgkService.handleActionMove(motionEvent, tgkService.mTgkCaseShowView, TgkService.this.mIsLandscape);
                    }
                });
            }
        }
        this.mHandler.removeMessages(6);
        sendMsgDelayed(6, (Object) null, 5000);
    }

    private void showToastAndCaseView(String str, boolean z) {
        if (z) {
            sendMsgDelayed(4, getApplicationContext().getResources().getString(R.string.tgk_function_enable), HighLightsUtils.RESET_DELAY_TIME);
        }
        if (TgkHelper.getTgkCaseShowStates(getApplicationContext(), str)) {
            sendMsgDelayed(7, str, HighLightsUtils.RESET_DELAY_TIME);
            return;
        }
        removeMessages(7);
        sendMsgDelayed(8, (Object) null, 0);
        sendMsgDelayed(1000, (Object) null, 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopService() {
        dismissView(true);
        if (this.mTgkGameInfo != null && this.mTgkCaseShowView != null) {
            Log.d(TAG, "show TgkCaseView not stopService!");
            return;
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(7);
        }
        hideTgkCaseView();
        stopSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tgkCaseListPopViewScrollToPosition(int i, int i2) {
        int i3 = (i * 5) + i2;
        if (this.mTgkCaseListShowView != null) {
            this.mTgkCaseListShowView.scrollToPositionWithOffset(i3);
        }
    }

    private void unRegisterSettingsObserver() {
        try {
            Log.d(TAG, "unRegisterSettingsObserver: ");
            this.mContext.getContentResolver().unregisterContentObserver(this.mNubiaGameSceneObserver);
            this.mContext.getContentResolver().unregisterContentObserver(this.mNotifyShowSoftInput);
        } catch (Exception e) {
            Log.d(TAG, "unRegisterSettingsObserver: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRenameView(boolean z) {
        TgkMapView tgkMapView = this.mTgkMapView;
        if (tgkMapView != null) {
            tgkMapView.updateRenameView(z);
        }
    }

    private void updateViewPosition(View view, int i) {
        int i2 = i == 1 ? TgkHelper.mScreenWidthInLandscape : TgkHelper.mScreenHeightInLandscape;
        int i3 = i == 1 ? TgkHelper.mScreenHeightInLandscape : TgkHelper.mScreenWidthInLandscape;
        float f = this.mLastMovePointX;
        int i4 = this.mTgkCaseShowViewWidth;
        if (f > i2 - (i4 / 2)) {
            this.mLastMovePointX = i2 - (i4 / 2);
        }
        float f2 = this.mLastMovePointY;
        int i5 = this.mTgkCaseShowViewHeight;
        if (f2 > i3 - (i5 / 2)) {
            this.mLastMovePointY = i3 - (i5 / 2);
        }
        if (this.mLastMovePointX < i4 / 2) {
            this.mLastMovePointX = i4 / 2;
        }
        if (this.mLastMovePointY < i5 / 2) {
            this.mLastMovePointY = i5 / 2;
        }
        this.mWindowParams.x = (int) (this.mLastMovePointX - (i4 / 2));
        this.mWindowParams.y = (int) (this.mLastMovePointY - (this.mTgkCaseShowViewHeight / 2));
        if (view == null || this.mWindowManager == null) {
            return;
        }
        hideTgkCaseListPopView();
        this.mWindowManager.updateViewLayout(view, this.mWindowParams);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.mWindowManager = (WindowManager) getSystemService("window");
        this.mHandler = new WorkHandler(getMainLooper());
        Context applicationContext = getApplicationContext();
        this.mContext = applicationContext;
        TgkHelper.getScreenSize(applicationContext);
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mWorkHandlerThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new WorkHandler(this.mWorkHandlerThread.getLooper());
        this.mInputManagerProxy = new InputManagerProxy(this.mContext);
        TgkHelper.reportDataInit(this.mContext);
        this.mTgkCaseShowViewWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.tgk_case_show_view_width);
        this.mTgkCaseShowViewHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.tgk_case_show_view_height);
        registerSettingsObserver();
        TgkLampHelper.getShoulderSettings(this.mContext);
        TgkHelper.getIsSupportLampFunction(this.mContext);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (DEBUG) {
            Log.e(TAG, "onDestroy");
        }
        unRegisterSettingsObserver();
        HandlerThread handlerThread = this.mWorkHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mWorkHandler = null;
        }
        if (this.mWorkHandlerThread != null) {
            this.mWorkHandlerThread = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int i3 = -1;
        int i4 = 0;
        if (intent == null) {
            Log.e(TAG, "intent is null!");
        } else {
            int intExtra = intent.getIntExtra("type", -1);
            this.mPackageName = intent.getStringExtra("packagename");
            i4 = intent.getIntExtra(ACTION_IS_ORIENTATION_CHANGE, 0);
            if (!TextUtils.isEmpty(this.mPackageName)) {
                i3 = intExtra;
            }
        }
        sendMsgDelayed(i3, this.mPackageName, 20, i4);
        return super.onStartCommand(intent, i, i2);
    }

    public void onTgkCaseViewBottonClick(int i) {
        if (this.mTgkCaseShowView != null) {
            if (!this.mTgkCaseShowView.getIsNormal()) {
                this.mHandler.removeMessages(6);
                this.mTgkCaseShowView.setTgkCaseNormal(true);
                return;
            }
            this.mTgkCaseShowView.setIsswipe(!this.mTgkCaseShowView.getIsswipe());
            if (this.mTgkCaseShowView.getIsswipe()) {
                this.mHandler.removeMessages(6);
                showTgkCaseListShowView(i);
            } else {
                this.mHandler.removeMessages(6);
                sendMsgDelayed(6, (Object) null, 5000);
                hideTgkCaseListPopView();
            }
        }
    }

    public void showToast(String str) {
        ToastUtil.showTgkToast(this.mContext, str);
    }
}
