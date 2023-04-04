package es.travelWorld.traveling;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import es.travelWorld.traveling.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_login);
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.tvGet.setOnClickListener(view ->
                Snackbar.make(view,"Disponible proximamente...",Snackbar.LENGTH_SHORT).show());
        binding.tvCreate.setOnClickListener(view ->
                Snackbar.make(view,"Disponible proximamente...",Snackbar.LENGTH_SHORT).show());

    }


}