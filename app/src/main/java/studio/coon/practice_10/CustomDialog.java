package studio.coon.practice_10;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

class CustomDialog extends Dialog implements android.view.View.OnClickListener {
    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etSurName) EditText etSurName;
    @BindView(R.id.etLog) EditText etLogin;
    @BindView(R.id.etPas) EditText etPass;
    @BindView(R.id.btn_yes) Button btnYes;
    @BindView(R.id.btn_no) Button btnNo;
    public Activity c;
    public CustomDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_custom);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                String temp_name = etName.getText().toString();
                String temp_surname = etSurName.getText().toString();
                String temp_login = etLogin.getText().toString();
                String temp_pass = etPass.getText().toString();
                int pass_length = etPass.getText().length();

                if (TextUtils.isEmpty(temp_name)) {
                    etName.setError(c.getString(R.string.hint_name));
                    etName.requestFocus();
                    return;
                } else if (temp_surname.matches("")) {
                    etSurName.requestFocus();
                    etSurName.setError(c.getString(R.string.hint_surname));
                    return;
                } else if (temp_login.matches("")) {
                    etLogin.requestFocus();
                    etLogin.setError(c.getString(R.string.hint_login));
                    return;
                } else if (temp_pass.matches("")) {
                    etPass.setError(c.getString(R.string.hint_pass));
                    return;
                } else if (2 >= pass_length) {
                    Toast.makeText(c, R.string.toast, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    etName.setError(null);
                    etSurName.setError(null);
                    etLogin.setError(null);
                    etPass.setError(null);
                    String strLogin = etLogin.getText().toString();
                    String strPass = etPass.getText().toString();
                    SharedPrefsHelper.put(c, "Login", strLogin);
                    SharedPrefsHelper.put(c, "Pass", strPass);
                    // осуществляем переход
                    Intent intent = new Intent(c, SecondActivity.class);
                    c.startActivity(intent);
                }
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

}