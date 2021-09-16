package Entity;

public class Ship extends InGameObject {

    public String name;
    protected int speed;
    public int damage;
    private int shotInverval;

    public void moveRight() {
        this.posX += this.speed;
    }

    public void moveLeft() {
        this.posX -= this.speed;
    }

    public void moveForward() {
        this.posY =+ this.speed;
    }
    
    public void moveBackward() {
        this.posY -= this.speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void shoot() {
        // TODO vincular isso com a entidade de projéteis
        System.out.println(this.shotInverval);
    }
    

    
    
    public Ship(int initialPosX, int initialPosY, int width, int height, int initialLife) {
        super(initialPosX, initialPosY, width, height, initialLife);
        //TODO Auto-generated constructor stub
    }
    
}
