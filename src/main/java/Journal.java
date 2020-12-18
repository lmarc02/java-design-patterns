import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

// Responsibility principle

public class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text){
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index){
        entries.remove(index);
    }

    public String toString(){
        return String.join(System.lineSeparator(), entries);
    }


    public void save(String filename) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(filename)){
            out.println();
        }
    }

}

class Persistence{

    public void saveToFile(Journal journal,
                           String filename,
                           boolean overwrite){

        if (overwrite || new File(filename).exists()) {
            try(PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

class Demo{
    public static void main(String[] args)throws Exception{
        Journal j = new Journal();
        j.addEntry("i ate a bug");
        j.addEntry("i had 2 coffees");
        System.out.println(j);

        Persistence p = new Persistence();

        p.saveToFile(j, "journal.txt", false);



    }
}
