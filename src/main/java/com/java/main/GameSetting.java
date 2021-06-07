package com.java.main;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
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
    /**
     * �Ƿ������ض���.
     *
     * @return �������, ����{@code true}, ���򷵻�{@code false}
     */
    public boolean isOpenLoadAnimation()
    {
        return openLoadAnimation;
    }

    public void setLoadAnimation(final boolean openLoadAnimation)
    {
        this.openLoadAnimation = openLoadAnimation;
    }

    public void saveSetting(final File path)
    {
        requireNonNull(path);
        var jsonFile = new JSONObject();
        jsonFile.put("��ʾ���ض���", isOpenLoadAnimation());

        try (var out = new PrintWriter(path, StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
