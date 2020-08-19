import processing.core.PImage;

import java.util.List;

public abstract class AbstractMiner extends AbstractMove{
    private int resourceLimit;
    private int resourceCount;
    public AbstractMiner(String id, Point position, List<PImage> images,int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceCount = resourceCount;
        this.resourceLimit = resourceLimit;
    }

    protected int getResourceCount() { return resourceCount; }
    protected void incrementResourceCount(int i) {resourceCount += i;}
    protected int getResourceLimit() { return resourceLimit; }
    protected boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (this.getClass().equals(MinerNotFull.class)) {
            if (getResourceCount() >= getResourceLimit()) {
                MinerFull miner = Factory.createMinerFull(getId(), getResourceLimit(),
                        getPosition(), getActionPeriod(), getAnimationPeriod(),
                        getImages());
                world.removeEntity(this);
                scheduler.unscheduleAllEvents(this);
                world.addEntity(miner);
                miner.scheduleActions(scheduler, world, imageStore);
                return true;
            }
        }
        else {
            MinerNotFull miner = Factory.createMinerNotFull(getId(), getResourceLimit(), getPosition(),
                    getActionPeriod(), getAnimationPeriod(),
                    getImages());
            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(miner);
            miner.scheduleActions(scheduler, world, imageStore);
        }
        return false;
    }

    protected Point nextPosition(WorldModel world, Point destPos) {
        int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
        Point newPos = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
            newPos = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = this.getPosition();
            }
        }

        return newPos;
    }
}
