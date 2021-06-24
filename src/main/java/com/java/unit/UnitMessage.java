package com.java.unit;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 留恋千年
 */
public class UnitMessage implements Serializable
{
    public static void main(String[] args)
    {
        Runnable t1 = () ->
        {
            for (var i = 0; i < 100; i++)
            {
                System.out.println("线程1");
                try
                {
                    Thread.sleep(20);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };

        Runnable t2 = () ->
        {
            for (var i = 0; i < 100; i++)
            {
                System.out.println("线程2");
                try
                {
                    Thread.sleep(20);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        var th = Executors.newCachedThreadPool();
        th.submit(t2);
        th.submit(t1);
        th.shutdown();
    }
}
