//import IncortaAPI.*;
//import sample.DescreptionReader;
//import sample.StatisticsMapOneMOneD;

//
//import static java.util.stream.Collectors.toMap;
//
//public class Main {
//
//    public static void main (String[]args) throws Exception {
//        ReadingCSV3 read = new ReadingCSV3();
//        read.readFile();
//        StatisticsMapOneMOneD mapBuilder = new StatisticsMapOneMOneD(
//                read.getDataset(), read.getMeasure(), read.getDimension(),
//                read.getTitle());
//        System.out.println(mapBuilder.getStatisticsMap());
//        System.out.println(mapBuilder.getPatterns());
////        System.out.println();
////        System.out.println("ANALYTICS");
////        Analytics analyticsObj = new Analytics(read.getDataset());
////        System.out.println(analyticsObj.getMax());
////        System.out.println(analyticsObj.getMin());
////        System.out.println(analyticsObj.getSlope());
////        System.out.println("Patterns");
////        System.out.println(analyticsObj.getPatterns());
////
//
////
////    public static void main (String[]args) throws Exception{
////        //Main su = new Main();
/////*
////        su.init("kevin16");
////        // high quality
////        su.doSpeak("Data can be represented in many ways. The 4 main types of graphs are a bar graph or bar chart, line graph, pie chart, and diagram." +
////                " Bar graphs are used to show relationships between " +
////                "different data series that are independent of each other");
////        su.terminate();
////*/
//        DescreptionReader descreptionReader = new DescreptionReader();
//        Templates templates = new Templates();
//        String inputText = templates.generateDescription(mapBuilder.getStatisticsMap(),mapBuilder.getPatterns());
//
//        String description = inputText;
//        //description = description.replaceAll(",","\n");
//        System.out.println(description);
//        //inputText = inputText.replaceAll(" ","+");
////        DescriptionReader descriptionReader = new DescriptionReader();
////        descriptionReader.generateFile(description);
//        //System.out.println(inputText);
//        //put it in the url
//        //descreptionReader.getFile("http://localhost:59125/process?INPUT_TEXT=" +
//        //        inputText +
//         //       "&INPUT_TYPE=TEXT&OUTPUT_TYPE=AUDIO&AUDIO=WAVE_FILE&LOCALE=en_US&voice=cmu-rms-hsmm");
//        /*
//        * voices:
//dfki-spike-hsmm en_GB male hmm
//dfki-spike en_GB male unitselection general
//dfki-prudence-hsmm en_GB female hmm
//dfki-prudence en_GB female unitselection general
//dfki-poppy-hsmm en_GB female hmm
//dfki-poppy en_GB female unitselection general
//dfki-obadiah-hsmm en_GB male hmm
//dfki-obadiah en_GB male unitselection general
//cmu-slt-hsmm en_US female hmm
//cmu-slt en_US female unitselection general
//cmu-rms-hsmm en_US male hmm
//cmu-rms en_US male unitselection general
//cmu-bdl-hsmm en_US male hmm
//cmu-bdl en_US male unitselection general
//        *
//        * */
//
//    }
//}
