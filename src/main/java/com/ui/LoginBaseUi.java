package com.ui;

import com.java.account.AccountMessage;
import static com.java.message.PrivateData.*;
import java.util.Scanner;

/**
 * @author 留恋千年
 * @version 1.1.0
 */
public class LoginBaseUi extends BaseUi
{
    private static LoginBaseUi loginUi;
    private LoginBaseUi()
    {
    }

    public static BaseUi getLoginUi()
    {
        if (loginUi == null)
        {
            loginUi = new LoginBaseUi();
        }
        return loginUi;
    }

    @Override
    public void show()
    {
        loginMain();
    }

    public static AccountMessage loginMain()
    {
        System.out.print("请输入账号: ");
        var account = AccountMessage.readAccount(new Scanner(System.in));

        switch (account.getId())
        {
            case NEW_GAME_MANAGER:
                verifyPassword();
            case NEW_PLAYER:
                System.out.println("您好!新玩家");
                account.createAccountDataFolder();
                break;
            case GAME_MANAGER:
                verifyPassword();
            case PLAYER:
                System.out.println("登录成功!");
                System.out.println("欢迎您再次来到文字冒险的世界");
                break;
            default: throw new AssertionError();
        }
        return account;
    }

    private static void verifyPassword()
    {
        var in = new Scanner(System.in);
        String password;

        System.out.print("请输入密码: ");
        password = in.nextLine();
        if (password.equals(PASSWORD1) || password.equals(PASSWORD2))
        {
            System.out.println("登录成功!");
        }
        else
        {
            System.out.println("密码错误!");
            System.out.println("3秒后游戏退出!");
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.exit(2);
        }
    }
}
