package com.java.main;

import com.alibaba.fastjson.JSONObject;
import com.java.localPersistence.JsonBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.requireNonNull;

/**
 * @author ����ǧ��
 * @version 1.0.0
 * @since 2021-6-6
 */
public class GameSetting
{
    private static final GameSetting GAME_SETTING = new GameSetting();

    public static void main(String[] args)
    {
        var a = "-".toCharArray();
        System.out.println(a[1]);
    }
    private GameSetting()
    {

    }

    /**
     *
     * @return ����һ�� {@code GameSetting}����
     */
    public static GameSetting getGameSetting()
    {
        return GAME_SETTING;
    }

    private boolean openLoadAnimation = true;
    private char separatorCharacter = '-';
    /**
     * �������ļ������ڱ���.
     */
    public void saveSetting(final File path)
    {
        requireNonNull(path);
        var jsonFile = new JSONObject();

        jsonFile.put("��ʾ���ض���", isOpenLoadAnimation());
        jsonFile.put("�ָ���", String.valueOf(getSeparatorCharacter()));

        try (var out = new PrintWriter(path, StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadSettingFile(final File path) throws FileNotFoundException
    {
        var jsonData = JsonBase.loadJsonFile(requireNonNull(path));

        openLoadAnimation = jsonData.getBooleanValue("��ʾ���ض���");
        var chars = jsonData.getString("�ָ���").toCharArray();
        separatorCharacter = chars[0];
    }

    public boolean isOpenLoadAnimation()
    {
        return openLoadAnimation;
    }

    public void setLoadAnimation(final boolean openLoadAnimation)
    {
        this.openLoadAnimation = openLoadAnimation;
    }

    public char getSeparatorCharacter()
    {
        return separatorCharacter;
    }

    public void setSeparatorCharacter(final char separatorCharacter)
    {
        this.separatorCharacter = separatorCharacter;
    }
}
