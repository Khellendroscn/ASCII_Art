package net.khe.asciiart;

import net.khe.util.Generator;
import net.khe.util.RandomGenerator;
import static net.khe.util.Print.*;
/**
 * Created by hyc on 2016/10/24.
 */
public class PixToAscii {
    public static char[] charSet =
            "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ".toCharArray();
    public static int whiteLevel = 0xFF-15;
    public static int blackLevel = 15;
    private static final int darkLevelUnit =
            (whiteLevel-blackLevel)/charSet.length+1;
    public static int darkLevel(int red,int green,int blue){
        return (int)(red+green+blue)/3;
    }
    public static Character toCharacter(int red,int green,int blue){
        int level = darkLevel(red,green,blue);
        if(level<=blackLevel) return '$';
        if(level>=whiteLevel) return ' ';
        int index = (level-blackLevel)/darkLevelUnit;
        return charSet[index];
    }
    public static Character toCharacter(RGB rgb){
        return toCharacter(rgb.getRGB()[0],rgb.getRGB()[1],rgb.getRGB()[2]);
    }
    public static void main(String[] args) {
        Generator<Integer> generator = new RandomGenerator.Integer(225);
        for(int i=0;i<20;++i){
            int red = generator.next();
            int green = generator.next();
            int blue = generator.next();
            println("RGB: "+red+" , "+green+" , "+blue);
            println("DarkLevel: "+darkLevel(red,green,blue));
            println("Char: "+toCharacter(red,green,blue));
            println("--------------------");
        }
    }
}
