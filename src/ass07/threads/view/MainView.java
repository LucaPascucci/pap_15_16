package ass07.threads.view;

import ass07.threads.controller.Controller;
import ass07.threads.model.PlayerData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Luca on 27/05/16.
 */
public class MainView extends JFrame implements ActionListener{

    private final static String NEW_LINE = "\n";

    private Controller controller;
    private JButton startBtn = new JButton("Start");
    private JButton stopBtn = new JButton("Stop");
    private JButton resetBtn = new JButton("Reset");
    private JTextField turnTF = new JTextField(4);
    private JTextField turnTimeTF = new JTextField(6);
    private JTextField magicNumberTF = new JTextField(8);
    private JTextArea textArea = new JTextArea(40,50);


    public MainView (Dimension windowSize){
        this.setSize(windowSize.width,windowSize.height);
        this.setTitle("Guess the Number");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.startBtn.addActionListener(this);
        this.stopBtn.addActionListener(this);
        this.resetBtn.addActionListener(this);
        this.stopBtn.setEnabled(false);

        LayoutManager layout = new BorderLayout();
        this.setLayout(layout);

        JPanel controlPanel = new JPanel();
        controlPanel.add(this.startBtn);
        controlPanel.add(this.stopBtn);
        controlPanel.add(this.resetBtn);

        this.turnTF.setEditable(false);
        this.turnTimeTF.setEditable(false);
        this.magicNumberTF.setEditable(false);

        JPanel infoPanel = new JPanel();
        infoPanel.add(new JLabel("Magic Number:"));
        infoPanel.add(this.magicNumberTF);
        infoPanel.add(new JLabel("Turn:"));
        infoPanel.add(this.turnTF);
        infoPanel.add(new JLabel("Computation Turn Time:"));
        infoPanel.add(this.turnTimeTF);

        this.textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(this.textArea);

        this.add(controlPanel,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(infoPanel,BorderLayout.SOUTH);
    }

    public void setListener(Controller controller){
        this.controller = controller;
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
            this.turnTF.setText("");
            this.turnTimeTF.setText("");
            this.magicNumberTF.setText("");
            this.textArea.setText("");
            this.startBtn.setEnabled(true);
            this.controller.reset();

        }
    }

    public void updateView(List<PlayerData> players, int turn, long time){
        SwingUtilities.invokeLater(() -> {
                this.textArea.append("Turn: " + turn + NEW_LINE);
                players.stream().forEach(p -> textArea.append(p.toString() + NEW_LINE));
                this.textArea.append(NEW_LINE);
                this.turnTimeTF.setText("" + time);
                this.turnTF.setText("" + turn);
            }
        );
    }

    public void updateWinner(int winner, int playersNumber){
        SwingUtilities.invokeLater(() -> {
                this.textArea.append("There's a winner!"+ NEW_LINE);
                for (int i = 1; i <= playersNumber; i++){
                    if (i == winner){
                        this.textArea.append("Player-"+ winner +": won!" + NEW_LINE);
                    }else{
                        this.textArea.append("Player-"+ i +": sob!" + NEW_LINE);
                    }
                }
                this.stopBtn.setEnabled(false);
                this.startBtn.setEnabled(false);
                this.resetBtn.setEnabled(true);

            }
        );
    }

    public void setMagicNumber(int number){
        SwingUtilities.invokeLater(() ->
            this.magicNumberTF.setText("" + number)
        );
    }
}
