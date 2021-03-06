package showmilhao.poo.ufba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Audio implements Runnable {
	
	int audioid;

	public static void tocarSom(File nomeDoSom, int intervaloExecucao) throws IOException, InterruptedException {
		InputStream som;
		
		try {
			
			som = new FileInputStream(nomeDoSom);
			AudioStream audio = new AudioStream(som);
			
			AudioPlayer.player.start(audio);
			
			Thread.sleep(intervaloExecucao);
			
			AudioPlayer.player.stop(audio);

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void run() {
	if(audioid == 0){
		File perdeu = new File("SoundofSilence.wav");
		try {
			tocarSom(perdeu, 21000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	else {
		File ganhou = new File("ganhou.wav");
	try {
		tocarSom(ganhou, 37000);
	} catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}		
}

	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
	}

}
