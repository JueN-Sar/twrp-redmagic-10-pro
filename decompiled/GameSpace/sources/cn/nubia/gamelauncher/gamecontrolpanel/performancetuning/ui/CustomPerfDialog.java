package cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.CustomPerfProfileManager;
import cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog;
import java.util.List;

/* loaded from: classes.dex */
public class CustomPerfDialog {
    private static CustomPerfDialog sInstance;
    private AlertDialog mAlertDialog;

    public interface ConfirmListener {
        void onConfirm(String str);
    }

    private CustomPerfDialog() {
    }

    public static CustomPerfDialog getInstance() {
        if (sInstance == null) {
            sInstance = new CustomPerfDialog();
        }
        return sInstance;
    }

    public void dismissDialog() {
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mAlertDialog.dismiss();
        }
        this.mAlertDialog = null;
    }

    public void showDeleteDialog(Context context, final ConfirmListener confirmListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_perf_dialog_text_content, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(R.string.custom_perf_dialog_delete_title);
        dismissDialog();
        AlertDialog create = new AlertDialog.Builder(context, 2131952382).setTitle(R.string.nubia_customize_handle_delete_dialog_btn).setView(inflate).setNegativeButton(R.string.nubia_game_performance_super_dialog_cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.nubia_customize_handle_delete_dialog_btn, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CustomPerfDialog.ConfirmListener.this.onConfirm(null);
            }
        }).create();
        this.mAlertDialog = create;
        create.getWindow().setType(2038);
        this.mAlertDialog.show();
    }

    public void showEditDialog(final Context context, final String str, final List<String> list, final String str2, String str3, final ConfirmListener confirmListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_perf_dialog_rename_title, (ViewGroup) null);
        final TextView textView = (TextView) inflate.findViewById(R.id.tv_title);
        textView.setText(str2);
        View inflate2 = LayoutInflater.from(context).inflate(R.layout.custom_perf_dialog_rename, (ViewGroup) null);
        final EditText editText = (EditText) inflate2.findViewById(R.id.edt_name);
        editText.setText(str);
        dismissDialog();
        this.mAlertDialog = new AlertDialog.Builder(context, 2131952382).setView(inflate2).setCustomTitle(inflate).setNegativeButton(R.string.nubia_game_performance_super_dialog_cancel, (DialogInterface.OnClickListener) null).setPositiveButton(str3, (DialogInterface.OnClickListener) null).create();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        editText.addTextChangedListener(new TextWatcher() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String trim = editable.toString().trim();
                if (list.contains(trim) && !TextUtils.equals(trim, str)) {
                    textView.setText(R.string.custom_perf_dialog_same_name);
                    textView.setTextColor(context.getResources().getColor(R.color.custom_perf_dialog_rename_cursor));
                } else if (TextUtils.isEmpty(trim)) {
                    textView.setText(R.string.custom_perf_dialog_empty_name);
                    textView.setTextColor(context.getResources().getColor(R.color.custom_perf_dialog_rename_cursor));
                } else {
                    textView.setText(str2);
                    textView.setTextColor(-1);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.mAlertDialog.getWindow().setType(2038);
        this.mAlertDialog.show();
        this.mAlertDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                if (TextUtils.isEmpty(trim) || list.contains(trim)) {
                    return;
                }
                confirmListener.onConfirm(editText.getText().toString());
                CustomPerfDialog.this.mAlertDialog.dismiss();
            }
        });
    }

    public void showHelpDetailDialog(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_perf_dialog_help, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(CustomPerfProfileManager.getInstance().getHelpText());
        dismissDialog();
        AlertDialog create = new AlertDialog.Builder(context, 2131952382).setTitle(R.string.custom_perf_dialog_help_title).setNeutralButton(R.string.confirm, (DialogInterface.OnClickListener) null).setView(inflate).create();
        this.mAlertDialog = create;
        create.getWindow().setType(2038);
        this.mAlertDialog.show();
    }

    public void showRenameDialog(Context context, String str, List<String> list, ConfirmListener confirmListener) {
        showEditDialog(context, str, list, context.getString(R.string.nubia_customize_handle_rename_dialog_title), context.getString(R.string.nubia_touch_game_key_save_dialog_save), confirmListener);
    }

    public void showResetDialog(Context context, final ConfirmListener confirmListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_perf_dialog_text_content, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_content)).setText(R.string.custom_perf_dialog_reset_content);
        dismissDialog();
        AlertDialog create = new AlertDialog.Builder(context, 2131952382).setTitle(R.string.custom_perf_dialog_reset_title).setView(inflate).setNegativeButton(R.string.nubia_game_performance_super_dialog_cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.custom_perf_dialog_reset_title, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.performancetuning.ui.CustomPerfDialog$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CustomPerfDialog.ConfirmListener.this.onConfirm(null);
            }
        }).create();
        this.mAlertDialog = create;
        create.getWindow().setType(2038);
        this.mAlertDialog.show();
    }

    public void showSaveAsDialog(Context context, String str, List<String> list, ConfirmListener confirmListener) {
        String string = context.getString(R.string.custom_perf_dialog_save_as_title);
        showEditDialog(context, str, list, string, string, confirmListener);
    }
}
