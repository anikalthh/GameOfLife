package sdf;

import java.util.ArrayList;

public class GoL {
    public static ArrayList<ArrayList<Cell>> play(ArrayList<ArrayList<Cell>> grid) {
        Integer x_len = grid.get(0).size();
        Integer y_len = grid.size();
        ArrayList<ArrayList<Cell>> newGrid = new ArrayList<>();

        //make new grid
        for (int i=0; i<y_len; i++) {
            ArrayList<Cell> row = new ArrayList<>();
            newGrid.add(row);
            for (int j=0; j<x_len; j++) {
                Cell cell = new Cell();
                row.add(cell);
                cell.setXY_coord(j, i);
            }
        }

        for (int y=0; y<y_len; y++) {
            System.out.println();
            for (int x=0; x<x_len; x++){
                Cell currCell = grid.get(y).get(x);
                Boolean nextStateAlive = currCell.nextStateAlive(grid, x_len, y_len);
                    if (nextStateAlive) {
                        System.out.print('*');
                    } else {
                        System.out.print('.');
                    }
            }
        }


        for (int y=0; y<y_len; y++) {
            for (int x=0; x<x_len; x++){
                Cell currCell = grid.get(y).get(x);
                currCell.returnNextState(grid, newGrid, x_len, y_len);
            }
        }

        return newGrid;
    }
}
