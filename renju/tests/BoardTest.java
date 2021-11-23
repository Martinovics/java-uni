package renju.tests;

import org.junit.*;

import static org.junit.Assert.*;

import java.awt.Color;
import renju.classes.*;




public class BoardTest {

	private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    
    @Test                                                
    public void updateTest() {
    	assertTrue(board.update(0, 0, Color.BLACK));
    	assertFalse(board.update(0, 0, Color.BLACK));
    	assertFalse(board.update(0, 0, Color.WHITE));
    	assertTrue(board.update(0, 1, Color.BLACK));
    }
    
    
    @Test                                                
    public void colorAtTest() {
    	board.update(0, 0, Color.BLACK);
    	assertTrue(board.colorAt(0, 0).equals(Color.BLACK));
    	assertFalse(board.colorAt(0, 0).equals(Color.WHITE));
    	board.update(0, 1, Color.WHITE);
    	assertTrue(board.colorAt(0, 1).equals(Color.WHITE));
    	assertFalse(board.colorAt(0, 1).equals(Color.BLACK));
    }
    
    
    @Test                                                
    public void countColorTest() {
    	assertEquals(0, board.countColor(Color.BLACK));
    	assertEquals(0, board.countColor(Color.WHITE));
    	board.update(0, 0, Color.BLACK);
    	board.update(0, 0, Color.BLACK); // err
    	board.update(0, 1, Color.WHITE);
    	assertEquals(1, board.countColor(Color.BLACK));
    	assertEquals(1, board.countColor(Color.WHITE));
    }

    
}
