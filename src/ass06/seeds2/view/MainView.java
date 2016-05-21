package ass06.seeds2.view;

import ass06.seeds2.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Luca on 20/05/16.
 */
public class MainView extends JFrame implements ActionListener{

    private SeedsPanel seedsPanel;
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton resetButton = new JButton("Reset");
    private JLabel era = new JLabel("564");
    private JLabel alivePSeeds = new JLabel("564");

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

        JPanel infoPanel = new JPanel();
        infoPanel.add(new JLabel("Iteration:"));
        infoPanel.add(this.era);
        infoPanel.add(new JLabel("Alive Points:"));
        infoPanel.add(this.alivePSeeds);

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
            this.seedsPanel.clearPanel();
            this.controller.reset();
        }
    }
}
