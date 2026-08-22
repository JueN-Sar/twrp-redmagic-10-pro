package cn.nubia.chatassistant.fragment;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import cn.nubia.chatassistant.bean.ChatAssistantVoiceBean;
import cn.nubia.chatassistant.bean.Song;
import cn.nubia.chatassistant.customchat.DeleteDialog;
import cn.nubia.chatassistant.customchat.LocalMusicActivity;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.MusicManagerUtils;
import cn.nubia.chatassistant.util.ToastUtils;
import cn.nubia.gamelauncher.R;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes.dex */
public class ImportFragment extends BaseFragment {
    private static final int CLOSE_REQUEST_CODE = 997;
    private static final int IMPORT_REQUEST_CODE = 999;
    private static final int REVERT_REQUEST_CODE = 998;
    private static final String TAG = "ImportFragment";
    public ChatAssistantVoiceBean chatAssistantVoiceBean;
    private TextView importText;
    private TextView mConfirmButton;
    private TextView mImportButton;
    private View mImportFinishLayout;
    private View mRevertImport;
    int mTime;
    private TextView mVoiceTimeText;
    private TextView musicName;
    private View soundIcon;

    private void showImportFinishUI() {
        this.mImportFinishLayout.setVisibility(0);
        this.mImportButton.setVisibility(8);
        this.importText.setVisibility(8);
        this.musicName.setText(this.chatAssistantVoiceBean.getVoiceFileName());
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(this.chatAssistantVoiceBean.getVoiceFilePath());
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            LogUtils.i(TAG, "showImportFinishUI METADATA_KEY_DURATION : " + extractMetadata);
            int parseInt = Integer.parseInt(extractMetadata);
            this.mTime = parseInt;
            this.mTime = parseInt / 1000;
            this.mVoiceTimeText.setText(this.mTime + "”");
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
                throw th;
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    @Override // cn.nubia.chatassistant.fragment.BaseFragment
    public void initView() {
        View findViewById = findViewById(R.id.select_finish_Layout);
        this.mImportFinishLayout = findViewById;
        findViewById.setVisibility(8);
        this.musicName = (TextView) findViewById(R.id.music_name);
        this.importText = (TextView) findViewById(R.id.import_recorder_text);
        this.mRevertImport = findViewById(R.id.btn_rev_start);
        this.mVoiceTimeText = (TextView) findViewById(R.id.scan_c);
        this.soundIcon = findViewById(R.id.sound_log);
        this.importText.setText(Html.fromHtml(getResources().getString(R.string.import_recorder_txt)));
        findViewById(R.id.select_sound_layout).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.ImportFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ImportFragment.TAG, "onClick select_sound_layout: ");
                if (MusicManagerUtils.getInstance().isPlay()) {
                    return;
                }
                MusicManagerUtils.getInstance().startPlay(ImportFragment.this.chatAssistantVoiceBean.getVoiceFilePath(), new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.chatassistant.fragment.ImportFragment.1.1
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                    }
                });
                ImportFragment importFragment = ImportFragment.this;
                importFragment.startAnimation(importFragment.soundIcon, ImportFragment.this.mTime - 1);
            }
        });
        this.mRevertImport.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.ImportFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ImportFragment.TAG, "onClick mRevertImport: ");
                Intent intent = new Intent(ImportFragment.this.getContext(), (Class<?>) DeleteDialog.class);
                intent.putExtra("text", ImportFragment.this.getResources().getString(R.string.import_delete_text));
                ImportFragment.this.startActivityForResult(intent, ImportFragment.REVERT_REQUEST_CODE);
            }
        });
        TextView textView = (TextView) findViewById(R.id.btn_confirm);
        this.mConfirmButton = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.ImportFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ImportFragment.TAG, "onClick mRevertImport: ");
                if (ImportFragment.this.mTime < 2 || ImportFragment.this.mTime > 10) {
                    ToastUtils.showToast(ImportFragment.this.getContext(), ImportFragment.this.getResources().getString(R.string.recorder_error_text), 0);
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("chatAssistantVoiceBean", ImportFragment.this.chatAssistantVoiceBean);
                ((FragmentActivity) Objects.requireNonNull(ImportFragment.this.getActivity())).setResult(-1, intent);
                ImportFragment.this.getActivity().finish();
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.select_music);
        this.mImportButton = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.ImportFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.i(ImportFragment.TAG, "onClick mImportButton: ");
                ImportFragment.this.startActivityForResult(new Intent(ImportFragment.this.getActivity(), (Class<?>) LocalMusicActivity.class), 999);
            }
        });
        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.fragment.ImportFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ImportFragment.this.mImportFinishLayout.getVisibility() != 0) {
                    ImportFragment.this.getActivity().finish();
                    return;
                }
                Intent intent = new Intent(ImportFragment.this.getContext(), (Class<?>) DeleteDialog.class);
                intent.putExtra("text", ImportFragment.this.getResources().getString(R.string.import_delete_text));
                ImportFragment.this.startActivityForResult(intent, ImportFragment.CLOSE_REQUEST_CODE);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtils.i(TAG, "requestCode : " + i + " , resultCode : " + i2);
        if (999 == i && i2 == -1) {
            Song song = (Song) intent.getSerializableExtra("song");
            if (song != null) {
                if (this.chatAssistantVoiceBean == null) {
                    this.chatAssistantVoiceBean = new ChatAssistantVoiceBean(song.getName());
                }
                this.chatAssistantVoiceBean.setVoiceFileName(song.getName());
                this.chatAssistantVoiceBean.setVoiceFilePath(song.getPath());
                this.chatAssistantVoiceBean.setTime(song.getDuration());
                this.chatAssistantVoiceBean.setSystemDefault(false);
            }
            showImportFinishUI();
            return;
        }
        if (REVERT_REQUEST_CODE == i && i2 == -1) {
            startActivityForResult(new Intent(getActivity(), (Class<?>) LocalMusicActivity.class), 999);
        } else if (CLOSE_REQUEST_CODE == i && i2 == -1) {
            ((FragmentActivity) Objects.requireNonNull(getActivity())).setResult(-1, new Intent());
            getActivity().finish();
        }
    }

    @Override // cn.nubia.chatassistant.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.i(TAG, "onCreate: ");
        setLayout(R.layout.fragment_import);
    }
}
