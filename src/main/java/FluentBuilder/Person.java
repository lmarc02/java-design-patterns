package FluentBuilder;



// builder inheritance with recursive generics

class Person
{
    public String name;

    public String position;

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<S extends PersonBuilder<S>>
{
    protected Person person = new Person();

    // critical to return SELF here
    public S withName(String name)
    {
        person.name = name;
        return self();
    }

    protected S self()
    {
        // unchecked cast, but actually safe
        // proof: try sticking a non-PersonBuilder
        //        as SELF parameter; it won't work!
        return (S) this;
    }

    public Person build()
    {
        return person;
    }
}

class EmployeeBuilder
        extends PersonBuilder<EmployeeBuilder>
{
    public EmployeeBuilder worksAs(String position)
    {
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self()
    {
        return this;
    }
}

class RecursiveGenericsDemo
{
    public static void main(String[] args)
    {
        EmployeeBuilder eb = new EmployeeBuilder()
                .withName("Dmitri")
                .worksAs("Quantitative Analyst");
        System.out.println(eb.build());
    }
}