package ass06.seeds2.view;

import ass06.seeds2.controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Luca on 20/05/16.
 */
public class SeedsPanel extends JPanel{

    private CellPanel [][] cellsPanel;
    private boolean drawing = false;
    private Controller controller;

    public SeedsPanel(Dimension worldSize) {

        this.cellsPanel = new CellPanel[worldSize.height][worldSize.width];

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < worldSize.height; row++) {
            for (int col = 0; col < worldSize.width; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                CellPanel cellPane = new CellPanel(row,col);
                Border border;
                if (row < worldSize.height - 1) {
                    if (col < worldSize.width - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < worldSize.width - 1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellPane.setBorder(border);
                this.add(cellPane, gbc);
                this.cellsPanel[row][col] = cellPane;
            }
        }
    }

    public void setListener(Controller controller){
        this.controller = controller;
    }

    public void clearPanel(){
        for (CellPanel[] row : this.cellsPanel){
            for (CellPanel cell: row){
                cell.setCell(false);
            }
        }
        this.repaint();
    }

    public void updatePanel(List<Point> seeds){
        this.clearPanel();
        seeds.stream().forEach(s -> this.cellsPanel[(int)s.getY()][(int)s.getX()].setCell(true));
    }

    private class CellPanel extends JPanel {

        private Color defaultBackground = getBackground();

        public CellPanel(int row, int col) {

            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    drawing = true;
                    if (SwingUtilities.isRightMouseButton(e)){
                        if (getBackground().equals(Color.BLUE)) {
                            setBackground(defaultBackground);
                            controller.manageSeed(row, col, false);
                        }
                    }else{
                        if (!getBackground().equals(Color.BLUE)) {
                            setBackground(Color.BLUE);
                            controller.manageSeed(row, col, true);
                        }
                    }

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    if (drawing) {
                        if (SwingUtilities.isRightMouseButton(e)){
                            if (getBackground().equals(Color.BLUE)) {
                                setBackground(defaultBackground);
                                controller.manageSeed(row, col, false);
                            }
                        }else{
                            if (!getBackground().equals(Color.BLUE)) {
                                setBackground(Color.BLUE);
                                controller.manageSeed(row, col, true);
                            }
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    drawing = false;
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(10, 10);
        }

        public void setCell(boolean value){
            if(!value){
                setBackground(this.defaultBackground);
            }else{
                setBackground(Color.BLUE);
            }
        }
    }

}