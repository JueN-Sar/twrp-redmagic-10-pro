package cn.nubia.tgk;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import cn.nubia.gamelauncher.R;
import cn.nubia.tgk.data.TgkData;
import cn.nubia.tgk.data.TgkGameInfo;
import cn.nubia.tgk.proxy.InputManagerProxy;
import cn.nubia.tgk.util.ImeHeightUtils;
import cn.nubia.tgk.util.TgkLampHelper;
import cn.nubia.tgk.util.ToastUtil;
import cn.nubia.tgk.widget.FakeFloatView;
import cn.nubia.tgk.widget.LampCaseListPopView;
import cn.nubia.tgk.widget.LampListViewAdapter;
import cn.nubia.tgk.widget.MarqueeTextView;
import cn.nubia.tgk.widget.MovableImageViewGroup;
import cn.nubia.tgk.widget.TgkCaseListPopView;
import cn.nubia.tgk.widget.TgkCaseListViewAdapter;
import cn.nubia.tgk.widget.TgkCenterVisualEffectView;
import cn.nubia.tgk.widget.TgkCustomRadioButton;
import cn.nubia.tgk.widget.TgkMenuPopView;
import cn.nubia.tgk.widget.TgkMenuView;
import cn.nubia.tgk.widget.TgkMultSeekBarView;
import cn.nubia.tgk.widget.TgkSensitivityView;
import cn.nubia.tgk.widget.TgkTopVisualEffectView;
import cn.nubia.tgk.widget.TgkVisualEffectTransparencyView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class TgkMapView extends FrameLayout implements KeyEvent.Callback {
    private static boolean DEBUG = false;
    private static final String TAG = "TgkMapView";
    private View helpView;
    private boolean isLampListViewShow;
    private boolean isNoRemind;
    private boolean isPreviewViewImgShow;
    private volatile boolean isRenameViewStubShow;
    private boolean isTgkCaseListViewShow;
    private LampListViewAdapter.onDataChangeListener lampCaseListAdapterListener;
    private ImageButton mColseBtn;
    private Context mContext;
    private TextView mDeleteTextView;
    private View mDeleteView;
    private View mDeleteViewLine;
    private FakeFloatView[][] mFBArray;
    private int[][] mFFViewBg;
    private String mGameAppPackageName;
    private boolean mGameInfoChanged;
    private ITgkMapViewClickListener mIClickListener;
    private InputManagerProxy mInputManagerProxy;
    private int mIsLandscape;
    private boolean mIsMoreVsShow;
    private volatile boolean mIsTgkMultViewShow;
    private Button mKnowButton;
    private LampListViewAdapter mLampAdapter;
    private LampCaseListPopView mLampCaseListPopView;
    private int mLampCaseSelectPosition;
    private TextView mLampCaseTitle;
    private LinearLayout mLampListView;
    private int mLastTgkOptId;
    private FakeFloatView[] mLinkFBArray;
    private TextView mModifyTextView;
    private View mModifyView;
    private LinearLayout mMoreBtnsView;
    private Button mMoreCloseBtn;
    private View mMoreOpenBtn;
    private RelativeLayout mMoreRelativeLayout;
    private ScrollView mMoreVScrollView;
    private View mMoreView;
    private ViewStub mMoreVs;
    private int mMoveVersionViewBg;
    private ImageView mNoRemindImageView;
    private LinearLayout mNoRemindPanel;
    private FakeFloatView[][] mOneTwoFBArray;
    private MovableImageViewGroup mPreviewImgViewGroup;
    private TextView mPreviewTextView;
    private View mPreviewView;
    private ViewStub mPreviewViewImgVs;
    private View mPreviewViewLine;
    private ViewStub mRenameViewStub;
    private View mRootView;
    private ViewStub mRotationGuidancePanel;
    private TgkSensitivityView[] mSensitivityViews;
    private boolean mSupportTgkMoveVision;
    private boolean mSupportedGameKeyLink;
    private ToggleButton mSwitchBtn;
    private TgkCaseListViewAdapter mTgkCaseAdapter;
    private TgkCaseListPopView mTgkCaseListPopView;
    private LinearLayout mTgkCaseListView;
    private EditText mTgkCaseNameEditor;
    private ToggleButton mTgkCaseShowSw;
    private TextView mTgkCaseTitle;
    private boolean[] mTgkDisableFlag;
    private TgkGameInfo mTgkGameInfo;
    private ToggleButton mTgkHapticFeedbackSw;
    private LinearLayout mTgkHeadView;
    private final int[] mTgkKeyCodeArray;
    private FrameLayout mTgkMainView;
    private TgkMenuView[] mTgkMenuBtnArray;
    private int mTgkMenuDirection;
    private TgkMenuPopView mTgkMenuPopView;
    private ViewStub mTgkMultViewStub;
    private ArrayList<TgkCustomRadioButton> mTgkOptList;
    private TgkVisualEffectTransparencyView mTgkVisualEffectTransparencyView;
    private View.OnClickListener mViewClickListener;
    private WorkHandler mWorkHandler;
    private MovableImageViewGroup.onClosedListener movableImgViewGroupClosedListener;
    private final TgkSensitivityView.OnChangeListener sensitivityChangeListener;
    private TgkCaseListViewAdapter.onDataChangeListener tgkCaseListViewAdapterListener;
    private final TgkMultSeekBarView.OnChangeListener tgkMultSeekBarViewOnChangeListener;
    private TgkVisualEffectTransparencyView.OnChangeListener tgkTransparencyListener;

    private static class DeleteAsyncTask extends AsyncTask<Long, Integer, Integer> {
        private String mShotPicture = "";
        private WeakReference<TgkMapView> tgkMapViewWeakReference;

        protected DeleteAsyncTask(TgkMapView tgkMapView) {
            this.tgkMapViewWeakReference = new WeakReference<>(tgkMapView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Long... lArr) {
            TgkMapView tgkMapView = this.tgkMapViewWeakReference.get();
            if (tgkMapView != null) {
                TgkHelper.deleteTgkCase(tgkMapView.mContext.getContentResolver(), (int) lArr[0].longValue(), lArr[1].longValue(), tgkMapView.mContext, this.mShotPicture);
            }
            return 0;
        }

        public void setShotPicture(String str) {
            this.mShotPicture = str;
        }
    }

    private class FingerEventListener implements FakeFloatView.IFingerEventListener {
        public FingerEventListener() {
        }

        @Override // cn.nubia.tgk.widget.FakeFloatView.IFingerEventListener
        public void fingerDown(int i) {
        }

        @Override // cn.nubia.tgk.widget.FakeFloatView.IFingerEventListener
        public void fingerStartMove(int i) {
            TgkMapView.this.updatNonFloatViewDisplay(false);
        }

        @Override // cn.nubia.tgk.widget.FakeFloatView.IFingerEventListener
        public void fingerUp(int i) {
            if (TgkMapView.this.mTgkHeadView.getVisibility() == 0) {
                return;
            }
            TgkMapView.this.updatNonFloatViewDisplay(true);
            TgkMapView.this.saveFloatViewPos();
        }
    }

    public interface ITgkMapViewClickListener {
        void doClose(TgkGameInfo tgkGameInfo);

        void requestColorfulLight(int i);

        void showHelp();

        void showToast(String str);
    }

    private static class UiAsyncTask extends AsyncTask<Long, Integer, Bitmap> {
        private WeakReference<TgkMapView> tgkMapViewWeakReference;

        protected UiAsyncTask(TgkMapView tgkMapView) {
            this.tgkMapViewWeakReference = new WeakReference<>(tgkMapView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Long... lArr) {
            TgkMapView tgkMapView = this.tgkMapViewWeakReference.get();
            if (tgkMapView != null) {
                return TgkHelper.queryTgkCasePicture(tgkMapView.mContext.getContentResolver(), lArr[0].longValue());
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            TgkMapView tgkMapView = this.tgkMapViewWeakReference.get();
            if (tgkMapView != null) {
                tgkMapView.mTgkGameInfo.picture = bitmap;
                tgkMapView.updatePreviewBtnState();
            }
        }
    }

    private static class WorkHandler extends Handler {
        private WeakReference<TgkMapView> mWeakReference;

        public WorkHandler(TgkMapView tgkMapView) {
            this.mWeakReference = new WeakReference<>(tgkMapView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            TgkMapView tgkMapView = this.mWeakReference.get();
            if (tgkMapView != null) {
                boolean booleanValue = ((Boolean) message.obj).booleanValue();
                int i = message.what;
                if (i == 2) {
                    if (message.arg1 == 137) {
                        if (booleanValue) {
                            tgkMapView.requestColorfulLight(TgkLampHelper.getLeftUpId());
                            tgkMapView.mOneTwoFBArray[0][0].setPressed(false);
                            return;
                        } else {
                            tgkMapView.requestColorfulLight(TgkLampHelper.getLeftUpId());
                            tgkMapView.mOneTwoFBArray[0][1].setPressed(false);
                            return;
                        }
                    }
                    if (message.arg1 == 138) {
                        if (booleanValue) {
                            tgkMapView.requestColorfulLight(TgkLampHelper.getRightUpId());
                            tgkMapView.mOneTwoFBArray[1][0].setPressed(false);
                            return;
                        } else {
                            tgkMapView.requestColorfulLight(TgkLampHelper.getRightUpId());
                            tgkMapView.mOneTwoFBArray[1][1].setPressed(false);
                            return;
                        }
                    }
                    return;
                }
                if (i != 6) {
                    return;
                }
                if (message.arg1 == 137) {
                    if (booleanValue) {
                        tgkMapView.requestColorfulLight(TgkLampHelper.getLeftDownId());
                        tgkMapView.mOneTwoFBArray[0][0].setPressed(true);
                        tgkMapView.doFBPressedWork(6, 137, false, ((1000 / message.arg2) / 5) * 3, message.arg2);
                        return;
                    } else {
                        tgkMapView.requestColorfulLight(TgkLampHelper.getLeftUpId());
                        tgkMapView.mOneTwoFBArray[0][0].setPressed(false);
                        tgkMapView.doFBPressedWork(6, 137, true, 2 * ((1000 / message.arg2) / 5), message.arg2);
                        return;
                    }
                }
                if (message.arg1 == 138) {
                    if (booleanValue) {
                        tgkMapView.requestColorfulLight(TgkLampHelper.getRightDownId());
                        tgkMapView.mOneTwoFBArray[1][0].setPressed(true);
                        tgkMapView.doFBPressedWork(6, 138, false, ((1000 / message.arg2) / 5) * 3, message.arg2);
                    } else {
                        tgkMapView.requestColorfulLight(TgkLampHelper.getRightUpId());
                        tgkMapView.mOneTwoFBArray[1][0].setPressed(false);
                        tgkMapView.doFBPressedWork(6, 138, true, 2 * ((1000 / message.arg2) / 5), message.arg2);
                    }
                }
            }
        }
    }

    static {
        DEBUG = "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
    }

    public TgkMapView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public TgkMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTgkMenuBtnArray = new TgkMenuView[3];
        this.mMoreCloseBtn = null;
        this.mTgkKeyCodeArray = new int[]{137, 138, 136};
        this.mTgkMenuDirection = -1;
        this.isTgkCaseListViewShow = false;
        this.isLampListViewShow = false;
        this.mLampCaseSelectPosition = -1;
        this.mTgkOptList = new ArrayList<>();
        this.mLastTgkOptId = -1;
        this.mIsTgkMultViewShow = false;
        this.mMoreVs = null;
        this.mTgkVisualEffectTransparencyView = null;
        this.mIsMoreVsShow = false;
        this.isPreviewViewImgShow = false;
        this.isRenameViewStubShow = false;
        this.mMoveVersionViewBg = R.drawable.tgk_move_versionfloat_ball_bg_drawable;
        this.mFFViewBg = new int[][]{new int[]{R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_m, R.drawable.tgk_float_ball_bg_m}, new int[]{R.drawable.tgk_copy_float_ball_bg_drawable_l1, R.drawable.tgk_copy_float_ball_bg_drawable_l2, R.drawable.tgk_copy_float_ball_bg_drawable_r1, R.drawable.tgk_copy_float_ball_bg_drawable_r2, R.drawable.tgk_copy_float_ball_bg_drawable_m1, R.drawable.tgk_copy_float_ball_bg_drawable_m2}, new int[]{R.drawable.tgk_down_float_ball_bg_drawable_l, R.drawable.tgk_up_float_ball_bg_drawable_l, R.drawable.tgk_down_float_ball_bg_drawable_r, R.drawable.tgk_up_float_ball_bg_drawable_r, R.drawable.tgk_down_float_ball_bg_drawable_m, R.drawable.tgk_up_float_ball_bg_drawable_m}, new int[]{R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_m, R.drawable.tgk_float_ball_bg_m}, new int[]{R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_m, R.drawable.tgk_float_ball_bg_m}, new int[]{R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_l, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_r, R.drawable.tgk_float_ball_bg_m, R.drawable.tgk_float_ball_bg_m}, new int[]{R.drawable.tgk_copy_float_ball_bg_drawable_l1, R.drawable.tgk_copy_float_ball_bg_drawable_l2, R.drawable.tgk_copy_float_ball_bg_drawable_r1, R.drawable.tgk_copy_float_ball_bg_drawable_r2, R.drawable.tgk_copy_float_ball_bg_drawable_m1, R.drawable.tgk_copy_float_ball_bg_drawable_m2}};
        this.mGameInfoChanged = false;
        this.mSupportedGameKeyLink = true;
        this.mSupportTgkMoveVision = true;
        this.mIsLandscape = 1;
        this.mTgkDisableFlag = new boolean[]{true, true, true, true, true, true, true, true, true, true};
        this.lampCaseListAdapterListener = new LampListViewAdapter.onDataChangeListener() { // from class: cn.nubia.tgk.TgkMapView.4
            @Override // cn.nubia.tgk.widget.LampListViewAdapter.onDataChangeListener
            public void onChanged(int i) {
                TgkMapView.this.mLampAdapter.setSelectState(i);
                TgkMapView.this.mLampAdapter.notifyDataSetChanged();
                TgkMapView.this.setLampCaseTvTitle();
                TgkMapView.this.mLampCaseSelectPosition = i;
                TgkMapView.this.hideLampListPopView();
                TgkHelper.openLampScene(TgkMapView.this.mLampCaseSelectPosition);
                TgkMapView.this.requestColorfulLight(TgkLampHelper.getLeftDownId());
                TgkMapView.this.requestColorfulLight(TgkLampHelper.getLeftUpId());
                TgkMapView.this.requestColorfulLight(TgkLampHelper.getRightDownId());
                TgkMapView.this.requestColorfulLight(TgkLampHelper.getRightUpId());
            }
        };
        this.tgkCaseListViewAdapterListener = new TgkCaseListViewAdapter.onDataChangeListener() { // from class: cn.nubia.tgk.TgkMapView.5
            @Override // cn.nubia.tgk.widget.TgkCaseListViewAdapter.onDataChangeListener
            public void onChanged(int i, int i2) {
                TgkMapView.this.mTgkGameInfo.selectedTableId = i;
                TgkMapView.this.mTgkGameInfo.selectedCasePosition = i2;
                TgkMapView.this.mGameInfoChanged = true;
                TgkMapView.this.mTgkGameInfo.picture = null;
                TgkData selectedCaseData = TgkMapView.this.mTgkGameInfo.getSelectedCaseData();
                if (selectedCaseData != null && (selectedCaseData.optionArray[0] == 4 || selectedCaseData.optionArray[1] == 4)) {
                    TgkHelper.queryTgkLinkState(TgkMapView.this.mContext.getContentResolver(), TgkMapView.this.mGameAppPackageName, TgkMapView.this.mTgkGameInfo.getSelectedCaseData().ID, TgkMapView.this.mTgkGameInfo.getSelectedCaseData().state);
                }
                TgkMapView.this.tgkCaseListPopViewScrollToPosition(i, i2);
                TgkMapView.this.showPreviewViewImg(false);
                TgkMapView.this.hideAllLinkFb();
                TgkMapView.this.adjustOptIdBySw();
                TgkMapView.this.hideTgkCaseListPopView();
                TgkMapView tgkMapView = TgkMapView.this;
                tgkMapView.updateAllViewState(tgkMapView.mTgkGameInfo.getSelectedCaseData().mainSw);
                new UiAsyncTask(TgkMapView.this).execute(Long.valueOf(TgkMapView.this.mTgkGameInfo.getSelectedCaseData().ID));
                TgkMapView tgkMapView2 = TgkMapView.this;
                tgkMapView2.setTGKMapEnabled(tgkMapView2.mTgkGameInfo.getSelectedCaseData().mainSw);
                TgkHelper.reportClickTgkCaseButton(TgkMapView.this.mContext, TgkMapView.this.mTgkGameInfo.getSelectedCaseData().packageName);
            }
        };
        this.tgkMultSeekBarViewOnChangeListener = new TgkMultSeekBarView.OnChangeListener() { // from class: cn.nubia.tgk.TgkMapView.9
            @Override // cn.nubia.tgk.widget.TgkMultSeekBarView.OnChangeListener
            public void onChanged(TgkMultSeekBarView tgkMultSeekBarView, int i) {
                TgkMapView.this.mGameInfoChanged = true;
                Log.e(TgkMapView.TAG, "tgkMultSeekBarViewOnChangeListener value=" + i);
                int i2 = i == 0 ? 2 : i == 1 ? 5 : 10;
                TgkMapView.this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[TgkMapView.this.mTgkMenuDirection] = i2;
                TgkMapView.this.mInputManagerProxy.setTgkRapidFireCount(i2, TgkMapView.this.mTgkKeyCodeArray[TgkMapView.this.mTgkMenuDirection]);
            }
        };
        this.mViewClickListener = new View.OnClickListener() { // from class: cn.nubia.tgk.TgkMapView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.lamp_list_view /* 2131362669 */:
                        TgkMapView.this.hidePopViewsExceptCaseListPopView();
                        Log.d(TgkMapView.TAG, "lamp_list_view=" + TgkMapView.this.isLampListViewShow);
                        TgkMapView.this.showLampListPopView(!r4.isLampListViewShow);
                        break;
                    case R.id.tgk_case_list_delete_textview /* 2131363446 */:
                    case R.id.tgk_case_list_view_delete /* 2131363451 */:
                        TgkMapView.this.deleteTgkCase();
                        TgkMapView.this.hideMoreBtnsView();
                        break;
                    case R.id.tgk_case_list_modify_textview /* 2131363447 */:
                    case R.id.tgk_case_list_view_modify /* 2131363453 */:
                        TgkMapView.this.renameTgkCase();
                        TgkMapView.this.hideMoreBtnsView();
                        break;
                    case R.id.tgk_case_list_preview_textview /* 2131363448 */:
                    case R.id.tgk_case_list_view_preview /* 2131363455 */:
                        TgkMapView.this.previewCase();
                        TgkMapView.this.hideTgkCaseListPopView();
                        TgkMapView.this.hideLampListPopView();
                        TgkMapView.this.hideMoreBtnsView();
                        break;
                    case R.id.tgk_case_list_view /* 2131363450 */:
                        TgkMapView.this.hidePopViewsExceptCaseListPopView();
                        Log.d(TgkMapView.TAG, "tgk_case_list_view=" + TgkMapView.this.isTgkCaseListViewShow);
                        TgkMapView.this.showTgkCaseListPopView(!r4.isTgkCaseListViewShow);
                        break;
                    case R.id.tgk_case_list_view_more /* 2131363454 */:
                        TgkMapView.this.clickMoreBtnsView();
                        break;
                    case R.id.tgk_case_show_sw /* 2131363464 */:
                        TgkHelper.setTgkCaseShowStates(TgkMapView.this.mContext, TgkMapView.this.mGameAppPackageName, ((ToggleButton) view).isChecked());
                        break;
                    case R.id.tgk_center_visual_effect_btn /* 2131363470 */:
                        TgkMapView.this.mTgkGameInfo.centerVisualEffectSw = ((ToggleButton) view).isChecked();
                        TgkMapView.this.mGameInfoChanged = true;
                        Log.d(TgkMapView.TAG, "tgk_center_visual_effect click checked=" + TgkMapView.this.mTgkGameInfo.centerVisualEffectSw);
                        TgkMapView.this.mTgkVisualEffectTransparencyView.setEnabled(TgkMapView.this.mTgkGameInfo.centerVisualEffectSw);
                        break;
                    case R.id.tgk_close_btn /* 2131363473 */:
                        TgkMapView.this.dismiss(true);
                        break;
                    case R.id.tgk_main_help /* 2131363482 */:
                        if (TgkMapView.this.mIClickListener != null) {
                            TgkMapView.this.mIClickListener.showHelp();
                            break;
                        }
                        break;
                    case R.id.tgk_main_switch /* 2131363483 */:
                        boolean isChecked = ((ToggleButton) view).isChecked();
                        TgkMapView.this.mTgkGameInfo.getSelectedCaseData().mainSw = isChecked;
                        TgkMapView.this.setTgkGameInfoChanged();
                        TgkMapView.this.onMainSwitchBtnChanged(isChecked);
                        TgkHelper.openLampScene(isChecked ? TgkMapView.this.mLampCaseSelectPosition : 0);
                        break;
                    case R.id.tgk_more_close_btn /* 2131363488 */:
                        TgkMapView.this.showMoreView(false);
                        TgkMapView.this.hideTgkCaseListPopView();
                        TgkMapView.this.hideLampListPopView();
                        TgkMapView.this.mIsMoreVsShow = false;
                        break;
                    case R.id.tgk_more_open_btn /* 2131363489 */:
                        TgkMapView.this.showMoreView(true);
                        TgkMapView.this.mIsMoreVsShow = true;
                        TgkHelper.setClickMoreButtonStatus(true);
                        break;
                    case R.id.tgk_rename_cancel /* 2131363508 */:
                        TgkMapView.this.hideRename();
                        break;
                    case R.id.tgk_rename_confirm /* 2131363509 */:
                        String obj = TgkMapView.this.mTgkCaseNameEditor.getText().toString();
                        if (!TgkHelper.hasSameTgkCaseName(TgkMapView.this.mTgkGameInfo, obj)) {
                            TgkMapView.this.mTgkGameInfo.getSelectedCaseData().showName = obj;
                            TgkMapView.this.setTgkGameInfoChanged();
                            TgkMapView.this.setTgkCaseTvTitle();
                            if (TgkMapView.this.mTgkCaseAdapter != null) {
                                TgkMapView.this.mTgkCaseAdapter.onCaseRenamed();
                            }
                            TgkMapView.this.hideRename();
                            break;
                        } else if (TgkMapView.this.mIClickListener != null) {
                            TgkMapView.this.mIClickListener.showToast(TgkMapView.this.mContext.getResources().getString(R.string.tgk_rename_repeat_prompt));
                            break;
                        }
                        break;
                    case R.id.tgk_top_visual_effect_btn /* 2131363521 */:
                        TgkMapView.this.mTgkGameInfo.topVisualEffectSw = ((ToggleButton) view).isChecked();
                        TgkMapView.this.mGameInfoChanged = true;
                        Log.d(TgkMapView.TAG, "tgk_top_visual_effect click isChecked=" + TgkMapView.this.mTgkGameInfo.topVisualEffectSw);
                        break;
                    case R.id.touch_haptic_feedback_sw /* 2131363567 */:
                        boolean isChecked2 = ((ToggleButton) view).isChecked();
                        TgkMapView.this.mTgkGameInfo.getSelectedCaseData().vibrateSw = isChecked2;
                        TgkMapView.this.setTgkGameInfoChanged();
                        TgkMapView.this.mInputManagerProxy.setTouchHapticFeedbackEnable(isChecked2);
                        break;
                }
            }
        };
        this.tgkTransparencyListener = new TgkVisualEffectTransparencyView.OnChangeListener() { // from class: cn.nubia.tgk.TgkMapView.14
            @Override // cn.nubia.tgk.widget.TgkVisualEffectTransparencyView.OnChangeListener
            public void onChanged(int i) {
                TgkMapView.this.mTgkGameInfo.centerVisualEffectTransparency = i;
                TgkMapView.this.mGameInfoChanged = true;
            }
        };
        this.movableImgViewGroupClosedListener = new MovableImageViewGroup.onClosedListener() { // from class: cn.nubia.tgk.TgkMapView.16
            @Override // cn.nubia.tgk.widget.MovableImageViewGroup.onClosedListener
            public void onClose() {
                TgkMapView.this.hidePreviewViewImg();
            }
        };
        this.sensitivityChangeListener = new TgkSensitivityView.OnChangeListener() { // from class: cn.nubia.tgk.TgkMapView.18
            @Override // cn.nubia.tgk.widget.TgkSensitivityView.OnChangeListener
            public void onChanged(TgkSensitivityView tgkSensitivityView, int i) {
                if (tgkSensitivityView == TgkMapView.this.mSensitivityViews[0]) {
                    TgkMapView.this.mTgkGameInfo.getSelectedCaseData().sensitivityArray[0] = i;
                    TgkMapView.this.setTgkGameInfoChanged();
                    TgkMapView.this.mInputManagerProxy.setTgkSensitivity(i, 137);
                } else if (tgkSensitivityView == TgkMapView.this.mSensitivityViews[1]) {
                    TgkMapView.this.mTgkGameInfo.getSelectedCaseData().sensitivityArray[1] = i;
                    TgkMapView.this.setTgkGameInfoChanged();
                    TgkMapView.this.mInputManagerProxy.setTgkSensitivity(i, 138);
                }
            }
        };
        this.mContext = context;
        this.mInputManagerProxy = new InputManagerProxy(context);
        this.mWorkHandler = new WorkHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustOptIdBySw() {
        for (int i = 0; i < TgkHelper.TGK_COUNT; i++) {
            boolean z = this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i];
            int i2 = this.mTgkGameInfo.getSelectedCaseData().optionArray[i];
            if (!z && i2 != 9) {
                this.mTgkGameInfo.getSelectedCaseData().optionArray[i] = 9;
                setTgkGameInfoChanged();
            }
        }
    }

    private void adjustTGKOptId(int i) {
        int i2 = this.mTgkKeyCodeArray[i];
        int i3 = this.mTgkGameInfo.getSelectedCaseData().optionArray[i];
        int i4 = this.mTgkGameInfo.getSelectedCaseData().setLinkFlagArray[i];
        boolean z = this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i];
        if (i4 == 1000) {
            if (this.mSupportedGameKeyLink && TgkHelper.getTgkLinkState(i2)) {
                this.mTgkGameInfo.getSelectedCaseData().optionArray[i] = 4;
                i3 = 4;
            }
            this.mTgkGameInfo.getSelectedCaseData().setLinkFlagArray[i] = 0;
            setTgkGameInfoChanged();
        }
        Log.e(TAG, "orgValue=" + i3);
        Log.e(TAG, "mTgkGameInfo.getSelectedCaseData().optionArray[direction]=" + this.mTgkGameInfo.getSelectedCaseData().optionArray[i]);
        if (4 == i3) {
            if (!this.mSupportedGameKeyLink || !TgkHelper.getTgkLinkState(i2)) {
                this.mTgkGameInfo.getSelectedCaseData().optionArray[i] = 0;
                setTgkGameInfoChanged();
            }
        } else if (9 == i3) {
            this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i] = false;
            setTgkGameInfoChanged();
        }
        if (z) {
            return;
        }
        this.mTgkGameInfo.getSelectedCaseData().optionArray[i] = 9;
        setTgkGameInfoChanged();
    }

    private void adjustTgkOptItems(boolean z) {
        int i = z ? 0 : 8;
        int i2 = !z ? 0 : 8;
        for (int i3 = 0; i3 < this.mTgkOptList.size(); i3++) {
            if (7 == i3 || 8 == i3) {
                this.mTgkOptList.get(i3).setVisibility(i);
            } else if (3 == i3) {
                this.mTgkOptList.get(i3).setVisibility(i2);
            } else if (4 == i3) {
                this.mTgkOptList.get(i3).setVisibility(i2);
            } else {
                this.mTgkOptList.get(i3).setVisibility(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickMoreBtnsView() {
        LinearLayout linearLayout = this.mMoreBtnsView;
        if (linearLayout != null) {
            if (linearLayout.getVisibility() != 0) {
                showMoreBtnsView();
            } else {
                hideMoreBtnsView();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [cn.nubia.tgk.TgkMapView$10] */
    private void deleteLinkOpt(int i) {
        int i2 = this.mTgkKeyCodeArray[i];
        final Bundle bundle = new Bundle();
        final String tgkLinkKey = TgkHelper.getTgkLinkKey(this.mTgkGameInfo.getSelectedCaseData().ID, this.mTgkGameInfo.getSelectedCaseData().state, i2);
        bundle.putString("touch_key_name", "" + tgkLinkKey);
        bundle.putString("packageName", this.mGameAppPackageName);
        new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.tgk.TgkMapView.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                try {
                    Log.d("GameKeyLink", "deleteLinkOpt keyCode=" + tgkLinkKey + ", mGameAppPackageName" + TgkMapView.this.mGameAppPackageName);
                    TgkMapView.this.mContext.getContentResolver().call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "touch_key_call", "delete_touch_key", bundle);
                    return null;
                } catch (Exception unused) {
                    Log.e(TgkMapView.TAG, "call method failed");
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteTgkCase() {
        hidePopViews();
        this.mTgkGameInfo.selectedTableId = 0;
        int i = this.mTgkGameInfo.selectedCasePosition;
        this.mTgkGameInfo.presetTableList.get(0).state |= 1;
        this.mTgkGameInfo.selectedCasePosition = 0;
        long j = this.mTgkGameInfo.importTableList.get(i).ID;
        String str = this.mTgkGameInfo.importTableList.get(i).shotPicture;
        this.mTgkGameInfo.importTableList.remove(i);
        TgkCaseListViewAdapter tgkCaseListViewAdapter = this.mTgkCaseAdapter;
        if (tgkCaseListViewAdapter != null) {
            tgkCaseListViewAdapter.onCaseDeleted();
        }
        DeleteAsyncTask deleteAsyncTask = new DeleteAsyncTask(this);
        deleteAsyncTask.setShotPicture(str);
        deleteAsyncTask.execute(1L, Long.valueOf(j));
        updateTgkCaseListViewOptionBtns();
        updateAllViewState(this.mTgkGameInfo.getSelectedCaseData().mainSw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doFBPressedWork(int i, int i2, boolean z, int i3, int i4) {
        if (this.mWorkHandler != null) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i4;
            message.obj = Boolean.valueOf(z);
            this.mWorkHandler.sendMessageDelayed(message, i3);
        }
    }

    private void getFolatViewRect() {
        for (int i = 0; i < TgkHelper.TGK_COUNT; i++) {
            switch (this.mTgkGameInfo.getSelectedCaseData().optionArray[i]) {
                case 0:
                case 5:
                case 6:
                case 7:
                    this.mOneTwoFBArray[i][0].getGlobalVisibleRect(this.mTgkGameInfo.getSelectedCaseData().pointsArray[i][0]);
                    setTgkGameInfoChanged();
                    break;
                case 1:
                case 2:
                case 8:
                    this.mOneTwoFBArray[i][0].getGlobalVisibleRect(this.mTgkGameInfo.getSelectedCaseData().pointsArray[i][0]);
                    this.mOneTwoFBArray[i][1].getGlobalVisibleRect(this.mTgkGameInfo.getSelectedCaseData().pointsArray[i][1]);
                    setTgkGameInfoChanged();
                    break;
                case 3:
                    this.mOneTwoFBArray[i][0].getGlobalVisibleRect(this.mTgkGameInfo.getSelectedCaseData().pointsArray[i][0]);
                    setTgkGameInfoChanged();
                    break;
            }
        }
    }

    private void getGameKeyLinkMotionState() {
        Bundle bundle = new Bundle();
        Context context = this.mContext;
        bundle.putString("packageName", this.mGameAppPackageName);
        try {
            Log.d("GameKeyLink", "packageName=" + this.mGameAppPackageName);
            Bundle call = context.getContentResolver().call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "touch_key_call", "isMacroEnable", bundle);
            boolean z = false;
            boolean z2 = call.getBoolean("isMacroEnable", false);
            boolean z3 = call.getBoolean("isPackageEnable", false);
            if (z2 && z3) {
                z = true;
            }
            this.mSupportedGameKeyLink = z;
            Log.d("GameKeyLink", "isMacroEnable=" + z2 + ", isPackageEnable=" + z3 + ", supportedGameKeyLink=" + this.mSupportedGameKeyLink);
        } catch (Exception unused) {
            Log.e(TAG, "call method failed");
        }
    }

    private void hideAllFloatBall() {
        hideFloatBall(0);
        hideFloatBall(1);
        hideFloatBall(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideAllLinkFb() {
        showLinkFb(0, 8);
        showLinkFb(1, 8);
        showLinkFb(2, 8);
    }

    private void hideDoubleFb(int i) {
        this.mOneTwoFBArray[i][0].setVisibility(8);
        this.mOneTwoFBArray[i][1].setVisibility(8);
    }

    private void hideFloatBall(int i) {
        hideDoubleFb(i);
        showLinkFb(i, 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLampListPopView() {
        if (this.isLampListViewShow) {
            showLampListPopView(false);
        }
    }

    private void hideListPopView(MotionEvent motionEvent) {
        int i = 0;
        Rect rect = new Rect(0, 0, 0, 0);
        if (this.mLampCaseListPopView != null) {
            this.mLampListView.getGlobalVisibleRect(rect);
            if (!isInView(rect, motionEvent)) {
                this.mLampCaseListPopView.getGlobalVisibleRect(rect);
                if (!isInView(rect, motionEvent)) {
                    hideLampListPopView();
                }
            }
        }
        if (this.mTgkCaseListPopView != null) {
            this.mTgkCaseListView.getGlobalVisibleRect(rect);
            if (!isInView(rect, motionEvent)) {
                this.mTgkCaseListPopView.getGlobalVisibleRect(rect);
                if (!isInView(rect, motionEvent)) {
                    hideTgkCaseListPopView();
                }
            }
        }
        if (this.mMoreBtnsView != null) {
            this.mMoreView.getGlobalVisibleRect(rect);
            if (!isInView(rect, motionEvent)) {
                this.mMoreBtnsView.getGlobalVisibleRect(rect);
                if (!isInView(rect, motionEvent)) {
                    hideMoreBtnsView();
                }
            }
        }
        if (this.mTgkMenuPopView == null || this.mIsTgkMultViewShow) {
            return;
        }
        int[] iArr = {R.id.tgk_l_menu, R.id.tgk_r_menu, R.id.tgk_m_menu};
        boolean z = false;
        while (i < 3) {
            this.mTgkMenuBtnArray[i] = (TgkMenuView) findViewById(iArr[i]);
            this.mTgkMenuBtnArray[i].getGlobalVisibleRect(rect);
            if (isInView(rect, motionEvent)) {
                return;
            }
            i++;
            z = true;
        }
        if (z) {
            this.mTgkMenuPopView.getGlobalVisibleRect(rect);
            if (isInView(rect, motionEvent)) {
                return;
            }
            hideTgkMenuPopView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideMoreBtnsView() {
        LinearLayout linearLayout = this.mMoreBtnsView;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    private void hideMoreView() {
        if (this.mIsMoreVsShow) {
            this.mMoreOpenBtn.setVisibility(0);
            this.mMoreVs.setVisibility(8);
            this.mIsMoreVsShow = false;
        }
    }

    private void hidePopViews() {
        hideRename();
        hidePreviewViewImg();
        hideTgkCaseListPopView();
        hideLampListPopView();
        hideTgkMenuPopView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePopViewsExceptCaseListPopView() {
        hideRename();
        hidePreviewViewImg();
        hideTgkMenuPopView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePopViewsExceptMenuPopView() {
        hideRename();
        hidePreviewViewImg();
        hideTgkCaseListPopView();
        hideLampListPopView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePreviewViewImg() {
        if (this.isPreviewViewImgShow) {
            showPreviewViewImg(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideRename() {
        if (this.isRenameViewStubShow) {
            View findViewById = findViewById(R.id.tgk_rename_bottom_view);
            if (findViewById != null) {
                new LinearLayout.LayoutParams(-1, 0);
                findViewById.setVisibility(8);
            }
            this.mRenameViewStub.setVisibility(8);
            this.isRenameViewStubShow = false;
            hideKeyboard();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTgkCaseListPopView() {
        if (this.isTgkCaseListViewShow) {
            showTgkCaseListPopView(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTgkMenuPopView() {
        int i = this.mTgkMenuDirection;
        if (i > -1) {
            showTgkMenuPopView(i);
        }
    }

    private void initData(String str) {
        int i = this.mContext.getResources().getConfiguration().orientation == 2 ? 1 : 0;
        this.mIsLandscape = i;
        TgkGameInfo gameInfo = TgkHelper.getGameInfo(this.mContext, this.mGameAppPackageName, i);
        this.mTgkGameInfo = gameInfo;
        TgkHelper.updateValidRect(gameInfo.getSelectedCaseData(), this.mIsLandscape);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        TgkHelper.queryTgkLinkState(contentResolver, str, this.mTgkGameInfo.getSelectedCaseData().ID, this.mTgkGameInfo.getSelectedCaseData().state);
        adjustTGKOptId(0);
        adjustTGKOptId(1);
        adjustTGKOptId(2);
        int tgkDisableOpt = TgkHelper.getTgkDisableOpt(contentResolver, this.mGameAppPackageName);
        int[] iArr = {1, 2, 4, 8, 16, 32, 64};
        for (int i2 = 0; i2 < 7; i2++) {
            if ((iArr[i2] & tgkDisableOpt) > 0) {
                this.mTgkDisableFlag[i2] = false;
                if (i2 == this.mTgkGameInfo.getSelectedCaseData().optionArray[0]) {
                    this.mTgkGameInfo.getSelectedCaseData().optionArray[0] = 0;
                    setTgkGameInfoChanged();
                }
                if (i2 == this.mTgkGameInfo.getSelectedCaseData().optionArray[1]) {
                    this.mTgkGameInfo.getSelectedCaseData().optionArray[1] = 0;
                    setTgkGameInfoChanged();
                }
                int i3 = this.mTgkGameInfo.getSelectedCaseData().optionArray[2];
                if (i2 == i3) {
                    this.mTgkGameInfo.getSelectedCaseData().optionArray[2] = 0;
                    setTgkGameInfoChanged();
                } else if (1 == i2 && i3 == 8) {
                    this.mTgkGameInfo.getSelectedCaseData().optionArray[2] = 7;
                    setTgkGameInfoChanged();
                    this.mTgkDisableFlag[8] = false;
                }
            }
        }
        this.mLampCaseSelectPosition = TgkHelper.queryLameCastSelect(this.mContext, this.mGameAppPackageName);
        TgkGameInfo tgkGameInfo = this.mTgkGameInfo;
        if (tgkGameInfo == null || !tgkGameInfo.getSelectedCaseData().mainSw) {
            TgkHelper.openLampScene(0);
        } else {
            TgkHelper.openLampScene(this.mLampCaseSelectPosition);
        }
    }

    private void initFloatBallView() {
        FakeFloatView fakeFloatView = (FakeFloatView) findViewById(R.id.left_ball_one);
        fakeFloatView.setFingerEventListener(new FingerEventListener());
        fakeFloatView.setFingerLeftOrRight(0, false);
        FakeFloatView fakeFloatView2 = (FakeFloatView) findViewById(R.id.left_ball_two);
        fakeFloatView2.setFingerEventListener(new FingerEventListener());
        FakeFloatView fakeFloatView3 = (FakeFloatView) findViewById(R.id.right_ball_one);
        fakeFloatView3.setFingerEventListener(new FingerEventListener());
        FakeFloatView fakeFloatView4 = (FakeFloatView) findViewById(R.id.right_ball_two);
        fakeFloatView4.setFingerEventListener(new FingerEventListener());
        FakeFloatView fakeFloatView5 = (FakeFloatView) findViewById(R.id.middle_ball_one);
        fakeFloatView5.setFingerEventListener(new FingerEventListener());
        FakeFloatView fakeFloatView6 = (FakeFloatView) findViewById(R.id.middle_ball_two);
        fakeFloatView6.setFingerEventListener(new FingerEventListener());
        FakeFloatView fakeFloatView7 = (FakeFloatView) findViewById(R.id.l_link_ball);
        FakeFloatView fakeFloatView8 = (FakeFloatView) findViewById(R.id.r_link_ball);
        FakeFloatView fakeFloatView9 = (FakeFloatView) findViewById(R.id.m_link_ball);
        fakeFloatView7.setFingerEventListener(new FingerEventListener());
        fakeFloatView8.setFingerEventListener(new FingerEventListener());
        fakeFloatView9.setFingerEventListener(new FingerEventListener());
        fakeFloatView7.setMoveEnabled(false);
        fakeFloatView8.setMoveEnabled(false);
        fakeFloatView9.setMoveEnabled(false);
        this.mOneTwoFBArray = new FakeFloatView[][]{new FakeFloatView[]{fakeFloatView, fakeFloatView2}, new FakeFloatView[]{fakeFloatView3, fakeFloatView4}, new FakeFloatView[]{fakeFloatView5, fakeFloatView6}};
        this.mLinkFBArray = new FakeFloatView[]{fakeFloatView7, fakeFloatView8, fakeFloatView9};
        this.mFBArray = new FakeFloatView[][]{new FakeFloatView[]{fakeFloatView, null, fakeFloatView3, null, fakeFloatView5, null}, new FakeFloatView[]{fakeFloatView, fakeFloatView2, fakeFloatView3, fakeFloatView4, fakeFloatView5, fakeFloatView6}, new FakeFloatView[]{fakeFloatView, fakeFloatView2, fakeFloatView3, fakeFloatView4, fakeFloatView5, fakeFloatView6}, new FakeFloatView[]{fakeFloatView, null, fakeFloatView3, null, fakeFloatView5, null}, new FakeFloatView[]{fakeFloatView7, null, fakeFloatView8, null, fakeFloatView9, null}, new FakeFloatView[]{fakeFloatView, null, fakeFloatView3, null, fakeFloatView5, null}, new FakeFloatView[]{fakeFloatView, null, fakeFloatView3, null, fakeFloatView5, null}, new FakeFloatView[]{fakeFloatView, null, fakeFloatView3, null, fakeFloatView5, null}, new FakeFloatView[]{fakeFloatView, fakeFloatView2, fakeFloatView3, fakeFloatView4, fakeFloatView5, fakeFloatView6}, new FakeFloatView[]{null, null, null, null, null, null}};
    }

    private void initMainSwitchBtn() {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.tgk_main_switch);
        this.mSwitchBtn = toggleButton;
        toggleButton.setChecked(this.mTgkGameInfo.getSelectedCaseData().mainSw);
        this.mSwitchBtn.setOnClickListener(this.mViewClickListener);
    }

    private void initMoreView() {
        if (this.mMoreRelativeLayout == null) {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.tgk_more_view);
            this.mMoreRelativeLayout = relativeLayout;
            if (relativeLayout != null) {
                relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.TgkMapView.12
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        TgkMapView.this.hideMoreBtnsView();
                    }
                });
            }
        }
        if (this.mLampListView == null) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lamp_list_view);
            this.mLampListView = linearLayout;
            if (linearLayout != null) {
                linearLayout.setOnClickListener(this.mViewClickListener);
                this.mLampListView.setBackgroundResource(R.drawable.tgk_case_list_view_bg_down);
            }
        }
        if (this.mTgkCaseListView == null) {
            LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.tgk_case_list_view);
            this.mTgkCaseListView = linearLayout2;
            linearLayout2.setOnClickListener(this.mViewClickListener);
            this.mTgkCaseListView.setBackgroundResource(R.drawable.tgk_case_list_view_bg_down);
            ScrollView scrollView = (ScrollView) findViewById(R.id.tgk_more_vs_scrollview);
            this.mMoreVScrollView = scrollView;
            if (scrollView != null) {
                scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: cn.nubia.tgk.TgkMapView.13
                    @Override // android.view.View.OnScrollChangeListener
                    public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                        if (i2 > TgkMapView.this.getResources().getDimensionPixelSize(R.dimen.tgk_scroll_hide_popView)) {
                            TgkMapView.this.hideTgkCaseListPopView();
                        }
                        if (i2 < TgkMapView.this.getResources().getDimensionPixelSize(R.dimen.tgk_scroll_lamp_hide_popView)) {
                            TgkMapView.this.hideLampListPopView();
                        }
                    }
                });
            }
            TextView textView = (TextView) findViewById(R.id.tgk_case_title);
            this.mTgkCaseTitle = textView;
            textView.setSelected(true);
            TextView textView2 = (TextView) findViewById(R.id.lamp_title);
            this.mLampCaseTitle = textView2;
            if (textView2 != null) {
                textView2.setSelected(true);
                setLampCaseTvTitle();
            }
            setTgkCaseTvTitle();
            this.mPreviewView = findViewById(R.id.tgk_case_list_view_preview);
            this.mMoreBtnsView = (LinearLayout) findViewById(R.id.tgk_more_btns_view);
            this.mPreviewTextView = (TextView) findViewById(R.id.tgk_case_list_preview_textview);
            this.mDeleteTextView = (TextView) findViewById(R.id.tgk_case_list_delete_textview);
            this.mModifyTextView = (TextView) findViewById(R.id.tgk_case_list_modify_textview);
            this.mDeleteView = findViewById(R.id.tgk_case_list_view_delete);
            this.mPreviewViewLine = findViewById(R.id.tgk_case_list_view_preview_line);
            this.mDeleteViewLine = findViewById(R.id.tgk_case_list_view_delete_line);
            this.mModifyView = findViewById(R.id.tgk_case_list_view_modify);
            this.mMoreView = findViewById(R.id.tgk_case_list_view_more);
            TextView textView3 = this.mPreviewTextView;
            if (textView3 != null) {
                textView3.setOnClickListener(this.mViewClickListener);
            }
            TextView textView4 = this.mDeleteTextView;
            if (textView4 != null) {
                textView4.setOnClickListener(this.mViewClickListener);
            }
            TextView textView5 = this.mModifyTextView;
            if (textView5 != null) {
                textView5.setOnClickListener(this.mViewClickListener);
            }
            View view = this.mPreviewView;
            if (view != null) {
                view.setOnClickListener(this.mViewClickListener);
            }
            View view2 = this.mDeleteView;
            if (view2 != null) {
                view2.setOnClickListener(this.mViewClickListener);
            }
            View view3 = this.mModifyView;
            if (view3 != null) {
                view3.setOnClickListener(this.mViewClickListener);
            }
            View view4 = this.mMoreView;
            if (view4 != null) {
                view4.setOnClickListener(this.mViewClickListener);
            }
            hideMoreBtnsView();
        }
        if (this.mSensitivityViews == null) {
            int i = 0;
            this.mSensitivityViews = new TgkSensitivityView[]{(TgkSensitivityView) findViewById(R.id.tgk_sensitivity_view_l), (TgkSensitivityView) findViewById(R.id.tgk_sensitivity_view_r)};
            while (true) {
                TgkSensitivityView[] tgkSensitivityViewArr = this.mSensitivityViews;
                if (i >= tgkSensitivityViewArr.length) {
                    break;
                }
                tgkSensitivityViewArr[i].setChangedListener(this.sensitivityChangeListener);
                setSensitivityState(i);
                i++;
            }
        }
        if (this.mTgkHapticFeedbackSw == null) {
            ToggleButton toggleButton = (ToggleButton) findViewById(R.id.touch_haptic_feedback_sw);
            this.mTgkHapticFeedbackSw = toggleButton;
            toggleButton.setChecked(this.mTgkGameInfo.getSelectedCaseData().vibrateSw);
            this.mTgkHapticFeedbackSw.setOnClickListener(this.mViewClickListener);
        }
        if (this.mTgkCaseShowSw == null) {
            ToggleButton toggleButton2 = (ToggleButton) findViewById(R.id.tgk_case_show_sw);
            this.mTgkCaseShowSw = toggleButton2;
            toggleButton2.setChecked(TgkHelper.getTgkCaseShowStates(this.mContext, this.mGameAppPackageName));
            this.mTgkCaseShowSw.setOnClickListener(this.mViewClickListener);
        }
        if (this.mTgkVisualEffectTransparencyView == null) {
            TgkTopVisualEffectView tgkTopVisualEffectView = (TgkTopVisualEffectView) findViewById(R.id.tgk_top_visual_effect_view);
            tgkTopVisualEffectView.setChecked(this.mTgkGameInfo.topVisualEffectSw);
            tgkTopVisualEffectView.setClickListener(this.mViewClickListener);
            TgkCenterVisualEffectView tgkCenterVisualEffectView = (TgkCenterVisualEffectView) findViewById(R.id.tgk_center_visual_effect_view);
            tgkCenterVisualEffectView.setChecked(this.mTgkGameInfo.centerVisualEffectSw);
            tgkCenterVisualEffectView.setClickListener(this.mViewClickListener);
            TgkVisualEffectTransparencyView tgkVisualEffectTransparencyView = (TgkVisualEffectTransparencyView) findViewById(R.id.tgk_visual_effect_transparency_view);
            this.mTgkVisualEffectTransparencyView = tgkVisualEffectTransparencyView;
            tgkVisualEffectTransparencyView.setEnabled(this.mTgkGameInfo.centerVisualEffectSw);
            this.mTgkVisualEffectTransparencyView.setChangedListener(this.tgkTransparencyListener);
            this.mTgkVisualEffectTransparencyView.setProgress(this.mTgkGameInfo.centerVisualEffectTransparency);
        }
    }

    private void initRotationGuidancePanel(final int i, final int i2, final int i3) {
        this.mNoRemindPanel = (LinearLayout) findViewById(R.id.noremindpanel);
        this.mKnowButton = (Button) findViewById(R.id.knowButton);
        this.mNoRemindImageView = (ImageView) findViewById(R.id.noremindimageview);
        this.mNoRemindPanel.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.TgkMapView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TgkMapView.this.isNoRemind = !r2.isNoRemind;
                TgkMapView.this.mNoRemindImageView.setImageResource(TgkMapView.this.isNoRemind ? R.drawable.tgk_no_remind_light : R.drawable.tgk_no_remind_normal);
            }
        });
        this.mKnowButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.TgkMapView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TgkMapView.this.isNoRemind) {
                    TgkHelper.setNoRemindStatus(TgkMapView.this.mContext.getContentResolver());
                }
                TgkMapView.this.onRotationGuidDisappeared();
                if (TgkMapView.this.mTgkGameInfo == null || TgkMapView.this.mTgkGameInfo.getSelectedCaseData() == null) {
                    return;
                }
                boolean tgkLinkCaseState = TgkHelper.getTgkLinkCaseState(TgkMapView.this.mContext, TgkMapView.this.mGameAppPackageName, TgkMapView.this.mTgkGameInfo.getSelectedCaseData().ID, TgkMapView.this.mTgkGameInfo.getSelectedCaseData().state, i3);
                TgkMapView tgkMapView = TgkMapView.this;
                tgkMapView.showGameKeyLinkMotion(tgkMapView.mContext, i3, tgkLinkCaseState, i, i2);
                if (tgkLinkCaseState) {
                    return;
                }
                TgkMapView.this.updatNonFloatViewDisplay(true);
                TgkMapView.this.updateFloatViewPos();
                TgkMapView.this.updateFbViewState(i);
                TgkMapView.this.setTgkMenuBtnTitle(i);
                TgkMapView.this.showLinkFb(i, 0);
            }
        });
    }

    private void initTgkMenuPopView() {
        this.mTgkMenuPopView = (TgkMenuPopView) findViewById(R.id.tgk_pop_menu_view);
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_single));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_copy));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_up_down));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_move_vision));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_link));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_long_press));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_mult_clicks));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_slide_single));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_slide_copy));
        this.mTgkOptList.add((TgkCustomRadioButton) findViewById(R.id.tgk_off));
        TgkCustomRadioButton.OnClickListener onClickListener = new TgkCustomRadioButton.OnClickListener() { // from class: cn.nubia.tgk.TgkMapView.6
            @Override // cn.nubia.tgk.widget.TgkCustomRadioButton.OnClickListener
            public void onClick(View view) {
                int i;
                int i2;
                switch (view.getId()) {
                    case R.id.tgk_copy /* 2131363474 */:
                        i = 1;
                        break;
                    case R.id.tgk_link /* 2131363479 */:
                        i = 4;
                        break;
                    case R.id.tgk_long_press /* 2131363480 */:
                        i = 5;
                        break;
                    case R.id.tgk_move_vision /* 2131363494 */:
                        i = 3;
                        break;
                    case R.id.tgk_mult_clicks /* 2131363495 */:
                        i = 6;
                        break;
                    case R.id.tgk_off /* 2131363499 */:
                        i = 9;
                        break;
                    case R.id.tgk_single /* 2131363515 */:
                        i = 0;
                        break;
                    case R.id.tgk_slide_copy /* 2131363518 */:
                        i = 8;
                        break;
                    case R.id.tgk_slide_single /* 2131363519 */:
                        i = 7;
                        break;
                    case R.id.tgk_up_down /* 2131363523 */:
                        i = 2;
                        break;
                    default:
                        i = -1;
                        break;
                }
                if (-1 == i || i == TgkMapView.this.mLastTgkOptId) {
                    return;
                }
                ((TgkCustomRadioButton) TgkMapView.this.mTgkOptList.get(TgkMapView.this.mLastTgkOptId)).setChecked(false);
                ((TgkCustomRadioButton) TgkMapView.this.mTgkOptList.get(i)).setChecked(true);
                int i3 = TgkMapView.this.mTgkMenuDirection;
                if (i != 4) {
                    TgkMapView.this.mTgkGameInfo.getSelectedCaseData().optionArray[i3] = i;
                    TgkMapView.this.mTgkGameInfo.getSelectedCaseData().setLinkFlagArray[i3] = 0;
                    TgkMapView.this.setTgkGameInfoChanged();
                    int i4 = TgkMapView.this.mTgkKeyCodeArray[i3];
                    if (i == 6) {
                        TgkMapView.this.mInputManagerProxy.setTgkRapidFireCount(TgkMapView.this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[i3], i4);
                    }
                    if (9 == i) {
                        TgkMapView.this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i3] = false;
                        TgkMapView.this.mInputManagerProxy.setSubTgkEnable(i3, false);
                    }
                }
                if (9 == TgkMapView.this.mLastTgkOptId) {
                    TgkMapView.this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i3] = true;
                    TgkMapView.this.setTgkGameInfoChanged();
                    TgkMapView.this.mInputManagerProxy.setSubTgkEnable(i3, true);
                }
                TgkMapView tgkMapView = TgkMapView.this;
                tgkMapView.onTGKOptChanged(tgkMapView.mLastTgkOptId, i, i3);
                TgkMapView.this.mLastTgkOptId = i;
                String title = ((TgkCustomRadioButton) view).getTitle();
                if (i != 4) {
                    TgkMapView.this.mTgkMenuBtnArray[i3].setTitle(title);
                } else {
                    TgkMapView.this.mTgkMenuBtnArray[i3].setTitle(TgkHelper.getTgkLinkCaseName(TgkMapView.this.mTgkKeyCodeArray[i3]));
                }
                if (i == 3) {
                    for (int i5 = 0; i5 < TgkHelper.TGK_COUNT; i5++) {
                        if (i3 != i5 && (i2 = TgkMapView.this.mTgkGameInfo.getSelectedCaseData().optionArray[i5]) == 3) {
                            TgkMapView.this.mTgkGameInfo.getSelectedCaseData().setLinkFlagArray[i5] = 0;
                            TgkMapView.this.mTgkGameInfo.getSelectedCaseData().optionArray[i5] = 0;
                            TgkMapView.this.mTgkMenuBtnArray[i5].setTitle(((TgkCustomRadioButton) TgkMapView.this.mTgkOptList.get(0)).getTitle());
                            TgkMapView.this.onTGKOptChanged(i2, 0, i5);
                        }
                    }
                }
                if (i != 6) {
                    TgkMapView.this.hideTgkMenuPopView();
                } else if (TgkHelper.getISUseMultCaseStates(TgkMapView.this.mContext)) {
                    TgkMapView.this.hideTgkMenuPopView();
                } else {
                    TgkMapView.this.showTgkMultView();
                    TgkHelper.setUseMultCaseStates(TgkMapView.this.mContext, true);
                }
            }

            @Override // cn.nubia.tgk.widget.TgkCustomRadioButton.OnClickListener
            public void onDisableClick(View view) {
                ToastUtil.showTgkToast(TgkMapView.this.mContext, TgkMapView.this.mContext.getResources().getString(R.string.tgk_disabled_prompt));
            }
        };
        TgkCustomRadioButton.OnClickSettingListener onClickSettingListener = new TgkCustomRadioButton.OnClickSettingListener() { // from class: cn.nubia.tgk.TgkMapView.7
            @Override // cn.nubia.tgk.widget.TgkCustomRadioButton.OnClickSettingListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.tgk_link) {
                    TgkMapView tgkMapView = TgkMapView.this;
                    tgkMapView.showGameKeyLinkMotion(tgkMapView.mContext, TgkMapView.this.mTgkKeyCodeArray[TgkMapView.this.mTgkMenuDirection], true, TgkMapView.this.mTgkMenuDirection, TgkMapView.this.mLastTgkOptId);
                } else {
                    if (id != R.id.tgk_mult_clicks) {
                        return;
                    }
                    TgkMapView.this.showTgkMultView();
                }
            }
        };
        Iterator<TgkCustomRadioButton> it = this.mTgkOptList.iterator();
        int i = 0;
        while (it.hasNext()) {
            TgkCustomRadioButton next = it.next();
            next.setOnClickListener(onClickListener);
            next.setEnabled(this.mTgkDisableFlag[i]);
            i++;
        }
        this.mTgkOptList.get(4).setOnClickSettingListener(onClickSettingListener);
        this.mTgkOptList.get(4).setShowSettinView(true);
        this.mTgkOptList.get(6).setOnClickSettingListener(onClickSettingListener);
        this.mTgkOptList.get(6).setShowSettinView(true);
        if (!this.mSupportedGameKeyLink) {
            this.mTgkOptList.get(4).setEnabled(false);
        }
        if (this.mSupportTgkMoveVision) {
            return;
        }
        this.mTgkOptList.get(3).setEnabled(false);
    }

    private void initTgkMenuView() {
        int[] iArr = {R.id.tgk_l_menu, R.id.tgk_r_menu, R.id.tgk_m_menu};
        TgkMenuView.OnClickListener onClickListener = new TgkMenuView.OnClickListener() { // from class: cn.nubia.tgk.TgkMapView.3
            @Override // cn.nubia.tgk.widget.TgkMenuView.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                int i = id != R.id.tgk_l_menu ? id != R.id.tgk_m_menu ? id != R.id.tgk_r_menu ? -1 : 1 : 2 : 0;
                if (-1 < i) {
                    TgkMapView.this.hidePopViewsExceptMenuPopView();
                    TgkMapView.this.showTgkMenuPopView(i);
                }
            }
        };
        for (int i = 0; i < 3; i++) {
            this.mTgkMenuBtnArray[i] = (TgkMenuView) findViewById(iArr[i]);
            this.mTgkMenuBtnArray[i].setEnabled(this.mTgkGameInfo.getSelectedCaseData().mainSw);
            this.mTgkMenuBtnArray[i].setMarquee(this.mTgkGameInfo.getSelectedCaseData().mainSw);
            if (2 != i || TgkHelper.IS_SUPPORT_MIDDLE_TGK) {
                setTgkMenuBtnTitle(i);
                this.mTgkMenuBtnArray[i].setOnClickListener(onClickListener);
            } else {
                this.mTgkMenuBtnArray[i].setVisibility(8);
            }
        }
    }

    private void initView() {
        Context context;
        int i;
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.tgk_map_view, this);
        this.mRootView = inflate;
        MarqueeTextView marqueeTextView = (MarqueeTextView) inflate.findViewById(R.id.tgk_title);
        if (TgkHelper.isSPRDDevice()) {
            context = this.mContext;
            i = R.string.tgk_msg_sprd;
        } else {
            context = this.mContext;
            i = R.string.tgk_msg;
        }
        marqueeTextView.setText(context.getString(i));
        this.mTgkMainView = (FrameLayout) this.mRootView.findViewById(R.id.tgk_main_view);
        this.mTgkHeadView = (LinearLayout) this.mRootView.findViewById(R.id.tgk_head_view);
        View findViewById = this.mRootView.findViewById(R.id.tgk_main_help);
        this.helpView = findViewById;
        findViewById.setOnClickListener(this.mViewClickListener);
        if (TgkHelper.IS_SUPPORT_MIDDLE_TGK) {
            this.mTgkHeadView.setBackgroundResource(R.drawable.tgk_head_view_bg_middle);
        }
        initMainSwitchBtn();
        ImageButton imageButton = (ImageButton) this.mRootView.findViewById(R.id.tgk_close_btn);
        this.mColseBtn = imageButton;
        imageButton.setOnClickListener(this.mViewClickListener);
        TgkHelper.setClickMoreButtonStatus(false);
        this.mMoreVs = (ViewStub) findViewById(R.id.tgk_more_vs);
        initTgkMenuView();
        View findViewById2 = findViewById(R.id.tgk_more_open_btn);
        this.mMoreOpenBtn = findViewById2;
        findViewById2.setOnClickListener(this.mViewClickListener);
        initFloatBallView();
        this.mRenameViewStub = (ViewStub) findViewById(R.id.tgk_rename_vs);
        this.mTgkMultViewStub = (ViewStub) findViewById(R.id.tgk_mult_view_vs);
        this.mIsTgkMultViewShow = false;
        this.mPreviewViewImgVs = (ViewStub) findViewById(R.id.tgk_preview_img_vs);
        this.mRotationGuidancePanel = (ViewStub) findViewById(R.id.tgk_rotation_guidance_panel);
    }

    private boolean isInView(Rect rect, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        return rawX >= ((float) rect.left) && rawX <= ((float) rect.right) && rawY >= ((float) rect.top) && rawY <= ((float) rect.bottom);
    }

    private void lampListPopViewScrollToPosition(int i, int i2) {
        this.mLampCaseListPopView.scrollToPositionWithOffset((i * 4) + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMainSwitchBtnChanged(boolean z) {
        updateAllViewState(z);
        setTGKMapEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRotationGuidDisappeared() {
        this.mRotationGuidancePanel.setVisibility(8);
        updateAllViewState(this.mTgkGameInfo.getSelectedCaseData().mainSw);
        setTGKMapEnabled(this.mTgkGameInfo.getSelectedCaseData().mainSw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTGKOptChanged(int i, int i2, int i3) {
        updateTgkMenuBtnsBackgroundResource(this.mTgkMenuDirection, i2, true);
        Log.d(TAG, "onTGKOptChanged oriopt=" + i + ", newOpt=" + i2 + ", direction=" + i3);
        if (i == 0 || 5 == i || 6 == i || 7 == i || 3 == i) {
            if (1 == i2 || 8 == i2) {
                showOneTwoFb(i3, 1);
            } else if (2 == i2) {
                showOneTwoFb(i3, 2);
            } else if (4 == i2) {
                selecteLinkOpt(i3, i);
                hideDoubleFb(i3);
            } else if (9 == i2) {
                hideFloatBall(i3);
                setSensitivityEnabled(i3);
            } else if (3 == i && i2 != 3) {
                showSingleFb(i3, i2);
            } else if (3 != i && i2 == 3) {
                showSingleFb(i3, i2);
            }
            if (5 == i) {
                this.mOneTwoFBArray[0][0].setPressed(false);
                this.mOneTwoFBArray[1][0].setPressed(false);
                this.mOneTwoFBArray[2][0].setPressed(false);
            }
        } else if (1 == i || 8 == i) {
            if (i2 == 0 || 5 == i2 || 6 == i2 || 7 == i2 || 3 == i2) {
                showSingleFb(i3, i2);
            } else if (2 == i2) {
                showOneTwoFb(i3, 2);
            } else if (4 == i2) {
                selecteLinkOpt(i3, i);
                hideDoubleFb(i3);
            } else if (9 == i2) {
                hideFloatBall(i3);
                setSensitivityEnabled(i3);
            }
        } else if (2 == i) {
            if (i2 == 0 || 5 == i2 || 6 == i2 || 7 == i2 || 3 == i2) {
                showSingleFb(i3, i2);
            } else if (1 == i2 || 8 == i2) {
                showOneTwoFb(i3, 1);
            } else if (4 == i2) {
                selecteLinkOpt(i3, i);
                hideDoubleFb(i3);
            } else if (9 == i2) {
                hideFloatBall(i3);
                setSensitivityEnabled(i3);
            }
        } else if (4 == i) {
            if (i3 == 2) {
                this.mInputManagerProxy.setMiddleTgkLinkFunction(0);
            } else if (i3 == 0) {
                this.mInputManagerProxy.setLeftTgkLinkFunction(0);
            } else if (i3 == 1) {
                this.mInputManagerProxy.setRightTgkLinkFunction(0);
            }
            showLinkFb(i3, 8);
            if (9 == i2) {
                hideFloatBall(i3);
                setSensitivityEnabled(i3);
            } else if (1 == i2 || 8 == i2) {
                showOneTwoFb(i3, 1);
            } else if (2 == i2) {
                showOneTwoFb(i3, 2);
            } else if (i2 == 0 || 5 == i2 || 6 == i2 || 7 == i2) {
                showSingleFb(i3, i2);
            } else if (3 == i2) {
                showSingleFb(i3, i2);
            }
        } else if (9 == i) {
            if (4 == i2) {
                selecteLinkOpt(i3, i);
                hideDoubleFb(i3);
            } else if (i2 == 0 || 5 == i2 || 6 == i2 || 7 == i2) {
                showSingleFb(i3, i2);
            } else if (1 == i2 || 8 == i2) {
                showOneTwoFb(i3, 1);
            } else if (2 == i2) {
                showOneTwoFb(i3, 2);
            } else if (3 == i2) {
                showSingleFb(i3, i2);
            }
            setSensitivityEnabled(i3);
        }
        updateFloatViewPos();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void previewCase() {
        hideRename();
        if (this.isPreviewViewImgShow) {
            showPreviewViewImg(false);
            return;
        }
        if (this.mTgkGameInfo.picture != null) {
            showPreviewViewImg(true);
            if (this.mPreviewImgViewGroup == null) {
                MovableImageViewGroup movableImageViewGroup = (MovableImageViewGroup) findViewById(R.id.tgk_move_img_vg);
                this.mPreviewImgViewGroup = movableImageViewGroup;
                movableImageViewGroup.setListener(this.movableImgViewGroupClosedListener);
            }
            this.mPreviewImgViewGroup.setImageBitmap(this.mTgkGameInfo.picture);
        }
    }

    private void processLongPress() {
        FakeFloatView[][] fakeFloatViewArr;
        for (int i = 0; i < TgkHelper.TGK_COUNT; i++) {
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[i] == 5 && (fakeFloatViewArr = this.mOneTwoFBArray) != null) {
                fakeFloatViewArr[i][0].setPressed(false);
            }
        }
    }

    private void removeCallbacksAndMessages() {
        WorkHandler workHandler = this.mWorkHandler;
        if (workHandler != null) {
            workHandler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renameTgkCase() {
        hidePopViews();
        if (this.isRenameViewStubShow) {
            return;
        }
        this.mRenameViewStub.setVisibility(0);
        final Button button = (Button) findViewById(R.id.tgk_rename_confirm);
        Button button2 = (Button) findViewById(R.id.tgk_rename_cancel);
        button.setOnClickListener(this.mViewClickListener);
        button2.setOnClickListener(this.mViewClickListener);
        final TextView textView = (TextView) findViewById(R.id.tgk_case_modify_title);
        if (this.mTgkCaseNameEditor == null) {
            this.mTgkCaseNameEditor = (EditText) findViewById(R.id.tgk_case_modify_name);
        }
        this.mTgkCaseNameEditor.setText(this.mTgkGameInfo.getSelectedCaseData().showName);
        this.mTgkCaseNameEditor.selectAll();
        this.mTgkCaseNameEditor.addTextChangedListener(new TextWatcher() { // from class: cn.nubia.tgk.TgkMapView.15
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj = TgkMapView.this.mTgkCaseNameEditor.getText().toString();
                if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj.trim())) {
                    Button button3 = button;
                    if (button3 != null) {
                        button3.setEnabled(false);
                        button.setClickable(false);
                        button.setTextColor(TgkMapView.this.mContext.getResources().getColor(R.color.tgk_text_white_30));
                        button.setBackground(TgkMapView.this.mContext.getResources().getDrawable(R.drawable.tgk_button_bg_disable));
                    }
                    TextView textView2 = textView;
                    if (textView2 != null) {
                        textView2.setText(TgkMapView.this.mContext.getResources().getText(R.string.gamepad_null_case_name));
                        textView.setTextColor(TgkMapView.this.mContext.getResources().getColor(R.color.tgk_edit_case_has_color));
                        return;
                    }
                    return;
                }
                if (TgkHelper.hasSameTgkCaseName(TgkMapView.this.mTgkGameInfo, obj)) {
                    Button button4 = button;
                    if (button4 != null) {
                        button4.setEnabled(false);
                        button.setClickable(false);
                        button.setTextColor(TgkMapView.this.mContext.getResources().getColor(R.color.tgk_text_white_30));
                        button.setBackground(TgkMapView.this.mContext.getResources().getDrawable(R.drawable.tgk_button_bg_disable));
                    }
                    TextView textView3 = textView;
                    if (textView3 != null) {
                        textView3.setText(TgkMapView.this.mContext.getResources().getText(R.string.tgk_edit_case_has_title));
                        textView.setTextColor(TgkMapView.this.mContext.getResources().getColor(R.color.tgk_edit_case_has_color));
                        return;
                    }
                    return;
                }
                Button button5 = button;
                if (button5 != null) {
                    button5.setEnabled(true);
                    button.setClickable(true);
                    button.setTextColor(TgkMapView.this.mContext.getResources().getColor(R.color.tgk_text_press));
                    button.setBackground(TgkMapView.this.mContext.getResources().getDrawable(R.drawable.tgk_button_bg));
                }
                TextView textView4 = textView;
                if (textView4 != null) {
                    textView4.setText(TgkMapView.this.mContext.getResources().getText(R.string.tgk_case_modify_view_title));
                    textView.setTextColor(TgkMapView.this.mContext.getResources().getColor(R.color.tgk_text_press));
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.isRenameViewStubShow = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestColorfulLight(int i) {
        if (this.mIClickListener == null || !TgkHelper.IS_SUPPORT_LAMP_FUNCTION) {
            return;
        }
        this.mIClickListener.requestColorfulLight(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveFloatViewPos() {
        try {
            getFolatViewRect();
            this.mInputManagerProxy.sendTgkRectsToNative(this.mTgkGameInfo.getSelectedCaseData().pointsArray);
        } catch (Exception e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    private void selecteLinkOpt(int i, int i2) {
        int i3 = this.mTgkKeyCodeArray[i];
        if (TgkHelper.isShowRemind(this.mContext.getContentResolver())) {
            this.mRotationGuidancePanel.setVisibility(0);
            initRotationGuidancePanel(i, i2, i3);
            updatNonFloatViewDisplay(false);
            hideAllFloatBall();
            return;
        }
        TgkGameInfo tgkGameInfo = this.mTgkGameInfo;
        if (tgkGameInfo == null || tgkGameInfo.getSelectedCaseData() == null) {
            return;
        }
        showGameKeyLinkMotion(this.mContext, i3, TgkHelper.getTgkLinkCaseState(this.mContext, this.mGameAppPackageName, this.mTgkGameInfo.getSelectedCaseData().ID, this.mTgkGameInfo.getSelectedCaseData().state, i3), i, i2);
        showLinkFb(i, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLampCaseTvTitle() {
        if (this.mLampCaseTitle != null) {
            int queryLameCastSelect = TgkHelper.queryLameCastSelect(this.mContext, this.mGameAppPackageName);
            if (queryLameCastSelect == -1) {
                queryLameCastSelect = 1;
            }
            this.mLampCaseTitle.setText(TgkLampHelper.getLampCaseNameForIndex(queryLameCastSelect));
        }
    }

    private void setLayoutParams(View view, int i, int i2, int i3, int i4, int i5) {
        if (view == null) {
            Log.e(TAG, "setLayoutParams view is null!");
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.setMargins(i, i2, i3, i4);
        layoutParams.gravity = i5;
        view.setLayoutParams(layoutParams);
    }

    private void setSensitivityEnabled(int i) {
        if (!this.mIsMoreVsShow || i >= 2) {
            return;
        }
        this.mSensitivityViews[i].setEnabled(this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i]);
    }

    private void setSensitivityState(int i) {
        this.mSensitivityViews[i].setProgress(this.mTgkGameInfo.getSelectedCaseData().sensitivityArray[i]);
        this.mSensitivityViews[i].setEnabled(this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTGKMapEnabled(boolean z) {
        if (!z) {
            this.mInputManagerProxy.setGameKeyEnable(false);
            this.mInputManagerProxy.setLeftTgkEnable(false);
            this.mInputManagerProxy.setMiddleTgkEnable(false);
            this.mInputManagerProxy.setRightTgkEnable(false);
            this.mInputManagerProxy.setTouchHapticFeedbackEnable(false);
            return;
        }
        this.mInputManagerProxy.setTgkTopEffectEnable(false);
        this.mInputManagerProxy.setTgkCenterEffectEnable(false);
        this.mInputManagerProxy.setDefaultGameKeyLinkFunctionEnable(0);
        this.mInputManagerProxy.setGameKeyEnable(true);
        if (this.mTgkGameInfo.getSelectedCaseData().optionSwArray[0]) {
            this.mInputManagerProxy.setTgkMode(-1, 137);
            this.mInputManagerProxy.setLeftTgkEnable(true);
            this.mInputManagerProxy.setTgkSensitivity(this.mTgkGameInfo.getSelectedCaseData().sensitivityArray[0], 137);
        } else {
            this.mInputManagerProxy.setLeftTgkEnable(false);
        }
        if (this.mTgkGameInfo.getSelectedCaseData().optionSwArray[1]) {
            this.mInputManagerProxy.setTgkMode(-1, 138);
            this.mInputManagerProxy.setRightTgkEnable(true);
            this.mInputManagerProxy.setTgkSensitivity(this.mTgkGameInfo.getSelectedCaseData().sensitivityArray[1], 138);
        } else {
            this.mInputManagerProxy.setRightTgkEnable(false);
        }
        if (this.mTgkGameInfo.getSelectedCaseData().optionSwArray[2]) {
            this.mInputManagerProxy.setTgkMode(-1, 136);
            this.mInputManagerProxy.setMiddleTgkEnable(true);
        } else {
            this.mInputManagerProxy.setMiddleTgkEnable(false);
        }
        this.mInputManagerProxy.setTouchHapticFeedbackEnable(this.mTgkGameInfo.getSelectedCaseData().vibrateSw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTgkCaseTvTitle() {
        TextView textView = this.mTgkCaseTitle;
        if (textView != null) {
            textView.setText(this.mTgkGameInfo.getSelectedCaseData().showName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTgkGameInfoChanged() {
        this.mTgkGameInfo.getSelectedCaseData().state |= 2;
        this.mGameInfoChanged = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTgkMenuBtnTitle(int i) {
        int[] iArr = {R.string.tgk_single, R.string.tgk_copy, R.string.tgk_up_down, R.string.tgk_move_vision_new, R.string.tgk_link, R.string.tgk_long_press, R.string.tgk_mult_clicks, R.string.tgk_slide_single, R.string.tgk_slide_copy, R.string.tgk_off};
        int i2 = this.mTgkGameInfo.getSelectedCaseData().optionArray[i];
        updateTgkMenuBtnsBackgroundResource(i, i2, false);
        String string = this.mContext.getString(iArr[i2]);
        this.mTgkMenuBtnArray[i].setTitle(string);
        if (i2 != 4) {
            this.mTgkMenuBtnArray[i].setTitle(string);
        } else {
            this.mTgkMenuBtnArray[i].setTitle(TgkHelper.getTgkLinkCaseName(this.mTgkKeyCodeArray[i]));
        }
    }

    private void showFbByOptID(int i, int i2) {
        switch (i) {
            case 0:
                showSingleFb(i2, i);
                break;
            case 1:
                showOneTwoFb(i2, 1);
                break;
            case 2:
                showOneTwoFb(i2, 2);
                break;
            case 3:
                showSingleFb(i2, i);
                break;
            case 4:
                hideDoubleFb(i2);
                showLinkFb(i2, 0);
                break;
            case 5:
                showSingleFb(i2, i);
                break;
            case 6:
                showSingleFb(i2, i);
                break;
            case 7:
                showSingleFb(i2, i);
                break;
            case 8:
                showOneTwoFb(i2, 1);
                break;
            default:
                hideFloatBall(i2);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showLampListPopView(boolean r9) {
        /*
            Method dump skipped, instructions count: 447
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.tgk.TgkMapView.showLampListPopView(boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLinkFb(int i, int i2) {
        this.mLinkFBArray[i].setVisibility(i2);
    }

    private void showMoreBtnsView() {
        LinearLayout linearLayout = this.mMoreBtnsView;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
    }

    private void showMoreOpenBtn(boolean z) {
        if (!z || this.mIsMoreVsShow) {
            this.mMoreOpenBtn.setVisibility(8);
        } else {
            this.mMoreOpenBtn.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMoreView(boolean z) {
        if (!z) {
            if (this.mTgkHeadView.getVisibility() == 0) {
                this.mMoreOpenBtn.setVisibility(0);
            }
            this.mMoreVs.setVisibility(8);
            return;
        }
        this.mMoreOpenBtn.setVisibility(8);
        if (TgkHelper.IS_SUPPORT_MIDDLE_TGK) {
            this.mMoreVs.setLayoutResource(R.layout.tgk_more_layout_midddle);
        } else {
            this.mMoreVs.setLayoutResource(TgkHelper.IS_SUPPORT_LAMP_FUNCTION ? R.layout.tgk_more_layout_769j : R.layout.tgk_more_layout);
        }
        this.mMoreVs.setVisibility(0);
        if (this.mMoreCloseBtn == null) {
            Button button = (Button) findViewById(R.id.tgk_more_close_btn);
            this.mMoreCloseBtn = button;
            button.setOnClickListener(this.mViewClickListener);
        }
        initMoreView();
        updateTgkCaseListViewOptionBtns();
    }

    private void showOneTwoFb(int i, int i2) {
        this.mOneTwoFBArray[i][0].setVisibility(0);
        this.mOneTwoFBArray[i][0].setType(i2);
        int i3 = i * 2;
        this.mOneTwoFBArray[i][0].setBackgroundResource(this.mFFViewBg[i2][i3]);
        this.mOneTwoFBArray[i][1].setVisibility(0);
        this.mOneTwoFBArray[i][1].setType(i2);
        this.mOneTwoFBArray[i][1].setBackgroundResource(this.mFFViewBg[i2][i3 + 1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPreviewViewImg(boolean z) {
        if (z) {
            this.mPreviewViewImgVs.setVisibility(0);
        } else {
            this.mPreviewViewImgVs.setVisibility(8);
        }
        this.isPreviewViewImgShow = z;
    }

    private void showSingleFb(int i, int i2) {
        if (3 == i2) {
            this.mOneTwoFBArray[i][0].setBackgroundResource(this.mMoveVersionViewBg);
        } else {
            this.mOneTwoFBArray[i][0].setBackgroundResource(this.mFFViewBg[0][i * 2]);
        }
        this.mOneTwoFBArray[i][0].setVisibility(0);
        this.mOneTwoFBArray[i][0].setType(0);
        this.mOneTwoFBArray[i][1].setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTgkCaseListPopView(boolean z) {
        if (z) {
            if (this.mTgkCaseListPopView == null) {
                this.mTgkCaseListPopView = (TgkCaseListPopView) findViewById(R.id.tgk_pop_case_list_view);
            }
            if (this.mTgkCaseAdapter == null) {
                TgkCaseListViewAdapter tgkCaseListViewAdapter = new TgkCaseListViewAdapter(this.mContext, this.mTgkGameInfo.presetTableList, this.mTgkGameInfo.importTableList);
                this.mTgkCaseAdapter = tgkCaseListViewAdapter;
                tgkCaseListViewAdapter.setListener(this.tgkCaseListViewAdapterListener);
                this.mTgkCaseListPopView.setAdapter(this.mTgkCaseAdapter);
            }
            Rect rect = new Rect();
            this.mTgkCaseListView.getGlobalVisibleRect(rect);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mTgkCaseListPopView.getLayoutParams();
            if (this.mIsLandscape == 1) {
                layoutParams.height = 468;
                if (this.mContext.getResources().getBoolean(R.bool.is_right_to_left)) {
                    layoutParams.width = rect.left - rect.right;
                    layoutParams.setMargins(rect.right, rect.bottom + 10, rect.left, rect.bottom + 478);
                } else {
                    layoutParams.width = rect.right - rect.left;
                    layoutParams.setMargins(rect.left, rect.bottom + 10, rect.right, rect.bottom + 478);
                }
            } else {
                int dimension = (int) this.mContext.getResources().getDimension(R.dimen.tgk_case_list_view_height_portrait);
                int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.tgk_case_list_view_margin_top_portrait);
                layoutParams.width = rect.right - rect.left;
                layoutParams.height = dimension;
                if (this.mContext.getResources().getBoolean(R.bool.is_right_to_left)) {
                    layoutParams.setMargins(rect.left - 66, rect.bottom + dimension2, rect.right - 66, rect.bottom + dimension2 + dimension);
                } else {
                    layoutParams.setMargins(rect.left, rect.bottom + dimension2, rect.right, rect.bottom + dimension2 + dimension);
                }
            }
            this.mTgkCaseListPopView.setLayoutParams(layoutParams);
            this.mTgkCaseListPopView.setVisibility(0);
            tgkCaseListPopViewScrollToPosition(this.mTgkGameInfo.selectedTableId, this.mTgkGameInfo.selectedCasePosition);
            this.mTgkCaseListView.setBackgroundResource(R.drawable.tgk_case_list_view_bg_up);
        } else {
            this.mTgkCaseListPopView.setVisibility(8);
            this.mTgkCaseListView.setBackgroundResource(R.drawable.tgk_case_list_view_bg_down);
        }
        this.isTgkCaseListViewShow = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTgkMenuPopView(int i) {
        if (this.mTgkMenuDirection == i) {
            TgkMenuPopView tgkMenuPopView = this.mTgkMenuPopView;
            if (tgkMenuPopView != null) {
                tgkMenuPopView.setVisibility(8);
            }
            updateTgkMenuBtnsBackgroundResource(false);
            this.mTgkMenuDirection = -1;
            return;
        }
        if (this.mTgkMenuPopView == null) {
            initTgkMenuPopView();
        }
        int i2 = this.mTgkGameInfo.getSelectedCaseData().optionArray[i];
        int i3 = this.mLastTgkOptId;
        if (i2 != i3) {
            if (-1 != i3) {
                this.mTgkOptList.get(i3).setChecked(false);
            }
            this.mTgkOptList.get(i2).setChecked(true);
            this.mLastTgkOptId = i2;
        }
        adjustTgkOptItems(2 == i);
        Rect rect = new Rect();
        this.mTgkMenuBtnArray[i].getClickView().getGlobalVisibleRect(rect);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mTgkMenuPopView.getLayoutParams();
        if (this.mIsLandscape == 1) {
            int dimensionPixelSize = (TgkHelper.isP720F10Device() || TgkHelper.isP780F01Device()) ? 600 : this.mContext.getResources().getDimensionPixelSize(R.dimen.tgk_menu_pop_height);
            layoutParams.height = dimensionPixelSize;
            if (!this.mContext.getResources().getBoolean(R.bool.is_right_to_left)) {
                layoutParams.width = rect.right - rect.left;
                layoutParams.setMargins(rect.left, rect.bottom + 9, rect.left + layoutParams.width, rect.bottom + 9 + dimensionPixelSize);
            } else if (i == 1) {
                layoutParams.width = rect.right - rect.left;
                layoutParams.setMargins(rect.left + 116, rect.bottom + 9, rect.left + layoutParams.width + 116, rect.bottom + 9 + dimensionPixelSize);
            } else if (i == 0) {
                layoutParams.width = rect.right - rect.left;
                layoutParams.setMargins(rect.left - 1192, rect.bottom + 9, (rect.left + layoutParams.width) - 1192, rect.bottom + 9 + dimensionPixelSize);
            }
        } else {
            int dimension = (int) this.mContext.getResources().getDimension(R.dimen.tgk_menu_view_height_portrait);
            int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.tgk_menu_view_margin_top_portrait);
            layoutParams.width = rect.right - rect.left;
            layoutParams.height = dimension;
            if (!this.mContext.getResources().getBoolean(R.bool.is_right_to_left)) {
                layoutParams.setMargins(rect.left, rect.bottom + dimension2, rect.left + layoutParams.width, rect.bottom + dimension2 + dimension);
            } else if (i == 1) {
                layoutParams.setMargins(rect.left + 116, rect.bottom + dimension2, rect.right + 116, rect.bottom + dimension2 + dimension);
            } else if (i == 0) {
                layoutParams.setMargins(rect.left - 974, rect.bottom + dimension2, rect.right - 974, rect.bottom + dimension2 + dimension);
            }
        }
        this.mTgkMenuPopView.setLayoutParams(layoutParams);
        this.mTgkMenuPopView.setVisibility(0);
        this.mTgkMenuDirection = i;
        updateTgkMenuBtnsBackgroundResource(true);
    }

    private void stopFBPressedWork(int i, int i2) {
        WorkHandler workHandler = this.mWorkHandler;
        if (workHandler != null) {
            workHandler.removeMessages(i);
            if (i2 == 137) {
                this.mOneTwoFBArray[0][0].setPressed(false);
            } else if (i2 == 138) {
                this.mOneTwoFBArray[1][0].setPressed(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tgkCaseListPopViewScrollToPosition(int i, int i2) {
        this.mTgkCaseListPopView.scrollToPositionWithOffset((i * 5) + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatNonFloatViewDisplay(boolean z) {
        if (z) {
            this.mTgkHeadView.setVisibility(0);
            this.mMoreOpenBtn.setVisibility(0);
        } else {
            this.mTgkHeadView.setVisibility(4);
            this.mMoreOpenBtn.setVisibility(4);
        }
        if (this.mIsMoreVsShow) {
            showMoreView(z);
        }
        hidePopViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllViewState(boolean z) {
        updateTgkMenuView(z);
        if (z) {
            setTgkCaseTvTitle();
            setLampCaseTvTitle();
            updateFbViewState();
            updateFloatViewPos();
            if (this.mIsMoreVsShow) {
                updateTgkCaseListViewOptionBtns();
                updateSensitivityState();
            }
        } else {
            hideMoreView();
            hideAllFloatBall();
            hidePopViews();
        }
        showMoreOpenBtn(z);
    }

    private void updateFbViewState() {
        updateFbViewState(0);
        updateFbViewState(1);
        updateFbViewState(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFbViewState(int i) {
        showFbByOptID(this.mTgkGameInfo.getSelectedCaseData().optionSwArray[i] ? this.mTgkGameInfo.getSelectedCaseData().optionArray[i] : 10, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatViewPos() {
        int[] iArr = this.mTgkGameInfo.getSelectedCaseData().optionArray;
        Rect[][] rectArr = this.mTgkGameInfo.getSelectedCaseData().pointsArray;
        if (rectArr != null) {
            int[][] iArr2 = {new int[]{-1, -1}, new int[]{-1, -1}};
            int[] iArr3 = this.mTgkKeyCodeArray;
            for (int i = 0; i < TgkHelper.TGK_COUNT * 2; i++) {
                int i2 = i / 2;
                FakeFloatView fakeFloatView = this.mFBArray[iArr[i2]][i];
                int i3 = i % 2;
                Rect rect = rectArr[i2][i3];
                if (fakeFloatView != null && rect != null) {
                    setLayoutParams(fakeFloatView, rect.left, rect.top, rect.right, rect.bottom, -1);
                    iArr2[i3][0] = (rect.left + rect.right) / 2;
                    iArr2[i3][1] = (rect.top + rect.bottom) / 2;
                }
                if (i3 > 0) {
                    this.mInputManagerProxy.setTgkPoint(iArr2[0], iArr2[1], iArr3[i2]);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreviewBtnState() {
        if (this.mTgkGameInfo.picture == null) {
            View view = this.mPreviewView;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = this.mPreviewViewLine;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            TextView textView = this.mPreviewTextView;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        View view3 = this.mPreviewView;
        if (view3 != null) {
            view3.setVisibility(0);
        }
        View view4 = this.mPreviewViewLine;
        if (view4 != null) {
            view4.setVisibility(0);
        }
        TextView textView2 = this.mPreviewTextView;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
    }

    private void updateSensitivityState() {
        for (int i = 0; i < this.mSensitivityViews.length; i++) {
            setSensitivityState(i);
        }
    }

    private void updateTgkCaseListViewOptionBtns() {
        if (1 != this.mTgkGameInfo.selectedTableId) {
            View view = this.mPreviewView;
            if (view != null) {
                view.setVisibility(8);
            }
            TextView textView = this.mPreviewTextView;
            if (textView != null) {
                textView.setVisibility(8);
            }
            View view2 = this.mDeleteView;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            TextView textView2 = this.mDeleteTextView;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            View view3 = this.mPreviewViewLine;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            View view4 = this.mDeleteViewLine;
            if (view4 != null) {
                view4.setVisibility(8);
                return;
            }
            return;
        }
        if (this.mTgkGameInfo.picture == null) {
            View view5 = this.mPreviewView;
            if (view5 != null) {
                view5.setVisibility(8);
            }
            View view6 = this.mPreviewViewLine;
            if (view6 != null) {
                view6.setVisibility(8);
            }
            TextView textView3 = this.mPreviewTextView;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        } else {
            View view7 = this.mPreviewView;
            if (view7 != null) {
                view7.setVisibility(0);
            }
            View view8 = this.mPreviewViewLine;
            if (view8 != null) {
                view8.setVisibility(0);
            }
            TextView textView4 = this.mPreviewTextView;
            if (textView4 != null) {
                textView4.setVisibility(0);
            }
        }
        View view9 = this.mDeleteView;
        if (view9 != null) {
            view9.setVisibility(0);
        }
        View view10 = this.mDeleteViewLine;
        if (view10 != null) {
            view10.setVisibility(0);
        }
        TextView textView5 = this.mDeleteTextView;
        if (textView5 != null) {
            textView5.setVisibility(0);
        }
    }

    private void updateTgkMenuBtnsBackgroundResource(int i, int i2, boolean z) {
        if (i == 0) {
            if (i2 != 9) {
                if (z) {
                    this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_l_bg_2_up_select);
                } else {
                    this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_l_bg_2_down_select);
                }
                this.mTgkMenuBtnArray[i].setFirstBackgroundResource(R.drawable.tgk_menu_view_l_bg_1_select);
                return;
            }
            this.mTgkMenuBtnArray[i].setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
            if (z) {
                this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_up_select);
                return;
            } else {
                this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                return;
            }
        }
        if (i == 1) {
            if (i2 != 9) {
                if (z) {
                    this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_r_bg_2_up_select);
                } else {
                    this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_r_bg_2_down_select);
                }
                this.mTgkMenuBtnArray[i].setFirstBackgroundResource(R.drawable.tgk_menu_view_r_bg_1_select);
                return;
            }
            this.mTgkMenuBtnArray[i].setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
            if (z) {
                this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_up_select);
                return;
            } else {
                this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                return;
            }
        }
        if (i2 != 9) {
            if (z) {
                this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_m_bg_2_up_select);
            } else {
                this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_m_bg_2_down_select);
            }
            this.mTgkMenuBtnArray[i].setFirstBackgroundResource(R.drawable.tgk_menu_view_m_bg_1_select);
            return;
        }
        this.mTgkMenuBtnArray[i].setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
        if (z) {
            this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_up_select);
        } else {
            this.mTgkMenuBtnArray[i].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
        }
    }

    private void updateTgkMenuBtnsBackgroundResource(boolean z) {
        int[] iArr = this.mTgkGameInfo.getSelectedCaseData().optionArray;
        int i = this.mTgkMenuDirection;
        int i2 = iArr[i];
        if (!z) {
            if (i < 0 || i >= TgkHelper.TGK_COUNT) {
                return;
            }
            TgkMenuView[] tgkMenuViewArr = this.mTgkMenuBtnArray;
            int i3 = this.mTgkMenuDirection;
            TgkMenuView tgkMenuView = tgkMenuViewArr[i3];
            if (tgkMenuView != null) {
                if (i3 == 0) {
                    if (i2 == 9) {
                        tgkMenuView.setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                        this.mTgkMenuBtnArray[this.mTgkMenuDirection].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                        return;
                    } else {
                        tgkMenuView.setSecondBackgroundResource(R.drawable.tgk_menu_view_l_bg_2_down_select);
                        this.mTgkMenuBtnArray[this.mTgkMenuDirection].setFirstBackgroundResource(R.drawable.tgk_menu_view_l_bg_1_select);
                        return;
                    }
                }
                if (i3 == 2) {
                    if (i2 == 9) {
                        tgkMenuView.setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                        this.mTgkMenuBtnArray[this.mTgkMenuDirection].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                        return;
                    } else {
                        tgkMenuView.setSecondBackgroundResource(R.drawable.tgk_menu_view_m_bg_2_down_select);
                        this.mTgkMenuBtnArray[this.mTgkMenuDirection].setFirstBackgroundResource(R.drawable.tgk_menu_view_m_bg_1_select);
                        return;
                    }
                }
                if (i2 == 9) {
                    tgkMenuView.setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                    this.mTgkMenuBtnArray[this.mTgkMenuDirection].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                    return;
                } else {
                    tgkMenuView.setSecondBackgroundResource(R.drawable.tgk_menu_view_r_bg_2_down_select);
                    this.mTgkMenuBtnArray[this.mTgkMenuDirection].setFirstBackgroundResource(R.drawable.tgk_menu_view_r_bg_1_select);
                    return;
                }
            }
            return;
        }
        if (i < 0 || i >= TgkHelper.TGK_COUNT) {
            return;
        }
        TgkMenuView[] tgkMenuViewArr2 = this.mTgkMenuBtnArray;
        int i4 = this.mTgkMenuDirection;
        TgkMenuView tgkMenuView2 = tgkMenuViewArr2[i4];
        if (tgkMenuView2 != null) {
            if (i4 == 0) {
                if (i2 == 9) {
                    tgkMenuView2.setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                    this.mTgkMenuBtnArray[this.mTgkMenuDirection].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_up_select);
                } else {
                    tgkMenuView2.setSecondBackgroundResource(R.drawable.tgk_menu_view_l_bg_2_up_select);
                    this.mTgkMenuBtnArray[this.mTgkMenuDirection].setFirstBackgroundResource(R.drawable.tgk_menu_view_l_bg_1_select);
                }
            } else if (i4 == 2) {
                if (i2 == 9) {
                    tgkMenuView2.setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                    this.mTgkMenuBtnArray[this.mTgkMenuDirection].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_up_select);
                } else {
                    tgkMenuView2.setSecondBackgroundResource(R.drawable.tgk_menu_view_m_bg_2_up_select);
                    this.mTgkMenuBtnArray[this.mTgkMenuDirection].setFirstBackgroundResource(R.drawable.tgk_menu_view_m_bg_1_select);
                }
            } else if (i2 == 9) {
                tgkMenuView2.setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                this.mTgkMenuBtnArray[this.mTgkMenuDirection].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_up_select);
            } else {
                tgkMenuView2.setSecondBackgroundResource(R.drawable.tgk_menu_view_r_bg_2_up_select);
                this.mTgkMenuBtnArray[this.mTgkMenuDirection].setFirstBackgroundResource(R.drawable.tgk_menu_view_r_bg_1_select);
            }
        }
        for (int i5 = 0; i5 < TgkHelper.TGK_COUNT; i5++) {
            if (this.mTgkMenuDirection != i5 && this.mTgkMenuBtnArray[i5] != null) {
                int i6 = this.mTgkGameInfo.getSelectedCaseData().optionArray[i5];
                if (i5 == 0) {
                    if (i6 == 9) {
                        this.mTgkMenuBtnArray[i5].setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                        this.mTgkMenuBtnArray[i5].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                    } else {
                        this.mTgkMenuBtnArray[i5].setSecondBackgroundResource(R.drawable.tgk_menu_view_l_bg_2_down_select);
                        this.mTgkMenuBtnArray[i5].setFirstBackgroundResource(R.drawable.tgk_menu_view_l_bg_1_select);
                    }
                } else if (i5 == 2) {
                    if (i6 == 9) {
                        this.mTgkMenuBtnArray[i5].setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                        this.mTgkMenuBtnArray[i5].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                    } else {
                        this.mTgkMenuBtnArray[i5].setSecondBackgroundResource(R.drawable.tgk_menu_view_m_bg_2_down_select);
                        this.mTgkMenuBtnArray[i5].setFirstBackgroundResource(R.drawable.tgk_menu_view_m_bg_1_select);
                    }
                } else if (i6 == 9) {
                    this.mTgkMenuBtnArray[i5].setFirstBackgroundResource(R.drawable.tgk_menu_view_bg_off_select);
                    this.mTgkMenuBtnArray[i5].setSecondBackgroundResource(R.drawable.tgk_menu_view_off_bg_2_down_select);
                } else {
                    this.mTgkMenuBtnArray[i5].setSecondBackgroundResource(R.drawable.tgk_menu_view_r_bg_2_down_select);
                    this.mTgkMenuBtnArray[i5].setFirstBackgroundResource(R.drawable.tgk_menu_view_r_bg_1_select);
                }
            }
        }
    }

    private void updateTgkMenuView(boolean z) {
        for (int i = 0; i < this.mTgkMenuBtnArray.length; i++) {
            if (z) {
                setTgkMenuBtnTitle(i);
            }
            this.mTgkMenuBtnArray[i].setEnabled(z);
            this.mTgkMenuBtnArray[i].setMarquee(z);
        }
    }

    public void dismiss(boolean z) {
        hidePopViews();
        if (this.mIClickListener != null) {
            if (this.mTgkGameInfo.getSelectedCaseData().mainSw) {
                if (z) {
                    ToastUtil.showTgkToast(this.mContext, this.mContext.getResources().getString(R.string.tgk_save_remind_msg));
                }
                processLongPress();
                this.mInputManagerProxy.setTgkTopEffectEnable(this.mTgkGameInfo.topVisualEffectSw);
                this.mInputManagerProxy.setTgkCenterEffectEnable(this.mTgkGameInfo.centerVisualEffectSw);
                this.mInputManagerProxy.setTgkTransparency(this.mTgkGameInfo.centerVisualEffectTransparency);
                this.mInputManagerProxy.setTouchHapticFeedbackEnable(this.mTgkGameInfo.getSelectedCaseData().vibrateSw);
                this.mInputManagerProxy.setDefaultGameKeyLinkFunctionEnable(0);
                if (this.mTgkGameInfo.getSelectedCaseData().optionSwArray[2]) {
                    if (this.mTgkGameInfo.getSelectedCaseData().optionArray[2] == 4) {
                        this.mInputManagerProxy.setMiddleTgkLinkFunction(136);
                    }
                    if (this.mTgkGameInfo.getSelectedCaseData().optionArray[2] == 6) {
                        this.mInputManagerProxy.setTgkRapidFireCount(this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[2], 136);
                    }
                    this.mInputManagerProxy.setMiddleTgkEnable(true);
                } else {
                    this.mInputManagerProxy.setMiddleTgkEnable(false);
                }
                this.mInputManagerProxy.setTgkMode(this.mTgkGameInfo.getSelectedCaseData().optionArray[2], 136);
                if (this.mTgkGameInfo.getSelectedCaseData().optionSwArray[0]) {
                    if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 4) {
                        this.mInputManagerProxy.setLeftTgkLinkFunction(137);
                    }
                    if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 6) {
                        this.mInputManagerProxy.setTgkRapidFireCount(this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[0], 137);
                    }
                    this.mInputManagerProxy.setLeftTgkEnable(true);
                } else {
                    this.mInputManagerProxy.setLeftTgkEnable(false);
                }
                this.mInputManagerProxy.setTgkMode(this.mTgkGameInfo.getSelectedCaseData().optionArray[0], 137);
                if (this.mTgkGameInfo.getSelectedCaseData().optionSwArray[1]) {
                    if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 4) {
                        this.mInputManagerProxy.setRightTgkLinkFunction(138);
                    }
                    if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 6) {
                        this.mInputManagerProxy.setTgkRapidFireCount(this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[1], 138);
                    }
                    this.mInputManagerProxy.setRightTgkEnable(true);
                } else {
                    this.mInputManagerProxy.setRightTgkEnable(false);
                }
                this.mInputManagerProxy.setTgkMode(this.mTgkGameInfo.getSelectedCaseData().optionArray[1], 138);
                this.mInputManagerProxy.sendTgkRectsToNative(this.mTgkGameInfo.getSelectedCaseData().pointsArray);
                TgkHelper.openLampScene(this.mLampCaseSelectPosition);
            } else {
                this.mInputManagerProxy.setGameKeyEnable(false);
                this.mInputManagerProxy.setLeftTgkEnable(false);
                this.mInputManagerProxy.setMiddleTgkEnable(false);
                this.mInputManagerProxy.setDefaultGameKeyLinkFunctionEnable(0);
                this.mInputManagerProxy.setRightTgkEnable(false);
                this.mInputManagerProxy.setTouchHapticFeedbackEnable(false);
            }
            if (this.mGameInfoChanged) {
                this.mGameInfoChanged = false;
                showMoreView(false);
                hideTgkCaseListPopView();
                hideLampListPopView();
                this.mIsMoreVsShow = false;
                this.mTgkGameInfo.setIsLandscape(this.mIsLandscape);
                this.mIClickListener.doClose(this.mTgkGameInfo);
            } else {
                this.mIClickListener.doClose(null);
            }
        }
        removeCallbacksAndMessages();
        this.mInputManagerProxy.showTgkView(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            if (keyEvent.getAction() == 0) {
                onKeyDown(keyEvent);
            } else {
                onKeyUp(keyEvent);
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            hideListPopView(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void hideKeyboard() {
        EditText editText = this.mTgkCaseNameEditor;
        if (editText != null) {
            ((InputMethodManager) editText.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mTgkCaseNameEditor.getWindowToken(), 0);
        }
    }

    public void initTgkMapView(String str) {
        this.mGameAppPackageName = str;
        getGameKeyLinkMotionState();
        initData(str);
        initView();
        updateAllViewState(this.mTgkGameInfo.getSelectedCaseData().mainSw);
        setTGKMapEnabled(this.mTgkGameInfo.getSelectedCaseData().mainSw);
        this.mInputManagerProxy.showTgkView(true);
        this.mInputManagerProxy.releaseTgk();
    }

    public boolean onKeyDown(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 137) {
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 5 && keyEvent.getRepeatCount() == 0) {
                boolean z = !this.mOneTwoFBArray[0][0].isPressed();
                requestColorfulLight(z ? TgkLampHelper.getLeftDownId() : TgkLampHelper.getLeftUpId());
                this.mOneTwoFBArray[0][0].setPressed(z);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 4 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftDownId());
                this.mLinkFBArray[0].setPressed(true);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 0 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftDownId());
                this.mOneTwoFBArray[0][0].setPressed(true);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 1 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftDownId());
                this.mOneTwoFBArray[0][0].setPressed(true);
                this.mOneTwoFBArray[0][1].setPressed(true);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 2 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftDownId());
                this.mOneTwoFBArray[0][0].setPressed(true);
                doFBPressedWork(2, 137, true, 50, 0);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 6 && keyEvent.getRepeatCount() == 0) {
                doFBPressedWork(6, 137, true, 0, this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[0]);
            }
            return true;
        }
        if (keyCode == 138) {
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 5 && keyEvent.getRepeatCount() == 0) {
                boolean z2 = !this.mOneTwoFBArray[1][0].isPressed();
                requestColorfulLight(z2 ? TgkLampHelper.getRightDownId() : TgkLampHelper.getRightUpId());
                this.mOneTwoFBArray[1][0].setPressed(z2);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 4 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightDownId());
                this.mLinkFBArray[1].setPressed(true);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 0 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightDownId());
                this.mOneTwoFBArray[1][0].setPressed(true);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 1 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightDownId());
                this.mOneTwoFBArray[1][0].setPressed(true);
                this.mOneTwoFBArray[1][1].setPressed(true);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 2 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightDownId());
                this.mOneTwoFBArray[1][0].setPressed(true);
                doFBPressedWork(2, 138, true, 50, 0);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 6 && keyEvent.getRepeatCount() == 0) {
                doFBPressedWork(6, 138, true, 0, this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[1]);
            }
        }
        return true;
    }

    public boolean onKeyUp(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 137) {
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 4 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftUpId());
                this.mLinkFBArray[0].setPressed(false);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 0 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftUpId());
                this.mOneTwoFBArray[0][0].setPressed(false);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 1 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftUpId());
                this.mOneTwoFBArray[0][0].setPressed(false);
                this.mOneTwoFBArray[0][1].setPressed(false);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 2 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getLeftDownId());
                this.mOneTwoFBArray[0][1].setPressed(true);
                doFBPressedWork(2, 137, false, 50, 0);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[0] == 6 && keyEvent.getRepeatCount() == 0) {
                stopFBPressedWork(6, 137);
            }
            return true;
        }
        if (keyCode == 138) {
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 4 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightUpId());
                this.mLinkFBArray[1].setPressed(false);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 0 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightUpId());
                this.mOneTwoFBArray[1][0].setPressed(false);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 1 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightUpId());
                this.mOneTwoFBArray[1][0].setPressed(false);
                this.mOneTwoFBArray[1][1].setPressed(false);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 2 && keyEvent.getRepeatCount() == 0) {
                requestColorfulLight(TgkLampHelper.getRightDownId());
                this.mOneTwoFBArray[1][1].setPressed(true);
                doFBPressedWork(2, 138, false, 50, 0);
            }
            if (this.mTgkGameInfo.getSelectedCaseData().optionArray[1] == 6 && keyEvent.getRepeatCount() == 0) {
                stopFBPressedWork(6, 138);
            }
        }
        return true;
    }

    public void setCloseListener(ITgkMapViewClickListener iTgkMapViewClickListener) {
        this.mIClickListener = iTgkMapViewClickListener;
    }

    public void setMulitFunction() {
        this.mGameInfoChanged = true;
        this.mTgkGameInfo.getSelectedCaseData().optionArray[this.mTgkMenuDirection] = 6;
        this.mTgkGameInfo.getSelectedCaseData().setLinkFlagArray[this.mTgkMenuDirection] = 0;
        InputManagerProxy inputManagerProxy = this.mInputManagerProxy;
        int[] iArr = this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray;
        int i = this.mTgkMenuDirection;
        inputManagerProxy.setTgkRapidFireCount(iArr[i], this.mTgkKeyCodeArray[i]);
        if (9 == this.mLastTgkOptId) {
            this.mTgkGameInfo.getSelectedCaseData().optionSwArray[this.mTgkMenuDirection] = true;
            setTgkGameInfoChanged();
            this.mInputManagerProxy.setSubTgkEnable(this.mTgkMenuDirection, true);
        }
        onTGKOptChanged(this.mLastTgkOptId, 6, this.mTgkMenuDirection);
        this.mTgkMenuBtnArray[this.mTgkMenuDirection].setTitle(this.mTgkOptList.get(6).getTitle());
        this.mTgkOptList.get(this.mLastTgkOptId).setChecked(false);
        this.mTgkOptList.get(6).setChecked(true);
        this.mLastTgkOptId = 6;
    }

    /* JADX WARN: Type inference failed for: r6v5, types: [cn.nubia.tgk.TgkMapView$17] */
    public void showGameKeyLinkMotion(final Context context, int i, boolean z, int i2, int i3) {
        final Bundle bundle = new Bundle();
        String tgkLinkKey = TgkHelper.getTgkLinkKey(this.mTgkGameInfo.getSelectedCaseData().ID, this.mTgkGameInfo.getSelectedCaseData().state, i);
        Log.d(TAG, "showGameKeyLinkMotion key=" + tgkLinkKey + ";isShowlinkView=" + z);
        bundle.putString("touch_key_name", "" + tgkLinkKey);
        bundle.putBoolean("is_need_show_linkview", z);
        new AsyncTask<Void, Void, Void>() { // from class: cn.nubia.tgk.TgkMapView.17
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                try {
                    context.getContentResolver().call(Uri.parse("content://cn.nubia.gamehelper.db.recordmotion"), "touch_key_call", "touch_link_motion", bundle);
                    return null;
                } catch (Exception unused) {
                    Log.e(TgkMapView.TAG, "call method failed");
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
        Log.d(TAG, "direction=" + i2 + ";oriOpt=" + i3);
        TgkGameInfo tgkGameInfo = this.mTgkGameInfo;
        if (tgkGameInfo != null && tgkGameInfo.getSelectedCaseData() != null && i2 >= 0) {
            if (z) {
                this.mTgkGameInfo.getSelectedCaseData().setLinkFlagArray[i2] = 1000;
                this.mTgkGameInfo.getSelectedCaseData().optionArray[i2] = i3;
                setTgkGameInfoChanged();
            } else {
                this.mTgkGameInfo.getSelectedCaseData().setLinkFlagArray[i2] = 0;
                this.mTgkGameInfo.getSelectedCaseData().optionArray[i2] = 4;
                setTgkGameInfoChanged();
            }
        }
        if (z) {
            dismiss(true);
        }
    }

    public void showTgkMultView() {
        this.mTgkMultViewStub.setVisibility(0);
        this.mIsTgkMultViewShow = true;
        this.mTgkMainView.setVisibility(8);
        TgkMultSeekBarView tgkMultSeekBarView = (TgkMultSeekBarView) findViewById(R.id.tgk_mult_seekbar_view);
        int i = this.mTgkGameInfo.getSelectedCaseData().rapidFireCountArray[this.mTgkMenuDirection];
        Log.e(TAG, "showTgkMultView fireCounts=" + i);
        if (i >= 1 && i < 3) {
            tgkMultSeekBarView.setProgress(0);
        } else if (i < 3 || i > 5) {
            tgkMultSeekBarView.setProgress(2);
        } else {
            tgkMultSeekBarView.setProgress(1);
        }
        tgkMultSeekBarView.setChangedListener(this.tgkMultSeekBarViewOnChangeListener);
        ((ImageButton) findViewById(R.id.tgk_mult_view_close)).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.TgkMapView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TgkMapView.this.mTgkMultViewStub.setVisibility(8);
                TgkMapView.this.mIsTgkMultViewShow = false;
                TgkMapView.this.mTgkMainView.setVisibility(0);
            }
        });
        setMulitFunction();
    }

    public void updateRenameView(boolean z) {
        if (this.mIsLandscape == 0 && this.isRenameViewStubShow) {
            Log.d(TAG, "isShowIme =" + z);
            View findViewById = findViewById(R.id.tgk_rename_bottom_view);
            if (findViewById != null) {
                if (!z) {
                    new LinearLayout.LayoutParams(-1, 0);
                    findViewById.setVisibility(8);
                } else {
                    int imeHeight = ImeHeightUtils.getImeHeight();
                    Log.d(TAG, "imeHeight =" + imeHeight);
                    findViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, imeHeight));
                    findViewById.setVisibility(0);
                }
            }
        }
    }
}
