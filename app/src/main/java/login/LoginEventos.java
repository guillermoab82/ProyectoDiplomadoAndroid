package login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import mx.unam.posgrado.eventoscep.Principal;
import mx.unam.posgrado.eventoscep.R;
import mx.unam.posgrado.eventoscep.data.EventData;
import mx.unam.posgrado.eventoscep.data.EventInterface;
import mx.unam.posgrado.eventoscep.model.USERRequest;
import mx.unam.posgrado.eventoscep.model.UserCredential;
import mx.unam.posgrado.eventoscep.model.UserResponseWS;
import mx.unam.posgrado.eventoscep.util.PreferenceUser;
import retrofit2.Call;
import retrofit2.Response;

public class LoginEventos extends AppCompatActivity  implements FacebookCallback<LoginResult>,GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    //private static final String TWITTER_KEY = "L7N6g1js11mcUQ3wjTPGi5nlz";
    //private static final String TWITTER_SECRET = "hRr6K3RRe7tvftq9FpTUefX8RQUCTaIyfkW0nYmodV4P1HQN9k";
    private static final String TWITTER_KEY = "L7N6g1js11mcUQ3wjTPGi5nlz";
    private static final String TWITTER_SECRET = "hRr6K3RRe7tvftq9FpTUefX8RQUCTaIyfkW0nYmodV4P1HQN9k";
    CallbackManager callbackManager;
    @BindView(R.id.buttonaccount) Button loginButton;
    @BindView(R.id.nameLogin) EditText namelogin;
    @BindView(R.id.emailLogin) EditText emailogin;
    private String namesocial, imagesocial;
    TwitterSession session;
    String userid,email,origen;
    private static final String TAG = "SignInActivity";
    //Signin button google
    private SignInButton signInButton;
    //Signing Options google
    private GoogleSignInOptions gso;
    //google api client google
    private GoogleApiClient mGoogleApiClient;
    //Signin constant to check the activity result google
    private int RC_SIGN_IN = 100;
    @BindView(R.id.fb_login_buttonFB)
    LoginButton loginButtonfb;
    @BindView(R.id.twitter_login_button)
    TwitterLoginButton loginButtonTW;
    @BindView(R.id.profileImage)
    SimpleDraweeView logoImage;
    @BindView(R.id.txtHeadLogin)
    TextView txtheadlogin;
    @BindView(R.id.txtSubHeadLogin)
    TextView txtsubheadlogin;
    @BindView(R.id.txtComplementLogin)
    TextView txtcomplementlogin;

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

        //preguntar al share preferencesi existe un login, para ya no pedir el login
        SharedPreferences pref = getSharedPreferences("evanPreference", Context.MODE_PRIVATE);
        String correo = pref.getString("id", "0");
        if (correo != "0") {
            Intent intent = new Intent(getApplicationContext(), Principal.class);
            intent.putExtra("id", pref.getString("id", "0"));
            intent.putExtra("name", pref.getString("name", ""));
            intent.putExtra("imagestr", pref.getString("imagestr", ""));
            startActivity(intent);
        }

        //google
        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //Initializing signinbutton
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());
        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);
        //google fin
        //para twitter
        Log.d ("antes twitter:" ,"antes de twiter");

        loginButtonTW.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                session = result.data;
                userid = String.valueOf(session.getUserId());
                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                Twitter.getApiClient(session).getAccountService()
                        .verifyCredentials(true, false, new Callback<User>() {

                            @Override
                            public void success(Result<User> userResult) {
                                User user = userResult.data;
                                namesocial = user.name;
                                imagesocial = user.profileImageUrl;
                                userid = String.valueOf(user.id);
                               Log.d ("cadena twitter:" , namesocial + " " + imagesocial  + " " + userid);
                               // Snackbar.make(findViewById(android.R.id.content), namesocial + " " + imagesocial  + " " + userid, Snackbar.LENGTH_SHORT).show();
                                email = "twitter@twitter.com";//user.email;
                                origen = "twitter";
                                cambiaActivity();
                            }

                            @Override
                            public void failure(TwitterException exception) {
                                Snackbar.make(findViewById(android.R.id.content), exception.toString(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void failure(TwitterException exception) {
                Snackbar.make(findViewById(android.R.id.content), "falla conexion twitter.", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    //para revisarsi existe conexion en dispositivo
    public Boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    //facebook
    @Override
    public void onSuccess(LoginResult loginResult) {
        /*        */
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    namesocial = object.getString("name");
                    imagesocial = "http://graph.facebook.com/" + object.getString("id") + "/picture?type=large";
                    userid = String.valueOf(object.getLong("id"));
                    email = "facebook@facebook.com";//object.getString("email");
                    origen = "facebook";
                    cambiaActivity();
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });
        request.executeAsync();
    }

    //consulta y/o guarda usuario, ycambia activity
    private void cambiaActivity() {
       EventInterface userInterface = EventData.getRetofitInstance(2).create(EventInterface.class);
        USERRequest userRequest = new USERRequest();
        userRequest.setclave(userid);
        userRequest.setnombre(namesocial);
        userRequest.setcorreo(email);
        userRequest.setUrl(imagesocial);
        Call<UserResponseWS> userResponseCall = userInterface.getTokenAccess(userRequest);
        try {
            userResponseCall.enqueue(new retrofit2.Callback<UserResponseWS>() {
                @Override
                public void onResponse(Call<UserResponseWS> call, Response<UserResponseWS> response) {
                    //regresa datos el webservice??
                    if (response.body() != null) {
                        //guardar informacion en sharepreference
                        Log.d("preference", "estoyen preference");
                        SharedPreferences pref = getSharedPreferences("evanPreference",Context.MODE_PRIVATE) ;
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("id", userid);
                        editor.putString("name", namesocial);
                        editor.putString("tipo", email);
                        editor.putString("imagestr", imagesocial);
                        editor.putString("origenLog", origen);
                        editor.commit();
                        //manda los datos y presenta la activity principal

                       // Log.d("entre", "entre cambio activity");
                        Intent intent = new Intent(getApplicationContext(), Principal.class);
                        intent.putExtra("id", userid);
                        intent.putExtra("name", namesocial);
                        intent.putExtra("imagestr", imagesocial);
                        startActivity(intent);
                       // Log.d("pase", "pase  start activityy");

                    }
                    else{
                        Log.d("responseBody", "vuelva a intentar");
                        Snackbar.make(findViewById(android.R.id.content),"vuelva a intentar", Snackbar.LENGTH_INDEFINITE).show();
                    } //end of if
                 }

                @Override
                public void onFailure(Call<UserResponseWS> call, Throwable t) {
                    Log.d("call",  t.toString());
                    Snackbar.make(findViewById(android.R.id.content), t.toString(), Snackbar.LENGTH_INDEFINITE).show();
                }
            });
        }
        catch (Exception e)
        {
            Log.d("try", e.toString());
                Snackbar.make(findViewById(android.R.id.content), e.toString(), Snackbar.LENGTH_SHORT).show();
        }
    }
    //logeo nombre y correo normal
    @OnClick(R.id.buttonaccount)
    void buttonClick() {
          try {
              Log.d("boton login","entre boton login");
            namesocial = namelogin.getText().toString();
            imagesocial = "http://132.248.246.61/2017/imagenes/blanco.png";
             userid =  emailogin.getText().toString();
            email = emailogin.getText().toString();
            origen = "login";
            cambiaActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //faceook
    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content), "On cancel", Snackbar.LENGTH_SHORT).show();
    }
    //facebook
    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content), error.toString(), Snackbar.LENGTH_SHORT).show();
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
        //google
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isConnected() == false  ) {
            Snackbar.make(findViewById(android.R.id.content), "No cuenta con conexion a red", Snackbar.LENGTH_SHORT).show();
        }
    }

     //google
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Snackbar.make(findViewById(android.R.id.content), "No cuenta con conexion a red", Snackbar.LENGTH_SHORT).show();
    }
    // google
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    //google
    //google This function will option signing intent google
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            //Displaying name and email
            namesocial=acct.getDisplayName();
            imagesocial=acct.getPhotoUrl().toString();
            userid=String.valueOf(acct.getId());
            email="google@google.com";//(acct.getEmail());
            origen="google";
            cambiaActivity();
        } else {
            //If login fails
            Snackbar.make(findViewById(android.R.id.content), "No cuenta con conexion a red", Snackbar.LENGTH_SHORT).show();
        }
    }

    //google
}
