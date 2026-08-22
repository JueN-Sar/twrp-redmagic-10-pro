package cn.nubia.gamecenter.settings.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class HistogramChartView extends View {
    private static final int COLOR_BG = -16711936;
    private static final String TAG = "zhb";
    private List<String> listNear7Days;
    private List<Float> listNumber;
    private Context mContext;
    private int mHeight;
    private Paint mPaint;
    private int mWidth;
    private int paintColors;

    public HistogramChartView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public HistogramChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.mContext = context;
    }

    public HistogramChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listNumber = new ArrayList();
        this.listNear7Days = new ArrayList();
        this.mContext = context;
        init();
    }

    private void drawHistogramChart7Days(Canvas canvas) {
        this.mPaint.setColor(Color.parseColor("#80FFFFFF"));
        this.mPaint.setStyle(Paint.Style.FILL);
        int i = (this.mWidth - 240) / 6;
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.seven_days_hours_buttom_delta);
        int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.seven_days_hours_text_height);
        int dimension3 = (int) this.mContext.getResources().getDimension(R.dimen.seven_days_name_each_base_width);
        int i2 = 0;
        while (i2 < this.listNumber.size()) {
            int i3 = (i2 * i) + 120;
            int floatValue = (this.mHeight - dimension) - ((int) ((((r10 - dimension) - dimension2) * this.listNumber.get(i2).floatValue()) / 24.0f));
            int i4 = i3 + dimension3;
            float f = i3;
            float f2 = floatValue;
            float f3 = i4;
            float f4 = this.mHeight - dimension;
            int i5 = i;
            int i6 = dimension;
            this.mPaint.setShader(new LinearGradient(f, f2, f3, f4, new int[]{-8021807, -13814971}, (float[]) null, Shader.TileMode.CLAMP));
            this.mPaint.setShadowLayer(2.01f, 2.0f, 2.0f, 452984831);
            canvas.drawRect(new RectF(f, f2, f3, f4), this.mPaint);
            this.mPaint.setShader(null);
            this.mPaint.setTextSize(36.0f);
            this.mPaint.setColor(Color.parseColor("#80FFFFFF"));
            if (this.listNumber.get(i2).floatValue() == 0.0f) {
                canvas.drawText("0h", r8 + 104, floatValue - dimension2, this.mPaint);
            } else {
                canvas.drawText(String.valueOf(this.listNumber.get(i2)) + "h", r8 + 104, floatValue - dimension2, this.mPaint);
            }
            i2++;
            i = i5;
            dimension = i6;
        }
    }

    private void drawText7DaysName(Canvas canvas) {
        this.mPaint.setColor(Color.parseColor("#D9FFFFFF"));
        this.listNear7Days.size();
        int i = (this.mWidth - 240) / 6;
        int dimension = this.mHeight - ((int) this.mContext.getResources().getDimension(R.dimen.seven_days_name_text_draw_buttom_delta));
        for (int i2 = 0; i2 < this.listNear7Days.size(); i2++) {
            this.mPaint.setColor(Color.parseColor("#D9FFFFFF"));
            this.mPaint.setTextSize(36.0f);
            canvas.drawText(this.listNear7Days.get(i2), (i * i2) + 104, dimension, this.mPaint);
        }
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    private void get7DaysNamesFromNow() {
        Calendar calendar = Calendar.getInstance();
        LogUtil.d(TAG, (calendar.get(2) + 1) + "." + calendar.get(5));
        this.listNear7Days.add(String.valueOf(calendar.get(2) + 1) + "." + String.valueOf(calendar.get(5)));
        for (int i = 0; i < 6; i++) {
            calendar.add(5, -1);
            LogUtil.d(TAG, (calendar.get(2) + 1) + "." + calendar.get(5));
            this.listNear7Days.add(String.valueOf(calendar.get(2) + 1) + "." + String.valueOf(calendar.get(5)));
        }
        Collections.reverse(this.listNear7Days);
    }

    private void init() {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStrokeWidth(2.0f);
        this.mPaint.setColor(Color.parseColor("#D9FFFFFF"));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.listNumber.size() > 0) {
            drawHistogramChart7Days(canvas);
        }
        this.mPaint.setShader(null);
        drawText7DaysName(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
    }

    public void setData(List<Float> list, int i) {
        this.listNumber.clear();
        this.listNumber.addAll(list);
        this.paintColors = i;
        postInvalidate();
        get7DaysNamesFromNow();
    }
}
