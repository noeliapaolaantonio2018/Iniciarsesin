package com.example.iniciarsesin.ui.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iniciarsesin.R;
import com.example.iniciarsesin.model.Usuario;
import com.example.iniciarsesin.ui.ui.login.MainActivity;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class RegistroActivity extends AppCompatActivity {

    EditText dni;
    EditText nombre;
    EditText apellido;
    EditText email;
    EditText password;
    TextView msjerror;
    ViewModelRegistro viewModelRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializar();

        viewModelRegistro.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni()+"");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getMail());
                password.setText(usuario.getPassword());

            }
        });
        if(getIntent().getExtras().getInt("")==1) {
            File directorio=getFilesDir();
            viewModelRegistro.mostrar(getApplicationContext(),directorio);
        }
    }
    public void inicializar(){
        dni=findViewById(R.id.dni);
        nombre=findViewById(R.id.nombre);
        apellido= findViewById(R.id.apellido);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        msjerror= findViewById(R.id.msjreg);
        viewModelRegistro =  ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);
    }

    public void guardar(View view) {
        File directorio=getFilesDir();
        Usuario us = new Usuario();
        if (!dni.getText().toString().isEmpty() && !nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            us.setDni(Long.parseLong(dni.getText().toString()));
            us.setNombre(nombre.getText().toString());
            us.setApellido(apellido.getText().toString());
            us.setMail(email.getText().toString());
            us.setPassword(password.getText().toString());
            viewModelRegistro.guardar(getApplicationContext(), us, directorio);
            Intent ingresar = new Intent(this, MainActivity.class);

            startActivity(ingresar);

        } else {
            msjerror.setText("Todos los campos deben estar completos");
        }
    }
}
