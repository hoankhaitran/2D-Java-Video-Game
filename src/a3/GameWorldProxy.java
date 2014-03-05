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
public class GameWorldProxy implements IObservable, IGameWorld {

    GameWorld gw;

    public GameWorldProxy(GameWorld gw) {
        this.gw = gw;
    }
    public  int getFishCount(){
        return gw.getFishCount();
    }

    @Override
    public void registerObserver(IObserver O) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeObserver(IObserver O) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator getObjectIterator() {
        return gw.getObjectIterator();
    }

    @Override
    public GameObjectsCollection getObjectsCollection() {
        return gw.getObjectsCollection();
    }

    @Override
    public int generateRandomNum(int max) {
        return gw.generateRandomNum(max);
    }


    @Override
    public Net getNet() {
        return gw.getNet();
    }



    @Override
    public int getFishInNetPoint() {
        return gw.getFishInNetPoint();
    }



    @Override
    public int getSharkNum() {
        return gw.getSharkNum();
    }



    @Override
    public int getTotalFishPoint() {
        return gw.getTotalFishPoint();
    }



    @Override
    public int getTotalPoint() {
        return gw.getTotalPoint();
    }

    @Override
    public int getSharkInNetPoint() {
        return gw.getSharkInNetPoint();
    }


    @Override
    public int getGameClock() {
        return gw.getGameClock();
    }

    @Override
    public boolean isSound() {
        return gw.isSound();
    }

}
