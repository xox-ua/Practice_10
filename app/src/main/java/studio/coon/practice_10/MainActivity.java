package studio.coon.practice_10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
    SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_PASS = "Pass";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(mSettings.contains(APP_PREFERENCES_LOGIN)) {
            // осуществляем переход
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }


        // нажатие кнопки LogIn и обработка полей EditText (пустое, длина пароля меньше 6)
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//                    etLogin.setError(null);
//                    etPass.setError(null);
                    onButtonCheckChanged();
                    // осуществляем переход
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            }
        });

        // нажатие SignUp
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast1, Toast.LENGTH_SHORT).show();
            }
        });

        // Нажатие Forget
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast2, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // проверка и обработка CheckBox
    @OnCheckedChanged({R.id.chkBox})
    public void onButtonCheckChanged() {
        if(mCheckBox.isChecked()) {
            String strLogin = etLogin.getText().toString();
            String strPass = etPass.getText().toString();
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_LOGIN, strLogin);
            editor.putString(APP_PREFERENCES_PASS, strPass);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_LOGIN, null);
            editor.putString(APP_PREFERENCES_PASS, null);
            editor.apply();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        String strLogin = etLogin.getText().toString();
        String strPass = etPass.getText().toString();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_LOGIN, strLogin);
        editor.putString(APP_PREFERENCES_PASS, strPass);
        editor.apply();
    }
}
