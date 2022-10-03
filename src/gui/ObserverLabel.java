/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ObserverLabel
        extends JLabel
        implements Observer
{

    @Override
    public void notifyObserver(Observed observed)
    {
        System.out.println(observed.getState());
        setText("scor " + observed.getState());
    }
}
