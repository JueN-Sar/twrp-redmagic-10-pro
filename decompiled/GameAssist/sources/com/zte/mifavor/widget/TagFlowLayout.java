package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.zte.extres.R;
import com.zte.mifavor.widget.BaseTagAdapter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public class TagFlowLayout extends FlowLayout implements BaseTagAdapter.OnDataChangedListener {
    private static final String KEY_CHOOSE_POS = "key_choose_pos";
    private static final String KEY_DEFAULT = "key_default";
    private static final String TAG = "TagFlowLayout";
    private BaseTagAdapter mBaseTagAdapter;
    private OnSelectListener mOnSelectListener;
    private OnTagClickListener mOnTagClickListener;
    private int mSelectedMax;
    private Set<Integer> mSelectedView;

    public interface OnSelectListener {
        void a(Set set);
    }

    public interface OnTagClickListener {
        boolean a(View view, int i2, FlowLayout flowLayout);
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSelectedMax = -1;
        this.mSelectedView = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.mSelectedMax = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_select, -1);
        this.mMaxLines = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_lines, 100);
        obtainStyledAttributes.recycle();
    }

    private void c() {
        removeAllViews();
        BaseTagAdapter baseTagAdapter = this.mBaseTagAdapter;
        HashSet c2 = baseTagAdapter.c();
        for (final int i2 = 0; i2 < baseTagAdapter.a(); i2++) {
            View d2 = baseTagAdapter.d(this, i2, baseTagAdapter.b(i2));
            final TagView tagView = new TagView(getContext());
            d2.setDuplicateParentStateEnabled(true);
            if (d2.getLayoutParams() != null) {
                tagView.setLayoutParams(d2.getLayoutParams());
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(d(getContext(), 5.0f), d(getContext(), 5.0f), d(getContext(), 5.0f), d(getContext(), 5.0f));
                tagView.setLayoutParams(marginLayoutParams);
            }
            d2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            tagView.addView(d2);
            addView(tagView);
            if (c2.contains(Integer.valueOf(i2))) {
                f(i2, tagView);
            }
            if (this.mBaseTagAdapter.g(i2, baseTagAdapter.b(i2))) {
                f(i2, tagView);
            }
            d2.setClickable(false);
            tagView.setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.widget.TagFlowLayout.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TagFlowLayout.this.e(tagView, i2);
                    if (TagFlowLayout.this.mOnTagClickListener != null) {
                        TagFlowLayout.this.mOnTagClickListener.a(tagView, i2, TagFlowLayout.this);
                    }
                }
            });
        }
        this.mSelectedView.addAll(c2);
    }

    public static int d(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(TagView tagView, int i2) {
        if (tagView.isChecked()) {
            g(i2, tagView);
            this.mSelectedView.remove(Integer.valueOf(i2));
        } else if (this.mSelectedMax == 1 && this.mSelectedView.size() == 1) {
            Integer next = this.mSelectedView.iterator().next();
            g(next.intValue(), (TagView) getChildAt(next.intValue()));
            f(i2, tagView);
            this.mSelectedView.remove(next);
            this.mSelectedView.add(Integer.valueOf(i2));
        } else {
            if (this.mSelectedMax > 0 && this.mSelectedView.size() >= this.mSelectedMax) {
                return;
            }
            f(i2, tagView);
            this.mSelectedView.add(Integer.valueOf(i2));
        }
        OnSelectListener onSelectListener = this.mOnSelectListener;
        if (onSelectListener != null) {
            onSelectListener.a(new HashSet(this.mSelectedView));
        }
    }

    private void f(int i2, TagView tagView) {
        tagView.setChecked(true);
        this.mBaseTagAdapter.e(i2, tagView.getTagView());
    }

    private void g(int i2, TagView tagView) {
        tagView.setChecked(false);
        this.mBaseTagAdapter.h(i2, tagView.getTagView());
    }

    public BaseTagAdapter getAdapter() {
        return this.mBaseTagAdapter;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet(this.mSelectedView);
    }

    @Override // com.zte.mifavor.widget.FlowLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            TagView tagView = (TagView) getChildAt(i4);
            if (tagView.getVisibility() != 8 && tagView.getTagView().getVisibility() == 8) {
                tagView.setVisibility(8);
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        String string = bundle.getString(KEY_CHOOSE_POS);
        if (!TextUtils.isEmpty(string)) {
            for (String str : string.split("\\|")) {
                int parseInt = Integer.parseInt(str);
                this.mSelectedView.add(Integer.valueOf(parseInt));
                TagView tagView = (TagView) getChildAt(parseInt);
                if (tagView != null) {
                    f(parseInt, tagView);
                }
            }
        }
        super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());
        String str = "";
        if (this.mSelectedView.size() > 0) {
            Iterator<Integer> it = this.mSelectedView.iterator();
            while (it.hasNext()) {
                str = str + it.next().intValue() + "|";
            }
            str = str.substring(0, str.length() - 1);
        }
        bundle.putString(KEY_CHOOSE_POS, str);
        return bundle;
    }

    public void setAdapter(BaseTagAdapter baseTagAdapter) {
        this.mBaseTagAdapter = baseTagAdapter;
        baseTagAdapter.f(this);
        this.mSelectedView.clear();
        c();
    }

    public void setMaxLines(int i2) {
        this.mMaxLines = i2;
        invalidate();
    }

    public void setMaxSelectCount(int i2) {
        if (this.mSelectedView.size() > i2) {
            Log.w(TAG, "you has already select more than " + i2 + " views , so it will be clear .");
            this.mSelectedView.clear();
        }
        this.mSelectedMax = i2;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
