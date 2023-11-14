package sdf;

import java.util.ArrayList;

public class Cell {
    private Integer x_coord;
    private Integer y_coord;
    private Boolean alive = false;

    public void revive() {
        alive = true;
    }

    public Boolean isAlive() {
        return this.alive;
    }

    public Integer[] getXY_coord() {
        Integer[] res = {x_coord, y_coord};
        return res;
    }

    public void setXY_coord(Integer x_coord, Integer y_coord) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }


    public void kill() {
        alive = false;
    }

    public Boolean nextStateAlive(ArrayList<ArrayList<Cell>> grid, Integer x_len, Integer y_len) {
        Integer neighbours = 0;

        Integer x_iter = x_coord-1;
        Integer y_iter = y_coord-1;
        //iterate x_coord
        // System.out.printf("CURRENT X,Y: (%s, %s)\n", x_coord, y_coord);
        for (int y=y_iter; y<(y_iter+3); y++) {
            if ((y >= 0) && (y<y_len)) {
                for (int x=x_iter; x<(x_iter+3); x++) {
                    if ((x >= 0) && (x<x_len) && !((x==x_coord) && (y==y_coord))) {
                        Cell compareCell = grid.get(y).get(x);
                        // System.out.printf("Checking: %s, %s (%s)\n", x, y, compareCell.isAlive());
                        if (compareCell.isAlive()) {
                            neighbours ++;
                        }
                    }
                }
            }
        }
        // System.out.printf("NEIGHBOURS FOR (%s, %s): %s\n", x_coord, y_coord, neighbours);
        Boolean state;
        if (neighbours<=1) {
            state = false;
        } else if ((neighbours==2) && (this.alive)) {
            state = true;
        } else if (neighbours==3) {
            state = true;
        } else {
            state = false;
        }
        // System.out.printf("ALIVE NEXT FOR (%s, %s): %s\n", x_coord, y_coord, state);
        // System.out.printf("ALIVE NOW (%s, %s): %s\n", x_coord, y_coord, this.alive);
        // System.out.println();
        return state;
    }

    public void returnNextState(ArrayList<ArrayList<Cell>> grid, ArrayList<ArrayList<Cell>> newGrid, Integer x_len, Integer y_len) {
        Integer neighbours = 0;

        Integer x_iter = x_coord-1;
        Integer y_iter = y_coord-1;
        
        //iterate x_coord
        for (int y=y_iter; y<(y_iter+3); y++) {
            if ((y >= 0) && (y<y_len)) {
                for (int x=x_iter; x<(x_iter+3); x++) {
                    if ((x >= 0) && (x<x_len) && !((x==x_coord) && (y==y_coord))) {
                        Cell compareCell = grid.get(y).get(x);
                        // System.out.printf("Checking: %s, %s\n", x, y);
                        if (compareCell.isAlive()) {
                            neighbours ++;
                        }
                    }
                }
            }
        }

        if (neighbours<=1) {
            newGrid.get(y_coord).get(x_coord).kill();

        } else if ((neighbours==2) && (this.alive)) {
            newGrid.get(y_coord).get(x_coord).revive();

        } else if (neighbours==3) {
            newGrid.get(y_coord).get(x_coord).revive();

        } else if (neighbours>=4) {
            newGrid.get(y_coord).get(x_coord).kill();
        }
    }
}
