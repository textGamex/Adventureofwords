package com.java.localPersistence;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author ����ǧ��
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
     * ��ȡjson�ļ�������json����.
     *
     * @param path Ҫ��ȡ��json�ļ���·��
     * @return ���ļ���json����
     * @throws FileNotFoundException ���{@code path}������
     * @throws NullPointerException ���{@code path}Ϊnull
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
            var e2 = new FileNotFoundException(path + " ������");
            e2.initCause(e);
            throw e2;
        }
        return JSONObject.parseObject(line);
    }
}
