package ba.leftor.exercises.lifecycle;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;

/**
 * TracerActivity class is used to report lifecycle events via notifications
 * Created by USER on 16.1.2016.
 */
public class TracerActivity extends Activity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        notify("onCreate");
    }


    @Override
    protected void onPause(){
        super.onPause();
        notify("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        notify("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notify("onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        notify("onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        notify("onSaveInstanceState");
    }

    private void notify(String methodName) {
        String name = this.getClass().getName();
        String[]strings = name.split("\\.");
        Notification notification = new Notification.Builder(this).setContentTitle(methodName + " " + strings[strings.length - 1]).setAutoCancel(true).setContentText(name).build();
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify((int)System.currentTimeMillis(), notification);

    }
}
