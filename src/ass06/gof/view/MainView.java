package ass06.gof.view;

import ass06.gof.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Luca on 20/05/16.
 */
public class MainView extends JFrame implements ActionListener{

    private SeedsPanel seedsPanel;
    private JButton startBtn = new JButton("Start");
    private JButton stopBtn = new JButton("Stop");
    private JButton resetBtn = new JButton("Reset");
    private JButton rulesBtn = new JButton("Ricci");
    private JTextField aliveSeedsTF = new JTextField(5);
    private JTextField eraTF = new JTextField(5);
    private JTextField eraTimeTF = new JTextField(5);


    private Controller controller;

    public MainView(Dimension windowSize, Dimension worldSize){
        this.setSize(windowSize.width,windowSize.height);
        this.setTitle("Seeds - Game of Life");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.startBtn.addActionListener(this);
        this.stopBtn.addActionListener(this);
        this.resetBtn.addActionListener(this);
        this.stopBtn.setEnabled(false);
        this.rulesBtn.addActionListener(this);

        LayoutManager layout = new BorderLayout();
        this.setLayout(layout);

        JPanel controlPanel = new JPanel();
        controlPanel.add(this.startBtn);
        controlPanel.add(this.stopBtn);
        controlPanel.add(this.resetBtn);
        controlPanel.add(this.rulesBtn);

        this.eraTF.setText("0");
        this.eraTF.setEditable(false);
        this.aliveSeedsTF.setText("0");
        this.aliveSeedsTF.setEditable(false);
        this.eraTimeTF.setText("0");
        this.eraTimeTF.setEditable(false);

        JPanel infoPanel = new JPanel();
        infoPanel.add(new JLabel("Era:"));
        infoPanel.add(this.eraTF);
        infoPanel.add(new JLabel("Alive Points:"));
        infoPanel.add(this.aliveSeedsTF);
        infoPanel.add(new JLabel("Computation era Time:"));
        infoPanel.add(this.eraTimeTF);

        this.seedsPanel = new SeedsPanel(worldSize);

        this.add(controlPanel,BorderLayout.NORTH);
        this.add(this.seedsPanel,BorderLayout.CENTER);
        this.add(infoPanel,BorderLayout.SOUTH);
    }

    public void setListener(Controller controller){
        this.controller = controller;
        this.seedsPanel.setListener(this.controller);
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
            this.startBtn.setEnabled(true);
            this.controller.stopped();
        }

        if (e.getSource().equals(this.resetBtn)){
            this.eraTF.setText("0");
            this.eraTimeTF.setText("0");
            this.aliveSeedsTF.setText("0");
            this.seedsPanel.clearPanel();
            this.controller.reset();
        }

        if (e.getSource().equals(this.rulesBtn)){
            if (e.getActionCommand().equals("Ricci")){
                this.rulesBtn.setText("Conway");
            }else{
                this.rulesBtn.setText("Ricci");
            }
            this.controller.changeRules();
        }
    }

    public void setAliveSeeds(int aliveSeeds){
        this.aliveSeedsTF.setText("" + aliveSeeds);
    }

    public void updateInfo(int era, int aliveSeeds, long time){
        this.setAliveSeeds(aliveSeeds);
        this.eraTF.setText("" + era);
        this.eraTimeTF.setText("" + time);
    }

    public void emptyWorldMessage(){
        this.stopBtn.setEnabled(false);
        this.resetBtn.setEnabled(true);
        this.startBtn.setEnabled(true);
        JOptionPane.showMessageDialog(this,"The world is empty.\nPlease add some seeds.");
    }

    public void updateAliveSeeds(List<Point> newSeeds){
        this.seedsPanel.updatePanel(newSeeds);
    }
}
