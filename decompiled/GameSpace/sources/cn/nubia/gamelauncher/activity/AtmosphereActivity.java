package cn.nubia.gamelauncher.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.helper.ImageCache;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.view.DynamicView;
import cn.nubia.common.view.SimpleEditImageView;
import cn.nubia.common.wallpaper.WallpaperList;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.adapter.AtmosphereAdapter;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.atmosphere.CustomBean;
import cn.nubia.gamelauncher.atmosphere.LiveAtmosphereManager;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.helper.ShortCutHelper;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;
import com.bumptech.glide.Glide;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class AtmosphereActivity extends BaseActivity {
    private static final int OPEN_GALLERY_REQUEST_CODE = 100;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    public static final String TAG = "Atmosphere";
    AtmosphereAdapter mAdapter;
    private TextView mApplyText;
    private Atmosphere mAtmosphere;
    private File mAtmosphereMapFile;
    private RecyclerView mAtmosphereRecyclerView;
    private ImageView mBackIcon;
    private TextView mCancelText;
    private Canvas mCanvas;
    private int mCropBitmapHeight;
    private int mCropBitmapHeightOffSet;
    private int mCropBitmapStartX;
    private int mCropBitmapStartY;
    private int mCropBitmapWidth;
    private AppListItemBean mCurrentItem;
    private int mDeviceHeight;
    private int mDeviceWidth;
    private DynamicView mDynamicView;
    private SimpleEditImageView mEditImageView;
    private Bitmap mGalleryBitmap;
    public String mGalleryPackage;
    String mGameName;
    private Handler mHandler;
    private ArrayList<CustomBean> mList;
    private ImageView mMaskView;
    private Paint mPaint;
    private TextView mPrompt;
    String mUrl;
    private Bitmap savedBitmap;

    public AtmosphereActivity() {
        this.mGalleryPackage = CommonUtil.isInter() ? "com.google.android.apps.photos" : "com.android.gallery3d";
        this.mGalleryBitmap = null;
        this.mPaint = null;
        this.mCanvas = null;
        this.mList = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearOldBitmapFromStorage, reason: merged with bridge method [inline-methods] */
    public void m216xd7f6f780() {
        if (this.mAtmosphereMapFile.exists()) {
            String packageName = this.mCurrentItem.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return;
            }
            Log.d("Atmosphere", "clearOldBitmapFromStorage()");
            File file = new File(this.mAtmosphereMapFile.getAbsolutePath());
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    if (file2.getName().startsWith(packageName)) {
                        file2.delete();
                    }
                }
            }
            Log.d("Atmosphere", "clearOldBitmapFromStorage() End!");
        }
    }

    private void clickApply() {
        if (this.mAdapter == null) {
            return;
        }
        Trace.beginSection("clickApply");
        String applyType = this.mAdapter.getApplyType();
        applyType.hashCode();
        switch (applyType) {
            case "highlight":
                this.mAtmosphere.setType(Atmosphere.TYPE_HIGHLIGHT, false);
                Log.d("Atmosphere", "clickApply Atmosphere.TYPE_HIGHLIGHT");
                doHighLightTrack();
                break;
            case "gallery":
            case "net":
            case "local":
            case "current":
                createUrl();
                drawBitmap();
                notifyAtmosphereChanged();
                WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AtmosphereActivity.this.m216xd7f6f780();
                    }
                });
                WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AtmosphereActivity.this.m217x1b821541();
                    }
                });
                break;
        }
        finish();
        Trace.endSection();
    }

    private void createUrl() {
        this.mUrl = this.mAtmosphereMapFile.getAbsolutePath() + "/" + (this.mAtmosphere.getPackageName() + "_" + Util.getCurrentTime() + ".png");
    }

    private void doHighLightTrack() {
        Log.d("Atmosphere", "doHighLightTrack start");
        Bundle bundle = new Bundle();
        bundle.putString(NubiaTrackManager.EVENT_NAME, "highlight_game_wallpaper_setting");
        bundle.putString("app_name", this.mCurrentItem.getName());
        bundle.putString("package_name", this.mCurrentItem.getPackageName());
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
        Log.d("Atmosphere", "doHighLightTrack end");
    }

    private void drawBitmap() {
        Trace.beginSection("drawBitmap");
        try {
            RectF rectF = new RectF();
            this.mEditImageView.getCurrentMatrix().mapRect(rectF);
            this.mCanvas.drawBitmap(this.mEditImageView.getMatrixBitmap(), rectF.left, rectF.top, this.mPaint);
            LogUtil.d("Atmosphere", "drawBitmap mCropBitmapStartX = " + this.mCropBitmapStartX + " mCropBitmapStartY = " + this.mCropBitmapStartY + " mCropBitmapWidth = " + this.mCropBitmapWidth + " mCropBitmapHeight = " + this.mCropBitmapHeight);
            if (this.mCropBitmapStartX + this.mCropBitmapWidth > this.savedBitmap.getWidth()) {
                this.mCropBitmapWidth = this.savedBitmap.getWidth() - this.mCropBitmapStartX;
            }
            if (this.mCropBitmapStartY + this.mCropBitmapHeight > this.savedBitmap.getHeight()) {
                this.mCropBitmapHeight = this.savedBitmap.getHeight() - this.mCropBitmapStartY;
            }
            this.savedBitmap = Bitmap.createBitmap(this.savedBitmap, this.mCropBitmapStartX, this.mCropBitmapStartY, this.mCropBitmapWidth, this.mCropBitmapHeight);
            ImageCache.getInstance().put(this.mUrl, this.savedBitmap);
        } catch (Exception e) {
            LogUtil.d("Atmosphere", "drawBitmap error " + e.toString());
        }
        Trace.endSection();
    }

    private void initAtmosphereMapFile() {
        this.mAtmosphereMapFile = getApplicationContext().getExternalFilesDir(Atmosphere.CUSTOM_DIR);
    }

    private void initCropItem() {
        AppListItemBean selectedItem = AppAddModel.getInstance().getSelectedItem();
        this.mCurrentItem = selectedItem;
        if (selectedItem == null) {
            Intent intent = getIntent();
            this.mCurrentItem = AppAddModel.getInstance().findItemFromAllList(intent.getStringExtra("selected"), intent.getStringExtra(ShortCutHelper.SHORTCUT_ID));
        }
        if (this.mCurrentItem == null) {
            Log.d("Atmosphere", "initCropItem() error, the selected item not found ");
            finish();
        }
        this.mAtmosphere = this.mCurrentItem.getAtmosphere();
        this.mGameName = this.mCurrentItem.getName();
        Log.d("Atmosphere", "initCropItem() mAtmosphere : " + this.mAtmosphere);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initDataForUpdateHighLightsDirectory, reason: merged with bridge method [inline-methods] */
    public void m223x7d4b270() {
        LiveAtmosphereManager.getInstance().m233xf624d5f5();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    AtmosphereActivity.this.m218xe34e17ec();
                }
            });
        }
    }

    private void initDisplayInfo() {
        int width = Util.getDisplayRect(this).width();
        this.mCropBitmapWidth = width;
        this.mDeviceWidth = width;
        int height = Util.getDisplayRect(this).height();
        this.mCropBitmapHeight = height;
        this.mDeviceHeight = height;
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.crop_bitmap_height_offset);
        this.mCropBitmapHeightOffSet = dimensionPixelOffset;
        this.mCropBitmapStartX = (this.mDeviceWidth - this.mCropBitmapWidth) / 2;
        this.mCropBitmapStartY = ((this.mDeviceHeight - this.mCropBitmapHeight) / 2) - dimensionPixelOffset;
    }

    private void initDynamicView() {
        Atmosphere atmosphere = this.mAtmosphere;
        if (atmosphere == null) {
            return;
        }
        String highLightUrl = atmosphere.getHighLightUrl();
        if (TextUtils.isEmpty(highLightUrl)) {
            return;
        }
        Log.d("Atmosphere", "initDynamicView() url : " + highLightUrl);
        DynamicView dynamicView = (DynamicView) findViewById(R.id.dynamic);
        this.mDynamicView = dynamicView;
        dynamicView.setVisibility(0);
        this.mDynamicView.setRepeated(true);
        this.mDynamicView.setUri(highLightUrl);
        this.mDynamicView.start();
    }

    private void initList() {
        this.mList.add(new CustomBean(this.mAtmosphere.getCurrentUrl(), this.mAtmosphere.getType()));
        if (LiveAtmosphereManager.getInstance().isSupportHighLight(this.mAtmosphere.getPackageName())) {
            this.mList.add(new CustomBean(this.mAtmosphere.getHighLightUrl(), Atmosphere.TYPE_HIGHLIGHT));
        }
        if (this.mAtmosphere.getNetUrl() != null) {
            this.mList.add(new CustomBean(this.mAtmosphere.getNetUrl(), Atmosphere.TYPE_NET));
        }
        Iterator<String> it = WallpaperList.LOCAL_WALLPAPER_LIST.iterator();
        while (it.hasNext()) {
            this.mList.add(new CustomBean(it.next(), Atmosphere.TYPE_LOCAL));
        }
        this.mList.add(new CustomBean("android.resource://cn.nubia.gamelauncher/mipmap/gallery_bg", Atmosphere.TYPE_GALLERY));
        Iterator<CustomBean> it2 = this.mList.iterator();
        while (it2.hasNext()) {
            CustomBean next = it2.next();
            Log.d("Atmosphere", "initList() item : " + next);
            Glide.with((Activity) this).load(next.getUrl()).preload();
        }
    }

    private void initPaintAndCanvas() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-16711936);
        this.savedBitmap = Bitmap.createBitmap(this.mDeviceWidth, this.mDeviceHeight, Bitmap.Config.ARGB_4444);
        this.mCanvas = new Canvas(this.savedBitmap);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.picture_list);
        this.mAtmosphereRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AtmosphereAdapter atmosphereAdapter = new AtmosphereAdapter(this, this.mEditImageView, this.mPrompt, this.mList, this.mAtmosphere, new Runnable() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AtmosphereActivity.this.m219xeb629c30();
            }
        });
        this.mAdapter = atmosphereAdapter;
        this.mAtmosphereRecyclerView.setAdapter(atmosphereAdapter);
    }

    private void initTitle() {
        String str = this.mGameName + "-" + getString(R.string.atmosphere_activity_title);
        TextView textView = (TextView) findViewById(R.id.atmosphere_action_title);
        textView.setVisibility(0);
        textView.setText(str);
    }

    private void initView() {
        this.mEditImageView = (SimpleEditImageView) findViewById(R.id.simple_edit);
        this.mCancelText = (TextView) findViewById(R.id.cancel);
        this.mApplyText = (TextView) findViewById(R.id.apply);
        this.mPrompt = (TextView) findViewById(R.id.atmosphere_highlight_prompt);
        this.mMaskView = (ImageView) findViewById(R.id.custom_mask);
        this.mBackIcon = (ImageView) findViewById(R.id.atmosphere_actionbar_back);
        this.mMaskView.setVisibility(0);
        this.mBackIcon.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtmosphereActivity.this.m220x4025890c(view);
            }
        });
        this.mCancelText.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtmosphereActivity.this.m221x83b0a6cd(view);
            }
        });
        this.mApplyText.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtmosphereActivity.this.m222xc73bc48e(view);
            }
        });
        initTitle();
    }

    private boolean isResultDataSupport(Intent intent) {
        if (intent == null || intent.getData() == null) {
            Log.d("Atmosphere", "isResultDataSupport() data is null!");
            return false;
        }
        String type = getContentResolver().getType(CommonUtil.getSecureUri(intent.getData()));
        if (type != null && type.startsWith("image")) {
            return true;
        }
        Log.d("Atmosphere", "isResultDataSupport() mimeType : " + type);
        return false;
    }

    private void notifyAtmosphereChanged() {
        this.mAtmosphere.setCropUrl(this.mUrl);
        this.mAtmosphere.setType(Atmosphere.TYPE_CROP, false);
    }

    private void saveCropBitmapToStorage(Bitmap bitmap) {
        Log.d("Atmosphere", "saveCropBitmapToStorage()");
        if (!this.mAtmosphereMapFile.exists()) {
            this.mAtmosphereMapFile.mkdirs();
        }
        String cropUrl = this.mAtmosphere.getCropUrl();
        if (CommonUtil.isSecurePath(cropUrl)) {
            File file = new File(cropUrl);
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                if (bitmap != null) {
                    Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight()).compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("Atmosphere", "saveCropBitmapToStorage() End!");
        }
    }

    private void showPermission() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 1);
        }
    }

    public void clickCancel() {
        finish();
    }

    /* renamed from: clickGalley, reason: merged with bridge method [inline-methods] */
    public void m219xeb629c30() {
        try {
            Intent intent = new Intent();
            intent.setPackage(this.mGalleryPackage);
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.setAction("android.intent.action.PICK");
            intent.setType("image/*");
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 100);
        } catch (Exception e) {
            LogUtil.d("Atmosphere", "open ACTION_PICK error : " + e.getMessage() + ", and " + this.mGalleryPackage + " is install : " + Util.isAppInstall(getApplicationContext(), this.mGalleryPackage));
        }
    }

    /* renamed from: lambda$clickApply$6$cn-nubia-gamelauncher-activity-AtmosphereActivity, reason: not valid java name */
    /* synthetic */ void m217x1b821541() {
        saveCropBitmapToStorage(this.savedBitmap);
    }

    /* renamed from: lambda$initDataForUpdateHighLightsDirectory$1$cn-nubia-gamelauncher-activity-AtmosphereActivity, reason: not valid java name */
    /* synthetic */ void m218xe34e17ec() {
        initList();
        initDynamicView();
        initRecyclerView();
    }

    /* renamed from: lambda$initView$2$cn-nubia-gamelauncher-activity-AtmosphereActivity, reason: not valid java name */
    /* synthetic */ void m220x4025890c(View view) {
        clickCancel();
    }

    /* renamed from: lambda$initView$3$cn-nubia-gamelauncher-activity-AtmosphereActivity, reason: not valid java name */
    /* synthetic */ void m221x83b0a6cd(View view) {
        clickCancel();
    }

    /* renamed from: lambda$initView$4$cn-nubia-gamelauncher-activity-AtmosphereActivity, reason: not valid java name */
    /* synthetic */ void m222xc73bc48e(View view) {
        clickApply();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (!isResultDataSupport(intent)) {
                Log.d("Atmosphere", "onActivityResult() but data is not support!");
                this.mAdapter.doSelectedGallery(false);
                return;
            }
            Log.d("Atmosphere", "onActivityResult() and has data : " + intent);
            this.mAdapter.doSelectedGallery(true);
            try {
                InputStream openInputStream = getContentResolver().openInputStream(CommonUtil.getSecureUri(intent.getData()));
                Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
                this.mGalleryBitmap = decodeStream;
                if (decodeStream != null && (decodeStream.getWidth() > this.mDeviceWidth || this.mGalleryBitmap.getHeight() > this.mDeviceHeight)) {
                    int max = Math.max(this.mGalleryBitmap.getWidth() / this.mDeviceWidth, this.mGalleryBitmap.getHeight() / this.mDeviceHeight);
                    Bitmap bitmap = this.mGalleryBitmap;
                    this.mGalleryBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / max, this.mGalleryBitmap.getHeight() / max, true);
                }
                this.mEditImageView.setBitmap(this.mGalleryBitmap);
                openInputStream.close();
            } catch (Exception unused) {
                Log.d("Atmosphere", "InputStream close error");
            }
        }
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.atmosphere_layout);
        initCropItem();
        initView();
        initDisplayInfo();
        initPaintAndCanvas();
        initAtmosphereMapFile();
        AppListItemBean appListItemBean = this.mCurrentItem;
        if (appListItemBean != null && !TextUtils.isEmpty(appListItemBean.getPackageName()) && LiveAtmosphereManager.getInstance().isSupportHighLight(this.mCurrentItem.getPackageName())) {
            this.mHandler = new Handler(Looper.getMainLooper());
            WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.activity.AtmosphereActivity$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    AtmosphereActivity.this.m223x7d4b270();
                }
            });
        } else {
            initList();
            initDynamicView();
            initRecyclerView();
        }
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        DynamicView dynamicView = this.mDynamicView;
        if (dynamicView != null) {
            dynamicView.release();
        }
    }
}
