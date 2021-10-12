package Entity;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ship extends InGameObject {

    public String name;
    protected int speed;
    public int damage;
    private final int shotInverval = 50; // tempo em milisegundos
    private Bullet bullet;    
    private int time = 0;

    public Ship()  {
        super(565, 630, 35, 35, 100);
        this.speed = 5;
        try {
            this.setDrawing(ImageIO.read(new File("src/assets/mainShip.png")));
        } catch(IOException e) {    
            System.out.println("Image not found in" + this.getClass());
        }
    }

    public void moveRight() {
        this.posX += this.speed;
    }

    public void moveLeft() {
        this.posX -= this.speed;
    }

    public void moveForward() {
        this.posY -= this.speed;
    }
    
    public void moveBackward() {
        this.posY += this.speed;
    }

    public boolean canShoot() {
        if(this.time >= this.shotInverval) {
            this.time = 0;
            return true;
        } else {
            return false;
        }
    }

    public Bullet shoot() {
        bullet = new Bullet(this.posX + 3 ,
                    this.posY - (this.height - 14), 32, 32, 0);
        return bullet;
    }

    @Override
    public void update() {
        this.time += 1;
    }
}
