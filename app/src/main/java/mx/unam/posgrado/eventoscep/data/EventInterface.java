package mx.unam.posgrado.eventoscep.data;

import mx.unam.posgrado.eventoscep.model.Events;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by GuillermoAB on 27/09/2016.
 */

public interface EventInterface {
    @GET("webapp/get.php?status=1")
    Call<Events> getEvents();

}
