package pl.jj.util.access;

public class SimplePojo {

    @JField("myName")
    private String name;

    @JField("myLastName")
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
