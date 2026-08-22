package cn.nubia.gamecenter.settings.gamekeylamp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.gamekeylamp.EffectAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class EffectAdapter extends RecyclerView.Adapter {
    private final EffectClickListener mClickListener;

    public interface EffectClickListener {
        void onItemClick(int i);
    }

    class EffectHolder extends RecyclerView.ViewHolder {
        public EffectViewLinearLayout mLampEffect;

        public EffectHolder(View view) {
            super(view);
            EffectViewLinearLayout effectViewLinearLayout = (EffectViewLinearLayout) view.findViewById(R.id.lamp_effect);
            this.mLampEffect = effectViewLinearLayout;
            effectViewLinearLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.gamekeylamp.EffectAdapter$EffectHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    EffectAdapter.EffectHolder.this.m207xce6e47b6(view2);
                }
            });
        }

        /* renamed from: lambda$new$0$cn-nubia-gamecenter-settings-gamekeylamp-EffectAdapter$EffectHolder, reason: not valid java name */
        /* synthetic */ void m207xce6e47b6(View view) {
            EffectViewLinearLayout effectViewLinearLayout = (EffectViewLinearLayout) view;
            effectViewLinearLayout.setPaintColor(KeyLampHelper.getInstance().getSelectedColors());
            effectViewLinearLayout.setSelected(true);
            int intValue = ((Integer) effectViewLinearLayout.getTag()).intValue();
            if (KeyLampHelper.getInstance().isSelectedEffect(intValue) || EffectAdapter.this.mClickListener == null) {
                return;
            }
            EffectAdapter.this.mClickListener.onItemClick(intValue);
        }
    }

    public EffectAdapter(EffectClickListener effectClickListener) {
        this.mClickListener = effectClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return KeyLampHelper.getInstance().getEffects().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        EffectViewLinearLayout effectViewLinearLayout = ((EffectHolder) viewHolder).mLampEffect;
        Effect effectByPosition = KeyLampHelper.getInstance().getEffectByPosition(i);
        effectViewLinearLayout.setTag(Integer.valueOf(i));
        effectViewLinearLayout.setEffectType(effectByPosition);
        effectViewLinearLayout.setPaintColor(KeyLampHelper.getInstance().getSelectedColors());
        effectViewLinearLayout.setSelected(KeyLampHelper.getInstance().isSelectedEffect(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        EffectHolder effectHolder = (EffectHolder) viewHolder;
        if (ColorAdapter.PAYLOAD_SELECT_CHANGE.equals((String) list.get(0))) {
            EffectViewLinearLayout effectViewLinearLayout = effectHolder.mLampEffect;
            effectViewLinearLayout.setPaintColor(KeyLampHelper.getInstance().getSelectedColors());
            effectViewLinearLayout.setSelected(KeyLampHelper.getInstance().isSelectedEffect(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new EffectHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lamp_effect_item, viewGroup, false));
    }
}
