package ass08.trackBeatActors.actors;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by Luca on 25/06/16.
 */
public class ViewActor extends UntypedActor implements ActionListener{

    private MainView view;

    public ViewActor (Dimension windowSize, int heartbeat_th, int sec_th){
        this.view = new MainView(windowSize,heartbeat_th,sec_th);
        this.view.setVisible(true);
    }

    /**
     * Crea Props per l'attore ViewActor.
     *
     * @return Props per la crezione di questo attore
     */
    public static Props props(final Dimension dim, final int heartbeat_th, final int sec_th) {
        return Props.create(new Creator<ViewActor>() {
            private static final long serialVersionUID = 1L;

            @Override
            public ViewActor create() throws Exception {
                return new ViewActor(dim, heartbeat_th, sec_th);
            }
        });
    }

    @Override
    public void onReceive(Object message) throws Exception {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.view.startBtn)){
            System.out.println("Start");
        }

        if (e.getSource().equals(this.view.stopBtn)){
            System.out.println("Stop");
        }

        if (e.getSource().equals(this.view.resetBtn)){
            System.out.println("Reset");
        }

        if (e.getSource().equals(this.view.heartBeatTHTF)){
            System.out.println("HBTH");
        }

        if (e.getSource().equals(this.view.secTHTF)){
            System.out.println("SECTH");
        }
    }

    private class MainView extends JFrame {

        private static final int STEP_RANGE_X = 400;
        private static final int STEP_RANGE_Y = 300;

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

        public MainView (Dimension windowSize, int heart_beat_th, int sec_th){
            this.setSize(windowSize);
            this.setTitle("TrackBeat Simulator");
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setResizable(false);

            this.startBtn.addActionListener(ViewActor.this);
            this.stopBtn.addActionListener(ViewActor.this);
            this.resetBtn.addActionListener(ViewActor.this);
            //this.stopBtn.setEnabled(false);
            //this.resetBtn.setEnabled(false);

            this.alarmLBL.setForeground(Color.RED);
            Font font = this.alarmLBL.getFont();
            this.alarmLBL.setFont(font.deriveFont(font.getStyle() | Font.BOLD));
            this.alarmLBL.setVisible(false);

            LayoutManager layout = new BorderLayout();
            this.setLayout(layout);

            this.heartBeatTHTF.setText("" + heart_beat_th);
            this.secTHTF.setText("" + sec_th);
            this.heartBeatTHTF.addActionListener(ViewActor.this);
            this.secTHTF.addActionListener(ViewActor.this);

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

                    /*if (currData != null) {

                        //preleva il colore oppurtuno per la posizione corrente
                        g2D.setColor(getCurrPosColor());
                        g2D.fill(new Ellipse2D.Double(currData.getPos().getX() + STEP_RANGE_X, currData.getPos().getY() + STEP_RANGE_Y, 7.0, 7.0));

                        //Disegna la posizione con battito cardiaco massimo
                        g2D.setColor(Color.RED);
                        g2D.fill(new Ellipse2D.Double(MaxHBData.getPos().getX() + STEP_RANGE_X, MaxHBData.getPos().getY() + STEP_RANGE_Y, 7.0, 7.0));
                    }*/
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

    }
}
