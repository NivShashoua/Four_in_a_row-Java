package fourInARowModelPackage;

import javax.swing.plaf.IconUIResource;
import java.lang.management.PlatformLoggingMXBean;

public class fourInARowModel implements IfourInARowModel
{
    //Constants
    private static final int RowSize = 5;
    private static final int ColSize = 7;
    private static final int SequenceToWin = 4;

    //Components
    private Colour player;             // which player is playing
    private final Colour[][] matrix;    // the bord of the game in the background

    //Constructor
    public fourInARowModel()
    {
        player = Colour.RED;
        matrix = new Colour[RowSize][ColSize];

        //initialize all the cell int matrix empty
        for(int i = 0; i < RowSize; i++)
        {
            for(int j = 0; j< ColSize; j++)
            {
                matrix[i][j] = Colour.EMPTY;
            }
        }
    }

    //search if one of the won from side to side
    private Colour searchToSides()
    {
        for(int row = 0; row < RowSize; row++)
        {
            int count = 0; //count sequence
            Colour tmpPlayer = Colour.EMPTY; //the last cell we checked

            for (int col = 0; col< ColSize; col++)
            {
                //if we found the same player in the next cell
                if(matrix[row][col] == tmpPlayer && tmpPlayer != Colour.EMPTY)
                {
                    count++;
                }
                //if we found the other player in the next cell
                else if(matrix[row][col] != Colour.EMPTY)
                {
                    count = 1;
                }
                //if we found the next cell empty
                else
                {
                    count = 0;
                }
                if(count == SequenceToWin) //someone won
                {
                    return tmpPlayer;
                }
                tmpPlayer = matrix[row][col];
            }
        }
        return Colour.EMPTY; //no one won
    }

    //search if one of the won from up to down
    private Colour searchUpAndDown()
    {
        for(int col = 0; col< ColSize; col++)
        {
            int count = 0; //count sequence
            Colour tmpPlayer = Colour.EMPTY; //the last cell we checked

            for (int row = 0; row < RowSize; row++)
            {
                //if we found the same player in the next cell
                if(matrix[row][col] == tmpPlayer && tmpPlayer != Colour.EMPTY)
                {
                    count++;
                }
                //if we found the other player in the next cell
                else if(matrix[row][col] != Colour.EMPTY)
                {
                    count = 1;
                }
                //if we found the next cell empty
                else
                {
                    count = 0;
                }
                if(count == SequenceToWin) //someone won
                {
                    return tmpPlayer;
                }
                tmpPlayer = matrix[row][col];
            }
        }
        return Colour.EMPTY; //no one won
    }

    //search if one of the won in obliquely from down to right
    private Colour searchObliquelyFromDownToRight()
    {
        for(int rowMemory = 0, colMemory = 0; rowMemory < RowSize || colMemory < ColSize; rowMemory++)
        {
            //search in the first half of the matrix
            int row = rowMemory;
            int col = colMemory;

            //search in the second half of the matrix
            if(rowMemory >= RowSize)
            {
                row = RowSize -1;
                colMemory++;
            }

            int count = 0; //count sequence
            Colour tmpPlayer = Colour.EMPTY; //the last cell we checked

            for(; row >= 0 && col < ColSize ; row--, col++)
            {
                //if we found the same player in the next cell
                if(matrix[row][col] == tmpPlayer && tmpPlayer != Colour.EMPTY)
                {
                    count++;
                }
                //if we found the other player in the next cell
                else if(matrix[row][col] != Colour.EMPTY)
                {
                    count = 1;
                }
                //if we found the next cell empty
                else
                {
                    count = 0;
                }
                if(count == SequenceToWin) //someone won
                {
                    return tmpPlayer;
                }
                tmpPlayer = matrix[row][col];
            }
        }

        return Colour.EMPTY; //no one won
    }

    //search if one of the won in obliquely from down to left
    private Colour searchObliquelyFromDownToLeft()
    {
        for(int rowMemory = 0, colMemory = ColSize -1; rowMemory < RowSize || colMemory >= 0; rowMemory++)
        {
            //search in the first half of the matrix
            int row = rowMemory;
            int col = colMemory;

            //search in the second half of the matrix
            if(rowMemory >= RowSize)
            {
                row = RowSize -1;
                colMemory--;
            }

            int count = 0; //count sequence
            Colour tmpPlayer = Colour.EMPTY; //the last cell we checked

            for(; row >= 0 && col >= 0 ; row--, col--)
            {
                //if we found the same player in the next cell
                if(matrix[row][col] == tmpPlayer && tmpPlayer != Colour.EMPTY)
                {
                    count++;
                }
                //if we found the other player in the next cell
                else if(matrix[row][col] != Colour.EMPTY)
                {
                    count = 1;
                }
                //if we found the next cell empty
                else
                {
                    count = 0;
                }
                if(count == SequenceToWin) //someone won
                {
                    return tmpPlayer;
                }
                tmpPlayer = matrix[row][col];
            }
        }

        return Colour.EMPTY; //no one won
    }

    //this function reset the game, make all the cell in the matrix empty
    public void clear()
    {
        for(int i = 0; i < RowSize; i++)
        {
            for(int j = 0; j< ColSize; j++)
            {
                matrix[i][j] = Colour.EMPTY;
            }
        }
        player = Colour.RED; //player red always begin
    }

    //insert a disk according to the column parameter
    public void insertDisk(int col)
    {
        for(int row = RowSize -1; row >= 0; row--)
        {
            if(matrix[row][col] == Colour.EMPTY)
            {
                if(player == Colour.RED)
                {
                    matrix[row][col] = Colour.RED;
                    player = Colour.BLUE;
                }
                else
                {
                    matrix[row][col] = Colour.BLUE;
                    player = Colour.RED;
                }
                return;
            }
        }
        throw new IllegalArgumentException("out of bond"); // if the column is already full.
    }

    //return the player who won the game. if no one won return EMPTY.
    public Colour whoWon()
    {
        if(searchToSides() != Colour.EMPTY)
        {
            return  searchToSides();
        }
        else if(searchUpAndDown() != Colour.EMPTY)
        {
            return searchUpAndDown();
        }
        else if (searchObliquelyFromDownToRight() != Colour.EMPTY)
        {
            return searchObliquelyFromDownToRight();
        }
        else if(searchObliquelyFromDownToLeft() != Colour.EMPTY)
        {
            return searchObliquelyFromDownToLeft();
        }
        return Colour.EMPTY;
    }

    public Colour getColourInCell(int row, int col)
    {
        return matrix[row][col];
    }
}
