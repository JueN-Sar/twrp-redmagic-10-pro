package com.zte.plugin.reminder;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.gameassist.utils.WechatHelper;
import com.zte.plugin.reminder.GameReminderListAdapter;
import com.zte.plugin.reminder.NubiaTimePickerView;
import com.zte.plugin.reminder.permission.PermissionGrantedListener;
import com.zte.plugin.reminder.permission.RequestPermissionActivity;
import com.zte.plugin.reminder.permission.RequestPermissionActivityBase;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class GameReminderView extends RelativeLayout {
    private static final int QUERY_REMINDER_TOKEN = 0;
    private Button mCancelButton;
    private ContentObserver mContentObserver;
    private RelativeLayout mContentView;
    private RelativeLayout.LayoutParams mContentViewLayoutParams;
    private TextView mDateShownView;
    private Button mDoneButton;
    private View mReminderEditPanel;
    private ImageView mReminderEmptyIconView;
    private View mReminderEmptyView;
    private ImageView mReminderIconView;
    private GameReminderListAdapter mReminderListAdapter;
    private View mReminderListPanel;
    private ListView mReminderListView;
    private ReminderQueryHanlder mReminderQueryHanlder;
    private EditText mReminderTitleView;
    private RemoveViewListener mRemoveViewListener;
    private CheckBox mRingCheckboxView;
    private NubiaTimePickerView mTimePickerView;

    public final class ReminderQueryHanlder extends AsyncQueryHandler {
        public ReminderQueryHanlder(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override // android.content.AsyncQueryHandler
        protected void onQueryComplete(int i2, Object obj, Cursor cursor) {
            super.onQueryComplete(i2, obj, cursor);
            if (i2 != 0) {
                return;
            }
            if (cursor == null || cursor.getCount() == 0) {
                GameReminderView.this.mReminderEmptyIconView.setVisibility(0);
                GameReminderView.this.mReminderEmptyView.setVisibility(0);
                GameReminderView.this.mReminderListView.setVisibility(8);
                GameReminderView.this.mReminderListAdapter.changeCursor(null);
                return;
            }
            GameReminderView.this.mReminderEmptyIconView.setVisibility(8);
            GameReminderView.this.mReminderEmptyView.setVisibility(8);
            GameReminderView.this.mReminderListView.setVisibility(0);
            GameReminderView.this.mReminderListAdapter.changeCursor(cursor);
        }
    }

    public interface RemoveViewListener {
        void a();
    }

    public GameReminderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContentObserver = new ContentObserver(null) { // from class: com.zte.plugin.reminder.GameReminderView.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                GameReminderView.this.y();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTopPakageName() {
        return SystemMgr.t();
    }

    private void p() {
        this.mCancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.zte.plugin.reminder.GameReminderView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (GameReminderView.this.mRemoveViewListener != null) {
                    GameReminderView.this.mRemoveViewListener.a();
                }
            }
        });
        this.mDoneButton.setOnClickListener(new View.OnClickListener() { // from class: com.zte.plugin.reminder.GameReminderView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long choosedDateTimeLong = GameReminderView.this.mTimePickerView.getChoosedDateTimeLong();
                if (choosedDateTimeLong < System.currentTimeMillis()) {
                    GameReminderUtils.i(GameReminderView.this.getContext(), GameReminderView.this.getContext().getString(R.string.game_reminder_time_illegal));
                    return;
                }
                String obj = TextUtils.isEmpty(GameReminderView.this.mReminderTitleView.getText()) ? "" : GameReminderView.this.mReminderTitleView.getText().toString();
                boolean isChecked = GameReminderView.this.mRingCheckboxView.isChecked();
                GameReminderWindowManager.G(GameReminderView.this.getContext()).P(obj, GameReminderView.this.getTopPakageName(), choosedDateTimeLong, isChecked ? 1 : 0, GameReminderView.this.mRemoveViewListener);
            }
        });
    }

    private void q() {
        ((ImageView) findViewById(R.id.goto_edit)).setOnClickListener(new View.OnClickListener() { // from class: com.zte.plugin.reminder.GameReminderView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameReminderView.this.mReminderEditPanel.setVisibility(0);
                GameReminderView.this.mReminderEditPanel.bringToFront();
                GameReminderView.this.mReminderEditPanel.getLocationOnScreen(new int[2]);
                TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
                translateAnimation.setDuration(250L);
                GameReminderView.this.mReminderEditPanel.setAnimation(translateAnimation);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.zte.plugin.reminder.GameReminderView.5.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        GameReminderView.this.mReminderListPanel.setVisibility(8);
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                        GameReminderView.this.mReminderEditPanel.setVisibility(0);
                    }
                });
            }
        });
    }

    private void r() {
        ((ImageView) findViewById(R.id.goto_list)).setOnClickListener(new View.OnClickListener() { // from class: com.zte.plugin.reminder.GameReminderView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameReminderView.this.mReminderListPanel.setVisibility(0);
                GameReminderView.this.mReminderListPanel.bringToFront();
                GameReminderView.this.mReminderListPanel.getLocationOnScreen(new int[2]);
                TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
                translateAnimation.setDuration(250L);
                GameReminderView.this.mReminderListPanel.setAnimation(translateAnimation);
                translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.zte.plugin.reminder.GameReminderView.6.1
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        GameReminderView.this.mReminderEditPanel.setVisibility(8);
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                        GameReminderView.this.mReminderListPanel.setVisibility(0);
                    }
                });
                translateAnimation.start();
            }
        });
    }

    private void s() {
        GameReminderListAdapter gameReminderListAdapter = new GameReminderListAdapter(getContext(), null);
        this.mReminderListAdapter = gameReminderListAdapter;
        gameReminderListAdapter.a(new GameReminderListAdapter.OnDataChangedListener() { // from class: com.zte.plugin.reminder.GameReminderView.4
            @Override // com.zte.plugin.reminder.GameReminderListAdapter.OnDataChangedListener
            public void a() {
                GameReminderView.this.y();
            }
        });
        this.mReminderListView.setAdapter((ListAdapter) this.mReminderListAdapter);
        this.mReminderQueryHanlder = new ReminderQueryHanlder(getContext().getContentResolver());
        y();
        getContext().getContentResolver().registerContentObserver(GameReminderColumn.f18021a, false, this.mContentObserver);
    }

    private void t() {
        this.mTimePickerView.setOnTimeChangedListener(new NubiaTimePickerView.OnTimeChangeListener() { // from class: com.zte.plugin.reminder.GameReminderView.9
            @Override // com.zte.plugin.reminder.NubiaTimePickerView.OnTimeChangeListener
            public void a() {
                GameReminderView.this.mDateShownView.setText(GameReminderView.this.mTimePickerView.getChoosedDateTimeString());
            }
        });
        this.mTimePickerView.p();
    }

    private void w() {
        this.mReminderEditPanel.setVisibility(0);
        this.mReminderListPanel.setVisibility(8);
    }

    private void x() {
        GaLog.a("WechatHelper", "GameReminderView SystemMgr.isWechatGameApp() : " + SystemMgr.L());
        GaLog.a("WechatHelper", "GameReminderView SystemMgr.sResumedTaskHashcode : " + SystemMgr.A);
        if (SystemMgr.L()) {
            this.mReminderIconView.setImageDrawable(WechatHelper.a().b(Integer.toString(SystemMgr.A)));
            return;
        }
        String t = SystemMgr.t();
        if (TextUtils.isEmpty(t)) {
            return;
        }
        try {
            this.mReminderIconView.setImageDrawable(getContext().getPackageManager().getApplicationIcon(t));
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        ReminderQueryHanlder reminderQueryHanlder = this.mReminderQueryHanlder;
        if (reminderQueryHanlder != null) {
            reminderQueryHanlder.cancelOperation(0);
            this.mReminderQueryHanlder.startQuery(0, null, GameReminderColumn.f18021a, null, "time>?", new String[]{Long.toString(System.currentTimeMillis())}, "time asc");
        }
    }

    public void A(boolean z) {
        if (z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mContentView.getLayoutParams();
            this.mContentViewLayoutParams = layoutParams;
            layoutParams.removeRule(12);
            this.mContentViewLayoutParams.addRule(11);
            this.mContentViewLayoutParams.removeRule(14);
            this.mContentViewLayoutParams.addRule(15);
            RelativeLayout.LayoutParams layoutParams2 = this.mContentViewLayoutParams;
            layoutParams2.bottomMargin = 0;
            layoutParams2.rightMargin = 48;
            return;
        }
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mContentView.getLayoutParams();
        this.mContentViewLayoutParams = layoutParams3;
        layoutParams3.removeRule(11);
        this.mContentViewLayoutParams.addRule(12);
        this.mContentViewLayoutParams.removeRule(15);
        this.mContentViewLayoutParams.addRule(14);
        RelativeLayout.LayoutParams layoutParams4 = this.mContentViewLayoutParams;
        layoutParams4.rightMargin = 0;
        layoutParams4.bottomMargin = 48;
    }

    public String getDateShown() {
        TextView textView = this.mDateShownView;
        if (textView != null) {
            return textView.getText().toString();
        }
        return null;
    }

    public Calendar getDateTime() {
        NubiaTimePickerView nubiaTimePickerView = this.mTimePickerView;
        if (nubiaTimePickerView != null) {
            return nubiaTimePickerView.getCalendar();
        }
        return null;
    }

    public int getMonthDay() {
        NubiaTimePickerView nubiaTimePickerView = this.mTimePickerView;
        if (nubiaTimePickerView != null) {
            return nubiaTimePickerView.getMonthDay();
        }
        return 0;
    }

    public String getTitle() {
        EditText editText = this.mReminderTitleView;
        if (editText != null) {
            return editText.getText().toString();
        }
        return null;
    }

    public void n() {
        setRemoveViewListener(null);
        getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
    }

    public void o() {
        t();
        p();
        r();
        q();
        s();
        x();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        RequestPermissionActivityBase.h(new PermissionGrantedListener() { // from class: com.zte.plugin.reminder.GameReminderView.2
            @Override // com.zte.plugin.reminder.permission.PermissionGrantedListener
            public void a(boolean z) {
                if (z) {
                    return;
                }
                GameReminderUtils.i(GameReminderView.this.getContext(), GameReminderView.this.getContext().getString(R.string.game_reminder_power_off_alarm_permisson_toast));
            }
        });
        this.mContentView = (RelativeLayout) findViewById(R.id.content);
        this.mReminderEditPanel = findViewById(R.id.reminder_edit_panel);
        this.mReminderListPanel = findViewById(R.id.reminder_list_panel);
        this.mReminderIconView = (ImageView) findViewById(R.id.reminder_icon);
        this.mReminderTitleView = (EditText) findViewById(R.id.reminder_title);
        this.mDateShownView = (TextView) findViewById(R.id.date_shown);
        CheckBox checkBox = (CheckBox) findViewById(R.id.ring_checkbox);
        this.mRingCheckboxView = checkBox;
        if (GameReminderUtils.f18032d) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.plugin.reminder.GameReminderView.3
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (SharedPreferencesUtil.k(GameReminderView.this.getContext()).i() || !z || !RequestPermissionActivity.j(GameReminderView.this.getContext()) || GameReminderView.this.mRemoveViewListener == null) {
                        return;
                    }
                    GameReminderView.this.mRemoveViewListener.a();
                }
            });
        }
        this.mTimePickerView = (NubiaTimePickerView) findViewById(R.id.time_picker);
        this.mCancelButton = (Button) findViewById(R.id.cancel_button);
        this.mDoneButton = (Button) findViewById(R.id.done_button);
        this.mReminderEmptyIconView = (ImageView) findViewById(R.id.reminder_empty_icon);
        this.mReminderEmptyView = findViewById(R.id.reminder_empty);
        this.mReminderListView = (ListView) findViewById(R.id.reminder_list);
    }

    public void setDateShown(String str) {
        TextView textView = this.mDateShownView;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setDateTime(Calendar calendar) {
        NubiaTimePickerView nubiaTimePickerView = this.mTimePickerView;
        if (nubiaTimePickerView != null) {
            nubiaTimePickerView.q(calendar);
        }
    }

    public void setMonthDay(int i2) {
        NubiaTimePickerView nubiaTimePickerView = this.mTimePickerView;
        if (nubiaTimePickerView != null) {
            nubiaTimePickerView.setMonthDay(i2);
        }
    }

    public void setRemoveViewListener(RemoveViewListener removeViewListener) {
        this.mRemoveViewListener = removeViewListener;
    }

    public void setRingCheck(boolean z) {
        CheckBox checkBox = this.mRingCheckboxView;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    public void setTitle(String str) {
        EditText editText = this.mReminderTitleView;
        if (editText != null) {
            editText.setText(str);
        }
    }

    public boolean u() {
        CheckBox checkBox = this.mRingCheckboxView;
        if (checkBox != null) {
            return checkBox.isChecked();
        }
        return false;
    }

    public void v() {
        for (int i2 = 0; i2 < this.mReminderListView.getChildCount(); i2++) {
            View childAt = this.mReminderListView.getChildAt(i2);
            if (childAt instanceof GameReminderItem) {
                ((GameReminderItem) childAt).l();
            }
        }
    }

    public void z() {
        this.mTimePickerView.p();
        this.mReminderTitleView.setText("");
        this.mRingCheckboxView.setChecked(false);
        x();
        w();
        y();
    }
}
