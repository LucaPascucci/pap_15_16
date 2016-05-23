package test.seeds.view;

import test.seeds.controller.Controller;

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
    private JLabel interations = new JLabel("564");
    private JLabel alivePoints = new JLabel("564");

    private Controller controller;

    public MainView(int w, int h, int worldRows, int worldColumns){
        this.setSize(w,h);
        this.setTitle("Seeds - Game of Life");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        LayoutManager layout = new BorderLayout();
        this.setLayout(layout);

        JPanel controlPanel = new JPanel();
        controlPanel.add(this.startButton);
        controlPanel.add(this.stopButton);

        JPanel infoPanel = new JPanel();
        infoPanel.add(new JLabel("Iteration:"));
        infoPanel.add(this.interations);
        infoPanel.add(new JLabel("Alive Points:"));
        infoPanel.add(this.alivePoints);

        this.seedsPanel = new SeedsPanel(worldRows,worldColumns);

        this.add(controlPanel,BorderLayout.NORTH);
        this.add(this.seedsPanel,BorderLayout.CENTER);
        this.add(infoPanel,BorderLayout.SOUTH);

        //this.pack();
    }

    public void setListener(Controller controller){
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
