package com.barleysoft.IntervalPro;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

public class IntervalProActivity extends Activity implements OnItemSelectedListener {
	IntervalPlayer mIntervalPlayer;
	int mLastInterval = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        mIntervalPlayer = new IntervalPlayer(this);
        
        Spinner spinnerInterval = (Spinner) findViewById(R.id.spinnerInterval);
        spinnerInterval.setOnItemSelectedListener(this);
    }
    
    public void onButtonPlayClicked(View v) {
    	Random rand = new Random();
    	mLastInterval = rand.nextInt(12 + 1);
    	
    	Note rootNote = new Note("C4");
    	mIntervalPlayer.play(rootNote, mLastInterval);
    }

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		
		
		if (mLastInterval == pos) {
			Toast.makeText(parent.getContext(), 
					"Correct!",
					Toast.LENGTH_SHORT).show();
		} else {	
			Toast.makeText(parent.getContext(), 
					"Wrong, " + parent.getItemAtPosition(mLastInterval).toString(),
					Toast.LENGTH_SHORT).show();
		}
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}