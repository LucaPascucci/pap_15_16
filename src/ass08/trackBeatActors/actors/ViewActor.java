package ass08.trackBeatActors.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import ass08.trackBeatActors.model.ComplexData;
import ass08.trackBeatActors.model.EnumAction;
import ass08.trackBeatActors.model.TrackBeatData;
import ass08.trackBeatActors.msgs.ActionMsg;
import ass08.trackBeatActors.msgs.AttachMsg;
import ass08.trackBeatActors.msgs.NewDataMsg;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

/**
 * Created by Luca on 25/06/16.
 */
public class ViewActor extends UntypedActor implements ActionListener{

    private ActorRef controller;
    private MainView view;

    private ViewActor (Dimension windowSize, int heartbeat_th, int sec_th){
        this.view = new MainView(windowSize,heartbeat_th,sec_th);
        this.view.setVisible(true);
    }

    /**
     * Crea Props per l'attore ViewActor.
     * @return Props per la creazione di questo attore
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

        if (message instanceof AttachMsg){
            this.controller = getSender();
        }

        if (message instanceof NewDataMsg){
            this.view.updateView(((NewDataMsg) message).getComplexData());
        }

        if (message instanceof ActionMsg){
            switch (((ActionMsg) message).getAction()){
                case UPDATE_BHTH:
                    this.view.restoreHBTH(((ActionMsg) message).getValue());
                    break;
                case UPDATE_SECTH:
                    this.view.restoreSecTH(((ActionMsg) message).getValue());
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.view.startBtn)){
            this.view.updateByAction(EnumAction.START);
            this.controller.tell(new ActionMsg(EnumAction.START),getSelf());
        }

        if (e.getSource().equals(this.view.stopBtn)){
            this.view.updateByAction(EnumAction.STOP);
            this.controller.tell(new ActionMsg(EnumAction.STOP),getSelf());
        }

        if (e.getSource().equals(this.view.resetBtn)){
            this.view.updateByAction(EnumAction.RESET);
            this.view.resetView();
            this.controller.tell(new ActionMsg(EnumAction.RESET),getSelf());
        }

        if (e.getSource().equals(this.view.heartBeatTHTF)){
            try {
                int value = Integer.parseInt(this.view.heartBeatTHTF.getText());
                if (value >= 0){
                    this.controller.tell(new ActionMsg(EnumAction.UPDATE_BHTH,value,true),getSelf());
                } else {
                    this.controller.tell(new ActionMsg(EnumAction.UPDATE_BHTH,-1,false),getSelf());
                }
            } catch (NumberFormatException ex){
                this.controller.tell(new ActionMsg(EnumAction.UPDATE_BHTH,-1,false),getSelf());
            }
        }

        if (e.getSource().equals(this.view.secTHTF)){
            try {
                int value = Integer.parseInt(this.view.secTHTF.getText());
                if (value > 0){
                    this.controller.tell(new ActionMsg(EnumAction.UPDATE_SECTH,value,true),getSelf());
                } else {
                    this.controller.tell(new ActionMsg(EnumAction.UPDATE_SECTH,-1,false),getSelf());
                }
            } catch (NumberFormatException ex){
                this.controller.tell(new ActionMsg(EnumAction.UPDATE_SECTH,-1,false),getSelf());
            }
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
        private JLabel alarmLBL = new JLabel("HB Alarm!");
        private DecimalFormat df = new DecimalFormat("#.##");
        private JPanel mapPanel;
        private TrackBeatData currData;
        private TrackBeatData MaxHBData;

        private MainView (Dimension windowSize, int heart_beat_th, int sec_th){
            this.setSize(windowSize);
            this.setTitle("TrackBeat Simulator");
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setResizable(false);

            this.startBtn.addActionListener(ViewActor.this);
            this.stopBtn.addActionListener(ViewActor.this);
            this.resetBtn.addActionListener(ViewActor.this);
            this.stopBtn.setEnabled(false);
            this.resetBtn.setEnabled(false);

            this.alarmLBL.setForeground(Color.RED);
            this.alarmLBL.setFont(this.alarmLBL.getFont().deriveFont(20.0f));
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
            controlPanel.add(new JLabel("HeartBeat TH"));
            controlPanel.add(this.heartBeatTHTF);
            controlPanel.add(new JLabel("Seconds TH"));
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

            JLabel hrLBL = new JLabel("HeartBeat");
            hrLBL.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel hrAVGLBL = new JLabel("AVG HeartBeat");
            hrAVGLBL.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel hrMAXLBL = new JLabel("MAX HeartBeat");
            hrMAXLBL.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel currPosLBL = new JLabel("Curr Pos");
            currPosLBL.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel hrMAXPosLBL = new JLabel("MAX HB Pos");
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

        //aggiornamento grafico della GUI
        void updateView(ComplexData data){
            SwingUtilities.invokeLater(() -> {

                //aggiorno i punti da disegnare sulla mappa
                this.currData = data.getCurrData();
                this.MaxHBData = data.getMaxData();

                //aggiornamento di valori testuali esplicativi
                this.heartBeatTF.setText("" + this.currData.getHeartbeat());
                this.currPosTF.setText("X: " + this.df.format(this.currData.getPos().getX()) + " Y: " + this.df.format(this.currData.getPos().getY()));
                this.heartBeatMAXTF.setText("" + this.MaxHBData.getHeartbeat());
                this.heartBeatMAXPosTF.setText("X: " + this.df.format(this.MaxHBData.getPos().getX()) + " Y: " + this.df.format(this.MaxHBData.getPos().getY()));

                this.heartBeatAVGTF.setText(this.df.format(data.getHB_AVG()));
                this.speedTF.setText(this.df.format(data.getSpeed()));
                this.alarmLBL.setVisible(data.isActiveAlarm());

                //Ridisegna la mappa con i punti aggiornati
                this.mapPanel.repaint();
            });
        }

        void updateByAction(EnumAction action){
            SwingUtilities.invokeLater(() -> {
                switch (action){
                    case START:
                        this.stopBtn.setEnabled(true);
                        this.resetBtn.setEnabled(false);
                        this.startBtn.setEnabled(false);
                        break;
                    case STOP:
                        this.stopBtn.setEnabled(false);
                        this.resetBtn.setEnabled(true);
                        this.startBtn.setEnabled(false);
                        break;
                    case RESET:
                        this.alarmLBL.setVisible(false);
                        this.startBtn.setEnabled(true);
                        this.resetBtn.setEnabled(false);
                        break;
                }
            });
        }

        void resetView(){
            SwingUtilities.invokeLater(() -> {
                this.heartBeatTF.setText("");
                this.currPosTF.setText("");
                this.heartBeatMAXTF.setText("");
                this.heartBeatMAXPosTF.setText("");
                this.heartBeatAVGTF.setText("");
                this.speedTF.setText("");
                this.currData = null;
                this.MaxHBData = null;
                this.mapPanel.repaint();
            });
        }

        void restoreHBTH(int value){
            SwingUtilities.invokeLater(() -> this.heartBeatTHTF.setText("" + value));
        }

        void restoreSecTH(int value){
            SwingUtilities.invokeLater(() -> this.secTHTF.setText("" + value));
        }

        //Genera il colore della posizione corrente in base al battito cardiaco
        Color getCurrPosColor()
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
}
