import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FontSelector implements ActionListener {
    private static String font;
    private static JComboBox fontSelector;

    private FontSelector() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSelector = new JComboBox(fonts);

        fontSelector.setMaximumSize(fontSelector.getPreferredSize());
        fontSelector.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox fontClicked = (JComboBox)event.getSource();

        font = (String)fontClicked.getSelectedItem();
    }

    static JComboBox createFontSelector() {
        if(fontSelector == null) {
            new FontSelector();
        }

        return fontSelector;
    }

    static String getFont() {
        return font;
    }
}
