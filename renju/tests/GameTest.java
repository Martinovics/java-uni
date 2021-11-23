package renju.tests;

import org.junit.*;

import static org.junit.Assert.*;

import java.awt.Color;
import renju.classes.*;




public class GameTest {

	private Game game;

    @Before
    public void setUp() {
        game = new Game(new Player("P1", Color.BLACK), new Player("P2", Color.WHITE));
    }

    
    @Test                                                
    public void hasDoubleOpenedTest() {
    	assertFalse(game.hasDoubleOpened());
    	game.board.update(0, 0, Color.BLACK);
    	game.board.update(1, 1, Color.BLACK);
    	game.board.update(2, 2, Color.BLACK);
    	game.board.update(4, 4, Color.BLACK);
    	game.board.update(5, 5, Color.BLACK);
    	assertFalse(game.hasDoubleOpened());
    	game.board.update(6, 6, Color.BLACK);
    	assertTrue(game.hasDoubleOpened());
    }
    
    
    @Test                                                
    public void longestStreakTest() {
    	assertEquals(0, game.longestStreak(Color.BLACK));
    	game.board.update(0, 0, Color.BLACK);
    	game.board.update(1, 1, Color.BLACK);
    	assertEquals(2, game.longestStreak(Color.BLACK));
    	assertEquals(0, game.longestStreak(Color.WHITE));
    }
    
    
    @Test                                                
    public void whoHasColorTest() {
    	assertEquals("P1", game.whoHasColor(Color.BLACK).getName());
    	assertEquals("P2", game.whoHasColor(Color.WHITE).getName());
    }
    
    
    @Test                                                
    public void setWinnerTest() {
    	game.setWinner(new Player("P1", Color.BLACK));
    	assertEquals("P1", game.whoHasColor(Color.BLACK).getName());
    	game.setWinner(new Player("P1", Color.WHITE));
    	assertEquals("P2", game.whoHasColor(Color.WHITE).getName());
    }
    
    
    @Test                                                
    public void hasWinnerTest() {
    	assertFalse(game.hasWinner());
    	game.setWinner(new Player("P1", Color.BLACK));
    	assertTrue(game.hasWinner());
    }

    
}
