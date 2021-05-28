package com.java.unit;

import com.alibaba.fastjson.JSONObject;
import com.java.account.AccountMessage;
import com.java.account.Identity;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;
import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 * <p>�Ҿ���һ�¶�������</p>
 * <em>
 *     <li>���л���</li>
 *     <li>ӵ�о���</li>
 *     <li>������һ�����辭��</li>
 *     <li>��ɫ�������ڼ�ʱ��</li>
 * </em>
 * @version 0.3.2
 * @author ǧ��
 */
public class Role extends BasicUnit
{
    /**��ҵ�id*/
    private static int nextId = 70000;
    private final int id = ++nextId;
    /**���л���*/
    private int cash;
    /**ӵ�о���*/
    private int exp;
    /**������һ�����辭��*/
    private int upgradeNeedXp;
    /**��ɫ��������*/
    private final LocalDateTime creatingDateTime = LocalDateTime.now();

    /**
     * @throws NullPointerException ���{@code builder}Ϊnull
     */
    protected Role(Builder builder)
    {
        super(requireNonNull(builder));
        cash           = builder.cash;
        exp            = builder.exp;
        upgradeNeedXp  = builder.upgradeNeedXp;
    }

    /**
     * @author ǧ��
     * @version 1.1.2
     * @see com.java.unit.BasicUnit.Builder
     */
    public static class Builder extends BasicUnit.Builder<Builder>
    {
        private int cash           = 0;
        private int exp            = 0;
        private int upgradeNeedXp  = 10;

        /**
         * @throws NullPointerException ���{@code name}Ϊnull
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

    public final void addUpgradeNeedXP(int addUpgradeXP)
    {
        upgradeNeedXp += addUpgradeXP;
    }

    public final void addEXP(int gainEXP)
    {
        exp += gainEXP;
    }

    public final void addCash(int addCash)
    {
        cash += addCash;
    }

    public final void addTotalGameScore(int addGameScore)
    {
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
     * @return �ַ�����ʾ�Ķ���
     */
    @Override
    public String toString()
    {
        return  super.toString() +
                "[���л���:" + cash +
                ", ��ɫӵ�о���:" + exp +
                ", ������һ�����辭��:" + upgradeNeedXp +
                ", ��������:" + creatingDateTime +
                ']' +
                buff().toString();
    }

    /**
     * ���ڱ����ɫ������
     *
     * <p>����˺����������, �������л���ʽ����, ������ڲ���Ա, ����json��ʽ����</p>
     * @param acc �˽�ɫ����Ӧ����ҵ��˺�
     * @see AccountMessage
     * @throws NullPointerException ���{@code acc}Ϊnull
     */
    public void saveData(AccountMessage acc)
    {
        if (acc == null)
            throw new NullPointerException();
        if (acc.getId() == Identity.GAME_MANAGER || acc.getId() == Identity.NEW_GAME_MANAGER)
        {
            saveGameManagerData(acc);
        }
        else
            savePlayerData(acc);
    }
    private void saveGameManagerData(AccountMessage account)
    {
        assert account != null;

        var jsonFile = new JSONObject();
        jsonFile.put("����", super.getName());
        jsonFile.put("��λ�ȼ�", super.getLevel());
        jsonFile.put("�������ֵ", super.getHp());
        jsonFile.put("�ٶ�", super.getSpeed());
        jsonFile.put("ħ��ֵ", super.getMana());
        jsonFile.put("��������", super.getPhysicalAttack());
        jsonFile.put("����", super.getCrit());
        jsonFile.put("��������", super.getCritResistance());
        jsonFile.put("����Ч��", super.getCritsEffect());
        jsonFile.put("��������", super.getPhysicalResistance());
        jsonFile.put("����", super.getArmor());
        jsonFile.put("ÿ�غ������ظ�", super.getLifeRegeneration());
        jsonFile.put("����", super.getHit());
        jsonFile.put("����", super.getEvade());
        jsonFile.put("���л���", cash);
        jsonFile.put("��ɫӵ�о���", exp);
        jsonFile.put("������һ�����辭��", upgradeNeedXp);

        try (var out = new PrintWriter(
                account.getPlayerDataResolveFile("RoleAttribute.json"), StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void savePlayerData(AccountMessage account)
    {
        assert account != null;
        try (var out = new ObjectOutputStream(
                new FileOutputStream(account.getPlayerDataResolveFile("RoleAttribute.dat"))))
        {
            out.writeObject(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param account Ҫ��ȡ���˺�
     * @return ���������Ϣ
     * @throws FileNotFoundException ���Ҫ��ȡ���ļ�������
     * @since 15
     */
    public static Role loadData(AccountMessage account) throws FileNotFoundException
    {
        if (account == null)
            throw new NullPointerException();
        //���������,����ô�ܶ�ȡ��?
        if (fileNotExist(account))
            throw new IllegalStateException("�ļ�������,Id: " + account.getId());

        if (account.getId() == Identity.PLAYER)
        {
            var archive = loadPlayerData(account);
            if (archive == null)
                throw new NullPointerException("loadPlayer��������Null");
            return archive;
        }
        else
        {
            return loadGameManagerData(account);
        }
    }
    private static boolean fileNotExist(AccountMessage acc)
    {
        assert acc != null;

        return acc.getId() == Identity.NONE || acc.getId() == Identity.NEW_GAME_MANAGER
                || acc.getId() == Identity.NEW_PLAYER;
    }
    private static Role loadPlayerData(AccountMessage acc)
    {
        assert acc != null;

        Role archive = null;
        try (var in = new ObjectInputStream(new FileInputStream(
                acc.getPlayerDataResolveFile("RoleAttribute.dat"))))
        {
            archive = (Role) in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return archive;
    }

    private static Role loadGameManagerData(AccountMessage acc) throws FileNotFoundException
    {
        assert acc != null;

        String line;
        try (var in = new Scanner(acc.getPlayerDataResolveFile(
                "RoleAttribute.json"), StandardCharsets.UTF_8))
        {
            line = in.nextLine();
        }
        catch (IOException e)
        {
            var e2 = new FileNotFoundException();
            e2.initCause(e);
            throw e2;
        }
        var json = JSONObject.parseObject(line);

        var name = json.getString("����");
        var level = json.getIntValue("��λ�ȼ�");
        var hp = json.getIntValue("�������ֵ");
        var speed = json.getIntValue("�ٶ�");
        var mana = json.getIntValue("ħ��ֵ");
        var atk = json.getIntValue("��������");
        var crit = json.getIntValue("����");
        var critResistance = json.getIntValue("��������");
        var critsEffect = json.getDoubleValue("����Ч��");
        var physicalResistance = json.getDoubleValue("��������");
        var armor = json.getIntValue("����");
        var hit = json.getIntValue("����");
        var evade = json.getIntValue("����");
        var cash = json.getIntValue("���л���");
        var lifeRegeneration = json.getIntValue("ÿ�غ������ظ�");
        var exp = json.getIntValue("��ɫӵ�о���");
        var upgradeNeedXp = json.getIntValue("������һ�����辭��");

        return new Builder(name).level(level).maxHp(hp).mana(mana).physicalAttack(atk).cash(cash).crit(crit).speed(speed)
                .physicalResistance(physicalResistance).critsEffect(critsEffect).hit(hit).evade(evade).exp(exp)
                .lifeRegeneration(lifeRegeneration).upgradeNeedXp(upgradeNeedXp).critResistance(critResistance)
                .armor(armor).build();
    }
}