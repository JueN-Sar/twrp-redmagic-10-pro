package cn.nubia.gameassist.panel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.drawable.diplogen.DiplogenUtils;
import cn.nubia.gameassist.panel.drawable.diplogen.NeonLampDrawable;
import cn.nubia.gameassist.panel.drawable.diplogen.PanelDrawable;
import cn.nubia.gameassist.panel.drawable.diplogen.RatioPoint;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;

/* loaded from: classes.dex */
public class PanelTouchHelper {

    /* renamed from: c, reason: collision with root package name */
    private boolean f6809c;

    /* renamed from: d, reason: collision with root package name */
    private int f6810d;

    /* renamed from: e, reason: collision with root package name */
    private float f6811e;

    /* renamed from: f, reason: collision with root package name */
    private float f6812f;

    /* renamed from: g, reason: collision with root package name */
    private int f6813g;

    /* renamed from: h, reason: collision with root package name */
    private int f6814h;

    /* renamed from: i, reason: collision with root package name */
    private int f6815i;

    /* renamed from: k, reason: collision with root package name */
    private VelocityTracker f6817k;

    /* renamed from: l, reason: collision with root package name */
    private Callback f6818l;

    /* renamed from: m, reason: collision with root package name */
    private final OutSpace f6819m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f6820n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f6821o;

    /* renamed from: a, reason: collision with root package name */
    private boolean f6807a = false;

    /* renamed from: b, reason: collision with root package name */
    public boolean f6808b = false;

    /* renamed from: j, reason: collision with root package name */
    private int f6816j = -1;

    /* renamed from: p, reason: collision with root package name */
    private RectF f6822p = new RectF();

    /* renamed from: q, reason: collision with root package name */
    private Runnable f6823q = new Runnable() { // from class: cn.nubia.gameassist.panel.PanelTouchHelper.1
        @Override // java.lang.Runnable
        public void run() {
            GaLog.k("PanelTouchHelper", "hidePanel mResetHiddingRunnable");
            PanelTouchHelper.this.f6808b = false;
        }
    };

    public interface Callback {
        void hidePanel();

        boolean touchCaptureView(float f2, float f3);

        boolean touchInChildView(float f2, float f3);
    }

    public static class OutSpace {

        /* renamed from: a, reason: collision with root package name */
        private Path f6825a;

        /* renamed from: b, reason: collision with root package name */
        private Path f6826b;

        /* renamed from: c, reason: collision with root package name */
        private final Paint f6827c;

        public OutSpace() {
            b(new RectF());
            Paint paint = new Paint();
            this.f6827c = paint;
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3.0f);
            paint.setColor(-1);
        }

        private RatioPoint[][] a(RatioPoint[][] ratioPointArr, RatioPoint[] ratioPointArr2) {
            int length = ratioPointArr.length;
            RatioPoint[][] ratioPointArr3 = new RatioPoint[length + 1][];
            System.arraycopy(ratioPointArr, 0, ratioPointArr3, 0, ratioPointArr.length);
            ratioPointArr3[length] = ratioPointArr2;
            return ratioPointArr3;
        }

        public void b(RectF rectF) {
            if (FoldMgr.f() && FoldMgr.c().e()) {
                Point R = GameAssistWindowManager.R();
                float dimensionPixelSize = ContextWrapper.getContext().getResources().getDimensionPixelSize(R.dimen.fold_game_assist_panel_height);
                RatioPoint[][] a2 = a(DiplogenUtils.h(NeonLampDrawable.H, dimensionPixelSize, dimensionPixelSize), new RatioPoint[]{new RatioPoint(0.0f, R.y)});
                this.f6825a = DiplogenUtils.c(null, a2, a(DiplogenUtils.i(DiplogenUtils.h(a2, -1.0f, 1.0f), R.x, 0.0f), new RatioPoint[]{new RatioPoint(R.x, R.y)}));
                Path path = new Path();
                path.addRect(rectF, Path.Direction.CW);
                this.f6825a.op(path, Path.Op.DIFFERENCE);
                this.f6825a.close();
                this.f6826b = this.f6825a;
                return;
            }
            int P = GameAssistWindowManager.P();
            float Q = GameAssistWindowManager.Q();
            RatioPoint[][] h2 = DiplogenUtils.h(DiplogenUtils.i(NeonLampDrawable.F, PanelDrawable.t, 0.0f), Q, Q);
            float f2 = P;
            this.f6825a = DiplogenUtils.c(null, h2, DiplogenUtils.i(DiplogenUtils.h(h2, -1.0f, 1.0f), f2, 0.0f));
            Path path2 = new Path();
            Path.Direction direction = Path.Direction.CW;
            path2.addRect(rectF, direction);
            Path path3 = this.f6825a;
            Path.Op op = Path.Op.DIFFERENCE;
            path3.op(path2, op);
            this.f6825a.close();
            float f3 = Q / 2.0f;
            RatioPoint[][] g2 = DiplogenUtils.g(h2, new RatioPoint(f3, f3), 90.0f);
            this.f6826b = DiplogenUtils.c(null, g2, DiplogenUtils.i(DiplogenUtils.h(g2, 1.0f, -1.0f), 0.0f, f2));
            Path path4 = new Path();
            path4.addRect(rectF, direction);
            this.f6826b.op(path4, op);
            this.f6826b.close();
        }

        public boolean c(MotionEvent motionEvent) {
            Path path = new Path();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            path.addRect(x - 1, y - 1, x + 1, y + 1, Path.Direction.CCW);
            if (RotationMgr.j()) {
                path.op(this.f6825a, Path.Op.INTERSECT);
            } else {
                path.op(this.f6826b, Path.Op.INTERSECT);
            }
            GaLog.k("oneMoreThing", "path =" + path.isEmpty());
            return !path.isEmpty();
        }
    }

    public PanelTouchHelper(Context context, Callback callback) {
        this.f6810d = 1;
        this.f6818l = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f6813g = viewConfiguration.getScaledTouchSlop();
        this.f6814h = (int) (context.getResources().getDisplayMetrics().density * 20.0f);
        this.f6815i = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f6810d = 1;
        this.f6819m = new OutSpace();
    }

    private boolean a(float f2, float f3) {
        if (Math.abs(f2) < Math.abs(f3)) {
            return false;
        }
        return this.f6812f > ((float) (GameAssistWindowManager.Q / 2)) ? f2 > ((float) this.f6813g) : (-f2) > ((float) this.f6813g);
    }

    private boolean b(float f2, float f3) {
        float f4 = (f2 * f2) + (f3 * f3);
        int i2 = this.f6813g;
        return f4 > ((float) (i2 * i2));
    }

    private float c() {
        VelocityTracker velocityTracker = this.f6817k;
        velocityTracker.computeCurrentVelocity(1000, this.f6815i);
        return j() ? velocityTracker.getXVelocity() : velocityTracker.getYVelocity();
    }

    private void g() {
        if (this.f6808b) {
            GaLog.k("PanelTouchHelper", "hidePanel is hidding");
            return;
        }
        this.f6808b = true;
        GameAssistApplication.u(this.f6823q);
        GameAssistApplication.t(this.f6823q, 300L);
        this.f6818l.hidePanel();
    }

    private void h() {
        VelocityTracker velocityTracker = this.f6817k;
        if (velocityTracker == null) {
            this.f6817k = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void i() {
        if (this.f6817k == null) {
            this.f6817k = VelocityTracker.obtain();
        }
    }

    private boolean j() {
        return RotationMgr.j();
    }

    private void l(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & 65280) >> 8;
        if (motionEvent.getPointerId(action) == this.f6816j) {
            int i2 = action == 0 ? 1 : 0;
            this.f6811e = motionEvent.getY(i2);
            this.f6812f = motionEvent.getX(i2);
            this.f6816j = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.f6817k;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void n() {
        VelocityTracker velocityTracker = this.f6817k;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f6817k = null;
        }
    }

    private void o() {
        this.f6816j = -1;
        this.f6807a = false;
        this.f6808b = false;
        n();
        this.f6810d = 1;
    }

    public void d(Canvas canvas) {
    }

    public boolean e(RectF rectF) {
        RectF rectF2 = this.f6822p;
        return rectF2.left == rectF.left && rectF2.top == rectF.top && rectF2.right == rectF.right && rectF2.bottom == rectF.bottom;
    }

    public void f() {
        GameAssistApplication.u(this.f6823q);
        this.f6808b = false;
    }

    public boolean k(MotionEvent motionEvent) {
        int findPointerIndex;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        if (actionMasked == 6) {
                            l(motionEvent);
                        }
                    }
                } else {
                    if (this.f6821o) {
                        return false;
                    }
                    int i2 = this.f6816j;
                    if (i2 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i2)) != -1) {
                        float x = motionEvent.getX(findPointerIndex);
                        float y = motionEvent.getY(findPointerIndex);
                        float f2 = x - this.f6812f;
                        float f3 = y - this.f6811e;
                        if (this.f6809c) {
                            this.f6809c = !b(f2, f3);
                        }
                        if (this.f6810d == 1 && a(f2, f3) && !this.f6818l.touchInChildView(x, y)) {
                            this.f6807a = true;
                            this.f6812f = x;
                            this.f6811e = y;
                        }
                        if (this.f6810d == 2 && b(f2, f3)) {
                            this.f6807a = true;
                            this.f6812f = x;
                            this.f6811e = y;
                        }
                        i();
                        this.f6817k.addMovement(motionEvent);
                    }
                }
            }
            if (this.f6810d == 1 && this.f6809c) {
                g();
            }
            this.f6807a = false;
            this.f6816j = -1;
            n();
            o();
        } else {
            this.f6807a = false;
            this.f6821o = true;
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            this.f6812f = x2;
            this.f6811e = y2;
            if (this.f6810d == 2) {
                this.f6816j = motionEvent.getPointerId(0);
            }
            if (this.f6810d == 1 && this.f6818l.touchCaptureView(x2, y2)) {
                this.f6816j = motionEvent.getPointerId(0);
            }
            this.f6820n = this.f6819m.c(motionEvent);
            this.f6809c = !this.f6818l.touchCaptureView(x2, y2) || this.f6820n;
            h();
            this.f6817k.addMovement(motionEvent);
        }
        return this.f6807a;
    }

    public boolean m(MotionEvent motionEvent) {
        int findPointerIndex;
        i();
        this.f6817k.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        if (actionMasked == 5) {
                            int actionIndex = motionEvent.getActionIndex();
                            this.f6812f = motionEvent.getX(actionIndex);
                            this.f6811e = motionEvent.getY(actionIndex);
                            this.f6816j = motionEvent.getPointerId(actionIndex);
                        } else if (actionMasked == 6) {
                            l(motionEvent);
                        }
                    }
                } else {
                    if (this.f6821o) {
                        this.f6821o = false;
                        return false;
                    }
                    if (this.f6816j == -1) {
                        int actionIndex2 = motionEvent.getActionIndex();
                        if (actionIndex2 != -1) {
                            float x = motionEvent.getX(actionIndex2);
                            float y = motionEvent.getY(actionIndex2);
                            if (this.f6809c) {
                                this.f6809c = !b(x - this.f6812f, y - this.f6811e);
                            }
                            if (this.f6818l.touchCaptureView(x, y)) {
                                this.f6816j = motionEvent.getPointerId(actionIndex2);
                                this.f6812f = x;
                                this.f6811e = y;
                            }
                        }
                    }
                    int i2 = this.f6816j;
                    if (i2 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i2)) != -1) {
                        float x2 = motionEvent.getX(findPointerIndex);
                        float y2 = motionEvent.getY(findPointerIndex);
                        float f2 = x2 - this.f6812f;
                        float f3 = y2 - this.f6811e;
                        if (this.f6809c) {
                            this.f6809c = !b(f2, f3);
                        }
                        if (!this.f6807a && a(f2, f3)) {
                            GaLog.e("PanelTouchHelper", "onTouchEvent move == " + this.f6809c + this.f6816j);
                            this.f6807a = true;
                            this.f6812f = x2;
                            this.f6811e = y2;
                        }
                        if (this.f6807a) {
                            this.f6812f = x2;
                            this.f6811e = y2;
                        }
                    }
                }
            }
            if (this.f6810d == 1 && this.f6809c) {
                g();
            }
            if (this.f6807a) {
                float c2 = c();
                GaLog.e("PanelTouchHelper", "onTouchEvent up velocity=" + c2 + " mMinimumVelocity=" + this.f6814h);
                if (Math.abs(c2) > this.f6814h) {
                    g();
                }
            } else if (this.f6810d == 2) {
                g();
            }
            this.f6807a = false;
            this.f6816j = -1;
            n();
        } else {
            this.f6807a = false;
            float x3 = motionEvent.getX();
            float y3 = motionEvent.getY();
            this.f6809c = !this.f6818l.touchCaptureView(x3, y3) || this.f6820n;
            this.f6812f = x3;
            this.f6811e = y3;
            if (this.f6810d == 2) {
                this.f6816j = motionEvent.getPointerId(0);
            }
            if (this.f6810d == 1 && this.f6818l.touchCaptureView(x3, y3)) {
                this.f6816j = motionEvent.getPointerId(0);
            }
            GaLog.e("PanelTouchHelper", "onTouchEvent down == " + this.f6809c + this.f6816j);
            h();
            this.f6817k.addMovement(motionEvent);
        }
        return true;
    }

    public void p(RectF rectF) {
        GaLog.a("PanelTouchHelper", "update omt " + rectF + "," + this.f6822p);
        if (e(rectF)) {
            return;
        }
        this.f6822p.set(rectF);
        this.f6819m.b(this.f6822p);
    }

    public void q() {
        if (SystemMgr.H()) {
            GaLog.j("PanelTouchHelper", "updateOutSpace " + GameAssistWindowManager.R() + " " + this.f6822p);
            this.f6819m.b(this.f6822p);
        }
    }
}
