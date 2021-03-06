package com.java.localPersistence;

import java.io.*;

/**
 * @author ็ๆๅๅนด
 */
public class SaveData
{
    public static <T extends Serializable> void saveObjectData(T object, File file)
    {
        try (var out = new ObjectOutputStream(new FileOutputStream(file)))
        {
            out.writeObject(object);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
