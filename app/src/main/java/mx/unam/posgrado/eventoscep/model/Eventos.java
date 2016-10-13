package mx.unam.posgrado.eventoscep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by GuillermoAB on 28/09/2016.
 */

public class Eventos implements Serializable {
    @SerializedName("nid")
    @Expose
    private String nid;
    @SerializedName("field_image_fid")
    @Expose
    private String fieldImageFid;
    @SerializedName("field_imagen_h_fid")
    @Expose
    private Object fieldImagenHFid;
    @SerializedName("field_imagen_p_fid")
    @Expose
    private String fieldImagenPFid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("uri2")
    @Expose
    private String uri2;
    @SerializedName("linkURL")
    @Expose
    private Object linkURL;
    @SerializedName("fInit")
    @Expose
    private String fInit;
    @SerializedName("hInit")
    @Expose
    private String hInit;
    @SerializedName("fEnd")
    @Expose
    private String fEnd;
    @SerializedName("hEnd")
    @Expose
    private String hEnd;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("fInitC")
    @Expose
    private String fInitC;
    @SerializedName("fEndC")
    @Expose
    private String fEndC;

    /**
     *
     * @return
     * The nid
     */
    public String getNid() {
        return nid;
    }

    /**
     *
     * @param nid
     * The nid
     */
    public void setNid(String nid) {
        this.nid = nid;
    }

    /**
     *
     * @return
     * The fieldImageFid
     */
    public String getFieldImageFid() {
        return fieldImageFid;
    }

    /**
     *
     * @param fieldImageFid
     * The field_image_fid
     */
    public void setFieldImageFid(String fieldImageFid) {
        this.fieldImageFid = fieldImageFid;
    }

    /**
     *
     * @return
     * The fieldImagenHFid
     */
    public Object getFieldImagenHFid() {
        return fieldImagenHFid;
    }

    /**
     *
     * @param fieldImagenHFid
     * The field_imagen_h_fid
     */
    public void setFieldImagenHFid(Object fieldImagenHFid) {
        this.fieldImagenHFid = fieldImagenHFid;
    }

    /**
     *
     * @return
     * The fieldImagenPFid
     */
    public String getFieldImagenPFid() {
        return fieldImagenPFid;
    }

    /**
     *
     * @param fieldImagenPFid
     * The field_imagen_p_fid
     */
    public void setFieldImagenPFid(String fieldImagenPFid) {
        this.fieldImagenPFid = fieldImagenPFid;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     * The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return
     * The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     * The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return
     * The uri2
     */
    public String getUri2() {
        return uri2;
    }

    /**
     *
     * @param uri2
     * The uri2
     */
    public void setUri2(String uri2) {
        this.uri2 = uri2;
    }

    /**
     *
     * @return
     * The linkURL
     */
    public Object getLinkURL() {
        return linkURL;
    }

    /**
     *
     * @param linkURL
     * The linkURL
     */
    public void setLinkURL(Object linkURL) {
        this.linkURL = linkURL;
    }

    /**
     *
     * @return
     * The fInit
     */
    public String getFInit() {
        return fInit;
    }

    /**
     *
     * @param fInit
     * The fInit
     */
    public void setFInit(String fInit) {
        this.fInit = fInit;
    }

    /**
     *
     * @return
     * The hInit
     */
    public String getHInit() {
        return hInit;
    }

    /**
     *
     * @param hInit
     * The hInit
     */
    public void setHInit(String hInit) {
        this.hInit = hInit;
    }

    /**
     *
     * @return
     * The fEnd
     */
    public String getFEnd() {
        return fEnd;
    }

    /**
     *
     * @param fEnd
     * The fEnd
     */
    public void setFEnd(String fEnd) {
        this.fEnd = fEnd;
    }

    /**
     *
     * @return
     * The hEnd
     */
    public String getHEnd() {
        return hEnd;
    }

    /**
     *
     * @param hEnd
     * The hEnd
     */
    public void setHEnd(String hEnd) {
        this.hEnd = hEnd;
    }

    /**
     *
     * @return
     * The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The fInitC
     */
    public String getFInitC() {
        return fInitC;
    }

    /**
     *
     * @param fInitC
     * The fInitC
     */
    public void setFInitC(String fInitC) {
        this.fInitC = fInitC;
    }

    /**
     *
     * @return
     * The fEndC
     */
    public String getFEndC() {
        return fEndC;
    }

    /**
     *
     * @param fEndC
     * The fEndC
     */
    public void setFEndC(String fEndC) {
        this.fEndC = fEndC;
    }

}
