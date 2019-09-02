package sample;

import IncortaAPI.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;


public class Controller {
    @FXML
    private ScrollPane myScrollPane;

    @FXML
    private TextArea description1;

    @FXML
    private Button button1;

    @FXML
    private TextArea description2;

    @FXML
    private Button button2;

    @FXML
    private TextArea description3;

    @FXML
    private Button button3;

    @FXML
    private TextArea description4;

    @FXML
    private TextArea description5;

    @FXML
    private Button button4;

    @FXML
    void viewDescription(ActionEvent event) {
        description1.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
        description2.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
        description3.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
        description4.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
        description5.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
        String button_id = ((Button)event.getSource()).getId().substring(6, 7);
        int button_number = Integer.valueOf(button_id);
        ((Button)event.getSource()).setText("Change format");
        String description = "";
        switch (button_number) {
            case 1 :
                ReadingCSV1 read = new ReadingCSV1();
                try {
                    read.readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StatisticsMapOneMOneD mapBuilder = new StatisticsMapOneMOneD(
                        read.getDataset(), read.getMeasure(), read.getDimension(),
                        read.getTitle());
                DescreptionReader descreptionReader = new DescreptionReader();
                Templates templates = new Templates();
                String inputText = templates.generateDescription(mapBuilder.getStatisticsMap(),mapBuilder.getPatterns());
                description = inputText;
                description1.setText(description);
                description1.setWrapText(true);
                description1.setEditable(false);
                description1.setVisible(true);
                break;
            case 2 :
                ReadingCSV2 read2 = new ReadingCSV2();
                try {
                    read2.readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StatisticsMapOneMOneD mapBuilder2 = new StatisticsMapOneMOneD(
                        read2.getDataset(), read2.getMeasure(), read2.getDimension(),
                        read2.getTitle());
                DescreptionReader descreptionReader2 = new DescreptionReader();
                Templates templates2 = new Templates();
                String inputText2 = templates2.generateDescription(mapBuilder2.getStatisticsMap(),mapBuilder2.getPatterns());
                description = inputText2;
                description2.setText(description);
                description2.setWrapText(true);
                description2.setEditable(false);
                description2.setVisible(true);

                break;
            case 3 :
                ReadingCSV3 read3 = new ReadingCSV3();
                try {
                    read3.readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StatisticsMapOneMOneD mapBuilder3 = new StatisticsMapOneMOneD(
                        read3.getDataset(), read3.getMeasure(), read3.getDimension(),
                        read3.getTitle());
                DescreptionReader descreptionReader3 = new DescreptionReader();
                Templates templates3 = new Templates();
                String inputText3 = templates3.generateDescription(mapBuilder3.getStatisticsMap(),mapBuilder3.getPatterns());
                description = inputText3;
                description3.setText(description);
                description3.setWrapText(true);
                description3.setEditable(false);
                description3.setVisible(true);

                break;
            case 4 :
                ReadingCSV4 read4 = new ReadingCSV4();
                try {
                    read4.readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StatisticsMapOneMOneD mapBuilder4 = new StatisticsMapOneMOneD(
                        read4.getDataset(), read4.getMeasure(), read4.getDimension(),
                        read4.getTitle());
                DescreptionReader descreptionReader4 = new DescreptionReader();
                Templates templates4 = new Templates();
                String inputText4 = templates4.generateDescription(mapBuilder4.getStatisticsMap(),mapBuilder4.getPatterns());
                description = inputText4;
                description4.setText(description);
                description4.setWrapText(true);
                description4.setEditable(false);
                description4.setVisible(true);
                break;
            case 5 :
                ReadingCSV5 read5 = new ReadingCSV5();
                try {
                    read5.readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StatisticsMapOneMOneD mapBuilder5 = new StatisticsMapOneMOneD(
                        read5.getDataset(), read5.getMeasure(), read5.getDimension(),
                        read5.getTitle());
                DescreptionReader descreptionReader5 = new DescreptionReader();
                Templates templates5 = new Templates();
                String inputText5 = templates5.generateDescription(mapBuilder5.getStatisticsMap(),mapBuilder5.getPatterns());
                description = inputText5;
                description5.setText(description);
                description5.setWrapText(true);
                description5.setEditable(false);
                description5.setVisible(true);
                break;
        }

    }

}