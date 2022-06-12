package Domain;

/**
 * @author Patrick G. Schemel
 */
public class Project {

    //region [Variables]

    private int id;
    private String name;
    private int order;

    //endregion

    //region [Normal Getters & Setters]

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    //endregion

    //region [Constructor]

    public Project(int id,String name, int order){
        this.id = id;
        this.name = name;
        this.order = order;
    }

    //endregion

}
