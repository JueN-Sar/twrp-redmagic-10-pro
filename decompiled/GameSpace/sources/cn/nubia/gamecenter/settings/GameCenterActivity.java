package cn.nubia.gamecenter.settings;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.CategoryAdapter;
import cn.nubia.gamecenter.settings.about.AboutFragment;
import cn.nubia.gamecenter.settings.about.RaceKeyOffFragment;
import cn.nubia.gamecenter.settings.basic.BasicActivity;
import cn.nubia.gamecenter.settings.basic.FlaseTouchFragment;
import cn.nubia.gamecenter.settings.basic.NotDisturbFragment;
import cn.nubia.gamecenter.settings.basic.OtherOptionsFragment;
import cn.nubia.gamecenter.settings.basic.StreamingFragment;
import cn.nubia.gamecenter.settings.basic.ZteFlaseTouchFragment;
import cn.nubia.gamecenter.settings.datamanager.DataManagerFragment;
import cn.nubia.gamecenter.settings.gamekeylamp.GameKeysLampFragment;
import cn.nubia.gamecenter.settings.mirror.MirrorHostFragment;
import cn.nubia.gamecenter.settings.net.NetFragment;
import cn.nubia.gamecenter.settings.screen.ScreenSettingsFragment;
import cn.nubia.gamecenter.settings.summary.ArkBaseFragment;
import cn.nubia.gamecenter.settings.summary.SummaryFragment;
import cn.nubia.gamecenter.settings.utils.FlickerUtils;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import cn.nubia.gamecenter.settings.wallpaper.WallpaperListFragment;
import cn.nubia.gamecenter.settings.watermark.WatermarkFragment;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class GameCenterActivity extends BasicActivity implements View.OnClickListener, CategoryAdapter.OnItemClickListener {
    private static final String HAS_NEW_VERSION = "hasNewVersion";
    private static final String KEY_START_TYPE = "gcs_start_type";
    private static final String TAG = "GameCenterActivity";
    private CategoryAdapter mAdapter;
    private Handler mAnimationHandler;
    private RecyclerView mCategoryView;
    private Fragment mCurrentFragment;
    private FragmentManager mFragmentManager;

    private static void addCategory(ArrayList<CategoryInfo> arrayList) {
        if (FeatureUtil.supportStreaming()) {
            arrayList.add(arrayList.size() - 1, StreamingFragment.getCategoryInfo());
            LogUtil.w(TAG, "add StreamingFragment");
        }
    }

    public static Map<String, CategoryInfo> getAllCategory() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ArkBaseFragment.getCategoryInfo());
        arrayList.add(DataManagerFragment.getCategoryInfo());
        arrayList.add(SummaryFragment.getCategoryInfo());
        arrayList.add(NetFragment.getCategoryInfo());
        arrayList.add(NotDisturbFragment.getCategoryInfo());
        arrayList.add(FlaseTouchFragment.getCategoryInfo());
        arrayList.add(RaceKeyOffFragment.getCategoryInfo());
        arrayList.add(WallpaperListFragment.getCategoryInfo());
        arrayList.add(WatermarkFragment.getCategoryInfo());
        arrayList.add(StreamingFragment.getCategoryInfo());
        arrayList.add(OtherOptionsFragment.getCategoryInfo());
        arrayList.add(GameKeysLampFragment.getCategoryInfo());
        arrayList.add(MirrorHostFragment.getCategoryInfo());
        arrayList.add(ScreenSettingsFragment.getCategoryInfo());
        arrayList.add(ZteFlaseTouchFragment.getCategoryInfo());
        arrayList.add(AboutFragment.getCategoryInfo());
        HashMap hashMap = new HashMap(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CategoryInfo categoryInfo = (CategoryInfo) it.next();
            hashMap.put(categoryInfo.getSimpleName(), categoryInfo);
        }
        return hashMap;
    }

    private ArrayList<CategoryInfo> getShowCategory() {
        Map<String, CategoryInfo> allCategory = getAllCategory();
        ArrayList<CategoryInfo> arrayList = new ArrayList<>();
        for (String str : FeatureUtil.getGameCenterMenu()) {
            if (str.equals(CategoryInfo.TYPE_DIVIDER_NAME)) {
                arrayList.add(new CategoryInfo());
            } else {
                CategoryInfo categoryInfo = allCategory.get(str);
                if (categoryInfo != null) {
                    arrayList.add(categoryInfo);
                } else {
                    LogUtil.w(TAG, "unknown " + str);
                }
            }
        }
        addCategory(arrayList);
        removeCategory(allCategory, arrayList);
        return arrayList;
    }

    private void initCategoryView() {
        this.mCategoryView.setLayoutManager(new LinearLayoutManager(this));
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, getShowCategory());
        this.mAdapter = categoryAdapter;
        categoryAdapter.setOnItemClickListener(this);
        this.mCategoryView.setAdapter(this.mAdapter);
    }

    private void initView() {
        findViewById(R.id.left_name).setOnClickListener(this);
        this.mCategoryView = (RecyclerView) findViewById(R.id.category);
        initCategoryView();
        Handler handler = new Handler();
        this.mAnimationHandler = handler;
        handler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.GameCenterActivity.1
            @Override // java.lang.Runnable
            public void run() {
                GcsAnimationUtil.setGcsMenuTranslationX(GameCenterActivity.this.mCategoryView);
                GcsAnimationUtil.setGcsRedItemAlpha(GameCenterActivity.this.mCategoryView);
            }
        });
    }

    private void removeCategory(Map<String, CategoryInfo> map, ArrayList<CategoryInfo> arrayList) {
        if (FeatureUtil.contains679Or709() && !Utils.isSupportColorfulLight(this)) {
            LogUtil.w(TAG, "remove GameKeysLampFragment");
            arrayList.remove(map.get(GameKeysLampFragment.TAG));
        }
        if (Utils.isCustomizeIP_PB_CN()) {
            LogUtil.w(TAG, "remove GameKeysLampFragment NX789J IP_PB_CN");
            arrayList.remove(map.get(GameKeysLampFragment.TAG));
        }
        if (Utils.isMTKLiquidCool()) {
            LogUtil.w(TAG, "remove GameKeysLampFragment P688F02 LIQUIDCOOL");
            arrayList.remove(map.get(GameKeysLampFragment.TAG));
        }
        if (!FeatureUtil.supportRaceKeyOff()) {
            LogUtil.w(TAG, "remove RaceKeyOffFragment " + arrayList.remove(map.get("RaceKeyOffFragment")));
        }
        if (!FeatureUtil.supportStreaming()) {
            LogUtil.w(TAG, "remove StreamingFragment " + arrayList.remove(map.get("StreamingFragment")));
        }
        if (FeatureUtil.supportMirrorHost()) {
            return;
        }
        LogUtil.w(TAG, "remove MirrorHostFragment " + arrayList.remove(map.get("MirrorHostFragment")));
    }

    private void setStartType(Intent intent) {
        if (this.mAdapter.setStartType(getIntent().getStringExtra(KEY_START_TYPE))) {
            this.mCategoryView.smoothScrollToPosition(this.mAdapter.getCurrentPosition());
        }
        FlickerUtils.showFlicker(intent.getStringExtra("view_id"));
    }

    private void setWeekMode(Fragment fragment) {
        if (this.mAdapter.mSummaryWeekMode && (fragment instanceof SummaryFragment)) {
            LogUtil.i(TAG, "SummaryFragment Week Mode");
            ((SummaryFragment) fragment).setWeekMode(true);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.left_name) {
            finish();
        }
    }

    @Override // cn.nubia.gamecenter.settings.basic.BasicActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gcs_gamecenter_main);
        initView();
        this.mFragmentManager = getSupportFragmentManager();
        setStartType(getIntent());
        Utils.setNewVersion(getIntent().getBooleanExtra(HAS_NEW_VERSION, false));
        NubiaTrackManager.getInstance().init(getApplicationContext());
    }

    @Override // cn.nubia.gamecenter.settings.basic.BasicActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        FlickerUtils.release();
    }

    @Override // cn.nubia.gamecenter.settings.CategoryAdapter.OnItemClickListener
    public void onItemClick(Class<?> cls) {
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        if (this.mCurrentFragment != null) {
            if (cls.getSimpleName().equals(this.mCurrentFragment.getClass().getSimpleName())) {
                return;
            } else {
                beginTransaction.hide(this.mCurrentFragment);
            }
        }
        Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag(cls.getSimpleName());
        if (findFragmentByTag != null) {
            beginTransaction.show(findFragmentByTag);
        } else {
            LogUtil.i(TAG, "getFragment " + cls.getSimpleName());
            try {
                findFragmentByTag = ((FragmentInterface) cls.newInstance()).getFragment();
                beginTransaction.add(R.id.content, findFragmentByTag, cls.getSimpleName());
                setWeekMode(findFragmentByTag);
            } catch (IllegalAccessException | InstantiationException e) {
                LogUtil.wtf(TAG, e);
                return;
            }
        }
        this.mCurrentFragment = findFragmentByTag;
        beginTransaction.commit();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setStartType(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.mCurrentFragment != null) {
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.hide(this.mCurrentFragment);
            beginTransaction.commit();
            this.mCurrentFragment = null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mAdapter.onResume();
    }
}
