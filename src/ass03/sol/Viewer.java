package ass03.sol;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Viewer extends JFrame  {	
	private ViewerPanel panel;
	
	public Viewer(ExtPointCloud cloud){
		this.setTitle("Viewer");
		this.setSize(400,400);
		this.setResizable(false);
		setPreferredSize(new Dimension(400,400));
		this.panel = new ViewerPanel(cloud,400,400,100);
		this.getContentPane().add(this.panel);
		pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);		
			}
		});
	}
	
	public void display(){
		setVisible(true);
	}
}
