package net.khe.asciiart;

import net.khe.util.FilePrinter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by hyc on 2016/10/24.
 */
public class AsciiArt implements Iterable<ArrayList<Character>>{
    //
    private ArrayList<ArrayList<Character>> charGraphic = new ArrayList<>();
    ImgScanner scanner;
    public AsciiArt(ImgScanner scanner){
        this.scanner = scanner;
        makeList();
    }
    private void makeList(){
        for(ArrayList<RGB> childList:scanner){
            ArrayList<Character> tempList = new ArrayList<>();
            for(RGB rgb:childList){
                tempList.add(PixToAscii.toCharacter(rgb));
            }
            charGraphic.add(tempList);
        }
    }
    @Override
    public Iterator<ArrayList<Character>> iterator() {
        return charGraphic.iterator();
    }

    public static void main(String[] args) {
        ImgScanner scanner = new ImgScanner("images/img3.jpg",15,17);
        AsciiArt asciiArt = new AsciiArt(scanner);
        File out = new File("text/img4_ascii.txt");
        FilePrinter printer;
        try{
            printer = new FilePrinter(out);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        for(ArrayList<Character> childList:asciiArt){
            for(Character c:childList){
                printer.print(c);
            }
            printer.println();
        }
        printer.close();
    }
}
