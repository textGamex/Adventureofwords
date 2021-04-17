package com.ui;

import com.java.Account.AccountMessage;

import java.util.Scanner;

public class LoginUi
{
    public static void main(String[] args)
    {
        System.out.print("�������˺�: ");
        var account = AccountMessage.readAccount(new Scanner(System.in));
        switch (account.getId())
        {
            case NEW_GAME_MANAGER:
            case NEW_PLAYER:
                System.out.println("����!�����");
                account.createAccountDataFolder();
                break;
            case GAME_MANAGER:
            case PLAYER:
                System.out.println("��¼�ɹ�!");
                System.out.println("��ӭ���ٴ���������ð�յ�����");
                break;
        }
    }
    public static AccountMessage loginMain()
    {
        System.out.print("�������˺�: ");
        var account = AccountMessage.readAccount(new Scanner(System.in));
        switch (account.getId())
        {
            case NEW_GAME_MANAGER:
            case NEW_PLAYER:
                System.out.println("����!�����");
                account.createAccountDataFolder();
                break;
            case GAME_MANAGER:
            case PLAYER:
                System.out.println("��¼�ɹ�!");
                System.out.println("��ӭ���ٴ���������ð�յ�����");
                break;
        }
        return account;
    }
}
