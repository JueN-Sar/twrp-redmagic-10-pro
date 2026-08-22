package cn.nubia.gamecenter.settings.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import cn.nubia.common.view.CropView;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public class WallpaperCropActivity extends Activity implements CropView.OnButtonClickListener {
    public static String TAG = "wallpaper";
    protected CropView mCropView;

    private void initView() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.wallpaper_crop_layout);
        CropView cropView = (CropView) findViewById(R.id.cropView);
        this.mCropView = cropView;
        cropView.setOnButtonClickListener(this);
        Log.d(TAG, "CropAct-------->crop wallpaper start");
        this.mCropView.setSourceBitmap(WallpaperManager.getInstance().getCropBitmap());
    }

    public void hideNavigationBar(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(5382);
        }
    }

    @Override // cn.nubia.common.view.CropView.OnButtonClickListener
    public void onClickApply(View view) {
        Bitmap bitmap;
        Log.d(TAG, "crop - onClickApply() ");
        try {
            bitmap = this.mCropView.getCropBitmap();
        } catch (Exception e) {
            Log.d(TAG, "crop - onClickApply() Exception : " + e.getMessage());
            bitmap = null;
        }
        WallpaperManager.getInstance().setGalleryBitmap(bitmap);
        Intent intent = new Intent();
        intent.putExtra("result", "apply");
        setResult(101, intent);
        finish();
    }

    @Override // cn.nubia.common.view.CropView.OnButtonClickListener
    public void onClickCancel(View view) {
        Log.d(TAG, "crop - onClickCancel() ");
        WallpaperManager.getInstance().clearCropBitmap();
        Intent intent = new Intent();
        intent.putExtra("result", "cancel");
        setResult(101, intent);
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        hideNavigationBar(z);
    }
}
