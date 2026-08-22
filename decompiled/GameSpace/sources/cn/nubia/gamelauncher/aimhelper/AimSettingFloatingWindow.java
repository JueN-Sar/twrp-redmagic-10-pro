package cn.nubia.gamelauncher.aimhelper;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import cn.nubia.common.app.AlertDialogCenter;
import cn.nubia.gamelauncher.R;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class AimSettingFloatingWindow {
    private static final String TAG = "AimSettingFloatingWindow";
    private ImageButton mBtnClose;
    private View mContentView;
    private Context mContext;
    private GameHelperController mGameHelperController;
    private SeekBar mOpacitySeekbar;
    private ImageView mQuickHideCheckbox;
    private ImageButton mQuickHideTipButton;
    private AlertDialogCenter mQuickHideTipDialog;
    private SeekBar mSeekBar;
    private WindowManager mWindowManager;
    private static int[] styleResArr = {R.id.style1, R.id.style2, R.id.style3, R.id.style4, R.id.style5};
    private static int[] colorResArr = {R.id.white, R.id.red, R.id.yellow, R.id.green, R.id.blue};
    public static int[] colors = {-1, -3339749, -1711815, -16200683, -16722987};
    private boolean isShowing = false;
    private View.OnClickListener mStyleClickListener = new View.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (int i = 0; i < AimSettingFloatingWindow.styleResArr.length; i++) {
                ((SelectableImageView) AimSettingFloatingWindow.this.mContentView.findViewById(AimSettingFloatingWindow.styleResArr[i])).setSelect(AimSettingFloatingWindow.styleResArr[i] == view.getId());
                if (AimSettingFloatingWindow.styleResArr[i] == view.getId()) {
                    AimConfigs.getInstance(AimSettingFloatingWindow.this.mContext).setStyle(AimSettingFloatingWindow.this.mGameHelperController.getTopApplication(), i + 1);
                }
            }
            AimSettingFloatingWindow.this.mGameHelperController.refreshAimCenter();
        }
    };
    private View.OnClickListener mColorClickListener = new View.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (int i = 0; i < AimSettingFloatingWindow.colorResArr.length; i++) {
                ((SelectableImageView) AimSettingFloatingWindow.this.mContentView.findViewById(AimSettingFloatingWindow.colorResArr[i])).setSelect(AimSettingFloatingWindow.colorResArr[i] == view.getId());
                if (AimSettingFloatingWindow.colorResArr[i] == view.getId()) {
                    Log.d(AimSettingFloatingWindow.TAG, "color:" + AimSettingFloatingWindow.colors[i]);
                    AimConfigs.getInstance(AimSettingFloatingWindow.this.mContext).setColor(AimSettingFloatingWindow.this.mGameHelperController.getTopApplication(), AimSettingFloatingWindow.colors[i]);
                }
            }
            AimSettingFloatingWindow.this.mGameHelperController.refreshAimCenter();
        }
    };
    private View.OnClickListener mQuickHideCheckedChangeListener = new View.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AimSettingFloatingWindow.this.onQuickHideChange();
        }
    };

    public AimSettingFloatingWindow(Context context, GameHelperController gameHelperController) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mGameHelperController = gameHelperController;
    }

    private void initViews() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.aim_setting_layout, (ViewGroup) null);
        this.mContentView = inflate;
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.btn_close);
        this.mBtnClose = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AimSettingFloatingWindow.this.hide();
            }
        });
        for (int i : styleResArr) {
            this.mContentView.findViewById(i).setOnClickListener(this.mStyleClickListener);
        }
        for (int i2 : colorResArr) {
            this.mContentView.findViewById(i2).setOnClickListener(this.mColorClickListener);
        }
        SeekBar seekBar = (SeekBar) this.mContentView.findViewById(R.id.seekbar);
        this.mSeekBar = seekBar;
        seekBar.setMax(100);
        this.mSeekBar.setMin(40);
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i3, boolean z) {
                if (z) {
                    AimSettingFloatingWindow.this.onSizeChange(i3);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        ImageButton imageButton2 = (ImageButton) this.mContentView.findViewById(R.id.btn_quick_hide_tip);
        this.mQuickHideTipButton = imageButton2;
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AimSettingFloatingWindow.this.showQuickHideTipDialog();
            }
        });
        this.mQuickHideTipDialog = new AlertDialogCenter.Builder(this.mContext, 2131952382).setMessage(this.mContext.getString(R.string.quick_hide_toast)).create();
        this.mQuickHideTipButton.getLocationInWindow(new int[2]);
        Window window = this.mQuickHideTipDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.type = 2038;
        window.setAttributes(attributes);
        SeekBar seekBar2 = (SeekBar) this.mContentView.findViewById(R.id.opacityseekbar);
        this.mOpacitySeekbar = seekBar2;
        seekBar2.setMax(100);
        this.mOpacitySeekbar.setMin(0);
        this.mOpacitySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar3, int i3, boolean z) {
                if (z) {
                    AimSettingFloatingWindow.this.onTransparentChange(i3);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar3) {
            }
        });
        ImageView imageView = (ImageView) this.mContentView.findViewById(R.id.quickhide_checkbox);
        this.mQuickHideCheckbox = imageView;
        imageView.setOnClickListener(this.mQuickHideCheckedChangeListener);
    }

    private ViewGroup.LayoutParams makeParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.format = 1;
        layoutParams.flags = 67108906;
        layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.aim_setting_layout_width);
        layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.aim_setting_layout_expand_height);
        layoutParams.gravity = 21;
        layoutParams.dimAmount = 0.66f;
        layoutParams.y = this.mContext.getResources().getDimensionPixelSize(R.dimen.top_margin);
        layoutParams.x = this.mContext.getResources().getDimensionPixelSize(R.dimen.right_margin);
        layoutParams.setTitle("AimSettingFloat");
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onQuickHideChange() {
        boolean isQuickHide = AimConfigs.getInstance(this.mContext).isQuickHide(this.mGameHelperController.getTopApplication());
        AimConfigs.getInstance(this.mContext).setQuickHide(this.mGameHelperController.getTopApplication(), !isQuickHide);
        ImageView imageView = this.mQuickHideCheckbox;
        if (imageView != null) {
            setChecked(imageView, !isQuickHide);
        }
        this.mGameHelperController.refreshQuickHideFloatView();
        this.mGameHelperController.refreshAimCenter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSizeChange(int i) {
        Log.d(TAG, "onSizeChange size=" + i);
        AimConfigs.getInstance(this.mContext).setSize(this.mGameHelperController.getTopApplication(), i);
        this.mGameHelperController.refreshAimCenter();
    }

    private void onSwitchOpen(boolean z) {
        updateGlobalSettingValue(z, this.mGameHelperController.getTopApplication());
        if (!z) {
            this.mGameHelperController.hideQuickHideFloatView();
        } else if (AimConfigs.getInstance(this.mContext).isQuickHide(this.mGameHelperController.getTopApplication())) {
            this.mGameHelperController.showQuickHideFloatView();
        } else {
            this.mGameHelperController.hideQuickHideFloatView();
        }
        this.mContentView.setBackgroundResource(z ? R.mipmap.window_on_bg : R.mipmap.window_off_bg);
        this.mWindowManager.updateViewLayout(this.mContentView, makeParams());
        if (z) {
            refreshUI();
        }
        this.mGameHelperController.refreshAimCenter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTransparentChange(int i) {
        Log.d(TAG, "onTransparentChange transparent=" + i);
        AimConfigs.getInstance(this.mContext).setTransparent(this.mGameHelperController.getTopApplication(), i);
        this.mGameHelperController.refreshAimCenter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showQuickHideTipDialog() {
        if (this.mQuickHideTipDialog.isShowing()) {
            return;
        }
        this.mQuickHideTipButton.getLocationOnScreen(new int[2]);
        Window window = this.mQuickHideTipDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.type = 2038;
        window.setAttributes(attributes);
        this.mQuickHideTipDialog.show();
    }

    private void updateColorChoiceUI() {
        int color = AimConfigs.getInstance(this.mContext).getColor(this.mGameHelperController.getTopApplication());
        for (int i = 0; i < colors.length; i++) {
            ((SelectableImageView) this.mContentView.findViewById(colorResArr[i])).setSelect(color == colors[i]);
        }
    }

    private void updateGlobalSettingValue(final boolean z, final String str) {
        AsyncTask.execute(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.AimSettingFloatingWindow.8
            @Override // java.lang.Runnable
            public void run() {
                ContentResolver contentResolver = AimSettingFloatingWindow.this.mContext.getContentResolver();
                String string = Settings.Global.getString(contentResolver, "aim_helper_open_pkgs");
                HashSet hashSet = new HashSet(TextUtils.isEmpty(string) ? Collections.emptyList() : Arrays.asList(string.split(",")));
                if (z) {
                    hashSet.add(str);
                } else {
                    hashSet.remove(str);
                }
                StringBuffer stringBuffer = new StringBuffer();
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    stringBuffer.append((String) it.next()).append(",");
                }
                String stringBuffer2 = stringBuffer.toString();
                if (stringBuffer2.endsWith(",")) {
                    stringBuffer2 = stringBuffer2.substring(0, stringBuffer2.length() - 1);
                }
                LogUtil.i(this, "updateGlobalSettingValue value=" + stringBuffer2);
                Settings.Global.putString(contentResolver, "aim_helper_open_pkgs", stringBuffer2);
            }
        });
    }

    private void updateStyleChoiceUI() {
        int style = AimConfigs.getInstance(this.mContext).getStyle(this.mGameHelperController.getTopApplication());
        int i = 0;
        while (true) {
            int[] iArr = styleResArr;
            if (i >= iArr.length) {
                return;
            }
            SelectableImageView selectableImageView = (SelectableImageView) this.mContentView.findViewById(iArr[i]);
            i++;
            selectableImageView.setSelect(i == style);
        }
    }

    public void dismissQuickHideTipDialog() {
        AlertDialogCenter alertDialogCenter = this.mQuickHideTipDialog;
        if (alertDialogCenter == null || !alertDialogCenter.isShowing()) {
            return;
        }
        this.mQuickHideTipDialog.dismiss();
    }

    public void hide() {
        if (this.isShowing) {
            this.mWindowManager.removeViewImmediate(this.mContentView);
            this.isShowing = false;
            Settings.Global.putInt(this.mContext.getContentResolver(), "game_mode_floating_window_show", 0);
            LogUtil.i(TAG, "hide choice view");
        }
        hideQuickHideDialog();
    }

    public void hideQuickHideDialog() {
        dismissQuickHideTipDialog();
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public void refreshUI() {
        AimConfigs aimConfigs = AimConfigs.getInstance(this.mContext);
        updateStyleChoiceUI();
        updateColorChoiceUI();
        this.mSeekBar.setProgress(aimConfigs.getSize(this.mGameHelperController.getTopApplication()));
        boolean isQuickHide = aimConfigs.isQuickHide(this.mGameHelperController.getTopApplication());
        ImageView imageView = this.mQuickHideCheckbox;
        if (imageView != null) {
            setChecked(imageView, isQuickHide);
        }
        this.mOpacitySeekbar.setProgress(aimConfigs.getTransparent(this.mGameHelperController.getTopApplication()));
    }

    public void setChecked(ImageView imageView, boolean z) {
        imageView.setImageResource(z ? R.drawable.function_toggle_on : R.drawable.function_toggle_off);
    }

    public void show() {
        String str = TAG;
        LogUtil.d(str, "show isShowing:" + this.isShowing);
        if (this.isShowing) {
            return;
        }
        initViews();
        this.mContentView.setBackgroundResource(R.mipmap.window_on_bg);
        this.mWindowManager.addView(this.mContentView, makeParams());
        refreshUI();
        this.isShowing = true;
        Settings.Global.putInt(this.mContext.getContentResolver(), "game_mode_floating_window_show", 1);
        LogUtil.i(str, "show choice view");
    }
}
