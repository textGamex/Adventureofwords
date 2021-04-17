package com.ui;

import com.java.Account.AccountMessage;

import java.util.Scanner;

public class LoginUi
{
    public static void main(String[] args)
    {
        System.out.print("请输入账号: ");
        var account = AccountMessage.readAccount(new Scanner(System.in));
        switch (account.getId())
        {
            case NEW_GAME_MANAGER:
            case NEW_PLAYER:
                System.out.println("您好!新玩家");
                account.createAccountDataFolder();
                break;
            case GAME_MANAGER:
            case PLAYER:
                System.out.println("登录成功!");
                System.out.println("欢迎您再次来到文字冒险的世界");
                break;
        }
    }
    public static AccountMessage loginMain()
    {
        System.out.print("请输入账号: ");
        var account = AccountMessage.readAccount(new Scanner(System.in));
        switch (account.getId())
        {
            case NEW_GAME_MANAGER:
            case NEW_PLAYER:
                System.out.println("您好!新玩家");
                account.createAccountDataFolder();
                break;
            case GAME_MANAGER:
            case PLAYER:
                System.out.println("登录成功!");
                System.out.println("欢迎您再次来到文字冒险的世界");
                break;
        }
        return account;
    }
}
