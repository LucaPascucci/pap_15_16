package test.gof2;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class GridPanel extends JPanel {
	
	private CellPanel [][] cellsPanel;
	private boolean[][] cells;
	private int row,column;
	
	public GridPanel(int r,int c)
	{
		row=r;
		column=c;
		cells= new boolean[r][c];
		cellsPanel = new CellPanel[r][c];
		
		setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                cells[row][col]=false;
                cellsPanel[row][col]=new CellPanel(row,col);
                
                Border border = null;
                if (row < r-1) {
                    if (col < c-1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < c-1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellsPanel[row][col].setBorder(border);
                add(cellsPanel[row][col], gbc);
            }
        }
	}
	
	public boolean[][] getCellsValue(){
		return cells;
	}
	
	public void setCellsValue(boolean[][] table){
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				cellsPanel[i][j].live(table[i][j]);
			}
		}
	}
	
	public void clear(){
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				cellsPanel[i][j].live(false);
			}
		}
	}
	
	private class CellPanel extends JPanel {

        private Color defaultBackground = getBackground();
        
        public CellPanel(int row,int col) {
        	
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                	if(getBackground()==Color.BLUE){
                		setBackground(defaultBackground);
                		cells[row][col]=false;
                	}else{
                		setBackground(Color.BLUE);
                		cells[row][col]=true;
                	}
                }

            });
        }
        
        public void live(boolean flag){
        	if(!flag){
        		setBackground(defaultBackground);
        	}else{
        		setBackground(Color.BLUE);
        	}
        }

    }
}
