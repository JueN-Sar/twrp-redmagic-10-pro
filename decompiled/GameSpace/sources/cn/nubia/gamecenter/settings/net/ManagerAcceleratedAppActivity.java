package cn.nubia.gamecenter.settings.net;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.basic.BasicActivity;
import cn.nubia.gamecenter.settings.compatible.GameModeHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ManagerAcceleratedAppActivity extends BasicActivity {
    public static final String DB_WHITE_LIST = "game_mode_white_list";
    public static final String KEY_GAME_SUPPORT_LIST = "game_support_list";
    private static final String TAG = "ManagerAcceleratedAppActivity";
    private View emptyView;
    private ApplistAdapter mApplistAdapter;
    private ListView mListView;
    private String mSupportGamePackage;
    private View vAcceleratedAppView;
    private List<AppItem> mAppList = new ArrayList();
    private List<String> mSupportGameList = new ArrayList();

    private static class AppHolder {
        ImageView mImageView;
        TextView mTextView;

        private AppHolder() {
        }
    }

    private static class AppItem {
        String activityName;
        Drawable icon;
        CharSequence label;
        String packageName;

        private AppItem() {
        }
    }

    private class ApplistAdapter extends ArrayAdapter<AppItem> {
        private LayoutInflater mInflater;

        public ApplistAdapter(Context context) {
            super(context, 0);
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
            addAll(ManagerAcceleratedAppActivity.this.mAppList);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            AppHolder appHolder;
            View view2;
            AppItem item = getItem(i);
            if (view == null) {
                appHolder = new AppHolder();
                view2 = this.mInflater.inflate(R.layout.gcs_net_accelerated_app_item, viewGroup, false);
                appHolder.mImageView = (ImageView) view2.findViewById(R.id.gcs_net_accelerated_app_icon);
                appHolder.mTextView = (TextView) view2.findViewById(R.id.gcs_net_accelerated_app_name);
                view2.setTag(appHolder);
            } else {
                appHolder = (AppHolder) view.getTag();
                view2 = view;
            }
            appHolder.mImageView.setImageDrawable(item.icon);
            appHolder.mTextView.setText(item.label);
            return view2;
        }
    }

    private AppItem createAppItem(ApplicationInfo applicationInfo, PackageManager packageManager) {
        AppItem appItem = new AppItem();
        appItem.packageName = applicationInfo.packageName;
        appItem.label = applicationInfo.loadLabel(packageManager).toString();
        appItem.icon = applicationInfo.loadIcon(packageManager);
        return appItem;
    }

    private void getWhiteListApps() {
        try {
            this.mAppList.clear();
            PackageManager packageManager = getPackageManager();
            for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(8192)) {
                if (isInNetworkAccGameList(applicationInfo.packageName)) {
                    this.mAppList.add(createAppItem(applicationInfo, packageManager));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniListView() {
        ApplistAdapter applistAdapter = new ApplistAdapter(this);
        this.mApplistAdapter = applistAdapter;
        this.mListView.setAdapter((ListAdapter) applistAdapter);
    }

    private void initAcceleratedAppView() {
        this.vAcceleratedAppView = findViewById(R.id.accelerated_app_view);
        this.mListView = (ListView) findViewById(android.R.id.list);
        this.emptyView = findViewById(R.id.emptyView);
    }

    private void initActionBar() {
        findViewById(R.id.manager_app_icon).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.net.ManagerAcceleratedAppActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ManagerAcceleratedAppActivity.this.onBackPressed();
            }
        });
    }

    private void initSupportNetworkAccGameList() {
        this.mSupportGameList.clear();
        String string = Settings.Global.getString(getContentResolver(), GameModeHelper.NETWORK_ACCELERATION_APP_LABEL_WHITE_LIST);
        this.mSupportGamePackage = Settings.Global.getString(getContentResolver(), "network_acceleration_app_package");
        LogUtil.d(TAG, "mSupportGamePackage:" + this.mSupportGamePackage);
        if (string == null || string.equals("")) {
            return;
        }
        this.mSupportGameList.clear();
        for (String str : string.split("\\|")) {
            if (!"".equals(str)) {
                this.mSupportGameList.add(str);
            }
        }
    }

    private boolean isInNetworkAccGameList(String str) {
        if (str == null) {
            return false;
        }
        try {
            PackageManager packageManager = getPackageManager();
            if (!this.mSupportGameList.contains(packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString().replaceAll("\\|", ""))) {
                if (TextUtils.isEmpty(this.mSupportGamePackage)) {
                    return false;
                }
                if (!this.mSupportGamePackage.contains(str)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            LogUtil.e(TAG, "Could not get label for " + str + ": " + e);
            return false;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // cn.nubia.gamecenter.settings.basic.BasicActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gcs_manager_accelerated_apps);
        initActionBar();
        initAcceleratedAppView();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            LogUtil.d(TAG, "onResume");
            initSupportNetworkAccGameList();
            getWhiteListApps();
            List<AppItem> list = this.mAppList;
            if (list == null || list.size() <= 0) {
                this.emptyView.setVisibility(0);
                this.vAcceleratedAppView.setVisibility(8);
            } else {
                this.emptyView.setVisibility(8);
                this.vAcceleratedAppView.setVisibility(0);
                iniListView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
