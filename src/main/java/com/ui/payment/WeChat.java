package com.ui.payment;

import com.ui.Ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author 留恋千年
 * @version 1.1.0
 */
public final class WeChat extends Ui
{
    public static void main(String[] args)
    {
        WeChat.showCollectionCode();
    }

    private final WeChat weChat = new WeChat();

    private WeChat()
    {

    }

    public Ui newWeChat()
    {
        return weChat;
    }

    public static void showCollectionCode()
    {
        EventQueue.invokeLater(() -> {
            JFrame jframe  = new JFrame("微信支付");
            jframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            jframe.setSize(500,700);
            jframe.setLocationRelativeTo(null);//在屏幕中居中显示
            JLabel label = new JLabel();
            jframe.add(label);
            label.setIcon(new ImageIcon("resources\\image\\WeChat.png"));
            jframe.setVisible(true);   //显示
        });
    }

    @Override
    public void show()
    {
        showCollectionCode();
    }
}
