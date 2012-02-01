package com.android.silentadvisor;

import java.util.Timer;
import com.android.silentadvisor.Mail;
import java.util.TimerTask;
import java.util.prefs.Preferences;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SilentAdvisorActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        //addPreferencesFromResource(R.xml.preferences);
        Button prefBtn = (Button) findViewById(R.id.button1);
        prefBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
		        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

		        switch (am.getRingerMode()) {
		            case AudioManager.RINGER_MODE_SILENT:
		                Log.i("MyApp","Silent mode");
		                sendSMS("5556", "estoy silenciado");
		               
		                break;
		            case AudioManager.RINGER_MODE_VIBRATE:
		                Log.i("MyApp","Vibrate mode");
		                sendSMS("5556", "estoy silenciado");
		                break;
		            case AudioManager.RINGER_MODE_NORMAL:
		                Log.i("MyApp","Normal mode");
		                break;
		        }

				
			}
        	
        	
        }, 0, 25000);
        	
        
        
    }

    private void sendSMS(String phoneNumber, String message)
    {        
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);  
        MandaMail("", "");
    }    
    private void MandaMail(String Destino, String message)
    {        
        Mail m = new Mail("cgarciabarrera@gmail.com", "brujitas"); 
        
        String[] toArr = {"htc21lextrend@gmail.com"}; 
        m.set_to(toArr);
        m.set_from("cgarciabarrera@gmail.com");
        
        m.set_subject("Mi movil esta en silencio"); 
        m.set_body("Estoy silenciado");
         
        try {
			m.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //SmsManager sms = SmsManager.getDefault();
        //sms.sendTextMessage(phoneNumber, null, message, null, null);        
    }    

    
}