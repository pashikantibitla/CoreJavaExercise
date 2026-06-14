interface Printable {
    void print();
}

interface Command {
    void execute();
}

public class EnumImplementingInterface implements Printable, Command {
    enum Action implements Printable, Command {
        START {
            public void print() {
                System.out.println("Starting...");
            }

            public void execute() {
                System.out.println("Execute start");
            }
        },
        STOP {
            public void print() {
                System.out.println("Stopping...");
            }

            public void execute() {
                System.out.println("Execute stop");
            }
        },
        PAUSE {
            public void print() {
                System.out.println("Pausing...");
            }

            public void execute() {
                System.out.println("Execute pause");
            }
        }
    }

    public static void main(String[] args) {
        for (Action action : Action.values()) {
            action.print();
            action.execute();
            System.out.println();
        }
    }

    public void print() {}
    public void execute() {}
}
