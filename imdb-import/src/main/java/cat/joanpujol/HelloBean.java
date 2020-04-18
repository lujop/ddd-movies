package cat.joanpujol;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloBean {

    public String hello() {
        return "hello";
    }
}
