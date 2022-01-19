package Coursework;

public class Text {

    public void getOptions(){
        System.out.println("\nEnter option:");
        System.out.println("1.Add new treatment");
        System.out.println("2.Delete existing treatment");
        System.out.println("3.Change existing treatment");
        System.out.println("4.Show all treatment plans");
        System.out.println("5.Get specific result");
        System.out.println("6.Show all possible results");
        System.out.println("7.Sort all possible results");
        System.out.println("8.Uniform Cost Search");
        System.out.println("9.Exit and save data");
    }

    public void getChoices(){
        System.out.println("Enter choice:");
        System.out.println("1.Type");
        System.out.println("2.Name");
        System.out.println("3.TSS");
        System.out.println("4.COD");
        System.out.println("5.BOD");
        System.out.println("6.Area");
        System.out.println("7.Energy");
    }

    public void getSortType(){
        System.out.println("Enter result to be sorted:");
        System.out.println("1.TSS");
        System.out.println("2.BOD");
        System.out.println("3.COD");
        System.out.println("4.Cost");
    }

    public void getSortOrder(){
        System.out.println("Enter order to be sorted:");
        System.out.println("1.Ascending");
        System.out.println("2.Descending");
    }

    public void getStandard(){
        System.out.println("Enter wastewater standard:");
        System.out.println("0.No Standard");
        System.out.println("1.Standard A");
        System.out.println("2.Standard B");
    }

    public void getHeader(){
        System.out.println("Treatment Types");
        System.out.format("%-15S %-30S %-30S %-50S %-20S %-4S %-5S %-5S %-5S\n", "Preliminary", "Chemical", "Biological", "Tertiary", "Sludge", "TSS","COD","BOD","Cost");
    }

    public void getTreatmentText(String text){
        System.out.println("Enter treatment " + text +":");
    }

    public void getInitialText(String text){
        System.out.println("Enter initial " + text +":");
    }

    public void getSortText(String text){
        System.out.println("Enter sort " + text +":");
    }

    public void invalidText(){
        System.out.println("Invalid input.");
    }
}
