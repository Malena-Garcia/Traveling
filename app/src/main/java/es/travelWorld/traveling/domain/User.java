package es.travelWorld.traveling.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.io.Serializable;

import es.travelWorld.traveling.BR;

public class User extends BaseObservable implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
    }

    public void readFromParcel(Parcel source) {
        this.nombre = source.readString();
        this.apellido = source.readString();
    }

    public User() {
    }

    protected User(Parcel in) {
        this.nombre = in.readString();
        this.apellido = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
