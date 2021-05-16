package com.java.unit;

import com.java.account.AccountMessage;
import com.java.message.PrivateData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest
{
    private static final Role role = new Role.Builder("²âÊÔ").maxHp(1).physicalAttack(2).level(3).exp(4).mana(5)
        .lifeRegeneration(6).evade(7).crit(8).critsEffect(9.0).cash(10).upgradeNeedXp(11)
        .physicalResistance(12.0).build();

    @DisplayName("BuilderÄ£Ê½²âÊÔ")
    @Test
    void Builder()
    {
        assertTrue(role instanceof Role);

        assertEquals("²âÊÔ", role.getName());
        assertEquals(1, role.getHp());
        assertEquals(2, role.getPhysicalAttack());
        assertEquals(3, role.getLevel());
        assertEquals(4, role.getExp());
        assertEquals(5, role.getMana());
        assertEquals(6, role.getLifeRegeneration());
        assertEquals(7, role.getEvade());
        assertEquals(8, role.getCrit());
        assertEquals(9.0, role.getCritsEffect());
        assertEquals(10, role.getCash());
        assertEquals(11, role.getUpgradeNeedXp());
        assertEquals(12.0, role.getPhysicalResistance());

        assertThrows(NullPointerException.class, () -> Role.loadData(null));
    }

    @Test
    void saveData() throws FileNotFoundException
    {
        var account = new AccountMessage(PrivateData.ACCOUNT1);
        role.saveData(account);

        var data = Role.loadData(account);

        assertEquals("²âÊÔ", data.getName());
        assertEquals(1, data.getHp());
        assertEquals(2, data.getPhysicalAttack());
        assertEquals(3, data.getLevel());
        assertEquals(4, data.getExp());
        assertEquals(5, data.getMana());
        assertEquals(6, data.getLifeRegeneration());
        assertEquals(7.0, data.getEvade());
        assertEquals(8, data.getCrit());
        assertEquals(9.0, data.getCritsEffect());
        assertEquals(10, data.getCash());
        assertEquals(11, data.getUpgradeNeedXp());
        assertEquals(12.0, data.getPhysicalResistance());

        assertThrows(NullPointerException.class, () -> role.saveData(null));
    }
}