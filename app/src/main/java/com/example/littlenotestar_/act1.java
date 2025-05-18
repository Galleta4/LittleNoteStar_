package com.example.littlenotestar_;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class act1 extends Fragment {
    private EditText cajaUser, cajaPwd;
    private Button btnEntrar, btnRegistrarse;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_act1, container, false);

        cajaUser = vista.findViewById(R.id.user);
        cajaPwd = vista.findViewById(R.id.pwd);
        btnEntrar = vista.findViewById(R.id.btn_Entrar);
        btnRegistrarse = vista.findViewById(R.id.btn_sig);

        //BOTÓN PARA ENTRAR U INICIAR SESIÓN
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), nana.class);
                startActivity(intent);
            }
        });

        return vista;
    }

    private void iniciarSesion() {

        //AQUÍ SE OBTUVO EL USUARIO Y CONTRASEÑA (SE CONVIRTIÓ EN STRING)
        String usuario = cajaUser.getText().toString();
        String contraseña = cajaPwd.getText().toString();

        //COPARAMOS SI EL USUARIO Y CONTRASEÑA EXISTEN
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        //ACCEDEMOS A LA BASE DE DATOS
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();


        database.child("usuarios").child(usuario).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot dataSnapshot = task.getResult();

                if (dataSnapshot.exists()) {
                    Registros registro = dataSnapshot.getValue(Registros.class);

                    if (registro != null && registro.getContraseña().equals(contraseña)) {
                        Toast.makeText(getContext(), "Inicio de sesión exitoso ⭐ " + usuario, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), act3.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Contraseña incorrecta, porfavor vuelva a intentarlo.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Usuario no encontrado.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Error al consultar la base de datos D:", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
