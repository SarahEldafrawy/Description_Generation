import Analytics.Analytics;
import IncortaAPI.ReadingCSV;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static Analytics.FIELDSCONSTANTS.BAR_CHART;
import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main (String[]args) throws Exception {
//        List<Double> list = new ArrayList<>();
//
//        list.add(1.0);
//
//        list.add(9.8);
//        list.add(9.8);
//        list.add(3.4);
//        list.add(3.4);
//        list.add(3.4);
//
//        Map<Double, Long> collect = list.stream().collect(
//                Collectors.groupingBy(e -> e, Collectors.counting()));
//        System.out.println(collect);
//
//        collect.values().removeIf(value -> value == 1);
//        System.out.println(collect);
//
//        Map<Double, Long> sorted = collect
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(
//                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
//                                LinkedHashMap::new));
//        System.out.println(sorted);

        ReadingCSV read = new ReadingCSV();
        read.readFile("/home/saraheldafrawy/Templates-Analytics/src/Incorta-Analytics-data/data1.csv");
        StatisticsMapOneMOneD mapBuilder = new StatisticsMapOneMOneD(
                read.getDataset(), read.getMeasure(), read.getDimension(),
                BAR_CHART, "Products Revenue From branches");
        System.out.println(mapBuilder.getStatisticsMap());
        System.out.println(mapBuilder.getPatterns());
        System.out.println();
        System.out.println("ANALYTICS");
        Analytics analyticsObj = new Analytics(read.getDataset());
        System.out.println(analyticsObj.getMax());
        System.out.println(analyticsObj.getMin());
        System.out.println(analyticsObj.getSlope());
        System.out.println("Patterns");
        System.out.println(analyticsObj.getPatterns());


//
//    public static void main (String[]args) throws Exception{
//        //Main su = new Main();
///*
//        su.init("kevin16");
//        // high quality
//        su.doSpeak("Data can be represented in many ways. The 4 main types of graphs are a bar graph or bar chart, line graph, pie chart, and diagram." +
//                " Bar graphs are used to show relationships between " +
//                "different data series that are independent of each other");
//        su.terminate();
//*/
        DescreptionReader descreptionReader = new DescreptionReader();
        Templates templates = new Templates();
        String inputText = templates.generateDescription(mapBuilder.getStatisticsMap(),mapBuilder.getPatterns());
        //inputText = inputText.replaceAll(" ","+");
        System.out.println(inputText);
        //put it in the url
//        descreptionReader.getFile("http://localhost:59125/process?INPUT_TEXT=" +
//                inputText +
//                "&INPUT_TYPE=TEXT&OUTPUT_TYPE=AUDIO&AUDIO=WAVE_FILE&LOCALE=en_US");
    }
}
