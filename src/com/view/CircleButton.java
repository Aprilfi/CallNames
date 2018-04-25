package com.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleButton extends JButton {

    Shape shape;
    Color bgColor = SystemColor.control;
    public CircleButton() {
        this("未命名", null);
    }
    public CircleButton(String label) {
        this(label, null);
    }
    public CircleButton(String label, Color bgColor) {
        super(label); // 调用父类构造方法
        if (bgColor != null) {
            this.bgColor = bgColor;
        }
        Dimension size = this.getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        this.setPreferredSize(size); // 设置宽高等距
        this.setContentAreaFilled(false); // 不绘制内容区域
        this.setBorderPainted(false); // 不绘制边框
        this.setFocusPainted(false); // 不绘制焦点状态
    }
    protected void paintComponent(Graphics g) {
        // 如果鼠标按下，isArmed()方法返回true
        if (this.getModel().isArmed()) {
            g.setColor(java.awt.SystemColor.controlHighlight);
        } else {
            g.setColor(java.awt.SystemColor.controlShadow);
            g.setColor(this.bgColor); // 设置背景颜色
        }
        g.fillOval(0, 0, this.getSize().width - 1, this.getSize().height - 1); // 绘制圆形背景区域
        g.setColor(java.awt.SystemColor.controlShadow); // 设置边框颜色
        g.drawOval(0, 0, this.getSize().width - 1, this.getSize().height - 1); // 绘制边框线
        super.paintComponent(g);
    }
    public boolean contains(int x, int y) {
        if ((shape == null) || (!shape.getBounds().equals(this.getBounds()))) {
            this.shape = new Ellipse2D.Float(0, 0, this.getWidth(), this
                    .getHeight());
        }
        return shape.contains(x, y);
    }
}
