package cn.nubia.gameassist.plugin.panel;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.panel.GameAssistWindowManager;

/* loaded from: classes.dex */
public class CardPluginMode extends BasePluginMode {
    public CardPluginMode(Context context, GameAssistWindowManager gameAssistWindowManager) {
        super(context, gameAssistWindowManager);
        this.f7233b = new PluginTilesAdapter(context, 1);
    }

    @Override // cn.nubia.gameassist.plugin.panel.BasePluginMode
    public void b(boolean z, View view, int i2) {
        this.f7239h = z;
        this.f7234c = (RecyclerView) view.findViewById(R.id.game_assist_plugins_card);
        this.f7235d = a(1);
        this.f7233b.W(1);
        super.b(z, view, i2);
    }
}
