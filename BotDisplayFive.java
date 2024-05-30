import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BotDisplayFive here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BotDisplayFive extends Actor
{
    /**
     * Act - do whatever the BotDisplayFive wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public void scaleImage(int width, int height) {
        GreenfootImage image = getImage();
        image.scale(20, 20);
        setImage(image);
    }
}
