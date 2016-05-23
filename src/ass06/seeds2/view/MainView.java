package ass06.seeds2.view;

import ass06.seeds2.controller.Controller;

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
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton resetButton = new JButton("Reset");
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

        this.startButton.addActionListener(this);
        this.stopButton.addActionListener(this);
        this.resetButton.addActionListener(this);
        this.stopButton.setEnabled(false);

        LayoutManager layout = new BorderLayout();
        this.setLayout(layout);

        JPanel controlPanel = new JPanel();
        controlPanel.add(this.startButton);
        controlPanel.add(this.stopButton);
        controlPanel.add(this.resetButton);

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
        if (e.getSource().equals(startButton)){
            System.out.println("Start");
            this.stopButton.setEnabled(true);
            this.resetButton.setEnabled(false);
            this.startButton.setEnabled(false);
            this.controller.started();
        }

        if (e.getSource().equals(stopButton)){
            System.out.println("Stop");
            this.stopButton.setEnabled(false);
            this.resetButton.setEnabled(true);
            this.startButton.setEnabled(true);
            this.controller.stopped();
        }

        if (e.getSource().equals(resetButton)){
            System.out.println("Reset");
            this.eraTF.setText("0");
            this.eraTimeTF.setText("0");
            this.aliveSeedsTF.setText("0");
            this.seedsPanel.clearPanel();
            this.controller.reset();
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
        this.stopButton.setEnabled(false);
        this.resetButton.setEnabled(true);
        this.startButton.setEnabled(true);
        JOptionPane.showMessageDialog(this,"The world is empty.\nPlease add some seeds.");
    }

    public void updateAliveSeeds(List<Point> newSeeds){
        this.seedsPanel.updatePanel(newSeeds);
    }
}
