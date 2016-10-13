package mx.unam.posgrado.eventoscep.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import mx.unam.posgrado.eventoscep.R;
import mx.unam.posgrado.eventoscep.model.Eventos;

/**
 * Created by GuillermoAB on 27/09/2016.
 */

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder>{
    private List<Eventos> eventsList;
    private OnItemClickListener onItemClickListener;
    private String url;
    public EventAdapter(){}
    public EventAdapter(List<Eventos> eventos){this.eventsList = eventos;}

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view_holder,parent,false));
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Eventos events = eventsList.get(position);
        if(events.getUri2()!= null){
            url = events.getUri2().toString();
        }else{
            if(events.getUri()!=null){
                url= events.getUri();
            }else {
                url = "img-not-available.svg";
            }
        }
        holder.eventHolder_img.setImageURI("http://www.posgrado.unam.mx/sites/default/files/"+url);
        holder.setItemClick(events,onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setEventsList(List<Eventos> eventsList){
        this.eventsList = eventsList;
    }

    @Override
    public int getItemCount() {
        return eventsList!=null?eventsList.size():0;
    }
    public interface OnItemClickListener{
        void onItemClick(Eventos events);
    }
}
