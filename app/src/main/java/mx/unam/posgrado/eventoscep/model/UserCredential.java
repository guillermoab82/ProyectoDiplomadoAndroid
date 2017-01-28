package mx.unam.posgrado.eventoscep.model;

/**
 * Created by Mario on 14/11/2016.
 */

public class UserCredential {
    public String userId;
    public String userName;
    public String userURL;
    public String userOrigin;


    public UserCredential(String userId, String userName, String userURL, String userOrigin ) {
        this.userName = userName;
        this.userId = userId;
        this.userURL = userURL;
        this.userOrigin = userOrigin;
    }
}
