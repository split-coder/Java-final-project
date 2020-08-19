public class Activity extends Action {


    private EntityActive entity;
    public Activity(
            EntityActive entity,
            WorldModel world,
            ImageStore imageStore)
    {
        super(world, imageStore, 0);
        this.entity = entity;
    }
    //getters
    protected EntityActive getEntity(){ return entity; }

    // Functions below this are all added functions from the functions from the file.
    // deal with switch statement after checking with entity
    protected void executeAction(EventScheduler scheduler)
    {
       this.getEntity().executeActivity(this.world, this.imageStore, scheduler);
    }

}


