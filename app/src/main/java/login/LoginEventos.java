package login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
/*import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;*/
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import mx.unam.posgrado.eventoscep.Principal;
import mx.unam.posgrado.eventoscep.R;

   public class LoginEventos extends AppCompatActivity  implements FacebookCallback<LoginResult> {
    private static final String TWITTER_KEY = "L7N6g1js11mcUQ3wjTPGi5nlz";
    private static final String TWITTER_SECRET = "hRr6K3RRe7tvftq9FpTUefX8RQUCTaIyfkW0nYmodV4P1HQN9k";
    CallbackManager callbackManager;
    private String namesocial,imagesocial;
    TwitterSession session;
    Long userid;
    @BindView(R.id.fb_login_buttonFB)  LoginButton loginButtonfb;
    @BindView(R.id.twitter_login_button)  TwitterLoginButton loginButtonTW;
    @BindView(R.id.profileImage) SimpleDraweeView logoImage;
    @BindView(R.id.txtHeadLogin) TextView txtheadlogin;
    @BindView(R.id.txtSubHeadLogin) TextView txtsubheadlogin;
    @BindView(R.id.txtComplementLogin) TextView txtcomplementlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
       // getActionBar().hide();
        getSupportActionBar().hide();
        //fresco initialice
        Fresco.initialize(this);
        //twitter
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_eventos);

        ButterKnife.bind(this);

        //para twitter
        loginButtonTW.setCallback(new Callback<TwitterSession>() {
            @Override
               public void success(Result<TwitterSession> result) {
                session = result.data;
                userid = session.getUserId();
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Log.d("datos: ",msg);
               // getUserData();
TwitterSession session = Twitter.getSessionManager().getActiveSession();
                Twitter.getApiClient(session).getAccountService()
                        .verifyCredentials(true,false,new Callback<User>(){

                            @Override
                            public void success(Result<User> userResult) {
                                User user =userResult.data;
                                namesocial=user.name;
                                imagesocial=user.profileImageUrl;
                                userid=user.id;
                                Log.d("log twitter imageUrl:",user.profileImageUrl);
                                Log.d("log twitter name:", user.name);
                                Log.d("log twitter id:", String.valueOf(user.id));
                                cambiaActivity();
                            }
                            @Override
                            public void failure(TwitterException exception) {

                            }
                        });
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

//facebook
    @Override
    public void onSuccess(LoginResult loginResult) {
        /*        */
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback()
        {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try
                {
                    namesocial=object.getString("name");
                    imagesocial="http://graph.facebook.com/"+ object.getString("id") +"/picture?type=large";
                    userid=object.getLong("id");
                    Log.d("log face name:", namesocial);
                    Log.d("log face image:", imagesocial);
                    Log.d(" log face id", String.valueOf(userid));
                    cambiaActivity();
                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
       // cambiaActivity();
     }

       private void cambiaActivity(){
           Intent intent= new Intent(getApplicationContext(),Principal.class);
           intent.putExtra("id",userid);
           intent.putExtra("name",namesocial);
           intent.putExtra("imagestr",imagesocial);
           startActivity(intent);

       }
//faceook
    @Override
    public void onCancel() {

    }
//facebook
    @Override
    public void onError(FacebookException error) {

    }
//facebook
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // facebook
        callbackManager = CallbackManager.Factory.create();
        loginButtonfb.registerCallback(callbackManager,this);
        callbackManager.onActivityResult(requestCode,resultCode,data);
   //twitter
        loginButtonTW.onActivityResult(requestCode,resultCode,data);
    }


/*//google
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
//google
       @Override
       public void onConnected(Bundle bundle) {

       }
//google
       @Override
       public void onConnectionSuspended(int i) {

       }*/
   }
