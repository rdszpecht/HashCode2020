import java.io.*;
import java.util.*;

public class HashCode2020 {
    private static BufferedReader systemReader;
    private static ArrayList<Library> libraries = new ArrayList<>();
    private static ArrayList<Library> chosenLibs = new ArrayList<>();
    private static int[] bookScores;
    private static int totalTime;
    
    public static void main(String[] args) throws IOException {
        readFile();
        
        Library lib;
        boolean keepDoing = true; 
        int syncingTimeSpent = 0;
        
        while(keepDoing){
            Collections.sort(libraries);
            lib = libraries.get(0);
            chosenLibs.add(lib);
            libraries.remove(lib);
            
            lib.sortBooks(bookScores);
            bookScores = lib.actScores(bookScores);            
            
            libraries.forEach((library) -> {
                library.calcValue(bookScores);
            });
            
            syncingTimeSpent += lib.getDaysForSync();
            if((syncingTimeSpent > totalTime) || (libraries.isEmpty())){
                keepDoing = false;
            }
        }
        
        writeFile();
    }

    public static int[] getBookScores() {
        return bookScores;
    }
    
    private static void writeFile() throws FileNotFoundException, IOException {
        //implement printing only the chosen libraries
        
        File file = new File("C:\\Users\\Lenovo Legion\\Desktop\\HashCode\\2020\\output.txt");
        OutputStream outputStream = new FileOutputStream(file);
        String toWrite = "";
        
        if (!file.exists()) {
            file.createNewFile();
        }
        
        // Total number of libraries
        toWrite += Integer.toString(chosenLibs.size()) + "\n";
        
        for (Library library : chosenLibs) {
            toWrite += library.getId() + " " + library.getBooks().size() + "\n";            
            toWrite += library.getBooksAsString() + "\n";           
        }
        
        outputStream.write(toWrite.getBytes());
        outputStream.close();
    }
    
    private static void readFile() throws FileNotFoundException, IOException{
        BufferedReader reader;
        String line = ""; 
        String nextLine = "";
        String[] aLine; 
        String[] aNextLine;
        int id = 0;
        
        //Read from file
        systemReader = new BufferedReader(new InputStreamReader(System.in));        
        System.out.println("Type the filename:");
        String filename = systemReader.readLine();
        File file = new File("C:\\Users\\Lenovo Legion\\Desktop\\HashCode\\2020\\" + filename);        
        reader = new BufferedReader(new FileReader(file));
        
        //Treatment of first two lines
        String line1 = reader.readLine(); 
        totalTime = Integer.parseInt(line1.split(" ")[2]);
        String[] line2 = reader.readLine().split(" ");
        bookScores = new int[line2.length];
        for (int i = 0; i < line2.length; i++) {
            bookScores[i] = Integer.parseInt(line2[i]);
        }
        //Following lines
        while(line!=null){
            try{
                line = reader.readLine();                
                nextLine = reader.readLine();
                
                aLine = line.split(" ");
                aNextLine = nextLine.split(" ");
                
                ArrayList<Book> books = new ArrayList<>();
                Library library;

                for (int i = 0; i < aNextLine.length; i++) {                    
                    books.add(new Book(Integer.parseInt(aNextLine[i])));
                }
                library = new Library(Integer.parseInt(aLine[0]), Integer.parseInt(aLine[1]), Integer.parseInt(aLine[2]), id, books);                               
                library.calcValue(bookScores);
                libraries.add(library);
                id++;
            }catch(Exception e){}        
        }
        reader.close();
    }
    
}
