import java.io.Serializable;

public class RingBuffer implements Serializable {
    private static final long serialVersionUID = 0x52696E67;
    private int size = 10;
    private int[] buffer = new int[size];
    private int head = 0;
    private int tail = 0;

    RingBuffer() {
        //TODO: inicializar com um ou mais valores
    }

    public int peek() {
        return buffer[head];
    }

    public int getAtIdx(int idx) {
        return buffer[idx];
    }

    public int getSize() {
		return size;
	}

	public int[] getBuffer() {
		return buffer;
	}

	public int getHead() {
		return head;
	}

	public int getTail() {
		return tail;
	}

	public int deque() {
        return buffer[head++];
    }

    public void enqueue(int value) {
        buffer[tail] = value;

        tail = ++tail % size;
        if(tail == head) {
            //TODO: resize
        }
    }

}
