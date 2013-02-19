package normalStage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

public class MusicPlayer extends Thread {
	
	private boolean stop = false;
	int level;
	public MusicPlayer(int i)
	{
		level = i;
	}
		
	private void rawplay(AudioFormat targetFormat,AudioInputStream din) throws IOException, LineUnavailableException
	{
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null)
		{
		//Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			
			while (nBytesRead != -1)
			{
				nBytesRead = din.read(data, 0, data.length);

				if (nBytesRead != -1)
					nBytesWritten = line.write(data, 0, nBytesRead);
				
				
				if (stop)
				{
					break;
				}
				
			}
		// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}
	}

	public void run()
	{
		MpegAudioFileReader reader = new MpegAudioFileReader();
		try {
			
			File file = new File("resources/level"+level+"/"+level+"sound.mp3");
			if(level==7) file = new File("resources/Panda.mp3");
			AudioInputStream in;
			AudioInputStream din;
			AudioFormat baseFormat;
			AudioFormat decodedFormat;

//			 Play now. LOOP HERE
			try {
				while(true){
					
					in = AudioSystem.getAudioInputStream(file);
					din = null;
					baseFormat = in.getFormat();
					decodedFormat =
					    new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					                    baseFormat.getSampleRate(),
					                    16,
					                    baseFormat.getChannels(),
					                    baseFormat.getChannels() * 2,
					                    baseFormat.getSampleRate(),
					                    false);
					din = AudioSystem.getAudioInputStream(decodedFormat, in);
					this.rawplay(decodedFormat, din);
					in.close();
					if(stop) break;
				}
			} catch (LineUnavailableException e) {

				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
			
		}

	private SourceDataLine getLine(AudioFormat audioFormat)	throws LineUnavailableException
	{
		SourceDataLine res = null;
		SourceDataLine.Info info = new SourceDataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}
		
	
	
	public void requestStop()
	{
		stop = true;
	}
	
}
