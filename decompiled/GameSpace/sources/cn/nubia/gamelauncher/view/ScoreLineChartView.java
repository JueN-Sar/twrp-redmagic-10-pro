package cn.nubia.gamelauncher.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;
import cn.nubia.gamelauncher.bean.DailyScoreBean;
import cn.nubia.gamelauncher.util.CommonUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes.dex */
public class ScoreLineChartView extends View {
    private static final String TAG = "ScoreRecord";
    private Context mContext;
    private Paint mCpsPaint;
    private List<DailyScoreBean> mDayScoreLists;
    private int mHeight;
    private int mInitRightX;
    private int mLineBottomY;
    private Paint mMpmPaint;
    private Paint mPaint;
    private PathEffect mPathEffect;
    private final int mPerPageCount;
    private int mRightIndex;
    private int mSlideX;
    private final int mStepPerPage;
    private int mStepX;
    private boolean mSupportPart1;
    private Paint mTextPaint;
    private int mWidth;
    private Paint mWinPaint;
    private float tempLastX;

    public ScoreLineChartView(Context context) {
        this(context, null);
    }

    public ScoreLineChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStepPerPage = 6;
        this.mPerPageCount = 7;
        this.mSlideX = 0;
        this.mContext = context;
        this.mDayScoreLists = new ArrayList();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setColor(Color.parseColor("#29FFFFFF"));
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(2.0f);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f);
        this.mPathEffect = dashPathEffect;
        this.mPaint.setPathEffect(dashPathEffect);
        Paint paint2 = new Paint(1);
        this.mTextPaint = paint2;
        paint2.setColor(Color.parseColor("#FFFFFFFF"));
        this.mTextPaint.setTextSize(22.0f);
        Paint paint3 = new Paint(1);
        this.mWinPaint = paint3;
        paint3.setColor(Color.parseColor("#FFFF6E44"));
        this.mWinPaint.setStyle(Paint.Style.STROKE);
        this.mWinPaint.setStrokeWidth(4.0f);
        Paint paint4 = new Paint(1);
        this.mCpsPaint = paint4;
        paint4.setColor(Color.parseColor("#FF76EF74"));
        this.mCpsPaint.setStyle(Paint.Style.STROKE);
        this.mCpsPaint.setStrokeWidth(4.0f);
        Paint paint5 = new Paint(1);
        this.mMpmPaint = paint5;
        paint5.setColor(Color.parseColor("#FFEF74EB"));
        this.mMpmPaint.setStyle(Paint.Style.STROKE);
        this.mMpmPaint.setStrokeWidth(4.0f);
    }

    public ScoreLineChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStepPerPage = 6;
        this.mPerPageCount = 7;
        this.mSlideX = 0;
    }

    private void drawCpsLine(Canvas canvas) {
        if (this.mDayScoreLists.size() < 1) {
            return;
        }
        int min = Math.min(this.mRightIndex + 7, this.mDayScoreLists.size());
        if (this.mSupportPart1) {
            Path path = new Path();
            path.moveTo(this.mDayScoreLists.get(this.mRightIndex).getLineX(), this.mDayScoreLists.get(this.mRightIndex).getWinY());
            if (this.mRightIndex + 1 == min) {
                canvas.drawCircle(this.mDayScoreLists.get(r4).getLineX(), this.mDayScoreLists.get(this.mRightIndex).getWinY(), 3.0f, this.mWinPaint);
            }
            for (int i = this.mRightIndex + 1; i < min; i++) {
                path.lineTo(this.mDayScoreLists.get(i).getLineX(), this.mDayScoreLists.get(i).getWinY());
            }
            canvas.drawPath(path, this.mWinPaint);
        }
        Path path2 = new Path();
        path2.moveTo(this.mDayScoreLists.get(this.mRightIndex).getLineX(), this.mDayScoreLists.get(this.mRightIndex).getCpsY());
        if (this.mRightIndex + 1 == min) {
            canvas.drawCircle(this.mDayScoreLists.get(r4).getLineX(), this.mDayScoreLists.get(this.mRightIndex).getCpsY(), 3.0f, this.mCpsPaint);
        }
        for (int i2 = this.mRightIndex + 1; i2 < min; i2++) {
            path2.lineTo(this.mDayScoreLists.get(i2).getLineX(), this.mDayScoreLists.get(i2).getCpsY());
        }
        canvas.drawPath(path2, this.mCpsPaint);
        Path path3 = new Path();
        path3.moveTo(this.mDayScoreLists.get(this.mRightIndex).getLineX(), this.mDayScoreLists.get(this.mRightIndex).getMpmY());
        if (this.mRightIndex + 1 == min) {
            canvas.drawCircle(this.mDayScoreLists.get(r4).getLineX(), this.mDayScoreLists.get(this.mRightIndex).getMpmY(), 3.0f, this.mMpmPaint);
        }
        for (int i3 = this.mRightIndex + 1; i3 < min; i3++) {
            path3.lineTo(this.mDayScoreLists.get(i3).getLineX(), this.mDayScoreLists.get(i3).getMpmY());
        }
        canvas.drawPath(path3, this.mMpmPaint);
    }

    private void drawDateLine(Canvas canvas) {
        if (this.mDayScoreLists.size() < 1) {
            return;
        }
        Path path = new Path();
        int min = Math.min(this.mRightIndex + 7, this.mDayScoreLists.size());
        for (int i = this.mRightIndex; i < min; i++) {
            path.moveTo(this.mDayScoreLists.get(i).getLineX(), 0.0f);
            path.lineTo(this.mDayScoreLists.get(i).getLineX(), this.mDayScoreLists.get(i).getLineBottomY());
            canvas.drawPath(path, this.mPaint);
            canvas.drawText(this.mDayScoreLists.get(i).getDateStr(), this.mDayScoreLists.get(i).getFontX(), this.mDayScoreLists.get(i).getFontY(), this.mTextPaint);
            path.reset();
        }
    }

    private void getDateLineX() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < this.mDayScoreLists.size(); i++) {
            DailyScoreBean dailyScoreBean = this.mDayScoreLists.get(i);
            dailyScoreBean.setLineX(this.mInitRightX - (this.mStepX * i));
            dailyScoreBean.setLineBottomY(this.mLineBottomY);
            calendar.setTimeInMillis(dailyScoreBean.getDate().longValue());
            String str = String.valueOf(calendar.get(2) + 1) + "/" + calendar.get(5);
            dailyScoreBean.setDateStr(str);
            dailyScoreBean.setFontX((int) (dailyScoreBean.getLineX() - (this.mTextPaint.measureText(str) / 2.0f)));
            dailyScoreBean.setFontY((int) (Math.abs(this.mTextPaint.descent() - this.mTextPaint.ascent()) + this.mLineBottomY));
        }
        this.mRightIndex = 0;
    }

    private void updateLineX() {
        boolean z = false;
        for (int i = 0; i < this.mDayScoreLists.size(); i++) {
            this.mDayScoreLists.get(i).updateLineX(this.mSlideX);
            if (!z && this.mDayScoreLists.get(i).getLineX() <= this.mInitRightX) {
                this.mRightIndex = i;
                z = true;
            }
        }
        this.mSlideX = 0;
    }

    public int getDateSize() {
        return this.mDayScoreLists.size();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDateLine(canvas);
        drawCpsLine(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        int dp2px = CommonUtil.dp2px(this.mContext, 4.0f) + 32;
        int i5 = this.mWidth;
        this.mInitRightX = i5 - 38;
        this.mLineBottomY = this.mHeight - dp2px;
        this.mStepX = (i5 - 76) / 6;
        getDateLineX();
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
    
        if (r0 != 3) goto L25;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getAction()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L89
            if (r0 == r2) goto L26
            r3 = 2
            if (r0 == r3) goto L12
            r5 = 3
            if (r0 == r5) goto L26
            goto L91
        L12:
            float r0 = r5.getX()
            float r1 = r4.tempLastX
            float r0 = r0 - r1
            int r0 = (int) r0
            r4.mSlideX = r0
            r4.updateLineX()
            float r5 = r5.getX()
            r4.tempLastX = r5
            goto L91
        L26:
            java.util.List<cn.nubia.gamelauncher.bean.DailyScoreBean> r5 = r4.mDayScoreLists
            int r5 = r5.size()
            if (r5 < r2) goto L91
            java.util.List<cn.nubia.gamelauncher.bean.DailyScoreBean> r5 = r4.mDayScoreLists
            java.lang.Object r5 = r5.get(r1)
            cn.nubia.gamelauncher.bean.DailyScoreBean r5 = (cn.nubia.gamelauncher.bean.DailyScoreBean) r5
            int r5 = r5.getLineX()
            int r0 = r4.mInitRightX
            if (r5 >= r0) goto L4d
            java.util.List<cn.nubia.gamelauncher.bean.DailyScoreBean> r5 = r4.mDayScoreLists
            java.lang.Object r5 = r5.get(r1)
            cn.nubia.gamelauncher.bean.DailyScoreBean r5 = (cn.nubia.gamelauncher.bean.DailyScoreBean) r5
            int r5 = r5.getLineX()
            int r0 = r0 - r5
            r4.mSlideX = r0
        L4d:
            int r5 = r4.mRightIndex
            java.util.List<cn.nubia.gamelauncher.bean.DailyScoreBean> r0 = r4.mDayScoreLists
            int r0 = r0.size()
            int r0 = r0 - r2
            if (r5 != r0) goto L81
            java.util.List<cn.nubia.gamelauncher.bean.DailyScoreBean> r5 = r4.mDayScoreLists
            int r0 = r5.size()
            int r0 = r0 - r2
            java.lang.Object r5 = r5.get(r0)
            cn.nubia.gamelauncher.bean.DailyScoreBean r5 = (cn.nubia.gamelauncher.bean.DailyScoreBean) r5
            int r5 = r5.getLineX()
            int r0 = r4.mInitRightX
            if (r5 <= r0) goto L81
            java.util.List<cn.nubia.gamelauncher.bean.DailyScoreBean> r5 = r4.mDayScoreLists
            int r1 = r5.size()
            int r1 = r1 - r2
            java.lang.Object r5 = r5.get(r1)
            cn.nubia.gamelauncher.bean.DailyScoreBean r5 = (cn.nubia.gamelauncher.bean.DailyScoreBean) r5
            int r5 = r5.getLineX()
            int r0 = r0 - r5
            r4.mSlideX = r0
        L81:
            int r5 = r4.mSlideX
            if (r5 == 0) goto L91
            r4.updateLineX()
            goto L91
        L89:
            r4.mSlideX = r1
            float r5 = r5.getX()
            r4.tempLastX = r5
        L91:
            r4.invalidate()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.view.ScoreLineChartView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setDateList(List<DailyScoreBean> list) {
        if (list != null) {
            this.mDayScoreLists.clear();
        }
        this.mDayScoreLists = list;
        getDateLineX();
        postInvalidate();
    }

    public void setSupportPart1(boolean z) {
        this.mSupportPart1 = z;
    }
}
