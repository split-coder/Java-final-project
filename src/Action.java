public abstract class Action
{
    protected WorldModel world;
    protected ImageStore imageStore;
    protected int repeatCount;

    public Action(WorldModel world, ImageStore imageStore, int repeatCount){
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    abstract protected void executeAction(EventScheduler scheduler);
    protected WorldModel getWorld(){ return world; }
    protected ImageStore getImageStore(){ return imageStore;}

}
