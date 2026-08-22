package cn.nubia.chatassistant.floatingball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class BroadcastFloatingBall {
    private static final String TAG = "BroadcastFloatingBall";
    public static BroadcastFloatingBall floatView2;
    private View.OnClickListener clickListener;
    private Context context;
    int mPerCharWidth;
    int mVisibleCharCount;
    private int oldPosition;
    WindowManager.LayoutParams params;
    private View view;
    private WindowManager wm;
    private int POSITION_TOP_OFFSET = 108;
    private int POSITION_RIGHT_OFFSET = 750;

    public BroadcastFloatingBall(Context context) {
        this.context = context;
    }

    public static BroadcastFloatingBall getInstance(Context context) {
        if (floatView2 == null) {
            floatView2 = new BroadcastFloatingBall(context);
        }
        return floatView2;
    }

    public void createFloatView() {
        if (this.view == null) {
            this.view = LayoutInflater.from(this.context).inflate(R.layout.chat_assistant_floating_ball, (ViewGroup) null);
        }
        this.wm = (WindowManager) this.context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.params = layoutParams;
        layoutParams.type = 1;
        this.params.flags = 40;
        this.params.format = -3;
        this.params.windowAnimations = R.style.custom_toast_anim_view;
        this.params.type = 2038;
        this.params.width = -2;
        this.params.height = -2;
        this.params.gravity = 51;
        int i = this.context.getResources().getDisplayMetrics().widthPixels;
        this.params.y = this.POSITION_TOP_OFFSET;
        this.params.x = i - this.POSITION_RIGHT_OFFSET;
        this.view.setBackgroundColor(0);
        this.view.setVisibility(0);
        this.view.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.chatassistant.floatingball.BroadcastFloatingBall.1
            float lastX;
            float lastY;
            int oldOffsetX;
            int oldOffsetY;
            int tag = 0;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (this.tag == 0) {
                    this.oldOffsetX = BroadcastFloatingBall.this.params.x;
                    this.oldOffsetY = BroadcastFloatingBall.this.params.y;
                }
                if (action == 0) {
                    this.lastX = x;
                    this.lastY = y;
                } else if (action == 2) {
                    BroadcastFloatingBall.this.params.x += ((int) (x - this.lastX)) / 3;
                    BroadcastFloatingBall.this.params.y += ((int) (y - this.lastY)) / 3;
                    this.tag = 1;
                    BroadcastFloatingBall.this.wm.updateViewLayout(BroadcastFloatingBall.this.view, BroadcastFloatingBall.this.params);
                } else if (action == 1) {
                    int i2 = BroadcastFloatingBall.this.params.x;
                    int i3 = BroadcastFloatingBall.this.params.y;
                    if (Math.abs(this.oldOffsetX - i2) > 20 || Math.abs(this.oldOffsetY - i3) > 20) {
                        BroadcastFloatingBall.this.wm.updateViewLayout(BroadcastFloatingBall.this.view, BroadcastFloatingBall.this.params);
                        this.tag = 0;
                    } else if (BroadcastFloatingBall.this.clickListener != null) {
                        BroadcastFloatingBall.this.clickListener.onClick(BroadcastFloatingBall.this.view);
                    }
                }
                return true;
            }
        });
        try {
            this.wm.addView(this.view, this.params);
        } catch (Exception e) {
            LogUtils.d("createFloatView", "exception");
            e.printStackTrace();
        }
    }

    public void destroy() {
        removeFloatView();
    }

    public void hideFloatView() {
        View view;
        if (this.wm == null || (view = this.view) == null || view.getVisibility() != 0) {
            return;
        }
        LogUtils.d(TAG, "hideFloatView");
        this.view.setVisibility(4);
    }

    public boolean isFloatViewExist() {
        return this.view != null;
    }

    public boolean isFloatViewVisible() {
        View view = this.view;
        return view != null && view.getVisibility() == 0;
    }

    public void measureVoiceText() {
        View view = this.view;
        if (view != null) {
            MarqueeTextView marqueeTextView = (MarqueeTextView) view.findViewById(R.id.msg);
            int textWidth = marqueeTextView.getTextWidth();
            int width = marqueeTextView.getWidth();
            this.mPerCharWidth = textWidth / marqueeTextView.getText().length();
            this.mVisibleCharCount = (width / r1) - 3;
        }
    }

    public void onFloatViewClick(View.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    public void removeFloatView() {
        if (this.wm == null || this.view == null) {
            return;
        }
        LogUtils.d(TAG, "removeFloatView");
        this.wm.removeViewImmediate(this.view);
        this.view = null;
        this.wm = null;
    }

    public void resetVoiceTextPosition() {
        View view = this.view;
        if (view != null) {
            ((MarqueeTextView) view.findViewById(R.id.msg)).resetScroll();
        }
    }

    public void scrollVoiceTextWhenSpeak(int i) {
        View view = this.view;
        if (view != null) {
            MarqueeTextView marqueeTextView = (MarqueeTextView) view.findViewById(R.id.msg);
            int i2 = (i - this.mVisibleCharCount) * this.mPerCharWidth;
            if (i2 > 0) {
                marqueeTextView.startScroll(i2 - this.oldPosition);
            }
            this.oldPosition = i2;
        }
    }

    public void setVoiceText(String str) {
        View view = this.view;
        if (view != null) {
            ((MarqueeTextView) view.findViewById(R.id.msg)).setText(str);
            str.length();
        }
    }

    public void showFloatView() {
        View view;
        if (this.wm == null || (view = this.view) == null || view.getVisibility() == 0) {
            return;
        }
        LogUtils.d(TAG, "showfloatview");
        this.view.setVisibility(0);
    }

    public void updateViewLayout() {
        if (this.wm == null || this.view == null) {
            return;
        }
        int i = this.context.getResources().getDisplayMetrics().widthPixels;
        this.params.y = this.POSITION_TOP_OFFSET;
        this.params.x = i - this.POSITION_RIGHT_OFFSET;
        this.wm.updateViewLayout(this.view, this.params);
    }
}
