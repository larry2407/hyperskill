import java.util.Scanner;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        return this.algorithm.select(persons);
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {
    private int step;
    public TakePersonsWithStepAlgorithm(int step) {
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        int l = persons.length;
        int lSelected = (l-1)/this.step +1;
        Person[] selected = null;
        if(l>0) {
            selected = new Person[lSelected];
        }else{
            selected = new Person[0];
        }

        for(int i=0; i<lSelected; i++){
            selected[i] = persons[i*this.step];
        }

        return selected;
    }
}


class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {
    private int count;
    public TakeLastPersonsAlgorithm(int count) {
       this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        int l = persons.length;
        Person[] selected = null;
        if(l==0){
            selected = new Person[0];
        }else if(l<=this.count){
            selected = persons;
        }else{
            selected = new Person[this.count];
            for(int i=0; i<this.count; i++){
                selected[i] = persons[l-this.count+i];
            }
        }
        return selected;
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String args[]) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}