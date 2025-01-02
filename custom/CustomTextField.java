package custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A custom text field with placeholder text and character limit functionality.
 * The placeholder text disappears when the field gains focus or is typed into,
 * and reappears if the field is empty when it loses focus.
 */
public class CustomTextField extends JTextField {
    /** Placeholder text to display when the field is empty and unfocused. */
    private String placeHolderText;

    /** Indicates whether the placeholder text is currently displayed. */
    private boolean hasPlaceHolder;

    /**
     * Constructs a CustomTextField with the specified placeholder text and character limit.
     *
     * @param placeHolderText the placeholder text to display when the field is empty
     * @param charLimit the maximum number of characters allowed in the field
     */
    public CustomTextField(String placeHolderText, int charLimit) {
        super();
        this.placeHolderText = placeHolderText;
        hasPlaceHolder = true;
        setDocument(new TextManager(charLimit));
        setText(this.placeHolderText);
        setMargin(new Insets(0, 10, 0, 0));
        addListeners();
    }

    /**
     * Checks if the placeholder text is currently displayed.
     *
     * @return true if the placeholder text is displayed, false otherwise
     */
    public boolean isHasPlaceHolder() {
        return hasPlaceHolder;
    }

    /**
     * Adds mouse, key, and focus listeners to handle placeholder text behavior.
     */
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (hasPlaceHolder) {
                    hasPlaceHolder = false;
                    setText("");
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (hasPlaceHolder) {
                    hasPlaceHolder = false;
                    setText("");
                }
            }
        });

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // No action needed on focus gained
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().toString().length() <= 0) {
                    hasPlaceHolder = true;
                    setText(placeHolderText);
                }
            }
        });
    }
}