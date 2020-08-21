import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class AbstractMove extends AnimateEntity {


    public AbstractMove(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }
//
    protected abstract Point nextPosition( WorldModel world, Point destPos);

    protected boolean move(WorldModel world, Entity target, EventScheduler scheduler){
        if (Point.adjacent(this.getPosition(), target.getPosition())){
            return moveHelp(world, target, scheduler);
        }
        else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    protected abstract boolean moveHelp(WorldModel world, Entity target, EventScheduler scheduler);

    protected int scheduleActionsHelp(){ return 0;}
}
