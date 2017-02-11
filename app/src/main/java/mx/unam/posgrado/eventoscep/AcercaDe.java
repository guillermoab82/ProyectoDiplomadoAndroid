package mx.unam.posgrado.eventoscep;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Luis Alfredo on 09/02/2017.
 */

public class AcercaDe extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener  {

    private GoogleMap mMap;

    private TextView twitter;
    private TextView facebook;
    private ImageView twitter_logo;
    private ImageView facebook_logo;
    private ImageView cep;

    private String direccion_url;

    private String url_twitter = "https://twitter.com/cepunam";
    private String url_facebook = "https://www.facebook.com/UNAMPosgrado";
    private String url_cep = "http://www.posgrado.unam.mx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_de);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if(status == ConnectionResult.SUCCESS) {

         /* Fragment del Mapa de ubicación */
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.mapa);
            mapFragment.getMapAsync(this);
        }else{
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,(Activity)getApplicationContext(),10);
        }


        TextView twitter = (TextView) findViewById(R.id.twitter);
        twitter_logo = (ImageView) findViewById(R.id.twitter_logo);
        TextView facebook = (TextView) findViewById(R.id.facebook);
        facebook_logo = (ImageView) findViewById(R.id.facebook_logo);
        cep = (ImageView) findViewById(R.id.image_cep);

        direccion_url = "";

        twitter.setOnClickListener(this);
        twitter_logo.setOnClickListener(this);
        facebook.setOnClickListener(this);
        facebook_logo.setOnClickListener(this);
        cep.setOnClickListener(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // Add marker in CEP UNAM and move de camera
        LatLng cep = new LatLng(19.309963, -99.185469);

        // Cambiando el título y el Color del marker de ubicación de CEP UNAM
        mMap.addMarker(new MarkerOptions().position(cep).title("Coordinación de Estudios de Posgrado").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        float zoomlevel = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cep, zoomlevel));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.twitter:
                direccion_url = url_twitter;
                iraWeb(direccion_url);
                break;
            case R.id.twitter_logo:
                direccion_url = url_twitter;
                iraWeb(direccion_url);
                break;
            case R.id.facebook:
                direccion_url = url_facebook;
                iraWeb(direccion_url);
                break;
            case R.id.facebook_logo:
                direccion_url = url_facebook;
                iraWeb(direccion_url);
                break;
            case R.id.image_cep:
                direccion_url = url_cep;
                iraWeb(direccion_url);
                break;
            default:
                break;
        }

    }

    public void iraWeb(String d) {
        Uri uri = Uri.parse(d);
        Intent intentNav = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intentNav);
    }



}

