import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TxtWin extends JFrame {
    private String title = "记事本";
    private Color groundColor = Color.white;
    private int width = 800;
    private int height = 600;
    static JMenuBar menuBar = new JMenuBar();
    static FgMenu fgFile = new FgMenu("文件(F)", KeyEvent.VK_F);
    static JMenuItem menuItemNew = new JMenuItem("新建(N)", KeyEvent.VK_N);
    static JMenuItem menuItemOpen = new JMenuItem("打开(O)", KeyEvent.VK_O);
    static JMenuItem menuItemSave = new JMenuItem("保存(S)", KeyEvent.VK_S);
    static JMenuItem menuItemFont = new JMenuItem("字体与颜色(F)", KeyEvent.VK_F);
    static JMenuItem menuItemQuit = new JMenuItem("退出(Q)", KeyEvent.VK_Q);
    JToolBar toolBar = new JToolBar();

    // 菜单设置
    public void menuSet() {
        this.setJMenuBar(menuBar);
        fgFile.add(menuItemNew);
        fgFile.add(menuItemOpen);
        fgFile.add(menuItemSave);
        fgFile.addSeparator();
        fgFile.add(menuItemFont);
        fgFile.addSeparator();
        fgFile.add(menuItemQuit);
        menuBar.add(fgFile);
    }

    public void launch() {
        // 窗口是否可见
        this.setVisible(true);
        // 窗口大小
        this.setSize(width, height);
        // 设置窗口位置
        this.setLocationRelativeTo(null);
        // 设置标题
        this.setTitle(title);
        // 菜单添加
        menuSet();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

   
}