interface SortStrategy {
    void sort(int[] data);
}

class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting using Bubble Sort");
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
}

class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting using Quick Sort");
        quickSort(data, 0, data.length - 1);
    }
    
    private void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int pi = partition(data, low, high);
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }
    
    private int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (data[j] < pivot) {
                i++;
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        int temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;
        return i + 1;
    }
}

class Context {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void executeSort(int[] data) {
        strategy.sort(data);
    }
}

public class SortingStrategy {
    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        
        Context context = new Context();
        
        context.setStrategy(new BubbleSortStrategy());
        context.executeSort(data.clone());
        
        context.setStrategy(new QuickSortStrategy());
        context.executeSort(data.clone());
    }
}
