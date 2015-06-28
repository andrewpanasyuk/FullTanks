import ObjectBF.*;

/**
 * Created by panasyuk on 16.06.2015.
 */
public class Field {
    private Construct[][] batlefield;
    public int bfWidth = 576;
    public int bfHeight = 576;

    public Field() {
        //String [][]start = makeField();
        this.batlefield = makeField();
    }

    public Field(int i) {
        if (i == 1) {
            //String [][]start = makeField();
            this.batlefield = cff();
        } else {
            this.batlefield = makeField();
        }
    }

    private Construct[][] makeField() {
        Construct[][] bf = new Construct[9][9];
        for (int i = 0; i < 9; i++) {
            for (int n = 0; n < 9; n++) {
                int s = Generation.gen(0, 9);
                if (s == 0 || s == 1) {
                    bf[i][n] = new Water(i, n);
                } else if (s == 2) {
                    bf[i][n] = new Brick(i, n);
                } else if (s == 3) {
                    bf[i][n] = new Rock(i, n);
                } else {
                    bf[i][n] = new Ampty(i, n);
                }
            }
        }
        return bf;
    }

    public Construct[][] cff() {
        String[][] bf = new String[][]{
                {" ", " ", "B", " ", " ", " ", " ", " ", " "},
                {" ", " ", "B", "W", "W", "W", " ", " ", " "},
                {"B", "B", "B", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", "R", "R", " ", " ", " ", " ", " "},
                {"W", " ", "B", "R", "R", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", "E", " "}
        };


        Construct[][] constractField = new Construct[9][9];
        for (int i = 0; i < 9; i++) {
            for (int n = 0; n < 9; n++) {
                if (bf[i][n] == "B") {
                    constractField[i][n] = new Brick(i, n);
                } else if (bf[i][n] == "R") {
                    constractField[i][n] = new Rock(i, n);
                } else if (bf[i][n] == "W") {
                    constractField[i][n] = new Water(i, n);
                } else if (bf[i][n] == "E") {
                    constractField[i][n] = new Eagle(i, n);
                } else {
                    constractField[i][n] = new Ampty(i, n);
                }
            }
        }
        return constractField;
    }


    public Construct[][] getBatlefield() {
        return batlefield;
    }

    public void setBatlefield(Construct[][] batlefield) {
        this.batlefield = batlefield;
    }

    public Construct scanQuadrant(int x, int y) {
        return batlefield[x][y];
    }

    public void updateQuadrant(int x, int y, Construct newParametr) {
        this.batlefield[x][y] = newParametr;
    }

    public int getDimentionY() {
        int dimentionY = bfHeight / 64;
        return dimentionY;

    }

    public int getDimentionX() {
        int dimentionX = bfWidth / 64;
        return dimentionX;

    }

    public int getBfWidth() {
        return bfWidth;
    }

    public int getBfHeight() {
        return bfHeight;
    }

}
