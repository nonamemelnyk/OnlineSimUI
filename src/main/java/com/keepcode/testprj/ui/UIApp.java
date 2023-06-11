package com.keepcode.testprj.ui;

import javax.swing.*;
import java.awt.*;

public class UIApp {

    private Container contentPane;

    public UIApp(Container contentPane){
        this.contentPane = contentPane;
    }

    public void run() {
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(contentPane);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(300, 300);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
