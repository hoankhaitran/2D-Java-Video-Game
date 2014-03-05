/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author hoantran
 */
public class GamePhysics {
    private int rowNum=0;
    private int colNum=0;
    private int cellSize=0;
    private int mapWidth;
    private int mapHeight;
    private Hashtable<Integer,ArrayList> buckets;
    public void setUpGridData(int mapWidth, int mapHeight, int cellSize){
        rowNum = mapWidth/cellSize;
        colNum = mapHeight/cellSize;
        buckets = new Hashtable<Integer,ArrayList>(rowNum*colNum);
        for(int i=0; i<rowNum*colNum;i++){
            buckets.put(i, new ArrayList());
        }
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.cellSize = cellSize;
        
    }
    
    public void clearBucket(){
        buckets.clear();
        for(int i=0; i<rowNum*colNum;i++){
            buckets.put(i, new ArrayList());
        }
    }
    
    public void addObjectToBuckets(GameObject obj){
        ArrayList cellIds = getIdForGameObject(obj);
    }
    private ArrayList getIdForGameObject(GameObject go){
        ArrayList bucketsObjectIsIn = new ArrayList();
        float minLocationX = go.getBounds().x;
        float minLocationY = go.getBounds().y;
        float maxLocationX = (float)go.getBounds().getMaxX();
        float maxLocationY = (float)go.getBounds().getMaxY();
        float width = mapWidth/cellSize;
        float height = mapHeight/cellSize;
        //Top left
        
        return bucketsObjectIsIn;
    }
    
}
