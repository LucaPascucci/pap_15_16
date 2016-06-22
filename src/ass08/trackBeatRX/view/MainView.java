package ass08.trackBeatRX.view;

import ass08.trackBeatRX.controller.Controller;
import ass08.trackBeatRX.model.TrackBeatData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Luca on 09/06/16.
 */
public class MainView extends JFrame implements ActionListener{

    private Controller controller;
    private JButton startBtn = new JButton("Start");
    private JButton stopBtn = new JButton("Stop");
    private JTextField heartBeatTF = new JTextField(4);
    private JTextField heartBeatAVGTF = new JTextField(4);
    private JTextField speedTF = new JTextField(4);
    private JTextField heartBeatTHTF = new JTextField(4);
    private JTextField secTHTF = new JTextField(4);
    private JLabel alarmLBL = new JLabel("Alarm!");
    private MapPanel mapPanel;

    public MainView (Dimension windowSize, Dimension mapSize){
        this.setSize(windowSize);
        this.setTitle("TrackBeat simulator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.startBtn.addActionListener(this);
        this.stopBtn.addActionListener(this);
        this.stopBtn.setEnabled(false);

        this.alarmLBL.setForeground(Color.RED);
        Font font = this.alarmLBL.getFont();
        this.alarmLBL.setFont(font.deriveFont(font.getStyle() | Font.BOLD));

        LayoutManager layout = new BorderLayout();
        this.setLayout(layout);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Heart Beat TH"));
        controlPanel.add(this.heartBeatTHTF);
        controlPanel.add(new JLabel("TH Seconds"));
        controlPanel.add(this.secTHTF);
        controlPanel.add(this.startBtn);
        controlPanel.add(this.stopBtn);

        this.heartBeatAVGTF.setEditable(false);
        this.heartBeatTF.setEditable(false);
        this.speedTF.setEditable(false);

        JPanel infoPanel = new JPanel();
        infoPanel.add(this.alarmLBL);
        infoPanel.add(new JLabel("Heart Rate:"));
        infoPanel.add(this.heartBeatTF);
        infoPanel.add(new JLabel("AVG Heart Rate:"));
        infoPanel.add(this.heartBeatAVGTF);
        infoPanel.add(new JLabel("Speed:"));
        infoPanel.add(this.speedTF);
        infoPanel.add(new JLabel("Max HB Pos:"));
        infoPanel.add(this.speedTF);

        this.mapPanel = new MapPanel(mapSize);

        this.add(controlPanel,BorderLayout.NORTH);
        this.add(this.mapPanel,BorderLayout.CENTER);
        this.add(infoPanel,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.startBtn)){
            this.stopBtn.setEnabled(true);
            this.startBtn.setEnabled(false);
            this.controller.started();
        }

        if (e.getSource().equals(this.stopBtn)){
            this.stopBtn.setEnabled(false);
            this.startBtn.setEnabled(true);
            this.controller.stopped();
        }
    }

    public void setListener(Controller controller){
        this.controller = controller;
        this.mapPanel.setListener(controller);
    }

    public void updateInfo(TrackBeatData data){
        SwingUtilities.invokeLater(() -> {

            //TODO aggiornamento grafico
        });
    }

    public void activeAlarm(boolean value){
        SwingUtilities.invokeLater(() -> this.alarmLBL.setVisible(value));
    }

}
