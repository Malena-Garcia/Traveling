package es.travelWorld.traveling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import es.travelWorld.traveling.databinding.ActivityHomeBinding;
import es.travelWorld.traveling.domain.User;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        es.travelWorld.traveling.databinding.ActivityHomeBinding dataBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_home);
        setContentView(dataBinding.getRoot());
        getIntentExtras();
    }

    private void getIntentExtras() {
        if (getIntent().getExtras()!=null) {
            User recibeDatos = getIntent().getExtras().getParcelable("datosLogin");
            updateUI(recibeDatos);
        }
    }

    private void updateUI(User recibeDatos) {
        Toast.makeText(this, "Tenemos usuario "+ recibeDatos.getNombre() + " " + recibeDatos.getApellido(), Toast.LENGTH_LONG).show();
         Log.e("Male", "Tenemos usuario "+ recibeDatos.getNombre() + " " + recibeDatos.getApellido());
    }
}