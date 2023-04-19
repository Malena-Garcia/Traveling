package es.travelWorld.traveling;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import es.travelWorld.traveling.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);
        setContentView(dataBinding.getRoot());
        setListeners();
    }

    private void setListeners() {
        dataBinding.tvGet.setOnClickListener(view ->
                Snackbar.make(view,"Disponible proximamente...",Snackbar.LENGTH_SHORT).show());
        dataBinding.tvCreate.setOnClickListener(view ->
                Snackbar.make(view,"Disponible proximamente...",Snackbar.LENGTH_SHORT).show());

    }


}