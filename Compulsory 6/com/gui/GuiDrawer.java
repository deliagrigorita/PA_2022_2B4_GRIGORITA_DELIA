package com.gui;

import javax.swing.*;

public class GuiDrawer {
    JFrame frame;

    public GuiDrawer() {
        this.frame = new JFrame();
    }

    public void runInterface() {

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        frame.add(b);//adding button in JFrame

        frame.setSize(400,500);//400 width and 500 height
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);

    }
}
