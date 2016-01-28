package com.example.lynxit.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Notify("New Message","You 've received a new message");
            }
        });
    }

    private void Notify(String notificationTitle, String notificationMessage){
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification =  null;//new Notification(R.mipmap.ic_launcher,"New Message",System.currentTimeMillis());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);


        Intent notificationIntent = new Intent(this,NotificationView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        notification = builder.setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_launcher).setTicker(notificationMessage).
                setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentTitle(notificationTitle).setContentText(notificationMessage).build();
        notificationManager.notify(9999, notification);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
