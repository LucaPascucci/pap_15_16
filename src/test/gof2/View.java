package test.gof2;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame implements ActionListener{

	private JButton startButton,pauseButton,stopButton;
	private JTextField speedField;
	private Controller controller;
	private GridPanel gridPanel;
	
	public View(int width,int height,int row,int column){
		
		setTitle("Game of life");
		setSize(width,height);
		
		startButton = new JButton("start");
		pauseButton = new JButton("pause");
		stopButton = new JButton("stop");
		speedField = new JTextField(5);
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(new JLabel("Speed "));
		controlPanel.add(speedField);
		controlPanel.add(startButton);
		controlPanel.add(pauseButton);
		controlPanel.add(stopButton);
		startButton.addActionListener(this);
		pauseButton.addActionListener(this);
		stopButton.addActionListener(this);
		pauseButton.setEnabled(false);
		stopButton.setEnabled(false);

		gridPanel = new GridPanel(row,column); 
		gridPanel.setSize(row,column);
		
		JPanel globalPanel = new JPanel();
		LayoutManager layout = new BorderLayout();
		globalPanel.setLayout(layout);

		globalPanel.add(BorderLayout.NORTH,controlPanel);
		globalPanel.add(BorderLayout.CENTER,gridPanel);
		setContentPane(globalPanel);	
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}

	public void addController(Controller controller){
		this.controller=controller;
	}
	
	private void setStartButton(boolean flag){
		startButton.setEnabled(flag);
	}
	
	private void setPauseButton(boolean flag){
		pauseButton.setEnabled(flag);	
	}
	
	private void setStopButton(boolean flag){
		stopButton.setEnabled(flag);
	}
	
	public void setCellsLive(boolean [][] table){
		gridPanel.setCellsValue(table);
	}
	
	public boolean [][] getCellsLive(){
		return gridPanel.getCellsValue();
	}
	
	public void clear(){
		gridPanel.clear();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand(); 
		if (cmd.equals("start")){
			int speed=0;
			
			try{
				speed=Integer.parseInt(speedField.getText());
			}catch(NumberFormatException ex){
				speed=50;
			}
			
			setStartButton(false);
			setPauseButton(true);
			setStopButton(true);
			
			controller.start(speed);
			
		} else if (cmd.equals("stop")){
			
			setStartButton(true);
			setPauseButton(false);
			setStopButton(false);
			
			controller.stop();
		} else if (cmd.equals("pause")){
			
			setStartButton(true);
			setPauseButton(false);
			setStopButton(true);
			
			controller.pause();
		}
	}
	
	
}
