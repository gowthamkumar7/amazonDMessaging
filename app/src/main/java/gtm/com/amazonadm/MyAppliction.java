package gtm.com.amazonadm;

import android.app.Application;

import com.amazon.device.messaging.ADM;

/**
 * Created by Administrator on 8/21/2017.
 */

public class MyAppliction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ADM adm = new ADM(getApplicationContext());
        if (adm.isSupported()) {
            if (adm.getRegistrationId() == null) {
                adm.startRegister();
            } else {
                /* Send the registration ID for this app instance to your server. */
                /* This is a redundancy since this should already have been performed at registration time from the onRegister() callback */
                /* but we do it because our python server doesn't save registration IDs. */
                final MyServerMsgHandler srv = new MyServerMsgHandler();
                srv.registerAppInstance(getApplicationContext(), adm.getRegistrationId());
            }
        }
    }
}
