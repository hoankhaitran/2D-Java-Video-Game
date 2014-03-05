/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.util.Iterator;

/**
 *
 * @author FXSSD
 */
public interface ICollection {

    public void add(GameObject newObject);

    public Iterator getIterator();
}
