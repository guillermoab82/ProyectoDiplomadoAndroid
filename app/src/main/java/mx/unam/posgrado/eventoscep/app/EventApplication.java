package mx.unam.posgrado.eventoscep.app;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by GuillermoAB on 29/09/2016.
 */

public class EventApplication extends Application {
    @Override
    public void onCreate() {
        Fresco.initialize(this);
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
    }
}
