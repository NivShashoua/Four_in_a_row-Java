import fourInARowControllerPackage.IfourInARowController;
import fourInARowControllerPackage.fourInARowController;
import fourInARowModelPackage.IfourInARowModel;
import fourInARowModelPackage.fourInARowModel;
import fourInARowViewPackage.IfourInARowView;
import fourInARowViewPackage.fourInARowView;

public class Question1Main
{
    public static void main(String[] args)
    {
        IfourInARowModel model = new fourInARowModel();
        IfourInARowView view = new fourInARowView(model);
        IfourInARowController controller = new fourInARowController(model, view);
    }
}
