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
import cn.nubia.gamecenter.settings.gamekeylamp.EffectAdapter;
import cn.nubia.gamecenter.settings.utils.LogUtil;

/* loaded from: classes.dex */
public class SelectEffectPreference extends Preference implements EffectAdapter.EffectClickListener {
    public static final String TAG = "SelectEffectPreference";
    private EffectAdapter mEffectAdapter;

    public SelectEffectPreference(Context context) {
        this(context, null);
    }

    public SelectEffectPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SelectEffectPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SelectEffectPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, R.attr.preferenceStyle), i2);
    }

    public void doUpdate() {
        notifyChanged();
        this.mEffectAdapter.notifyItemChanged(KeyLampHelper.getInstance().getSelectedEffectPosition(), ColorAdapter.PAYLOAD_SELECT_CHANGE);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        LogUtil.i(TAG, "onBindViewHolder");
        if (this.mEffectAdapter == null) {
            RecyclerView recyclerView = (RecyclerView) preferenceViewHolder.findViewById(R.id.effect_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            recyclerView.addItemDecoration(new LampItemDecoration());
            EffectAdapter effectAdapter = new EffectAdapter(this);
            this.mEffectAdapter = effectAdapter;
            recyclerView.setAdapter(effectAdapter);
        }
    }

    @Override // cn.nubia.gamecenter.settings.gamekeylamp.EffectAdapter.EffectClickListener
    public void onItemClick(int i) {
        Log.i(KeyLampHelper.TAG, "SelectEffect --- onItemClick " + i);
        int selectedEffectPosition = KeyLampHelper.getInstance().getSelectedEffectPosition();
        KeyLampHelper.getInstance().onSelectedEffectChange(i);
        this.mEffectAdapter.notifyItemChanged(selectedEffectPosition, ColorAdapter.PAYLOAD_SELECT_CHANGE);
        this.mEffectAdapter.notifyItemChanged(i, ColorAdapter.PAYLOAD_SELECT_CHANGE);
        callChangeListener(Integer.valueOf(i));
    }
}
