package es.travelWorld.traveling;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import es.travelWorld.traveling.databinding.ActivityRegisterBinding;
import es.travelWorld.traveling.domain.User;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding dataBinding;
    private AutoCompleteTextView registroDropdown;
    private TextInputEditText nombreSet, apellidoSet;
    private int nom=0;
    private int apel=0;
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

    private ActivityResultLauncher<String> pedirPermisos = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    abrirCamara();
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor, habilite el permiso para usar la camara", Toast.LENGTH_SHORT).show();
                }
            });

    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoBitmap.launch(intent);
    }

    ActivityResultLauncher<Intent> photoBitmap =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null){
                    Bundle extras = result.getData().getExtras();
                    Bitmap imgBitmap = (Bitmap) extras.get("data");
                   // photo.setImageBitmap(imgBitmap);
                }
            });

    private void setViews() {
        nombreSet = dataBinding.nombreSet;
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
            String permission = Manifest.permission.CAMERA;
            pedirPermisos.launch(permission);
        });

        dataBinding.mainLogin.setOnClickListener(v -> {
            User user = provideFromServer();
            Intent i = new Intent(RegisterActivity.this,Login.class).putExtra("datosRegistro",user);
            startActivity(i);
        });
    }

    private void gotoUrl(String web) {
        Uri webpage = Uri.parse(web);
        startActivity(new Intent(Intent.ACTION_VIEW,webpage));
    }

     private void setBindRepo() {
        dataBinding.setActivity(RegisterActivity.this);
        dataBinding.setModelView(new User());
    }

    public void textNombreChanged(CharSequence nombre) {
        if (nombre != null && nombre.length() > 0) {
            if (nombre.toString().contains("@") || nombre.toString().contains("!")) {
                nombreSet.setError("Ups, no creo que sea correcto, revísalo.");
            }
            Toast.makeText(RegisterActivity.this, nombre, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(RegisterActivity.this,apellido, Toast.LENGTH_SHORT).show();
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

    private User provideFromServer() {
        User user = new User();
        user.setNombre(nombreSet.getText().toString());
        user.setApellido(apellidoSet.getText().toString());
        return user;
    }
}