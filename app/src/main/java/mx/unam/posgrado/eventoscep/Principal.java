package mx.unam.posgrado.eventoscep;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import org.json.JSONObject;

/**
 * Created by Mario on 13/09/2016.
 */
public class Principal extends AppCompatActivity {
    @BindView(R.id.list_toolbar)
    Toolbar toolbar;
    @BindView(R.id.l_navigation_view)
    NavigationView navigationview;
    @BindView(R.id.l_navigation_drawer)
    DrawerLayout drawerlayout;
    TextView namesoc;
    SimpleDraweeView photosoc;
    String name, imageURL;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);
       /* namesoc = (TextView) findViewById(R.id.item_nameSocial);
        photosoc = (SimpleDraweeView)  findViewById(R.id.imagephoto_Social);*/
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerlayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.eventos:
                        //Snackbar.make(findViewById(android.R.id.content), "Eventos / convocatorias.", Snackbar.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.mainContenedor,new EventoLista()).commit();
                        return true;
                    case R.id.informacion:
                        Snackbar.make(findViewById(android.R.id.content), "Información.", Snackbar.LENGTH_SHORT).show();
                        //getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,new fragment_listing()).commit();
                        return true;

                    case R.id.enlaces:
                        Snackbar.make(findViewById(android.R.id.content), "Enlaces.", Snackbar.LENGTH_SHORT).show();
                        //getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,new fragmentRFavorites()).commit();
                        return true;
                    case R.id.yoUNAM:
                        Snackbar.make(findViewById(android.R.id.content), "Yo UNAM.", Snackbar.LENGTH_SHORT).show();
                        //getFragmentManager().beginTransaction().replace(R.id.activity_detalles_fldetalles,new fragment_acecade()).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });

         getUserInfo();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void getUserInfo() {
        if (getIntent() != null) {
            name = getIntent().getExtras().getString("name");
            imageURL = getIntent().getExtras().getString("imagestr");
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback()
            {

                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view;
            Uri uri = Uri.parse(imageURL);
            namesoc = (TextView) findViewById(R.id.item_namesocial);

            photosoc = (SimpleDraweeView) findViewById(R.id.imagephoto_social);

            ImageRequest request = ImageRequest.fromUri(uri);

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(photosoc.getController()).build();

            photosoc.setController(controller);
            // photosoc.setImageURI(url);

            namesoc.setText(name);
                }
            });
            request.executeAsync();

        }
    }
}
