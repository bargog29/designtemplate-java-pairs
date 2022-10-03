package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class MainPanel
        extends JPanel
        implements Observed
{

    public int ok = 0;
    public static final int IMAGE_SIZE = 150;
    JButton[] imageButtons = new JButton[4];
    CurrentImagePanel currentImagePanel = new CurrentImagePanel(this);
    private Image[] currentImages;
    private JProgressBar remainingTimeProgressBar;
//    private JLabel scoreLabel = new JLabel("0");
    //private JLabel endgame = new JLabel("Timpul a expirat");
    private int guessedImageIndex;
    private ObserverLabel scoreLabel = new ObserverLabel();
    Random random = new Random();
    int scor = 0;
    Timer timer = new Timer(1, (e) ->
    {
        //remainingTimeProgressBar.setValue(remainingTimeProgressBar.getValue() - 20);
        Context context = new Context(new OperationSubstract());
        remainingTimeProgressBar.setValue(context.executeStrategy(remainingTimeProgressBar.getValue(), 20));
        if (remainingTimeProgressBar.getValue() <= 0)
        {
            ok = 1;
            repaint();
            removeAll();
            System.exit(0);
        }
    });

    public MainPanel()
    {
        setBackground(Color.WHITE);
        for (int i = 0; i < imageButtons.length; i++)
        {
            imageButtons[i] = new JButton();
            imageButtons[i].setPreferredSize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));
        }
        currentImagePanel.setPreferredSize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));
        remainingTimeProgressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 60000);
        remainingTimeProgressBar.setValue(60000);
        for (JButton imageButton : imageButtons)
        {
            imageButton.addActionListener(this::checkImage);
        }
        metodaAdaugaImagini();
        initLayout();
        timer.start();
        setObserver((Observer) scoreLabel);
        notifyObservers();
    }

    private void checkImage(ActionEvent e)
    {
        for (int i = 0; i < imageButtons.length; i++)
        {
            if (e.getSource().equals(imageButtons[i]))
            {
                if (guessedImageIndex == i)
                {
                    System.out.println("Corect");
                    scor++;
                    notifyObservers();
//                  scoreLabel.setText(String.valueOf(Integer.parseInt(scoreLabel.getText()) + 1));
                    Context context = new Context(new OperationAdd());
                    remainingTimeProgressBar.setValue(context.executeStrategy(remainingTimeProgressBar.getValue(), 5000));
                    metodaAdaugaImagini();
                }
                else
                {
                    Context context = new Context(new OperationSubstract());
                    System.out.println("Ghinion");
                    remainingTimeProgressBar.setValue(context.executeStrategy(remainingTimeProgressBar.getValue(), 10000));
                }
            }
        }
    }

    public Image getCurrentImage()
    {
        return currentImages[guessedImageIndex];
    }

    private void initLayout()
    {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                        .addComponent(scoreLabel)
                        .addComponent(currentImagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(imageButtons[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(imageButtons[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(imageButtons[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(imageButtons[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addComponent(remainingTimeProgressBar)
                )
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(scoreLabel)
                        .addComponent(currentImagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                                .addComponent(imageButtons[0], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(imageButtons[1], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, false)
                                .addComponent(imageButtons[2], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(imageButtons[3], GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addComponent(remainingTimeProgressBar)
                )
        );
    }

    private void metodaAdaugaImagini()
    {
        int currImagesIndex = random.nextInt(ImageAcces.getNumOfImages());
        currentImages = ImageAcces.getImages(currImagesIndex);
        for (int i = 0; i < currentImages.length; i++)
        {
            imageButtons[i].setIcon(new ImageIcon(currentImages[i]));
        }
        guessedImageIndex = random.nextInt(4);
//        revalidate();
        repaint();
    }
    List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void setObserver(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public int getState()
    {
        return scor;
    }

    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.notifyObserver(this);
        }
    }
}
