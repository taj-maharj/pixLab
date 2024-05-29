import java.awt.Color;

public class Stenanography {
    public static void clearLow(Pixel p){
        p.setColor(new Color(p.getRed()*4, p.getGreen()*4, p.getBlue()*4));
    }

    public static void testClearLow(Picture pic){
        for( Pixel[] p: pic.getPixels2D()){
            for(Pixel pix: p){
                clearLow(pix);
            }
        }
    }

    public static void main(String[] args) {
        Picture 
    }
}
