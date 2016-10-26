package mx.unam.posgrado.eventoscep;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.unam.posgrado.eventoscep.model.Eventos;

/**
 * Created by GuillermoAB on 03/10/2016.
 */

public class EventoDetails extends AppCompatActivity{
    @BindView(R.id.detailsTxtTitle)
    TextView title;
    @BindView(R.id.detailsImg)
    SimpleDraweeView img;
    @BindView(R.id.detailsTxtFInicio) TextView FInicio;
    @BindView(R.id.detailsTxtHInicio) TextView HInicio;
    @BindView(R.id.detailsTxtFFin) TextView FFin;
    @BindView(R.id.detailsTxtHFin) TextView HFin;
    @BindView(R.id.detailsLinkURL) TextView LinkURL;
    @BindView(R.id.labelLinkURL) TextView labelLinURL;
    @BindView(R.id.BarDetails)
    Toolbar Bar;
    private Eventos eventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_details);
        ButterKnife.bind(this);
        setSupportActionBar(Bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(getIntent().getExtras()!=null){
            eventos = (Eventos) getIntent().getSerializableExtra("eventos");
            title.setText(eventos.getTitle());
            if(eventos.getUri()!= null) {
                img.setImageURI("http://www.posgrado.unam.mx/sites/default/files/" + eventos.getUri());
            }else{
                if(eventos.getUri2()!=null) {
                    img.setImageURI("http://www.posgrado.unam.mx/sites/default/files/" + eventos.getUri2());
                }else{
                    img.setImageURI("http://www.posgrado.unam.mx/sites/default/files/" + "image-not-available.png");
                }
            }
            FInicio.setText(eventos.getFInit());
            HInicio.setText(eventos.getHInit());
            FFin.setText(eventos.getFEnd());
            HFin.setText(eventos.getHEnd());
            if(eventos.getLinkURL()!=null){
                LinkURL.setText(eventos.getLinkURL().toString());
            }else{
                labelLinURL.setText("");
                labelLinURL.setText("");
            }
        }else{
            Snackbar.make(findViewById(android.R.id.content),getResources().getText(R.string.msj_error_data),Snackbar.LENGTH_SHORT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.URIShare:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,eventos.getsURI().toString());
                startActivity(Intent.createChooser(shareIntent,getResources().getText(R.string.msjShare)));
                return true;
            case android.R.id.home:
                finish();
                return true;
           default:
               return super.onOptionsItemSelected(item);
        }
    }
}
