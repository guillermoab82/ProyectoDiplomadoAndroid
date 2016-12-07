package mx.unam.posgrado.eventoscep.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;

import mx.unam.posgrado.eventoscep.model.UserCredential;

/**
 * Created by Mario on 13/11/2016.
 */

public class PreferenceUser {
    private static final String FILE_NAME ="cepreference";
    private final SharedPreferences sp;

    public PreferenceUser(Context context)
   {
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }
    //se guardan los datos en el archivo
    public void saveUser(UserCredential userCredential)
    {
           sp.edit().putString("Id_user",userCredential.userId).apply();
           sp.edit().putString("name_user",userCredential.userName).apply();
           sp.edit().putString("URL_user",userCredential.userURL).apply();
           sp.edit().putString("origin_user",userCredential.userOrigin).apply();
     }

    public UserCredential getUser()
    {
        String mId_user=sp.getString("Id_user",null);
        String mname_user=sp.getString("name_user",null);
        String mURL_user=sp.getString("URL_user",null);
        String morigin_user=sp.getString("origin_user",null);

        if(TextUtils.isEmpty(mId_user) || TextUtils.isEmpty(mname_user) || TextUtils.isEmpty(mURL_user) || TextUtils.isEmpty(morigin_user))
            return null;
        return new UserCredential(mId_user,mname_user,mURL_user,morigin_user);

    }


}
