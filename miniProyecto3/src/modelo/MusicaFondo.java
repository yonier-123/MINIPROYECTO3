package modelo;

import javax.sound.sampled.*;//esto es la clase para manejar audios
import java.io.File;//para manejar archivos

public class MusicaFondo {
    private Clip clip; //Clip es el objeto que reproduce el audios

    public void reproducirMusica(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.out.println("No se pudo reproducir la música.");
        }
    }

    public void detenerMusica() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}