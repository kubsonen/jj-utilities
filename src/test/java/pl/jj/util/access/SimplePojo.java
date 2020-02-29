package pl.jj.util.access;

import java.util.List;

public class SimplePojo {

    @JField("myName")
    private String name;

    @JField("myLastName")
    private String lastName;

    @JField("simplePojos")
    private List<SimplePojo> sps;

    private Boolean pojo;

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

    public Boolean isPojo() {
        return pojo;
    }

    public void setPojo(Boolean pojo) {
        this.pojo = pojo;
    }

    public List<SimplePojo> getSps() {
        return sps;
    }

    public void setSps(List<SimplePojo> sps) {
        this.sps = sps;
    }
}
