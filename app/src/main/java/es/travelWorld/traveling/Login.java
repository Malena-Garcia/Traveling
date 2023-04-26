package es.travelWorld.traveling;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import es.travelWorld.traveling.databinding.ActivityLoginBinding;
import es.travelWorld.traveling.domain.User;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding dataBinding;
    private TextInputEditText username, userpassword;
    private String nombreRegistro = null;
    private String apellidoRegistro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);
        setContentView(dataBinding.getRoot());
        setViews();
        setListeners();
        getIntentExtras();
    }

    private void getIntentExtras() {
        if (getIntent().getExtras()!=null) {
            User recibeDatos = getIntent().getExtras().getParcelable("datosRegistro");
            nombreRegistro = recibeDatos.getNombre();
            apellidoRegistro = recibeDatos.getApellido();
        }
    }



    private void setViews() {
        username = dataBinding.inputUser;
        userpassword = dataBinding.inputPassword;
    }

    private void setListeners() {
        dataBinding.tvGet.setOnClickListener(view ->
                Snackbar.make(view,"Disponible proximamente...",Snackbar.LENGTH_SHORT).show());
        dataBinding.tvGet.setOnClickListener(view ->
                Snackbar.make(view,username.getText(),Snackbar.LENGTH_SHORT).show());

        dataBinding.tvCreate.setOnClickListener(view -> {
            Intent i = new Intent(Login.this, RegisterActivity.class);
            startActivity(i);
        });

        dataBinding.inputUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (username.length()!=0 && userpassword.length()!=0) {
                    dataBinding.btnLogin.setEnabled(true);
               } else {
                    dataBinding.btnLogin.setEnabled(false);
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dataBinding.inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (username.length()!=0 && userpassword.length()!=0) {
                    dataBinding.btnLogin.setEnabled(true);
                } else {
                    dataBinding.btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dataBinding.btnLogin.setOnClickListener(view -> {
           if (nombreRegistro == null ){
                Snackbar.make(view,"Debe registrarse para acceder, pulse 'Create now'...",Snackbar.LENGTH_SHORT).show();
           } else if (!nombreRegistro.equals(username.getText().toString()) || !apellidoRegistro.equals(userpassword.getText().toString()) ){
                Toast.makeText(Login.this, " usuario o contraseña incorrectos ", Toast.LENGTH_SHORT).show();
           } else if (nombreRegistro.equals(username.getText().toString()) && apellidoRegistro.equals(userpassword.getText().toString()) ){
                Toast.makeText(Login.this, " usuario y contraseña correctos ", Toast.LENGTH_SHORT).show();
                User userLogin = provideFromServer();
                Intent i = new Intent(Login.this,Home.class).putExtra("datosLogin",userLogin);
                startActivity(i);
            }
        });
    }

    private User provideFromServer() {
        User userLogin = new User();
        userLogin.setNombre(username.getText().toString());
        userLogin.setApellido(userpassword.getText().toString());
        return userLogin;
    }
}