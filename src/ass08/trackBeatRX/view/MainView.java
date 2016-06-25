package ass08.trackBeatRX.view;

import ass08.trackBeatRX.controller.Controller;
import ass08.trackBeatRX.model.TrackBeatData;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

/**
 * Created by Luca on 09/06/16.
 */
public class MainView extends JFrame implements ActionListener{

    private static final int STEP_RANGE_X = 400;
    private static final int STEP_RANGE_Y = 300;

    private Controller controller;
    private JButton startBtn = new JButton("Start");
    private JButton stopBtn = new JButton("Stop");
    private JButton resetBtn = new JButton("Reset");
    private JTextField heartBeatTF = new JTextField();
    private JTextField heartBeatAVGTF = new JTextField();
    private JTextField speedTF = new JTextField();
    private JTextField heartBeatMAXTF = new JTextField();
    private JTextField currPosTF = new JTextField();
    private JTextField heartBeatMAXPosTF = new JTextField();
    private JTextField heartBeatTHTF = new JTextField(4);
    private JTextField secTHTF = new JTextField(4);
    private JLabel alarmLBL = new JLabel("Alarm!");
    private DecimalFormat df = new DecimalFormat("#.##");
    private JPanel mapPanel;
    private TrackBeatData currData;
    private TrackBeatData MaxHBData;

    public MainView (Dimension windowSize, int heart_beat_th, int sec_th){
        this.setSize(windowSize);
        this.setTitle("TrackBeat simulator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.startBtn.addActionListener(this);
        this.stopBtn.addActionListener(this);
        this.resetBtn.addActionListener(this);
        this.stopBtn.setEnabled(false);
        this.resetBtn.setEnabled(false);

        this.alarmLBL.setForeground(Color.RED);
        Font font = this.alarmLBL.getFont();
        this.alarmLBL.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
        this.alarmLBL.setVisible(false);

        LayoutManager layout = new BorderLayout();
        this.setLayout(layout);

        this.heartBeatTHTF.setText("" + heart_beat_th);
        this.secTHTF.setText("" + sec_th);
        this.heartBeatTHTF.addActionListener(this);
        this.secTHTF.addActionListener(this);

        JPanel controlPanel = new JPanel();
        controlPanel.add(this.alarmLBL);
        controlPanel.add(new JLabel("Heart Beat TH"));
        controlPanel.add(this.heartBeatTHTF);
        controlPanel.add(new JLabel("TH Seconds"));
        controlPanel.add(this.secTHTF);
        controlPanel.add(this.startBtn);
        controlPanel.add(this.stopBtn);
        controlPanel.add(this.resetBtn);

        this.heartBeatTF.setEditable(false);
        this.heartBeatTF.setHorizontalAlignment(SwingConstants.CENTER);
        this.heartBeatAVGTF.setEditable(false);
        this.heartBeatAVGTF.setHorizontalAlignment(SwingConstants.CENTER);
        this.heartBeatMAXTF.setEditable(false);
        this.heartBeatMAXTF.setHorizontalAlignment(SwingConstants.CENTER);
        this.currPosTF.setEditable(false);
        this.currPosTF.setHorizontalAlignment(SwingConstants.CENTER);
        this.heartBeatMAXPosTF.setEditable(false);
        this.heartBeatMAXPosTF.setHorizontalAlignment(SwingConstants.CENTER);
        this.speedTF.setEditable(false);
        this.speedTF.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel hrLBL = new JLabel("Heart Rate");
        hrLBL.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hrAVGLBL = new JLabel("AVG Heart Rate");
        hrAVGLBL.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hrMAXLBL = new JLabel("MAX Heart Rate");
        hrMAXLBL.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel currPosLBL = new JLabel("Curr Pos");
        currPosLBL.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hrMAXPosLBL = new JLabel("MAX HR Pos");
        hrMAXPosLBL.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel speedLBL = new JLabel("Speed");
        speedLBL.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0,6));
        infoPanel.add(hrLBL);
        infoPanel.add(hrAVGLBL);
        infoPanel.add(hrMAXLBL);
        infoPanel.add(currPosLBL);
        infoPanel.add(hrMAXPosLBL);
        infoPanel.add(speedLBL);
        infoPanel.add(this.heartBeatTF);
        infoPanel.add(this.heartBeatAVGTF);
        infoPanel.add(this.heartBeatMAXTF);
        infoPanel.add(this.currPosTF);
        infoPanel.add(this.heartBeatMAXPosTF);
        infoPanel.add(this.speedTF);

        this.mapPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2D = (Graphics2D)g;

                if (currData != null) {

                    //preleva il colore oppurtuno per la posizione corrente
                    g2D.setColor(getCurrPosColor());
                    g2D.fill(new Ellipse2D.Double(currData.getPos().getX() + STEP_RANGE_X, currData.getPos().getY() + STEP_RANGE_Y, 7.0, 7.0));

                    //Disegna la posizione con battito cardiaco massimo
                    g2D.setColor(Color.RED);
                    g2D.fill(new Ellipse2D.Double(MaxHBData.getPos().getX() + STEP_RANGE_X, MaxHBData.getPos().getY() + STEP_RANGE_Y, 7.0, 7.0));
                }
            }
        };

        this.mapPanel.setPreferredSize(new Dimension(800,600));
        this.mapPanel.setBorder(new MatteBorder(1,1,1,1, Color.GRAY));
        //Creo questo JPanel per evitare che la mappa raggiunga i bordi del frame
        JPanel fakePanel = new JPanel();
        fakePanel.add(this.mapPanel);

        this.add(controlPanel,BorderLayout.NORTH);
        this.add(fakePanel,BorderLayout.CENTER);
        this.add(infoPanel,BorderLayout.SOUTH);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.startBtn)){
            this.stopBtn.setEnabled(true);
            this.resetBtn.setEnabled(false);
            this.startBtn.setEnabled(false);
            this.controller.started();
        }

        if (e.getSource().equals(this.stopBtn)){
            this.stopBtn.setEnabled(false);
            this.resetBtn.setEnabled(true);
            this.startBtn.setEnabled(false);
            this.controller.stopped();
        }

        if (e.getSource().equals(this.resetBtn)){
            this.heartBeatTF.setText("");
            this.currPosTF.setText("");
            this.heartBeatMAXTF.setText("");
            this.heartBeatMAXPosTF.setText("");
            this.heartBeatAVGTF.setText("");
            this.speedTF.setText("");
            this.alarmLBL.setVisible(false);
            this.startBtn.setEnabled(true);
            this.currData = null;
            this.MaxHBData = null;
            this.mapPanel.repaint();
            this.controller.reset();
        }

        if (e.getSource().equals(this.heartBeatTHTF)){
            try {
                int value = Integer.parseInt(this.heartBeatTHTF.getText());
                this.controller.modifyHBTH(true,value);
            } catch (NumberFormatException ex){
                this.controller.modifyHBTH(false,-1);
            }
        }
        if (e.getSource().equals(this.secTHTF)){
            try {
                int value = Integer.parseInt(this.secTHTF.getText());
                this.controller.modifySecTH(true,value);
            } catch (NumberFormatException ex){
                this.controller.modifySecTH(false,-1);
            }
        }
    }

    public void setListener(Controller controller){
        this.controller = controller;
    }

    //aggiornamento grafico della GUI
    public void updateView(TrackBeatData currData,double avg_hb, TrackBeatData maxHeartBeatData, double speed, boolean activeAlarm) {
        SwingUtilities.invokeLater(() -> {

            //aggiornamento di valori testuali esplicativi
            this.heartBeatTF.setText("" + currData.getHeartbeat());
            this.currPosTF.setText("X: " + df.format(currData.getPos().getX()) + " Y: " + df.format(currData.getPos().getY()));
            this.heartBeatMAXTF.setText("" + maxHeartBeatData.getHeartbeat());
            this.heartBeatMAXPosTF.setText("X: " + df.format(maxHeartBeatData.getPos().getX()) + " Y: " + df.format(maxHeartBeatData.getPos().getY()));
            this.heartBeatAVGTF.setText(df.format(avg_hb));
            this.speedTF.setText(df.format(speed));
            this.alarmLBL.setVisible(activeAlarm);

            //aggiorno i punti da disegnare sulla mappa
            this.currData = currData;
            this.MaxHBData = maxHeartBeatData;

            //Ridisegna la mappa con i punti aggiornati
            this.mapPanel.repaint();
        });
    }

    public void restoreHBTH(int value){
        SwingUtilities.invokeLater(() -> this.heartBeatTHTF.setText("" + value));
    }

    public void restoreSecTH(int value){
        SwingUtilities.invokeLater(() -> this.secTHTF.setText("" + value));
    }

    //Genera il colore della posizione corrente in base al battito cardiaco
    private Color getCurrPosColor()
    {
        //il massimo è dinamico dato che va in base al valore massimo generato runtime
        int percent = (int)(((this.currData.getHeartbeat() - 60.0) / (this.MaxHBData.getHeartbeat() - 60.0)) * 100.0);

        if (percent == 100){
            percent = 99;
        }
        int r,g,b = 0;
        //regolazione del di red e green in base alla percentuale del valore corrente sul range dinamico
        if (percent < 50) {
            // da green a yellow
            r = (int)(255 * (percent / 50.0));
            g = 255;

        } else {
            // da yellow a red
            r = 255;
            g = (int)(255 * ((50.0 - percent % 50.0) / 50.0));
        }

        return new Color(r,g,b);
    }
}
