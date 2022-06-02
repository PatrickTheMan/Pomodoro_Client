package Domain;

public class Project {

    private int id;
    private String name;
    private int order;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public Project(int id,String name, int order){
        this.id = id;
        this.name = name;
        this.order = order;
    }

}
