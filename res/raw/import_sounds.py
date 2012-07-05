"""Import Sound Files"""
import os

LOW_OCTAVE = 5
HIGH_OCTAVE = 5

NOTES = "C Db D Eb E F Gb G Ab A Bb B".split()
LENGTH = 1.5 # number of seconds to keep

for octave in range(LOW_OCTAVE, HIGH_OCTAVE + 1):
    for note in NOTES:
        # Grab
        url = "http://theremin.music.uiowa.edu/sound%%20files/MIS/Piano_Other"\
              "/piano/Piano.mf.%s%d.aiff" % (note, octave)
        print "getting %s" % url
        os.system("curl -q -O %s" % url)

        aiff_file = os.path.basename(url)
        mp3_file = aiff_file.replace('.aiff', '.mp3')

        # Convert
        os.system("sox %s %s trim 0 %f" % (aiff_file, mp3_file, LENGTH))

        # Rename
        new_path = mp3_file.lower()
        new_path = new_path.replace('.', '_')
        new_path = new_path.replace('_mf', '')
        new_path = new_path.replace('_mp3', '')
        os.rename(mp3_file, new_path)

        os.unlink(aiff_file)
