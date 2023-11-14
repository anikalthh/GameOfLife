package sdf;

import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception{
        File f = new File("gol/glider.gol");
        ArrayList<ArrayList<Cell>> grid = ReadFile.readFile(f);

        for (int i=0; i<5; i++) {
            grid = GoL.play(grid);
            System.out.println();
        }
        
    }
}
