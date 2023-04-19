package es.travelWorld.traveling;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import es.travelWorld.traveling.databinding.ActivityRegisterBinding;
import es.travelWorld.traveling.domain.User;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding dataBinding;
    private AutoCompleteTextView registroDropdown;
    private TextInputEditText nombreSet, apellidoSet;
    private int nom=0;
    private int apel=0;
    private AppCompatImageView photo;
    private AppCompatImageView hacerFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_register);
        setContentView(dataBinding.getRoot());
        setViews();
        setListeners();
        setBindRepo();
        String[] items = getResources().getStringArray(R.array.edad);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, items);
        registroDropdown.setAdapter(adapter);
    }

    ActivityResultLauncher<Intent> photoBitmap =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null){
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        photo.setImageBitmap(imgBitmap);
                    }
                }
            });

    private void setViews() {
        nombreSet = dataBinding.nombreSet;
        photo = dataBinding.pruebaCamara;
        apellidoSet = dataBinding.apellidoSet;
        registroDropdown = dataBinding.registroDropdown;
        hacerFoto= dataBinding.cameraReg;
    }

    private void setListeners() {
        registroDropdown.setOnItemClickListener((parent, view, position, id) -> {
        registroDropdown.setError(null);
            String elegido = parent.getItemAtPosition(position).toString();
            if (!elegido.equals("18-99 años")) {
                registroDropdown.setError("Esta app no es para ti");
            }
        });

        dataBinding.tvCondiciones.setOnClickListener(view ->
                gotoUrl("https://developers.google.com/ml-kit/terms"));

        hacerFoto.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            photoBitmap.launch(intent);
        });
    }

    private void gotoUrl(String s) {
        Uri webpage = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,webpage));
    }

     private void setBindRepo() {
        //dataBinding.setUser(new User());
        dataBinding.setActivity(RegisterActivity.this);
        dataBinding.setModelView(new User());
    }

    public void textNombreChanged(CharSequence nombre) {
        if (nombre != null && nombre.length() > 0) {
            if (nombre.toString().contains("@") || nombre.toString().contains("!")) {
                nombreSet.setError("Ups, no creo que sea correcto, revísalo.");
            }
            nom = 1;
        } else {
            nom = 0;
        }

        if (nom != 0 && apel != 0) {
            dataBinding.mainLogin.setEnabled(true);
        } else {
            dataBinding.mainLogin.setEnabled(false);
        }
    }

    public void textApellidoChanged(CharSequence apellido)  {
        if (apellido!=null && apellido.length()>0){
            if(apellido.toString().contains("@") || apellido.toString().contains("!")){
                apellidoSet.setError("Ups, no creo que sea correcto, revísalo.");
            }
            apel=1;
        } else {
            apel=0;
        }

        if (nom!=0 && apel!=0) {
            dataBinding.mainLogin.setEnabled(true);
        } else {
            dataBinding.mainLogin.setEnabled(false);
        }
    }
}