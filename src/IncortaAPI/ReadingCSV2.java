package IncortaAPI;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Analytics.FIELDSCONSTANTS.BAR_CHART;
import static Analytics.FIELDSCONSTANTS.LINE_CHART;

public class ReadingCSV2 {

    private String dimension;
    private String measure;
    private ArrayList<Pair<String, Double>> dataset; //Pair<dimension, measure_value>

    //"/home/saraheldafrawy/Templates-Analytics/src/Incorta-Analytics-data/data1.csv"
    public void readFile() throws IOException {
        dataset = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader("/home/saraheldafrawy/Templates-Analytics/src/Incorta-Analytics-data/data2.csv"));
        String row = csvReader.readLine();
        String[] data = row.split(",");
        this.dimension = data[0].replaceAll("\"", "");
        this.measure = data[1].replaceAll("\"", "");
        while ((row = csvReader.readLine()) != null) {
            data = row.split(",");
            dataset.add(new Pair<>(data[0].replaceAll("\"", ""),
                    (Double.parseDouble(data[1].replaceAll("\"", "")))));
        }
        System.out.println(dataset);
        csvReader.close();
    }

    public String getDimension() {
        return this.dimension;
    }

    public String getMeasure() {
        return this.measure;
    }

    public ArrayList<Pair<String, Double>> getDataset() {
        return this.dataset;
    }

    public String getTitle() {
        return LINE_CHART;
    }
}
