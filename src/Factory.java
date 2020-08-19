import processing.core.PImage;

import java.util.List;


public class Factory {

    public static final String QUAKE_ID = "quake";
    public static final int QUAKE_ACTION_PERIOD = 1100;
    public static final int QUAKE_ANIMATION_PERIOD = 100;
    public static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;
    public static final String QUAKE_KEY = "quake";

    public static Animation createAnimationAction(Entity entity, int repeatCount) {
        return new Animation( entity, null, null, repeatCount);
    }
    public static Activity createActivityAction(
            EntityActive entity, WorldModel world, ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore);
    }
    public static Blacksmith createBlacksmith(
            String id, Point position, List<PImage> images)
    {

        return new Blacksmith(id, position, images);
    }

    public static MinerFull createMinerFull(
            String id,
            int resourceLimit,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new MinerFull(id, position, images,
                resourceLimit, resourceLimit, actionPeriod,
                animationPeriod);
    }

    public static MinerNotFull createMinerNotFull(
            String id,
            int resourceLimit,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new MinerNotFull(id, position, images,
                resourceLimit,0, actionPeriod, animationPeriod);
    }

    public static Obstacle createObstacle(
            String id, Point position, List<PImage> images)
    {

        return new Obstacle(id, position, images);
    }
    public static Ore createOre(
            String id, Point position, int actionPeriod, List<PImage> images)
    {

        return new Ore(id, position, images, actionPeriod);
    }
    // change Entity to Blob
    public static OreBlob createOreBlob(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {

        return new OreBlob(id, position, images, actionPeriod, animationPeriod);
    }
    public static Quake createQuake(
            Point position, List<PImage> images)
    {
        return new Quake(QUAKE_ID, position, images, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
    }
    public static Vein createVein(
            String id, Point position, int actionPeriod, List<PImage> images)
    {
        return new Vein(id, position, images,
                actionPeriod);
    }
}
