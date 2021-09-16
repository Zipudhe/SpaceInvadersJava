package Entity;

public class Bullet extends InGameObject {

    protected int speed;
    public int damage;

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public Bullet(int initialPosX, int initialPosY, int width, int height, int initialLife) {
        super(initialPosX, initialPosY, width, height, initialLife);
        //TODO Auto-generated constructor stub
    }


    
}
