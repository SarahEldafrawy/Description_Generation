package Analytics;

import javafx.util.Pair;

import java.util.ArrayList;

public interface IAnalytics {

    /*returns dimension with measure*/
    Pair<String, Double> getMax();

    /*returns dimension with measure*/
    Pair<String, Double> getMin();

    /*returns dimensions with same value*/ //TODO ASK ISLAM bigger than two or more???
    ArrayList<Pair<String, Double>> getPatterns();

    /*returns <Increasing/Decreasing, rate> */
    Pair<String, Double> getSlope();

}
