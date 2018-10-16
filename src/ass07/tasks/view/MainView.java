package ass07.tasks.view;

import ass07.tasks.controller.Controller;
import ass07.tasks.model.PlayerData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            this.controller.start();
        }

        if (e.getSource().equals(this.stopBtn)){
            this.stopBtn.setEnabled(false);
            this.resetBtn.setEnabled(true);
            this.startBtn.setEnabled(true);
            this.controller.stop();
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

    public void updatePlayerAttempt(PlayerData data){
        /* Causes doRun.run() to be executed asynchronously on the
         * AWT event dispatching thread.  This will happen after all
         * pending AWT events have been processed.  This method should
         * be used when an application thread needs to update the GUI.*/
        SwingUtilities.invokeLater(() ->
            this.textArea.append(data.toString() + NEW_LINE)
        );
    }

    public void updateWinner(int winner, int playerNumber){
        SwingUtilities.invokeLater(() -> {
            this.textArea.append(NEW_LINE + "There's a winner!"+ NEW_LINE);
            for (int i = 1; i <= playerNumber; i++){
                if (i == winner){
                    this.textArea.append("Player-" + winner + ": won!" + NEW_LINE);
                }else{
                    this.textArea.append("Player-" + i + ": sob!" + NEW_LINE);
                }
            }
            this.stopBtn.setEnabled(false);
            this.startBtn.setEnabled(false);
            this.resetBtn.setEnabled(true);
        });
    }

    public void setMagicNumber(int number){
        SwingUtilities.invokeLater(() ->
                this.magicNumberTF.setText("" + number)
        );
    }

    public void updateTurn(int turn){
        SwingUtilities.invokeLater(() -> {
            if (turn == 1) {
                this.textArea.append("Turn: " + turn + NEW_LINE);
            } else {
                this.textArea.append(NEW_LINE + "Turn: " + turn + NEW_LINE);
            }
            this.turnTF.setText("" + turn);
        });
    }

    public void updateTurnTime(long time){
        SwingUtilities.invokeLater(() ->
                this.turnTimeTF.setText("" + time)
        );
    }
}
