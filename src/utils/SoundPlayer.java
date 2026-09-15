package utils;

import javax.sound.sampled.*;


public class SoundPlayer {

   
    public static void playWin() {
        playTone(520, 120);  
        playTone(660, 180);   
    }

    public static void playLose() {
        playTone(300, 120);   
        playTone(220, 200);  
    }

    public static void playDraw() {
        playTone(400, 180);   
    }

    public static void playClick() {
        playTone(600, 40);  
    }

   
    private static void playTone(int frequencyHz, int durationMs) {
        Thread t = new Thread(() -> {
            try {
                int sampleRate  = 44100;
                int numSamples  = sampleRate * durationMs / 1000;
                byte[] buffer   = new byte[numSamples * 2];  

                for (int i = 0; i < numSamples; i++) {
                   
                    double angle = 2.0 * Math.PI * i * frequencyHz / sampleRate;
                    double sine  = Math.sin(angle);

                   
                    double fadeOut = (i > numSamples * 0.9)
                        ? (numSamples - i) / (numSamples * 0.1)
                        : 1.0;

                   
                    int value = (int) (sine * fadeOut * Short.MAX_VALUE * 0.6);
                    buffer[2 * i]     = (byte) (value & 0xFF);
                    buffer[2 * i + 1] = (byte) ((value >> 8) & 0xFF);
                }

                AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
                DataLine.Info   info = new DataLine.Info(SourceDataLine.class, format);

                if (!AudioSystem.isLineSupported(info)) return;

                SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();
                line.write(buffer, 0, buffer.length);
                line.drain();
                line.close();

            } catch (Exception e) {
               
                System.out.println("Sound skipped: " + e.getMessage());
            }
        });
        t.setDaemon(true);   
        t.start();
    }
}