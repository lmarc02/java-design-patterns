
//Substitution principle

public class Rectangular {
    protected int width, height;

    @Override
    public String toString() {
        return "Rectangular{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
    public Rectangular(){
    }

    public Rectangular(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea(){
        return width*height;
    }

    public boolean isSquare(){
        return width == height;
    }
}

class Square extends Rectangular{

    public Square(){}

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }

}



class RectangularFactory{

    public static Rectangular newRectangular(int width, int height){
        return new Rectangular(width, height);
    }
    public static Rectangular newSquare(int side){
        return new Rectangular(side, side);
    }
    public Rectangular rectangularFactory(Rectangular r, Boolean isSquare){
        if (isSquare){
            return RectangularFactory.newSquare(r.getWidth());
        }else {
            return RectangularFactory.newRectangular(r.getWidth(), r.getHeight());
        }

    }

}

class Demo2{
    static void useIt(Rectangular r){
        int width = r.getWidth();
        //r.setHeight(10);
        System.out.println("expect area of " + (width * r.getHeight()) + " got " + r.getArea());
    }

    public static void main(String[] args){
        Rectangular rc = new Rectangular(2, 3);
        useIt(rc);

        Rectangular sq = new Square();
        sq.setWidth(5);
        useIt(sq);

        RectangularFactory rf = new RectangularFactory();
        rf.rectangularFactory(sq, sq.isSquare());
        useIt(sq);

        rf.rectangularFactory(rc, rc.isSquare());
        useIt(rc);




    }
}

