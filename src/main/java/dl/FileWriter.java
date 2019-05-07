package dl;

import bll.composite.MenuItem;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;

class FileWriter {

    private String fileName;

    FileWriter(String fileName)
    {
        this.fileName = fileName;
    }

    void writeMenuItem(HashSet<MenuItem> e)
    {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in" + this.fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    Collection<MenuItem> readMenuItem()
    {
        HashSet<MenuItem> returnedMenu = new HashSet<>();
        HashSet<MenuItem> e;
        try {
            FileInputStream fileIn = new FileInputStream(this.fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while(true)
            {
                try{
                    e = (HashSet<MenuItem>) in.readObject();
                    returnedMenu = e;
                } catch (IOException ex){
                    break;
                }
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("MenuItem class not found");
            c.printStackTrace();
        }

        return returnedMenu;
    }


}
