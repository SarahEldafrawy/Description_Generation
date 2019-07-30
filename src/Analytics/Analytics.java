package Analytics;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import static Analytics.FIELDSCONSTANTS.DECREASING;
import static Analytics.FIELDSCONSTANTS.INCREASING;
import static java.util.stream.Collectors.toMap;

public class Analytics implements IAnalytics{

    private ArrayList<Pair<String, Double>> dataset; //Pair<dimension, measure_value>

    public Analytics(ArrayList<Pair<String, Double>> dataset) {
        this.dataset = dataset;
    }

    @Override
    public Pair<String, Double> getMax() {
        ArrayList<Double> arrayList = new ArrayList<Double>();

        for (int i = 0; i < dataset.size(); i++) {
            arrayList.add(dataset.get(i).getValue());
        }
        double max = Collections.max(arrayList);

        return new Pair<>((dataset.get(arrayList.indexOf(max))).getKey(), max);
    }

    @Override
    public Pair<String, Double> getMin() {
        ArrayList<Double> arrayList = new ArrayList<Double>();

        for (int i = 0; i < dataset.size(); i++) {
            arrayList.add(dataset.get(i).getValue());
        }

        Double min = Collections.min(arrayList);

        return new Pair<>((dataset.get(arrayList.indexOf(min))).getKey(), min);
    }

    @Override
    public ArrayList<Pair<String, Double>> getPatterns() {
        ArrayList<Pair<String, Double>> patternsList = new ArrayList<>();
        ArrayList<Double> normList = new ArrayList<>();
        //getList of Double values
        for (int i = 0; i < dataset.size(); i++) {
            normList.add(dataset.get(i).getValue());
        }
        //perform normalization
        norm(normList);
        System.out.println(normList);
//        normList.forEach(i -> normList.set(normList.indexOf(i), (double) Math.round(i*10)));
        for(int i = 0; i< normList.size(); i++) {
            normList.set(i,(double) Math.round(normList.get(i)*100)/10);
        }
        System.out.println(normList);
        //get occurences
        Map<Double, Long> collect = normList.stream().collect(
                Collectors.groupingBy(e -> e, Collectors.counting()));
        //remove 1 occurence
        collect.values().removeIf(value -> value == 1);
        //sorting map by descending values
        Map<Double, Long> sorted = collect
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        //->start loop stop at 2
        //find index of most occurences
        for(Map.Entry<Double, Long> entry : sorted.entrySet()) {
            //get keyValues and add them in a list
            // -> forloop
            //insert pattern in the list
            //-> again
            StringBuilder dimensions = new StringBuilder();
            ArrayList<Double> values = new ArrayList<>();
            for (int i = 0; i < normList.size(); i++) {
                if (normList.get(i).equals(entry.getKey())) {
                    values.add(dataset.get(i).getValue());
                    dimensions.append(dataset.get(i).getKey());
                    dimensions.append(", ");
                }
            }
            if (dimensions.length() > 0) {
                dimensions.replace(dimensions.lastIndexOf(", "), dimensions.lastIndexOf(",") + 1, "" );
                dimensions.replace(dimensions.lastIndexOf(", "), dimensions.lastIndexOf(",") + 1, " and" );
                patternsList.add(new Pair<>(dimensions.toString(), getMedian(values)));
            }
        }
        return patternsList;
    }
    private double getMedian(ArrayList<Double> list){
        Collections.sort(list);
        if (list.size()%2 == 1) {
            return (list.get(list.size()/2) + list.get(list.size()/2 - 1))/2;
        } else {
            return list.get(list.size() / 2);
        }
    }
    private void norm(ArrayList<Double> list) {
        double max = Collections.max(list);
        double min = Collections.min(list);
        for(int i = 0; i < list.size(); i++) {
            double val = (list.get(i) - min) / (max - min);
            list.set(i, val);
        }
    }

    @Override
    /*returns <<Increasing/Decreasing, rate>, <dimension, measure>>*/
    public Pair<String, Double> getSlope() {

        ArrayList<String> dimensions = new ArrayList<>(); //set of dimensions
        for (int i = 0; i< dataset.size(); i++) {
            if(!dimensions.contains(dataset.get(i).getKey())) {
                dimensions.add(dataset.get(i).getKey());
            }
        }

        double[][] points = new double[2][dataset.size()];
        for (int i = 0; i < dataset.size(); i++) {
            points[0][i] = dimensions.indexOf(dataset.get(i).getKey());
            points[1][i] = dataset.get(i).getValue();
        }
        System.out.println(points);
        // creating regression object, passing true to have intercept term
        SimpleRegression simpleRegression = new SimpleRegression(true);

        // passing data to the model
        // model will be fitted automatically by the class
        simpleRegression.addData(points);

        // querying for model parameters
//        System.out.println(simpleRegression.get);
        System.out.println(simpleRegression.getSlope());
        if (simpleRegression.getSlope() < 0) {
            return new Pair<>(INCREASING, (double) Math.round(simpleRegression.getSlope()*10)/10);
        } else {
            return new Pair<>(DECREASING, (double) Math.round(simpleRegression.getSlope()*10)/10);
        }
    }
}
