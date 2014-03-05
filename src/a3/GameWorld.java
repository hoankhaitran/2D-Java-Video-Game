package a3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld implements IObservable, IGameWorld {

    private GameObjectsCollection ObjectsCollection;
    private ArrayList<IObserver> observers;
    private int totalPoint;
    private int totalFishPoint;
    private int sharkNum;
    private int fishInNetPoint;
    private int sharkInNetPoint;
    private int gameClock;
    private boolean sound = false;

    @Override
    public int generateRandomNum(int max) {
        Random rand = new Random();
        int num = rand.nextInt(max);
        return num + 1;
    }

    public int getFishCount() {
        Iterator itr;
        itr = this.getObjectIterator();
        int count = 0;
        while (itr.hasNext()) {
            GameObject obj = (GameObject) itr.next();
            if (obj instanceof Fish) {
                count++;
            }
        }
        return count;

    }

    @Override
    public Iterator getObjectIterator() {
        return this.ObjectsCollection.getIterator();
    }

    @Override
    public GameObjectsCollection getObjectsCollection() {
        return ObjectsCollection;
    }

    public GameWorld(int width, int height, int fishNum, int sharkNum, int seaweedNum) {// get width and height from Game class MAX_WIDTH and MAX_HEIGHT
        ObjectsCollection = new GameObjectsCollection();
        observers = new ArrayList<IObserver>();
        gameClock = Game.MASTER_CLOCK_IN_MS;

        for (int i = 0; i < fishNum; i++) { // generate number of fishes based on user input 
            float y = generateRandomNum(Game.MAP_HEIGHT - Game.OFF_SET); // make sure plant is not outside the map
            float x = generateRandomNum(Game.MAP_WIDTH - Game.OFF_SET);
            if (x < Game.BAR_SIZE * 3 && y < Game.BAR_SIZE * 2) //The bar area
            {
                x += Game.BAR_SIZE * 4;//make sure food is not random at Bar
                y += Game.BAR_SIZE * 3;
            }

            int dir = (int) generateRandomNum(Game.MAX_POLAR_DEGREE);
            int speed = generateRandomNum(Game.MAX_SPEED);

            Fish fish = new Fish(x, y, dir, speed);
            //if(Game.DEBUG==true)// this is to start the fish thread. This is not a part of this assignment

            ObjectsCollection.add(fish);

        }
        for (int i = 0; i < sharkNum; i++) {
            float y = generateRandomNum(Game.MAP_HEIGHT - Game.OFF_SET); // make sure plant is not outside the map
            float x = generateRandomNum(Game.MAP_WIDTH - Game.OFF_SET);
            if (x < Game.BAR_SIZE * 3 && y < Game.BAR_SIZE * 2) //The bar area
            {
                x += Game.BAR_SIZE * 4;//make sure food is not random at Bar
                y += Game.BAR_SIZE * 4;
            }

            int dir = (int) generateRandomNum(Game.MAX_POLAR_DEGREE);
            int speed = generateRandomNum(Game.MAX_SPEED);

            Shark shark = new Shark(x, y, dir, speed);
            //if(Game.DEBUG==true)


            ObjectsCollection.add(shark);
        }
        for (int i = 0; i < seaweedNum; i++) {// generate number of plants based on user input 
            float y = generateRandomNum(Game.MAP_HEIGHT - Game.OFF_SET); // make sure plant is not outside the map
            float x = generateRandomNum(Game.MAP_WIDTH - Game.OFF_SET);
            if (x < Game.BAR_SIZE * 3 && y < Game.BAR_SIZE * 2) //The bar area
            {
                x += Game.BAR_SIZE * 4;//make sure food is not random at Bar
                y += Game.BAR_SIZE * 3;
            }
            Seaweed seaweed = new Seaweed(x, y);
            ObjectsCollection.add(seaweed);
        }
        ObjectsCollection.add(new Net(250, 250));
        ObjectsCollection.add(new SushiBar(0, 0, Game.BAR_SIZE));


    }

    @Override
    public void registerObserver(IObserver O) {
        observers.add(O);// add to the arrayList the observers of this class
    }

    @Override
    public void removeObserver(IObserver O) {
        int i = observers.indexOf(O);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() { //iterate thru the list and notify all observers
        for (int i = 0; i < observers.size(); i++) {
            IObserver obs = observers.get(i);
            obs.update();
        }
    }

    public void setChanged() {// call the method when the model changed
        notifyObservers();
    }

    public void addGameObject(GameObject O) {
        ObjectsCollection.add(O);
    }

    @Override
    public Net getNet() {
        CopyOnWriteArrayList l = ObjectsCollection.getList();
        Net net = null;
        for (int i = 0; i < l.size(); i++) {
            GameObject obj = (GameObject) l.get(i);
            if (obj instanceof Net) {
                net = (Net) obj;
                return net;

            }
        }
        return net;

    }

    public SushiBar getSushiBar() {
        CopyOnWriteArrayList l = ObjectsCollection.getList();
        SushiBar bar = null;
        for (int i = 0; i < l.size(); i++) {
            GameObject obj = (GameObject) l.get(i);
            if (obj instanceof SushiBar) {
                bar = (SushiBar) obj;
                return bar;

            }
        }
        return bar;

    }
    

    public void randomShark() {
        int num = new Random().nextInt(Game.RANDOM_RATE);
        if (num == 4) { // randomize a shark at top of the map
            float y = 0;
            float x = generateRandomNum(Game.MAP_WIDTH) + Game.BAR_SIZE * 4;
            int dir = (int) generateRandomNum(Game.MAX_POLAR_DEGREE);
            int speed = (int) generateRandomNum(Game.MAX_SPEED);
            Shark shark = new Shark(x, y, dir, speed);
            ObjectsCollection.add(shark);
        }
        if (num == 10) { // randomize a shark on the left of the map
            float y = generateRandomNum(Game.MAP_HEIGHT) + Game.OFF_SET * 6;
            float x = 0;
            int dir = (int) generateRandomNum(Game.MAX_POLAR_DEGREE);
            int speed = (int) generateRandomNum(Game.MAX_SPEED);
            Shark shark = new Shark(x, y, dir, speed);
            ObjectsCollection.add(shark);
        }
        if (num == 15) { // randomize a shark on the right of the map
            float y = Game.MAP_HEIGHT;
            float x = generateRandomNum(Game.MAP_WIDTH);
            int dir = (int) generateRandomNum(Game.MAX_POLAR_DEGREE);
            int speed = (int) generateRandomNum(Game.MAX_SPEED);
            Shark shark = new Shark(x, y, dir, speed);
            ObjectsCollection.add(shark);
        }
        if (num == 25) { // randomize a shark on the bottom of the map
            float y = generateRandomNum(Game.MAP_HEIGHT);
            float x = Game.MAP_WIDTH;
            int dir = (int) generateRandomNum(Game.MAX_POLAR_DEGREE);
            int speed = (int) generateRandomNum(Game.MAX_SPEED);
            Shark shark = new Shark(x, y, dir, speed);
            ObjectsCollection.add(shark);
        }
    }

    public void randomFood() {
        int num = new Random().nextInt(Game.RANDOM_RATE);

        if (num == 3 || num == 266) { // randomize a seaweed based on system time in milliseconds
            float y = generateRandomNum(Game.MAP_HEIGHT - Game.OFF_SET); // make sure plant is not outside the map
            float x = generateRandomNum(Game.MAP_WIDTH - Game.OFF_SET);
            if (x < Game.BAR_SIZE * 3 && y < Game.BAR_SIZE * 2) //The net area
            {
                x += Game.BAR_SIZE * 3;//make sure food is not random at Bar
                y += Game.BAR_SIZE * 2;
            }
            Seaweed sw = new Seaweed(x, y);
            ObjectsCollection.add(sw);
        }
    }
//Collision Detection among game objects
    public void checkFishCollision() {
        Iterator itr = getObjectIterator();
        while (itr.hasNext()) {
            GameObject temp1 = (GameObject) itr.next();
            if (temp1 instanceof Fish) {
                Fish f1 = (Fish) temp1;
                Iterator itr1 = getObjectIterator();
                while (itr1.hasNext()) {
                    GameObject temp2 = (GameObject) itr1.next();
                    if (temp2 instanceof Fish) {
                        Fish f2 = (Fish) temp2;
                        if (f1 != f2) {//make sure we do not check a fish against itself
                            if (f1.collidesWith(f2) && getFishCount() <= Game.MAX_FISH_NUM && (f1.isCollided() == false && f2.isCollided() == false)) {
                                //code to make a new baby fish
                                f1.setCollided(true);
                                f2.handleCollision(this);
                                if (isSound()) {
                                    Game.MAKE_BABY_AUDIO.play();
                                }

                            }
                        }
                    }
                }
            }
        }


    }

    public void checkFishAndSharkCollision() {
        Iterator itr = getObjectIterator();
        while (itr.hasNext()) {
            GameObject temp1 = (GameObject) itr.next();
            if (temp1 instanceof Shark) {
                Shark s = (Shark) temp1;
                Iterator itr1 = getObjectIterator();
                while (itr1.hasNext()) {
                    GameObject temp2 = (GameObject) itr1.next();
                    if (temp2 instanceof Fish) {
                        Fish f2 = (Fish) temp2;
                        if (s.getPolygon().intersects(f2.getBounds())) {// triangle intersects with rectangle
                            //code to kill fish
                            if (isSound()) {
                                Game.SHARK_EAT_FISH_AUDIO.play();
                            }
                            f2.setLivable(false);//Mark eaten fish
                        }
                    }
                }
            }
        }


    }

    public void checkFishAndFoodCollision() {
        Iterator itr = getObjectIterator();
        while (itr.hasNext()) {
            GameObject temp1 = (GameObject) itr.next();
            if (temp1 instanceof Fish) {
                Fish f = (Fish) temp1;
                Iterator itr1 = getObjectIterator();
                while (itr1.hasNext()) {
                    GameObject temp2 = (GameObject) itr1.next();
                    if (temp2 instanceof Plants) {
                        Plants p = (Plants) temp2;
                        if (f.getBounds().intersects(p.getBounds())) {// Rectangle intersects with rectangle
                            //code to kill plant
                            if (isSound()) {
                                Game.FISH_EAT_FOOD_AUDIO.play();
                            }
                            f.setSize(f.getSize() + 1);
                            p.setLivable(false);
                        }
                    }
                }
            }
        }


    }
//Move animals cant go into the bar
    public void checkFishSharkAndBar() {
        Iterator itr = getObjectIterator();
        SushiBar myBar = getSushiBar();
        while (itr.hasNext()) {
            GameObject temp1 = (GameObject) itr.next();
            if (temp1 instanceof Fish) {
                Fish f = (Fish) temp1;
                if (f.getBounds().intersects(myBar.getBounds())) {
                    //code to move fish away from bar
                    f.setDeltaX(-f.getDeltaX());
                    f.setDeltaY(-f.getDeltaY());
                }

            }
            if (temp1 instanceof Shark) {
                Shark f = (Shark) temp1;
                if (f.collidesWith(myBar)) {
                    //code to move shark away from bar
                    f.setDeltaX(-f.getDeltaX());
                    f.setDeltaY(-f.getDeltaY());
                }

            }
        }
    }

    public void checkNetAndBarCollision() {
        Net myNet = getNet();
        SushiBar myBar = getSushiBar();

        if (myNet.collidesWith(myBar)) {
            myNet.handleCollision(this);
        } else {
            myNet.setDeliverable(true);
        }


    }

    public void cleanUp() {
        Iterator itr = getObjectIterator();
        while (itr.hasNext()) {
            GameObject obj = (GameObject) itr.next();
            if (obj.isLivable() == false) {
                itr.remove();
                itr = getObjectIterator();

            }
        }
    }

    @Override
    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    @Override
    public int getTotalFishPoint() {
        return totalFishPoint;
    }

    public void setTotalFishPoint(int totalFishPoint) {
        this.totalFishPoint = totalFishPoint;
    }

    @Override
    public int getSharkNum() {
        return sharkNum;
    }

    public void setSharkNum(int sharkNum) {
        this.sharkNum = sharkNum;
    }

    @Override
    public int getFishInNetPoint() {
        return fishInNetPoint;
    }

    public void setFishInNetPoint(int fishInNetPoint) {
        this.fishInNetPoint = fishInNetPoint;
    }

    @Override
    public int getSharkInNetPoint() {
        return sharkInNetPoint;
    }

    public void setSharkInNetPoint(int sharkInNetPoint) {
        this.sharkInNetPoint = sharkInNetPoint;
    }

    @Override
    public int getGameClock() {
        return gameClock;
    }

    public void setGameClock(int gameClock) {
        this.gameClock = gameClock;
    }

    @Override
    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }
}
