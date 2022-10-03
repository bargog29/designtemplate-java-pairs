package gui;

import javax.swing.JFrame;

public class MainFrame
        extends JFrame
{

    private static MainFrame frame;

    public static MainFrame getMainFrame()
    {
        if (frame == null)
        {
            frame = new MainFrame();
        }
        return frame;
    }

    private MainFrame()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new MainPanel());
        pack();
        setLocationRelativeTo(null);
    }
}
