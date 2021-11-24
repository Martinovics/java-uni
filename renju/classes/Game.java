package renju.classes;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/** Játék osztály.
*/
@SuppressWarnings("serial")
public class Game implements Serializable {

	/** Elsõ játékos.
	*/
    public Player p1;
    /** Második játékos.
    */
    public Player p2;
    /** Játékmezõ.
    */
    public Board board;
    /** Utolsó mentés ideje.
    */
    private String lastSaved;
    /** A nyertes játékos.
    */
    private Player winner;


    
    
    /**
     * Létrehoz egy játékot, üres játékmezõvel.  
     *
     * @param  p1 Elsõ játékos.
     * @param  p2 Második játékos.
     */
    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new Board();
    }

    
    
    
    /**
     * Létrehoz egy játékot, a megadott játékmezõvel.  
     *
     * @param  p1 Elsõ játékos.
     * @param  p2 Második játékos.
     * @param  board A játékmezõ, melyet használni szeretnénk.
     */
    public Game(Player p1, Player p2, Board board) {
        this(p1, p2);
        this.board = board;
    }




    
    /**
     * Megadja, hogy a játék mikor volt utoljára elmentve.  
     *
     * @return Utolsó mentési idõ (string-ként).
     */
    public String getLastSaveTime() {
        return this.lastSaved;
    }



    
    /**
     * Megadja, hogy melyik játékos birtokolja a megadott színt.  
     *
     * @param  color Szín.
     * @return A megfelelõ színû játékos.
     */
    public Player whoHasColor(Color color) {
        if (this.p1.getColor().equals(color)) {
            return this.p1;
        } else {
            return this.p2;
        }
    }




    /**
     * Beállítja, hogy ki nyerte a játékot.  
     *
     * @param p A játékos, aki a nyertes lesz.
     */
    public void setWinner(Player p) {
        this.winner = p;
    }




    /**
     * Megadja, hogy van-e már nyertese a játéknak.  
     *
     * @return True, ha van, egyébként false.
     */
    public boolean hasWinner() {
        if (this.winner != null) {
            return true;
        } else {
            return false;
        }
    }




    
    /**
     * Megadja, hogy melyik játékos nyerte a játékot.  
     *
     * @return A nyertes játékos.
     */
    public Player getWinner() {
        return this.winner;
    }




    private String colorLetterB(Color color) {
        if (color.equals(Color.BLACK)) {
            return "b";
        } else {
            return ".";
        }
    }




    private boolean hasDoubleOpened(String str) {
        if (str.startsWith("bbb.bbb.") || str.contains(".bbb.bbb.") || str.endsWith(".bbb.bbb")) {
            return true;
        } else if (str.startsWith("bbbb.bbbb.") || str.contains(".bbbb.bbbb.") || str.endsWith(".bbbb.bbbb")) {
            return true;
        } else {
            return false;
        }
    }




    /**
     * Megadja, hogy a fekete játékosnak van-e dupla nyitott hármasa vagy négyese.  
     *
     * @return True/false.
     */
    public boolean hasDoubleOpened() {

        // horizontal
        for (int row=0; row != 15; row+=1) {
            String boardStr = "";
            for (int col=0; col != 15; col+=1)
                boardStr += colorLetterB(this.board.colorAt(row, col));
            if (hasDoubleOpened(boardStr)) return true;
        }

        // vertical
        for (int col=0; col != 15; col+=1) {
            String boardStr = "";
            for (int row=0; row != 15; row+=1)
                boardStr += colorLetterB(this.board.colorAt(row, col));
            if (hasDoubleOpened(boardStr)) return true;
        }

        // diagonals
        int mtxSize = 15;

        // (+) diagonals
        for (int startCol=0; startCol != mtxSize; startCol+=1) {
            String boardStr = "";
            for (int row=0, col=startCol; row != mtxSize && 0 <= col; row += 1, col -= 1) {
                boardStr += colorLetterB(this.board.colorAt(row, col));
            }
            if (hasDoubleOpened(boardStr)) return true;
        }
        for (int startRow=1; startRow != mtxSize; startRow+=1) {
            String boardStr = "";
            for (int row=startRow, col=mtxSize-1; row != mtxSize && 0 <= col; row+=1, col-=1) {
                boardStr += colorLetterB(this.board.colorAt(row, col));
            }
            if (hasDoubleOpened(boardStr)) return true;
        }

        // (-) diagonals
        for (int startCol=mtxSize-1; 0 <= startCol; startCol-=1) {
            String boardStr = "";
            for (int row=0, col=startCol; row != mtxSize && col != mtxSize; row += 1, col += 1) {
                boardStr += colorLetterB(this.board.colorAt(row, col));
            }
            if (hasDoubleOpened(boardStr)) return true;
        }
        for (int startRow=1; startRow != mtxSize; startRow+=1) {
            String boardStr = "";
            for (int row=startRow, col=0; row != mtxSize && col != mtxSize; row += 1, col += 1) {
                boardStr += colorLetterB(this.board.colorAt(row, col));
            }
            if (hasDoubleOpened(boardStr)) return true;
        }

        return false;
    }




    private String colorLetter(Color color) {
        if (color.equals(Color.BLACK)) {
            return "b";
        } else if (color.equals(Color.WHITE)) {
            return "w";
        } else {
            return ".";
        }
    }




    private int longestStreak(String str, Color color) {
        if (!color.equals(Color.BLACK) && !color.equals(Color.WHITE)) return 0;

        for (int streak=15; 0 <= streak; streak-=1) {
            String subStr = "";
            for (int i=0; i != streak; i+=1) {
                subStr += colorLetter(color);
            }

            if (str.contains(subStr)) {
                return streak;
            }
        }
        return 0;
    }




    /**
     * Megadja, hogy a megadott színnek milyen hosszú a legnagyobb streak-je.  
     *
     * @param  color A szín, melyet vizsgálni szeretnénk.
     * @return Egész szám.
     */
    public int longestStreak(Color color) {

        int maxStreak = 0;

        // horizontal
        for (int row=0; row != 15; row+=1) {
            String boardStr = "";
            for (int col=0; col != 15; col+=1)
                boardStr += colorLetter(this.board.colorAt(row, col));
            int streak = longestStreak(boardStr, color);
            if (maxStreak < streak) maxStreak = streak;
        }

        // vertical
        for (int col=0; col != 15; col+=1) {
            String boardStr = "";
            for (int row=0; row != 15; row+=1)
                boardStr += colorLetter(this.board.colorAt(row, col));
            int streak = longestStreak(boardStr, color);
            if (maxStreak < streak) maxStreak = streak;
        }

        // diagonals
        int mtxSize = 15;

        // (+) diagonals
        for (int startCol=0; startCol != mtxSize; startCol+=1) {
            String boardStr = "";
            for (int row=0, col=startCol; row != mtxSize && 0 <= col; row += 1, col -= 1)
                boardStr += colorLetter(this.board.colorAt(row, col));
            int streak = longestStreak(boardStr, color);
            if (maxStreak < streak) maxStreak = streak;
        }
        for (int startRow=1; startRow != mtxSize; startRow+=1) {
            String boardStr = "";
            for (int row=startRow, col=mtxSize-1; row != mtxSize && 0 <= col; row+=1, col-=1)
                boardStr += colorLetter(this.board.colorAt(row, col));
            int streak = longestStreak(boardStr, color);
            if (maxStreak < streak) maxStreak = streak;
        }

        // (-) diagonals
        for (int startCol=mtxSize-1; 0 <= startCol; startCol-=1) {
            String boardStr = "";
            for (int row=0, col=startCol; row != mtxSize && col != mtxSize; row += 1, col += 1)
                boardStr += colorLetter(this.board.colorAt(row, col));
            int streak = longestStreak(boardStr, color);
            if (maxStreak < streak) maxStreak = streak;
        }
        for (int startRow=1; startRow != mtxSize; startRow+=1) {
            String boardStr = "";
            for (int row=startRow, col=0; row != mtxSize && col != mtxSize; row += 1, col += 1)
                boardStr += colorLetter(this.board.colorAt(row, col));
            int streak = longestStreak(boardStr, color);
            if (maxStreak < streak) maxStreak = streak;
        }

        return maxStreak;
    }



    /**
     * Elmenti a játékot.  
     */
    public void save() {
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        this.lastSaved = filename;

        System.out.println("saved game: " + filename + ".ser");

        try {
            FileOutputStream fo = new FileOutputStream("./saves/" + filename + ".ser");
            ObjectOutputStream so = new ObjectOutputStream(fo);
            so.writeObject(this);
            so.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
