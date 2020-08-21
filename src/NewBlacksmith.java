import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class NewBlacksmith extends AbstractMove {
        private static final String JEFF_KEY = "JEFF";


        public NewBlacksmith(
                String id,
                Point position,
                List<PImage> images,
                int actionPeriod,
                int animationPeriod)
        {
            super(id,position, images, actionPeriod, animationPeriod);


        }

        @Override
        protected Point nextPosition(WorldModel world, Point destPos) {
            int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
            Point newPos = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

            Optional<Entity> occupant = world.getOccupant(newPos);

            if (horiz == 0 || (occupant.isPresent() && !(occupant.get().getClass()
                    == Ore.class)))
            {
                int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
                newPos = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);
                occupant = world.getOccupant(newPos);

                if (vert == 0 || (occupant.isPresent() && !(occupant.get().getClass()
                        == Ore.class)))
                {
                    newPos = this.getPosition();
                }
            }
            return newPos;
        }


        //6
        protected void executeActivity(
                WorldModel world,
                ImageStore imageStore,
                EventScheduler scheduler)
        {
            Optional<Entity> obsta = world.findNearest(getPosition(), OreBlob.class);
            long nextPeriod = this.getActionPeriod();

            if (obsta.isPresent()) {
                Point tgtPos = obsta.get().getPosition();

                if (this.move(world, obsta.get(), scheduler)) {

                }
            }



            scheduler.scheduleEvent(this,
                    Factory.createActivityAction( this, world, imageStore),
                    nextPeriod);
        }

        //16
        protected boolean moveHelp(
                WorldModel world,
                Entity target,
                EventScheduler scheduler)
        {

            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;

        }
}
