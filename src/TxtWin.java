import javax.swing.*;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TxtWin extends JFrame {
    private String title = "记事本";// 标题
    private int width = 800;// 宽
    private int height = 600;// 高

    // 菜单选项
    static JMenuBar menuBar = new JMenuBar();
    static FgMenu fgMenu = new FgMenu("文件(F)", KeyEvent.VK_F);
    static JMenuItem menuItemNew = new JMenuItem("新建(N)", KeyEvent.VK_N);
    static JMenuItem menuItemOpen = new JMenuItem("打开(O)", KeyEvent.VK_O);
    static JMenuItem menuItemSave = new JMenuItem("保存(S)", KeyEvent.VK_S);
    static JMenuItem menuItemFont = new JMenuItem("字体与颜色(F)", KeyEvent.VK_F);
    static JMenuItem menuItemQuit = new JMenuItem("退出(Q)", KeyEvent.VK_Q);

    // 工具栏选项
    static JToolBar toolBar = new JToolBar();

    // 文本框
    private JTextArea jta = new JTextArea();
    private JScrollPane jsp = new JScrollPane(jta);

    // 菜单添加
    private void addMenuBar() {
        this.setJMenuBar(menuBar);
        fgMenu.add(menuItemNew);
        fgMenu.add(menuItemOpen);
        fgMenu.add(menuItemSave);
        fgMenu.addSeparator();
        fgMenu.add(menuItemFont);
        fgMenu.addSeparator();
        fgMenu.add(menuItemQuit);
        menuBar.add(fgMenu);
        // 添加监听
        menuItemNew.addActionListener(e -> newFileAction());
        menuItemOpen.addActionListener(e -> openFileAction());
        menuItemSave.addActionListener(e -> saveFileAction());
        menuItemFont.addActionListener(e -> changeFontAndColor());
        menuItemQuit.addActionListener(e -> dispose());
    }

    // 工具栏添加
    private void addToolBar() {
        // 工具条
        Container c = getContentPane();
        c.add(BorderLayout.NORTH, toolBar);

        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        FgButton[] btn = { new FgButton(new ImageIcon(ImageScaling.zoom("img//new.png", 0.2)), "新建文件"),
                new FgButton(new ImageIcon(ImageScaling.zoom("img//open.png", 0.2)), "打开文件"),
                new FgButton(new ImageIcon(ImageScaling.zoom("img//save.png", 0.3)), "保存文件") };
        // 给按键添加监听
        btn[0].addActionListener(e -> newFileAction());
        btn[1].addActionListener(e -> openFileAction());
        btn[2].addActionListener(e -> saveFileAction());
        for (FgButton button : btn) {
            button.setBorder(BorderFactory.createEmptyBorder());
            toolBar.add(button);
        }
        // 设置不可浮动
        toolBar.setFloatable(false);
    }

    // 文本框添加
    private void addTextArea() {
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        jta.setFont(new Font("楷体", Font.PLAIN, 16));
        jta.setBackground(Color.white);

        this.add(jsp, BorderLayout.CENTER);
    }

    // 监听
    // 新建
    private void newFileAction() {
        jta.setText("");
    }

    // 打开
    private void openFileAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                jta.setText(sb.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // 保存
    private void saveFileAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(jta.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // 字体与颜色
    private void changeFontAndColor() {

        // 字体选择
        Font newFont = FontChooser.showDialog(this, "选择字体", jta.getFont());
        if (newFont != null)
            jta.setFont(newFont);

        // 颜色选择
        Color newColor = JColorChooser.showDialog(this, "选择颜色", jta.getForeground());
        if (newColor != null)
            jta.setForeground(newColor);

    }

    // 退出
    private void quitFileAction(){
        
    }
    // 启动窗口
    public void launch() {

        // 菜单添加
        addMenuBar();
        // 工具栏添加
        addToolBar();
        // 文本框添加
        addTextArea();

        // 窗口是否可见
        this.setVisible(true);
        // 窗口大小
        this.setSize(width, height);
        // 设置窗口位置
        this.setLocationRelativeTo(null);
        // 设置标题
        this.setTitle(title);
        // 关闭进程
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}