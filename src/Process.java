import java.io.Serializable;

//Superclasse para os processos
public abstract class Process implements Serializable {
    private static final long serialVersionUID = 0x4545;
    protected int pid;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public void execute() {
    }


}
