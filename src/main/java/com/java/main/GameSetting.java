package com.java.main;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.requireNonNull;

/**
 * @author 留恋千年
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
     * @return 返回一个 {@code GameSetting}对象
     */
    public static GameSetting getGameSetting()
    {
        return GAME_SETTING;
    }

    private boolean openLoadAnimation = true;
    /**
     * 是否开启加载动画.
     *
     * @return 如果开启, 返回{@code true}, 否则返回{@code false}
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
        jsonFile.put("显示加载动画", isOpenLoadAnimation());

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
