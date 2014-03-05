package a3;

import a3.myCommands.AboutMenuItem;
import a3.myCommands.ActivateSound;
import a3.myCommands.ContractNetCommand;
import a3.myCommands.DownCommand;
import a3.myCommands.ExpandNetCommand;
import a3.myCommands.LeftCommand;
import a3.myCommands.NetScoopCommand;
import a3.myCommands.NewMenuItem;
import a3.myCommands.QuitMenuItem;
import a3.myCommands.ReverseCommand;
import a3.myCommands.RightCommand;
import a3.myCommands.SaveMenuItem;
import a3.myCommands.TestAction;
import a3.myCommands.TickCommand;
import a3.myCommands.UndoMenuItem;
import a3.myCommands.UpCommand;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;
import javax.swing.*;

public class Game extends JFrame {

    //Audio clips
    public static AudioClip FISH_EAT_FOOD_AUDIO;
    public static AudioClip MAKE_BABY_AUDIO;
    public static AudioClip SHARK_EAT_FISH_AUDIO;
    public static AudioClip SCOOP_AUDIO;
    public static AudioClip BACKGROUND_AUDIO;
    //GAME CONSTANTS
// Accessible from any other class
    public static boolean GAME_RUNNING = true;
    public static final int RANDOM_RATE = 300; //Higher means less shark
    public static final int BAR_SIZE = 50;
    public static final int OFF_SET = 25; //value from 0 to 30
    public static final int MAX_WIDTH = 1200;
    public static final int SHARK_DAMAGE = 10;
    public static final int MAX_HEIGHT = 710;
    public static final int MAP_WIDTH = 1050;
    public static final int MAP_HEIGHT = 610;
    public static final int MAX_SPEED = 150;
    public static final int NET_SPEED = 30;
    public static final int MAX_FISH_SIZE = 4;
    public static final int MAX_FISH_NUM = 500;
    public static final int MAX_POLAR_DEGREE = 359;
    public static final int THREAD_SLEEP = 30;
    public static boolean DEBUG = false;
    public static final int FISH_NUM = 10;
    public static final int SHARK_NUM = 2;
    public static final int SEAWEED_NUM = 4;
    public static final int DELAY_IN_MS = 30; //in milliseconds
    public static final int MASTER_CLOCK_IN_MS = 60000;//Game time is 60s
    public static final int WAIT_TIME_IN_MS = 300;// * (1000 / DELAY_IN_MS);// about 3 seconds wait time until fish can have baby again
    public static Timer gameTimer;
//Graphical components declaration
    private JMenuBar topMenuBar = new JMenuBar();
    private JPanel mainPanel = new JPanel();
    private PointsView pointPanel;
    private MapView mapView;
    private JPanel commandPanel = new JPanel();
    private String message;
    //Command JButtons
    JButton expandBt = new JButton("Expand Net");
    JButton contractBt = new JButton("Contract Net");
    JButton scoopBt = new JButton("Scoop");
    JButton moveRightBt = new JButton("Move Right");
    JButton moveLeftBt = new JButton("Move Left");
    JButton moveUpBt = new JButton("Move Up");
    JButton moveDownBt = new JButton("Move Down");
    JButton reverseBt = new JButton("Reverse");
    JButton playAndPauseBt = new JButton("Pause");
    //Other variables
    private GameWorldProxy gwp;
    private GameWorld gw;
    private boolean pauseMode;

    public Game() {

        //Create game world
        gw = new GameWorld(Game.MAX_WIDTH, Game.MAX_HEIGHT, Game.FISH_NUM, Game.SHARK_NUM, Game.SEAWEED_NUM);
        gwp = new GameWorldProxy(gw);
        //Load audio files from hdd
        String fileName = "voice_girl_2_years_says_weewee.wav";
        String audioDir = "/a3/Audio/";
        String filePath = audioDir + fileName;
        FISH_EAT_FOOD_AUDIO = Applet.newAudioClip(this.getClass().getResource(filePath));
        fileName = "cartoon_male_american_says_oh_baby_.wav";
        filePath = audioDir + fileName;
        MAKE_BABY_AUDIO = Applet.newAudioClip(this.getClass().getResource(filePath));
        fileName = "male_scream_short.wav";
        filePath = audioDir + fileName;
        SHARK_EAT_FISH_AUDIO = Applet.newAudioClip(this.getClass().getResource(filePath));
        fileName = "SCOOP_AUDIO.wav";
        filePath = audioDir + fileName;
        SCOOP_AUDIO = Applet.newAudioClip(this.getClass().getResource(filePath));
        fileName = "BACKGROUND_AUDIO.wav";
        filePath = audioDir + fileName;
        BACKGROUND_AUDIO = Applet.newAudioClip(this.getClass().getResource(filePath));
        pointPanel = new PointsView(gwp);
        mapView = new MapView(gwp);
        setupGUI();
        pauseMode = false;
        gw.registerObserver(pointPanel);
        gw.registerObserver(mapView);
        gameTimer = new Timer(DELAY_IN_MS, new TickCommand(gw));
        gameTimer.start();
        if (gw.isSound()) {
            BACKGROUND_AUDIO.loop();
        }



    }

    private void setupGUI() {// this method will setup all the needed GUI components for the game

        //Prepare the file menu 
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");// These text will be overwrite when call setAction()
        newItem.setAction(new NewMenuItem());
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAction(new SaveMenuItem());
        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setAction(new UndoMenuItem());
        JCheckBoxMenuItem soundItem = new JCheckBoxMenuItem();
        //   soundItem.setSelected(true);


        final ActivateSound actSound = new ActivateSound(gw);
        soundItem.setAction(actSound);


        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setAction(new AboutMenuItem());
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.setAction(new QuitMenuItem());
        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.add(undoItem);
        fileMenu.add(soundItem);
        fileMenu.add(aboutItem);
        fileMenu.add(quitItem);
        topMenuBar.add(fileMenu);
        //Prepare the commands menu 
        JMenu commandMenu = new JMenu("Commands");

        final JMenuItem expandItem = new JMenuItem("Expand net");
        final ExpandNetCommand expandNet = new ExpandNetCommand(gw);
        expandItem.setAction(expandNet);
        final JMenuItem contractItem = new JMenuItem("Contract net");
        final ContractNetCommand contractNet = new ContractNetCommand(gw);
        contractItem.setAction(contractNet);

        commandMenu.add(expandItem);
        commandMenu.add(contractItem);
        topMenuBar.add(commandMenu);


//Preparing the main panel which includes point panel, commands panel and map panel using borderlayout

        commandPanel.setLayout(new GridLayout(12, 1));
        commandPanel.setBorder(BorderFactory.createTitledBorder("Commands:"));
        commandPanel.add(expandBt);
        expandBt.setAction(expandNet);
        commandPanel.add(contractBt);
        contractBt.setAction(contractNet);
        commandPanel.add(scoopBt);
        final NetScoopCommand scoopNet = new NetScoopCommand(gw);
        scoopBt.setAction(scoopNet);
        commandPanel.add(moveRightBt);
        final RightCommand right = new RightCommand(gwp);
        moveRightBt.setAction(right);
        commandPanel.add(moveLeftBt);
        final LeftCommand left = new LeftCommand(gwp);
        moveLeftBt.setAction(left);
        commandPanel.add(moveUpBt);
        final UpCommand up = new UpCommand(gwp);
        moveUpBt.setAction(up);
        commandPanel.add(moveDownBt);
        final DownCommand down = new DownCommand(gwp);
        moveDownBt.setAction(down);

        commandPanel.add(reverseBt);
        final ReverseCommand reverse = new ReverseCommand(gwp);
        reverseBt.setAction(reverse);
        reverseBt.setEnabled(false);
        commandPanel.add(playAndPauseBt);
        playAndPauseBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Pause")) {

                    gameTimer.stop();
                    pauseMode = true;
                    playAndPauseBt.setText("Play");
                    up.setEnabled(false);
                    down.setEnabled(false);
                    left.setEnabled(false);
                    right.setEnabled(false);
                    scoopNet.setEnabled(false);
                    expandNet.setEnabled(false);
                    contractNet.setEnabled(false);
                    reverseBt.setEnabled(true);
                    BACKGROUND_AUDIO.stop();
                    GAME_RUNNING = false;
                }
                if (e.getActionCommand().equals("Play")) {
                    gameTimer.start();

                    pauseMode = false;
                    playAndPauseBt.setText("Pause");
                    up.setEnabled(true);
                    down.setEnabled(true);
                    left.setEnabled(true);
                    right.setEnabled(true);
                    scoopNet.setEnabled(true);
                    expandNet.setEnabled(true);
                    contractNet.setEnabled(true);
                    reverseBt.setEnabled(false);
                    if (gw.isSound()) {
                        BACKGROUND_AUDIO.loop();
                    }
                    GAME_RUNNING = true;
                }
            }
        });
        //add game objects to the map panel
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mapView, BorderLayout.CENTER);
        mainPanel.add(commandPanel, BorderLayout.WEST);
        mainPanel.add(pointPanel, BorderLayout.NORTH);
        //add MouseMotionListener to MapView to listen to mouse motion

        mapView.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Iterator itr = gw.getObjectIterator();
                while (itr.hasNext()) {
                    GameObject obj = (GameObject) itr.next();
                    if (obj instanceof ISelectable) {
                        ISelectable f = (ISelectable) obj;

                        if (f.isSelected()) {
                            if (f instanceof Fish) {
                                Fish fish = (Fish) f;
                                fish.setLocationX(e.getX() - fish.getDiameter() / 2.0f);
                                fish.setLocationY(e.getY() - fish.getDiameter() / 2.0f);
                            }

                            mapView.repaint(); //update the map
                        }
                    }
                }



            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });

        //add MouseWheelListener to MapView to listen to mouse wheel clicks

        mapView.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Iterator itr = gw.getObjectIterator();
                while (itr.hasNext()) {
                    GameObject obj = (GameObject) itr.next();
                    if (obj instanceof ISelectable) {
                        ISelectable f = (ISelectable) obj;

                        if (f.isSelected()) {
                            if (f instanceof Fish) {
                                Fish fish = (Fish) f;
                                if (e.getWheelRotation() >= 0) {// wheel down to increase size
                                    fish.setSize(fish.getSize() + 1);
                                } else {
                                    fish.setSize(fish.getSize() - 1);
                                    if (fish.getSize() <= 0) {// wheel up to decrease size
                                        fish.setSize(0);
                                    }
                                }
                            }

                            mapView.repaint(); //update the map
                        }
                    }
                }
            }
        });
        //add MouseListener to MapView to listen to mouse clicks

        mapView.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (pauseMode == true) {
                    if (e.isControlDown()) {//select multiple fish with control key down
                        Iterator itr = gw.getObjectIterator();
                        while (itr.hasNext()) {
                            GameObject temp = (GameObject) itr.next();
                            if (temp instanceof ISelectable) {
                                ISelectable f = (ISelectable) temp;
                                if (f.getBounds().contains(e.getPoint())) {
                                    f.setSelected(true);
                                    mapView.repaint();
                                }
                            }
                        }
                    } else {//select only one fish at a time
                        Iterator itr = gw.getObjectIterator();
                        while (itr.hasNext()) {//go thru the first loop to find the selected one
                            GameObject temp = (GameObject) itr.next();
                            if (temp instanceof ISelectable) {
                                ISelectable f = (ISelectable) temp;
                                if (f.getBounds().contains(e.getPoint())) {
                                    itr = gw.getObjectIterator();
                                    while (itr.hasNext()) {//go thru the second loop to unselect all the others
                                        GameObject temp1 = (GameObject) itr.next();
                                        if (temp1 instanceof ISelectable) {
                                            ISelectable f1 = (ISelectable) temp1;
                                            f1.setSelected(false);
                                        }
                                    }
                                    f.setSelected(true);//enable only the selected one
                                    mapView.repaint(); //update the map
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });



        //testing keybinding do not grade this
        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap imap = mapView.getInputMap(mapName);
        ActionMap amap = mapView.getActionMap();
        KeyStroke wkey = KeyStroke.getKeyStroke("UP");
        imap.put(wkey, "UP");
        amap.put("UP", up);
        KeyStroke skey = KeyStroke.getKeyStroke("DOWN");
        imap.put(skey, "DOWN");
        amap.put("DOWN", down);
        KeyStroke akey = KeyStroke.getKeyStroke("LEFT");
        imap.put(akey, "LEFT");
        amap.put("LEFT", left);
        KeyStroke dkey = KeyStroke.getKeyStroke("RIGHT");
        imap.put(dkey, "RIGHT");
        amap.put("RIGHT", right);
        KeyStroke plusKey = KeyStroke.getKeyStroke('=');
        imap.put(plusKey, "=");
        amap.put("=", expandNet);
        KeyStroke minusKey = KeyStroke.getKeyStroke('-');
        imap.put(minusKey, "-");
        amap.put("-", contractNet);
        KeyStroke sKey = KeyStroke.getKeyStroke('s');
        imap.put(sKey, "s");
        amap.put("s", scoopNet);
        KeyStroke gKey = KeyStroke.getKeyStroke('g');
        TestAction TestCommand = new TestAction();
        imap.put(gKey, "g");
        amap.put("g", TestCommand);


        // this.requestFocus();
        //Customize JFrame
        this.setJMenuBar(topMenuBar);
        this.getContentPane().add(mainPanel);
        this.setSize(MAX_WIDTH, MAX_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        message = "Sushi Time by Hoan Tran";
        this.setTitle(message);
        this.setVisible(true);
        this.setResizable(false);
        // this.pack();
    }
}
