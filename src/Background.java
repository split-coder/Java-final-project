import java.util.List;

import processing.core.PImage;

public class Background
{
    private String id;
    private List<PImage> images;
    private int imageIndex;

    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }
    // getters
    public String getId(){ return id; }
    public List<PImage> getImages(){return images;}
    public int getImageIndex(){return imageIndex;}

    //Functions Added from the file
    //1
    public PImage getCurrentImage() {
        return (this.images.get(this.imageIndex));

    }

}
