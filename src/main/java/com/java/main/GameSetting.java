package com.java.main;

import com.alibaba.fastjson.JSONObject;
import com.java.localPersistence.JsonBaseTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

/**
 * 游戏设置.
 *
 * @author 留恋千年
 * @version 1.0.0
 * @since 2021-6-6
 */
public class GameSetting
{
    private static final GameSetting GAME_SETTING = new GameSetting();
    private GameSetting()
    {
        throw new AssertionError();
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
    private String separatorCharacter = "-";

    /**
     * 把设置文件保存在本地.
     */
    public void saveSetting(final File path)
    {
        requireNonNull(path);
        var jsonFile = new JSONObject();

        jsonFile.put("显示加载动画", isOpenLoadAnimation());
        jsonFile.put("分隔符", getSeparatorCharacter());

        try (var out = new PrintWriter(path, StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static GameSetting loadSettingFile(final File path) throws FileNotFoundException
    {
        var jsonData = JsonBaseTool.loadJsonFile(requireNonNull(path));

        var openLoadAnimation = jsonData.getBooleanValue("显示加载动画");
        var separatorCharacter = jsonData.getString("分隔符");

        GAME_SETTING.setLoadAnimation(openLoadAnimation);
        GAME_SETTING.setSeparatorCharacter(separatorCharacter);

        return GAME_SETTING;
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
     * @param separatorCharacter 分隔符
     * @throws NullPointerException 如果{@code separatorCharacter}为null
     */
    public void setSeparatorCharacter(final String separatorCharacter)
    {
        this.separatorCharacter = requireNonNull(separatorCharacter);
    }
}
