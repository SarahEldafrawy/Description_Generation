import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DescreptionReader {
    public static final String descriptionFileName = "description.wav";

    public static void getFile(String urlToRead) throws Exception {
        URL website = new URL(urlToRead);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(descriptionFileName);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        try {
            File descriptionFile = new File(descriptionFileName);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(descriptionFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        }
        catch (Exception FileNotFoundException){
            throw FileNotFoundException;
        }
    }

}
