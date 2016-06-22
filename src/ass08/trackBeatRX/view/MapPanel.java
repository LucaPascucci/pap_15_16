package ass08.trackBeatRX.view;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Luca on 20/05/16.
 */
public class MapPanel extends JPanel{

    private static final int MAX_X = 400;
    private static final int MIN_X = -400;
    private static final int MAX_Y = 300;
    private static final int MIN_Y = -300;

    private CellPanel [][] cellsPanel;
    private Dimension mapSize;

    public MapPanel(Dimension mapSize) {

        this.mapSize = mapSize;
        this.cellsPanel = new CellPanel[this.mapSize.height][this.mapSize.width];

        this.setLayout(new GridBagLayout());
        this.setEnabled(false);

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < this.mapSize.height; row++) {
            for (int col = 0; col < this.mapSize.width; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                CellPanel cellPane = new CellPanel(row,col);

                if (row == 0){
                    if (col == 0){
                        cellPane.setBorder(new MatteBorder(1,1,0,0, Color.GRAY));
                    } else if (col == this.mapSize.width - 1){
                        cellPane.setBorder(new MatteBorder(1,0,0,1, Color.GRAY));
                    } else {
                        cellPane.setBorder(new MatteBorder(1,0,0,0, Color.GRAY));
                    }
                }
                if (row == this.mapSize.height - 1){
                    if (col == 0){
                        cellPane.setBorder(new MatteBorder(0,1,1,0, Color.GRAY));
                    } else if (col == this.mapSize.width - 1){
                        cellPane.setBorder(new MatteBorder(0,0,1,1, Color.GRAY));
                    } else {
                        cellPane.setBorder(new MatteBorder(0,0,1,0, Color.GRAY));
                    }
                }

                if (col == 0){
                    if (row > 0 && row < this.mapSize.height - 1){
                        cellPane.setBorder(new MatteBorder(0,1,0,0, Color.GRAY));
                    }
                }

                if (col == this.mapSize.width - 1){
                    if (row > 0 && row < this.mapSize.height - 1){
                        cellPane.setBorder(new MatteBorder(0,0,0,1, Color.GRAY));
                    }
                }

                this.add(cellPane, gbc);
                this.cellsPanel[row][col] = cellPane;
            }
        }
    }

    public void clearPanel(){
        //TODO
    }

    public void updatePanel(){
        //TODO
    }

    private class CellPanel extends JPanel {

        private Color defaultBackground = getBackground();

        public CellPanel(int row, int col) {

            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(8, 8);
        }

        public void setCell(boolean value){
            //TODO
        }
    }

    //new_value = ( (old_value - old_min) / (old_max - old_min) ) * (new_max - new_min) + new_min
    private int convertXtoMapRange(double x){
        return (int) ((x - MIN_X)/(MAX_X - MIN_X)) * (this.mapSize.width);
    }

    private int convertYtoMapRange(double y){
        return (int) ((y - MIN_Y)/(MAX_Y - MIN_Y)) * (this.mapSize.height);
    }
    //TODO creare metodi per fare combaciare i range

}