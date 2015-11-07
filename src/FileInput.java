import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

/**
 * Created by Georgi on 10/31/2015.
 */
public class FileInput {

    private TablePart[][] table=null;
    private int R;
    private int K;
    private int N;

    public int getN() {
        return N;
    }

    public int getR() {
        return R;

    }

    public int getK() {
        return K;
    }

    public TablePart[][] getTable() {
        return table;
    }

    public FileInput(){
        input();
    }

    private void input(){
        Scanner scanner = new Scanner(System.in);
        Scanner fileScanner=null;
        System.out.print("Enter file directory \n default is Queen \n The queens will be written in the file with the name file \n");
        //String directory = scanner.nextLine();
        File file = new File("Queen");
        try {
             fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double size = Math.sqrt(fileScanner.nextDouble());
         N = (int)Math.round(size);
         R=fileScanner.nextInt();
         K=fileScanner.nextInt();
        table = new TablePart[N][N];
        for (int i = 0; i < N ; i++) {
            for (int j = 0; j < N ; j++) {
                table[i][j] = new TablePart();
                table[i][j].setNumber(fileScanner.nextInt());
                System.out.print(table[i][j].getNumber() + " ");
            }
            System.out.println();
        }
        fileScanner.close();
    }



}
