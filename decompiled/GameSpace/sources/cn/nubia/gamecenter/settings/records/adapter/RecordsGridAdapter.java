package cn.nubia.gamecenter.settings.records.adapter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.app.ShareCompat;
import androidx.media3.common.MimeTypes;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.bean.HighlightsFile;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import com.bumptech.glide.Glide;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class RecordsGridAdapter extends BaseAdapter {
    private static final String TAG = "RecordsActivity";
    public CallBack callBack;
    private boolean isSupport = FeatureUtil.getBoolean(HighLightsUtils.ZTE_FEATURE_GAME_RANDOM_RECORD, false).booleanValue();
    private Context mContext;
    private List<HighlightsFile> mData;
    private AlertDialog mDialog;
    private HashMap<String, Integer> mPreViewHashMap;
    private PopupWindow popupWindow;

    public interface CallBack {
        void refreshData(boolean z, boolean z2, HighlightsFile highlightsFile);
    }

    private static class ViewHolder {
        ImageView mImageView;
        ImageView mNewIcon;
        TextView mVideoDurationTime;
        TextView mVideoLabel;
        ImageView mVideoTypeView;

        private ViewHolder() {
        }
    }

    public RecordsGridAdapter(Context context, List<HighlightsFile> list, HashMap<String, Integer> hashMap) {
        this.mContext = context;
        this.mData = list;
        this.mPreViewHashMap = hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentValues covertToVideoContentValues(HighLightsDb highLightsDb) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", highLightsDb.getPath());
        contentValues.put("isPreview", (Integer) 1);
        return contentValues;
    }

    private void deleteFile(Uri uri, int i) {
        if (deleteFile(uri, i, this.mContext)) {
            sendDeleteBroadcast();
        }
    }

    private boolean deleteFile(Uri uri, int i, Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        if (i == 1) {
            contentValues.put("is_trashed", (Integer) 1);
        } else {
            if (i != 2 && i != 3) {
                return false;
            }
            contentValues.put("is_trashed", (Integer) 1);
        }
        return contentResolver.update(uri, contentValues, null, null) > 0;
    }

    private int getResID(String str) {
        if (str.contains(HighLightsUtils.WZRY_PACKAGE_NAME) || str.contains(HighLightsUtils.LOL_PACKAGE_NAME)) {
            if (str.contains(HighLightsUtils.AUTO_FIRST)) {
                return R.string.gcs_game_video_label_kill;
            }
            if (str.contains(HighLightsUtils.AUTO_SECOND)) {
                return R.string.gcs_game_video_label_team;
            }
            if (str.contains(HighLightsUtils.AUTO_THIRD)) {
                return R.string.gcs_game_video_label_victory;
            }
            if (str.contains(HighLightsUtils.AUTO_FOURTH)) {
                return R.string.gcs_game_video_label_hero;
            }
        }
        if (str.contains(HighLightsUtils.CJZC_PACKAGE_NAME) || str.contains("com.tencent.tmgp.cf") || str.contains(HighLightsUtils.SMZH_PACKAGE_NAME)) {
            if (str.contains(HighLightsUtils.AUTO_FIRST)) {
                return R.string.gcs_game_video_label_kill;
            }
            if (str.contains(HighLightsUtils.AUTO_SECOND)) {
                return R.string.gcs_game_video_label_fight;
            }
            if (str.contains(HighLightsUtils.AUTO_THIRD)) {
                return R.string.gcs_game_video_label_move;
            }
        }
        if (str.contains(HighLightsUtils.YS_PACKAGE_NAME)) {
            if (str.contains(HighLightsUtils.AUTO_FIRST)) {
                return R.string.gcs_game_video_label_fly;
            }
            if (str.contains(HighLightsUtils.AUTO_SECOND)) {
                return R.string.gcs_game_video_label_landscape;
            }
            if (str.contains(HighLightsUtils.AUTO_THIRD)) {
                return R.string.gcs_game_video_label_battle;
            }
        }
        if (!str.contains(HighLightsUtils.XQTD_PACKAGE_NAME)) {
            return 0;
        }
        if (str.contains(HighLightsUtils.AUTO_FIRST)) {
            return R.string.gcs_game_video_label_landscape;
        }
        if (str.contains(HighLightsUtils.AUTO_SECOND)) {
            return R.string.gcs_game_video_label_battle;
        }
        return 0;
    }

    public static void setThumbImage(Context context, HighlightsFile highlightsFile, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context).load(highlightsFile.getPath()).centerCrop().dontAnimate().into(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(final int i) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.gcs_record_dialog_delete_layout, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        this.mDialog = create;
        create.setButton(-2, this.mContext.getString(R.string.gcs_game_high_light_dialog_delete_cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.adapter.RecordsGridAdapter$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
        this.mDialog.setButton(-1, this.mContext.getString(R.string.gcs_game_high_light_dialog_delete_ok), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.adapter.RecordsGridAdapter$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                RecordsGridAdapter.this.m212xdba7099b(i, dialogInterface, i2);
            }
        });
        this.mDialog.show();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<HighlightsFile> list = this.mData;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x00f4, code lost:
    
        if (r2.equals(r5.mPreViewHashMap.get(r6)) == false) goto L18;
     */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(final int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            Method dump skipped, instructions count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.records.adapter.RecordsGridAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    /* renamed from: lambda$showDialog$1$cn-nubia-gamecenter-settings-records-adapter-RecordsGridAdapter, reason: not valid java name */
    /* synthetic */ void m212xdba7099b(int i, DialogInterface dialogInterface, int i2) {
        HighlightsFile highlightsFile = this.mData.get(i);
        deleteFile(this.mData.get(i).getUri(), this.mData.get(i).getType());
        this.mData.remove(i);
        if (this.mData.size() == 0) {
            this.callBack.refreshData(true, true, highlightsFile);
        } else if (this.mData.size() % 6 == 0) {
            this.callBack.refreshData(false, true, highlightsFile);
        } else {
            this.callBack.refreshData(false, false, highlightsFile);
            notifyDataSetChanged();
        }
        dialogInterface.dismiss();
    }

    public void openImage(Context context, Uri uri) {
        try {
            LogUtil.i(TAG, "openImage fileUri = " + uri);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uri, MimeTypes.IMAGE_JPEG);
            intent.putExtra("is_game_highlights", "cn.nubia.gamehighlights");
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    public void playVideo(Context context, Uri uri) {
        try {
            LogUtil.i(TAG, "playVideo fileUri = " + uri);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("is_game_highlights", "cn.nubia.gamehighlights");
            intent.setDataAndType(uri, MimeTypes.VIDEO_MP4);
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    public void playZteVideo(Context context, Uri uri) {
        try {
            LogUtil.i(TAG, "playZteVideo fileUri = " + uri);
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setClassName("com.zte.videoplayer", "com.zte.videoplayer.VideoPlayerActivity");
            intent.setAction("com.android.camera.action.REVIEW");
            intent.putExtra("gallery_rotation", true);
            intent.setDataAndType(uri, MimeTypes.VIDEO_MP4);
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    public void sendDeleteBroadcast() {
        Intent intent = new Intent();
        intent.setAction("cn.nubia.gamecenter.action.DELETE_HIGH_LIGHTS_FILE");
        this.mContext.sendBroadcast(intent);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void shareFile(Uri uri, boolean z) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            if (z) {
                intent.setType("video/*");
            } else {
                intent.setType("image/*");
            }
            intent.putExtra("safe_shared", true);
            intent.putExtra("android.intent.extra.STREAM", uri);
            Context context = this.mContext;
            context.startActivity(Intent.createChooser(intent, context.getResources().getText(R.string.gcs_game_high_light_pop_window_share)));
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    public void shareInterFile(Uri uri, boolean z) {
        try {
            if (z) {
                ShareCompat.IntentBuilder.from((Activity) this.mContext).setType("video/*").setStream(uri).startChooser();
            } else {
                ShareCompat.IntentBuilder.from((Activity) this.mContext).setType("image/*").setStream(uri).startChooser();
            }
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }
}
