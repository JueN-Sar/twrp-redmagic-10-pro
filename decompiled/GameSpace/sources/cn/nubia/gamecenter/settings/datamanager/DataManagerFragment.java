package cn.nubia.gamecenter.settings.datamanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.arkbase.nbaccount.INbAccountLogin;
import cn.nubia.arkbase.nbaccount.INbAccountLoginCallback;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.BaseFragment;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.GcsAnimationUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.datamanager.DataManagerPieChartView;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.MarqueeTextView;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class DataManagerFragment extends BaseFragment implements FragmentInterface, View.OnClickListener, DataManagerPieChartView.IPieChartCallBack {
    private static final String ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME = "cn.nubia.arkbase.service.NbAccountLoginService";
    private static final String ARK_BASE_PACKAGE_NAME = "cn.nubia.arkbase";
    private static final int MSG_UPDATE_VIEW = 0;
    private static final String RECORD_ACTION = "cn.nubia.gamecenter.settings.action.GAME_CENTER_RADMAGICTIME_DETAIL";
    private static final String RECORD_PACKAGE_NAME = "package_name";
    private static final String TAG = "[DataManager]-Fragment";
    private float angleNoteBlue;
    private float angleNoteGray;
    private float angleNoteRed;
    private float angleNoteYellow;
    private float angleRecordBlue;
    private float angleRecordGray;
    private float angleRecordRed;
    private float angleRecordYellow;
    private View animationView;
    private int blueX;
    private int blueY;
    private MarqueeTextView columnPkgTx;
    private int conDensityDpi;
    private DataManagerColumnView dataManagerColumnView;
    private DataManagerPieChartView dataManagerPieChartView;
    private float density;
    private int densityDpi;
    private TextView gameNote;
    private View gameNoteDownIcon;
    private int grayX;
    private int grayY;
    private float mBottomValueTranslationY;
    private Context mContext;
    private boolean mIsTablet;
    private INbAccountLogin mNbAccountLoginImpl;
    private int mPieTextDistance;
    private boolean mResumed;
    private boolean noteData;
    private Map<String, Integer> notePkgMap;
    private List<Map<String, String>> notesMap;
    private MarqueeTextView picture;
    private TextView pictureValues;
    private MarqueeTextView pieBluePkgTx;
    private View pieChartBg;
    private MarqueeTextView pieGrayPkgTx;
    private MarqueeTextView pieRedPkgTx;
    private MarqueeTextView pieYellowPkgTx;
    private boolean recordData;
    private String recordPackage;
    private Map<String, Integer> recordPkgMap;
    private List<Map<String, String>> recordsMap;
    private TextView redMagicTime;
    private MarqueeTextView redMagicTimeChecked;
    private View redMagicTimeDownIcon;
    private int redX;
    private int redY;
    private float scaledDensity;
    private MarqueeTextView screenshot;
    private TextView screenshotValues;
    private TextView totalValues;
    private MarqueeTextView video;
    private TextView videoValues;
    private MarqueeTextView wording;
    private MarqueeTextView wordingPending;
    private TextView wordingPendingValues;
    private TextView wordingValues;
    private int yellowX;
    private int yellowY;
    private String REDMAGIC_TIME = "REDMAGIC_TIME";
    private String GAME_NOTE = "GAME_NOTE";
    private int recordLength = 0;
    private int noteLength = 0;
    private int recordTotalValue = 0;
    private int videoValue = 0;
    private int screenshotValue = 0;
    private int noteTotalValue = 0;
    private int pictureValue = 0;
    private int wordingValue = 0;
    private int wordingValuePending = 0;
    private int deathVideoTotal = 0;
    private int fullVideoTotal = 0;
    private int collectionVideoTotal = 0;
    private int momentVideoTotal = 0;
    private int manualVideoTotal = 0;
    private int pictureTotal = 0;
    private int textTotal = 0;
    private ArrayList<String> recordPkg = new ArrayList<>();
    private ArrayList<Integer> recordPkgvalue = new ArrayList<>();
    private ArrayList<String> notePkg = new ArrayList<>();
    private ArrayList<Integer> notePkgvalue = new ArrayList<>();
    private String pageType = "REDMAGIC_TIME";
    private final ServiceConnection mArkBaseServiceConnection = new ServiceConnection() { // from class: cn.nubia.gamecenter.settings.datamanager.DataManagerFragment.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.i(DataManagerFragment.TAG, "onServiceConnected");
            try {
                DataManagerFragment.this.mNbAccountLoginImpl = INbAccountLogin.Stub.asInterface(iBinder);
                DataManagerFragment.this.mNbAccountLoginImpl.registerCallback(DataManagerFragment.this.mNbAccountLoginCb);
                DataManagerFragment.this.loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.i(DataManagerFragment.TAG, "onServiceDisconnected");
            DataManagerFragment.this.mNbAccountLoginImpl = null;
        }
    };
    private final NbAccountLoginCallback mNbAccountLoginCb = new NbAccountLoginCallback(this);
    private Handler handler = new Handler() { // from class: cn.nubia.gamecenter.settings.datamanager.DataManagerFragment.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            DataManagerFragment dataManagerFragment = DataManagerFragment.this;
            dataManagerFragment.setCheckMode(dataManagerFragment.pageType);
        }
    };

    private static class NbAccountLoginCallback extends INbAccountLoginCallback.Stub {
        private final WeakReference<DataManagerFragment> mDataManagerFragment;

        public NbAccountLoginCallback(DataManagerFragment dataManagerFragment) {
            this.mDataManagerFragment = new WeakReference<>(dataManagerFragment);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onAccountInfo(Map map, Bitmap bitmap) {
            LogUtil.i(DataManagerFragment.TAG, "CwlonAccountInfo, accountInfo = " + map);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onAccountLabel(List list) {
            LogUtil.i(DataManagerFragment.TAG, "onAccountLabel, labelList = " + list);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onError(String str) {
            LogUtil.i(DataManagerFragment.TAG, "onError errorType:=" + str);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameHighLights(List list) {
            LogUtil.i(DataManagerFragment.TAG, "onGameHighLights, ********recordList = " + list);
            if (this.mDataManagerFragment.get() != null) {
                this.mDataManagerFragment.get().onGameHighLights(list);
            }
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameNotes(List list) {
            LogUtil.i(DataManagerFragment.TAG, "onGameNotes, ********recordList = " + list);
            if (this.mDataManagerFragment.get() != null) {
                this.mDataManagerFragment.get().onGameNotes(list);
            }
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGamePowers(List list) throws RemoteException {
            LogUtil.i(DataManagerFragment.TAG, "GamePowers, ********recordList = " + list);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameRecords(List list) {
            LogUtil.i(DataManagerFragment.TAG, "onGameRecords, recordList = " + list);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onGameScores(List list) throws RemoteException {
            LogUtil.i(DataManagerFragment.TAG, "GameScores, ********recordList = " + list);
        }

        @Override // cn.nubia.arkbase.nbaccount.INbAccountLoginCallback
        public void onSuccess(String str, Map map, Bitmap bitmap) {
            LogUtil.i(DataManagerFragment.TAG, "onSuccess code:=" + str + ", accountInfo = " + map + ", avatar = " + bitmap);
        }
    }

    private void bindArkBaseService() {
        LogUtil.i(TAG, "bindArkBaseService");
        if (this.mContext == null) {
            LogUtil.e(TAG, "bindArkBaseService, ignore !");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.arkbase", ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME);
        this.mContext.bindService(intent, this.mArkBaseServiceConnection, 1);
    }

    public static String extractNumber(String str) {
        if (str != null && !str.isEmpty()) {
            String[] split = str.split("@", 2);
            if (split.length > 1) {
                try {
                    Log.i(TAG, "cwl parts[1] = " + split[1]);
                    return split[1];
                } catch (NumberFormatException unused) {
                }
            }
        }
        return null;
    }

    private static String getAppName(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return "cn.nubia.gamenotes.fiber".equals(str) ? "纤维捕捉器" : str.contains("com.tencent.mm@") ? queryShortcuts(context, extractNumber(str)) : packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e(TAG, "Get app name failed!");
            return "-";
        }
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(DataManagerFragment.class, R.drawable.data_manager, R.string.gcs_gamecenter_menu_datamanager);
    }

    private void getPieTextCoordinate(float f, float f2, float f3, float f4) {
        float f5 = f + f2;
        int i = this.mPieTextDistance;
        double d = i;
        double d2 = (i * 2.0f) / 2.0f;
        double d3 = f * 0.5f;
        this.grayX = (int) (d + (d2 * Math.sin(Math.toRadians(d3))));
        this.grayY = (int) (this.mPieTextDistance - (((r10 * 2.0f) / 2.0f) * Math.cos(Math.toRadians(d3))));
        double d4 = (f2 * 0.5f) + f;
        this.yellowX = (int) (this.mPieTextDistance + (((r5 * 2.0f) / 2.0f) * Math.sin(Math.toRadians(d4))));
        this.yellowY = (int) (this.mPieTextDistance - (((r5 * 2.0f) / 2.0f) * Math.cos(Math.toRadians(d4))));
        double d5 = (f3 * 0.5f) + f5;
        this.blueX = (int) (this.mPieTextDistance + (((r5 * 2.0f) / 2.0f) * Math.sin(Math.toRadians(d5))));
        this.blueY = (int) (this.mPieTextDistance - (((r5 * 2.0f) / 2.0f) * Math.cos(Math.toRadians(d5))));
        double d6 = f5 + f3 + (0.5f * f4);
        this.redX = (int) (this.mPieTextDistance + (((r5 * 2.0f) / 2.0f) * Math.sin(Math.toRadians(d6))));
        this.redY = (int) (this.mPieTextDistance - (((r5 * 2.0f) / 2.0f) * Math.cos(Math.toRadians(d6))));
        LogUtil.i(TAG, "angleGray = " + f + "; angleYellow = " + f2 + "; angleBlue = " + f3 + "; angleRed = " + f4);
        LogUtil.i(TAG, "grayX = " + this.grayX + "| grayY = " + this.grayY + "| yellowX = " + this.yellowX + "| yellowY = " + this.yellowY + "| blueX = " + this.blueX + "| yellowY = " + this.yellowY + "| redX = " + this.grayX + "| redX = " + this.grayY);
        this.pieGrayPkgTx.setTranslationX(this.grayX);
        this.pieGrayPkgTx.setTranslationY(this.grayY);
        this.pieYellowPkgTx.setTranslationX(this.yellowX);
        this.pieYellowPkgTx.setTranslationY(this.yellowY);
        this.pieBluePkgTx.setTranslationX(this.blueX);
        this.pieBluePkgTx.setTranslationY(this.blueY);
        this.pieRedPkgTx.setTranslationX(this.redX);
        this.pieRedPkgTx.setTranslationY(this.redY);
    }

    private void initView(View view) {
        LogUtil.i(TAG, "initView");
        this.animationView = view.findViewById(R.id.total_layout);
        this.redMagicTime = (TextView) view.findViewById(R.id.datamanager_main_head_redmagic_time);
        this.gameNote = (TextView) view.findViewById(R.id.datamanager_main_head_gamenote);
        this.redMagicTimeDownIcon = view.findViewById(R.id.datamanager_main_head_redmagic_time_downicon);
        this.gameNoteDownIcon = view.findViewById(R.id.datamanager_main_head_gamenote_downicon);
        this.redMagicTimeChecked = (MarqueeTextView) view.findViewById(R.id.column_redmagic_time_check);
        this.pieChartBg = view.findViewById(R.id.piechart_background);
        DataManagerColumnView dataManagerColumnView = (DataManagerColumnView) view.findViewById(R.id.my_datamanager_columnview);
        this.dataManagerColumnView = dataManagerColumnView;
        dataManagerColumnView.setMode(this.REDMAGIC_TIME);
        DataManagerPieChartView dataManagerPieChartView = (DataManagerPieChartView) view.findViewById(R.id.my_datamanager_piechart);
        this.dataManagerPieChartView = dataManagerPieChartView;
        dataManagerPieChartView.setMode(this.REDMAGIC_TIME);
        this.dataManagerPieChartView.setCallBack(this);
        this.dataManagerPieChartView.setOnClickListener(this);
        this.redMagicTime.setOnClickListener(this);
        this.gameNote.setOnClickListener(this);
        this.redMagicTimeChecked.setOnClickListener(this);
        this.totalValues = (TextView) view.findViewById(R.id.total_value);
        this.video = (MarqueeTextView) view.findViewById(R.id.video);
        this.videoValues = (TextView) view.findViewById(R.id.video_value);
        this.screenshot = (MarqueeTextView) view.findViewById(R.id.screenshot);
        this.screenshotValues = (TextView) view.findViewById(R.id.screenshot_value);
        this.picture = (MarqueeTextView) view.findViewById(R.id.picture);
        this.pictureValues = (TextView) view.findViewById(R.id.picture_value);
        this.wording = (MarqueeTextView) view.findViewById(R.id.wording);
        this.wordingValues = (TextView) view.findViewById(R.id.wording_value);
        this.wordingPending = (MarqueeTextView) view.findViewById(R.id.wording_pending);
        this.wordingPendingValues = (TextView) view.findViewById(R.id.wording_pending_value);
        this.columnPkgTx = (MarqueeTextView) view.findViewById(R.id.column_head_app_text);
        this.pieGrayPkgTx = (MarqueeTextView) view.findViewById(R.id.pie_text_pkg_gray);
        this.pieYellowPkgTx = (MarqueeTextView) view.findViewById(R.id.pie_text_pkg_yellow);
        this.pieBluePkgTx = (MarqueeTextView) view.findViewById(R.id.pie_text_pkg_blue);
        this.pieRedPkgTx = (MarqueeTextView) view.findViewById(R.id.pie_text_pkg_red);
        if (this.mIsTablet) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.redMagicTimeChecked.getLayoutParams();
            layoutParams.horizontalBias = 0.8f;
            this.redMagicTimeChecked.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.columnPkgTx.getLayoutParams();
            layoutParams2.horizontalBias = 0.8f;
            this.columnPkgTx.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        try {
            INbAccountLogin iNbAccountLogin = this.mNbAccountLoginImpl;
            if (iNbAccountLogin != null) {
                iNbAccountLogin.loadGameNotes();
                this.mNbAccountLoginImpl.loadGameHighLights();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String queryShortcuts(Context context, String str) {
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(HighLightsUtils.URI_GAME_SPACE_SHORTCUT), null, null, null, null);
            String str2 = null;
            while (query.moveToNext()) {
                if (str.equals(query.getString(query.getColumnIndex("hashcode")))) {
                    str2 = query.getString(query.getColumnIndex("label"));
                }
            }
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void recordOnly() {
        if (FeatureUtil.getBoolean("ZTE_FEATURE_MANUAL_RECORD_ONLY", false).booleanValue()) {
            String str = this.GAME_NOTE;
            this.pageType = str;
            setCheckMode(str);
            this.redMagicTime.setVisibility(8);
            this.redMagicTimeDownIcon.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckMode(String str) {
        LogUtil.i(TAG, "CWL setCheckMode recordData = " + this.recordData);
        if (str.equals(this.REDMAGIC_TIME)) {
            this.redMagicTime.setTextColor(this.mContext.getResources().getColor(R.color.gcs_datamanager_head_text_checked));
            this.gameNote.setTextColor(this.mContext.getResources().getColor(R.color.gcs_datamanager_head_text_unchecked));
            this.redMagicTimeDownIcon.setVisibility(0);
            this.gameNoteDownIcon.setVisibility(4);
            this.redMagicTimeChecked.setVisibility(0);
            this.dataManagerColumnView.setMode(this.REDMAGIC_TIME);
            this.dataManagerPieChartView.setMode(this.REDMAGIC_TIME);
            if (this.recordTotalValue == 0) {
                this.totalValues.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                this.videoValues.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                this.screenshotValues.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                this.totalValues.setTranslationY(0.0f);
                this.videoValues.setTranslationY(0.0f);
                this.screenshotValues.setTranslationY(0.0f);
            } else {
                this.totalValues.setText(this.recordTotalValue + "");
                this.videoValues.setText(this.videoValue + "");
                this.screenshotValues.setText(this.screenshotValue + "");
                this.totalValues.setTranslationY(this.mBottomValueTranslationY);
                this.videoValues.setTranslationY(this.mBottomValueTranslationY);
                this.screenshotValues.setTranslationY(this.mBottomValueTranslationY);
            }
            setPieAngle(this.REDMAGIC_TIME);
            boolean z = this.recordData;
            if (!z) {
                this.dataManagerColumnView.setData(z, str, "", "", "", "", "", "", "");
            }
            this.video.setVisibility(0);
            this.videoValues.setVisibility(0);
            this.screenshot.setVisibility(0);
            this.screenshotValues.setVisibility(0);
            this.picture.setVisibility(4);
            this.pictureValues.setVisibility(4);
            this.wording.setVisibility(4);
            this.wordingValues.setVisibility(4);
            this.wordingPending.setVisibility(4);
            this.wordingPendingValues.setVisibility(4);
        } else if (str.equals(this.GAME_NOTE)) {
            this.redMagicTime.setTextColor(this.mContext.getResources().getColor(R.color.gcs_datamanager_head_text_unchecked));
            this.gameNote.setTextColor(this.mContext.getResources().getColor(R.color.gcs_datamanager_head_text_checked));
            this.redMagicTimeDownIcon.setVisibility(4);
            this.gameNoteDownIcon.setVisibility(0);
            this.redMagicTimeChecked.setVisibility(4);
            this.dataManagerColumnView.setMode(this.GAME_NOTE);
            this.dataManagerPieChartView.setMode(this.GAME_NOTE);
            setPieAngle(this.GAME_NOTE);
            boolean z2 = this.noteData;
            if (!z2) {
                this.dataManagerColumnView.setData(z2, str, "", "", "", "", "", "", "");
            }
            LogUtil.i(TAG, "CWL setCheckMode noteTotalValue == " + this.noteTotalValue);
            if (this.noteTotalValue == 0) {
                this.totalValues.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                this.pictureValues.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                this.wordingValues.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                this.totalValues.setTranslationY(0.0f);
                this.pictureValues.setTranslationY(0.0f);
                this.wordingValues.setTranslationY(0.0f);
                this.columnPkgTx.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                LogUtil.i(TAG, "CWL setCheckMode noteTotalValue == 0 ");
            } else {
                LogUtil.i(TAG, "CWL setCheckMode noteTotalValue ！= 0 ");
                this.totalValues.setText(this.noteTotalValue + "");
                this.pictureValues.setText(this.pictureValue + "");
                this.wordingValues.setText(this.wordingValue + "");
                this.totalValues.setTranslationY(this.mBottomValueTranslationY);
                this.pictureValues.setTranslationY(this.mBottomValueTranslationY);
                this.wordingValues.setTranslationY(this.mBottomValueTranslationY);
            }
            int i = this.wordingValuePending;
            if (i == 0) {
                this.wordingPendingValues.setText(this.mContext.getString(R.string.datamanager_bottom_null));
                this.wordingPendingValues.setTranslationY(0.0f);
            } else {
                float f = i;
                LogUtil.i(TAG, "CWL pendingValue1 = " + f);
                float size = f / this.notePkgMap.size();
                LogUtil.i(TAG, "CWL pendingValue2 = " + size);
                if (size < 1.0f) {
                    this.wordingPendingValues.setText("<1");
                    this.wordingPendingValues.setTranslationY(this.mBottomValueTranslationY);
                } else {
                    this.wordingPendingValues.setText(Math.round(size) + "");
                    this.wordingPendingValues.setTranslationY(this.mBottomValueTranslationY);
                }
            }
            this.video.setVisibility(4);
            this.videoValues.setVisibility(4);
            this.screenshot.setVisibility(4);
            this.screenshotValues.setVisibility(4);
            this.picture.setVisibility(0);
            this.pictureValues.setVisibility(0);
            this.wording.setVisibility(0);
            this.wordingValues.setVisibility(0);
            this.wordingPending.setVisibility(0);
            this.wordingPendingValues.setVisibility(0);
        }
        this.dataManagerColumnView.reDraw();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v3 */
    private void setPieAngle(String str) {
        ?? r11;
        float f;
        ?? r14;
        if (str != this.REDMAGIC_TIME) {
            if (str == this.GAME_NOTE) {
                this.angleNoteGray = 0.0f;
                this.angleNoteYellow = 0.0f;
                this.angleNoteBlue = 0.0f;
                this.angleNoteRed = 0.0f;
                if (this.noteLength == 0) {
                    this.pieChartBg.setBackgroundResource(R.mipmap.gcs_datamanager_chart_empty);
                    this.dataManagerPieChartView.setAngle(0.0f, 0.0f, 0.0f, 0.0f);
                    getPieTextCoordinate(0.0f, 0.0f, 0.0f, 0.0f);
                    setPieTextVisibility(false, false, false, false);
                    setPieTextPkgName("", "", "", "", 0, 0, 0, 0);
                } else {
                    this.pieChartBg.setBackgroundResource(R.mipmap.chart_outline);
                }
                int i = this.noteLength;
                if (i == 1) {
                    this.angleNoteRed = 360.0f;
                    this.dataManagerPieChartView.setAngle(0.0f, 0.0f, 0.0f, 360.0f);
                    this.dataManagerPieChartView.setPkgName("", "", "", this.notePkg.get(0));
                    this.dataManagerPieChartView.setArcSize(this.noteLength);
                    getPieTextCoordinate(0.0f, 0.0f, 0.0f, this.angleNoteRed);
                    setPieTextVisibility(false, false, false, true);
                    setPieTextPkgName("", "", "", this.notePkg.get(0), 0, 0, 0, this.notePkgvalue.get(0).intValue());
                } else if (i == 2) {
                    float intValue = (this.notePkgvalue.get(0).intValue() / this.noteTotalValue) * 360.0f;
                    this.angleNoteRed = intValue;
                    float f2 = 360.0f - intValue;
                    this.angleNoteGray = f2;
                    this.dataManagerPieChartView.setAngle(f2, 0.0f, 0.0f, intValue);
                    this.dataManagerPieChartView.setPkgName(this.notePkg.get(1), "", "", this.notePkg.get(0));
                    this.dataManagerPieChartView.setArcSize(this.noteLength);
                    getPieTextCoordinate(this.angleNoteGray, 0.0f, 0.0f, this.angleNoteRed);
                    setPieTextVisibility(true, false, false, true);
                    setPieTextPkgName(this.notePkg.get(1), "", "", this.notePkg.get(0), this.notePkgvalue.get(1).intValue(), 0, 0, this.notePkgvalue.get(0).intValue());
                } else if (i == 3) {
                    this.angleNoteRed = (this.notePkgvalue.get(0).intValue() / this.noteTotalValue) * 360.0f;
                    float intValue2 = (this.notePkgvalue.get(1).intValue() / this.noteTotalValue) * 360.0f;
                    this.angleNoteBlue = intValue2;
                    float f3 = this.angleNoteRed;
                    float f4 = (360.0f - f3) - intValue2;
                    this.angleNoteGray = f4;
                    this.dataManagerPieChartView.setAngle(f4, 0.0f, intValue2, f3);
                    this.dataManagerPieChartView.setPkgName(this.notePkg.get(2), "", this.notePkg.get(1), this.notePkg.get(0));
                    this.dataManagerPieChartView.setArcSize(this.noteLength);
                    getPieTextCoordinate(this.angleNoteGray, 0.0f, this.angleNoteBlue, this.angleNoteRed);
                    setPieTextVisibility(true, false, true, true);
                    setPieTextPkgName(this.notePkg.get(2), "", this.notePkg.get(1), this.notePkg.get(0), this.notePkgvalue.get(2).intValue(), 0, this.notePkgvalue.get(1).intValue(), this.notePkgvalue.get(0).intValue());
                } else if (i >= 4) {
                    this.angleNoteRed = (this.notePkgvalue.get(0).intValue() / this.noteTotalValue) * 360.0f;
                    this.angleNoteBlue = (this.notePkgvalue.get(1).intValue() / this.noteTotalValue) * 360.0f;
                    float intValue3 = (this.notePkgvalue.get(2).intValue() / this.noteTotalValue) * 360.0f;
                    this.angleNoteYellow = intValue3;
                    float f5 = this.angleNoteRed;
                    float f6 = this.angleNoteBlue;
                    float f7 = ((360.0f - f5) - f6) - intValue3;
                    this.angleNoteGray = f7;
                    this.dataManagerPieChartView.setAngle(f7, intValue3, f6, f5);
                    this.dataManagerPieChartView.setPkgName(this.mContext.getResources().getString(R.string.datamanager_pie_other), this.notePkg.get(2), this.notePkg.get(1), this.notePkg.get(0));
                    this.dataManagerPieChartView.setArcSize(4);
                    getPieTextCoordinate(this.angleNoteGray, this.angleNoteYellow, this.angleNoteBlue, this.angleNoteRed);
                    setPieTextVisibility(true, true, true, true);
                    setPieTextPkgName(this.mContext.getResources().getString(R.string.datamanager_pie_other), this.notePkg.get(2), this.notePkg.get(1), this.notePkg.get(0), ((this.noteTotalValue - this.notePkgvalue.get(0).intValue()) - this.notePkgvalue.get(1).intValue()) - this.notePkgvalue.get(2).intValue(), this.notePkgvalue.get(2).intValue(), this.notePkgvalue.get(1).intValue(), this.notePkgvalue.get(0).intValue());
                }
                this.dataManagerPieChartView.setTouchMode(3);
                this.dataManagerPieChartView.reDraw();
                LogUtil.i(TAG, "onGameNotes, angleNoteRed = " + this.angleNoteRed + "; angleNoteBlue = " + this.angleNoteBlue + "; angleNoteYellow = " + this.angleNoteYellow + "; angleNoteGray = " + this.angleNoteGray);
                return;
            }
            return;
        }
        this.angleRecordGray = 0.0f;
        this.angleRecordYellow = 0.0f;
        this.angleRecordBlue = 0.0f;
        this.angleRecordRed = 0.0f;
        if (this.recordLength == 0 || !this.recordData) {
            this.pieChartBg.setBackgroundResource(R.mipmap.gcs_datamanager_chart_empty);
            this.dataManagerPieChartView.setAngle(0.0f, 0.0f, 0.0f, 0.0f);
            this.dataManagerPieChartView.setPkgName("", "", "", "");
            this.dataManagerPieChartView.setArcSize(0);
            getPieTextCoordinate(0.0f, 0.0f, 0.0f, 0.0f);
            setPieTextVisibility(false, false, false, false);
            r11 = 0;
            f = 0.0f;
            r14 = 1;
            setPieTextPkgName("", "", "", "", 0, 0, 0, 0);
        } else {
            this.pieChartBg.setBackgroundResource(R.mipmap.chart_outline);
            r11 = 0;
            f = 0.0f;
            r14 = 1;
        }
        if (this.recordData) {
            int i2 = this.recordLength;
            if (i2 == r14) {
                this.angleRecordRed = 360.0f;
                this.dataManagerPieChartView.setAngle(f, f, f, 360.0f);
                this.dataManagerPieChartView.setPkgName("", "", "", this.recordPkg.get(r11));
                this.dataManagerPieChartView.setArcSize(this.recordLength);
                getPieTextCoordinate(f, f, f, this.angleRecordRed);
                setPieTextVisibility(r11, r11, r11, r14);
                setPieTextPkgName("", "", "", this.recordPkg.get(r11), 0, 0, 0, this.recordPkgvalue.get(r11).intValue());
            } else if (i2 == 2) {
                float intValue4 = (this.recordPkgvalue.get(r11).intValue() / this.recordTotalValue) * 360.0f;
                this.angleRecordRed = intValue4;
                float f8 = 360.0f - intValue4;
                this.angleRecordGray = f8;
                this.dataManagerPieChartView.setAngle(f8, f, f, intValue4);
                this.dataManagerPieChartView.setPkgName(this.recordPkg.get(r14), "", "", this.recordPkg.get(r11));
                this.dataManagerPieChartView.setArcSize(this.recordLength);
                getPieTextCoordinate(this.angleRecordGray, f, f, this.angleRecordRed);
                setPieTextVisibility(r14, r11, r11, r14);
                setPieTextPkgName(this.recordPkg.get(r14), "", "", this.recordPkg.get(r11), this.recordPkgvalue.get(r14).intValue(), 0, 0, this.recordPkgvalue.get(r11).intValue());
            } else if (i2 == 3) {
                this.angleRecordRed = (this.recordPkgvalue.get(r11).intValue() / this.recordTotalValue) * 360.0f;
                float intValue5 = (this.recordPkgvalue.get(r14).intValue() / this.recordTotalValue) * 360.0f;
                this.angleRecordBlue = intValue5;
                float f9 = this.angleRecordRed;
                float f10 = (360.0f - f9) - intValue5;
                this.angleRecordGray = f10;
                this.dataManagerPieChartView.setAngle(f10, f, intValue5, f9);
                this.dataManagerPieChartView.setPkgName(this.recordPkg.get(2), "", this.recordPkg.get(r14), this.recordPkg.get(r11));
                this.dataManagerPieChartView.setArcSize(this.recordLength);
                getPieTextCoordinate(this.angleRecordGray, f, this.angleRecordBlue, this.angleRecordRed);
                setPieTextVisibility(r14, r11, r14, r14);
                setPieTextPkgName(this.recordPkg.get(2), "", this.recordPkg.get(r14), this.recordPkg.get(r11), this.recordPkgvalue.get(2).intValue(), 0, this.recordPkgvalue.get(r14).intValue(), this.recordPkgvalue.get(r11).intValue());
            } else if (i2 >= 4) {
                this.angleRecordRed = (this.recordPkgvalue.get(r11).intValue() / this.recordTotalValue) * 360.0f;
                this.angleRecordBlue = (this.recordPkgvalue.get(r14).intValue() / this.recordTotalValue) * 360.0f;
                float intValue6 = (this.recordPkgvalue.get(2).intValue() / this.recordTotalValue) * 360.0f;
                this.angleRecordYellow = intValue6;
                float f11 = this.angleRecordRed;
                float f12 = this.angleRecordBlue;
                float f13 = ((360.0f - f11) - f12) - intValue6;
                this.angleRecordGray = f13;
                this.dataManagerPieChartView.setAngle(f13, intValue6, f12, f11);
                this.dataManagerPieChartView.setPkgName(this.mContext.getResources().getString(R.string.datamanager_pie_other), this.recordPkg.get(2), this.recordPkg.get(r14), this.recordPkg.get(r11));
                this.dataManagerPieChartView.setArcSize(4);
                getPieTextCoordinate(this.angleRecordGray, this.angleRecordYellow, this.angleRecordBlue, this.angleRecordRed);
                setPieTextVisibility(r14, r14, r14, r14);
                setPieTextPkgName(this.mContext.getResources().getString(R.string.datamanager_pie_other), this.recordPkg.get(2), this.recordPkg.get(r14), this.recordPkg.get(r11), ((this.recordTotalValue - this.recordPkgvalue.get(r11).intValue()) - this.recordPkgvalue.get(r14).intValue()) - this.recordPkgvalue.get(2).intValue(), this.recordPkgvalue.get(2).intValue(), this.recordPkgvalue.get(r14).intValue(), this.recordPkgvalue.get(r11).intValue());
            }
        }
        this.dataManagerPieChartView.setTouchMode(3);
        this.dataManagerPieChartView.reDraw();
        LogUtil.i(TAG, "onGameHighLights, angle4 = " + this.angleRecordRed + "; angle3 = " + this.angleRecordBlue + "; angleRecordYellow = " + this.angleRecordYellow + "; angleRecordGray = " + this.angleRecordGray);
    }

    private void setPieTextChecked(int i) {
        LogUtil.i(TAG, "setPieTextChecked index = " + i);
        this.pieGrayPkgTx.setTextColor(this.mContext.getResources().getColor(i == 0 ? R.color.gcs_datamanager_checked_text : R.color.gcs_datamanager_gray_text));
        this.pieYellowPkgTx.setTextColor(this.mContext.getResources().getColor(i == 1 ? R.color.gcs_datamanager_checked_text : R.color.gcs_datamanager_yellow_text));
        this.pieBluePkgTx.setTextColor(this.mContext.getResources().getColor(i == 2 ? R.color.gcs_datamanager_checked_text : R.color.gcs_datamanager_blue_text));
        this.pieRedPkgTx.setTextColor(this.mContext.getResources().getColor(i == 3 ? R.color.gcs_datamanager_checked_text : R.color.gcs_datamanager_red_text));
        this.pieGrayPkgTx.setAlpha(i == 0 ? 1.0f : 0.8f);
        this.pieYellowPkgTx.setAlpha(i == 1 ? 1.0f : 0.8f);
        this.pieBluePkgTx.setAlpha(i == 2 ? 1.0f : 0.8f);
        this.pieRedPkgTx.setAlpha(i != 3 ? 0.8f : 1.0f);
    }

    private void setPieTextPkgName(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4) {
        StringBuilder sb;
        MarqueeTextView marqueeTextView = this.pieGrayPkgTx;
        if (str.equals(this.mContext.getResources().getString(R.string.datamanager_pie_other))) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder();
            str = getAppName(this.mContext, str);
        }
        marqueeTextView.setText(sb.append(str).append(" ").append(i).toString());
        this.pieYellowPkgTx.setText(getAppName(this.mContext, str2) + " " + i2);
        this.pieBluePkgTx.setText(getAppName(this.mContext, str3) + " " + i3);
        this.pieRedPkgTx.setText(getAppName(this.mContext, str4) + " " + i4);
        if (this.pieGrayPkgTx.getText().length() < 11) {
            this.pieGrayPkgTx.setMarquee(true);
            this.pieGrayPkgTx.setMarqueeAttr();
        } else {
            this.pieGrayPkgTx.setMarquee(false);
            this.pieGrayPkgTx.setMarqueeAttr();
        }
        if (this.pieYellowPkgTx.getText().length() < 11) {
            this.pieYellowPkgTx.setMarquee(true);
            this.pieYellowPkgTx.setMarqueeAttr();
        } else {
            this.pieYellowPkgTx.setMarquee(false);
            this.pieYellowPkgTx.setMarqueeAttr();
        }
        if (this.pieBluePkgTx.getText().length() < 11) {
            this.pieBluePkgTx.setMarquee(true);
            this.pieBluePkgTx.setMarqueeAttr();
        } else {
            this.pieBluePkgTx.setMarquee(false);
            this.pieBluePkgTx.setMarqueeAttr();
        }
        if (this.pieRedPkgTx.getText().length() < 11) {
            this.pieRedPkgTx.setMarquee(true);
            this.pieRedPkgTx.setMarqueeAttr();
        } else {
            this.pieRedPkgTx.setMarquee(false);
            this.pieRedPkgTx.setMarqueeAttr();
        }
    }

    private void setPieTextVisibility(boolean z, boolean z2, boolean z3, boolean z4) {
        this.pieGrayPkgTx.setVisibility(z ? 0 : 4);
        this.pieYellowPkgTx.setVisibility(z2 ? 0 : 4);
        this.pieBluePkgTx.setVisibility(z3 ? 0 : 4);
        this.pieRedPkgTx.setVisibility(z4 ? 0 : 4);
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment
    protected View createMainView() {
        if (this.m_activity == null) {
            return null;
        }
        this.mContext = this.m_activity;
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "data_manager_page_view");
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        this.density = displayMetrics.density;
        this.scaledDensity = displayMetrics.scaledDensity;
        this.densityDpi = displayMetrics.densityDpi;
        this.conDensityDpi = this.mContext.getResources().getConfiguration().densityDpi;
        Utils.updateDensity(this.mContext);
        this.mPieTextDistance = getResources().getInteger(R.integer.dm_piechart_text_distance);
        this.mIsTablet = getResources().getBoolean(R.bool.datamanager_is_tablet);
        this.mBottomValueTranslationY = getResources().getDimensionPixelSize(R.dimen.dm_bottom_value_translation_y);
        this.mResumed = true;
        bindArkBaseService();
        View inflate = View.inflate(this.m_activity, R.layout.gcs_gamecenter_fragment_data_manager, null);
        initView(inflate);
        GcsAnimationUtil.setGcsItemTranslationY(this.animationView);
        recordOnly();
        return inflate;
    }

    @Override // cn.nubia.gamecenter.settings.datamanager.DataManagerPieChartView.IPieChartCallBack
    public void notifyPkgChange(String str, String str2, int i) {
        this.recordPackage = str;
        if (str == null || str.equals("")) {
            this.columnPkgTx.setText(this.mContext.getString(R.string.datamanager_bottom_null));
            return;
        }
        setPieTextChecked(i);
        if (str.equals(this.mContext.getResources().getString(R.string.datamanager_pie_other))) {
            this.columnPkgTx.setText(str);
            if (!str2.equals(this.REDMAGIC_TIME)) {
                if (str2.equals(this.GAME_NOTE)) {
                    this.dataManagerColumnView.setData(this.noteData, str2, "", "", "", "", "", (((this.pictureTotal - Integer.parseInt(this.notesMap.get(this.notePkgMap.get(this.notePkg.get(0)).intValue()).get("pictureNotes"))) - Integer.parseInt(this.notesMap.get(this.notePkgMap.get(this.notePkg.get(1)).intValue()).get("pictureNotes"))) - Integer.parseInt(this.notesMap.get(this.notePkgMap.get(this.notePkg.get(2)).intValue()).get("pictureNotes"))) + "", (((this.textTotal - Integer.parseInt(this.notesMap.get(this.notePkgMap.get(this.notePkg.get(0)).intValue()).get("textNotes"))) - Integer.parseInt(this.notesMap.get(this.notePkgMap.get(this.notePkg.get(1)).intValue()).get("textNotes"))) - Integer.parseInt(this.notesMap.get(this.notePkgMap.get(this.notePkg.get(2)).intValue()).get("textNotes"))) + "");
                    return;
                }
                return;
            }
            this.dataManagerColumnView.setData(this.recordData, str2, (((this.deathVideoTotal - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(0)).intValue()).get("deathVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(1)).intValue()).get("deathVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(2)).intValue()).get("deathVideos"))) + "", (((this.fullVideoTotal - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(0)).intValue()).get("fullVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(1)).intValue()).get("fullVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(2)).intValue()).get("fullVideos"))) + "", (((this.collectionVideoTotal - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(0)).intValue()).get("collectionVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(1)).intValue()).get("collectionVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(2)).intValue()).get("collectionVideos"))) + "", (((this.momentVideoTotal - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(0)).intValue()).get("momentVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(1)).intValue()).get("momentVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(2)).intValue()).get("momentVideos"))) + "", (((this.manualVideoTotal - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(0)).intValue()).get("manualVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(1)).intValue()).get("manualVideos"))) - Integer.parseInt(this.recordsMap.get(this.recordPkgMap.get(this.recordPkg.get(2)).intValue()).get("manualVideos"))) + "", "", "");
            return;
        }
        this.columnPkgTx.setText(getAppName(this.mContext, str));
        if (str2.equals(this.REDMAGIC_TIME)) {
            boolean z = this.recordData;
            if (!z) {
                this.dataManagerColumnView.setData(z, str2, "", "", "", "", "", "", "");
                return;
            }
            LogUtil.i(TAG, "notifyPkgChange, pkg = " + str);
            int intValue = this.recordPkgMap.get(str).intValue();
            Map<String, String> map = this.recordsMap.get(intValue);
            this.dataManagerColumnView.setData(this.recordData, str2, map.get("deathVideos"), map.get("fullVideos"), map.get("collectionVideos"), map.get("momentVideos"), map.get("manualVideos"), "", "");
            LogUtil.i(TAG, "notifyPkgChange, pkgIndex = " + intValue + ", deathVideos = " + map.get("deathVideos") + ", fullVideos = " + map.get("fullVideos") + ", collectionVideos = " + map.get("collectionVideos") + ", momentVideos = " + map.get("momentVideos") + ", manualVideos = " + map.get("manualVideos"));
            return;
        }
        if (str2.equals(this.GAME_NOTE)) {
            boolean z2 = this.noteData;
            if (!z2) {
                this.dataManagerColumnView.setData(z2, str2, "", "", "", "", "", "", "");
                return;
            }
            LogUtil.i(TAG, "notifyPkgChange, pkg = " + str);
            int intValue2 = this.notePkgMap.get(str).intValue();
            Map<String, String> map2 = this.notesMap.get(intValue2);
            LogUtil.i(TAG, "notifyPkgChange, pkgIndex = " + intValue2 + ", pictureNotes = " + map2.get("pictureNotes") + ", textNotes = " + map2.get("textNotes"));
            this.dataManagerColumnView.setData(this.noteData, str2, "", "", "", "", "", map2.get("pictureNotes"), map2.get("textNotes"));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.i(TAG, "CWL Click ");
        if (view.getId() == R.id.datamanager_main_head_redmagic_time) {
            LogUtil.i(TAG, "CWL Click REDMAGIC_TIME");
            if (!this.pageType.equals(this.REDMAGIC_TIME)) {
                GcsAnimationUtil.setDataManagerFragmentAlpha(this.animationView);
            }
            String str = this.REDMAGIC_TIME;
            this.pageType = str;
            setCheckMode(str);
            return;
        }
        if (view.getId() == R.id.datamanager_main_head_gamenote) {
            LogUtil.i(TAG, "CWL Click GAME_NOTE");
            if (!this.pageType.equals(this.GAME_NOTE)) {
                GcsAnimationUtil.setDataManagerFragmentAlpha(this.animationView);
            }
            String str2 = this.GAME_NOTE;
            this.pageType = str2;
            setCheckMode(str2);
            return;
        }
        if (view.getId() == R.id.column_redmagic_time_check) {
            Intent intent = new Intent();
            intent.setAction(RECORD_ACTION);
            intent.putExtra("package_name", this.recordPackage);
            this.mContext.startActivity(intent);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        INbAccountLogin iNbAccountLogin = this.mNbAccountLoginImpl;
        if (iNbAccountLogin != null) {
            try {
                iNbAccountLogin.unregisterCallback(this.mNbAccountLoginCb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Context context = this.mContext;
        if (context != null) {
            context.unbindService(this.mArkBaseServiceConnection);
        }
    }

    public void onGameHighLights(List list) {
        LogUtil.i(TAG, "onGameHighLights, ********recordList = " + list);
        if (list == null || list.isEmpty()) {
            synchronized (this) {
                this.recordData = false;
            }
        } else {
            this.recordTotalValue = 0;
            this.recordsMap = list;
            TreeMap treeMap = new TreeMap();
            this.recordPkgMap = new TreeMap();
            synchronized (this) {
                int i = 0;
                for (Map<String, String> map : this.recordsMap) {
                    LogUtil.i(TAG, "onGameHighLights, packageName = " + map.get("packageName") + ", videoHighlights = " + map.get("videoHighlights") + ", pictureHighlights = " + map.get("pictureHighlights") + ", deathVideos = " + map.get("deathVideos") + ", fullVideos = " + map.get("fullVideos") + ", collectionVideos = " + map.get("collectionVideos") + ", momentVideos = " + map.get("momentVideos") + ", manualVideos = " + map.get("manualVideos"));
                    if (Integer.parseInt(map.get("videoHighlights")) != 0 || Integer.parseInt(map.get("pictureHighlights")) != 0) {
                        treeMap.put(map.get("packageName"), Integer.valueOf(Integer.parseInt(map.get("videoHighlights")) + Integer.parseInt(map.get("pictureHighlights"))));
                        this.recordTotalValue = this.recordTotalValue + Integer.parseInt(map.get("videoHighlights")) + Integer.parseInt(map.get("pictureHighlights"));
                        this.videoValue += Integer.parseInt(map.get("videoHighlights"));
                        this.screenshotValue += Integer.parseInt(map.get("pictureHighlights"));
                        this.recordPkgMap.put(map.get("packageName"), Integer.valueOf(i));
                        this.deathVideoTotal += Integer.parseInt(map.get("deathVideos"));
                        this.fullVideoTotal += Integer.parseInt(map.get("fullVideos"));
                        this.collectionVideoTotal += Integer.parseInt(map.get("collectionVideos"));
                        this.momentVideoTotal += Integer.parseInt(map.get("momentVideos"));
                        this.manualVideoTotal += Integer.parseInt(map.get("manualVideos"));
                    }
                    i++;
                }
            }
            LogUtil.i(TAG, "onGameHighLights, totalValue = " + this.recordTotalValue + ", videoValue = " + this.videoValue + ", screenshotValue = " + this.screenshotValue);
            synchronized (this) {
                int size = treeMap.size();
                this.recordLength = size;
                if (size <= 0 && this.videoValue <= 0) {
                    this.recordData = false;
                }
                this.recordData = true;
            }
            LogUtil.i(TAG, "onGameHighLights, recordLength = " + this.recordLength + ", recordData = " + this.recordData);
            ArrayList<Map.Entry> arrayList = new ArrayList(treeMap.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: cn.nubia.gamecenter.settings.datamanager.DataManagerFragment.4
                @Override // java.util.Comparator
                public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                    return entry2.getValue().compareTo(entry.getValue());
                }
            });
            synchronized (this) {
                for (Map.Entry entry : arrayList) {
                    LogUtil.i(TAG, "entry.getKey() = " + ((String) entry.getKey()) + ", entry.getValue() = " + entry.getValue());
                    this.recordPkg.add((String) entry.getKey());
                    this.recordPkgvalue.add((Integer) entry.getValue());
                }
            }
        }
        this.handler.sendEmptyMessage(0);
    }

    public void onGameNotes(List list) {
        LogUtil.i(TAG, "onGameNotes, ********recordList = " + list);
        if (list == null || list.isEmpty()) {
            synchronized (this) {
                this.noteData = false;
            }
            return;
        }
        synchronized (this) {
            this.noteTotalValue = 0;
            this.notesMap = list;
            list.iterator();
            LogUtil.i(TAG, "onGameNotes, noteLength = " + this.noteLength);
            TreeMap treeMap = new TreeMap();
            this.notePkgMap = new TreeMap();
            int i = 0;
            for (int i2 = 0; i2 < this.notesMap.size(); i2++) {
                Map<String, String> map = this.notesMap.get(i2);
                LogUtil.i(TAG, "onGameNotes, packageName = " + map.get("packageName") + ", pictureNotes = " + map.get("pictureNotes") + ", textNotes = " + map.get("textNotes") + ", suspend = " + map.get("suspend"));
                if (Integer.parseInt(map.get("pictureNotes")) != 0 || Integer.parseInt(map.get("textNotes")) != 0) {
                    this.noteLength++;
                    this.noteTotalValue = this.noteTotalValue + Integer.parseInt(map.get("pictureNotes")) + Integer.parseInt(map.get("textNotes"));
                    LogUtil.i(TAG, "onGameNotes, totalValue1 = " + this.noteTotalValue);
                    this.pictureValue += Integer.parseInt(map.get("pictureNotes"));
                    this.wordingValue += Integer.parseInt(map.get("textNotes"));
                    treeMap.put(map.get("packageName"), Integer.valueOf(Integer.parseInt(map.get("pictureNotes")) + Integer.parseInt(map.get("textNotes"))));
                    this.pictureTotal += Integer.parseInt(map.get("pictureNotes"));
                    this.textTotal += Integer.parseInt(map.get("textNotes"));
                }
                this.notePkgMap.put(map.get("packageName"), Integer.valueOf(i));
                i++;
                this.wordingValuePending += Integer.parseInt(map.get("suspend"));
            }
            LogUtil.i(TAG, "onGameNotes, totalValue = " + this.noteTotalValue);
            if (this.noteTotalValue != 0) {
                this.noteData = true;
            }
            ArrayList<Map.Entry> arrayList = new ArrayList(treeMap.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: cn.nubia.gamecenter.settings.datamanager.DataManagerFragment.3
                @Override // java.util.Comparator
                public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                    return entry2.getValue().compareTo(entry.getValue());
                }
            });
            for (Map.Entry entry : arrayList) {
                LogUtil.i(TAG, "entry.getKey() = " + ((String) entry.getKey()) + ", entry.getValue() = " + entry.getValue());
                this.notePkg.add((String) entry.getKey());
                this.notePkgvalue.add((Integer) entry.getValue());
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LogUtil.i(TAG, "onHiddenChanged :" + z);
        if (z) {
            Utils.resetDensity(this.mContext, this.density, this.scaledDensity, this.densityDpi, this.conDensityDpi);
        } else {
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            this.density = displayMetrics.density;
            this.scaledDensity = displayMetrics.scaledDensity;
            this.densityDpi = displayMetrics.densityDpi;
            this.conDensityDpi = this.mContext.getResources().getConfiguration().densityDpi;
            Utils.updateDensity(this.mContext);
        }
        if (z) {
            new Handler().post(new Runnable() { // from class: cn.nubia.gamecenter.settings.datamanager.DataManagerFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    DataManagerFragment.this.animationView.setAlpha(0.0f);
                }
            });
        } else {
            GcsAnimationUtil.setGcsItemTranslationY(this.animationView);
            GcsAnimationUtil.setGcsItemAlpha(this.animationView);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mResumed = false;
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mResumed = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
