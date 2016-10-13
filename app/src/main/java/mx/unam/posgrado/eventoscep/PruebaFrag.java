package mx.unam.posgrado.eventoscep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GuillermoAB on 05/10/2016.
 */

public class PruebaFrag extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_frag);
        ButterKnife.bind(this);
        getFragmentManager().beginTransaction().replace(R.id.main_container,new EventoLista()).commit();
    }
}
