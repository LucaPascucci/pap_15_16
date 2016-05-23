package test.seeds.view;

import test.seeds.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

/**
 * Created by Luca on 20/05/16.
 */
public class SeedsPanel extends JPanel{

    Controller controller;

    public SeedsPanel(int rows, int columns) {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                CellPane cellPane = new CellPane(row,col);
                Border border;
                if (row < rows - 1) {
                    if (col < columns - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < columns - 1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellPane.setBorder(border);
                this.add(cellPane, gbc);
            }
        }
    }

    public class CellPane extends JPanel {

        private Color defaultBackground;
        private int row,col;

        public CellPane(int r, int c) {

            this.row = r;
            this.col = c;

            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    //controller.addPoint(row,col);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    defaultBackground = getBackground();
                    setBackground(Color.BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    setBackground(defaultBackground);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    //controller.addPoint(row,col);
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(10, 10);
        }
    }

}