import Analytics.Analytics;
import IncortaAPI.ReadingCSV;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static Analytics.FIELDSCONSTANTS.BAR_CHART;
import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main (String[]args) throws IOException {
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



    }
} 