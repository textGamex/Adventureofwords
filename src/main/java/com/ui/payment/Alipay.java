package com.ui.payment;

import javax.swing.*;
import java.awt.*;

public final class Alipay
{
    public static void main(String[] args)
    {
        Alipay.showCollectionCode();
    }
    public static void showCollectionCode()
    {
        EventQueue.invokeLater(() -> {
            JFrame jframe  = new JFrame("֧����֧��");
            jframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            jframe.setSize(420,700);
            jframe.setLocationRelativeTo(null);//����Ļ�о�����ʾ
            JLabel label = new JLabel();
            jframe.add(label);
            label.setIcon(new ImageIcon("resources\\image\\Alipay.jpg"));//TODO:ͼƬ���ز�����
            jframe.setVisible(true);   //��ʾ
        });
    }
}
