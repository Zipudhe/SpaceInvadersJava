package Entity;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bullet extends InGameObject {

    protected int speed;
    public int damage;
    
    public Bullet(int initialPosX, int initialPosY, int width, int height, int initialLife) {
        super(initialPosX, initialPosY, width, height, initialLife);
        try {
            this.setDrawing(ImageIO.read(new File("src/assets/shot.png")));
        } catch(IOException e) {
            System.out.println("Image not found in " + this.getClass());
        }
        this.speed = 10;
        this.damage = 50;
    }
    
    public void update() {
        this.posY -= this.speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPos() {
        this.posY -= this.speed;
    }

    public Boolean handleColision(EnemyShip enemy) {
        int bulletPos = this.getPosX() + this.getWidth();
        int enemyPos = enemy.getPosX() + enemy.getWidth();

        if(this.getPosY() - this.height <= enemy.getPosY()) {
            if(
                bulletPos>= enemy.getPosX() && 
                bulletPos <= enemyPos
                ) {
                enemy.dealDamage(damage);
                return true;
            }
        }
        return false;
    }

    public int getWidth() {
        return this.witdh;
    }    
}
