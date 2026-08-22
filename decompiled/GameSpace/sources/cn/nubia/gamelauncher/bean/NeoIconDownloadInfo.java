package cn.nubia.gamelauncher.bean;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.commoninterface.NeoGameDBColumns;
import cn.nubia.gamelauncher.model.NeoDownloadHelper;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class NeoIconDownloadInfo {
    public static int TYPE_APP_CENTER = 1;
    public static int TYPE_GAME_CENTER = 2;
    public int appId;
    public AppListItemBean appListItemBean;
    private byte[] data;
    private boolean hasUpdateIcon;
    private int id;
    private boolean isNeedUpdateIcon = true;
    public Bitmap mCropIcon;
    public Bitmap mIcon;
    public String packageName;
    public int progress;
    public Bitmap progressIcon;
    public int requestId;
    public String status;
    public String title;
    public int type;
    public int versionCode;

    public NeoIconDownloadInfo(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(NeoGameDBColumns.ID));
        this.appId = cursor.getInt(cursor.getColumnIndex(NeoGameDBColumns.APP_ID));
        String string = cursor.getString(cursor.getColumnIndex(NeoGameDBColumns.TITLE));
        this.title = string;
        this.title = string == null ? "" : string;
        this.packageName = cursor.getString(cursor.getColumnIndex(NeoGameDBColumns.PACKAGENAME));
        this.type = Util.isMyOs() ? TYPE_GAME_CENTER : cursor.getInt(cursor.getColumnIndex("type"));
        if (Util.isTencentAppStore()) {
            this.type = 1;
        }
        this.requestId = Util.isMyOs() ? cursor.getInt(cursor.getColumnIndex(NeoGameDBColumns.REQUEST_ID)) : 0;
        this.versionCode = Util.isMyOs() ? cursor.getInt(cursor.getColumnIndex(NeoGameDBColumns.VERSION_CODE)) : 0;
        this.progress = cursor.getInt(cursor.getColumnIndex(NeoGameDBColumns.PROGRESS));
        this.status = cursor.getString(cursor.getColumnIndex("status"));
    }

    private void updateIcon(Context context) {
        byte[] bArr = this.data;
        if (bArr == null && this.mIcon == null) {
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.mipmap.default_neoicon);
            this.mIcon = decodeResource;
            this.data = BitmapUtils.flattenBitmap(decodeResource);
        } else if (!this.hasUpdateIcon && bArr != null) {
            this.hasUpdateIcon = true;
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            this.mIcon = decodeByteArray;
            if (decodeByteArray == null) {
                Bitmap decodeResource2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.default_neoicon);
                this.mIcon = decodeResource2;
                this.data = BitmapUtils.flattenBitmap(decodeResource2);
                LogUtil.d(NeoDownloadHelper.TAG, "updateIcon show default data == null && icon == null");
            }
        }
        if (this.mCropIcon == null) {
            this.mCropIcon = BitmapUtils.centerCrop(this.mIcon, 144, 144, 34, 0, true);
        }
        if (this.progress >= 99) {
            LogUtil.d(NeoDownloadHelper.TAG, "updateIcon process >= 99  info = " + this);
        }
        Bitmap createBitmapWithProcess = BitmapUtils.createBitmapWithProcess(this.mIcon, 100.0f);
        this.mIcon = createBitmapWithProcess;
        this.progressIcon = BitmapUtils.createBitmapWithProcess(createBitmapWithProcess, this.progress);
    }

    public boolean isNeedUpdateIcon() {
        if (!this.hasUpdateIcon || !this.isNeedUpdateIcon) {
            return false;
        }
        this.isNeedUpdateIcon = false;
        return true;
    }

    public String toString() {
        return "id=" + this.id + " ,app_id=" + this.appId + " ,title=" + this.title + " ,packageName=" + this.packageName + " ,process=" + this.progress + " ,status=" + this.status + " ,hasUpdateIcon=" + this.hasUpdateIcon + " ,type=" + this.type;
    }

    public void updateInfo(Cursor cursor, Context context) {
        int i = this.progress;
        int i2 = cursor.getInt(cursor.getColumnIndex(NeoGameDBColumns.PROGRESS));
        this.progress = i2;
        if (i2 < i) {
            this.progress = i;
        }
        this.status = cursor.getString(cursor.getColumnIndex("status"));
        this.data = cursor.getBlob(cursor.getColumnIndex(NeoGameDBColumns.ICON));
        updateIcon(context);
    }
}
