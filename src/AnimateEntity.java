import processing.core.PImage;

import java.util.List;

public abstract class AnimateEntity extends EntityActive
{
//    private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;
    private final int animationPeriod;
    public AnimateEntity(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod);
        this.animationPeriod = animationPeriod;
    }

    protected void nextImage() {

        int in= (this.getImageIndex() + 1) % this.getImages().size();
        this.setImageIndex(in);
    }
    protected int getAnimationPeriod(){return animationPeriod;}
    protected void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
        scheduler.scheduleEvent(this, Factory.createAnimationAction(this,
                scheduleActionsHelp()),
                this.getAnimationPeriod());
    }
    protected abstract int scheduleActionsHelp();
}