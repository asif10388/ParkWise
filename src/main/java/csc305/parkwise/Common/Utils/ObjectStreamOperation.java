package csc305.parkwise.Common.Utils;

import java.io.*;

public class ObjectStreamOperation {
    public ObjectStreamOperation() {
    }

    public static ObjectOutputStream getObjectOutputStream(String filePath) throws IOException {
        File userObjectsFile = new File(filePath);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

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
}
