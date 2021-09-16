package Screen;

public class Screen {
    protected int width;
    protected int height;
    protected boolean isRunning = false;

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void run() {
        this.isRunning = true;
    }

    public void stopRunning() {
        this.isRunning = false;
    }

}
