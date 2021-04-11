package com.ui.payment;

import javax.swing.*;
import java.awt.*;

public final class WeChat extends JFrame
{
    public static void main(String[] args)
    {
        WeChat.showCollectionCode();
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

}
