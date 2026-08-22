package cn.nubia.gamecenter.settings.gamekeylamp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.gamekeylamp.ColorAdapter;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class SelectColorPreference extends Preference implements ColorAdapter.ColorClickListener {
    public static final String TAG = "SelectColorPreference";
    private ColorAdapter mColorAdapter;
    private Runnable mColorListChangedListener;

    public SelectColorPreference(Context context) {
        this(context, null);
    }

    public SelectColorPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SelectColorPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SelectColorPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, R.attr.preferenceStyle), i2);
    }

    static /* synthetic */ void lambda$onBindViewHolder$0(SelectColorPreference selectColorPreference) {
        ColorAdapter colorAdapter = selectColorPreference.mColorAdapter;
        if (colorAdapter != null) {
            colorAdapter.notifyDataSetChanged();
        }
    }

    static /* synthetic */ void lambda$onBindViewHolder$1(WeakReference weakReference, WeakReference weakReference2) {
        final SelectColorPreference selectColorPreference = (SelectColorPreference) weakReference.get();
        RecyclerView recyclerView = (RecyclerView) weakReference2.get();
        if (selectColorPreference == null || recyclerView == null || selectColorPreference.mColorAdapter == null) {
            return;
        }
        recyclerView.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.SelectColorPreference$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SelectColorPreference.lambda$onBindViewHolder$0(SelectColorPreference.this);
            }
        });
    }

    public void doUpdate() {
        Log.i(TAG, "doUpdate()");
        notifyChanged();
        ColorAdapter colorAdapter = this.mColorAdapter;
        if (colorAdapter != null) {
            colorAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        Log.i(TAG, "onBindViewHolder");
        if (this.mColorAdapter == null) {
            RecyclerView recyclerView = (RecyclerView) preferenceViewHolder.findViewById(R.id.color_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            ColorAdapter colorAdapter = new ColorAdapter(this);
            this.mColorAdapter = colorAdapter;
            recyclerView.setAdapter(colorAdapter);
            final WeakReference weakReference = new WeakReference(this);
            final WeakReference weakReference2 = new WeakReference(recyclerView);
            this.mColorListChangedListener = new Runnable() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.SelectColorPreference$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    SelectColorPreference.lambda$onBindViewHolder$1(weakReference, weakReference2);
                }
            };
            KeyLampHelper.getInstance().addOnColorListChangedListener(this.mColorListChangedListener);
        }
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        Log.i(TAG, "onDetached");
        if (this.mColorListChangedListener != null) {
            KeyLampHelper.getInstance().removeOnColorListChangedListener(this.mColorListChangedListener);
            this.mColorListChangedListener = null;
        }
        super.onDetached();
    }

    @Override // cn.nubia.gamecenter.settings.gamekeylamp.ColorAdapter.ColorClickListener
    public void onItemClick(int i) {
        Log.i(TAG, "onItemClick " + i);
        int selectedColorPosition = KeyLampHelper.getInstance().getSelectedColorPosition();
        KeyLampHelper.getInstance().onSelectedColorChange(i);
        this.mColorAdapter.notifyItemChanged(i, ColorAdapter.PAYLOAD_SELECT_CHANGE);
        this.mColorAdapter.notifyItemChanged(selectedColorPosition, ColorAdapter.PAYLOAD_SELECT_CHANGE);
        callChangeListener(Integer.valueOf(i));
    }
}
