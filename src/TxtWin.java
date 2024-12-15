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
    private Color groundColor = Color.white;
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

    //
    private JTextArea jta = new JTextArea(); // Create a JTextArea
    private JScrollPane jsp = new JScrollPane(jta); // Wrap JTextArea in a JScrollPane for scrolling

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
        jta.setLineWrap(true); // Enable line wrapping
        jta.setWrapStyleWord(true); // Wrap at word boundaries
        jta.setFont(new Font("楷体", Font.PLAIN, 16)); // Set font
        jta.setBackground(Color.YELLOW); // Set background color

        // Add the JScrollPane (which contains JTextArea) to the JFrame
        this.add(jsp, BorderLayout.CENTER);
    }

    // 监听
    private void newFileAction() {
        jta.setText(""); // Clear the text area for a new file
    }

    private void openFileAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n"); // Append each line to the StringBuilder
                }
                jta.setText(sb.toString()); // Set the text area content
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFileAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(jta.getText()); // Write text from JTextArea to the file
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
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