package net.khe.asciiart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hyc on 2016/10/24.
 */
class RGB{
    private int red;
    private int green;
    private int blue;
    public RGB(int red,int green,int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public int[] getRGB(){
        return new int[]{red,green,blue};
    }
    public void add(RGB rgb){
        red += rgb.red;
        green += rgb.green;
        blue += rgb.blue;
    }
    public void divide(int num){
        red/=num;
        green/=num;
        blue/=num;
    }
}
public class ImgScanner implements Iterable<ArrayList<RGB>>{
    private BufferedImage image;
    private ArrayList<ArrayList<RGB>> RGB_List = new ArrayList<>();
    private int xUnit = 1;
    private int yUnit = 1;
    public ImgScanner(String dir){
        try{
            image = ImageIO.read(new File(dir));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        makeList();
    }
    public ImgScanner(String dir, int xUnit, int yUnit){
        try{
            image = ImageIO.read(new File(dir));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        makeList();
    }
    public void setxUnit(int xUnit){this.xUnit=xUnit;}
    public void setyUnit(int yUnit){this.yUnit=yUnit;}
    public ArrayList<ArrayList<RGB>> getRGB_List(){return RGB_List;}
    private void makeList(){
        int width = image.getWidth();
        int height = image.getHeight();
        for(int i=0;i<height;i+=yUnit){
            ArrayList<RGB> tempList = new ArrayList<>();
            for(int j=0;j<width;j+=xUnit){
                RGB tempRGB = new RGB(0,0,0);
                for(int y=i;y<i+yUnit&&y<height;++y){
                    for(int x=j;x<j+xUnit&&x<width;++x){
                        int pix = image.getRGB(x,y);
                        int red = (pix&0xFF0000)>>16;
                        int green = (pix&0xFF00)>>8;
                        int blue = (pix&0xFF);
                        tempRGB.add(new RGB(red,green,blue));
                    }
                }
                tempRGB.divide(xUnit*yUnit);
                tempList.add(tempRGB);
            }
            RGB_List.add(tempList);
        }
    }
    @Override
    public Iterator<ArrayList<RGB>> iterator() {
        return RGB_List.iterator();
    }
}
