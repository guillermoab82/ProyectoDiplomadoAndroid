package mx.unam.posgrado.eventoscep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mario on 10/11/2016.
 */

public class USERResponse {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("correo")
    @Expose
    private String correo;

    public String getid() {return id;}
    public String getclave() {return clave;}
    public String getnombre() {return nombre;}
   public String getcorreo() {return correo;}
 }
