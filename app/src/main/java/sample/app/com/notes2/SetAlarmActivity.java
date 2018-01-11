package sample.app.com.notes2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import database.DBhelper;
import model.Notes;

/**
 * Created by Nanda devi shetty b on 08-12-2017.
 */

 public class SetAlarmActivity extends Activity {
    private static SetAlarmActivity inst;
    DatePicker pickerDate;
    TimePicker pickerTime;
    Button buttonSetAlarm;
    private int isAlarm;
    DBhelper db;
    private Notes mNote;
    TextView info;
    String userChoosentime = null;
    String currernDate = null;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;


    final static int RQS_1 = 1;
    public static final String TAG = SetAlarmActivity.class.getSimpleName();

    public static SetAlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // getActionBar().setDisplayHomeAsUpEnabled(true);
        info = (TextView) findViewById(R.id.info);
        pickerDate = (DatePicker) findViewById(R.id.pickerdate);
        pickerTime = (TimePicker) findViewById(R.id.pickertime);


        Calendar now = Calendar.getInstance();

        pickerDate.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        buttonSetAlarm = (Button) findViewById(R.id.setalarm);
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
                cal.set(pickerDate.getYear(),
                        pickerDate.getMonth(),
                        pickerDate.getDayOfMonth(),
                        pickerTime.getCurrentHour(),
                        pickerTime.getCurrentMinute(),
                        00);


             /*   Intent i= new Intent(SetAlarmActivity.this, NotesAdapter.class);
                Bundle b=new Bundle();
                b.putString("Alarm","SET");
                i.putExtras(b);
                startActivity(i);*/


              /*  String call = String.valueOf(cal);

                Notes u=new Notes();
                u.date=call;
                db.addUser(u);
*/

                if (cal.compareTo(current) <= 0) {

                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                } else {
                    setAlarm(cal);
                }

                /*Intent intent = new Intent(SetAlarmActivity.this,AlarmReceiver.class);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setAction("sample.app.com.CUSTOM_INTENT");
                sendBroadcast(intent);
*/
            }
        });

    }

    private void setAlarm(Calendar targetCal) {



        info.setText("\n\n***\n"
                + "Alarm is set@ " + targetCal.getTime() + "\n"
                + "***\n");

      /*  Notes u= new Notes();
        String alarmSet=info.getText().toString();
        //u.date=alarmSet;


        //isAlarm = 1;
       // u.setAlarm(isAlarm);
        db.addUser(u);*/

     //   finish();
       // Notes u= new Notes();
      /*  String alarm = info.getText().toString();
        Intent i = new Intent(this, NotesAdapter.class);
        Bundle bundle=new Bundle();
        bundle.putString("setAlarm", alarm);
        i.putExtras(bundle);
        startActivity(i);*/

        Intent intent = getIntent();
        if (null != intent) {
            String name = intent.getExtras().getString("name");
            String content = intent.getExtras().getString("content");
            intent = new Intent(SetAlarmActivity.this, AlarmReceiver.class);
            intent.putExtra("names", name);
            intent.putExtra("description", content);
            Log.d(TAG, "name" + name + "," + "," + "content" + content);

        }
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        }


    }





/* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
             userChoosentime = String.valueOf(pickerTime.getHour()+pickerTime.getMinute());
        }
         currernDate = String.valueOf(pickerDate.getDayOfMonth());

        String userChoosenFinalDateandTime= currernDate.concat(userChoosentime);*/
