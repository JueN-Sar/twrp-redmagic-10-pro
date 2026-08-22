package cn.nubia.gamecenter.settings.barrageMessage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.helper.HideAppsHelper;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.WorkThread;
import cn.nubia.gamecenter.settings.GcsAnimationUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceAdapter;
import cn.nubia.gamecenter.settings.basic.BasicActivity;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class BarrageMessageSourceActivity extends BasicActivity implements BarrageMessageSourceAdapter.OnItemClickListener, View.OnClickListener {
    public static final String APPADD_COMPONENT = "component";
    public static final String APPADD_ISADD = "isAdd";
    public static final String AUTHORITY = "cn.nubia.gamelauncher.db.AppAddProvider";
    public static final String BARRAGE_MESSAGE_SOURCE_URI = "content://cn.nubia.gamelauncher.db.AppAddProvider/barrage_message_source?notify=false";
    public static final String BARRAGE_MESSAGE_SOURCE_URI_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/barrage_message_source?notify=true";
    public static final String DATABASE_WHERE = " = ?";
    public static final String GSC_GAME_BARRAGE_MESSAGE_DUANXIN = "gsc_barrage_message_bubble_source_duanxin";
    public static final String GSC_GAME_BARRAGE_MESSAGE_QQ = "gsc_barrage_message_bubble_source_qq";
    public static final String GSC_GAME_BARRAGE_MESSAGE_WECHAT = "gsc_barrage_message_bubble_source_weixin";
    private static final String HOME_APP_NAME = "com.zte.mifavor.launcher";
    public static final String KEY_GSC_BARRAGE_MESSAGE_SOURCE_DISABLE = "gsc_barrage_message_source_disable";
    public static final String PACKAGE_THREEBUTTON = "com.android.internal.systemui.navbar.threebutton";
    private static final String TAG = "BarrageMessageSourceActivity";
    private BarrageMessageSourceAdapter mAdapter;
    private RecyclerView mAppRecyclerView;
    private ImageButton mDeleteButton;
    private PopupWindow mMorePopupWindow;
    private EditText mSearchEditText;
    private List<AppBean> mAllAppsList = new ArrayList();
    private List<AppBean> mShowAppsList = new ArrayList();
    private Handler mHandler = new Handler();
    private Runnable delayRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceActivity.2
        @Override // java.lang.Runnable
        public void run() {
            BarrageMessageSourceActivity barrageMessageSourceActivity = BarrageMessageSourceActivity.this;
            barrageMessageSourceActivity.search(barrageMessageSourceActivity.mSearchEditText.getText().toString().trim());
        }
    };

    public static List<String> getAllApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        ArrayList arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if (includeInCount(packageManager, applicationInfo)) {
                arrayList.add(applicationInfo.packageName);
            }
        }
        arrayList.add("cn.nubia.gameassist");
        LogUtil.i(TAG, "find " + arrayList.size() + " source of installed Apps " + installedApplications.size());
        return arrayList;
    }

    private void getAllApps() {
        this.mAllAppsList.clear();
        Cursor query = getContentResolver().query(Uri.parse(BARRAGE_MESSAGE_SOURCE_URI), null, null, null, null);
        if (query == null) {
            LogUtil.e(TAG, "getAllApps null");
            return;
        }
        while (query.moveToNext()) {
            try {
                try {
                    String string = query.getString(query.getColumnIndex("component"));
                    String str = (String) getLabelByPkgName(string);
                    if (!TextUtils.isEmpty(str)) {
                        if (HideAppsHelper.getInstance().isHideApp(string)) {
                            LogUtil.i(TAG, "hide " + string);
                        } else {
                            boolean z = true;
                            if (query.getInt(query.getColumnIndex("isAdd")) != 1) {
                                z = false;
                            }
                            this.mAllAppsList.add(new AppBean(string, str, z));
                        }
                    }
                } catch (Exception e) {
                    LogUtil.wtf(TAG, e);
                }
            } finally {
                query.close();
            }
        }
        this.mAllAppsList.sort(new Comparator<AppBean>() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceActivity.5
            @Override // java.util.Comparator
            public int compare(AppBean appBean, AppBean appBean2) {
                return Collator.getInstance().compare(appBean.getLabel(), appBean2.getLabel());
            }
        });
    }

    private void getAppsForNpad() {
        this.mAllAppsList.clear();
        this.mAllAppsList.add(new AppBean(GSC_GAME_BARRAGE_MESSAGE_DUANXIN, getString(R.string.gamemode_barrage_message_mms), SettingUtil.getBoolean(this, GSC_GAME_BARRAGE_MESSAGE_DUANXIN, true)));
        this.mAllAppsList.add(new AppBean(GSC_GAME_BARRAGE_MESSAGE_QQ, getString(R.string.gamemode_barrage_message_QQ), SettingUtil.getBoolean(this, GSC_GAME_BARRAGE_MESSAGE_QQ, true)));
        this.mAllAppsList.add(new AppBean(GSC_GAME_BARRAGE_MESSAGE_WECHAT, getString(R.string.gamemode_barrage_message_Wechat), SettingUtil.getBoolean(this, GSC_GAME_BARRAGE_MESSAGE_WECHAT, true)));
    }

    private CharSequence getLabelByPkgName(String str) {
        PackageManager packageManager = getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.w(TAG, "not found " + e.getMessage());
            return null;
        }
    }

    public static List<AppBean> getSource(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String sourceDisable = getSourceDisable(context);
        ArrayList arrayList = new ArrayList();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        LogUtil.i(TAG, "installedPackages " + installedPackages.size());
        Iterator<PackageInfo> it = installedPackages.iterator();
        while (it.hasNext()) {
            String str = it.next().applicationInfo.packageName;
            if (!"com.android.internal.systemui.navbar.threebutton".equals(str)) {
                arrayList.add(new AppBean(str, null, !sourceDisable.contains(str)));
            }
        }
        return arrayList;
    }

    public static String getSourceDisable(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), KEY_GSC_BARRAGE_MESSAGE_SOURCE_DISABLE);
        LogUtil.i(TAG, "getSourceDisable:" + string);
        return string == null ? "" : string;
    }

    private static boolean includeInCount(PackageManager packageManager, ApplicationInfo applicationInfo) {
        if ((applicationInfo.flags & 128) != 0 || (applicationInfo.flags & 1) == 0 || HOME_APP_NAME.equals(applicationInfo.packageName)) {
            return true;
        }
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(new Intent("android.intent.action.MAIN", (Uri) null).addCategory("android.intent.category.LAUNCHER").setPackage(applicationInfo.packageName), 786944);
        return (queryIntentActivities == null || queryIntentActivities.size() == 0) ? false : true;
    }

    private void initActionBar() {
        findViewById(R.id.manager_app_icon).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BarrageMessageSourceActivity.this.onBackPressed();
            }
        });
    }

    private void initAppRecycleView() {
        if (CommonUtil.isAndroidU() || !CommonUtil.isP720P01()) {
            getAllApps();
        } else {
            getAppsForNpad();
        }
        this.mShowAppsList.clear();
        this.mShowAppsList.addAll(this.mAllAppsList);
        this.mAppRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.gcs_barrage_message_source_recycleview_divider));
        this.mAppRecyclerView.addItemDecoration(dividerItemDecoration);
        BarrageMessageSourceAdapter barrageMessageSourceAdapter = new BarrageMessageSourceAdapter(this, this.mShowAppsList);
        this.mAdapter = barrageMessageSourceAdapter;
        barrageMessageSourceAdapter.setOnItemClickListener(this);
        this.mAppRecyclerView.setAdapter(this.mAdapter);
    }

    private void initSearchEditText() {
        this.mSearchEditText.addTextChangedListener(new TextWatcher() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceActivity.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (BarrageMessageSourceActivity.this.delayRunnable != null) {
                    BarrageMessageSourceActivity.this.mHandler.removeCallbacks(BarrageMessageSourceActivity.this.delayRunnable);
                }
                BarrageMessageSourceActivity.this.mHandler.postDelayed(BarrageMessageSourceActivity.this.delayRunnable, 800L);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LogUtil.d(BarrageMessageSourceActivity.TAG, "onTextChanged:" + ((Object) charSequence));
                BarrageMessageSourceActivity.this.mDeleteButton.setVisibility(charSequence.length() > 0 ? 0 : 4);
            }
        });
        this.mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceActivity.4
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3) {
                    return false;
                }
                InputMethodManager inputMethodManager = (InputMethodManager) BarrageMessageSourceActivity.this.getApplicationContext().getSystemService("input_method");
                if (BarrageMessageSourceActivity.this.getCurrentFocus() == null) {
                    return false;
                }
                inputMethodManager.hideSoftInputFromWindow(BarrageMessageSourceActivity.this.getCurrentFocus().getWindowToken(), 2);
                return false;
            }
        });
    }

    private void initView() {
        this.mAppRecyclerView = (RecyclerView) findViewById(R.id.app_list);
        this.mSearchEditText = (EditText) findViewById(R.id.search);
        this.mDeleteButton = (ImageButton) findViewById(R.id.delete);
        if (CommonUtil.isAndroidU() || !CommonUtil.isP720P01()) {
            return;
        }
        this.mSearchEditText.setVisibility(8);
        findViewById(R.id.search_icon).setVisibility(8);
        findViewById(R.id.more).setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void search(String str) {
        LogUtil.d(TAG, "gcs_barrage_message_source_search:" + str);
        this.mShowAppsList.clear();
        if (str != null && !TextUtils.isEmpty(str)) {
            str = str.toLowerCase();
        }
        for (AppBean appBean : this.mAllAppsList) {
            String lowerCaseLabel = appBean.getLowerCaseLabel();
            if (!TextUtils.isEmpty(lowerCaseLabel) && lowerCaseLabel.contains(str)) {
                this.mShowAppsList.add(appBean);
            }
        }
        this.mAdapter.notifyDataSetChanged();
    }

    private void setSourceDisable() {
        StringBuilder sb = new StringBuilder();
        for (AppBean appBean : this.mAllAppsList) {
            if (!appBean.isChecked()) {
                sb.append(appBean.getPackageName());
                sb.append(";");
            }
        }
        String sb2 = sb.toString();
        LogUtil.d(TAG, "setSourceDisable:" + sb2);
        Settings.Global.putString(getContentResolver(), KEY_GSC_BARRAGE_MESSAGE_SOURCE_DISABLE, sb2);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        PopupWindow popupWindow = this.mMorePopupWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        this.mMorePopupWindow.dismiss();
        LogUtil.i(TAG, "dispatchTouchEvent dismiss");
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.select) {
            selectedAll(true);
            this.mMorePopupWindow.dismiss();
        } else if (id == R.id.close) {
            selectedAll(false);
            this.mMorePopupWindow.dismiss();
        }
    }

    public void onClickDelete(View view) {
        this.mSearchEditText.setText("");
    }

    public void onClickMore(View view) {
        if (this.mMorePopupWindow == null) {
            View inflate = getLayoutInflater().inflate(R.layout.gcs_barrage_message_source_more, (ViewGroup) null);
            inflate.findViewById(R.id.select).setOnClickListener(this);
            inflate.findViewById(R.id.close).setOnClickListener(this);
            this.mMorePopupWindow = new PopupWindow(inflate, -2, -2);
        }
        this.mMorePopupWindow.showAsDropDown(view, 0, 0, 1);
    }

    @Override // cn.nubia.gamecenter.settings.basic.BasicActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gcs_barrage_message_source_layout);
        initView();
        GcsAnimationUtil.setGcsItemTranslationY(this.mAppRecyclerView);
        initSearchEditText();
        initAppRecycleView();
    }

    @Override // cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceAdapter.OnItemClickListener
    public void onItemClick(View view, int i) {
        AppBean appBean = this.mShowAppsList.get(i);
        appBean.setChecked(!appBean.isChecked());
        if (!CommonUtil.isAndroidU() && CommonUtil.isP720P01()) {
            SettingUtil.putBoolean(this, appBean.getPackageName(), appBean.isChecked());
        } else {
            setSourceDisable();
            updateSource(appBean, true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        initActionBar();
        super.onResume();
        HideAppsHelper.getInstance().update();
    }

    public void selectedAll(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (AppBean appBean : this.mShowAppsList) {
            if (appBean.isChecked() != z) {
                appBean.setChecked(z);
                arrayList.add(appBean);
            }
        }
        if (arrayList.size() > 0) {
            this.mAdapter.notifyDataSetChanged();
            setSourceDisable();
            updateSource(arrayList);
        }
    }

    public void updateSource(AppBean appBean, boolean z) {
        ContentResolver contentResolver = getContentResolver();
        String[] strArr = {appBean.getPackageName()};
        ContentValues contentValues = new ContentValues();
        contentValues.put("isAdd", Boolean.valueOf(appBean.isChecked()));
        try {
            contentResolver.update(Uri.parse(z ? BARRAGE_MESSAGE_SOURCE_URI_NOTIFY : BARRAGE_MESSAGE_SOURCE_URI), contentValues, "component = ?", strArr);
        } catch (Exception e) {
            LogUtil.wtf(TAG, e);
        }
    }

    public void updateSource(final List<AppBean> list) {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageSourceActivity.6
            @Override // java.lang.Runnable
            public void run() {
                int size = list.size();
                int i = 0;
                while (true) {
                    int i2 = size - 1;
                    if (i >= i2) {
                        BarrageMessageSourceActivity.this.updateSource((AppBean) list.get(i2), true);
                        return;
                    } else {
                        BarrageMessageSourceActivity.this.updateSource((AppBean) list.get(i), false);
                        i++;
                    }
                }
            }
        });
    }
}
