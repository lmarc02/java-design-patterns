import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javafx.scene.Parent;


enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person{
    String name;

    public Person(String name){
        this.name = name;
    }
}

interface RelationshipBrowser{
    List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipBrowser{
    private List<Triplet<Person, Relationship,Person>> relations = new ArrayList<>();
    public void addParentChild(Person parent, Person child){
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {// low-level
        return relations;
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
       return relations.stream().filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT).map(Triplet::getValue2).collect(
               Collectors.toList());
    }
}

class Research{
//    public Research(Relationships relationships){// high-level
//        List<Triplet<Person, Relationship,Person>> relations = relationships.getRelations();
//        relations.stream().filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT).forEach(ch -> System.out
//                .println("John has a child called " + ch.getValue2().name));
//    }

    public Research(RelationshipBrowser browser){
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child : children) { System.out.println("John has a child called "+ child.name); }
    }
}

class Demo4{
    public static void main(String[] args){
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentChild(parent, child1);
        relationships.addParentChild(parent, child2);

        new Research(relationships);
    }
}