package ponyquest.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import pwnee.image.ImageEffects;
import pwnee.image.ImageLoader;
import pwnee.text.BlitteredFont;

import ponyquest.data.GameConfig;

/** A repository of images used for the game's various menus*/
public class MenuImages {
  
  /** The images, mapped by their names. */
  private static Map<String, Image> images = new HashMap<>(); 
  
  /** The default dialog font. */
  private static BlitteredFont font = null; 
  
  /** A compressed version of font. */
  private static BlitteredFont fontCompressed = null;
  
  /** The font for more stylized text. */
  private static BlitteredFont equestriaFont = null; 
  
  // style codes.
  public static final int STYLE_8BIT = 1;
  
  public static final int STYLE_16BIT = 2;
  
  public static final int STYLE_ORNATE = 3;
  
  public static final int STYLE_CANDY = 4;
  
  /** The style being used for dialog boxes. */
  public static int dialogStyle = STYLE_16BIT;
  
  /** Whether the menu images have been loaded yet. */
  public static boolean isLoaded = false;
  
  
  
  
  /** Loads all the menu image resources. */
  public static void loadImages() {
    if(isLoaded) { 
      return;
    }
    isLoaded = true;
    
    ImageLoader imgLoader = new ImageLoader();
    
    // load the fonts.
    _loadFonts(imgLoader);
    
    // load the dialog styles.
    _loadDialogBoxStyles(imgLoader);
    
    // load the icons
    _loadIcons(imgLoader);
    
    // Load the selection pointer styles.
    _loadPointers(imgLoader);
    
    imgLoader.waitForAll();
  }
  
  
  /** 
   * Returns the image for the specified key, loading the images from their 
   * resources if necessary. 
   */
  private static Image _getImg(String key) {
    loadImages();
    return images.get(key);
  }
  
  
  //////// Dialog box parts
  
  /** Loads the dialog box styles. */
  private static void _loadDialogBoxStyles(ImageLoader imgLoader) {
    _loadDialogBoxStyle(imgLoader, 1);
    _loadDialogBoxStyle(imgLoader, 2);
    _loadDialogBoxStyle(imgLoader, 3);
    _loadDialogBoxStyle(imgLoader, 4);
  }
  
  /** Loads the images for one of the dialog box styles. */
  private static void _loadDialogBoxStyle(ImageLoader imgLoader, int styleNum) {
    Image srcImg = imgLoader.loadResource("graphics/menus/DialogBox" + styleNum + ".png");
    srcImg = ImageEffects.setTransparentColor(srcImg, new Color(0x000000));
    
    Image img;
    
    img = ImageEffects.crop(srcImg, 0, 0, 16, 16);
    images.put("dia" + styleNum + "NW", img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 16, 0, 16, 16);
    images.put("dia" + styleNum + "N", img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 32, 0, 16, 16);
    images.put("dia" + styleNum + "NE", img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 0, 16, 16, 16);
    images.put("dia" + styleNum + "W", img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 16, 16, 16, 16);
    images.put("dia" + styleNum, img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 32, 16, 16, 16);
    images.put("dia" + styleNum + "E", img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 0, 32, 16, 16);
    images.put("dia" + styleNum + "SW", img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 16, 32, 16, 16);
    images.put("dia" + styleNum + "S", img);
    imgLoader.addImage(img);
    
    img = ImageEffects.crop(srcImg, 32, 32, 16, 16);
    images.put("dia" + styleNum + "SE", img);
    imgLoader.addImage(img);
    
    
  }
  
  
  /** Get the NW dialog image for the current style. */
  public static Image diaNW() {
    return _getImg("dia" + dialogStyle + "NW");
  }
  
  /** Get the N dialog image for the current style. */
  public static Image diaN() {
    return _getImg("dia" + dialogStyle + "N");
  }
  
  /** Get the NWE dialog image for the current style. */
  public static Image diaNE() {
    return _getImg("dia" + dialogStyle + "NE");
  }
  
  /** Get the W dialog image for the current style. */
  public static Image diaW() {
    return _getImg("dia" + dialogStyle + "W");
  }
  
  /** Get the center dialog image for the current style. */
  public static Image diaCenter() {
    return _getImg("dia" + dialogStyle);
  }
  
  /** Get the E dialog image for the current style. */
  public static Image diaE() {
    return _getImg("dia" + dialogStyle + "E");
  }
  
  /** Get the SW dialog image for the current style. */
  public static Image diaSW() {
    return _getImg("dia" + dialogStyle + "SW");
  }
  
  /** Get the S dialog image for the current style. */
  public static Image diaS() {
    return _getImg("dia" + dialogStyle + "S");
  }
  
  /** Get the SE dialog image for the current style. */
  public static Image diaSE() {
    return _getImg("dia" + dialogStyle + "SE");
  }
  
  
  /** Renders a menu/dialog box using the current dialog style. */
  public static void renderDiaBox(Graphics2D g, int x, int y, int w, int h) {
    g.drawImage(_makeDiaBoxImg(w, h), x, y, null);
    /*
    // draw corners
    g.drawImage(MenuImages.diaNW(), 0, 0, null);
    g.drawImage(MenuImages.diaNE(), w - 16, 0, null);
    g.drawImage(MenuImages.diaSW(), 0, h - 16, null);
    g.drawImage(MenuImages.diaSE(), w - 16, h - 16, null);
    
    // draw borders
    for(int i = 16; i < w - 16; i+=16) {
      g.drawImage(MenuImages.diaN(), i, 0, null);
      g.drawImage(MenuImages.diaS(), i, h - 16, null);
    }
    for(int j = 16; j < h - 16; j+=16) {
      g.drawImage(MenuImages.diaW(), 0, j, null);
      g.drawImage(MenuImages.diaE(), w - 16, j, null);
    }
    
    // draw interior
    for(int i = 16; i < w - 16; i+=16) {
      for(int j = 16; j < h - 16; j+=16) {
        g.drawImage(MenuImages.diaCenter(), i, j, null);
      }
    }
    */
  }
  
  private static Image _makeDiaBoxImg(int w, int h) {
    BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = result.createGraphics();
    
    // draw corners
    g.drawImage(MenuImages.diaNW(), 0, 0, null);
    g.drawImage(MenuImages.diaNE(), w - 16, 0, null);
    g.drawImage(MenuImages.diaSW(), 0, h - 16, null);
    g.drawImage(MenuImages.diaSE(), w - 16, h - 16, null);
    
    // draw borders
    for(int i = 16; i < w - 16; i+=16) {
      g.drawImage(MenuImages.diaN(), i, 0, null);
      g.drawImage(MenuImages.diaS(), i, h - 16, null);
    }
    for(int j = 16; j < h - 16; j+=16) {
      g.drawImage(MenuImages.diaW(), 0, j, null);
      g.drawImage(MenuImages.diaE(), w - 16, j, null);
    }
    
    // draw interior
    for(int i = 16; i < w - 16; i+=16) {
      for(int j = 16; j < h - 16; j+=16) {
        g.drawImage(MenuImages.diaCenter(), i, j, null);
      }
    }
    
    g.dispose();
    
    return ImageEffects.addColor(result, new Color(GameConfig.getDiaColor()));
  }
  
  
  //////// Pointers
  
  /** Loads the menu pointers. */
  private static void _loadPointers(ImageLoader imgLoader) {
    Image img = imgLoader.loadResource("graphics/menus/pointers.png");
    img = ImageEffects.setTransparentColor(img, new Color(0x000000));
    images.put("pointer", img);
    imgLoader.addImage(img);
  }
  
  
  /** Returns the image for the menu pointer. */
  public static Image pointer() {
    return _getImg("pointer");
  }
  
  
  
  //////// Fonts
  
  /** Loads the fonts used in menus and dialogs. */
  private static void _loadFonts(ImageLoader imgLoader) {
    font = new BlitteredFont(true, 10, 12, 0, 5);
    font.loadImages(imgLoader, "graphics/menus/font2.png", new Color(0x000000));
    
    fontCompressed = new BlitteredFont(false, 10, 12, 1, 5);
    fontCompressed.loadImages(imgLoader, "graphics/menus/font2.png", new Color(0x000000));
    
    equestriaFont = new BlitteredFont(true, 10, 12, 0, 5);
    equestriaFont.loadImages(imgLoader, "graphics/menus/font1.png", new Color(0x000000));
  }
  
  /** Returns the BlitteredFont used to render text for menus and dialogs. */
  public static BlitteredFont font() {
    return font;
  }
  
  /** Returns a compact, non-monospaced version of font(). */
  public static BlitteredFont fontCompressed() {
    return fontCompressed;
  }
  
  /** Returns a more Equestria-style BlitteredFont. */
  public static BlitteredFont font2() {
    return equestriaFont;
  }
  
  
  
  //////// Menu icons
  public static void _loadIcons(ImageLoader imgLoader) {
    Image srcImg = imgLoader.loadResource("graphics/menus/menuIcons.png");
    srcImg = ImageEffects.setTransparentColor(srcImg, new Color(0x000000));
    
    _loadIcon12x12(imgLoader, srcImg, "icon_time", 0, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_resources", 1, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_bits", 2, 0);
    
    _loadIcon12x12(imgLoader, srcImg, "icon_item", 3, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_ability", 4, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_equip", 5, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_status", 6, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_party", 7, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_config", 8, 0);
    _loadIcon12x12(imgLoader, srcImg, "icon_save", 9, 0);
  }
  
  /** Loads the image for a 12x12 icon from a spritemap. */
  private static void _loadIcon12x12(ImageLoader imgLoader, Image srcImg, String imgName, int gridX, int gridY) {
    int x = gridX*14 + 1;
    int y = gridY*14 + 1;
    
    Image img = ImageEffects.crop(srcImg, x, y, 12, 12);
    imgLoader.addImage(img);
    images.put(imgName, img);
  }
  
  
  /** Returns the time icon. */
  public static Image iconTime() {
    return _getImg("icon_time");
  }
  
  /** Returns the resources icon. */
  public static Image iconResources() {
    return _getImg("icon_resources");
  }
  
  /** Returns the bits icon. */
  public static Image iconBits() {
    return _getImg("icon_bits");
  }
  
  public static Image iconItem() {
    return _getImg("icon_item");
  }
  
  public static Image iconAbility() {
    return _getImg("icon_ability");
  }
  
  public static Image iconEquip() {
    return _getImg("icon_equip");
  }
  
  public static Image iconStatus() {
    return _getImg("icon_status");
  }
  
  public static Image iconParty() {
    return _getImg("icon_party");
  }
  
  public static Image iconConfig() {
    return _getImg("icon_config");
  }
  
  public static Image iconSave() {
    return _getImg("icon_save");
  }
  
}
