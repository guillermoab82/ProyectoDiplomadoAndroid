package login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
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
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import mx.unam.posgrado.eventoscep.Principal;
import mx.unam.posgrado.eventoscep.R;

   public class LoginEventos extends AppCompatActivity  implements FacebookCallback<LoginResult> {
    private static final String TWITTER_KEY = "L7N6g1js11mcUQ3wjTPGi5nlz";
    private static final String TWITTER_SECRET = "hRr6K3RRe7tvftq9FpTUefX8RQUCTaIyfkW0nYmodV4P1HQN9k";
    CallbackManager callbackManager;

    //Signing Options and google api client
   // private GoogleSignInOptions gso;
    //private GoogleApiClient mGoogleApiClient;

    @BindView(R.id.fb_login_buttonFB)  LoginButton loginButtonfb;
    @BindView(R.id.twitter_login_button)  TwitterLoginButton loginButtonTW;
    //@BindView(R.id.sign_in_buttonG) SignInButton LoginGoogle;
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

       //Initializing google signin option
        /* gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this  this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        */

        //para twitter
        loginButtonTW.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                  TwitterSession session = result.data;
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Log.d("datos: ",msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
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
        Intent intent= new Intent(getApplicationContext(),Principal.class);
        intent.putExtra("p",0);
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
