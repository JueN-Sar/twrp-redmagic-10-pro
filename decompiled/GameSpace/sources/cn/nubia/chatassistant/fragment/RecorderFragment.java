package cn.nubia.chatassistant.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import cn.nubia.chatassistant.bean.ChatAssistantVoiceBean;
import cn.nubia.chatassistant.customchat.DeleteDialog;
import cn.nubia.chatassistant.receiver.ScreenBroadcastReceiver;
import cn.nubia.chatassistant.ui.RecorderSoundLayout;
import cn.nubia.chatassistant.util.AudioRecorderUtils;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.MusicManagerUtils;
import cn.nubia.chatassistant.util.ToastUtils;
import cn.nubia.gamelauncher.R;
import java.io.File;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class RecorderFragment extends BaseFragment implements View.OnTouchListener, AudioRecorderUtils.OnDecibelListener {
    private static final int CLOSE_REQUEST_CODE = 667;
    private static final int REVERT_REQUEST_CODE = 666;
    private static final String TAG = "RecorderFragment";
    private ChatAssistantVoiceBean chatAssistantVoiceBean;
    private View closeButton;
    private TextView editErrorText;
    private ImageView hintButton;
    private TextView mCommit;
    private TextView mComplateBtn;
    private View mFinishRecorderLayout;
    private View mHintLayout;
    private EditText mInputName;
    private TextView mRecorderButton;
    private View mRenameRecorderLayout;
    private View mRevertStart;
    private View mStarRecorderLayout;
    private Timer mTimer;
    private RecorderSoundLayout recorderSoundLayout;
    private TextView recorderTimeText;
    private ImageView soundIcon;
    private TextView startOrStopButton;
    private AudioRecorderUtils audioRecorderUtils = AudioRecorderUtils.getInstance();
    private int recorderTime = 0;
    private int recordResult = 0;

    static /* synthetic */ int access$308(RecorderFragment recorderFragment) {
        int i = recorderFragment.recorderTime;
        recorderFragment.recorderTime = i + 1;
        return i;
    }

    private void refreshRecorderTime() {
        LogUtils.d(TAG, "refreshRecorderTime :");
        if (getActivity() == null) {
            return;
        }
        this.recorderTime = 0;
        this.recorderTimeText.setText(this.recorderTime + "”");
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        this.mTimer = timer2;
        timer2.schedule(new TimerTask() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.8
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                RecorderFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RecorderFragment.access$308(RecorderFragment.this);
                        RecorderFragment.this.recorderTimeText.setText(RecorderFragment.this.recorderTime + "”");
                        if (RecorderFragment.this.recorderTime >= 10) {
                            RecorderFragment.this.stopRecord();
                        }
                    }
                });
            }
        }, 1000L, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRecord() {
        LogUtils.d(TAG, "stopRecord :");
        this.mRecorderButton.setAlpha(1.0f);
        this.recorderTimeText.setVisibility(8);
        this.recorderSoundLayout.setVisibility(8);
        this.audioRecorderUtils.stopRecordAndFile();
        textStopRecord(this.recorderTime + "", AudioRecorderUtils.getWavFilePath(getContext()));
    }

    private void textStopRecord(String str, String str2) {
        LogUtils.i(TAG, "textStopRecord time : " + str + " ,path : " + str2);
        int parseInt = Integer.parseInt(str);
        if (this.chatAssistantVoiceBean == null) {
            this.chatAssistantVoiceBean = new ChatAssistantVoiceBean("");
        }
        this.chatAssistantVoiceBean.setVoiceFilePath(str2);
        this.chatAssistantVoiceBean.setTime(parseInt);
        this.chatAssistantVoiceBean.setSystemDefault(false);
        if (this.recordResult == 2) {
            ToastUtils.showToast(getContext(), getResources().getString(R.string.revert_recorder), 0);
        } else if (parseInt < 2) {
            ToastUtils.showToast(getContext(), getResources().getString(R.string.recorder_short_time), 0);
        } else {
            this.mFinishRecorderLayout.setVisibility(0);
            this.mStarRecorderLayout.setVisibility(8);
            this.startOrStopButton.setText(this.recorderTime + "”");
        }
        this.mRecorderButton.setAlpha(1.0f);
        this.recorderTimeText.setVisibility(8);
        this.recorderSoundLayout.setVisibility(8);
        ScreenBroadcastReceiver.unregisterScreenBroadcast(getContext());
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override // cn.nubia.chatassistant.fragment.BaseFragment
    public void initView() {
        this.mRecorderButton = (TextView) findViewById(R.id.recorder_button);
        this.mStarRecorderLayout = findViewById(R.id.start_layout);
        View findViewById = findViewById(R.id.select_finish_Layout);
        this.mFinishRecorderLayout = findViewById;
        findViewById.setVisibility(8);
        View findViewById2 = findViewById(R.id.rename_layout);
        this.mRenameRecorderLayout = findViewById2;
        findViewById2.setVisibility(8);
        this.mRevertStart = findViewById(R.id.btn_rev_start);
        this.mComplateBtn = (TextView) findViewById(R.id.complate_btn);
        this.mInputName = (EditText) findViewById(R.id.input_name);
        this.editErrorText = (TextView) findViewById(R.id.edit_error_text);
        this.soundIcon = (ImageView) findViewById(R.id.sound_log);
        this.recorderTimeText = (TextView) findViewById(R.id.recorder_time_layout);
        this.recorderSoundLayout = (RecorderSoundLayout) findViewById(R.id.recorder_sound_layout);
        this.startOrStopButton = (TextView) findViewById(R.id.scan_c);
        this.mHintLayout = findViewById(R.id.hint_layout);
        ImageView imageView = (ImageView) findViewById(R.id.hint_btn);
        this.hintButton = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecorderFragment.this.mHintLayout.setVisibility(0);
                RecorderFragment.this.mHintLayout.postDelayed(new Runnable() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RecorderFragment.this.mHintLayout.setVisibility(8);
                    }
                }, 4000L);
            }
        });
        findViewById(R.id.recorder_complete_sound_layout).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MusicManagerUtils.getInstance().isPlay()) {
                    return;
                }
                MusicManagerUtils.getInstance().startPlay(RecorderFragment.this.chatAssistantVoiceBean.getVoiceFilePath(), new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.2.1
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                    }
                });
                RecorderFragment recorderFragment = RecorderFragment.this;
                recorderFragment.startAnimation(recorderFragment.soundIcon, RecorderFragment.this.recorderTime - 1);
            }
        });
        this.mComplateBtn.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.3
            /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
                	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
                	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
                */
            @Override // android.view.View.OnClickListener
            public void onClick(android.view.View r8) {
                /*
                    Method dump skipped, instructions count: 506
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.nubia.chatassistant.fragment.RecorderFragment.AnonymousClass3.onClick(android.view.View):void");
            }
        });
        this.mComplateBtn.setClickable(false);
        this.mInputName.addTextChangedListener(new TextWatcher() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                RecorderFragment.this.editErrorText.setVisibility(8);
                if (editable.toString().length() > 0) {
                    RecorderFragment.this.mComplateBtn.setAlpha(1.0f);
                    RecorderFragment.this.mComplateBtn.setClickable(true);
                } else {
                    RecorderFragment.this.mComplateBtn.setAlpha(0.5f);
                    RecorderFragment.this.mComplateBtn.setClickable(false);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mRevertStart.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(RecorderFragment.this.getContext(), (Class<?>) DeleteDialog.class);
                intent.putExtra("text", RecorderFragment.this.getResources().getString(R.string.recorder_delete_text));
                RecorderFragment.this.startActivityForResult(intent, RecorderFragment.REVERT_REQUEST_CODE);
            }
        });
        TextView textView = (TextView) findViewById(R.id.btn_com);
        this.mCommit = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecorderFragment.this.recorderTime > 10) {
                    ToastUtils.showToast(RecorderFragment.this.getContext(), RecorderFragment.this.getResources().getString(R.string.recorder_error_text), 0);
                    return;
                }
                RecorderFragment.this.mFinishRecorderLayout.setVisibility(8);
                RecorderFragment.this.mRenameRecorderLayout.setVisibility(0);
                RecorderFragment.this.hintButton.setVisibility(8);
            }
        });
        this.audioRecorderUtils.setOnDecibelListener(this);
        this.mRecorderButton.setOnTouchListener(this);
        View findViewById3 = findViewById(R.id.close_btn);
        this.closeButton = findViewById3;
        findViewById3.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecorderFragment.this.mFinishRecorderLayout.getVisibility() != 0 && RecorderFragment.this.mRenameRecorderLayout.getVisibility() != 0) {
                    RecorderFragment.this.getActivity().finish();
                    return;
                }
                Intent intent = new Intent(RecorderFragment.this.getContext(), (Class<?>) DeleteDialog.class);
                intent.putExtra("text", RecorderFragment.this.getResources().getString(R.string.recorder_delete_text));
                RecorderFragment.this.startActivityForResult(intent, RecorderFragment.CLOSE_REQUEST_CODE);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtils.d(TAG, "onActivityResult : requestCode : " + i);
        if (REVERT_REQUEST_CODE == i && i2 == -1) {
            File file = new File(this.chatAssistantVoiceBean.getVoiceFilePath());
            if (file.exists()) {
                file.delete();
            }
            this.mFinishRecorderLayout.setVisibility(8);
            this.mStarRecorderLayout.setVisibility(0);
            return;
        }
        if (CLOSE_REQUEST_CODE == i && i2 == -1) {
            File file2 = new File(this.chatAssistantVoiceBean.getVoiceFilePath());
            if (file2.exists()) {
                file2.delete();
            }
            Intent intent2 = new Intent();
            intent2.putExtra("from", "record");
            ((FragmentActivity) Objects.requireNonNull(getActivity())).setResult(-1, intent2);
            getActivity().finish();
        }
    }

    @Override // cn.nubia.chatassistant.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.i(TAG, "onCreate: ");
        setLayout(R.layout.fragment_recorder);
    }

    @Override // cn.nubia.chatassistant.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        this.mRecorderButton.setAlpha(1.0f);
        this.recorderTimeText.setVisibility(8);
        this.recorderSoundLayout.setVisibility(8);
        this.audioRecorderUtils.stopRecordAndFile();
        ScreenBroadcastReceiver.unregisterScreenBroadcast(getContext());
    }

    @Override // cn.nubia.chatassistant.util.AudioRecorderUtils.OnDecibelListener
    public void onRecordDecibelValue(final double d) {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: cn.nubia.chatassistant.fragment.RecorderFragment.9
            @Override // java.lang.Runnable
            public void run() {
                RecorderFragment.this.recorderSoundLayout.setSoundValue((float) d);
            }
        });
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (R.id.recorder_button != view.getId()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            LogUtils.i(TAG, "OnTouchListener : ACTION_DOWN");
            this.mRecorderButton.setAlpha(0.5f);
            this.recorderTimeText.setVisibility(0);
            this.recorderSoundLayout.setVisibility(0);
            int startRecordAndFile = this.audioRecorderUtils.startRecordAndFile(getContext());
            this.recordResult = startRecordAndFile;
            if (startRecordAndFile == 2) {
                stopRecord();
            } else {
                refreshRecorderTime();
                ScreenBroadcastReceiver.registerScreenBroadcast(getContext(), this.audioRecorderUtils);
            }
        } else if (action == 1) {
            LogUtils.i(TAG, "OnTouchListener : ACTION_UP");
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
            }
            this.mRecorderButton.setAlpha(1.0f);
            this.recorderTimeText.setVisibility(8);
            this.recorderSoundLayout.setVisibility(8);
            if (this.recordResult != 2) {
                this.audioRecorderUtils.stopRecordAndFile();
                textStopRecord(this.recorderTime + "", AudioRecorderUtils.getWavFilePath(getContext()));
            }
        }
        return true;
    }
}
