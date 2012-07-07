package com.barleysoft.IntervalPro;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;

public class NotePlayer {
	private SoundPool mSoundPool;
	private HashMap<String, Integer> mSoundsMap;
	private Context mContext;
	private String mInstrument = "piano";
	private AudioManager mAudioManager;

	private int LOW_OCTAVE = 4;
	private int HIGH_OCTAVE = 5;

	private String[] PITCHES = { "C", "Db", "D", "Eb", "E", "F", "Gb", "G",
			"Ab", "A", "Bb", "B" };

	public NotePlayer(Context context) {
		mContext = context;
		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		mSoundsMap = new HashMap<String, Integer>();
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);

		for (int octave = LOW_OCTAVE; octave <= HIGH_OCTAVE; octave++) {
			for (String pitch : PITCHES) {
				int res = getSoundResourceIdentifier(pitch + octave);
				mSoundsMap.put(pitch + Integer.toString(octave), mSoundPool.load(mContext, res, 1));
			}
		}
	}

	private int getSoundResourceIdentifier(String note) {
		String resource_name = mInstrument + "_" + note.toLowerCase();
		return mContext.getResources().getIdentifier(resource_name, "raw",
				mContext.getPackageName());
	}

	public void playNote(Note note) {
		float rate = 1.0f;
		float streamVolumeCurrent = mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mAudioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;
		mSoundPool.play(mSoundsMap.get(note.toString()), volume, volume, 1, 0,
				rate);
	}

	public void playInterval(Note rootNote, int interval, int delay) {
		final Note nextNote = rootNote.getInterval(interval);
		playNote(rootNote);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				playNote(nextNote);
			}
		}, delay);
	}

}
