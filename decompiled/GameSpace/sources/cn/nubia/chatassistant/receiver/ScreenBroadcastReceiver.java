package cn.nubia.chatassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cn.nubia.chatassistant.util.AudioRecorderUtils;
import cn.nubia.chatassistant.util.LogUtils;

/* loaded from: classes.dex */
public class ScreenBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "ScreenBroadcastReceiver";
    private static AudioRecorderUtils mAudioRecorderUtils;
    private static ScreenBroadcastReceiver mScreenBroadcastReceiver;

    public static void registerScreenBroadcast(Context context, AudioRecorderUtils audioRecorderUtils) {
        try {
            LogUtils.i("ScreenBroadcastReceiverregisterScreenBroadcast");
            mAudioRecorderUtils = audioRecorderUtils;
            mScreenBroadcastReceiver = new ScreenBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            context.getApplicationContext().registerReceiver(mScreenBroadcastReceiver, intentFilter, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterScreenBroadcast(Context context) {
        try {
            LogUtils.i("ScreenBroadcastReceiverunregisterScreenBroadcast");
            mAudioRecorderUtils = null;
            if (mScreenBroadcastReceiver != null) {
                context.getApplicationContext().unregisterReceiver(mScreenBroadcastReceiver);
                mScreenBroadcastReceiver = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        AudioRecorderUtils audioRecorderUtils;
        try {
            String action = intent.getAction();
            LogUtils.i("ScreenBroadcastReceiverAction = " + action);
            if (!action.equals("android.intent.action.SCREEN_OFF") || (audioRecorderUtils = mAudioRecorderUtils) == null) {
                return;
            }
            audioRecorderUtils.stopRecordAndFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
