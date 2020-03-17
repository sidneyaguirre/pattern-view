package com.example.pattern;

import com.example.pattern.utils.PatternView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private PatternView patternView;
    private static String MY_PREFS_NAME = "PatternLock";
    private static String PATTERN_KEY;
    SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        patternView = (PatternView)findViewById(R.id.patternView);
        patternView.setCallBack(new PatternView.CallBack() {
            @Override
            public void onFinish(String password) {
                PATTERN_KEY = prefs.getString("Pattern", "invalid");

                if(PATTERN_KEY.equals("invalid")) {
                    Toast.makeText(MainActivity.this, "Options --> Create new Pattern", Toast.LENGTH_LONG).show();
                }else {
                    if(password.equals(PATTERN_KEY)) {
                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Pattern incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_new_pattern:
                Intent intent = new Intent(MainActivity.this, ChangeActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
