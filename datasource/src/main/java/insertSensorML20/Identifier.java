package insertSensorML20;

/**
 * Created by Zil on 2016/9/22.
 */
public class Identifier {

    private Term term;
    private String prefix;
    private String name;

    public Identifier(){

    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
