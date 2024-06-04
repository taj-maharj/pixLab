import java.awt.Color;

public class Stenanography {
    public static void clearLow(Pixel p){
        p.setColor(new Color(p.getRed()/4*4, p.getGreen()/4*4, p.getBlue()/4*4));
    }

    public static Picture testClearLow(Picture picture){
        Picture pic = picture;
        for( int i = 0; i<pic.getWidth(); i++){
            for(int j = 0; j<pic.getHeight(); j++){
                clearLow(pic.getPixel(i, j));
            }
        }
        return pic;
    }

    public static void setLow(Pixel p, Color c){
        int r = p.getRed();
        int g = p.getGreen();
        int b = p.getBlue();
        r = r/4*4+c.getRed()/64; 
        g = g/4*4+c.getGreen()/64; 
        b = b/4*4+c.getBlue()/64;

        p.setColor(new Color(r, g, b));
    }

    public static Picture testSetLow(Picture p, Color c){
        Picture pic = p;
        for( int i = 0; i<pic.getWidth(); i++){
            for(int j = 0; j<pic.getHeight(); j++){
                setLow(pic.getPixel(i, j), c);
            }
        }
        return pic;
    }

    public static Picture revealPicture(Picture hidden){
        Picture copy = new Picture(hidden);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D();
        for(int r = 0; r<pixels.length; r++){
            for(int c = 0; c<pixels[0].length; c++){
                Color col = source[r][c].getColor();
                int red = col.getRed()%4*64;
                int green = col.getGreen()%4*64;
                int blue = col.getBlue()%4*64;
                pixels[r][c].setColor(new Color(red, green, blue));
                // copy.getPixel(c, r).setColor(new Color(red, green, blue));
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        // Picture beach = new Picture("beach.jpg");
        // beach.explore();
        // Picture copy = testClearLow(beach);
        // copy.explore();

        Picture beach2 = new Picture ("beach.jpg");
        beach2.explore();
        Picture copy2 = testSetLow(beach2, Color.PINK);
        copy2.explore();
        Picture copy3 = revealPicture(copy2);
        copy3.explore();
    }
}
