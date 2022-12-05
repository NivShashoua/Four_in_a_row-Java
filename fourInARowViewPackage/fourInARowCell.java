package fourInARowViewPackage;

import fourInARowModelPackage.Colour;
import javax.swing.*;
import java.awt.*;

public class fourInARowCell extends JPanel
{
    //Constants
    private static final int DiskSize = 80; //the size of the disk

    //Components
    private Colour cellColour; //the colour of the cell.

    //Constructor
    public fourInARowCell()
    {
        cellColour = Colour.EMPTY;

        //paint the background of the cell
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.white);
    }

    public void changeCellColour(Colour newColour)
    {
        cellColour = newColour;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if(cellColour == Colour.RED)
        {
            g.setColor(Color.RED);
            g.fillOval(0,0,DiskSize,DiskSize);
            this.setBackground(Color.white);
        }
        else if(cellColour == Colour.BLUE)
        {
            g.setColor(Color.BLUE);
            g.fillOval(0,0,DiskSize,DiskSize);
            this.setBackground(Color.white);
        }
        else
        {
            g.setColor(Color.WHITE);
            g.fillRect(0,0,DiskSize+10,DiskSize+10);
            this.setBackground(Color.white);
        }
    }
}
