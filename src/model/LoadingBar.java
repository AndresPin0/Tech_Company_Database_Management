package model;

public class LoadingBar {

    public final double LOADED_WIDTH = 500.0;
    private double barWidth;
    private boolean active;

    public LoadingBar() {
        barWidth = 0.0;
        active = true;
    }

    public void doProgress() {
        if (barWidth >= LOADED_WIDTH) active = false;
        else barWidth++;
    }

    public double getBarWidth() {
        return barWidth;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
