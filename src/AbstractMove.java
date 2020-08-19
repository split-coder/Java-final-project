import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class AbstractMove extends AnimateEntity {


    public AbstractMove(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
    }
//
    protected abstract Point nextPosition( WorldModel world, Point destPos);
//    {
//        int horiz = Integer.signum(destPos.x - this.getPosition().x);
//        Point newPos = new Point(this.getPosition().x+ horiz,
//                this.getPosition().y);
//
//        Optional<Entity> occupant = world.getOccupant( newPos);
//
//        if (horiz == 0 ||
//                (occupant.isPresent() && !(occupant.get().getClass() == Ore.class )))
//        {
//            int vert = Integer.signum(destPos.y - this.getPosition().y);
//            newPos = new Point(this.getPosition().x, this.getPosition().y + vert);
//            occupant = world.getOccupant( newPos);
//
//            if ((vert == 0) ||
//                    (occupant.isPresent() && !(occupant.get().getClass() == Ore.class)))
//            {
//                newPos = this.getPosition();
//            }
//        }
//
//        return newPos;
//    }
    //9
//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
//        scheduler.scheduleEvent(this,
//                Factory.createActivityAction(this, world, imageStore),
//                this.getActionPeriod());
//        scheduler.scheduleEvent(this,
//                Factory.createAnimationAction(this, 0),
//                this.getAnimationPeriod());
//    }

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
