package cn.nubia.studio;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.xgravitation.util.LogUtils;

/* loaded from: classes.dex */
public class PopWindowManager implements PopupWindow.OnDismissListener, View.OnClickListener, View.OnTouchListener {
    private static final int FULL_SCREEN_FLAG = 4102;
    private static final String TAG = "PopWindowManager";
    private TextView mContentView;
    private Context mContext;
    private TextView mForwardButton;
    public EquipmentType mLastEquipmentType;
    private int mPopWindowToTargetViewPadding;
    private volatile PopupWindow mPopupWindow;
    private PopupWindowDismissCallBack mPopupWindowDismissCallBack;
    private int mPopupWindowHeight;
    private View mShowView;

    /* renamed from: cn.nubia.studio.PopWindowManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType;

        static {
            int[] iArr = new int[EquipmentType.values().length];
            $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType = iArr;
            try {
                iArr[EquipmentType.Mouse.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType[EquipmentType.Monitor.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType[EquipmentType.Keyboard.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    enum EquipmentType {
        Monitor,
        Keyboard,
        Mouse,
        X_Gamepad,
        X_Keyboard,
        X_Casting,
        None
    }

    interface PopupWindowDismissCallBack {
        EquipmentType getEquipmentTypeByClickPosition(int i, int i2);

        void updateButtonStatus(EquipmentType equipmentType);
    }

    public PopWindowManager(PopupWindowDismissCallBack popupWindowDismissCallBack) {
        this.mPopupWindowDismissCallBack = popupWindowDismissCallBack;
    }

    public PopupWindow createWindow(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.show_content_layout, (ViewGroup) null);
        this.mShowView = inflate;
        this.mContentView = (TextView) inflate.findViewById(R.id.content_view);
        this.mForwardButton = (TextView) this.mShowView.findViewById(R.id.forward_button);
        if (CommonUtil.isInternalVersion()) {
            this.mForwardButton.setVisibility(8);
        }
        this.mPopupWindowHeight = (int) (TypedValue.applyDimension(1, 64.0f, context.getResources().getDisplayMetrics()) + 0.5f);
        this.mPopWindowToTargetViewPadding = (int) (TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics()) + 0.5f);
        if (this.mPopupWindow == null) {
            synchronized (PopWindowManager.class) {
                if (this.mPopupWindow == null) {
                    this.mPopupWindow = new PopupWindow(this.mShowView, -2, this.mPopupWindowHeight);
                    this.mPopupWindow.setOutsideTouchable(true);
                    this.mPopupWindow.setFocusable(true);
                    this.mPopupWindow.setTouchInterceptor(new View.OnTouchListener() { // from class: cn.nubia.studio.PopWindowManager$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnTouchListener
                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                            return PopWindowManager.this.onTouch(view, motionEvent);
                        }
                    });
                    this.mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: cn.nubia.studio.PopWindowManager$$ExternalSyntheticLambda2
                        @Override // android.widget.PopupWindow.OnDismissListener
                        public final void onDismiss() {
                            PopWindowManager.this.onDismiss();
                        }
                    });
                    this.mPopupWindow.getContentView().setSystemUiVisibility(FULL_SCREEN_FLAG);
                    this.mPopupWindow.setBackgroundDrawable(context.getDrawable(R.drawable.popup_window_background_new));
                }
            }
        }
        return this.mPopupWindow;
    }

    public void dismissPopupWindow() {
        if (this.mPopupWindow == null || !this.mPopupWindow.isShowing()) {
            return;
        }
        this.mPopupWindow.dismiss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(view.getContext().getResources().getString(R.string.red_magic_pirchase_link_uri_text)));
                intent.addFlags(268435456);
                view.getContext().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            dismissPopupWindow();
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        LogUtils.d(TAG, " onDismiss ");
        PopupWindowDismissCallBack popupWindowDismissCallBack = this.mPopupWindowDismissCallBack;
        if (popupWindowDismissCallBack != null) {
            popupWindowDismissCallBack.updateButtonStatus(EquipmentType.None);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        EquipmentType equipmentType = EquipmentType.None;
        if (motionEvent.getAction() != 0) {
            return false;
        }
        LogUtils.d(TAG, " onTouch  getRawX = " + rawX + " ;; getRawY = " + rawY);
        PopupWindowDismissCallBack popupWindowDismissCallBack = this.mPopupWindowDismissCallBack;
        if (popupWindowDismissCallBack != null) {
            equipmentType = popupWindowDismissCallBack.getEquipmentTypeByClickPosition(rawX, rawY);
        }
        LogUtils.d(TAG, " onTouch  mLastEquipmentType = " + this.mLastEquipmentType + " ;; currentEquipmentType = " + equipmentType);
        if (this.mLastEquipmentType == equipmentType && equipmentType != EquipmentType.None) {
            return true;
        }
        this.mLastEquipmentType = equipmentType;
        return false;
    }

    public boolean popupWindowIsShowing() {
        if (this.mPopupWindow != null) {
            return this.mPopupWindow.isShowing();
        }
        return false;
    }

    public void showPopupWindow(View view) {
        if (this.mPopupWindow == null || this.mPopupWindow.isShowing()) {
            return;
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        Log.d(TAG, "showPopupWindow: left = " + rect.left + " ;; top = " + rect.top + " ;; right = " + rect.right + " bottom = " + rect.bottom);
        this.mPopupWindow.showAsDropDown(view, 0, -(((this.mPopupWindowHeight + rect.bottom) - rect.top) + this.mPopWindowToTargetViewPadding), 48);
    }

    public void updateContentView(Context context, EquipmentType equipmentType) {
        int i = AnonymousClass1.$SwitchMap$cn$nubia$studio$PopWindowManager$EquipmentType[equipmentType.ordinal()];
        int i2 = R.string.red_magic_sport_mouse_instruction_text;
        int i3 = R.dimen.popup_window_mouse_max_width;
        if (i != 1) {
            if (i == 2) {
                i2 = R.string.red_magic_sport_screen_instruction_text;
                i3 = R.dimen.popup_window_monitor_max_width;
            } else if (i == 3) {
                i2 = R.string.red_magic_sport_keyboard_instruction_text;
                i3 = R.dimen.popup_window_keyboard_max_width;
            }
        }
        this.mLastEquipmentType = equipmentType;
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(i3);
        TextView textView = this.mContentView;
        if (textView != null) {
            textView.setText(i2);
            this.mContentView.setMaxWidth(dimensionPixelOffset);
            this.mContentView.setSelected(true);
        }
        TextView textView2 = this.mForwardButton;
        if (textView2 != null) {
            textView2.setSelected(true);
        }
        if (this.mPopupWindow != null) {
            this.mShowView.setSystemUiVisibility(FULL_SCREEN_FLAG);
            this.mPopupWindow.setContentView(this.mShowView);
        }
        if (CommonUtil.isInternalVersion()) {
            return;
        }
        this.mForwardButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.studio.PopWindowManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PopWindowManager.this.onClick(view);
            }
        });
    }
}
