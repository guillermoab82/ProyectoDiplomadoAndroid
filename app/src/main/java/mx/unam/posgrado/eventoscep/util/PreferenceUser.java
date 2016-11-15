package mx.unam.posgrado.eventoscep.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

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
    public void saveUser(Integer Nuser)
    {
        //TODO validar si modelUser==null
       if(Nuser != null ){
            sp.edit().putString("Id_user",String.valueOf(Nuser)).apply();
        }

     }
    public Integer getUser()
    {
        String mUser=sp.getString("Id_user",null);
         if(TextUtils.isEmpty(mUser) )
            return null;
        return Integer.parseInt(mUser);

    }


}
