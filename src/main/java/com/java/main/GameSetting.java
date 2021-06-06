package com.java.main;

/**
 * @author ¡Ù¡µ«ßƒÍ
 * @version 1.0.0
 * @since 2021-6-6
 */
public class GameSetting
{
    private boolean openLoadAnimation;

    public GameSetting(final boolean openLoadAnimation)
    {
        this.openLoadAnimation = openLoadAnimation;
    }

    public boolean isOpenLoadAnimation()
    {
        return openLoadAnimation;
    }

    public void setLoadAnimation(final boolean openLoadAnimation)
    {
        this.openLoadAnimation = openLoadAnimation;
    }
}
