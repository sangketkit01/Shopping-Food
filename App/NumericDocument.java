package App;

import javax.swing.text.*;
public class NumericDocument extends PlainDocument {
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null || str.isEmpty()) {
            return;
        }

        StringBuilder builder = new StringBuilder(getText(0, getLength()));
        builder.insert(offset, str);

        if (isNumeric(builder.toString())) {
            super.insertString(offset, str, attr);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
