package fourInARowControllerPackage;

import fourInARowModelPackage.*;
import fourInARowViewPackage.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fourInARowController implements IfourInARowController
{
    //Constants
    private static final int NumberOfButtons = 7; //number of buttons in the last row

    //Components
    private IfourInARowModel model;
    private IfourInARowView view;

    //Constructor
    public fourInARowController(IfourInARowModel inputModel, IfourInARowView InputView)
    {
        model = inputModel;
        view = InputView;

        //add listeners
        for (int i = 0; i < NumberOfButtons; i++)
        {
            view.addInsertDiskListener(i, new insertDiskListener());
        }
        view.addClearListener(new ClearListener());
    }

    private class insertDiskListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String buttonName = e.getActionCommand();
            int buttonNumber = Integer.parseInt(buttonName) -1;
            try
            {
                model.insertDisk(buttonNumber);
            }
            catch (IllegalArgumentException err)
            {
                view.showMassage(err.getMessage());
            }
            view.drawBordGame();
            //check if someone won
            if(model.whoWon() == Colour.RED)
            {
                view.showMassage("Red won!!!");
                model.clear();
            }
            else if (model.whoWon() == Colour.BLUE)
            {
                view.showMassage("Blue won!!!");
                model.clear();
            }
        }


    }

    private class ClearListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            model.clear();
            view.drawBordGame();
        }
    }

}
