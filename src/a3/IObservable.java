/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

/**
 *
 * @author hoantran
 */
public interface IObservable {
    public void registerObserver(IObserver O);
    public void removeObserver(IObserver O);
    public void notifyObservers();
}
