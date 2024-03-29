package IncortaAPI;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static Analytics.FIELDSCONSTANTS.BAR_CHART;

public class ReadingCSV5 {

    private String dimension;
    private String measure;
    private ArrayList<Pair<String, Double>> dataset; //Pair<dimension, measure_value>

    //"/home/saraheldafrawy/Templates-Analytics/src/Incorta-Analytics-data/data1.csv"
    public void readFile() throws IOException {
        dataset = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader("/home/saraheldafrawy/Templates-Analytics/src/Incorta-Analytics-data/data5.csv"));
        String row = csvReader.readLine();
        String[] data = row.split(",");
        this.dimension = data[0].replaceAll("\"", "");
        this.dimension.concat(" in ").concat(data[1]);
        this.measure = data[2].replaceAll("\"", "");
        while ((row = csvReader.readLine()) != null) {
            data = row.split(",");
            dataset.add(new Pair<>(data[0].replaceAll("\"", "").concat(" in ").concat(data[1]).replaceAll("\"", ""),
                    (Double.parseDouble(data[2].replaceAll("\"", "").substring(0, data[2].indexOf("."))))));
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
        return BAR_CHART;
    }
}
