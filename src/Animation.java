public class Animation extends Action{

    private Entity entity;

    public Animation(
            Entity entity,
            WorldModel world,
            ImageStore imageStore,
            int repeatCount)
    {
        super(world, imageStore, repeatCount);
        this.entity = entity;
    }
    //getters
    protected Entity getEntity(){ return entity; }
    // Functions below this are all added functions from the functions from the file.


    //3
    protected void executeAction(EventScheduler scheduler)
    {
        ((AnimateEntity)this.getEntity()).nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity,
                    Factory.createAnimationAction(this.entity, Math.max(this.repeatCount - 1, 0)),
                    ((AnimateEntity)this.entity).getAnimationPeriod());
        }
    }




}
