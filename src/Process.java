import java.io.Serializable;

public abstract class Process implements Serializable {
    private static final long serialVersionUID = 0x4545;
    private long pid;

    public void execute() {}
}
