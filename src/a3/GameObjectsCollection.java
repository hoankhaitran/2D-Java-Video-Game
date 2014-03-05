/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author hoantran
 */
public class GameObjectsCollection implements ICollection {

    private CopyOnWriteArrayList<GameObject> myList;

    public GameObjectsCollection() {
        myList = new CopyOnWriteArrayList<GameObject>();
    }

    @Override
    public GameObjectsIterator getIterator() {
        return new GameObjectsIterator();
    }

    @Override
    public void add(GameObject newObject) {
        myList.add(newObject);
    }

    public int getSize() {
        return myList.size();
    }

    public CopyOnWriteArrayList<GameObject> getList() {
        return myList;
    }

    private class GameObjectsIterator implements Iterator { // this private class only available to the parent classs

        int index;

        public void GameObjectsIterator() {
            index = -1;
        }

        @Override
        public boolean hasNext() {
            if (myList.isEmpty()) {
                return false;
            }
            if (index == myList.size() - 1) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Object next() {
            index++;
            return myList.get(index);
        }

        @Override
        public void remove() {
            myList.remove(index);
        }
        


    }
}
