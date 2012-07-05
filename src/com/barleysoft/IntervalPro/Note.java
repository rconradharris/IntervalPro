package com.barleysoft.IntervalPro;

import android.util.Log;


public class Note {
	private String mPitch;
	private int mOctave;
	public String[] PITCHES = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B" };
	
	public final static int UNISON = 0;
	public final static int MINOR_SECOND = 1;
	public final static int MAJOR_SECOND = 2;
	public final static int MINOR_THIRD = 3;
	public final static int MAJOR_THIRD = 4;
	public final static int PERFECT_FOURTH = 5;
	public final static int TRITONE = 6;
	public final static int PERFECT_FIFTH = 7;
	public final static int MINOR_SIXTH = 8;
	public final static int MAJOR_SIXTH = 9;
	public final static int MINOR_SEVENTH = 10;
	public final static int MAJOR_SEVENTH = 11;
	public final static int OCTAVE = 12;
	
	Note(String pitch, int octave) {
		mPitch = pitch;
		mOctave = octave;
	}
	
	Note(String note) {
    	mPitch = note.substring(0, note.length() - 1);
    	mOctave = Character.getNumericValue(note.charAt(note.length() - 1));	
	}
	
	public String toString() {
		return mPitch + Integer.toString(mOctave);
	}

	private int getPitchIndex(String pitch) {
		for (int i=0; i <= PITCHES.length; i++) {
			Log.d("IntervalPro", "blah " + pitch + " " + PITCHES[i]);
			if (pitch.equals(PITCHES[i])) {
				return i;
			}			
		}
		return -1;
	}
	
	public Note getInterval(int interval) {
    	int pitchIndex = getPitchIndex(mPitch);
    	Log.d("IntervalPro", "pitchIndex " + Integer.toString(pitchIndex));
    	int newPitchIndex = (pitchIndex + interval) % 12;
    	String newPitch = PITCHES[newPitchIndex];
    	
    	int octaveOffset = (pitchIndex + interval) / 12;
    	int newOctave = mOctave + octaveOffset;
    	
		return new Note(newPitch, newOctave);	
    }
	
}
