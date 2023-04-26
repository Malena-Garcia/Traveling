package es.travelWorld.traveling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import es.travelWorld.traveling.databinding.ActivityHomeBinding;
import es.travelWorld.traveling.domain.User;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding dataBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_home);
        setContentView(dataBinding.getRoot());
        //setSupportActionBar(dataBinding.homeToolbar);

        getIntentExtras();
        dataBinding.homeToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case (R.id.disney):
                        gotoUrl("https://www.disneylandparis.com/es-es/");
                        break;
                    case (R.id.rent_car):
                        inflateFragment();
                        break;
                }
                return false;
            }
        });
    }

    private void inflateFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        transaction.add(dataBinding.mainContentFragment.getId(), homeFragment);
        transaction.commitAllowingStateLoss();
    }

    private void getIntentExtras() {
        if (getIntent().getExtras() != null) {
            User recibeDatos = getIntent().getExtras().getParcelable("datosLogin");
            updateUI(recibeDatos);
        }
    }

    private void updateUI(User recibeDatos) {
        Toast.makeText(this, "Tenemos usuario " + recibeDatos.getNombre() + " " + recibeDatos.getApellido(), Toast.LENGTH_LONG).show();
        Log.e("Male", "Tenemos usuario " + recibeDatos.getNombre() + " " + recibeDatos.getApellido());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void gotoUrl(String web) {
        Uri webpage = Uri.parse(web);
        startActivity(new Intent(Intent.ACTION_VIEW, webpage));
    }
}