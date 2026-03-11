public class RGBImage{

    // folder to look for image files
    private String defaultDirectory = "images";    

    // The pixels are stored in three 2D arrays.
    // These are the data structures we will use to perform 
    // the actual image manipulation.
    private int red[][];
    private int green[][];
    private int blue[][];
    
    // Helper will do the 'ugly' Java stuff (saving images to files, etc)
    private Helper myHelper;
    
    
    // Constructor, use an open file dialog to specify image
    public RGBImage() {
        myHelper = new Helper(this, defaultDirectory);
    }
    
    // Constructor, String argument specifies filename of image
    public RGBImage(String filename) {
        myHelper = new Helper(this, defaultDirectory, filename);
    }
    
    // this is called from Helper, when a new image is loaded from a file.  
    // You never need to call it.
    protected void updateArrays(int[][] red, int [][] green, int[][] blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    
    // Call this method to load a new image
    public void load() {
        myHelper.load(defaultDirectory);
    }
    
    // Call this method to save the image to the same file
    public void save() {
        myHelper.save();
    }

    // Call this method to save the image to a different file name
    public void saveAs(String filename) {
        myHelper.saveAs(filename);
    }
    
    // call this in case you closed the viewer, but want to see it again.
    public void show() {
        myHelper.show();
    }
    
    
    // This method should always be called after manipulating 
    // the pixels via the 2D arrays.  
    private void refresh() {
        myHelper.refresh(red, green, blue);
    }
    
    
    ///////////
    /////////// Image manipulation methods
    ///////////
    // Your code goes here.  
    // Don't forget to call refresh() after manipulating the red, green, and blue pixel arrays
    
    
    // Example: flips the image vertically.
    public void flipVertical() {
        int height = red.length;
        int width = red[0].length;
        
        int [][] tempR = new int[height][width];
        int [][] tempB = new int[height][width];
        int [][] tempG = new int[height][width];
        
        // Flip the red channel.
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                tempR[h][w] = red[height-h-1][w];
                tempG[h][w] = green[height-h-1][w];
                tempB[h][w] = blue[height-h-1][w];
            }
        } 
        for (int h=0;h<height;h++) {
            for (int w=0;w<width;w++) {
                red[h][w] = tempR[h][w];
                green[h][w] = tempG[h][w];
                blue[h][w] = tempB[h][w];
            }
        } 
                
        // Always do this after manipulating pixels.
        refresh();
    }
    public void flipHorizontal() {
        // TODO: reverse each row left-to-right
        int height = red.length;
        int width = red[0].length;

        int[][] tempR = new int[height][width];
        int[][] tempB = new int[height][width];
        int[][] tempG = new int[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                tempR[h][w] = red[h][width-w-1];
                tempG[h][w] = green[h][width-w-1];
                tempB[h][w] = blue[h][width-w-1];
            }
        }
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                red[h][w] = tempR[h][w];
                green[h][w] = tempG[h][w];
                blue[h][w] = tempB[h][w];
            }
        }

        refresh();
    }

    public void greyScale() {
        // TODO: set each channel value to the average of red/green/blue
        int height = red.length;
        int width = red[0].length;
        int tot = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                tot = 0;
                tot= (red[h][w] +green[h][w]+blue[h][w])/3;
                red[h][w] = tot;
                green[h][w] = tot;
                blue[h][w] = tot;
            }
        }
        refresh();
    }

    public void mirror() {
        // TODO: copy the left side of each row onto the right side
        int height = red.length;
        int width = red[0].length;

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width/2; w++) {
                red[h][w] = red[h][width-1-w];
                green[h][w] = green[h][width-1-w];
                blue[h][w] = blue[h][width-1-w];
            }
        }
        refresh();
        
    }

    public void blackAndWhite() {
        // TODO: set each pixel to 0 or 255 based on average brightness
        int height = red.length;
        int width = red[0].length;
        int tot = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                tot = 0;
                tot= (red[h][w] +green[h][w]+blue[h][w])/3;
                if(tot<127){
                    red[h][w] = 0;
                    green[h][w] = 0;
                    blue[h][w] = 0;

                }else{
                    red[h][w] = 255;
                    green[h][w] = 255;
                    blue[h][w] = 255;

                }
            }
        }

        refresh();
    }

    public void contrastStretch() {
        // TODO: scale channel values to fill range 0..255
        int height = red.length;
        int width = red[0].length;
        int rmax = 0;
        int gmax = 0;
        int bmax = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if(red[h][w]>rmax){
                    rmax=red[h][w];
                }
                if(green[h][w]>gmax){
                    gmax=green[h][w];
                }
                if(blue[h][w]>bmax){
                    bmax=blue[h][w];
                }
            }
        }
        int rmin = 1000;
        int gmin = 1000;
        int bmin = 1000;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if(red[h][w]<rmin){
                    rmin=red[h][w];
                }
                if(green[h][w]<gmin){
                    gmin=green[h][w];
                }
                if(blue[h][w]<bmin){
                    bmin=blue[h][w];
                }
            }
        }
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                red[h][w]= 255*(red[h][w]-rmin)/(rmax-rmin);
                green[h][w]= 255*(green[h][w]-gmin)/(gmax-gmin);
                blue[h][w]= 255*(blue[h][w]-bmin)/(bmax-bmin);
            }
        }


        refresh();
    }


}
