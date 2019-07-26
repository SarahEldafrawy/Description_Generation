import java.beans.PropertyVetoException;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class Main {

    SynthesizerModeDesc desc;
    Synthesizer synthesizer;
    Voice voice;
    //hello

    public void init(String voiceName)
            throws EngineException, AudioException, EngineStateError,
            PropertyVetoException
    {
        if (desc == null) {

            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            desc = new SynthesizerModeDesc(Locale.US);
            Central.registerEngineCentral
                    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            synthesizer = Central.createSynthesizer(desc);
            synthesizer.allocate();
            synthesizer.resume();
            SynthesizerModeDesc smd =
                    (SynthesizerModeDesc)synthesizer.getEngineModeDesc();
            Voice[] voices = smd.getVoices();
            Voice voice = null;
//            Voice voice= new Voice();
//            voice.setAge(Voice.AGE_CHILD | Voice.AGE_TEENAGER);
            for(int i = 0; i < voices.length; i++) {
                    System.out.println(voices[i].getName());
                if(voices[i].getName().equals(voiceName)) {
                    voice = voices[i];
                }
            }
            synthesizer.getSynthesizerProperties().setVoice(voice);
            synthesizer.getSynthesizerProperties().setSpeakingRate(140);
            synthesizer.getSynthesizerProperties().setPitch(110);
        }

    }

    public void terminate() throws EngineException, EngineStateError {
        synthesizer.deallocate();
    }

    public void doSpeak(String speakText)
            throws EngineException, AudioException, IllegalArgumentException,
            InterruptedException
    {
        synthesizer.speakPlainText(speakText, null);
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

    }


    public static void main (String[]args) throws Exception{
        Main su = new Main();

        su.init("kevin16");
        // high quality
        su.doSpeak("Data can be represented in many ways. The 4 main types of graphs are a bar graph or bar chart, line graph, pie chart, and diagram." +
                " Bar graphs are used to show relationships between " +
                "different data series that are independent of each other");
        su.terminate();
    }
} 