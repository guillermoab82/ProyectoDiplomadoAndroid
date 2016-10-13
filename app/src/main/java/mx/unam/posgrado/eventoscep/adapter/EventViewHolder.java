package mx.unam.posgrado.eventoscep.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.unam.posgrado.eventoscep.R;
import mx.unam.posgrado.eventoscep.adapter.EventAdapter;
import mx.unam.posgrado.eventoscep.model.Eventos;
import mx.unam.posgrado.eventoscep.model.Events;

/**
 * Created by GuillermoAB on 27/09/2016.
 */

public class EventViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.eventHolder_img) SimpleDraweeView eventHolder_img;
    private EventAdapter.OnItemClickListener onItemListener;
    private Eventos events;
    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void setItemClick(Eventos events, EventAdapter.OnItemClickListener onItemClickListener){
        this.events = events;
        this.onItemListener = onItemClickListener;
    }
    @OnClick(R.id.eventHolder_img)
    public void onViewClick(View view){
        if(onItemListener!=null) {
            onItemListener.onItemClick(events);
        }
    }
}
