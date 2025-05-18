package com.example.littlenotestar_;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            Log.e("MainActivity", "BIENVENID@⭐⭐");
            try {
                Intent intent = new Intent(MainActivity.this, SiguienteActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Log.e("MainActivity", "Error:c", e);
            }
        }, 3000);
    }
}