import javax.swing.*;
import java.awt.*;
import java.awt.GraphicsEnvironment;

class FontChooser {

    // 显示字体选择对话框，并返回用户选择的字体
    public static Font showDialog(JFrame parent, String title, Font initialFont) {
        // 创建一个对话框
        JDialog dialog = new JDialog(parent, title, true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        // 创建字体选择器和字体大小选择器
        JComboBox<String> cbxFont = new JComboBox<>();
        JComboBox<Integer> cbxFontSize = new JComboBox<>();
        JButton okButton = new JButton("确定");
        JButton cancelButton = new JButton("取消");

        // 获取系统可用的字体列表
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontList = ge.getAvailableFontFamilyNames();

        // 将所有字体加入字体选择框
        for (String fontName : fontList) {
            cbxFont.addItem(fontName);
        }

        // 将字体大小（9到72）加入字体大小选择框
        for (int i = 9; i <= 72; i++) {
            cbxFontSize.addItem(i);
        }

        // 设置默认字体和大小
        if (initialFont != null) {
            cbxFont.setSelectedItem(initialFont.getFamily()); // 设置字体
            cbxFontSize.setSelectedItem(initialFont.getSize()); // 设置字体大小
        }
        // 点击“确定”按钮时，返回用户选择的字体
        okButton.addActionListener(e -> {
            String selectedFontName = (String) cbxFont.getSelectedItem();
            int selectedFontSize = (Integer) cbxFontSize.getSelectedItem();
            Font selectedFont = new Font(selectedFontName, Font.PLAIN, selectedFontSize);
            parent.setFont(selectedFont); // 设置父窗口字体（可以根据实际需要设置）
            dialog.dispose(); // 关闭对话框
        });

        // 点击“取消”按钮时，直接关闭对话框
        cancelButton.addActionListener(e -> dialog.dispose());

        // 将控件添加到对话框
        dialog.add(new JLabel("字体名称："));
        dialog.add(cbxFont);
        dialog.add(new JLabel("字体大小："));
        dialog.add(cbxFontSize);
        dialog.add(okButton);
        dialog.add(cancelButton);

        // 设置对话框属性
        dialog.pack();
        dialog.setLocationRelativeTo(parent); // 居中显示
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

        return new Font((String) cbxFont.getSelectedItem(), Font.PLAIN, (Integer) cbxFontSize.getSelectedItem());
    }
}
