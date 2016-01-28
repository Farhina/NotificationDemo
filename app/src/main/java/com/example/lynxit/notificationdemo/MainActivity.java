package com.example.lynxit.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
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

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle(notificationTitle);
        builder.setContentText(notificationMessage);
        builder.setTicker("New Message Alert!");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        int numMessages=0;
        builder.setNumber(++numMessages);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        PendingIntent resultPendingIntent = null;

        String[] events = new String[6];
        events[0] = new String("This is first line....");

        events[1] = new String("This is second line....");

        events[2] = new String("This is third line....");

        events[3] = new String("This is fourth line....");

        events[4] = new String("This is fifth line....");

        events[5] = new String("This is sixth line....");

        inboxStyle.setBigContentTitle("Big Title Details:");

        for(int i=0;i<events.length;i++)
        {
            inboxStyle.addLine(events[i]);
        }
        builder.setStyle(inboxStyle);

        Intent resultIntent = new Intent (this,NotificationView.class);

        TaskStackBuilder stackbuilder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            stackbuilder = TaskStackBuilder.create(this);

            stackbuilder.addParentStack(NotificationView.class);
            stackbuilder.addNextIntent(resultIntent);
            resultPendingIntent= stackbuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(9999,builder.build());
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
