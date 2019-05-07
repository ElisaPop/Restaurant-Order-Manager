package dl;

import bll.composite.MenuItem;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class RestaurantSerializator {

    private String fileName;
    private File file;


    public RestaurantSerializator(String fileName)
    {
        this.fileName = fileName;
        this.file = new File(fileName);
        try {
            this.file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMenuItem(HashSet<MenuItem> itemToAdd)
    {
        dl.FileWriter newFileWriter = new dl.FileWriter(this.fileName);
        newFileWriter.writeMenuItem(itemToAdd);
    }

    public HashSet<MenuItem> getMenuItems()
    {
        dl.FileWriter newFileWriter = new dl.FileWriter(this.fileName);
        HashSet<MenuItem> newSet;
        newSet = (HashSet<MenuItem>) newFileWriter.readMenuItem();
        return newSet;
    }

}
