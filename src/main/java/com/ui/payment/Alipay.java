package com.ui.payment;

import com.ui.BaseUi;

import javax.swing.*;
import java.awt.*;

/**
 * @author 留恋千年
 * @version 1.0.0
 */
public final class Alipay extends BaseUi
{
    private static final Alipay ALIPAY = new Alipay();

    private Alipay()
    {

    }

    public static BaseUi newAlipay()
    {
        return ALIPAY;
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

    @Override
    public void show()
    {
        showCollectionCode();
    }
}
