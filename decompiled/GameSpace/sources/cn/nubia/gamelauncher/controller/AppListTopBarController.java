package cn.nubia.gamelauncher.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.activity.UninstallActivity;

/* loaded from: classes.dex */
public class AppListTopBarController {
    private TextView mUninstallText = null;
    private Context mContext = null;
    private ImageView mReturnBtn = null;

    /* JADX INFO: Access modifiers changed from: private */
    public void startUninstallActivity() {
        this.mContext.startActivity(new Intent(this.mContext, (Class<?>) UninstallActivity.class));
    }

    public void init(final Activity activity) {
        this.mContext = activity.getApplicationContext();
        ImageView imageView = (ImageView) activity.findViewById(R.id.left_icon);
        this.mReturnBtn = imageView;
        imageView.setClickable(true);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.AppListTopBarController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                activity.finish();
            }
        });
        TextView textView = (TextView) activity.findViewById(R.id.app_select_btn);
        this.mUninstallText = textView;
        textView.setClickable(true);
        this.mUninstallText.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.controller.AppListTopBarController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppListTopBarController.this.startUninstallActivity();
            }
        });
    }
}
