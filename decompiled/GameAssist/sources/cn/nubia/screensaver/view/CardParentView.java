package cn.nubia.screensaver.view;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;
import cn.nubia.screensaver.CardContainerController;
import cn.nubia.screensaver.GameScreensaverManager;
import cn.nubia.screensaver.power.GSPowerController;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.shared.wrapper.ContextWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CardParentView extends FrameLayout {
    private static final long DOUBLE_CLICK_TIME_DELTA = 200;
    private static final int GESTURE_EXIT_DISTANCE = 20;
    private static final String TAG = "CardParentView";
    private static final boolean TEST_ROTATION;
    private CardViewOutlineProvider mCardViewOutlineProvider;
    private CardContainerController mController;
    private FrameLayout mFlCardContent;
    private FrameLayout mFlFirstToast;
    private ImageView mIvCardDivider;
    private ImageView mIvToastClose;
    private long mLastClickTime;
    private CardView mLeftCard;
    private final Path mLeftCardPath;
    private float mRawDownX;
    private float mRawDownY;
    private CardView mRightCard;
    private final Path mRightCardPath;
    private boolean mScreenSaverFirstToast;
    private ObjectAnimator mShakeAnim;
    private int mShakeTime;
    private boolean mShowMonitor;
    private TestRotationAndTouch mTestRotationAndTouch;
    private boolean mTouchExitZone;
    private boolean mTouchLeftCard;
    private final Path mTouchLeftCardPath;
    private Vibrator mVib;

    public class CardViewOutlineProvider extends ViewOutlineProvider {

        /* renamed from: a, reason: collision with root package name */
        private int f9172a;

        public CardViewOutlineProvider() {
        }

        public void a(int i2) {
            this.f9172a = i2;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (view.equals(CardParentView.this.mLeftCard)) {
                CardParentView.this.mLeftCardPath.reset();
                CardParentView.this.mLeftCardPath.lineTo(view.getWidth(), 0.0f);
                CardParentView.this.mLeftCardPath.lineTo(view.getWidth() - this.f9172a, view.getHeight());
                CardParentView.this.mLeftCardPath.lineTo(0.0f, view.getHeight());
                CardParentView.this.mLeftCardPath.close();
                outline.setPath(CardParentView.this.mLeftCardPath);
                CardParentView.this.mLeftCard.setPath(CardParentView.this.mLeftCardPath);
                return;
            }
            CardParentView.this.mRightCardPath.reset();
            CardParentView.this.mRightCardPath.moveTo(this.f9172a, 0.0f);
            CardParentView.this.mRightCardPath.lineTo(view.getWidth(), 0.0f);
            CardParentView.this.mRightCardPath.lineTo(view.getWidth(), view.getHeight());
            CardParentView.this.mRightCardPath.lineTo(0.0f, view.getHeight());
            CardParentView.this.mRightCardPath.close();
            outline.setPath(CardParentView.this.mRightCardPath);
            CardParentView.this.mRightCard.setPath(CardParentView.this.mRightCardPath);
        }
    }

    private class TestRotationAndTouch {

        /* renamed from: a, reason: collision with root package name */
        private Paint f9174a;

        /* renamed from: b, reason: collision with root package name */
        private Paint f9175b;

        /* renamed from: d, reason: collision with root package name */
        private final Path[] f9177d = new Path[20];

        /* renamed from: e, reason: collision with root package name */
        private final List f9178e = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        private GameScreensaverManager f9176c = GameScreensaverManager.L();

        public TestRotationAndTouch() {
            Paint paint = new Paint();
            this.f9174a = paint;
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            Paint paint2 = new Paint();
            this.f9175b = paint2;
            paint2.setStyle(Paint.Style.STROKE);
            this.f9175b.setColor(-16711936);
            this.f9175b.setStrokeWidth(5.0f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(Canvas canvas) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                Path[] pathArr = this.f9177d;
                if (i3 >= pathArr.length) {
                    break;
                }
                Path path = pathArr[i3];
                if (path != null && !path.isEmpty()) {
                    canvas.drawPath(path, this.f9175b);
                }
                i3++;
            }
            this.f9174a.setTextSize(300.0f);
            this.f9174a.setColor(-65536);
            this.f9174a.setTextAlign(Paint.Align.CENTER);
            this.f9174a.setAlpha(((int) ((SystemClock.elapsedRealtime() / 1000) % 55)) + 200);
            float width = (float) ((CardParentView.this.getWidth() / 2) + ((Math.sin(((((int) (SystemClock.elapsedRealtime() / 23)) % 789) * 6.283185307179586d) / 789.0d) * CardParentView.this.getHeight()) / 3.0d));
            float height = (float) ((CardParentView.this.getHeight() / 2) + ((Math.sin(((((int) (SystemClock.elapsedRealtime() / 11)) % 456) * 6.283185307179586d) / 456.0d) * CardParentView.this.getHeight()) / 4.0d));
            GSPowerController gSPowerController = (GSPowerController) this.f9176c.I(GSPowerController.class);
            if (gSPowerController.w()) {
                canvas.drawText("Red Magic " + (gSPowerController.v() / 1000), width, height, this.f9174a);
                gSPowerController.p();
            } else {
                canvas.drawText("Red Magic", width, height, this.f9174a);
            }
            this.f9174a.setTextSize(50.0f);
            this.f9174a.setColor(-16711936);
            this.f9174a.setTextAlign(Paint.Align.LEFT);
            while (i2 < this.f9178e.size()) {
                String str = (String) this.f9178e.get(i2);
                i2++;
                canvas.drawText(str, 10.0f, this.f9174a.getTextSize() * 1.5f * i2, this.f9174a);
            }
            CardParentView.this.postInvalidate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            int i2 = 0;
            if (actionMasked == 0) {
                while (true) {
                    Path[] pathArr = this.f9177d;
                    if (i2 >= pathArr.length) {
                        break;
                    }
                    pathArr[i2] = null;
                    i2++;
                }
            } else {
                if (actionMasked != 2) {
                    if (actionMasked != 5) {
                        if (actionMasked != 6) {
                            return;
                        }
                    }
                }
                this.f9178e.clear();
                this.f9178e.add("RawX:" + ((int) motionEvent.getRawX()) + " RawY:" + ((int) motionEvent.getRawY()));
                while (i2 < motionEvent.getPointerCount()) {
                    int pointerId = motionEvent.getPointerId(i2);
                    int x = (int) motionEvent.getX(i2);
                    int y = (int) motionEvent.getY(i2);
                    this.f9177d[pointerId].lineTo(x, y);
                    this.f9178e.add("index:" + i2 + " id:" + pointerId + " x:" + x + " y:" + y);
                    i2++;
                }
                return;
            }
            int actionIndex = motionEvent.getActionIndex();
            int pointerId2 = motionEvent.getPointerId(actionIndex);
            int x2 = (int) motionEvent.getX(actionIndex);
            int y2 = (int) motionEvent.getY(actionIndex);
            this.f9177d[pointerId2] = new Path();
            this.f9177d[pointerId2].moveTo(x2, y2);
            this.f9178e.clear();
            this.f9178e.add("RawX:" + ((int) motionEvent.getRawX()) + " RawY:" + ((int) motionEvent.getRawY()));
            this.f9178e.add("index:" + actionIndex + " id:" + pointerId2 + " x:" + x2 + " y:" + y2);
        }
    }

    static {
        TEST_ROTATION = Settings.Global.getInt(ContextWrapper.getContext().getContentResolver(), "game_screensaver_test_rotation", 0) == 1;
    }

    public CardParentView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        boolean x = SharedPreferencesUtil.k(getContext()).x();
        this.mScreenSaverFirstToast = x;
        if (x) {
            return;
        }
        this.mVib = (Vibrator) getContext().getSystemService("vibrator");
        this.mFlFirstToast.setVisibility(0);
        this.mFlFirstToast.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.screensaver.view.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardParentView.this.t(view);
            }
        });
        this.mIvToastClose.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.screensaver.view.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardParentView.this.u(view);
            }
        });
    }

    private void q() {
        int i2 = this.mShakeTime + 1;
        this.mShakeTime = i2;
        if (i2 < 1) {
            x(800);
            return;
        }
        this.mShakeTime = 0;
        ImageView imageView = this.mIvToastClose;
        if (imageView != null) {
            imageView.callOnClick();
        }
    }

    private void r(Context context) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(View view) {
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u(View view) {
        SharedPreferencesUtil.k(getContext()).f0(true);
        this.mFlFirstToast.setVisibility(8);
        this.mScreenSaverFirstToast = true;
        setOnTouchListener(null);
    }

    private void v() {
        GaLog.a(TAG, "on double click exit");
        this.mController.r();
    }

    private void x(int i2) {
        ObjectAnimator objectAnimator = this.mShakeAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mFlFirstToast, PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y, Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(0.143f, p(-5.0f)), Keyframe.ofFloat(0.343f, p(4.0f)), Keyframe.ofFloat(0.514f, p(-4.0f)), Keyframe.ofFloat(0.688f, p(3.0f)), Keyframe.ofFloat(0.888f, p(-3.0f)), Keyframe.ofFloat(0.99f, p(2.0f)), Keyframe.ofFloat(1.0f, 0.0f)));
        this.mShakeAnim = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setDuration(i2);
        this.mShakeAnim.start();
        this.mVib.vibrate(DOUBLE_CLICK_TIME_DELTA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(int i2) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int max = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        int min = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        int width = max == this.mFlCardContent.getWidth() ? (this.mIvCardDivider.getWidth() * (min - this.mFlCardContent.getHeight())) / (this.mFlCardContent.getHeight() * 2) : 0;
        this.mTouchLeftCardPath.reset();
        this.mTouchLeftCardPath.lineTo(i2 + width, 0.0f);
        float f2 = min;
        this.mTouchLeftCardPath.lineTo((i2 - this.mIvCardDivider.getWidth()) - width, f2);
        this.mTouchLeftCardPath.lineTo(0.0f, f2);
        this.mTouchLeftCardPath.close();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        TestRotationAndTouch testRotationAndTouch = this.mTestRotationAndTouch;
        if (testRotationAndTouch != null) {
            testRotationAndTouch.c(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        TestRotationAndTouch testRotationAndTouch = this.mTestRotationAndTouch;
        if (testRotationAndTouch != null) {
            testRotationAndTouch.d(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (TEST_ROTATION) {
            this.mTestRotationAndTouch = new TestRotationAndTouch();
        }
        this.mIvCardDivider = (ImageView) findViewById(R.id.iv_card_divider);
        this.mLeftCard = (CardView) findViewById(R.id.left_card);
        this.mRightCard = (CardView) findViewById(R.id.right_card);
        this.mFlCardContent = (FrameLayout) findViewById(R.id.fl_card_content);
        this.mFlFirstToast = (FrameLayout) findViewById(R.id.fl_first_toast);
        this.mIvToastClose = (ImageView) findViewById(R.id.iv_toast_close);
        this.mLeftCard.setIsLeftCard(true);
        this.mCardViewOutlineProvider = new CardViewOutlineProvider();
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: cn.nubia.screensaver.view.CardParentView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnPreDrawListener(this);
                } else {
                    CardParentView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                int width = ((CardParentView.this.mFlCardContent.getWidth() - CardParentView.this.mIvCardDivider.getWidth()) / 2) + CardParentView.this.mIvCardDivider.getWidth();
                ViewGroup.LayoutParams layoutParams = CardParentView.this.mLeftCard.getLayoutParams();
                layoutParams.width = width;
                CardParentView.this.mLeftCard.setLayoutParams(layoutParams);
                ViewGroup.LayoutParams layoutParams2 = CardParentView.this.mRightCard.getLayoutParams();
                layoutParams2.width = width;
                CardParentView.this.mRightCard.setLayoutParams(layoutParams2);
                CardParentView.this.mCardViewOutlineProvider.a(CardParentView.this.mIvCardDivider.getWidth());
                CardParentView.this.mLeftCard.setOutlineProvider(CardParentView.this.mCardViewOutlineProvider);
                CardParentView.this.mLeftCard.setClipToOutline(true);
                CardParentView.this.mRightCard.setOutlineProvider(CardParentView.this.mCardViewOutlineProvider);
                CardParentView.this.mRightCard.setClipToOutline(true);
                CardParentView.this.mController = new CardContainerController(CardParentView.this);
                CardParentView.this.mLeftCard.setContainerController(CardParentView.this.mController);
                CardParentView.this.mRightCard.setContainerController(CardParentView.this.mController);
                CardParentView.this.y(width);
                if (!CardParentView.this.mShowMonitor) {
                    CardParentView.this.o();
                }
                GaLog.a(CardParentView.TAG, "card width " + width + ",w:" + CardParentView.this.mFlCardContent.getWidth() + ",h:" + CardParentView.this.mFlCardContent.getHeight() + ",d:" + CardParentView.this.mIvCardDivider.getWidth());
                return false;
            }
        });
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mScreenSaverFirstToast;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > 1) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                v();
                return true;
            }
            this.mLastClickTime = currentTimeMillis;
            this.mRawDownX = motionEvent.getRawX();
            this.mRawDownY = motionEvent.getRawY();
            this.mTouchLeftCard = s(motionEvent);
            float f2 = this.mRawDownX;
            if (f2 < 20.0f || f2 > getWidth() - 20 || this.mRawDownY > getHeight() - 20) {
                GaLog.a(TAG, "start gesture exit");
                this.mTouchExitZone = true;
            } else {
                this.mTouchExitZone = false;
            }
        } else if (action == 1 || action == 3) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            if (this.mTouchExitZone && this.mController != null) {
                if (this.mRawDownY <= getHeight() - 20) {
                    float f3 = this.mRawDownX;
                    float f4 = rawX - f3;
                    if (f3 > getWidth() - 20) {
                        f4 = this.mRawDownX - rawX;
                    }
                    if (f4 > 10.0f) {
                        GaLog.a(TAG, "gesture exit in x");
                        this.mController.r();
                    }
                } else if (this.mRawDownY - rawY > 10.0f) {
                    GaLog.a(TAG, "gesture exit in y");
                    this.mController.r();
                }
            }
        }
        if (!this.mTouchExitZone) {
            if (this.mTouchLeftCard) {
                this.mLeftCard.r(motionEvent);
            } else {
                this.mRightCard.r(motionEvent);
            }
        }
        return true;
    }

    public int p(float f2) {
        return (int) ((f2 * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public boolean s(MotionEvent motionEvent) {
        Path path = new Path();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        path.addRect(x - 1, y - 1, x + 1, y + 1, Path.Direction.CCW);
        path.op(this.mTouchLeftCardPath, Path.Op.INTERSECT);
        return !path.isEmpty();
    }

    public void setShowMonitor(boolean z) {
        this.mShowMonitor = z;
    }

    public void w() {
        CardContainerController cardContainerController = this.mController;
        if (cardContainerController != null) {
            cardContainerController.u();
        }
    }

    public CardParentView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mRightCardPath = new Path();
        this.mLeftCardPath = new Path();
        this.mTouchLeftCardPath = new Path();
        this.mShakeAnim = null;
        this.mShakeTime = 0;
        this.mLastClickTime = 0L;
        r(context);
    }
}
