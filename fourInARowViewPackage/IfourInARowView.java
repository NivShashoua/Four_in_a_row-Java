package fourInARowViewPackage;

import java.awt.event.ActionListener;

public interface IfourInARowView
{
    void drawBordGame();

    void addInsertDiskListener(int buttonNumber, ActionListener ins);

    void addClearListener(ActionListener clr);

    void showMassage(String str);
}
