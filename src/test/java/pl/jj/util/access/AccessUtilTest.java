package pl.jj.util.access;

import org.junit.Test;

public class AccessUtilTest {

    @Test
    public void initialize() {
        SimplePojo sp = new SimplePojo();
        new AccessUtil(sp);
    }

}
