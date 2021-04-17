package com.java.Unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleAttributeTest
{
    @DisplayName("Builderģʽ����")
    @Test
    void Builder()
    {
        var role = new RoleAttribute.Builder("w").atk(1).build();

        assertTrue(role instanceof RoleAttribute);
    }
}