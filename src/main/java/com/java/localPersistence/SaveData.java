package com.java.localPersistence;

import com.java.account.AccountMessage;
import com.java.unit.BasicUnit;

import java.io.*;

/**
 * @author ¡Ù¡µ«ßƒÍ
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
