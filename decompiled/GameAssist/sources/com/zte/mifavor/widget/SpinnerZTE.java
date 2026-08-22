package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class SpinnerZTE extends Spinner {
    private final String TAG;
    private final Handler mHandler;

    public SpinnerZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.spinnerStyle);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOnTouchListener(new View.OnTouchListener() { // from class: com.zte.mifavor.widget.SpinnerZTE.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 1) {
                    return false;
                }
                Log.d("SpinnerZTE", "onTouch in, action = " + action);
                SpinnerZTE.this.mHandler.postDelayed(new Runnable() { // from class: com.zte.mifavor.widget.SpinnerZTE.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SpinnerZTE spinnerZTE = SpinnerZTE.this;
                        spinnerZTE.setSpinnerPopupListener(spinnerZTE);
                    }
                }, 200L);
                return false;
            }
        });
    }

    public void setSpinnerPopupListener(Spinner spinner) {
        try {
            Field declaredField = Spinner.class.getDeclaredField("mPopup");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(spinner);
            if (obj instanceof ListPopupWindow) {
                android.widget.ListView listView = ((ListPopupWindow) obj).getListView();
                if (listView != null) {
                    listView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.zte.mifavor.widget.SpinnerZTE.2
                        @Override // android.view.ViewOutlineProvider
                        public void getOutline(View view, Outline outline) {
                            outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), com.zte.mifavor.utils.Utils.c(SpinnerZTE.this.getContext(), 16.0f));
                        }
                    });
                    listView.setClipToOutline(true);
                    Log.d("SpinnerZTE", "setSpinnerPopupListener  listView.set Outline Provider.");
                } else {
                    Log.w("SpinnerZTE", "setSpinnerPopupListener error.  listView is null.");
                }
            } else {
                Log.w("SpinnerZTE", "setSpinnerPopupListener error. popupObj=" + obj);
            }
        } catch (Exception e2) {
            Log.e("SpinnerZTE", "setSpinnerPopupListener error, e=", e2);
        }
    }

    public SpinnerZTE(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, -1);
    }

    public SpinnerZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, 0, i3);
    }

    public SpinnerZTE(Context context, AttributeSet attributeSet, int i2, int i3, int i4) {
        this(context, attributeSet, i2, i3, i4, null);
    }

    public SpinnerZTE(Context context, AttributeSet attributeSet, int i2, int i3, int i4, Resources.Theme theme) {
        super(context, attributeSet, i2, i3, i4, theme);
        this.TAG = "SpinnerZTE";
        this.mHandler = new Handler();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.entries}, i2, i3);
        CharSequence[] textArray = obtainStyledAttributes.getTextArray(0);
        if (textArray != null) {
            ArrayAdapterZTE arrayAdapterZTE = new ArrayAdapterZTE(context, com.zte.extres.R.layout.spinner_item, textArray);
            arrayAdapterZTE.setDropDownViewResource(com.zte.extres.R.layout.simple_spinner_dropdown_item);
            setAdapter((SpinnerAdapter) arrayAdapterZTE);
        }
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
        obtainStyledAttributes.recycle();
    }
}
