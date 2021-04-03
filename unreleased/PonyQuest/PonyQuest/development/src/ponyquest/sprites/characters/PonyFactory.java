package ponyquest.sprites.characters;

import javax.swing.JOptionPane;

public class PonyFactory {
  
  /** Loads the image data for all the concrete pony classes. */
  public static void loadImages() {
    AirHeartSprite.loadImages();
    ApplebloomSprite.loadImages();
    ApplejackSprite.loadImages();
    AppleTartSprite.loadImages();
    BigMacintoshSprite.loadImages();
    BonBonSprite.loadImages();
    CaramelSprite.loadImages();
    CarrotCakeSprite.loadImages();
    CheerileeSprite.loadImages();
    CupCakeSprite.loadImages();
    DerpySprite.loadImages();
    DiamondTiaraSprite.loadImages();
    DinkySprite.loadImages();
    DrWhoovesSprite.loadImages();
    FluttershySprite.loadImages();
    GoldenHarvestSprite.loadImages();
    GrannySmithSprite.loadImages();
    LyraHeartstringsSprite.loadImages();
    MayorMareSprite.loadImages();
    MochaccinoSprite.loadImages();
    PinkiePieSprite.loadImages();
    RainbowDashSprite.loadImages();
    RaritySprite.loadImages();
    ScootalooSprite.loadImages();
    SilverSpoonSprite.loadImages();
    SnailsSprite.loadImages();
    SnipsSprite.loadImages();
    SassaflashSprite.loadImages();
    StarHunterSprite.loadImages();
    SweetieBelleSprite.loadImages();
    TwilightSparkleSprite.loadImages();
    TwinkleshineSprite.loadImages();
    ZecoraSprite.loadImages();
  }
  
  
  
  /** Create a sprite for a pony by name. */
  public static PonySprite getPony(String ponyName) {
    PonySprite pony = null;
    switch(ponyName.replaceAll("\\s","").toLowerCase()) {
      default:
        JOptionPane.showMessageDialog(null, "No pony exists for " + ponyName + ". Exiting animation test.");
        System.exit(1);
        break;
      case "airheart":
        pony = new AirHeartSprite(0, 0);
        break;
      case "applebloom":
        pony = new ApplebloomSprite(0, 0);
        break;
      case "applejack":
        pony = new ApplejackSprite(0, 0);
        break;
      case "appletart":
        pony = new AppleTartSprite(0, 0);
        break;
      case "bigmacintosh": case "bigmac": case "bigmcintosh":
        pony = new BigMacintoshSprite(0, 0);
        break;
      case "bonbon": case "bon-bon":
        pony = new BonBonSprite(0, 0);
        break;
      case "caramel":
        pony = new CaramelSprite(0, 0);
        break;
      case "carrotcake": case "mrcake": case "mr.cake":
        pony = new CarrotCakeSprite(0, 0);
        break;
      case "cheerilee":
        pony = new CheerileeSprite(0, 0);
        break;
      case "cupcake": case "mrscake": case "mrs.cake":
        pony = new CupCakeSprite(0, 0);
        break;
      case "derpy": case "derpyhooves": case "ditzy": case "ditzydoo":
        pony = new DerpySprite(0, 0);
        break;
      case "diamondtiara":
        pony = new DiamondTiaraSprite(0, 0);
        break;
      case "dinky":
        pony = new DinkySprite(0, 0);
        break;
      case "drwhooves": case "doctorwhooves": case "timeturner":
        pony = new DrWhoovesSprite(0, 0);
        break;
      case "fluttershy":
        pony = new FluttershySprite(0, 0);
        break;
      case "goldenharvest": case "carrottop":
        pony = new GoldenHarvestSprite(0, 0);
        break;
      case "grannysmith":
        pony = new GrannySmithSprite(0, 0);
        break;
      case "lyraheartstrings":
        pony = new LyraHeartstringsSprite(0, 0);
        break;
      case "mayormare":
        pony = new MayorMareSprite(0, 0);
        break;
      case "mochaccino":
        pony = new MochaccinoSprite(0, 0);
        break;
      case "pinkiepie": case "pinkie":
        pony = new PinkiePieSprite(0, 0);
        break;
      case "rainbowdash":
        pony = new RainbowDashSprite(0, 0);
        break;
      case "rarity":
        pony = new RaritySprite(0, 0);
        break;
      case "scootaloo": case "scoots": case "chicken":
        pony = new ScootalooSprite(0, 0);
        break;
      case "silverspoon":
        pony = new SilverSpoonSprite(0, 0);
        break;
      case "snails":
        pony = new SnailsSprite(0, 0);
        break;
      case "snips":
        pony = new SnipsSprite(0, 0);
        break;
      case "sassaflash":
        pony = new SassaflashSprite(0, 0);
        break;
      case "starhunter":
        pony = new StarHunterSprite(0, 0);
        break;
      case "sweetiebelle":
        pony = new SweetieBelleSprite(0, 0);
        break;
      case "twilightsparkle": case "twilight":
        pony = new TwilightSparkleSprite(0, 0);
        break;
      case "twinkleshine":
        pony = new TwinkleshineSprite(0, 0);
        break;
      case "zecora":
        pony = new ZecoraSprite(0, 0);
        break;
    }
    return pony;
  }

}
