package com.architect.week3;

import com.architect.week3.component.*;

public class Main {

    public static void main(String[] args) {
        WinForm winForm = new WinForm("WINDOW窗口");

        // wire the first layer
        winForm.addChild(new Picture("LOGO图片"));
        winForm.addChild(new Button("登录"));
        winForm.addChild(new Button("注册"));
        Frame frame1 = new Frame("FRAME1");
        winForm.addChild(frame1);

        // wire the second layer: frame1
        frame1.addChild(new Label("用户名"));
        frame1.addChild(new TextBox("文本框"));
        frame1.addChild(new Label("密码"));
        frame1.addChild(new PasswordBox("密码框"));
        frame1.addChild(new CheckBox("复选框"));
        frame1.addChild(new TextBox("记住用户名"));
        frame1.addChild(new LinkLabel("忘记密码"));

        // render the WinForm
        winForm.draw();

    }
}
