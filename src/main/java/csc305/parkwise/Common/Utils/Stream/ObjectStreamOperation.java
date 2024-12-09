package csc305.parkwise.Common.Utils.Stream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectStreamOperation {
    public ObjectStreamOperation() {
    }

    public static ObjectOutputStream getObjectOutputStream(String filePath, boolean update) throws IOException {
        File userObjectsFile = new File(filePath);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        if(update) {
            fos = new FileOutputStream(userObjectsFile, false);
            oos = new ObjectOutputStream(fos);
            return oos;
        }

        if(userObjectsFile.exists()) {
            fos = new FileOutputStream(userObjectsFile, true);
            oos = new AppendableObjectOutputStream(fos);
        } else {
            fos = new FileOutputStream(userObjectsFile);
            oos = new ObjectOutputStream(fos);
        }

        return oos;
    }

    public static ObjectInputStream getObjectInputStream(String filePath) throws IOException {
        File userObjectsFile = new File(filePath);

        FileInputStream fos = null;
        ObjectInputStream ois = null;

        if(userObjectsFile.exists()) {
            fos = new FileInputStream(userObjectsFile);
            ois = new ObjectInputStream(fos);
        }

        return ois;
    }

    public static List<Object> getObjectsFromFile(String filePath) throws IOException {
        List<Object> objects = new ArrayList<>();
        ObjectInputStream ois = null;

        try{
            ois = getObjectInputStream(filePath);

            while (true) {
                try {
                    Object obj = ois.readObject();
                    objects.add(obj);
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return objects;
    }
}

