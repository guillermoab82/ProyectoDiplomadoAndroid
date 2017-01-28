package mx.unam.posgrado.eventoscep.data;

import android.support.v7.view.ActionMode;

import mx.unam.posgrado.eventoscep.model.Events;
import mx.unam.posgrado.eventoscep.model.USERRequest;
import mx.unam.posgrado.eventoscep.model.USERResponse;
import mx.unam.posgrado.eventoscep.model.UserResponseWS;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by GuillermoAB on 27/09/2016.
 */

public interface EventInterface {
    @GET("webapp/get.php?status=1")
    Call<Events> getEvents();

    @GET("scepw.svc/find/{id}")
    Call<USERResponse> getUser(@Path("id") String id);

    @POST("scepw.svc/create")
    Call<UserResponseWS> getTokenAccess(@Body USERRequest USERRequest);

}
