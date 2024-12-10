import javax.swing.JMenu;

public class FgMenu extends JMenu {
    public FgMenu(String label) {
        super(label);
    }

    public FgMenu(String label, int nAccelerator) {
        super(label);
        setMnemonic(nAccelerator);
    }
}
