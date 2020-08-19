import processing.core.PImage;

import java.util.List;

public class Quake extends AnimateEntity{
    public Quake(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    protected int scheduleActionsHelp() {
        return Factory.QUAKE_ANIMATION_REPEAT_COUNT;
    }

    //7
    protected void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }
}