package fourInARowModelPackage;

import fourInARowModelPackage.Colour;

public interface IfourInARowModel
{
    void clear();

    void insertDisk(int col);

    Colour whoWon();

    Colour getColourInCell(int row, int col);
}
