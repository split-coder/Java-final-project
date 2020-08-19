import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerFull extends AbstractMiner {

    public MinerFull(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int resourceCount,
            int actionPeriod,
            int animationPeriod)
    {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }
    //3
    protected void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> fullTarget =
                world.findNearest(this.getPosition(), Blacksmith.class);

        if (fullTarget.isPresent() && this.move(world,
                fullTarget.get(), scheduler))
        {
            transform(world, scheduler, imageStore);
        }
        else {
            scheduler.scheduleEvent(this,
                     Factory.createActivityAction( this, world, imageStore),
                    this.getActionPeriod());
        }
    }

    protected boolean moveHelp(WorldModel world, Entity target, EventScheduler scheduler){
        return true;
    }


}
