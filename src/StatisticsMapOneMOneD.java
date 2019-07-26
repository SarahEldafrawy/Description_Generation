import Analytics.Analytics;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Analytics.FIELDSCONSTANTS;

public class StatisticsMapOneMOneD {


    private Map<String, Map<String,String>> statisticsMap;
    private ArrayList<Pair<String, Double>> patternsList;

    StatisticsMapOneMOneD(ArrayList<Pair<String, Double>>  dataset, String measure, String dimension) {
        statisticsMap = new HashMap<>();
        patternsList = new ArrayList<>();
        Analytics analyticsObj = new Analytics(dataset);

        this.setMax(analyticsObj, measure);
        this.setMin(analyticsObj, measure);
        this.setRate(analyticsObj, measure, dimension);
        this.detectPatterns();
        this.checkValidAnalytics();
    }

    private void setMax(Analytics analyticsObj, String measure) {
        Pair<String, Double> returnedPair = analyticsObj.getMax();

        Map<String, String> map = new HashMap<>();
        map.put(FIELDSCONSTANTS.MEASURE_MAX, measure);
        map.put(FIELDSCONSTANTS.MEASURE_MAX_VALUE, returnedPair.getValue().toString());
        map.put(FIELDSCONSTANTS.DIMENSION_MAX, returnedPair.getKey());

        this.statisticsMap.put(FIELDSCONSTANTS.MIN, map);
    }

    private void setMin(Analytics analyticsObj, String measure) {
        Pair<String, Double> returnedPair = analyticsObj.getMin();

        Map<String, String> map = new HashMap<>();
        map.put(FIELDSCONSTANTS.MEASURE_MIN, measure);
        map.put(FIELDSCONSTANTS.MEASURE_MIN_VALUE, returnedPair.getValue().toString());
        map.put(FIELDSCONSTANTS.DIMENSION_MIN, returnedPair.getKey());

        this.statisticsMap.put(FIELDSCONSTANTS.MAX, map);
    }


    private void setRate(Analytics analyticsObj, String measure, String dimension) {

        Pair<String, Double> returnedPair = analyticsObj.getSlope();

        Map<String, String> map = new HashMap<>();
        map.put(FIELDSCONSTANTS.MEASURE, measure);
        map.put(FIELDSCONSTANTS.SLOPE, returnedPair.getValue().toString());
        map.put(FIELDSCONSTANTS.DIMENSION, dimension);

        this.statisticsMap.put(returnedPair.getKey(), map);
    }

    private void checkValidAnalytics() {
        //TODO implement
        //TODO what to check or validate?!!
    }

    private void detectPatterns() {
        // TODO implement detect patterns
        //TODO fill in patternsList
        /*
        [Dimension] A, B and C
        [Measure]
        [Value]
         */
    }

    public Map<String, Map<String,String>> getStatisticsMap() {
        return this.statisticsMap;
    }

    public ArrayList<Pair<String, Double>> getPatterns() {
        return this.patternsList;
    }
}
