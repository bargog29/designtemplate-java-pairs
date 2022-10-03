package main;

import gui.MainFrame;
import javax.swing.SwingUtilities;

public class Main
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            MainFrame mainFrame = MainFrame.getMainFrame();
            mainFrame.setVisible(true);
        });
    }
}
