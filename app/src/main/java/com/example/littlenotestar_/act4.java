package com.example.littlenotestar_;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class act4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String apodo = getIntent().getStringExtra("");
        //hacemos que el apodo se muestre en el TextView
        TextView apodoTextView = findViewById(R.id.apodo);
        apodoTextView.setText(apodo);
        //mostramos un toast con puntos suspensivos para indicar que la aplicación se está ejecutando
        Toast.makeText(this, "...", Toast.LENGTH_SHORT).show();
        //aquí se decide el tiempo que se muestra en pantalla este activity para despues pasar al siguiente
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(act4.this, act5.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
