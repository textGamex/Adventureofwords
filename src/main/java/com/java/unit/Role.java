package com.java.unit;

import com.alibaba.fastjson.JSONObject;
import com.java.account.AccountMessage;
import com.java.account.Identity;
import com.java.localPersistence.JsonBaseTool;
import com.java.localPersistence.SaveData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 * <p>且具有一下额外属性</p>
 * <li>持有货币</li>
 * <li>拥有经验</li>
 * <li>升到下一级所需经验</li>
 * <li>角色创建日期及时间</li>
 * @version 1.0.3
 * @author 留恋千年
 */
public class Role extends BasicUnit
{
    /**玩家的id*/
    private static int nextId = 70000;
    private final int id = ++nextId;
    /**持有货币*/
    private int cash;
    /**拥有经验*/
    private int exp;
    /**升到下一级所需经验*/
    private int upgradeNeedXp;
    /**角色创建日期*/
    private final LocalDateTime creatingDateTime = LocalDateTime.now();

    /**
     * @throws NullPointerException 如果{@code builder}为null
     */
    protected Role(Builder builder)
    {
        super(requireNonNull(builder));
        cash           = builder.cash;
        exp            = builder.exp;
        upgradeNeedXp  = builder.upgradeNeedXp;
    }

    /**
     * @author 留恋千年
     * @version 1.1.2
     * @see com.java.unit.BasicUnit.Builder
     */
    public static class Builder extends BasicUnit.Builder<Builder>
    {
        private int cash           = 0;
        private int exp            = 0;
        private int upgradeNeedXp  = 10;

        /**
         * @throws NullPointerException 如果{@code name}为null
         */
        public Builder(String name)
        {
            super(requireNonNull(name));
        }

        public Builder cash(int cash)
        {
            this.cash = cash;
            return this;
        }

        public Builder exp(int exp)
        {
            this.exp = exp;
            return this;
        }

        public Builder upgradeNeedXp(int upgradeNeedXp)
        {
            this.upgradeNeedXp = upgradeNeedXp;
            return this;
        }

        @Override
        public Role build()
        {
            return new Role(this);
        }
    }

    /**
     * 构建一个一级标准玩家角色对象.
     *
     * @param name 名称
     * @return 返回一个一级标准玩家角色对象
     * @throws NullPointerException 如果{@code name}为null
     */
    public static Role newStandardPrimaryLevelRole(final String name)
    {
        return new Role.Builder(requireNonNull(name)).maxHp(100).speed(50).level(1).armor(1).crit(10)
                .critsEffect(2.0).critResistance(50).physicalAttack(20).hit(50).evade(5).build();
    }

    public final int getCash()
    {
        return cash;
    }

    public final LocalDateTime getCreatingDateTime()
    {
        return creatingDateTime;
    }

    public final int getExp()
    {
        return exp;
    }

    public final int getUpgradeNeedXp()
    {
        return upgradeNeedXp;
    }

    @Override
    public final int getId()
    {
        return id;
    }

    public static int getNextId()
    {
        return nextId;
    }

    /**
     * @return 字符串表示的对象
     */
    @Override
    public String toString()
    {
        return  super.toString() +
                "[持有货币:" + cash +
                ", 角色拥有经验:" + exp +
                ", 升到下一级所需经验:" + upgradeNeedXp +
                ", 创建日期:" + creatingDateTime +
                ']' +
                buff().toString();
    }

    /**
     * 用于保存角色的属性
     *
     * <p>如果账号类型是玩家, 则用序列化方式保存, 如果是内部人员, 则用json方式保存</p>
     * @param acc 此角色所对应的玩家的账号
     * @see AccountMessage
     * @throws NullPointerException 如果{@code acc}为null
     */
    public void saveData(AccountMessage acc)
    {
        requireNonNull(acc);
        if (acc.getId() == Identity.GAME_MANAGER || acc.getId() == Identity.NEW_GAME_MANAGER)
        {
            saveGameManagerData(acc);
        }
        else
        {
            savePlayerData(acc);
        }
    }

    public void saveGameManagerData(final AccountMessage account)
    {
        saveGameManagerData(requireNonNull(account).getPlayerDataResolveFile("RoleAttribute.json"));
    }

    public void saveGameManagerData(final File path)
    {
        requireNonNull(path);

        var jsonFile = new JSONObject();
        jsonFile.put("名称", super.getName());
        jsonFile.put("单位等级", super.getLevel());
        jsonFile.put("最大生命值", super.defense().getHp());
        jsonFile.put("速度", super.getSpeed());
        jsonFile.put("魔法值", super.attack().getMana());
        jsonFile.put("物理攻击", super.attack().getPhysicalAttack());
        jsonFile.put("暴击", super.attack().getCrit());
        jsonFile.put("暴击抗性", super.defense().getCritResistance());
        jsonFile.put("暴击效果", super.attack().getCritsEffect());
        jsonFile.put("物理抗性", super.defense().getPhysicalResistance());
        jsonFile.put("护甲", super.defense().getArmor());
        jsonFile.put("每回合生命回复", super.defense().getLifeRegeneration());
        jsonFile.put("每回合魔法值回复", super.attack().getManaRecovery());
        jsonFile.put("命中", super.attack().getHit());
        jsonFile.put("闪避", super.defense().getEvade());
        jsonFile.put("持有货币", cash);
        jsonFile.put("角色拥有经验", exp);
        jsonFile.put("升到下一级所需经验", upgradeNeedXp);

        try (var out = new PrintWriter(path, StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void savePlayerData(final AccountMessage account)
    {
        SaveData.saveObjectData(this, requireNonNull(account).getPlayerDataResolveFile("RoleAttribute.dat"));
    }

    /**
     *
     * @param account 要读取的账号
     * @return 玩家属性信息
     * @throws FileNotFoundException 如果要读取的文件不存在
     * @since 15
     */
    public static Role loadData(final AccountMessage account) throws FileNotFoundException
    {
        requireNonNull(account);
        //如果不存在,那怎么能读取呢?
        if (fileNotExist(account))
        {
            throw new IllegalStateException("文件不存在,Id: " + account.getId());
        }

        if (account.getId() == Identity.PLAYER)
        {
            var archive = loadPlayerData(account);
            if (archive == null)
            {
                throw new NullPointerException("loadPlayer方法返回Null");
            }
            return archive;
        }
        else
        {
            return loadGameManagerData(account);
        }
    }

    private static boolean fileNotExist(final AccountMessage acc)
    {
        assert acc != null;

        return acc.getId() == Identity.NEW_GAME_MANAGER || acc.getId() == Identity.NEW_PLAYER;
    }

    public static Role loadPlayerData(final AccountMessage acc)
    {
        return loadPlayerData(requireNonNull(acc).getPlayerDataResolveFile("RoleAttribute.dat"));
    }

    public static Role loadPlayerData(final File path)
    {
        requireNonNull(path);

        Role archive = null;
        try (var in = new ObjectInputStream(new FileInputStream(path)))
        {
            archive = (Role) in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return archive;
    }

    public static Role loadGameManagerData(final AccountMessage acc) throws FileNotFoundException
    {
        return loadGameManagerData(requireNonNull(acc).getPlayerDataResolveFile("RoleAttribute.json"));
    }

    public static Role loadGameManagerData(final File path) throws FileNotFoundException
    {
        var json = JsonBaseTool.loadJsonFile(requireNonNull(path));

        var name = json.getString("名称");
        var level = json.getIntValue("单位等级");
        var hp = json.getIntValue("最大生命值");
        var speed = json.getIntValue("速度");
        var mana = json.getIntValue("魔法值");
        var atk = json.getIntValue("物理攻击");
        var crit = json.getIntValue("暴击");
        var critResistance = json.getIntValue("暴击抗性");
        var critsEffect = json.getDoubleValue("暴击效果");
        var physicalResistance = json.getDoubleValue("物理抗性");
        var armor = json.getIntValue("护甲");
        var hit = json.getIntValue("命中");
        var evade = json.getIntValue("闪避");
        var cash = json.getIntValue("持有货币");
        var lifeRegeneration = json.getIntValue("每回合生命回复");
        var manaRecovery = json.getIntValue("每回合魔法值回复");
        var exp = json.getIntValue("角色拥有经验");
        var upgradeNeedXp = json.getIntValue("升到下一级所需经验");

        return new Builder(name).level(level).maxHp(hp).mana(mana).physicalAttack(atk).cash(cash).crit(crit).speed(speed)
                .physicalResistance(physicalResistance).critsEffect(critsEffect).hit(hit).evade(evade).exp(exp)
                .lifeRegeneration(lifeRegeneration).upgradeNeedXp(upgradeNeedXp).critResistance(critResistance)
                .armor(armor).manaRecovery(manaRecovery).build();
    }
}