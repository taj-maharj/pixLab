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

    public static boolean canHide(Picture src, Picture secret){
        return (src.getWidth() == secret.getWidth()) && (src.getHeight() == secret.getHeight());
    }

    public static Picture hidePicture(Picture src, Picture secret){
        //Lowest 2 bits of src should be switched to highest 2 bits of secret
        Pixel[][] secretP = secret.getPixels2D();
        Pixel[][] srcP = src.getPixels2D();
        for (int r = 0; r<src.getHeight(); r++) {
            for (int c = 0; c<src.getWidth(); c++) {
                int red = srcP[r][c].getRed();
                int green = srcP[r][c].getGreen();
                int blue = srcP[r][c].getBlue();

                int Sred = secretP[r][c].getRed();
                int Sgreen = secretP[r][c].getGreen();
                int Sblue = secretP[r][c].getBlue();


                Pixel currPixel = srcP[r][c];
                currPixel.setRed(red/4*4+Sred/64);
                currPixel.setBlue(blue/4*4+Sblue/64);
                currPixel.setGreen(green/4*4+Sgreen/64);
            }
        }
        return src;
    }

    public static Picture hidePicture(Picture src, Picture secret, int startRow, int startColumn){
        Pixel[][] secretP = secret.getPixels2D();
        Pixel[][] srcP = src.getPixels2D();
        int secretRow = 0;
        for (int r = startRow; r<startRow+secret.getHeight(); r++) {
            int secretCol = 0;
            for (int c = startColumn; c<startColumn+secret.getWidth(); c++) {
                int red = srcP[r][c].getRed();
                int green = srcP[r][c].getGreen();
                int blue = srcP[r][c].getBlue();


                int Sred = secretP[secretRow][secretCol].getRed();
                int Sgreen = secretP[secretRow][secretCol].getGreen();
                int Sblue = secretP[secretRow][secretCol].getBlue();


                Pixel currPixel = srcP[r][c];
                currPixel.setRed(red/4*4+Sred/64);
                currPixel.setBlue(blue/4*4+Sblue/64);
                currPixel.setGreen(green/4*4+Sgreen/64);
                secretCol++;
            }
            secretRow++;
        }
        return src;
    }

    public static void main(String[] args) {
        // Picture beach = new Picture("beach.jpg");
        // Picture blueM = new Picture("blue-mark.jpg");
        // beach.explore();
        // Picture copy = testClearLow(beach);
        // copy.explore();

        // Picture beach2 = new Picture ("beach.jpg");
        // beach2.explore();
        // Picture copy2 = testSetLow(beach2, Color.PINK);
        // copy2.explore();
        // Picture copy3 = revealPicture(copy2);
        // copy3.explore();

        // if(canHide(beach,blueM)){
        //     beach.explore();
        //     blueM.explore();
            
        //     Picture secret = hidePicture(beach, blueM);

        //     secret.explore();

        //     secret = revealPicture(secret);
            
        //     secret.explore();
        // }

        Picture beach = new Picture("beach.jpg");
        Picture robot = new Picture("robot.jpg");
        Picture flower = new Picture("flower1.jpg");
        // beach.explore();

        Picture hidden = hidePicture(beach, robot, 65, 208);
        Picture otherHidden = hidePicture(hidden, flower, 28, 110);

        // otherHidden.explore();

        Picture unhidden = revealPicture(otherHidden);
        unhidden.explore();
    }
}
