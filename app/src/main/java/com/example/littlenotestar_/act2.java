package com.example.littlenotestar_;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class act2 extends Fragment {

    EditText cajaEmail, cajaUser, cajaPwd;
    Button btnRegistrar;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_act2, container, false);

        cajaEmail = vista.findViewById(R.id.email);
        cajaUser = vista.findViewById(R.id.user);
        cajaPwd = vista.findViewById(R.id.pwd);
        btnRegistrar = vista.findViewById(R.id.btn_Entrar);

        databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        return vista;
    }

    private void registrarUsuario() {
        String email = cajaEmail.getText().toString().trim();
        String usuario = cajaUser.getText().toString().trim();
        String contraseña = cajaPwd.getText().toString().trim();

        if (email.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(getContext(), "Por favor, llene todos los campos del registro.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(getContext(), "Ingrese un correo electrónico válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        Registros newUser = new Registros(usuario, contraseña, email);

        databaseReference.child(usuario).setValue(newUser)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(getContext(), "⭐ ¡Usuario registrado exitosamente! ⭐", Toast.LENGTH_SHORT).show();

                    cajaEmail.setText("");
                    cajaUser.setText("");
                    cajaPwd.setText("");

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.escenario1, new act1())
                            .addToBackStack(null)
                            .commit();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Error al registrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
