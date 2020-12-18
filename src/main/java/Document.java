//Segregation principle

public class Document {

}

interface Machine{
    void print(Document d);
    void fax(Document d) throws Exception;
    void scan(Document d);
}

class MultifunctionPrinter implements Machine{

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class OldFashionPrinter implements Machine{


    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) throws Exception{
        throw new Exception();
    }

    @Override
    public void scan(Document d) {

    }
}

interface Printer{
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

class JustPrinter implements Printer{

    @Override
    public void print(Document d) {

    }
}

class PhotoCopier implements Printer, Scanner{

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }

    interface MultiFunctionDevice extends Printer, Scanner{ }

    class MultiFunctionMachine implements MultiFunctionDevice{
        @Override
        public void print(Document d) {

        }

        @Override
        public void scan(Document d) {

        }
    }

}
