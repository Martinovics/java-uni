package renju.tests;

import org.junit.*;

import static org.junit.Assert.*;

import java.awt.Color;
import renju.classes.Player;




public class PlayerTest {

	private Player player;

    @Before
    public void setUp() {
        player = new Player("PlayerName", Color.BLACK);
    }

    
    @Test                                                
    public void setColorTest() {
    	player.setColor(Color.WHITE);
    	assertTrue(player.getColor().equals(Color.WHITE));
    	player.setColor(Color.BLACK);
    	assertTrue(player.getColor().equals(Color.BLACK)); 
    }
    
    
    @Test                                                
    public void getColorNameTest() {
    	assertEquals("BLACK", player.getColorName()); 
    	player.setColor(Color.WHITE);
    	assertEquals("WHITE", player.getColorName()); 
    }

    
}
