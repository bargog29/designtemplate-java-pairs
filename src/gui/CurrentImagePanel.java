
package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class CurrentImagePanel
        extends JPanel
{

    MainPanel mainPanel;

    public CurrentImagePanel(MainPanel mainPanel)
    {
        this.mainPanel = mainPanel;
        setBackground(Color.red);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(mainPanel.getCurrentImage(), 0, 0, getWidth(), getHeight(), null);
    }
}
