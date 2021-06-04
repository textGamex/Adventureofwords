package com.java.unit;

import com.java.account.AccountMessage;
import com.java.message.PrivateData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.NotSerializableException;

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
        assertEquals(1, role.defense().getHp());
        assertEquals(2, role.attack().getPhysicalAttack());
        assertEquals(3, role.getLevel());
        assertEquals(4, role.getExp());
        assertEquals(5, role.attack().getMana());
        assertEquals(6, role.defense().getLifeRegeneration());
        assertEquals(7, role.defense().getEvade());
        assertEquals(8, role.attack().getCrit());
        assertEquals(9.0, role.attack().getCritsEffect());
        assertEquals(10, role.getCash());
        assertEquals(11, role.getUpgradeNeedXp());
        assertEquals(12.0, role.defense().getPhysicalResistance());

        assertThrows(NullPointerException.class, () -> Role.loadData(null));
    }

    @Test
    void saveData() throws FileNotFoundException
    {
        var account = new AccountMessage(PrivateData.ACCOUNT1);
        role.saveData(account);

        var data = Role.loadData(account);

        assertEquals("²âÊÔ", data.getName());
        assertEquals(1, data.defense().getHp());
        assertEquals(2, data.attack().getPhysicalAttack());
        assertEquals(3, data.getLevel());
        assertEquals(4, data.getExp());
        assertEquals(5, data.attack().getMana());
        assertEquals(6, data.defense().getLifeRegeneration());
        assertEquals(7.0, data.defense().getEvade());
        assertEquals(8, data.attack().getCrit());
        assertEquals(9.0, data.attack().getCritsEffect());
        assertEquals(10, data.getCash());
        assertEquals(11, data.getUpgradeNeedXp());
        assertEquals(12.0, data.defense().getPhysicalResistance());

        assertThrows(NullPointerException.class, () -> role.saveData(null));
    }

    @Test
    void newStandardPrimaryLevelRole()
    {
        final var unit = Role.newStandardPrimaryLevelRole("²âÊÔµ¥Î»");

        assertEquals("²âÊÔµ¥Î»", unit.getName());
        assertEquals(100, unit.defense().getMaxHp());
        assertEquals(50, unit.getSpeed());
        assertEquals(1, unit.getLevel());
        assertEquals(1, unit.defense().getArmor());
        assertEquals(10, unit.attack().getCrit());
        assertEquals(2.0, unit.attack().getCritsEffect());
        assertEquals(50, unit.defense().getCritResistance());
        assertEquals(20,  unit.attack().getPhysicalAttack());
        assertEquals(50, unit.attack().getHit());
        assertEquals(5, unit.defense().getEvade());
    }

    @Test
    void testRoleSave()
    {
        var unit = new Role.Builder("²âÊÔ").build();

        assertDoesNotThrow(() -> unit.saveData( new AccountMessage("²âÊÔ")));
    }
}

