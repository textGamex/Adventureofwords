package com.java.Unit;

public final class EnemyUnit extends EnemyAttribute
{

   public static EnemyAttribute getEnemyUnit(String EnemyUnitType) throws IllegalArgumentException
    {
        return switch (EnemyUnitType) {
            case "哥布林" -> new EnemyAttribute(EnemyUnitType, 1, 50, 12,
                    0.1f, 1.5f, 2, 0,
                    3, 0.0f, 0, 10001, 5, 5, 3);
            default -> throw new IllegalArgumentException("异常参数: " + EnemyUnitType);
        };
    }

}