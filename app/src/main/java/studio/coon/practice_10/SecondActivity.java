package studio.coon.practice_10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {
    @BindView(R.id.btnSplash) Button btnSplash;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_second);
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.log_out:
                SharedPrefsHelper.remove(this, "Login");
                SharedPrefsHelper.remove(this, "Pass");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.btnSplash)
    void submitButton(View view) {
        if (view.getId() == R.id.btnSplash) {
            Intent intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
        }
    }

    //блокирую кнопку назад
    @Override
    public void onBackPressed() {
        // действия при нажатии на кнопку
    }
}
