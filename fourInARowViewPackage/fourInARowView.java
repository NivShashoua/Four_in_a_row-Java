package fourInARowViewPackage;

import fourInARowModelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class fourInARowView extends JFrame implements IfourInARowView
{
    //Constants
    private static final int FrameSize = 600;
    private static final int RowsSize = 5; //number of rows in the bord game
    private static final int ColsSize = 7; //number of columns in the bord game
    private static final int NumberOfButtons = 7; //number of buttons in the last row
    private static final int EmptySpace = 3; //how many cell to fill to make the clear button in the middle

    //Components
    private IfourInARowModel model;
    private final fourInARowCell[][] matrix;  //the bord game
    private final JButton[] buttons;    //the row of buttons that the user play with
    private JButton clearButton;        //a button to reset the game

    //Constructor
    public fourInARowView(IfourInARowModel inputModel)
    {
        model = inputModel;
        matrix = new fourInARowCell[RowsSize][ColsSize];
        clearButton = new JButton("clear");
        buttons = new JButton[NumberOfButtons];

        setLayout(new GridLayout(RowsSize + 2,ColsSize)); // +2 include the rows of the buttons

        //create the bord game
        for(int row = 0; row < RowsSize; row++)
        {
            for(int col = 0; col < ColsSize; col++)
            {
                matrix[row][col] = new fourInARowCell();
                add(matrix[row][col]);  //adding the panels
            }
        }

        //create the 7 buttons
        for(int i = 0; i < NumberOfButtons; i++)
        {
            this.buttons[i] = new JButton(i+1 + "");
            add(this.buttons[i]);
        }

        //create the clear button
        for (int i = 0; i < EmptySpace; i++)
        {
            add(new JPanel());  //unreachable panels to fill the 3 cell
        }
        add(clearButton);

        setSize(FrameSize, FrameSize );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //draw the bord game according to the model object
    public void drawBordGame()
    {
        for(int row = 0; row < RowsSize; row++)
        {
            for(int col = 0; col <ColsSize; col++)
            {
                matrix[row][col].changeCellColour(model.getColourInCell(row,col));
            }
        }

    }

    public void addInsertDiskListener(int buttonNumber, ActionListener ins)
    {
        buttons[buttonNumber].addActionListener(ins);
    }

    public void addClearListener(ActionListener clr)
    {
        clearButton.addActionListener(clr);
    }

    //massage box to interact with the user
    public void showMassage(String str)
    {
        JOptionPane.showMessageDialog(this ,str);
    }

}
