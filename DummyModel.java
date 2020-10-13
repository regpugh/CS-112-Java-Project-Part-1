import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DummyModel extends Model {

    Map<String, double[]> train;
    Map<String, Integer> trainCount;
    String positive = "Apple";
    String negative = "Banana";

    public DummyModel(){
        train = new HashMap<String, double[]>();
        trainCount = new HashMap<String, Integer>();
    }

    public void train(ArrayList<DataPoint> data){
        for(DataPoint d : data){
            if(train.containsKey(d.getLabel())){
                double val[] = train.get(d.getLabel());
                val[0] += d.getF1();
                val[1] += d.getF2();
                train.put(d.getLabel(), val);
                trainCount.put(d.getLabel(), trainCount.get(d.getLabel()) + 1);
            }
            else{
                double val[] = {0, 0};
                train.put(d.getLabel(), val);
                trainCount.put(d.getLabel(), 0);
            }
        }
        for (Map.Entry<String,double[]> entry : train.entrySet()){
            double val[] = entry.getValue();
            val[0] /= trainCount.get(entry.getKey());
            val[1] /= trainCount.get(entry.getKey());
            train.put(entry.getKey(), val);
        }
    }

    public String test(ArrayList<DataPoint> data){
        double values[] = {0, 0};
        for(DataPoint d : data){
            values[0] += d.getF1();
            values[1] += d.getF2();
        }
        values[0] /= data.size();
        values[1] /= data.size();
        double difference = Integer.MAX_VALUE;
        String result = "";
        for (Map.Entry<String,double[]> entry : train.entrySet()){
            double val[] = entry.getValue();
            if(Math.abs(val[0] - values[0]) + Math.abs(val[1] - values[1]) < difference){
                difference = Math.abs(val[0] - values[0]) + Math.abs(val[1] - values[1]);
                result = entry.getKey();
            }
        }
        return result;
    }

    public Double getAccuracy(ArrayList<DataPoint> data){
        double table[] = {0, 0, 0, 0};
        for(DataPoint d : data){
            double difference = Integer.MAX_VALUE;
            String result = "";
            for (Map.Entry<String,double[]> entry : train.entrySet()){
                double val[] = entry.getValue();
                if(Math.abs(val[0] - d.getF1()) + Math.abs(val[1] - d.getF2()) < difference){
                    difference = Math.abs(val[0] - d.getF1()) + Math.abs(val[1] - d.getF2());
                    result = entry.getKey();
                }
            }
            if(result.equals(positive) && d.getLabel().equals(positive)){
                table[3]++;
            }
            else if(result.equals(negative) && d.getLabel().equals(negative)){
                table[0]++;
            }
            else if(result.equals(positive) && d.getLabel().equals(negative)){
                table[1]++;
            }
            else{
                table[2]++;
            }
        }
        return table[3]/(table[3] + table[1]);
    }

    public Double getPrecision(ArrayList<DataPoint> data){
        double table[] = {0, 0, 0, 0};
        for(DataPoint d : data){
            double difference = Integer.MAX_VALUE;
            String result = "";
            for (Map.Entry<String,double[]> entry : train.entrySet()){
                double val[] = entry.getValue();
                if(Math.abs(val[0] - d.getF1()) + Math.abs(val[1] - d.getF2()) < difference){
                    difference = Math.abs(val[0] - d.getF1()) + Math.abs(val[1] - d.getF2());
                    result = entry.getKey();
                }
            }
            if(result.equals(positive) && d.getLabel().equals(positive)){
                table[3]++;
            }
            else if(result.equals(negative) && d.getLabel().equals(negative)){
                table[0]++;
            }
            else if(result.equals(positive) && d.getLabel().equals(negative)){
                table[1]++;
            }
            else{
                table[2]++;
            }
        }
        return table[3]/(table[3] + table[2]);
    }
    
}
