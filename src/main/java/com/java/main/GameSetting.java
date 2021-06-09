package com.java.main;

import com.alibaba.fastjson.JSONObject;
import com.java.localPersistence.JsonBaseTool;

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
    private String separatorCharacter = "-";
    /**
     * �������ļ������ڱ���.
     */
    public void saveSetting(final File path)
    {
        requireNonNull(path);
        var jsonFile = new JSONObject();

        jsonFile.put("��ʾ���ض���", isOpenLoadAnimation());
        jsonFile.put("�ָ���", getSeparatorCharacter());

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
        var jsonData = JsonBaseTool.loadJsonFile(requireNonNull(path));

        openLoadAnimation = jsonData.getBooleanValue("��ʾ���ض���");
        separatorCharacter = jsonData.getString("�ָ���");
    }

    public boolean isOpenLoadAnimation()
    {
        return openLoadAnimation;
    }

    public void setLoadAnimation(final boolean openLoadAnimation)
    {
        this.openLoadAnimation = openLoadAnimation;
    }

    public String getSeparatorCharacter()
    {
        return separatorCharacter;
    }

    /**
     * @param separatorCharacter �ָ���
     * @throws NullPointerException ���{@code separatorCharacter}Ϊnull
     */
    public void setSeparatorCharacter(final String separatorCharacter)
    {
        this.separatorCharacter = requireNonNull(separatorCharacter);
    }
}
