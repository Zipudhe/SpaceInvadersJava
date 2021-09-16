package Entity;

public class InGameObject {
    protected int posX;
    protected int posY;
    protected int witdh;
    protected int height;
    public int life;
    public boolean isDestroyed = false;

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

    public void setIsDestroyed(boolean state) {
        this.isDestroyed = state;
    }

    public boolean checkIsDestroyed() {
        return this.isDestroyed;
    }


    public  InGameObject(int initialPosX, int initialPosY, int width, int height, int initialLife) {
        this.posX = initialPosX;
        this.posY = initialPosY;
        this.life = initialLife;
        this.height = height;
        this.witdh = width;
        this.isDestroyed = false;
    }
}