package mx.unam.posgrado.eventoscep.model;

/**
 * Created by Mario on 10/11/2016.
 */

public class USERRequest {
    private String id;
    private String clave;
    private String nombre;
    private String correo;
    private String url;

    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }
    public String getclave() {
        return clave;
    }
    public void setclave(String clave) {
        this.clave = clave;
    }
    public String getnombre() {return nombre;    }
    public void setnombre(String nombre) {
        this.nombre = nombre;
    }
    public String getcorreo() {
        return correo;
    }
    public void setcorreo(String correo) {this.correo = correo;}
    public String geturl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
