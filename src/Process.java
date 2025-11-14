import java.io.Serializable;

public class Process implements Serializable {
    private static final long serialVersionUID = 0x4545;
    protected int pid;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "Superclasse";
    }

    public void execute() {
        System.out.println("Deu errado e executou a superclasse");
    }


}
