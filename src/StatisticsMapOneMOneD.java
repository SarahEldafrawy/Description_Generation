import Analytics.Analytics;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Analytics.FIELDSCONSTANTS.*;

public class StatisticsMapOneMOneD {


    private Map<String, Map<String,String>> statisticsMap;
    private ArrayList<Pair<String, Double>> patternsList;

    StatisticsMapOneMOneD(ArrayList<Pair<String, Double>>  dataset, String measure, String dimension, String chartType, String title) {
        // NOTE: WE CONSIDER ONLY BAR CHART AND LINE CHARTS
        statisticsMap = new HashMap<>();
        patternsList = new ArrayList<>();
        Analytics analyticsObj = new Analytics(dataset);
        if(chartType == LINE_CHART) {
            this.setRate(analyticsObj, measure, dimension);

        }
        this.setMax(analyticsObj, measure);
        this.setMin(analyticsObj, measure);
        this.detectPatterns(analyticsObj);
        this.addIntro(measure, dimension, chartType, title);
        this.checkValidAnalytics();
    }

    private void addIntro(String measure, String dimension, String chartType, String title) {
        /*    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String DIMENSION = "dimension";
    public static final String MEASURE = "measure";
    public static final String DIMENSION_TYPES = "dimension_types";

         */
        Map<String, String> map = new HashMap<>();
        map.put(TYPE, chartType);
        map.put(TITLE, title);
        map.put(MEASURE, measure);
        map.put(DIMENSION, dimension);
        map.put(DIMENSION_TYPES, "not yet implemented by sarah");
        this.statisticsMap.put(INTRODUCTION, map);
    }

    private void setMax(Analytics analyticsObj, String measure) {
        Pair<String, Double> returnedPair = analyticsObj.getMax();

        Map<String, String> map = new HashMap<>();
        map.put(MEASURE_MAX, measure);
        map.put(MEASURE_MAX_VALUE, returnedPair.getValue().toString());
        map.put(DIMENSION_MAX, returnedPair.getKey());

        this.statisticsMap.put(MAX, map);
    }

    private void setMin(Analytics analyticsObj, String measure) {
        Pair<String, Double> returnedPair = analyticsObj.getMin();

        Map<String, String> map = new HashMap<>();
        map.put(MEASURE_MIN, measure);
        map.put(MEASURE_MIN_VALUE, returnedPair.getValue().toString());
        map.put(DIMENSION_MIN, returnedPair.getKey());

        this.statisticsMap.put(MIN, map);
    }


    private void setRate(Analytics analyticsObj, String measure, String dimension) {

        Pair<String, Double> returnedPair = analyticsObj.getSlope();

        Map<String, String> map = new HashMap<>();
        map.put(MEASURE, measure); //discuss with kamal!!!
        map.put(SLOPE, returnedPair.getValue().toString());
        map.put(DIMENSION, dimension);

        this.statisticsMap.put(returnedPair.getKey(), map);
    }

    private void checkValidAnalytics() {
        //TODO implement
        //TODO what to check or validate?!!
        //TODO steady case instead of Increasing or Decreasing make slope equal 0
    }

    private void detectPatterns(Analytics analyticsObj) {
        this.patternsList = analyticsObj.getPatterns();
    }

    public Map<String, Map<String,String>> getStatisticsMap() {
        return this.statisticsMap;
    }

    public ArrayList<Pair<String, Double>> getPatterns() {
        return this.patternsList;
    }
}
