package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    private static Integer start_x;
    private static Integer start_y;
    private static Integer x_len;
    private static Integer y_len;
    private static ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

    public static ArrayList<ArrayList<Cell>> readFile(File f) throws FileNotFoundException, IOException {
        try (FileReader fr = new FileReader(f)) {
            BufferedReader br = new BufferedReader(fr);

            String line;

            while (null!=(line=br.readLine())) {
                String[] words = line.split(" ");
                String firstWord = words[0].trim();

                switch (firstWord) {
                    case "#":
                        continue;

                    case "GRID":
                        x_len = Integer.parseInt(words[1].trim());
                        y_len = Integer.parseInt(words[2].trim());
                        
                        for (int i=0; i<y_len; i++) {
                            ArrayList<Cell> row = new ArrayList<>();
                            grid.add(row);
                            for (int j=0; j<x_len; j++) {
                                Cell cell = new Cell();
                                row.add(cell);
                                cell.setXY_coord(j, i);
                            }
                        }
                        break;

                    case "START":
                        start_x = Integer.parseInt(words[1].trim());
                        start_y = Integer.parseInt(words[2].trim());
                        break;
                    
                    case "DATA":
                        Integer x_iterator = start_x;
                        Integer y_iterator = start_y;
                        while (null!=(line=br.readLine())) {
                            x_iterator = start_x;
                            char[] data = line.toCharArray();

                            //iterate through each data line
                            for (int j=0; j<data.length; j++) {
                                switch (data[j]) {
                                    case ' ':
                                        x_iterator ++;
                                        break;
                                    case '*':
                                        Cell currCell = grid.get(y_iterator).get(x_iterator);
                                        currCell.revive();
                                        x_iterator ++;
                                        break;
                                }
                            }
                            y_iterator ++;
                        }  
                }
            }
        }
        for (int y=0; y<y_len; y++) {
            System.out.println();
            for (int x=0; x<x_len; x++){
                Cell currCell = grid.get(y).get(x);
                if (currCell.isAlive()) {
                    System.out.print('*');
                } else {
                    System.out.print('.');
                }
            }
        }
        System.out.println();
        return grid;
        
    }
}
