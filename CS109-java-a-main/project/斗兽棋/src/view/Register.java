package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Register extends JFrame {
    public JTextField username;
    public JPasswordField password;
    public JButton registerButton;
    public JButton loginButton;
    public Map<String, String> userDatabase;
public JPanel panel;
    public Register() {
        userDatabase = new HashMap<>();
        setTitle("斗兽棋");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);


        JLabel usernameLabel = new JLabel("用户名: ");
        username = new JTextField();
        JLabel passwordLabel = new JLabel("密码: ");
        password = new JPasswordField();

        registerButton = new JButton("注册");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        String imagePath = "resource\\background\\background.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(imageIcon);

       panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {

                   String imagePath = "resource\\background\\background.png";

                    Image backgroundImage = ImageIO.read(new File(imagePath));

                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        panel.setLayout(new GridLayout(3, 2));

        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(registerButton);
        panel.add(loginButton);
       // panel.add(imageLabel);
        add(panel);

    }

   public void register() {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userDatabase.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "用户名已存在！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        userDatabase.put(username, password);
        JOptionPane.showMessageDialog(this, "注册成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
    }

    public void login() {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入用户名和密码！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userDatabase.containsKey(username)) {
            if (userDatabase.get(username).equals(password)) {
               // JOptionPane.showMessageDialog(this, "登录成功！", "成功", JOptionPane.INFORMATION_MESSAGE);

                BeginComponent beginComponent=new BeginComponent();
dispose();


                    //this.setVisible(false);
                    beginComponent.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "用户名不存在！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

}
