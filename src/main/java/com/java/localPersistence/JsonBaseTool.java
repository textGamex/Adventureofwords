package com.java.localPersistence;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author 留恋千年
 * @version 1.1.0
 * @since 2021-6-6
 */
public class JsonBaseTool
{
    private JsonBaseTool()
    {
        throw new AssertionError();
    }
    /**
     * 读取json文件并返回json对象.
     *
     * @param path 要读取的json文件的路径
     * @return 此文件的json对象
     * @throws FileNotFoundException 如果{@code path}不存在
     * @throws NullPointerException 如果{@code path}为null
     */
    public static JSONObject loadJsonFile(final File path) throws FileNotFoundException
    {
        Objects.requireNonNull(path);
        String line;
        try (var in = new Scanner(path, StandardCharsets.UTF_8))
        {
            line = in.nextLine();
        }
        catch (IOException e)
        {
            var e2 = new FileNotFoundException(path + " 不存在");
            e2.initCause(e);
            throw e2;
        }
        return JSONObject.parseObject(line);
    }
}
