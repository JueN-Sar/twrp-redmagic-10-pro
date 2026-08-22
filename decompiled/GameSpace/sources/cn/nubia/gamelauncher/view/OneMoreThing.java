package cn.nubia.gamelauncher.view;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.bean.OMTInfo;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class OneMoreThing extends ConstraintLayout implements View.OnClickListener {
    private static final String AUTHORITY = "com.zte.onemorething.contentProvider/omt_info";
    private static final long GETDATA_TIME = 2000;
    private static final long INTERVAL_TIME = 8000;
    private static final String METHOD_POST_VOTE_DATA = "postVoteData";
    private static final Uri OMT_PROVIDER_URI = Uri.parse("content://com.zte.onemorething.contentProvider/omt_info");
    private static final String PARAM_PACKAGE_NAME = "packageName";
    private static final String TAG = "OneMoreThing";
    private static final String WRAPPER_CLASS_NAME = "com.zte.gameassist.GameAssistWrapper";
    String currentCountry;
    String currentLanguage;
    private boolean isFirst;
    private boolean isZan;
    private String mCurrentTips;
    private int mDisplayNo;
    private Handler mHandler;
    private int mHotLevels;
    private int mNoteId;
    private String mNotes;
    private List<OMTInfo> mOMTInfo;
    private TextView mOmtText;
    private TextView mOmtZan;
    ContentObserver mRequestOMTInfoDataObserver;

    public OneMoreThing(Context context) {
        this(context, null);
    }

    public OneMoreThing(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isZan = false;
        this.isFirst = true;
        this.mDisplayNo = 0;
        this.mCurrentTips = null;
        this.mRequestOMTInfoDataObserver = new ContentObserver(this.mHandler) { // from class: cn.nubia.gamelauncher.view.OneMoreThing.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if (OneMoreThing.OMT_PROVIDER_URI.equals(uri)) {
                    try {
                        OneMoreThing.this.requestOMTInfoData();
                        Log.d(OneMoreThing.TAG, "omt info update");
                    } catch (Exception e) {
                        Log.e(OneMoreThing.TAG, "Error updating OMT info", e);
                    }
                }
            }
        };
        initLanguage();
        init();
        this.mOmtText.setOnClickListener(this);
        this.mOmtText.setSingleLine();
        this.mOmtText.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.mOmtText.setMarqueeRepeatLimit(-1);
        this.mOmtZan.setOnClickListener(this);
        this.mOmtText.setFocusable(false);
        requestOMTInfoData();
    }

    private void init() {
        this.mHandler = new Handler(Looper.getMainLooper());
        View inflate = LayoutInflater.from(getContext()).inflate(Controller.getInstance().isPureMode() ? R.layout.one_more_thing_layout_pure : R.layout.one_more_thing_layout, this);
        this.mOmtText = (TextView) inflate.findViewById(R.id.omt_text);
        this.mOmtZan = (TextView) inflate.findViewById(R.id.omt_zan);
        updateTextDelayed();
    }

    private void initLanguage() {
        Locale locale = getContext().getResources().getConfiguration().locale;
        this.currentLanguage = locale.getLanguage();
        this.currentCountry = locale.getCountry();
    }

    private void omtTrackManager() {
        if (AppAddModel.getInstance().getSelectedItem() == null) {
            return;
        }
        String name = AppAddModel.getInstance().getSelectedItem().getName();
        Bundle bundle = new Bundle();
        bundle.putString("package_name", "cn.nubia.gamelauncher");
        bundle.putString(NubiaTrackManager.EVENT_NAME, "omt_click");
        bundle.putString("app_name", name);
        bundle.putString("position", "game_lobby");
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private void postVoteData(int i, int i2) {
        Log.d(TAG, "postVoteData id = " + i + ", hasVote = " + i2);
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("id", i);
                bundle.putInt("hasVote", i2);
                ContentProviderClient acquireUnstableContentProviderClient = getContext().getContentResolver().acquireUnstableContentProviderClient(OMT_PROVIDER_URI);
                if (acquireUnstableContentProviderClient == null) {
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                        return;
                    }
                    return;
                }
                try {
                    acquireUnstableContentProviderClient.call(METHOD_POST_VOTE_DATA, null, bundle);
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                } catch (Exception e) {
                    e = e;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    Log.d(TAG, "postVoteData() e : " + e.getMessage());
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestOMTInfoData() {
        Context applicationContext = getContext().getApplicationContext();
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("packageName", applicationContext.getApplicationContext().getPackageName());
                ContentProviderClient acquireUnstableContentProviderClient = applicationContext.getApplicationContext().getContentResolver().acquireUnstableContentProviderClient(OMT_PROVIDER_URI);
                if (acquireUnstableContentProviderClient == null) {
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                        return;
                    }
                    return;
                }
                try {
                    acquireUnstableContentProviderClient.call("getOMTInfoData", null, bundle);
                    String string = bundle.getString("OMTInfo");
                    Log.d(TAG, "requestOMTInfoData() jsonArray =  " + string);
                    this.mOMTInfo = (List) new Gson().fromJson(string, new TypeToken<List<OMTInfo>>() { // from class: cn.nubia.gamelauncher.view.OneMoreThing.2
                    }.getType());
                    Log.d(TAG, "requestOMTInfoData() mOMTInfo =  " + this.mOMTInfo);
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                } catch (Exception e) {
                    e = e;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    Log.d(TAG, "requestOMTInfoData() e : " + e.getMessage());
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    contentProviderClient = acquireUnstableContentProviderClient;
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void setAlpha(int i) {
        float f = i;
        this.mOmtText.setAlpha(f);
        this.mOmtZan.setAlpha(f);
    }

    private void updateOMTZanData(boolean z, int i) {
        this.mOmtZan.setBackgroundResource(z ? R.drawable.zan_pressed : R.drawable.zan_normal);
        if (!z) {
            postVoteData(i, 0);
        } else {
            postVoteData(i, 1);
            Log.d(TAG, "updateOMTZanData ---> insert is succeed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void updateText() {
        Bundle bundle;
        ContentProviderClient acquireUnstableContentProviderClient;
        this.isFirst = false;
        ContentProviderClient contentProviderClient = null;
        ContentProviderClient contentProviderClient2 = null;
        try {
            try {
                bundle = new Bundle();
                bundle.putString("packageName", getContext().getPackageName());
                acquireUnstableContentProviderClient = getContext().getContentResolver().acquireUnstableContentProviderClient(OMT_PROVIDER_URI);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        if (acquireUnstableContentProviderClient == null) {
            if (acquireUnstableContentProviderClient != null) {
                acquireUnstableContentProviderClient.close();
                return;
            }
            return;
        }
        try {
            Bundle call = acquireUnstableContentProviderClient.call("getOMTInfoData", null, bundle);
            if (call != null) {
                this.mOMTInfo = (List) new Gson().fromJson(call.getString("OMTInfo"), new TypeToken<List<OMTInfo>>() { // from class: cn.nubia.gamelauncher.view.OneMoreThing.3
                }.getType());
            }
            OMTInfo oMTInfo = this.mOMTInfo.get(this.mDisplayNo);
            this.mNoteId = oMTInfo.getId();
            if ("en".equals(this.currentLanguage)) {
                this.mNotes = oMTInfo.getEn_note();
            } else if ("zh".equals(this.currentLanguage) && "TW".equals(this.currentCountry)) {
                this.mNotes = oMTInfo.getTr_note();
            } else {
                this.mNotes = oMTInfo.getNote();
            }
            String str = this.mNotes;
            if (str == null || str.length() == 0) {
                this.mNotes = oMTInfo.getNote();
            }
            this.mHotLevels = oMTInfo.getHot_level();
            int hasVote = oMTInfo.getHasVote();
            this.isZan = hasVote == 1;
            Log.d(TAG, "updateText() kxxxxxy---> mNoteId = " + this.mNoteId + " mNotes = " + this.mNotes + " mHotLevels = " + this.mHotLevels + " isZan = " + this.isZan);
            updateTextView();
            contentProviderClient = hasVote;
            if (acquireUnstableContentProviderClient != null) {
                acquireUnstableContentProviderClient.close();
                contentProviderClient = hasVote;
            }
        } catch (Exception e2) {
            e = e2;
            contentProviderClient2 = acquireUnstableContentProviderClient;
            Log.d(TAG, "updateText() kxxxxxy---> " + e.getMessage());
            contentProviderClient = contentProviderClient2;
            if (contentProviderClient2 != null) {
                contentProviderClient2.close();
                contentProviderClient = contentProviderClient2;
            }
        } catch (Throwable th2) {
            th = th2;
            contentProviderClient = acquireUnstableContentProviderClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
            throw th;
        }
    }

    private void updateTextDelayed() {
        long j = this.isFirst ? 0L : INTERVAL_TIME;
        Log.d(TAG, "updateTextDelayed() delayMillis : " + j);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.view.OneMoreThing$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                OneMoreThing.this.updateText();
            }
        }, j);
    }

    private void updateTextView() {
        String str;
        Log.w(TAG, "updateTextView(s) -- mCurrentTips : " + this.mCurrentTips);
        this.mHandler.removeCallbacksAndMessages(null);
        List<OMTInfo> list = this.mOMTInfo;
        if (list == null || list.size() == 0 || (str = this.mNotes) == null || str.length() == 0) {
            setAlpha(0);
            updateTextDelayed();
            return;
        }
        setAlpha(1);
        this.mOmtText.setText(this.mNotes);
        if (CommonUtil.isInternalVersion()) {
            this.mHotLevels = 0;
        }
        int i = this.mHotLevels;
        if (i == 1) {
            this.mOmtText.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getContext().getResources().getDrawable(R.drawable.fire1), (Drawable) null);
        } else if (i == 2) {
            this.mOmtText.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getContext().getResources().getDrawable(R.drawable.fire2), (Drawable) null);
        } else if (i != 3) {
            this.mOmtText.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            this.mOmtText.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getContext().getResources().getDrawable(R.drawable.fire3), (Drawable) null);
        }
        if (this.mDisplayNo + 1 == this.mOMTInfo.size()) {
            this.mDisplayNo = 0;
        } else {
            this.mDisplayNo++;
        }
        this.mOmtZan.setBackgroundResource(this.isZan ? R.drawable.zan_pressed : R.drawable.zan_normal);
        updateTextDelayed();
        Log.w(TAG, "updateTextView(e)");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        register();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.d(TAG, "onClick() - updateText()");
        switch (view.getId()) {
            case R.id.omt_text /* 2131362955 */:
                updateText();
                omtTrackManager();
                break;
            case R.id.omt_zan /* 2131362956 */:
                Log.d(TAG, "onClick() - updateText()");
                String str = this.mNotes;
                if (str == null || str.length() == 0) {
                    updateTextView();
                }
                updateOMTZanData(!this.isZan, this.mNoteId);
                this.isZan = !this.isZan;
                break;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow()");
        this.isFirst = true;
        this.mHandler.removeCallbacksAndMessages(null);
        unregister();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0) {
            Log.d(TAG, "onVisibilityChanged() default visibility : " + i);
            this.mHandler.removeCallbacksAndMessages(null);
        } else {
            Log.d(TAG, "onVisibilityChanged() VISIBLE");
            updateTextDelayed();
        }
    }

    public void register() {
        try {
            getContext().getContentResolver().registerContentObserver(OMT_PROVIDER_URI, true, this.mRequestOMTInfoDataObserver);
        } catch (Exception e) {
            Log.d(TAG, "register() e : " + e.getMessage());
        }
    }

    public void unregister() {
        try {
            getContext().getContentResolver().unregisterContentObserver(this.mRequestOMTInfoDataObserver);
        } catch (Exception e) {
            Log.d(TAG, "unregister() e : " + e.getMessage());
        }
    }
}
