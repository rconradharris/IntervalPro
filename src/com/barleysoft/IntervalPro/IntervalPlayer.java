package com.barleysoft.IntervalPro;

import android.content.Context;
import android.os.Handler;

public class IntervalPlayer {
	private static int DELAY = 1000;
	private NotePlayer mNotePlayer;

	public IntervalPlayer(Context context) {
		mNotePlayer = new NotePlayer(context);
	}

	public void play(Note rootNote, int interval) {
		final Note nextNote = rootNote.getInterval(interval);
		mNotePlayer.play(rootNote);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				mNotePlayer.play(nextNote);
			}
		}, DELAY);
	}
}
