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
            JFrame jframe  = new JFrame("΢��֧��");
            jframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            jframe.setSize(500,700);
            jframe.setLocationRelativeTo(null);//����Ļ�о�����ʾ
            JLabel label = new JLabel();
            jframe.add(label);
            label.setIcon(new ImageIcon("resources\\image\\WeChat.png"));
            jframe.setVisible(true);   //��ʾ
        });
    }

}
