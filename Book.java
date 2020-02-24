public class Book implements Comparable<Book>{
    private int id;
    private int value;

    public Book(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public int compareTo(Book o) {
        int toRet = 0;
        if (this.value < o.getValue()){
            toRet = -1;
        }else{
            if(this.value > o.getValue()){
                toRet = 1;
            }
        }
        return toRet;
    }
    
}
