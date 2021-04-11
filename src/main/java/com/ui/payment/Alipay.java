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
            JFrame jframe  = new JFrame("支付宝支付");
            jframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            jframe.setSize(420,700);
            jframe.setLocationRelativeTo(null);//在屏幕中居中显示
            JLabel label = new JLabel();
            jframe.add(label);
            label.setIcon(new ImageIcon("resources\\image\\Alipay.jpg"));//TODO:图片加载不出来
            jframe.setVisible(true);   //显示
        });
    }
}
