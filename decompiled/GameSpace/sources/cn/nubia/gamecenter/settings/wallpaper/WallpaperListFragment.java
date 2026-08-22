package cn.nubia.gamecenter.settings.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.wallpaper.WallpaperItemBean;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamecenter.settings.CategoryInfo;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.GcsAnimationUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.Preference;
import cn.nubia.gamecenter.settings.compatible.PreferenceFragment;
import cn.nubia.gamecenter.settings.wallpaper.WallpaperListAdapter;

/* loaded from: classes.dex */
public class WallpaperListFragment extends PreferenceFragment implements FragmentInterface, WallpaperManager.OnWallpaperChangedListener, WallpaperListAdapter.OnItemClickListener {
    public static final int REQUEST_CODE_CROP_WALLPAPER = 101;
    private static final int REQUEST_CODE_OPEN_GALLERY = 100;
    private static final String TAG = "wallpaper";
    WallpaperListAdapter mAdapter;
    private Context mContext;
    private View mRootView;
    private String mTag;
    private View mWallPaparStatusView;
    private RecyclerView mWallpaperList;

    private void cropWallpaer(Bitmap bitmap) {
        Log.d("wallpaper", "--->cropWallpaer()");
        WallpaperManager.getInstance().setCropBitmap(bitmap);
        Intent intent = new Intent(this.mContext, (Class<?>) WallpaperCropActivity.class);
        Log.d("wallpaper", "cropWallpaer() -> start WallpaperCropActivity");
        startActivityForResult(intent, 101);
    }

    public static CategoryInfo getCategoryInfo() {
        return new CategoryInfo(WallpaperListFragment.class, R.drawable.game_space_wallpaper, R.string.gcs_gamecenter_menu_game_wallpaper);
    }

    private void initWallpaperListView(View view) {
        this.mWallpaperList = (RecyclerView) view.findViewById(R.id.wallpaper_list);
        this.mWallpaperList.setLayoutManager(new GridLayoutManager(this.mContext, 2, 1, false));
        WallpaperListAdapter wallpaperListAdapter = new WallpaperListAdapter(this.mContext, WallpaperManager.getInstance().getWallpaperList());
        this.mAdapter = wallpaperListAdapter;
        wallpaperListAdapter.setOnItemClickListener(this);
        this.mWallpaperList.setAdapter(this.mAdapter);
    }

    private void onCropResult(Intent intent) {
        if (intent == null) {
            return;
        }
        Log.d("wallpaper", "onCropResult()");
        String stringExtra = intent.getStringExtra("result");
        Log.d("wallpaper", "Act - onCropResult() result : " + stringExtra);
        if (stringExtra == null || !stringExtra.equals("apply")) {
            return;
        }
        WallpaperManager.getInstance().setWallpaperId(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void onGalleryResult(android.content.Intent r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L9e
            android.net.Uri r0 = r6.getData()
            if (r0 != 0) goto La
            goto L9e
        La:
            java.lang.String r0 = "onGalleryResult()"
            java.lang.String r1 = "wallpaper"
            android.util.Log.d(r1, r0)
            android.content.ContentResolver r0 = r5.getContentResolver()
            r2 = 0
            android.graphics.BitmapFactory$Options r3 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r3.<init>()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            r4 = 0
            r3.inSampleSize = r4     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            android.net.Uri r6 = r6.getData()     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            android.net.Uri r6 = cn.nubia.common.util.CommonUtil.getSecureUri(r6)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            if (r6 != 0) goto L2b
            return
        L2b:
            java.io.InputStream r6 = r0.openInputStream(r6)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L44
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r6, r2, r3)     // Catch: java.lang.Exception -> L40 java.lang.Throwable -> L8e
            if (r6 == 0) goto L56
            r6.close()     // Catch: java.io.IOException -> L39
            goto L56
        L39:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            r6.<init>(r5)
            throw r6
        L40:
            r0 = move-exception
            goto L46
        L42:
            r5 = move-exception
            goto L90
        L44:
            r0 = move-exception
            r6 = r2
        L46:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L8e
            if (r6 == 0) goto L56
            r6.close()     // Catch: java.io.IOException -> L4f
            goto L56
        L4f:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            r6.<init>(r5)
            throw r6
        L56:
            if (r2 == 0) goto L8d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "Act - onGalleryResult() galleryBitmap : "
            r6.<init>(r0)
            int r0 = r2.getByteCount()
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r0 = ", w : "
            java.lang.StringBuilder r6 = r6.append(r0)
            int r0 = r2.getWidth()
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r0 = ", h : "
            java.lang.StringBuilder r6 = r6.append(r0)
            int r0 = r2.getHeight()
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r1, r6)
            r5.cropWallpaer(r2)
        L8d:
            return
        L8e:
            r5 = move-exception
            r2 = r6
        L90:
            if (r2 == 0) goto L9d
            r2.close()     // Catch: java.io.IOException -> L96
            goto L9d
        L96:
            r5 = move-exception
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            r6.<init>(r5)
            throw r6
        L9d:
            throw r5
        L9e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.wallpaper.WallpaperListFragment.onGalleryResult(android.content.Intent):void");
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public Fragment getFragment() {
        return this;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public String getInfoTag() {
        return this.mTag;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 100) {
            onGalleryResult(intent);
        } else if (i != 101) {
            Log.d("wallpaper", "onActivityResult() requestCode : " + i);
        } else {
            onCropResult(intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.gcs_wallpaper_list, viewGroup, false);
        this.mRootView = inflate;
        initWallpaperListView(inflate);
        WallpaperManager.getInstance().registerWallpaperChangedListener(this);
        View findViewById = this.mRootView.findViewById(R.id.wallpaper_list);
        this.mWallPaparStatusView = findViewById;
        GcsAnimationUtil.setGcsItemTranslationY(findViewById);
        return this.mRootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        WallpaperManager.getInstance().unregisterWallpaperChangedListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            new Handler().post(new Runnable() { // from class: cn.nubia.gamecenter.settings.wallpaper.WallpaperListFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    WallpaperListFragment.this.mWallPaparStatusView.setAlpha(0.0f);
                }
            });
        } else {
            GcsAnimationUtil.setGcsItemTranslationY(this.mWallPaparStatusView);
            GcsAnimationUtil.setGcsItemAlpha(this.mWallPaparStatusView);
        }
    }

    @Override // cn.nubia.gamecenter.settings.wallpaper.WallpaperListAdapter.OnItemClickListener
    public void onItemClick(View view, WallpaperItemBean wallpaperItemBean) {
        int childAdapterPosition = this.mWallpaperList.getChildAdapterPosition(view);
        if (WallpaperManager.getInstance().getCurrentWallpaperId() == childAdapterPosition && WallpaperManager.getInstance().getWallpaperList().get(childAdapterPosition).isSelected() && childAdapterPosition != 0) {
            return;
        }
        if (childAdapterPosition != 0) {
            WallpaperManager.getInstance().setWallpaperId(childAdapterPosition);
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setPackage(CommonUtil.isInter() ? "com.google.android.apps.photos" : "com.android.gallery3d");
            intent.setAction("android.intent.action.PICK");
            intent.setType("image/*");
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 100, null);
        } catch (Exception e) {
            Log.w("wallpaper", "onItemClick() - startActivityForResult() exception : " + e.getMessage());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // cn.nubia.gamecenter.settings.compatible.PreferenceFragment
    public boolean onPreferenceTreeClick(Preference preference) {
        return super.onPreferenceTreeClick(preference);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // cn.nubia.common.wallpaper.WallpaperManager.OnWallpaperChangedListener
    public void onWallpaperChanged() {
        if (this.mAdapter == null) {
            return;
        }
        Log.d("wallpaper", "onWallpaperChanged() id : " + WallpaperManager.getInstance().getCurrentWallpaperId());
        int lastWallpaperId = WallpaperManager.getInstance().getLastWallpaperId();
        WallpaperListAdapter.WallpaperViewHolder wallpaperViewHolder = (WallpaperListAdapter.WallpaperViewHolder) this.mWallpaperList.findViewHolderForAdapterPosition(lastWallpaperId);
        this.mAdapter.updateMask(wallpaperViewHolder, lastWallpaperId);
        if (wallpaperViewHolder == null) {
            this.mAdapter.notifyItemChanged(lastWallpaperId);
        }
        int currentWallpaperId = WallpaperManager.getInstance().getCurrentWallpaperId();
        this.mAdapter.updateMask((WallpaperListAdapter.WallpaperViewHolder) this.mWallpaperList.findViewHolderForAdapterPosition(currentWallpaperId), currentWallpaperId);
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setInfoTag(String str) {
        this.mTag = str;
    }

    @Override // cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
    }
}
