import java.util.*;

public final class EventScheduler
{
    private PriorityQueue<Event> eventQueue;
    private Map<Entity, List<Event>> pendingEvents;
    private double timeScale;

    public EventScheduler(double timeScale) {
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.pendingEvents = new HashMap<>();
        this.timeScale = timeScale;
    }

    //getters
    public PriorityQueue<Event> getEventQueue(){return eventQueue;}
    public Map<Entity, List<Event>> getPendingEvents(){return pendingEvents;}
    public double getTimeScale(){ return timeScale;}
    // Functions below this are all added functions from the functions from the file.
    //1


    public void scheduleEvent(
            Entity entity,
            Action action,
            long afterPeriod)
    {
        long time = System.currentTimeMillis() + (long)(afterPeriod
                * this.timeScale);
        Event event = new Event(action, time, entity);

        this.eventQueue.add(event);

        // update list of pending events for the given entity
        List<Event> pending = this.pendingEvents.getOrDefault(entity,
                                                                   new LinkedList<>());
        pending.add(event);
        this.pendingEvents.put(entity, pending);
    }

    public void scheduleActions(EntityActive entity,
                                WorldModel world, ImageStore imageStore) {
        this.scheduleEvent(entity, new Activity(entity, world, imageStore), entity.getActionPeriod());
        if (entity instanceof AnimateEntity)
            this.scheduleEvent(entity, new Animation((AnimateEntity) entity, world, imageStore,0), ((AnimateEntity) entity).getAnimationPeriod());

    }


    //2
    public void unscheduleAllEvents(Entity entity)
    {
        List<Event> pending = this.pendingEvents.remove(entity);

        if (pending != null) {
            for (Event event : pending) {
                this.eventQueue.remove(event);
            }
        }
    }

    //3
    private void removePendingEvent(Event event)
    {
        List<Event> pending = this.pendingEvents.get(event.getEntity());

        if (pending != null) {
            pending.remove(event);
        }
    }

    //4
    public void updateOnTime(long time) {
        while (!this.eventQueue.isEmpty()
                && this.eventQueue.peek().getTime() < time) {
            Event next = this.eventQueue.poll();

            this.removePendingEvent(next);

            next.getAction().executeAction(this);
        }
    }

}
