package mx.unam.posgrado.eventoscep;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.unam.posgrado.eventoscep.adapter.EventAdapter;
import mx.unam.posgrado.eventoscep.data.EventData;
import mx.unam.posgrado.eventoscep.data.EventInterface;
import mx.unam.posgrado.eventoscep.model.Eventos;
import mx.unam.posgrado.eventoscep.model.Events;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GuillermoAB on 27/09/2016.
 */

public class EventoLista extends Fragment {
    @BindView(R.id.rv_EventosLista)
    RecyclerView eventsListRecycler;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.evento_lista,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        eventsListRecycler.setLayoutManager(linearLayoutManager);
        eventsListRecycler.setItemAnimator(new DefaultItemAnimator());
        final EventAdapter eventAdapter = new EventAdapter();
        eventAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(Eventos events) {
                //Snackbar.make(findViewById(android.R.id.content),"Dió TAP",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),EventoDetails.class);
                intent.putExtra("eventos",events);
                startActivity(intent);
            }
        });
        EventInterface eventInterface = EventData.getRetofitInstance().create(EventInterface.class);
        Call<Events> modeleventsCall = eventInterface.getEvents();
        modeleventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                if(response.body()!=null){
                    eventAdapter.setEventsList(response.body().getEventos());
                    eventsListRecycler.setAdapter(eventAdapter);
                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.d("ErrorConexion",t.getMessage());
            }
        });
    }
}/*AppCompatActivity{
    @BindView(R.id.rv_EventosLista)
    RecyclerView eventsListRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento_lista);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        eventsListRecycler.setLayoutManager(linearLayoutManager);
        eventsListRecycler.setItemAnimator(new DefaultItemAnimator());
        final EventAdapter eventAdapter = new EventAdapter();
        eventAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(Eventos events) {
                //Snackbar.make(findViewById(android.R.id.content),"Dió TAP",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),EventoDetails.class);
                intent.putExtra("eventos",events);
                startActivity(intent);
            }
        });
        EventInterface eventInterface = EventData.getRetofitInstance().create(EventInterface.class);
        Call<Events> modeleventsCall = eventInterface.getEvents();
        modeleventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                if(response.body()!=null){
                    eventAdapter.setEventsList(response.body().getEventos());
                    eventsListRecycler.setAdapter(eventAdapter);
                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.d("ErrorConexion",t.getMessage());
            }
        });
    }
}*/
