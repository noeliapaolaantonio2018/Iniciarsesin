package com.example.iniciarsesin.ui.ui.registro;

import android.app.Application;
import android.content.Context;

import com.example.iniciarsesin.model.Usuario;
import com.example.iniciarsesin.request.ApiClient;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ViewModelRegistro extends AndroidViewModel {

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<Usuario> usuario;

    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario= new MutableLiveData<>();

        }
        return usuario;
    }
    public void guardar(Context context, Usuario usuario, File directorio ){

        ApiClient.guardar(context,usuario,directorio);
    }

    public void mostrar(Context context, File directorio){
        Usuario us= ApiClient.leer(context,directorio);
        usuario.setValue(us);
    }
}
