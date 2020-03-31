import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class TestCaseCreator extends JComponent{

    static LinkedList<int[]> data = new LinkedList<>();

    public void paint(Graphics g) {
        // Draw a simple line using the Graphics2D draw() method.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(0, 0, 0, 500));
        g2.draw(new Line2D.Double(500, 0, 500, 500));
        g2.setColor(Color.BLUE);
        for (int[] a : data) {
            g2.draw(new Line2D.Double(0, a[0], 500, a[1]));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //JFrame frame = new JFrame("Draw Line");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(new TestCaseCreator());
        //frame.pack();
        //frame.setSize(new Dimension(500, 500));
        //frame.setVisible(true);

        String filename = "moop";
        PrintWriter writer = new PrintWriter(new File(filename + ".in"));

        int n =10000;

        writer.println(n + 1);

        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int a = rand.nextInt(20);
            int b = rand.nextInt(20);
            writer.println(i + " " + i);
            //data.add(new int[] {a * 25, b * 25});
        }
        writer.println(10005 + " " + 10005);


        writer.close();
    }

}
