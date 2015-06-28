import ObjectBF.Destroy;

/**
 * Created by panasyuk on 16.06.2015.
 */
public abstract class AbstractTank implements Destroy {
    private int x;
    private int y;
    private Direction direction;
    protected int speed = 10;
    private ActionField af;
    protected Field bf;
    private ControlField cf;
    private int armor;
    protected Bullet bullet;
    protected int power;


    public AbstractTank() {

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

    public void moveRandom() throws Exception { // ---------------------------------
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
            if (cf.controlTank(bf, this)) {
                move();
            }
        }
    }

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
                //this.direction.setId(4);
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
        //System.out.println(newQadrant);
    }
    public void moveToQuadrantFire(int v, int h) throws Exception {
        String newQadrant = af.getQuadrantXY(v, h);
        int separator = newQadrant.indexOf("_");
        int goalY = Integer.parseInt(newQadrant.substring(0, separator));
        int goalX = Integer.parseInt(newQadrant.substring(separator + 1));
        if (x < goalX) {
            while (x < goalX) {
                this.direction = Direction.RIGHT;
                //this.direction.setId(4);
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
        //System.out.println(newQadrant);
//        fire();
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

//    public void tankDestroy() {
//        System.out.println(bullet.getX() + " *** " + bullet.getY());
//        System.out.println("popal!!");
//        System.out.println("armor = " + getArmor());
//        setArmor(getArmor() - 1);
//        if (getArmor() == 0) {
//            System.out.println("armor = " + getArmor());
//            setY(-100);
//            setX(-100);
//        }
//
//    }

    @Override
    public boolean destroy() {
        setArmor(getArmor() - 1);
        if (getArmor() == 0) {
            setY(-100);
            setX(-100);
            return true;

        }
        return false;
    }

    @Override
    public boolean destroy(int a) {
        return false;
    }
}
