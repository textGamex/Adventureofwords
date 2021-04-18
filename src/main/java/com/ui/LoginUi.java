package com.ui;

import com.java.Account.AccountMessage;
import static com.java.Message.PrivateData.*;
import java.util.Scanner;

public class LoginUi
{
    public static void main(String[] args)
    {
        loginMain();
    }
    public static AccountMessage loginMain()
    {
        System.out.print("�������˺�: ");
        var account = AccountMessage.readAccount(new Scanner(System.in));

        switch (account.getId())
        {
            case NEW_GAME_MANAGER:
                verifyPassword();
            case NEW_PLAYER:
                System.out.println("����!�����");
                account.createAccountDataFolder();
                break;
            case GAME_MANAGER:
                verifyPassword();
            case PLAYER:
                System.out.println("��¼�ɹ�!");
                System.out.println("��ӭ���ٴ���������ð�յ�����");
                break;
        }
        return account;
    }
    private static void verifyPassword()
    {
        var in = new Scanner(System.in);
        String password = "";

        System.out.print("����������: ");
        password = in.nextLine();
        if (password.equals(PASSWORD1) || password.equals(PASSWORD2))
            System.out.println("��¼�ɹ�!");
        else
        {
            System.out.println("�������!");
            System.out.println("3�����Ϸ�˳�!");
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
