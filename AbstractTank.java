import ObjectBF.Destroy;

import java.awt.*;

/**
 * Created by panasyuk on 16.06.2015.
 */
public abstract class AbstractTank implements Destroy {
    private String name;
    private int x;
    private int y;
    private Direction direction;
    protected int speed = 10;
    private ActionField af;
    protected Field bf;
    private int armor;
    protected Bullet bullet;
    protected int power;
    private String nameImageUP;
    private String nameImageD;
    private String nameImageL;
    private String nameImageR;
    private Image imgUP;
    private Image imgD;
    private Image imgL;
    private Image imgR;

    public String getNameImageD() {
        return nameImageD;
    }

    public void setNameImageD(String nameImageD) {
        this.nameImageD = nameImageD;
    }

    public String getNameImageL() {
        return nameImageL;
    }

    public void setNameImageL(String nameImageL) {
        this.nameImageL = nameImageL;
    }

    public String getNameImageR() {
        return nameImageR;
    }

    public void setNameImageR(String nameImageR) {
        this.nameImageR = nameImageR;
    }

    public Image getImgR() {
        return imgR;
    }

    public void setImgR(Image imgR) {
        this.imgR = imgR;
    }

    public Image getImgL() {
        return imgL;
    }

    public void setImgL(Image imgL) {
        this.imgL = imgL;
    }

    public Image getImgD() {
        return imgD;
    }

    public void setImgD(Image imgD) {
        this.imgD = imgD;
    }

    public Image getImgUP() {
        return imgUP;
    }

    public void setImgUP(Image imgUP) {
        this.imgUP = imgUP;
    }

    public String getNameImageUP() {
        return nameImageUP;
    }

    public void setNameImageUP(String nameImageUP) {
        this.nameImageUP = nameImageUP;
    }

    public AbstractTank() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public AbstractTank(ActionField af, Field bf) {
        this(bf, af, 128, 512, Direction.UP);
    }

    public AbstractTank(Field bf, ActionField af, int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.af = af;
        this.bf = bf;
    }

    public void turn(Direction direction) throws Exception {
        this.direction = direction;
        af.processTurn(this);
    }

    public void move() throws Exception {
        af.processMove(this);
        fire();
    }

    public void fire() throws Exception {
        Bullet bullet = new Bullet((x + 25), (y + 25), direction, power);
        af.processFire(bullet);

    }


    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

//    public void moveRandom() throws Exception { // ---------------------------------
//        while (true) {
//            int random = Generation.gen(1, 4);
//            if (random == 1) {
//                this.direction = Direction.UP;
//            } else if (random == 2) {
//                this.direction = Direction.DOWN;
//            } else if (random == 3) {
//                this.direction = Direction.LEFT;
//            } else {
//                this.direction = Direction.RIGHT;
//            }
//            if (cf.controlTank(bf, this)) {
//                move();
//            }
//        }
//    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void moveRandomWoll() throws Exception { // ---------------------------------
        while (true) {
            int random = Generation.gen(1, 4);
            if (random == 1) {
                this.direction = Direction.UP;
            } else if (random == 2) {
                this.direction = Direction.DOWN;
            } else if (random == 3) {
                this.direction = Direction.LEFT;
            } else {
                this.direction = Direction.RIGHT;
            }
            if (ControlField.controlTank(bf, this)) {
                if (ControlField.controlWoll(bf, this)) {
                    move();
                }
            }
        }
    }

    public String searchEagle() {
        String coordEagle = "";
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (bf.scanQuadrant(x, y).getArmor() > 4) {
                    coordEagle = x + "_" + y;
                }
            }
        }

        return coordEagle;
    }

    public void destroyEagle() throws Exception{
        int x=0;
        int y=0;
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                if (bf.scanQuadrant(a, b).getArmor() > 4) {
                    y = a;
                    x = b;
                }
            }
        }
        moveToQuadrantFire(x+1, y+1);
        while (bf.scanQuadrant(x, y).getArmor() != 0){
            fire();
        }
    }

    public void moveRandomWollFire() throws Exception { // ---------------------------------
        while (true) {
            int random = Generation.gen(1, 4);
            if (random == 1) {
                this.direction = Direction.UP;
            } else if (random == 2) {
                this.direction = Direction.DOWN;
            } else if (random == 3) {
                this.direction = Direction.LEFT;
            } else {
                this.direction = Direction.RIGHT;
            }
            if (ControlField.controlTank(bf, this)) {
                if (ControlField.controlWoll(bf, this) == false) {
                    fire();
                    //System.out.println("bumc");
                }
                move();

            }
        }
    }

    public void moveToQuadrant(int v, int h) throws Exception {
        String newQadrant = af.getQuadrantXY(v, h);
        int separator = newQadrant.indexOf("_");
        int goalY = Integer.parseInt(newQadrant.substring(0, separator));
        int goalX = Integer.parseInt(newQadrant.substring(separator + 1));
        if (x < goalX) {
            while (x < goalX) {
                this.direction = Direction.RIGHT;
                move();
            }
        } else {
            while (x > goalX) {
                this.direction = Direction.LEFT;
                move();
            }
        }

        if (y < goalY) {
            while (y < goalY) {
                this.direction = Direction.DOWN;
                move();
            }
        } else {
            while (y > goalY) {
                this.direction = Direction.UP;
                move();
            }
        }
    }

    public void moveToQuadrantFire(int v, int h) throws Exception {
        String newQadrant = af.getQuadrantXY(v, h);
        int separator = newQadrant.indexOf("_");
        int goalY = Integer.parseInt(newQadrant.substring(0, separator));
        int goalX = Integer.parseInt(newQadrant.substring(separator + 1));
        if (x < goalX) {
            while (x < goalX) {
                this.direction = Direction.RIGHT;
                move();

            }
        } else {
            while (x > goalX) {
                this.direction = Direction.LEFT;
                move();

            }
        }

        if (y < goalY) {
            while (y < goalY) {
                this.direction = Direction.DOWN;
                move();

            }
        } else {
            while (y > goalY) {
                this.direction = Direction.UP;
                move();

            }

        }
    }

    public int getX() {
        return x;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public boolean destroy(int a) {
        setArmor(getArmor() - 1);
        if (getArmor() == 0) {
            setY(-100);
            setX(-100);
            return true;

        }
        return false;
    }

    @Override
    public boolean destroy() {
        return false;
    }
}
