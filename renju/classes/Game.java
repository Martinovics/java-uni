package renju.classes;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/** J�t�k oszt�ly.
*/
@SuppressWarnings("serial")
public class Game implements Serializable {

	/** Els� j�t�kos.
	*/
    public Player p1;
    /** M�sodik j�t�kos.
    */
    public Player p2;
    /** J�t�kmez�.
    */
    public Board board;
    /** Utols� ment�s ideje.
    */
    private String lastSaved;
    /** A nyertes j�t�kos.
    */
    private Player winner;


    
    
    /**
     * L�trehoz egy j�t�kot, �res j�t�kmez�vel.  
     *
     * @param  p1 Els� j�t�kos.
     * @param  p2 M�sodik j�t�kos.
     */
    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new Board();
    }

    
    
    
    /**
     * L�trehoz egy j�t�kot, a megadott j�t�kmez�vel.  
     *
     * @param  p1 Els� j�t�kos.
     * @param  p2 M�sodik j�t�kos.
     * @param  board A j�t�kmez�, melyet haszn�lni szeretn�nk.
     */
    public Game(Player p1, Player p2, Board board) {
        this(p1, p2);
        this.board = board;
    }




    
    /**
     * Megadja, hogy a j�t�k mikor volt utolj�ra elmentve.  
     *
     * @return Utols� ment�si id� (string-k�nt).
     */
    public String getLastSaveTime() {
        return this.lastSaved;
    }



    
    /**
     * Megadja, hogy melyik j�t�kos birtokolja a megadott sz�nt.  
     *
     * @param  color Sz�n.
     * @return A megfelel� sz�n� j�t�kos.
     */
    public Player whoHasColor(Color color) {
        if (this.p1.getColor().equals(color)) {
            return this.p1;
        } else {
            return this.p2;
        }
    }




    /**
     * Be�ll�tja, hogy ki nyerte a j�t�kot.  
     *
     * @param p A j�t�kos, aki a nyertes lesz.
     */
    public void setWinner(Player p) {
        this.winner = p;
    }




    /**
     * Megadja, hogy van-e m�r nyertese a j�t�knak.  
     *
     * @return True, ha van, egy�bk�nt false.
     */
    public boolean hasWinner() {
        if (this.winner != null) {
            return true;
        } else {
            return false;
        }
    }




    
    /**
     * Megadja, hogy melyik j�t�kos nyerte a j�t�kot.  
     *
     * @return A nyertes j�t�kos.
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
     * Megadja, hogy a fekete j�t�kosnak van-e dupla nyitott h�rmasa vagy n�gyese.  
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
     * Megadja, hogy a megadott sz�nnek milyen hossz� a legnagyobb streak-je.  
     *
     * @param  color A sz�n, melyet vizsg�lni szeretn�nk.
     * @return Eg�sz sz�m.
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
     * Elmenti a j�t�kot.  
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
