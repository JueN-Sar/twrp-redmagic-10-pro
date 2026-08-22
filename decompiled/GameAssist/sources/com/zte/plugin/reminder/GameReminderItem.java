package com.zte.plugin.reminder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.WechatHelper;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.plugin.reminder.widget.GameReminderWidget;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class GameReminderItem extends LinearLayout implements RotationMgr.Callback {
    private String[] WEEKDAYS_SHORT;
    private SimpleDateFormat dateFormat;
    private Map<String, Drawable> iconCache;
    private TextView mDateView;
    private ImageView mDeleteView;
    private Dialog mDialog;
    private ImageView mIconView;
    private long mId;
    private TextView mTimeView;
    private TextView mTitleView;
    private TextView mWeekView;
    private SimpleDateFormat timeFormat;

    public GameReminderItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.iconCache = new HashMap();
        this.timeFormat = new SimpleDateFormat("HH:mm");
        this.dateFormat = new SimpleDateFormat("MM/dd");
        h();
    }

    private String e(long j2) {
        return this.dateFormat.format(new Date(j2));
    }

    private String f(long j2) {
        return this.timeFormat.format(new Date(j2));
    }

    private String g(long j2) {
        Calendar.getInstance().setTimeInMillis(j2);
        return this.WEEKDAYS_SHORT[r0.get(7) - 1];
    }

    private void h() {
        this.WEEKDAYS_SHORT = getContext().getResources().getStringArray(R.array.nubia_weeks_short);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(View view) {
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(DialogInterface dialogInterface, int i2) {
        getContext().getContentResolver().delete(GameReminderColumn.f18021a, "_id=?", new String[]{Long.toString(this.mId)});
        dialogInterface.dismiss();
        Intent intent = new Intent(getContext(), (Class<?>) AlarmService.class);
        intent.setAction("cn.nubia.gamereminder.UPDATE");
        getContext().startService(intent);
        GameReminderWidget.a(getContext());
    }

    private void m() {
        this.mDeleteView.setOnClickListener(new View.OnClickListener() { // from class: com.zte.plugin.reminder.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameReminderItem.this.i(view);
            }
        });
    }

    private void setDateTimeView(long j2) {
        TextView textView = this.mTimeView;
        if (textView != null) {
            textView.setText(f(j2));
        }
        TextView textView2 = this.mDateView;
        if (textView2 != null) {
            textView2.setText(e(j2));
        }
        TextView textView3 = this.mWeekView;
        if (textView3 != null) {
            textView3.setText(g(j2));
        }
    }

    private void setTitleView(String str) {
        if (this.mTitleView != null) {
            if (TextUtils.isEmpty(str)) {
                this.mTitleView.setText(R.string.game_reminder_input_hint);
            } else {
                this.mTitleView.setText(str);
            }
        }
    }

    public void d(long j2, String str, String str2, long j3) {
        this.mId = j2;
        setTitleView(str2);
        setDateTimeView(j3);
        setIconView(str);
    }

    public void l() {
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    protected void n() {
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            AlertDialog a2 = new AlertDialog.Builder(getContext().getApplicationContext(), com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).m(getContext().getString(R.string.game_reminder_confirm_delete)).c(true).i(com.zte.gameassist.common.R.string.single_ok, new DialogInterface.OnClickListener() { // from class: com.zte.plugin.reminder.c
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    GameReminderItem.this.j(dialogInterface, i2);
                }
            }).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: com.zte.plugin.reminder.d
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            }).a();
            this.mDialog = a2;
            a2.getWindow().setType(2008);
            this.mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.mDialog.getWindow().getDecorView().setSystemUiVisibility(6);
            this.mDialog.show();
            GameAssistDialog.f(this.mDialog.getWindow());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RotationMgr.e(getContext()).c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RotationMgr.e(getContext()).p(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIconView = (ImageView) findViewById(R.id.event_icon);
        this.mTitleView = (TextView) findViewById(R.id.title);
        this.mDeleteView = (ImageView) findViewById(R.id.delete);
        this.mTimeView = (TextView) findViewById(R.id.time);
        this.mDateView = (TextView) findViewById(R.id.date);
        this.mWeekView = (TextView) findViewById(R.id.week);
        m();
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        Dialog dialog = this.mDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.mDialog.getWindow());
    }

    protected void setIconView(String str) {
        if (this.mIconView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            int i2 = R.drawable.game_reminder_zte_notification_icon;
            if (ZteFeature.isRedMagicProduct()) {
                i2 = R.drawable.game_reminder_notification_icon;
            }
            this.mIconView.setImageResource(i2);
            return;
        }
        Drawable drawable = this.iconCache.get(str);
        if (drawable == null) {
            try {
                drawable = WechatHelper.i(str) ? WechatHelper.a().c(str, true) : getContext().getPackageManager().getApplicationIcon(str);
                this.iconCache.put(str, drawable);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (drawable != null) {
            this.mIconView.setImageDrawable(drawable);
        }
    }
}
