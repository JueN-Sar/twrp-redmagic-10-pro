package cn.nubia.common.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import cn.nubia.common.R;
import cn.nubia.common.util.CommonUtil;

/* loaded from: classes.dex */
public class SimpleEditImageView extends ImageView {
    private static final int EDGE_EXTEND = 0;
    private static final float MAX_SCALE = 5.0f;
    private static final int MODE_DRAG = 1;
    private static final int MODE_NONE = 0;
    private static final int MODE_ZOOM = 2;
    String TAG;
    private Bitmap mBitmap;
    private int mBitmapHeightOffSet;
    private float mBitmapStartX;
    private float mBitmapStartY;
    private int mBitmapWidthOffSet;
    private float mClippingBoxBottomEdge;
    private float mClippingBoxLeftEdge;
    private float mClippingBoxRightEdge;
    private float mClippingBoxTopEdge;
    private Context mContext;
    private Rect mCropRect;
    private Matrix mCurrentMatrix;
    private boolean mEnableTouch;
    private boolean mIsCropWallpaer;
    private PointF mMiddleF;
    private RectF mRectF;
    private Matrix mSavedMatrix;
    private PointF mStartF;
    private float mStartScale;
    private int mode;
    private float oldDis;

    public SimpleEditImageView(Context context) {
        this(context, null);
    }

    public SimpleEditImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStartF = new PointF();
        this.mMiddleF = new PointF();
        this.oldDis = 1.0f;
        this.mStartScale = -1.0f;
        this.mode = 0;
        this.mEnableTouch = true;
        this.mIsCropWallpaer = false;
        this.TAG = "GSWallpaper";
        this.mContext = context;
        this.mCurrentMatrix = new Matrix();
        this.mSavedMatrix = new Matrix();
        this.mBitmapHeightOffSet = getResources().getDimensionPixelOffset(R.dimen.bitmap_height_offset);
        DisplayMetrics displayMetrics = displayMetrics(this.mContext);
        if (this.mCropRect == null) {
            this.mCropRect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
    }

    private float calDistance(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private PointF calMidPoint(MotionEvent motionEvent) {
        this.mRectF = getRectF();
        PointF matrixBitmapPointF = getMatrixBitmapPointF();
        return new PointF(this.mRectF.left + ((matrixBitmapPointF == null ? 0.0f : matrixBitmapPointF.x) / 2.0f), this.mRectF.top + ((matrixBitmapPointF != null ? matrixBitmapPointF.y : 0.0f) / 2.0f));
    }

    private float calRotation(MotionEvent motionEvent) {
        return (float) Math.toDegrees(Math.atan2(motionEvent.getY(0) - motionEvent.getY(1), motionEvent.getX(0) - motionEvent.getX(1)));
    }

    private DisplayMetrics displayMetrics(Context context) {
        Display defaultDisplay = ((Activity) this.mContext).getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            return displayMetrics;
        } catch (Exception e) {
            DisplayMetrics displayMetrics2 = getResources().getDisplayMetrics();
            e.printStackTrace();
            return displayMetrics2;
        }
    }

    private float getDisX(MotionEvent motionEvent, int i) {
        float x = motionEvent.getX() - this.mStartF.x;
        float f = this.mRectF.left;
        float f2 = this.mRectF.left + i;
        if (x > 0.0f) {
            float f3 = f + x;
            float f4 = this.mClippingBoxLeftEdge;
            if (f3 > f4) {
                return f4 - f;
            }
        }
        if (x >= 0.0f) {
            return x;
        }
        float f5 = f2 + x;
        float f6 = this.mClippingBoxRightEdge;
        return f5 < f6 ? f6 - f2 : x;
    }

    private float getDisY(MotionEvent motionEvent, int i) {
        float y = motionEvent.getY() - this.mStartF.y;
        float f = this.mRectF.top;
        float f2 = this.mRectF.top + i;
        if (y > 0.0f) {
            float f3 = f + y;
            float f4 = this.mClippingBoxTopEdge;
            if (f3 > f4) {
                return f4 - f;
            }
        }
        if (y >= 0.0f) {
            return y;
        }
        float f5 = f2 + y;
        float f6 = this.mClippingBoxBottomEdge;
        return f5 < f6 ? f6 - f2 : y;
    }

    private RectF getRectF() {
        RectF rectF = new RectF();
        this.mCurrentMatrix.mapRect(rectF);
        return rectF;
    }

    private float getScale(float f) {
        float scaleXByMatrix = CommonUtil.getScaleXByMatrix(getImageMatrix());
        float f2 = this.mStartScale;
        return (scaleXByMatrix * f) / f2 >= MAX_SCALE ? Math.max(1.0f, (f2 * MAX_SCALE) / scaleXByMatrix) : Math.min(f, MAX_SCALE);
    }

    private float getZoomScale(float f, float f2) {
        this.mRectF = getRectF();
        PointF matrixBitmapPointF = getMatrixBitmapPointF();
        float f3 = f / f2;
        float max = Math.max(this.mMiddleF.y - this.mClippingBoxTopEdge, this.mClippingBoxBottomEdge - this.mMiddleF.y) / (matrixBitmapPointF.y / 2.0f);
        if (f3 < max) {
            f3 = max;
        }
        float max2 = Math.max(this.mMiddleF.x - this.mClippingBoxLeftEdge, this.mClippingBoxRightEdge - this.mMiddleF.x) / (matrixBitmapPointF.x / 2.0f);
        if (f3 < max2) {
            f3 = max2;
        }
        getResources().getDisplayMetrics();
        return f3 > 1.0f ? getScale(f3) : f3;
    }

    private void initBitmapPosition(Bitmap bitmap) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Context context = this.mContext;
        if (context instanceof Activity) {
            displayMetrics = displayMetrics(context);
        }
        this.mBitmapStartX = (displayMetrics.widthPixels - bitmap.getWidth()) / 2;
        this.mBitmapStartY = ((displayMetrics.heightPixels - bitmap.getHeight()) / 2) - this.mBitmapHeightOffSet;
        float max = Math.max(this.mCropRect.height() / bitmap.getHeight(), this.mCropRect.width() / bitmap.getWidth());
        Matrix matrix = new Matrix();
        this.mCurrentMatrix = matrix;
        matrix.setTranslate(this.mBitmapStartX, this.mBitmapStartY);
        this.mCurrentMatrix.postScale(max, max, displayMetrics.widthPixels / 2, (displayMetrics.heightPixels / 2) - this.mBitmapHeightOffSet);
        float width = (displayMetrics.widthPixels - this.mCropRect.width()) / 2.0f;
        this.mClippingBoxLeftEdge = width;
        this.mClippingBoxRightEdge = width + this.mCropRect.width();
        float height = ((displayMetrics.heightPixels - this.mCropRect.height()) / 2.0f) - this.mBitmapHeightOffSet;
        this.mClippingBoxTopEdge = height;
        this.mClippingBoxBottomEdge = height + this.mCropRect.height();
        setImageMatrix(this.mCurrentMatrix);
    }

    private void initStartScaleIfNeed() {
        if (this.mStartScale > 0.0f || this.mBitmap == null) {
            return;
        }
        this.mStartScale = CommonUtil.getScaleXByMatrix(getImageMatrix());
    }

    public Matrix getCurrentMatrix() {
        return this.mCurrentMatrix;
    }

    public boolean getEnable() {
        return this.mEnableTouch;
    }

    public Bitmap getMatrixBitmap() {
        Bitmap bitmap = this.mBitmap;
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.mBitmap.getHeight(), this.mCurrentMatrix, true);
    }

    public PointF getMatrixBitmapPointF() {
        if (this.mBitmap == null) {
            return null;
        }
        float[] fArr = new float[8];
        this.mCurrentMatrix.mapPoints(fArr, new float[]{0.0f, 0.0f, r0.getWidth(), 0.0f, 0.0f, this.mBitmap.getHeight(), this.mBitmap.getWidth(), this.mBitmap.getHeight()});
        Path path = new Path();
        path.moveTo(fArr[0], fArr[1]);
        path.lineTo(fArr[2], fArr[3]);
        path.lineTo(fArr[6], fArr[7]);
        path.lineTo(fArr[4], fArr[5]);
        path.close();
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        return new PointF(rectF.width(), rectF.height());
    }

    public boolean isCropWallpaper() {
        return this.mIsCropWallpaer;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mEnableTouch) {
            return true;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.mSavedMatrix.set(this.mCurrentMatrix);
            this.mStartF.set(motionEvent.getX(), motionEvent.getY());
            this.mode = 1;
            initStartScaleIfNeed();
        } else if (action == 1) {
            this.mode = 0;
        } else if (action == 2) {
            this.mCurrentMatrix.set(this.mSavedMatrix);
            PointF matrixBitmapPointF = getMatrixBitmapPointF();
            if (matrixBitmapPointF == null) {
                return false;
            }
            int i = (int) matrixBitmapPointF.x;
            int i2 = (int) matrixBitmapPointF.y;
            int i3 = this.mode;
            if (i3 == 1) {
                this.mRectF = getRectF();
                this.mCurrentMatrix.postTranslate(getDisX(motionEvent, i), getDisY(motionEvent, i2));
            } else if (i3 == 2 && motionEvent.getPointerCount() == 2) {
                float zoomScale = getZoomScale(calDistance(motionEvent), this.oldDis);
                this.mCurrentMatrix.postScale(zoomScale, zoomScale, this.mMiddleF.x, this.mMiddleF.y);
            }
        } else if (action == 5) {
            float calDistance = calDistance(motionEvent);
            this.oldDis = calDistance;
            if (calDistance > 10.0f) {
                this.mSavedMatrix.set(this.mCurrentMatrix);
                this.mMiddleF = calMidPoint(motionEvent);
                this.mode = 2;
            }
        } else if (action == 6) {
            this.mSavedMatrix.set(this.mCurrentMatrix);
            if (motionEvent.getActionIndex() == 0) {
                this.mStartF.set(motionEvent.getX(1), motionEvent.getY(1));
            } else if (motionEvent.getActionIndex() == 1) {
                this.mStartF.set(motionEvent.getX(0), motionEvent.getY(0));
            }
            this.mode = 1;
        }
        setImageMatrix(this.mCurrentMatrix);
        return true;
    }

    public void setBitmap(Bitmap bitmap) {
        Log.d(this.TAG, "setBitmap() bm.w : " + bitmap.getWidth() + ", bm.h : " + bitmap.getHeight());
        this.mBitmap = Bitmap.createBitmap(bitmap);
        setImageMatrix(null);
        setImageBitmap(this.mBitmap);
        initBitmapPosition(this.mBitmap);
    }

    public void setCropRect(Rect rect) {
        this.mCropRect = rect;
        Log.d(this.TAG, "setCropRect() rect : " + rect);
    }

    public void setCropWallpaperFlag(boolean z) {
        this.mIsCropWallpaer = z;
        Log.d(this.TAG, "setCropWallpaperFlag() mIsCropWallpaer : " + this.mIsCropWallpaer);
    }

    public void setEnable(boolean z) {
        this.mEnableTouch = z;
    }

    @Override // android.widget.ImageView
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
    }
}
