package com.ui.payment;

import com.ui.BaseUi;

import javax.swing.*;
import java.awt.*;

/**
 * @author 留恋千年
 * @version 1.1.0
 */
public final class WeChat extends BaseUi
{
    public static void main(String[] args)
    {
        WeChat.showCollectionCode();
    }

    private static WeChat wechat;

    private WeChat()
    {

    }

    public BaseUi getWeChat()
    {
        if (wechat == null)
        {
            wechat = new WeChat();
        }
        return wechat;
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
