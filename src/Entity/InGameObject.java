package Entity;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class InGameObject implements Update {
    protected int posX;
    protected int posY;
    protected int witdh;
    protected int height;
    public int life;
    public boolean isDestroyed = false;

    public int time = 0;

    private BufferedImage drawing;

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int pos) {
        this.posX += pos;
    }

    public void setPosY(int pos) {
        this.posY += pos;
    }

    public int getWidth() {
        return this.witdh;
    }

    public int getHeight() {
        return this.witdh;
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void dealDamage(int damage) {
        this.life -= damage;
        if(this.life <= 0) {
            this.setIsDestroyed(true);
        }
    }

    public void setIsDestroyed(boolean state) {
        this.isDestroyed = state;
    }

    public boolean checkIsDestroyed() {
        return this.isDestroyed;
    }

    public void setDrawing(BufferedImage image) {
        this.drawing = image;
    }

    public void draw(Graphics2D g) {
        this.time += 1;
        g.drawImage(
            drawing, 
            this.posX, 
            this.posY, 
            this.posX + this.witdh, 
            this.posY + this.height, 
            0, 
            0, 
            drawing.getWidth(), 
            drawing.getHeight(),
            null
            );
    }


    public  InGameObject(int initialPosX, int initialPosY, int width, int height, int initialLife) {
        this.posX = initialPosX;
        this.posY = initialPosY;
        this.life = initialLife;
        this.height = height;
        this.witdh = width;
        this.isDestroyed = false;
    }

    public void update() {
        // TODO Auto-generated method stub
        
    }
}