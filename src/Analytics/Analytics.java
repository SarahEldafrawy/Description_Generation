package Analytics;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import static Analytics.FIELDSCONSTANTS.DECREASING;
import static Analytics.FIELDSCONSTANTS.INCREASING;

public class Analytics implements IAnalytics{

    private ArrayList<Pair<String, Double>>  dataset; //Pair<dimension, measure_value>

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
        //TODO implement

        //Map<String, Long> counts =
        //    list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<Double> normList = new ArrayList<>();
        //getList of Double values
        for (int i = 0; i < dataset.size(); i++) {
            list.add(dataset.get(i).getValue());
            normList.add(dataset.get(i).getValue());
        }
        //perform normalization
        norm(normList);
        //get occurences

        //->start loop stop at 2
        //find index of most occurences

        //get keyValues and add them in a list -> forloop
        // concatenate using comma and 'and'

        //insert pattern in the list
        //-> again
        return null;
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
        //TODO check if its working correctly

        ArrayList<String> dimensions = new ArrayList<>(); //set of dimensions
        for (int i = 0; i< dataset.size(); i++) {
            if(!dimensions.contains(dataset.get(i).getKey())) {
                dimensions.add(dataset.get(i).getKey());
            }
        }

        double[][] points = new double[2][dataset.size()];
        for (int i = 0; i < dataset.size(); i++) {
            points[0][i] = dimensions.indexOf(dataset.get(i).getKey());
            points[1][i] = dataset.get(i).getValue(); //TODO check if this is working
        }

        // creating regression object, passing true to have intercept term
        SimpleRegression simpleRegression = new SimpleRegression(true);

        // passing data to the model
        // model will be fitted automatically by the class
        simpleRegression.addData(points);

        // querying for model parameters
        System.out.println("slope = " + simpleRegression.getSlope());
        System.out.println("intercept = " + simpleRegression.getIntercept());

        if (simpleRegression.getSlope() > 0) {
            return new Pair<>(INCREASING, simpleRegression.getSlope());
        } else {
            return new Pair<>(DECREASING, simpleRegression.getSlope());
        }
    }
}
