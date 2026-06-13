class Computer {
    private String cpu;
    private int ram;
    private int storage;
    private boolean hasGPU;
    
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGPU = builder.hasGPU;
    }
    
    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + "GB, Storage=" + storage + "GB, GPU=" + hasGPU + "]";
    }
    
    public static class Builder {
        private String cpu;
        private int ram;
        private int storage;
        private boolean hasGPU;
        
        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }
        
        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }
        
        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }
        
        public Builder hasGPU(boolean hasGPU) {
            this.hasGPU = hasGPU;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}

public class ComputerBuilder {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
            .cpu("Intel i9")
            .ram(32)
            .storage(1000)
            .hasGPU(true)
            .build();
        
        System.out.println(gamingPC);
    }
}
