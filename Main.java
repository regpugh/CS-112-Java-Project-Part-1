import java.util.ArrayList;
import java.util.Random;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

    public static void main(String[] args) {
        ArrayList<DataPoint> trainSet = new ArrayList<DataPoint>();
        ArrayList<DataPoint> testSet = new ArrayList<DataPoint>();
        String labels[] = {"Apple", "Banana"};
        for(int i = 0; i < 35; i++){
            Random r = new Random();
            double f1 = 50 * r.nextDouble();
            double f2 = 50 * r.nextDouble();
            int randomNum = r.nextInt(2);
            trainSet.add(new DataPoint(f1, f2, labels[randomNum], "dummy"));
        }
        for(int i = 0; i < 15; i++){
            Random r = new Random();
            double f1 = 50 * r.nextDouble();
            double f2 = 50 * r.nextDouble();
            int randomNum = r.nextInt(2);
            testSet.add(new DataPoint(f1, f2, labels[randomNum], "dummy"));
        }
        DummyModel model = new DummyModel();
        model.train(trainSet);
        System.out.println(model.test(testSet));
        double accuracy = model.getAccuracy(testSet);
        double precision = model.getPrecision(testSet);
        String acc = String.format("%10s : %2.2f", "Accuracy", accuracy);
        String pre = String.format("%10s : %2.2f", "Precision", precision);
        JFrame frame = new JFrame("Statistics");  
        JPanel panel = new JPanel();  
        panel.setLayout(new FlowLayout());  
        JLabel label = new JLabel(acc); 
        panel.add(label);   
        label = new JLabel(pre); 
        panel.add(label);  
        frame.add(panel);  
        frame.setSize(200, 300);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true); 
    }
    
}
