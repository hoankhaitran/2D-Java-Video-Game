/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.Iterator;

/**
 *
 * @author hoantran
 */
public interface IGameWorld {

    public Iterator getObjectIterator();

    public GameObjectsCollection getObjectsCollection();

    public int generateRandomNum(int max);

    public Net getNet();

    public int getFishInNetPoint();

    public int getSharkNum();

    public int getTotalFishPoint();

    public int getTotalPoint();

    public int getSharkInNetPoint();

    public boolean isSound();

    public int getGameClock();
}
