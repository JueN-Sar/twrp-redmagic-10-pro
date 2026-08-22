package cn.nubia.gamecenter.settings.basic;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.settings.trackclient.NubiaTrackManager;

/* loaded from: classes.dex */
public class GameChickenModeActivity extends FragmentActivity {
    private void addFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.main, new GameChickenModeFragment(), "GameChickenModeFragment");
        beginTransaction.commit();
    }

    private void initActionBar() {
        findViewById(R.id.game_chicken_mode_action_bar).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.basic.GameChickenModeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameChickenModeActivity.this.onBackPressed();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.gcs_activity_game_chicken_mode);
        initActionBar();
        NubiaTrackManager.getInstance().init(getApplicationContext());
        addFragment();
    }
}
