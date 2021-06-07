package com.ui.payment;

import com.ui.Ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author ����ǧ��
 * @version 1.0.0
 */
public final class Alipay extends Ui
{
    private static final Alipay ALIPAY = new Alipay();

    private Alipay()
    {

    }

    public static Ui newAlipay()
    {
        return ALIPAY;
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

    @Override
    public void show()
    {
        showCollectionCode();
    }
}
