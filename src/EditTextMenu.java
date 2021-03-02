import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class EditTextMenu {
    private static JMenu editTextMenu;

    private EditTextMenu() {
        editTextMenu = new JMenu("Edit");

        addCopyMenuItem(editTextMenu);
        addCutMenuItem(editTextMenu);
        addPasteMenuItem(editTextMenu);
    }

    private void addCopyMenuItem(JMenu editTextMenu) {
        JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());

        copy.setText("Copy");
        copy.setMnemonic(KeyEvent.VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        editTextMenu.add(copy);
    }

    private void addCutMenuItem(JMenu editTextMenu) {
        JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());

        cut.setText("Cut");
        cut.setMnemonic(KeyEvent.VK_X);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        editTextMenu.add(cut);
    }

    private void addPasteMenuItem(JMenu editTextMenu) {
        JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());

        paste.setText("Paste");
        paste.setMnemonic(KeyEvent.VK_P);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        editTextMenu.add(paste);
    }

    static JMenu createEditTextMenu() {
        if(editTextMenu == null) {
            new EditTextMenu();
        }

        return editTextMenu;
    }
}
