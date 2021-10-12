package Entity;

import java.awt.image.BufferedImage;

public class EnemyShip extends InGameObject {

    public String name;
    protected int speed;
    public int damage;
    private Bullet bullet;


    public EnemyShip( BufferedImage image, int initialPosX, int initialPosY, int width, int height, int initialLife) {
        super(initialPosX, initialPosY, width, height, initialLife);
        this.setDrawing(image);
        this.speed = 8;   
    }

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

    public Bullet shoot() {
        bullet = new Bullet(this.posX + 3 ,
                    this.posY - (this.height - 14), 32, 32, 0);
        return bullet;
    }
    
    @Override
    public void update() {
        if(this.time > 4) {
            this.posX += 5;
            if(this.posX > 1100) {
                this.posX = 230;
                this.posY += 50;
                this.speed += 30;
            }
            this.time = 0;
        }
    }
}