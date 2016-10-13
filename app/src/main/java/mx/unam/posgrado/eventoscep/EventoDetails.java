package mx.unam.posgrado.eventoscep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_details);
        ButterKnife.bind(this);
        if(getIntent().getExtras()!=null){
            Eventos eventos = (Eventos) getIntent().getSerializableExtra("eventos");
            title.setText(eventos.getTitle());
            img.setImageURI("http://www.posgrado.unam.mx/sites/default/files/"+eventos.getUri());
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
}
