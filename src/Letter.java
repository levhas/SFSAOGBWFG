import java.util.ArrayList;

public class Letter {
    private static int numOfLetters;



    private final int id;
    private final String letter;
    private ArrayList<Integer> neighbours;
    private int currentNeighbourIndex;

    public Letter(String letter) {
        this.letter = letter;
        id = numOfLetters;
        numOfLetters++;
        neighbours = new ArrayList<>();
        currentNeighbourIndex = 0;
    }

    public void setNeighbours(int size) {
        int x = id % size;
        int y = (int) (id / size);
        for (int iy = y - 1; iy <= y + 1 && iy < size; iy++) {
            for (int ix = x - 1; ix <= x + 1; ix++) {
                int cx = Math.min(Math.max(ix, 0), size-1);
                int cy = Math.min((Math.max(iy, 0) * size), size*size-1);
                int idx = cx + cy;
                if(!neighbours.contains(idx) && idx != id && idx <= 15) {
                    neighbours.add(idx);
                }
            }
        }
    }

    public int getNextIndex(){
        var retIndex = currentNeighbourIndex;
        currentNeighbourIndex++;
        try{
            return neighbours.get(retIndex);
        } catch (IndexOutOfBoundsException e) {
            currentNeighbourIndex = 0;
            return -1;
        }
    }

    public String get(){
        return letter;
    }

    @Override
    public String toString() {
        return letter;
    }
}
