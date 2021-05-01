package com.java.Unit;

import com.java.Account.AccountMessage;
import com.java.Message.PrivateData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RoleAttributeTest
{
    private static final RoleAttribute role = new RoleAttribute.Builder("≤‚ ‘").maxHp(1).atk(2).level(3).exp(4).mana(5)
        .lifeRegeneration(6).evade(7.0).critRate(8.0).critsEffect(9.0).cash(10).upgradeNeedXp(11)
        .physicalResistance(12.0).build();

    @DisplayName("Builderƒ£ Ω≤‚ ‘")
    @Test
    void Builder()
    {
        assertTrue(role instanceof RoleAttribute);

        assertEquals("≤‚ ‘", role.getName());
        assertEquals(1, role.getHp());
        assertEquals(2, role.getAtk());
        assertEquals(3, role.getLevel());
        assertEquals(4, role.getExp());
        assertEquals(5, role.getMana());
        assertEquals(6, role.getLifeRegeneration());
        assertEquals(7.0, role.getEvade());
        assertEquals(8.0, role.getCritRate());
        assertEquals(9.0, role.getCritsEffect());
        assertEquals(10, role.getCash());
        assertEquals(11, role.getUpgradeNeedXp());
        assertEquals(12.0, role.getPhysicalResistance());
    }

    @Test
    void saveData() throws FileNotFoundException
    {
        var account = new AccountMessage(PrivateData.ACCOUNT1);
        role.saveData(account);

        var data = RoleAttribute.loadData(account);

        assertEquals("≤‚ ‘", data.getName());
        assertEquals(1, data.getHp());
        assertEquals(2, data.getAtk());
        assertEquals(3, data.getLevel());
        assertEquals(4, data.getExp());
        assertEquals(5, data.getMana());
        assertEquals(6, data.getLifeRegeneration());
        assertEquals(7.0, data.getEvade());
        assertEquals(8.0, data.getCritRate());
        assertEquals(9.0, data.getCritsEffect());
        assertEquals(10, data.getCash());
        assertEquals(11, data.getUpgradeNeedXp());
        assertEquals(12.0, data.getPhysicalResistance());
    }
}