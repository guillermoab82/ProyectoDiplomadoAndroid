package mx.unam.posgrado.eventoscep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import mx.unam.posgrado.eventoscep.R;

/**
 * Created by Mario on 13/09/2016.
 */
public class Principal extends AppCompatActivity {
    Integer proveedor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);
        if (getIntent()!=null) {
             proveedor = getIntent().getExtras().getInt("p");
            if (proveedor.equals(0)) {
                getFBUserInfo();
            }
        }
    }
    private void getFBUserInfo() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback()
        {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try
                {
                    /*SimpleDraweeView userImage = (SimpleDraweeView) findViewById(R.id.item_imagephoto);
                    userImage.setImageURI("http://graph.facebook.com/"+ object.getString("id") +"/picture?type=large");*/
                    Log.d("facebook id : ",  object.getString("id"));
                    Log.d("facebook name : ", object.getString("name"));
                    Log.d("facebook email : ", object.getString("email"));
                   /* TextView userName= (TextView) findViewById(R.id.item_cameranamefull);
                    userName.setText(object.getString("name"));*/
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
    }
}
