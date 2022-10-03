/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui;

/**
 *
 * @author Adrian
 */
public interface Observed
{

    public void setObserver(Observer observer);

    public int getState();

    public void notifyObservers();
}
