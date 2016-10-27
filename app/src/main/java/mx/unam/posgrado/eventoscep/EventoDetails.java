package mx.unam.posgrado.eventoscep;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
            case R.id.AddCalendar:
                String fIniStr = eventos.getFInitC();
                String fEndStr = eventos.getFEndC();
                /*Calendar fIni = Calendar.getInstance();
                fIni.set(Integer.parseInt(fIniStr.substring(0,4)),Integer.parseInt(fIniStr.substring(5,7)),Integer.parseInt(fIniStr.substring(8,10)),Integer.parseInt(fIniStr.substring(11,13)),Integer.parseInt(fIniStr.substring(14,16)));
                Calendar fEnd = Calendar.getInstance();
                fEnd.set(Integer.parseInt(fEndStr.substring(0,4)),Integer.parseInt(fEndStr.substring(5,7)),Integer.parseInt(fEndStr.substring(8,10)),Integer.parseInt(fEndStr.substring(11,13)),Integer.parseInt(fEndStr.substring(14,16)));*/
                GregorianCalendar fIni = new GregorianCalendar(Integer.parseInt(fIniStr.substring(0,4)),Integer.parseInt(fIniStr.substring(5,7))-1,Integer.parseInt(fIniStr.substring(8,10)),Integer.parseInt(fIniStr.substring(11,13)),Integer.parseInt(fIniStr.substring(14,16)));
                GregorianCalendar fEnd = new GregorianCalendar(Integer.parseInt(fEndStr.substring(0,4)),Integer.parseInt(fEndStr.substring(5,7))-1,Integer.parseInt(fEndStr.substring(8,10)),Integer.parseInt(fEndStr.substring(11,13)),Integer.parseInt(fEndStr.substring(14,16)));
                Log.d("FechaInicio", fIniStr.substring(0,4) +"/" + fIniStr.substring(5,7) +"/" + fIniStr.substring(8,10) +"/" + fIniStr.substring(11,13) +"/" + fIniStr.substring(14,16));
                Intent addCalendarIntent = new Intent(Intent.ACTION_INSERT);
                addCalendarIntent.setType("vnd.android.cursor.item/event");
                addCalendarIntent.putExtra(CalendarContract.Events.TITLE,getResources().getText(R.string.EventCalendarTitle));
                addCalendarIntent.putExtra(CalendarContract.Events.DESCRIPTION,eventos.getTitle());
                addCalendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,fIni.getTimeInMillis());
                addCalendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,fEnd.getTimeInMillis());
                startActivity(addCalendarIntent);
                return true;
            case android.R.id.home:
                finish();
                return true;
           default:
               return super.onOptionsItemSelected(item);
        }
    }
}
