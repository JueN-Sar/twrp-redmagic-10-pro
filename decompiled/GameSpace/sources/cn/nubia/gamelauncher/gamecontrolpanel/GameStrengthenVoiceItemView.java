package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class GameStrengthenVoiceItemView extends LinearLayout {
    private TextView vVoiceDesc;
    private LinearLayout vVoiceLayout;
    private TextView vVoiceType;

    public GameStrengthenVoiceItemView(Context context) {
        this(context, null);
    }

    public GameStrengthenVoiceItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameStrengthenVoiceItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(attributeSet);
        initData();
    }

    private void initData() {
        int id = getId();
        if (id == R.id.nubia_game_strength_voice_default) {
            this.vVoiceType.setText(getContext().getString(R.string.nubia_game_voice_strengthen_default));
            this.vVoiceDesc.setText(getContext().getString(R.string.nubia_game_voice_strengthen_default_desc));
            return;
        }
        if (id == R.id.nubia_game_strength_voice_shoot) {
            this.vVoiceType.setText(getContext().getString(R.string.nubia_game_voice_strengthen_shoot));
            this.vVoiceDesc.setText(getContext().getString(R.string.nubia_game_voice_strengthen_shoot_desc));
        } else if (id == R.id.nubia_game_strength_voice_music) {
            this.vVoiceType.setText(getContext().getString(R.string.nubia_game_voice_strengthen_music));
            this.vVoiceDesc.setText(getContext().getString(R.string.nubia_game_voice_strengthen_music_desc));
        } else if (id == R.id.nubia_game_strength_voice_movie) {
            this.vVoiceType.setText(getContext().getString(R.string.nubia_game_voice_strengthen_movie));
            this.vVoiceDesc.setText(getContext().getString(R.string.nubia_game_voice_strengthen_movie_desc));
        }
    }

    private void initView(AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.nubia_game_strengthen_view_voice_item_port : R.layout.nubia_game_strengthen_view_voice_item, this);
        this.vVoiceType = (TextView) findViewById(R.id.nubia_game_strength_voice_type);
        this.vVoiceDesc = (TextView) findViewById(R.id.nubia_game_strength_voice_desc);
        this.vVoiceLayout = (LinearLayout) findViewById(R.id.nubia_game_strength_voice_layout);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        this.vVoiceLayout.setBackgroundResource(i);
    }

    public void setChecked(boolean z) {
        this.vVoiceLayout.setSelected(z);
    }
}
