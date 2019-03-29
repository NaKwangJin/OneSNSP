package com.example.onesns;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FriendActivity extends AppCompatActivity {

    TextView date;
    TextView time1;

    UsedAsync asyncTask;
    ProgressHandler handler;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        date = (TextView) findViewById(R.id.date);
        time1 = (TextView) findViewById(R.id.time);

        handler = new ProgressHandler();
        runTime();

    }

    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            date.setText(time);
        }
    }

    public void runTime() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        time = sdf.format(new Date(System.currentTimeMillis()));

                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);

                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        });
        thread.start();

        asyncTask = new UsedAsync();
        asyncTask.execute();
    }

    class UsedAsync extends AsyncTask<Integer, Integer, Integer> {
        Calendar cal;
        String timeGre;

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (isCancelled() == false) {
                cal = new GregorianCalendar();
                timeGre = String.format("%d/%d/%d %d:%d:%d", cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR),
                        cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
                publishProgress();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }

            return null;
        }


        @Override
        protected void onPreExecute() {
            cal = new GregorianCalendar();
            timeGre = String.format("%d/%d/%d %d:%d:%d", cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR),
                    cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
            time1.setText(timeGre);
            Log.d("time1", timeGre);

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            time1.setText(timeGre);

            super.onProgressUpdate(values);
        }
    }


}
