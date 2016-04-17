package ass03;

import javax.swing.*;
import static java.util.stream.Collectors.toList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

class Viewer {

}
/*
class ViewerPanel extends JPanel  {
	

	private static final long serialVersionUID = -3126514076957384057L;

	public ViewerPanel(){
	}
		
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		          RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
		          RenderingHints.VALUE_RENDER_QUALITY);
		       		
		g2.setColor(Color.BLACK);
		//...
	}
}

public class Viewer extends JFrame implements ShapeViewer {
	
	private ViewerPanel panel;
	
	public Viewer(List<Shape> shapes){
		setTitle("Viewer");
		setSize(400,400);
		setPreferredSize(new Dimension(400,400));
		panel = new ViewerPanel(shapes);
		this.getContentPane().add(panel);
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
	
	public void update(List<Shape> shapes){
		panel.updateShapes(shapes);
		repaint();
	}
}
*/