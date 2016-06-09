package ass08.trackBeatRX.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Created by Luca on 09/06/16.
 */
public class MapView extends JPanel{

    private int map_width;
    private int map_height;

    public MapView (){

        this.map_width = this.getWidth();
        this.map_height = this.getHeight();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.blue);

        //g2d.drawOval(5, 5, 100, 100);
        Ellipse2D.Double point = new Ellipse2D.Double(10, 5, 7, 7);
        g2d.fill(point);

        Ellipse2D.Double point2 = new Ellipse2D.Double(30, 30, 7, 7);
        g2d.fill(point2);

        //g2d.drawRoundRect();
        //g2d.setStroke(new BasicStroke(1.5f));
        //g2d.drawLine(5,5,30,30);

        //TODO sistemare posizione origine punti e origine linee
        g2d.draw(new Line2D.Float(5, 5, 30, 30));
    }

    //TODO creare un metodo che converte la posizione double double in double double nei limiti della mappa

    private double convertXtoMapRange(double x, double minRange, double maxRange){
        return ((x - minRange)/(maxRange - minRange)) * (this.map_width - 0) + 0;
    }

    private double convertYtoMapRange(double y, double minRange, double maxRange){
        return ((y - minRange)/(maxRange - minRange)) * (this.map_height - 0) + 0;
    }
}
