package ass03.sol;

import ass03.P2d;
import ass03.Region;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


class ViewerPanel extends JPanel  {
	private ExtPointCloud cloud;
	private int width;
	private int height;
	private double pixelPointRatio;
	
	private Pair<Integer,Integer> v0;
	private Pair<Integer,Integer> v1;
	private boolean isSelecting;
	private List<P2d> selectedPoints;
	
	public ViewerPanel(ExtPointCloud cloud, int w, int h, double pixelPointRatio){
		this.width = w;
		this.height = h;
		this.pixelPointRatio = pixelPointRatio;
		this.cloud = cloud;
		selectedPoints = new ArrayList<>();
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				v0 = new Pair<>(e.getX(),e.getY());
				v1 = new Pair<>(e.getX(),e.getY());
				isSelecting = true;
				repaint();
			}
						
			public void mouseReleased(MouseEvent e){
				isSelecting = false;
				Region r = new Region(
							new P2d((v0.getLeft() - width/2)/pixelPointRatio,
									(height/2 - v0.getRight())/pixelPointRatio),
							new P2d((v1.getLeft() - width/2)/pixelPointRatio,
									(height/2 - v1.getRight())/pixelPointRatio));
				log("selected region: "+r.getUpperLeft()+" "+r.getBottomRight());
				selectedPoints = cloud.getPointsInRegion(r);
				repaint();
			}
		});
		
		this.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e){
				v1 = new Pair<>(e.getX(),e.getY());
				repaint();
			}
		});
		
	}
	
	private void log(String msg){
		System.out.println("[VIEWER LOG] "+msg);
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
		g2.setBackground(Color.WHITE);

		g2.clearRect(0, 0, width, height);
		
		cloud.apply(p -> {
			int x = width/2 + (int) Math.round(pixelPointRatio*p.getX()); 
			int y = height/2 - (int) Math.round(pixelPointRatio*p.getY()); 
			g2.fillOval(x, y, 4, 4);
		});	
		
		g2.setColor(Color.RED);
		selectedPoints.stream().forEach(p -> {
			int x = width/2 + (int) Math.round(pixelPointRatio*p.getX()); 
			int y = height/2 - (int) Math.round(pixelPointRatio*p.getY()); 
			g2.fillOval(x, y, 4, 4);
		});
		
		if (isSelecting){
			g2.setColor(Color.BLUE);
			g2.drawRect(v0.getLeft(), v0.getRight(), (v1.getLeft()-v0.getLeft()), (v1.getRight()-v0.getRight()));
		}
	}
	
}
