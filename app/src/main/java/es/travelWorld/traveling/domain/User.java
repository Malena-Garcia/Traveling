package es.travelWorld.traveling.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import es.travelWorld.traveling.BR;

public class User extends BaseObservable {
    private String nombre;
    private String apellido;

    @Bindable
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(!nombre.equals(this.nombre)){
            this.nombre = nombre;
            notifyPropertyChanged(BR.nombre);
        }
    }

    @Bindable
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if(!apellido.equals(this.apellido)){
            this.apellido = apellido;
            notifyPropertyChanged(BR.apellido);
         }
    }
}
