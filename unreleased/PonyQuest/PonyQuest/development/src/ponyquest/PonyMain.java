package ponyquest;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import pwnee.*;
import pwnee.image.ImageLibrary;
import pwnee.input.Keyboard;
import pwnee.sound.MidiPlayer;
import pwnee.sound.SoundPlayer;

import ponyquest.data.GameConfig;
import ponyquest.data.SavedGameData;

/** The main window for this Pwnee game example. */
public class PonyMain extends JFrame implements WindowListener, WindowFocusListener {
    
    /** The singleton instance. */
    public static PonyMain instance;
    
    private GraphicsDevice grDev;
    private DisplayMode oldDisplay;
    
    public PonyPanel ponyPanel;
    
    /** Whether the game window is in fullscreen. */
    public boolean fullscreen = false;
    
    /** The width of the game's resolution. */
    public static int resWidth = 320;
    
    /** The height of the game's resolution. */
    public static int resHeight = 240;
    
    /** The resize scale for the game's resolution. */
    public static int resScale = 2;
    
    /** Our midi player */
    public static MidiPlayer midiPlayer = new MidiPlayer();;
    
    /** Our sound player. */
    public static SoundPlayer soundPlayer = new SoundPlayer();
    
    /** The game's image library used to cache loaded images. */
    public static ImageLibrary imageLib = new ImageLibrary();
    
    
    private PonyMain(boolean fullscreen) {
        super("Pony Quest");
        this.fullscreen = fullscreen;
    }
    
    public void init() {
      int screenX = resWidth*2;    
      int screenY = resHeight*2;
      this.setSize(screenX,screenY);
      this.setUndecorated(true);
      
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((int) screenSize.getWidth()/2 - screenX/2, (int) screenSize.getHeight()/2 - screenY/2);
      
      this.addWindowListener(this);
      this.addWindowFocusListener(this);
      
      // Save our starting display mode.
      grDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      oldDisplay = grDev.getDisplayMode();
      
      // setup the game for full-screen if requested.
      if(fullscreen)
          setFullscreen();

      // Create main window panel and add it into the window.
      ponyPanel = PonyPanel.getInstance();
      this.add(ponyPanel);
      
      // finishing touches on Game window
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);

      // Request focus so that we can poll keyboard input in our game.
      ponyPanel.requestFocusInWindow();
      
      instance.ponyPanel.start(60);
      
      System.err.println("Game Window successfully created!!!");
    }
    
    
    public void setFullscreen() {
        System.err.println("Trying to start program in Fullscreen mode.");
            
        // makes sure fullscreen is supported before doing anything.
        if(grDev.isFullScreenSupported()) {
            System.err.println("FullScreen is supported");
            this.setUndecorated(true);
            
            // Create and apply a new DisplayMode with a 640 x 480 resolution.
            try {
                DisplayMode resChangeMode = new DisplayMode(640,480,32,DisplayMode.REFRESH_RATE_UNKNOWN); 
                grDev.setFullScreenWindow(this); 
                grDev.setDisplayMode(resChangeMode); 
                System.err.println("Change resolution: Success!");
            }
            catch(Exception e) {
                System.err.println("Change resolution: FAIL!");
            }
        }
        
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    
    
    /** Obtains the base transform for the game's resized resolution. */
    public static AffineTransform getBaseTransform() {
      return AffineTransform.getScaleInstance(2, 2);
    }
    
    
    
    //////// General game data
    
    /** 
     * Returns the value of a String-mapped game variable. 
     * This could be a global variable from PonyConfig, 
     * a location-specific variable, or an AreaMap-specific variable. 
     */
    public static String getVarString(String var) {
      if(var.startsWith("config.")) {
        return GameConfig.get(var.substring(var.indexOf(".") + 1));
      }
      else {
        return FieldModeLevel.getInstance().map.getVarString(var);
      }
      
      // TODO: query variables in loaded save game data.
    }
    
    
    /** 
     * Activates a String-mapped in-game event. 
     * This could be an event that does something AreaMap-specific or
     * something at a higher level.
     */
    public static void doEventString(String event) {
      FieldModeLevel.getInstance().map.doEventString(event);
    }
    
    
    //////// Singleton logic
    
    /** 
     * Obtains the singleton instance. If it hasn't already been created, it is created with 
     * the provided arguments. 
     */
    public static PonyMain getInstance(String[] args) {
        if(instance == null) {
          ArrayList<String> argsList = new ArrayList<>();
          if(args != null) {
            for(String s : args) {
              System.out.println(s);
              argsList.add(s);
            }
          }
          
          // Extract game boot-up parameters from the arguments.
          boolean fullscreen = (argsList.contains("fullscreen") || argsList.contains("f"));
          
          // use the boot-up paramters to set up our game instance!
          instance = createInstance(fullscreen);
          instance.init();
        }
        return instance;
    }
    
    
    /** 
     * Obtain the singleton instance. If it hasn't already been created, 
     * create it with default parameters.
     */
    public static PonyMain getInstance() {
      return getInstance(null);
    }
    
    
    private static PonyMain createInstance(boolean fullscreen) {
      // Load and return the game instance.
      return new PonyMain(fullscreen);
    }
    
    
    /** Shortcut to get the keyboard input to the game. */
    public static Keyboard getKeyboard() {
      return getInstance().ponyPanel.keyboard;
    }
    
    
    //////// Window events
    public void windowActivated(WindowEvent e) {
        System.err.println("Window Activated");
    }
    
    public void windowClosed(WindowEvent e)  {
        System.err.println("program terminated. Restoring original display settings.");
    }
    
    public void windowClosing(WindowEvent e) {
        System.err.println("Window closing");
        if(fullscreen) {
          grDev.setDisplayMode(oldDisplay);
        }
    }
    
    public void windowDeactivated(WindowEvent e) {
        System.err.println("Window deactivated");
    }
    
     public void windowDeiconified(WindowEvent e) {
        System.err.println("Window Deiconified");
        if(ponyPanel != null) {
          ponyPanel.requestFocusInWindow();
        }
    }
    
     public void windowIconified(WindowEvent e) {
        System.err.println("Window Iconified");
    }
    
     public void windowOpened(WindowEvent e) {
        System.err.println("Window opened");
    }
    
    public void windowGainedFocus(WindowEvent e) {
        System.err.println("Window gained focus");
        if(ponyPanel != null) {
          ponyPanel.requestFocusInWindow();
        }
    }
    
    public void windowLostFocus(WindowEvent e)  {
        System.err.println("Window lost focus");
    }
    
    
    
    
    
    
    
    /** Creates the game window and makes it fullscreen if the user provided the argument "fullscreen". */
    public static void main(String[] args) {
        PonyMain.getInstance(args);
    }

}