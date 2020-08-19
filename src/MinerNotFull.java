import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerNotFull extends AbstractMiner{

    public MinerNotFull(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int resourceCount,
            int actionPeriod,
            int animationPeriod
    )
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }


    //4
    protected void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget =
                world.findNearest(this.getPosition(), Ore.class);

        if (!notFullTarget.isPresent() || !this.move( world,
                notFullTarget.get(),// I made the mistake of changing to: (MinerNotFull) notFullTarget.get
                scheduler)
                || !transform( world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,  Factory.createActivityAction( this, world, imageStore),
                    this.getActionPeriod());
        }
    }

    protected boolean moveHelp(WorldModel world, Entity target, EventScheduler scheduler){
        this.incrementResourceCount(1); // setResourceCount increments resource count by 1.
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
        return true;
    }



}
