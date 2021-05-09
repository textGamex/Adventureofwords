package com.java.CombatSystem.BuffModule;

import java.io.Serializable;

/**
 * 用于辅助实现{@link BuffModule}类的枚举类.
 *
 * <p>现在实现的负面效果</p>
 * <em>
 *     <li>流血</li>
 *     <li>中毒</li>
 *     <li>虚弱</li>
 *     <li>易伤</li>
 *     <li>减速</li>
 *     <li>虚弱</li>
 *     <li>沉默</li>
 * </em>
 *
 * <p>现在实现的增益效果</p>
 * <em>
 *     <li>恢复</li>
 *     <li>净化</li>
 *     <li>物理穿透</li>
 *     <li>法术穿透</li>
 * </em>
 * @author 千年
 * @version 1.1.0
 * @since 15
 * @see BuffModule
 */
public enum BuffType implements Serializable
{
    BLEED("流血"),
    POISON("中毒"),
    DEBILITY("虚弱"),
    DELICATE("易伤"),
    SLOW_DOWN("减速"),
    SILENT("沉默"),
    RECOVER("恢复"),
    RELIEVE("净化"),
    PHYSICS_PIERCE_THROUGH("物理穿透"),
    MAGIC_PIERCE_THROUGH("法术穿透");

    private final String type;
    BuffType(String type)
    {
        this.type = type;
    }

    /**
     * 获得这个buff类型的名称
     *
     * @return 这个buff类型的名称
     */
    public String getType()
    {
        return type;
    }
}
