import javafx.util.Pair;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/*
* intro
* min
* max
* pattern
* increase
* decrease
* steady
*
* This {2} deals with the {3} as a relation between {1} and {0} particularly in {4},
* We can see that the lowest {2} recorded was {0} for {1},
* and the highest {2} recorded was {0} for {1},
* looking through the data some certain patterns have been detected {0},
* over the {1} the {0} has increased by {2},
* over the {1} the {0} has decreased by {2},
* There has been no change in {1} over the {0};
*/

/*
In the following years the total growth went down to about 250,000 in 1998/99.
From that time on the Canadian population has been gradually growing again although the natural increase slows down.
So we can say that the growth of the population in Canada is based on migration.
*/


public class Templates {
    //todo fillin templates : i:template , j:sentence
    /*
      first sentence:intro
      second sentence:min
      third sentence:max
      fourth sentence:pattern
      fifth sentence:increase/decrease (rate)
     */
    private final int intro=0,min=1,max=2,patt=3,inc=4,dec=5,steady=6;
    private String [][] templates = new String [4][];
    public Templates(){
        createTemplates();
    }
    private void createTemplates(){
        //todo keep the measure and the dimension from the first sentence to use then
        templates[0] = new String[7];//j=4:increase, j=5:decrease
        templates[1] = new String[7];//j=4:increase, j=5:decrease
        templates[2] = new String[7];//j=4:increase, j=5:decrease
        templates[0][intro] = "This {2} deals with the {3} as a relation between {1} and {0} particularly in {4}";
        //todo to put or not to put dim_unit
        //0:dimension, 1:measure, 2:type, 3:title, 4:dimension_type

        templates[0][min] = ",We can see that the lowest {2} recorded was {0} for {1}";
        //0:measure_min_value, 1:dimension_min, 2:measure_min

        templates[0][max] = ",and the highest {2} recorded was {0} for {1}";//todo to put or not to put meas_unit
        //0:measure_max_value, 1:dimension_max, 2:measure_max

        templates[0][patt] = ",looking through the data some certain pattern{1} have been detected: {0}";
        //0:the patterns

        templates[0][inc] = ",looking through the data it was found the {0} is increasing.";
        //0:measure

        templates[0][dec] = ",looking through the data it was found the {0} is decreasing.";
        //0:measure

        templates[0][steady] = ",There has been no change the {0}.";
        //0:measure

        templates[1][intro] = "the {2} shows the {3} in " +
                "{0} of {4} {1} for the dimension_value {0}"; //todo to put or not to put dim_unit
        //0:dimension, 1:measure, 2:type, 3:title, 4:dimension_type

        templates[1][min] = ",the lowest {2} is about {0} for {1}";
        //0:measure_min_value, 1:dimension_min, 2:measure_min

        templates[1][max] = ",the highest {2} is about {0} for {1}";//todo to put or not to put meas_unit
        //0:measure_max_value, 1:dimension_max, 2:measure_max

        templates[1][patt] = ",the following pattern{1} have been observed: {0}";
        //0:the patterns

        templates[1][inc] = ",the numbers show that the {0} is increasing.";
        //0:measure

        templates[1][dec] = ",the numbers show that the {0} is increasing.";
        //0:measure

        templates[1][steady] = ",There has been no change the {0}.";
        //0:measure

        templates[2][intro] = "the {2} illustrates the {3} in " +
                "{0} of {4} {1} for the dimension_value {0}"; //todo to put or not to put dim_unit
        //0:dimension, 1:measure, 2:type, 3:title, 4:dimension_type

        templates[2][min] = ",the least {2} is about {0} for {1}";
        //0:measure_min_value, 1:dimension_min, 2:measure_min

        templates[2][max] = ",the most {2} is about {0} for {1}";//todo to put or not to put meas_unit
        //0:measure_max_value, 1:dimension_max, 2:measure_max

        templates[2][patt] = ",the following pattern{1} have been observed: {0}";
        //0:the patterns

        templates[2][inc] = ",{0} is found to be increasing";
        //0:measure

        templates[2][dec] = ",{0} is found to be decreasing";
        //0:measure

        templates[2][steady] = ",There has been no change in {1} over the {0}.";
        //0:dimension, 1:measure

    }
    /*
        fillin template : choose a template and fill it with appropriate values
        @parameters: statistics map
                     patterns array list
        @return value: the description
     */
    public String generateDescription(Map<String, Map<String,String>> statistics, ArrayList<Pair<String, Double>> patterns) {
        int firstSentenceIndex, secondSentenceIndex, thirdSentenceIndex, fourthSentenceIndex, fifthSentenceIndex;
        String dimension, measure, fifthKey; //inc,dec,steady
        Random rand = new Random();

        firstSentenceIndex = rand.nextInt(3);
        secondSentenceIndex = rand.nextInt(3);
        thirdSentenceIndex = secondSentenceIndex;
        fourthSentenceIndex = rand.nextInt(3);
        fifthSentenceIndex = rand.nextInt(3);

        String firstSentence = templates[firstSentenceIndex][intro],
                secondSentence = templates[secondSentenceIndex][min],
                thirdSentence = templates[thirdSentenceIndex][max],
                fourthSentence = templates[fourthSentenceIndex][patt],
                fifthSentence;


        Map<String, String> firstSenMap = statistics.get("intro");
        Map<String, String> secondSenMap = statistics.get("max");
        Map<String, String> thirdSenMap = statistics.get("min");
        Map<String, String> fifthSenMap ;

        if(statistics.containsKey("increase")){
         fifthSenMap = statistics.get("increase");
         fifthSentence = templates[fifthSentenceIndex][inc];
         fifthKey = "increase";
        }
        else if(statistics.containsKey("decrease")){
         fifthSenMap = statistics.get("decrease");
         fifthSentence = templates[fifthSentenceIndex][dec];
         fifthKey = "decrease";
        }
        else if(statistics.containsKey("steady")){
         fifthSenMap = statistics.get("steady");
         fifthSentence = templates[fifthSentenceIndex][steady];
         fifthKey = "steady";
        }
        else{
            fifthSentence="";
            fifthKey="no Key";
            fifthSenMap = null;//no rate map
        }
        /*
        * prepare patterns String
        */

        dimension = firstSenMap.get("dimension");
        measure = firstSenMap.get("measure");

        dimension = dimension.replaceAll("\"","");
        measure = measure.replaceAll("\"","");

        StringBuilder allPatterns = new StringBuilder();

        for(int index=0;index<patterns.size();index++){
            Pair pattern = patterns.get(index);
            //todo add measure unit
            allPatterns.append(pattern.getKey());
            allPatterns.append("have about the same ");
            allPatterns.append(measure);
            allPatterns.append(" which is nearly ");
            allPatterns.append(adjustPercision((Double) pattern.getValue()));
        }


        firstSentence = MessageFormat.format(firstSentence,dimension,measure,
                firstSenMap.get("type"), firstSenMap.get("title"),firstSenMap.get("dimension_types"));

        secondSentence = MessageFormat.format(secondSentence,adjustPercisionS(secondSenMap.get("measure_max_value")),
                secondSenMap.get("dimension_max"),secondSenMap.get("measure_max").replaceAll("\"",""));

        thirdSentence = MessageFormat.format(thirdSentence,adjustPercisionS(thirdSenMap.get("measure_min_value")),
                thirdSenMap.get("dimension_min"),thirdSenMap.get("measure_min").replaceAll("\"",""));

        if(patterns.size()>1){
            fourthSentence = MessageFormat.format(fourthSentence,allPatterns.toString(),"s");
        }
        else
            fourthSentence = MessageFormat.format(fourthSentence,allPatterns.toString(),"");

        if(fifthKey.equals("steady")){
            fifthSentence = MessageFormat.format(fifthSentence,measure);
        }
        else if(fifthKey.equals("increase")||fifthKey.equals("decrease")){
            if(fifthSentenceIndex == 2) {
                fifthSentence = MessageFormat.format(fifthSentence,measure);
            } else {
                fifthSentence = MessageFormat.format(fifthSentence,measure);
            }
        }
        else{
        }
        StringBuilder builder = new StringBuilder();
        builder.append(firstSentence);
        builder.append(secondSentence);
        builder.append(thirdSentence);
        builder.append(fourthSentence);
        if(!fifthSentence.equals("")) {
            builder.append(fifthSentence);
        }
        return builder.toString();
    }


    private String adjustPercisionS(String value){
        String val= value;
        val = val.substring(0,val.length()-2);
        int valSize =  val.length();
        StringBuilder percision = new StringBuilder();
        if(val.length()>3){
            percision.append(val.substring(0,3));
            valSize -=3;
            while(valSize>0){
                valSize--;
                percision.append("0");
            }
        }
        return percision.toString();
    }

    private String adjustPercision(double value){
        String val= String.valueOf(value);
        val = val.substring(0,val.length()-2);
        int valSize =  val.length();
        StringBuilder percision = new StringBuilder();
        if(val.length()>3){
            percision.append(val.substring(0,3));
            valSize -=3;
            while(valSize>0){
                valSize--;
                percision.append("0");
            }
        }
        return percision.toString();
    }
}