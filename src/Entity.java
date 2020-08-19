import java.util.List;


import processing.core.PImage;

public abstract class Entity{
        private String id;
        private Point position;
        private List<PImage> images;
        private int imageIndex;
        private int actionPeriod;


public Entity(String id,Point position,List<PImage> images,int actionPeriod){
        this.id=id;
        this.position=position;
        this.images=images;
        this.imageIndex=0;
        this.actionPeriod = actionPeriod;

        }
        protected Point getPosition(){return position;};

        protected String getId(){return id;}

        protected void setPosition(Point p){this.position=p;};
        protected int getImageIndex(){return imageIndex;};
        protected List<PImage> getImages(){return images;};
        protected PImage getCurrentImage(){return (this.images.get(this.imageIndex));};
        protected void setImageIndex(int integer) { imageIndex = integer; }
        protected int getActionPeriod(){return actionPeriod;}


        }
