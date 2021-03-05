import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class EditMenu implements ActionListener {
    private static JMenu editMenu;
    private static UndoManager manager;
    private static JTextArea textArea;

    private EditMenu() {
        editMenu = new JMenu("Edit");
        manager = new UndoManager();
        textArea = TextEditor.textArea;

        addCopyMenuItem();
        addCutMenuItem();
        addPasteMenuItem();
        addSelectAllMenuItem();
        addUndoMenuItem();
        addRedoMenuItem();
    }

    private void addCopyMenuItem() {
        JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());

        copy.setText("Copy");
        copy.setMnemonic(KeyEvent.VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        editMenu.add(copy);
    }

    private void addCutMenuItem() {
        JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());

        cut.setText("Cut");
        cut.setMnemonic(KeyEvent.VK_X);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
        editMenu.add(cut);
    }

    private void addPasteMenuItem() {
        JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());

        paste.setText("Paste");
        paste.setMnemonic(KeyEvent.VK_P);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        editMenu.add(paste);
    }

    private void addSelectAllMenuItem() {
        JMenuItem selectAll = new JMenuItem("Select All");

        selectAll.setMnemonic(KeyEvent.VK_A);
        selectAll.addActionListener(this);
        editMenu.add(selectAll);
    }

    private void addUndoMenuItem() {
        JMenuItem undo = new JMenuItem("Undo");

        undo.setMnemonic(KeyEvent.VK_U);
        undo.addActionListener(this);
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        manager.addEdit(e.getEdit());
                    }
                }
        );
        editMenu.add(undo);
    }

    private void addRedoMenuItem() {
        JMenuItem redo = new JMenuItem("Redo");

        redo.setMnemonic(KeyEvent.VK_R);
        redo.addActionListener(this);
        editMenu.add(redo);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        switch (command) {
            case "Select All":
                TextEditor.textArea.selectAll();
                break;
            case "Undo":
                manager.undo();
                break;
            case "Redo":
                manager.redo();
                break;
        }


    }

    static JMenu createEditMenu() {
        if(editMenu == null) {
            new EditMenu();
        }

        return editMenu;
    }
}
