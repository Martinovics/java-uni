package renju.classes;

import java.awt.*;
import java.io.Serializable;



/** Játékos osztály.
*/
@SuppressWarnings("serial")
public class Player implements Serializable {

	/** A játékos színe.
	*/
    private String name;
    /** A játékos neve.
	*/
    private Color color;

    
    /**
     * Inicializál egy játékost.  
     *
     * @param  name A játékos neve.
     * @param  color A játékos színe.
     */
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }


    
    
    /**
     * Megadja, a játékos nevét.
     * 
     * @return A jatékos neve.
     */
    public String getName() {
        return name;
    }

    
    
    
    /**
     * Megadja, a játékos színét.  
     * 
     * @return A játékos színe.
     */
    public Color getColor() {
        return color;
    }


    
    /**
     * Megadja, a játékos színének nevét.  
     * 
     * @return 'BLACK'/'WHITE'
     */
    public String getColorName() {
        if (this.color.equals(Color.BLACK)) {
            return "BLACK";
        } else {
            return "WHITE";
        }
    }

    
    
    
    /**
     * Beállítja a játékos színét.  
     *
     * @param  color Erre a színre állítja a játékos színét.
     */
    public void setColor(Color color) {
        this.color = color;
    }


}
