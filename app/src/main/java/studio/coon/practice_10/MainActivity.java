package studio.coon.practice_10;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.etLogin) EditText etLogin;
    @BindView(R.id.etPass) EditText etPass;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.tvSignup) TextView tvSignUp;
    @BindView(R.id.tvForget) TextView tvForget;
    @BindView(R.id.chkBox) CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        if(SharedPrefsHelper.contains(this, "Login")) {
            String login = (String) SharedPrefsHelper.get(this, "Login", "");
            String pass = (String) SharedPrefsHelper.get(this, "Pass", "");
            Log.wtf("main-login", login);
            Log.wtf("main-pass", pass);
            if (!"".equals(login) && !"".equals(pass)) {
                // осуществляем переход
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
            }
        }
    }

    // проверка и обработка CheckBox
    @OnCheckedChanged({R.id.chkBox})
    public void onButtonCheckChanged() {
        if(mCheckBox.isChecked()) {
            String strLogin = etLogin.getText().toString();
            String strPass = etPass.getText().toString();
            SharedPrefsHelper.put(this, "Login", strLogin);
            SharedPrefsHelper.put(this, "Pass", strPass);
        } else {
            //SharedPrefsHelper.remove(this, "Welcome");
            SharedPrefsHelper.remove(this, "Login");
            SharedPrefsHelper.remove(this, "Pass");
        }
    }

    // нажатие кнопки LogIn и обработка полей EditText (пустое, длина пароля меньше 2)
    @OnClick(R.id.btnLogin)
    public void onClickLogIn() {
        String temp_login = etLogin.getText().toString();
        String temp_pass = etPass.getText().toString();
        int pass_length = etPass.getText().length();

        if (temp_login.matches("")) {
            etLogin.requestFocus();
            etLogin.setError(getString(R.string.error_login));
        } else if (temp_pass.matches("")) {
            etPass.setError(getString(R.string.error_pass));
        } else if (2 >= pass_length) {
            Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_SHORT).show();
        } else {
            etLogin.setError(null);
            etPass.setError(null);
            onButtonCheckChanged();
            // осуществляем переход
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
    }

    // нажатие SignUp
    @OnClick(R.id.tvSignup)
    public void onClickSignUp() {
        // добавляем свой AlertDialog
        CustomDialog castomDialog = new CustomDialog(this);
        castomDialog.show();
    }

    // Нажатие Forget
    @OnClick(R.id.tvForget)
    public void onClickForget() {
        etLogin.setText(null);
        etPass.setText(null);
     }

}
