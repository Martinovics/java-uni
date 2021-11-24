package renju.classes;

import java.awt.*;
import java.io.Serializable;



/** J�t�kos oszt�ly.
*/
@SuppressWarnings("serial")
public class Player implements Serializable {

	/** A j�t�kos sz�ne.
	*/
    private String name;
    /** A j�t�kos neve.
	*/
    private Color color;

    
    /**
     * Inicializ�l egy j�t�kost.  
     *
     * @param  name A j�t�kos neve.
     * @param  color A j�t�kos sz�ne.
     */
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }


    
    
    /**
     * Megadja, a j�t�kos nev�t.
     * 
     * @return A jat�kos neve.
     */
    public String getName() {
        return name;
    }

    
    
    
    /**
     * Megadja, a j�t�kos sz�n�t.  
     * 
     * @return A j�t�kos sz�ne.
     */
    public Color getColor() {
        return color;
    }


    
    /**
     * Megadja, a j�t�kos sz�n�nek nev�t.  
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
     * Be�ll�tja a j�t�kos sz�n�t.  
     *
     * @param  color Erre a sz�nre �ll�tja a j�t�kos sz�n�t.
     */
    public void setColor(Color color) {
        this.color = color;
    }


}
