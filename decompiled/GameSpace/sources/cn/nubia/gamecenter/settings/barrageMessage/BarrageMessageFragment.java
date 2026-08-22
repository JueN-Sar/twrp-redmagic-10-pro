package cn.nubia.gamecenter.settings.barrageMessage;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.NoFocusCheckBox;
import cn.nubia.gamecenter.settings.utils.Utils;

/* loaded from: classes.dex */
public class BarrageMessageFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final int COLOR_MENU_NORMAL = R.color.gcs_gamecenter_menu_text;
    private static final int COLOR_MENU_SELECTED = R.color.gcs_gamecenter_menu_text_checked;
    public static final String GSC_BARRAGE_MESSAGE = "gsc_meditation_level";
    public static final int GSC_BARRAGE_MESSAGE_CLOSE = 0;
    public static final int GSC_BARRAGE_MESSAGE_EXIT = 0;
    public static final int GSC_BARRAGE_MESSAGE_MAX_LENGTH_FEW = 15;
    public static final int GSC_BARRAGE_MESSAGE_MAX_LENGTH_MANY = 54;
    public static final int GSC_BARRAGE_MESSAGE_ON = 1;
    public static final int GSC_BARRAGE_MESSAGE_OPEN = 1;
    public static final String GSC_BARRAGE_MESSAGE_PREVIEW = "gsc_barrage_message_preview";
    public static final int GSC_BARRAGE_MESSAGE_TYPE0 = 0;
    public static final int GSC_BARRAGE_MESSAGE_TYPE1 = 1;
    public static final int GSC_BARRAGE_MESSAGE_TYPE2 = 2;
    public static final int GSC_BARRAGE_MESSAGE_TYPE3 = 3;
    public static final int GSC_BARRAGE_MESSAGE_TYPE4 = 4;
    public static final int GSC_BARRAGE_MESSAGE_TYPEFACE0 = 0;
    public static final int GSC_BARRAGE_MESSAGE_TYPEFACE1 = 1;
    public static final int GSC_BARRAGE_MESSAGE_TYPEFACE2 = 2;
    public static final int GSC_BARRAGE_MESSAGE_VELOCITY0 = 0;
    public static final int GSC_BARRAGE_MESSAGE_VELOCITY1 = 1;
    public static final int GSC_BARRAGE_MESSAGE_VELOCITY2 = 2;
    public static final String KEY_GAME_SUPPORT_LIST = "game_support_list";
    private static final String TAG = "BarrageMessageFragment";
    private static final int TRANSPARENCY_MAX = 10;
    private static final int TRANSPARENCY_MIN = 1;
    public static final int TRANSPARENCY_PROGRESS_DEFAULT = 5;
    private ColorStateList colorStateListNormal;
    private ColorStateList colorStateListSelected;
    private TextView locationDown;
    private TextView locationUp;
    private final ContentObserver mBarrageMessageTotalChangedObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.i(BarrageMessageFragment.TAG, "onChange");
            BarrageMessageFragment.this.mMessageBean = new BarrageMessageBean(BarrageMessageFragment.this.getContext());
            BarrageMessageFragment.this.initView();
        }
    };
    private LinearLayout mBubbleCheckArea0;
    private LinearLayout mBubbleCheckArea1;
    private LinearLayout mBubbleCheckArea2;
    private LinearLayout mBubbleCheckArea3;
    private LinearLayout mBubbleCheckArea4;
    private NoFocusCheckBox mBubbleCheckBox0;
    private NoFocusCheckBox mBubbleCheckBox1;
    private NoFocusCheckBox mBubbleCheckBox2;
    private NoFocusCheckBox mBubbleCheckBox3;
    private NoFocusCheckBox mBubbleCheckBox4;
    private BarrageMessageBean mMessageBean;
    private ImageView mQuickreplyCheckbox;
    private LinearLayout mSettingsLayout;
    private ImageView mShieldNotificationCheckbox;
    private LinearLayout mShieldNotificationLayout;
    private ImageView mSourceCheckbox;
    private View mSourceItem;
    private ImageView mTotalSwitch;
    private SeekBar mTransparencySeekBar;
    private TextView mTypefaceLarge;
    private TextView mTypefaceMedium;
    private TextView mTypefaceSmall;
    private TextView mVelocityFast;
    private TextView mVelocityMedium;
    private TextView mVelocitySlow;
    private TextView maxLengthFew;
    private TextView maxLengthMany;

    private void enableBarrageMessageSeekBar() {
        if (this.mMessageBean.isTotalSwitch()) {
            this.mTransparencySeekBar.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageFragment.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    LogUtil.d(BarrageMessageFragment.TAG, "onTouch");
                    return false;
                }
            });
        } else {
            this.mTransparencySeekBar.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gamecenter.settings.barrageMessage.BarrageMessageFragment.3
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    LogUtil.d(BarrageMessageFragment.TAG, "onTouch");
                    return true;
                }
            });
        }
    }

    public static String getSummaryFromBarrageMessage(Context context) {
        int i = Settings.Global.getInt(context.getContentResolver(), GSC_BARRAGE_MESSAGE, 0);
        return context.getResources().getString(i != 0 ? i != 1 ? R.string.gcs_game_video_close : R.string.gcs_game_video_open : R.string.gcs_game_video_close);
    }

    private Drawable getTransparencyDrawable(int i) {
        return i > 0 ? getResources().getDrawable(R.drawable.gyro_reverse) : getResources().getDrawable(R.drawable.gyro_normal);
    }

    private void hideNavigationBar() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initView() {
        this.colorStateListSelected = getContext().getResources().getColorStateList(COLOR_MENU_SELECTED);
        this.colorStateListNormal = getContext().getResources().getColorStateList(COLOR_MENU_NORMAL);
        setTotalSwitch();
        setBubbleView();
        setVelocityView();
        setLocationView();
        setFontSizeView();
        setLengthView();
        setProgressView();
        enableBarrageMessageSeekBar();
        setShieldNotificationView();
        setReplyView();
    }

    private void onClickBubble(View view) {
        int id = view.getId();
        if (id == R.id.check_barrage_message_bubbletype_type_0) {
            this.mMessageBean.setBubble(0);
        } else if (id == R.id.check_barrage_message_bubbletype_type_1) {
            this.mMessageBean.setBubble(1);
        } else if (id == R.id.check_barrage_message_bubbletype_type_2) {
            this.mMessageBean.setBubble(2);
        } else if (id == R.id.check_barrage_message_bubbletype_type_3) {
            this.mMessageBean.setBubble(3);
        } else if (id == R.id.check_barrage_message_bubbletype_type_4) {
            this.mMessageBean.setBubble(4);
        }
        setBubbleView();
    }

    private void onClickFontSize(View view) {
        int id = view.getId();
        if (id == R.id.gsc_barrage_message_typeface_small) {
            this.mMessageBean.setFontSize(0);
        } else if (id == R.id.gsc_barrage_message_typeface_medium) {
            this.mMessageBean.setFontSize(1);
        } else if (id == R.id.gsc_barrage_message_typeface_large) {
            this.mMessageBean.setFontSize(2);
        }
        setFontSizeView();
    }

    private void onClickLength(View view) {
        int id = view.getId();
        if (id == R.id.gsc_barrage_message_max_length_few) {
            this.mMessageBean.setLength(15);
        } else if (id == R.id.gsc_barrage_message_max_length_many) {
            this.mMessageBean.setLength(54);
        }
        setLengthView();
    }

    private void onClickLocation(View view) {
        int id = view.getId();
        if (id == R.id.gsc_barrage_message_location_up) {
            this.mMessageBean.setLocation(0);
        } else if (id == R.id.gsc_barrage_message_location_down) {
            this.mMessageBean.setLocation(1);
        }
        setLocationView();
    }

    private void onClickQuickReply(View view) {
        this.mMessageBean.setQuickReply(!this.mMessageBean.isQuickReply());
        setReplyView();
    }

    private void onClickShieldNotification(View view) {
        this.mMessageBean.setShieldNotification(!this.mMessageBean.isShieldNotification());
        setShieldNotificationView();
    }

    private void onClickSource(View view) {
        Settings.Global.putInt(getContext().getContentResolver(), "gsc_barrage_message_preview", 1);
        startBarrageMessageSourceActivity(getActivity());
    }

    private void onClickVelocity(View view) {
        int id = view.getId();
        if (id == R.id.gsc_barrage_message_velocity_slow) {
            this.mMessageBean.setVelocity(0);
        } else if (id == R.id.gsc_barrage_message_velocity_in) {
            this.mMessageBean.setVelocity(1);
        } else if (id == R.id.gsc_barrage_message_velocity_fast) {
            this.mMessageBean.setVelocity(2);
        }
        setVelocityView();
    }

    private void setBubbleView() {
        int bubble = this.mMessageBean.getBubble();
        this.mBubbleCheckBox0.setCustomChecked(bubble == 0);
        this.mBubbleCheckBox1.setCustomChecked(bubble == 1);
        this.mBubbleCheckBox2.setCustomChecked(bubble == 2);
        this.mBubbleCheckBox3.setCustomChecked(bubble == 3);
        this.mBubbleCheckBox4.setCustomChecked(bubble == 4);
    }

    private void setFontSizeView() {
        int fontSize = this.mMessageBean.getFontSize();
        LogUtil.i(TAG, "setFontSize:" + fontSize);
        if (fontSize == 0) {
            this.mTypefaceSmall.setTextColor(this.colorStateListSelected);
            this.mTypefaceMedium.setTextColor(this.colorStateListNormal);
            this.mTypefaceLarge.setTextColor(this.colorStateListNormal);
        } else if (fontSize == 1) {
            this.mTypefaceSmall.setTextColor(this.colorStateListNormal);
            this.mTypefaceMedium.setTextColor(this.colorStateListSelected);
            this.mTypefaceLarge.setTextColor(this.colorStateListNormal);
        } else if (fontSize == 2) {
            this.mTypefaceSmall.setTextColor(this.colorStateListNormal);
            this.mTypefaceMedium.setTextColor(this.colorStateListNormal);
            this.mTypefaceLarge.setTextColor(this.colorStateListSelected);
        }
    }

    private void setLengthView() {
        int length = this.mMessageBean.getLength();
        LogUtil.i(TAG, "setLength:" + length);
        boolean z = length == 15;
        this.maxLengthFew.setTextColor(z ? this.colorStateListSelected : this.colorStateListNormal);
        this.maxLengthMany.setTextColor(z ? this.colorStateListNormal : this.colorStateListSelected);
    }

    private void setLocationView() {
        int location = this.mMessageBean.getLocation();
        LogUtil.i(TAG, "setLocation:" + location);
        boolean z = location == 0;
        this.locationUp.setTextColor(z ? this.colorStateListSelected : this.colorStateListNormal);
        this.locationDown.setTextColor(z ? this.colorStateListNormal : this.colorStateListSelected);
    }

    private void setProgressView() {
        this.mTransparencySeekBar.setProgress(this.mMessageBean.getTransparency());
        if (FeatureUtil.isMtk() || FeatureUtil.isSprd()) {
            this.mBubbleCheckArea1.setVisibility(8);
        }
    }

    private void setReplyView() {
        setChecked(this.mQuickreplyCheckbox, this.mMessageBean.isQuickReply());
    }

    private void setShieldNotificationView() {
        setChecked(this.mShieldNotificationCheckbox, this.mMessageBean.isShieldNotification());
    }

    private void setTotalSwitch() {
        boolean isTotalSwitch = this.mMessageBean.isTotalSwitch();
        setChecked(this.mTotalSwitch, isTotalSwitch);
        this.mBubbleCheckArea0.setClickable(isTotalSwitch);
        this.mBubbleCheckArea1.setClickable(isTotalSwitch);
        this.mBubbleCheckArea2.setClickable(isTotalSwitch);
        this.mBubbleCheckArea3.setClickable(isTotalSwitch);
        this.mBubbleCheckArea4.setClickable(isTotalSwitch);
        this.mVelocitySlow.setClickable(isTotalSwitch);
        this.mVelocityMedium.setClickable(isTotalSwitch);
        this.mVelocityFast.setClickable(isTotalSwitch);
        this.locationUp.setClickable(isTotalSwitch);
        this.locationDown.setClickable(isTotalSwitch);
        this.mTypefaceSmall.setClickable(isTotalSwitch);
        this.mTypefaceMedium.setClickable(isTotalSwitch);
        this.mTypefaceLarge.setClickable(isTotalSwitch);
        this.maxLengthFew.setClickable(isTotalSwitch);
        this.maxLengthMany.setClickable(isTotalSwitch);
        this.mTransparencySeekBar.setClickable(isTotalSwitch);
        this.mShieldNotificationCheckbox.setClickable(isTotalSwitch);
        this.mSourceItem.setClickable(isTotalSwitch);
        this.mSourceCheckbox.setClickable(isTotalSwitch);
        this.mQuickreplyCheckbox.setClickable(isTotalSwitch);
        this.mSettingsLayout.setClickable(isTotalSwitch);
        this.mSettingsLayout.setAlpha(isTotalSwitch ? 1.0f : 0.5f);
    }

    private void setVelocityView() {
        int velocity = this.mMessageBean.getVelocity();
        LogUtil.i(TAG, "setVelocity:" + velocity);
        if (velocity == 0) {
            this.mVelocitySlow.setTextColor(this.colorStateListSelected);
            this.mVelocityMedium.setTextColor(this.colorStateListNormal);
            this.mVelocityFast.setTextColor(this.colorStateListNormal);
        } else if (velocity == 1) {
            this.mVelocitySlow.setTextColor(this.colorStateListNormal);
            this.mVelocityMedium.setTextColor(this.colorStateListSelected);
            this.mVelocityFast.setTextColor(this.colorStateListNormal);
        } else if (velocity == 2) {
            this.mVelocitySlow.setTextColor(this.colorStateListNormal);
            this.mVelocityMedium.setTextColor(this.colorStateListNormal);
            this.mVelocityFast.setTextColor(this.colorStateListSelected);
        }
    }

    public static void startBarrageMessageSourceActivity(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, BarrageMessageSourceActivity.class.getName());
        intent.putExtra("game_support_list", 1);
        context.startActivity(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.gamemode_barrage_message_checkbox) {
            onClickTotalSwitch(view);
            return;
        }
        if (id == R.id.check_barrage_message_bubbletype_type_0 || id == R.id.check_barrage_message_bubbletype_type_1 || id == R.id.check_barrage_message_bubbletype_type_2 || id == R.id.check_barrage_message_bubbletype_type_3 || id == R.id.check_barrage_message_bubbletype_type_4) {
            onClickBubble(view);
            return;
        }
        if (id == R.id.gsc_barrage_message_velocity_slow || id == R.id.gsc_barrage_message_velocity_in || id == R.id.gsc_barrage_message_velocity_fast) {
            onClickVelocity(view);
            return;
        }
        if (id == R.id.gsc_barrage_message_location_up || id == R.id.gsc_barrage_message_location_down) {
            onClickLocation(view);
            return;
        }
        if (id == R.id.gsc_barrage_message_typeface_small || id == R.id.gsc_barrage_message_typeface_medium || id == R.id.gsc_barrage_message_typeface_large) {
            onClickFontSize(view);
            return;
        }
        if (id == R.id.gsc_barrage_message_max_length_few || id == R.id.gsc_barrage_message_max_length_many) {
            onClickLength(view);
            return;
        }
        if (id == R.id.gamemode_barrage_message_shield_notification_checkbox) {
            onClickShieldNotification(view);
            return;
        }
        if (id == R.id.gamemode_barrage_message_source_checkbox) {
            onClickSource(view);
        } else if (id == R.id.gamemode_barrage_message_source_layout) {
            onClickSource(view);
        } else if (id == R.id.gamemode_barrage_message_quickreply_checkbox) {
            onClickQuickReply(view);
        }
    }

    public void onClickTotalSwitch(View view) {
        this.mMessageBean.setTotalSwitch(!this.mMessageBean.isTotalSwitch());
        setTotalSwitch();
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideNavigationBar();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.gcs_barrage_message_view_menu, viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        getContext().getContentResolver().unregisterContentObserver(this.mBarrageMessageTotalChangedObserver);
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.mMessageBean.setTransparency(i);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor(GSC_BARRAGE_MESSAGE), true, this.mBarrageMessageTotalChangedObserver);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ImageView imageView = (ImageView) view.findViewById(R.id.gamemode_barrage_message_checkbox);
        this.mTotalSwitch = imageView;
        imageView.setOnClickListener(this);
        this.mSettingsLayout = (LinearLayout) view.findViewById(R.id.nubia_gamemode_barrage_message_settings_layout);
        this.mBubbleCheckBox0 = (NoFocusCheckBox) view.findViewById(R.id.check_barrage_message_bubbletype_box_0);
        this.mBubbleCheckBox1 = (NoFocusCheckBox) view.findViewById(R.id.check_barrage_message_bubbletype_box_1);
        this.mBubbleCheckBox2 = (NoFocusCheckBox) view.findViewById(R.id.check_barrage_message_bubbletype_box_2);
        this.mBubbleCheckBox3 = (NoFocusCheckBox) view.findViewById(R.id.check_barrage_message_bubbletype_box_3);
        this.mBubbleCheckBox4 = (NoFocusCheckBox) view.findViewById(R.id.check_barrage_message_bubbletype_box_4);
        this.mBubbleCheckArea0 = (LinearLayout) view.findViewById(R.id.check_barrage_message_bubbletype_type_0);
        this.mBubbleCheckArea1 = (LinearLayout) view.findViewById(R.id.check_barrage_message_bubbletype_type_1);
        this.mBubbleCheckArea2 = (LinearLayout) view.findViewById(R.id.check_barrage_message_bubbletype_type_2);
        this.mBubbleCheckArea3 = (LinearLayout) view.findViewById(R.id.check_barrage_message_bubbletype_type_3);
        this.mBubbleCheckArea4 = (LinearLayout) view.findViewById(R.id.check_barrage_message_bubbletype_type_4);
        this.mBubbleCheckArea0.setOnClickListener(this);
        this.mBubbleCheckArea1.setOnClickListener(this);
        this.mBubbleCheckArea2.setOnClickListener(this);
        this.mBubbleCheckArea3.setOnClickListener(this);
        this.mBubbleCheckArea4.setOnClickListener(this);
        if (Utils.isInternal(getContext())) {
            this.mBubbleCheckArea1.setVisibility(8);
        }
        this.mVelocitySlow = (TextView) view.findViewById(R.id.gsc_barrage_message_velocity_slow);
        this.mVelocityMedium = (TextView) view.findViewById(R.id.gsc_barrage_message_velocity_in);
        this.mVelocityFast = (TextView) view.findViewById(R.id.gsc_barrage_message_velocity_fast);
        this.mVelocitySlow.setOnClickListener(this);
        this.mVelocityMedium.setOnClickListener(this);
        this.mVelocityFast.setOnClickListener(this);
        this.locationUp = (TextView) view.findViewById(R.id.gsc_barrage_message_location_up);
        this.locationDown = (TextView) view.findViewById(R.id.gsc_barrage_message_location_down);
        this.locationUp.setOnClickListener(this);
        this.locationDown.setOnClickListener(this);
        this.mTypefaceSmall = (TextView) view.findViewById(R.id.gsc_barrage_message_typeface_small);
        this.mTypefaceMedium = (TextView) view.findViewById(R.id.gsc_barrage_message_typeface_medium);
        this.mTypefaceLarge = (TextView) view.findViewById(R.id.gsc_barrage_message_typeface_large);
        this.mTypefaceSmall.setOnClickListener(this);
        this.mTypefaceMedium.setOnClickListener(this);
        this.mTypefaceLarge.setOnClickListener(this);
        this.maxLengthFew = (TextView) view.findViewById(R.id.gsc_barrage_message_max_length_few);
        this.maxLengthMany = (TextView) view.findViewById(R.id.gsc_barrage_message_max_length_many);
        this.maxLengthFew.setOnClickListener(this);
        this.maxLengthMany.setOnClickListener(this);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.gamemode_barrage_message_transparency_seekbar);
        this.mTransparencySeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        this.mTransparencySeekBar.setProgressDrawable(getTransparencyDrawable(5));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.gamemode_barrage_message_shield_notification_layout);
        this.mShieldNotificationLayout = linearLayout;
        linearLayout.setVisibility(8);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.gamemode_barrage_message_shield_notification_checkbox);
        this.mShieldNotificationCheckbox = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.gamemode_barrage_message_source_checkbox);
        this.mSourceCheckbox = imageView3;
        imageView3.setOnClickListener(this);
        View findViewById = view.findViewById(R.id.gamemode_barrage_message_source_layout);
        this.mSourceItem = findViewById;
        findViewById.setOnClickListener(this);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.gamemode_barrage_message_quickreply_checkbox);
        this.mQuickreplyCheckbox = imageView4;
        imageView4.setOnClickListener(this);
        View findViewById2 = view.findViewById(R.id.gamemode_barrage_message_quickreply_layout);
        if (!FeatureUtil.windowReplyEnable()) {
            findViewById2.setVisibility(8);
        }
        this.mMessageBean = new BarrageMessageBean(getContext());
        initView();
        if (CommonUtil.isAndroidU() || !CommonUtil.isP720P01()) {
            return;
        }
        view.findViewById(R.id.gamemode_barrage_message_location_layout).setVisibility(8);
        view.findViewById(R.id.gamemode_barrage_message_max_length_layout).setVisibility(8);
    }

    public void setChecked(ImageView imageView, boolean z) {
        imageView.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }
}
