import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Georgi on 11/6/2015.
 */
public class SearchQueen {
    private boolean addQueen=true;
    private ArrayList<Queen> queenList;
    private TablePart[][] table;
    private int size;
    private int R;
    private int K;
    private int resultForTablePart=0;
    private int tempK=K;
    private boolean returnV=false;
    private Map<Integer,Integer> map = new HashMap<>();
    private boolean attack=false;

    private void canAddQueen(){
        FileInput fileInput = new FileInput();
        table= fileInput.getTable();
        R=fileInput.getR();
        K=fileInput.getK();
        queenList = new ArrayList<>();

        while(addQueen){
          startSearch(fileInput.getN());

        }
    }

    private void startSearch(int N){
        size=N;
        Queen max = new Queen();
        boolean queenIsAdd = false;
        int result=0;
        int tempResult;
        int tempResultNumber=0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempResult = range(j,i);
                returnV=false;
                attack=false;
                //System.out.println(tempResult);
                if(tempResult > result){
                    result = tempResult;
                    //System.out.println("result " + result);
                    max.setX(j);
                    max.setY(i);
                    max.setResult(result);
                    //System.out.println(max.getResult() + " max");
                    queenIsAdd = true;
                    tempResultNumber=tempK;

                }
                tempK=K;
            }
        }

        if(!queenIsAdd){
            addQueen=false;
        }else {
            table[max.getY()][max.getX()].setHasQueen(true);
            System.out.println(max.getResult() + " max");
            queenList.add(max);
            K=tempResultNumber;

        }
    }

    private int range(int x , int y){
        if(table[y][x].isHasQueen()){
            return 0;
        }

        int row = x;
        int col = y;
        map.put(table[col][row].getNumber(), 4);
         System.out.print("Y:" + col + " x:" + row + " " + table[col][row].getNumber() + " " + "\n");
        resultForTablePart = table[y][x].getNumber();
        if(!returnV){
        operation(row,col,row, col-1,0, -1,R);} //up
        if(!returnV){
        operation(row,col,row, col+1, 0, +1,R);} //down
        if(!returnV){
        operation(row,col,row-1, col,-1, 0,R);} //left
        if(!returnV){
        operation(row,col,row+1, col,+1, 0,R);} //right
        if(!returnV){
        operation(row,col,row+1, col-1,+1, -1,R);} //upRight
        if(!returnV){
        operation(row,col,row+1, col+1,+1, +1,R);} //downRight
        if(!returnV){
        operation(row,col,row-1, col-1, -1, -1, R);} //upLeft
        if(!returnV){
        operation(row, col, row - 1, col + 1, -1, +1, R);} //downLeft

         System.out.println("new loop \n");

        int tempValue=0;
        for(Map.Entry<Integer,Integer> mapE: map.entrySet()){
            if(tempValue < mapE.getValue()){
                tempValue = mapE.getValue();
            }
        }
        //System.out.print(tempValue + "   map");
        int result = resultForTablePart * tempValue ;
        map.clear();
        resultForTablePart=0;
        if(!returnV){return result;}
        else {return 0;}
    }

    private void operation(int startX,int startY,int x,int y,int xAdd,int yAdd,int R){

        if ((x<0) || (y<0) || (y>=size) || (x >= size) ||(R == 0)) {
            return;
        }else {
            if(table[y][x].isHasQueen()){
                if(tempK>=1){
                    attackQueen(startX,startY,x,y);
                    System.out.print("end2");
                } else {
                    System.out.print("end1");
                    returnV=true;
                    return;
                }
            }

            try{
                //System.out.println(table[y][x].getNumber() + " one" );
                resultForTablePart += table[y][x].getNumber();
                System.out.println(resultForTablePart + " result");
                Integer count = map.get(table[y][x].getNumber());
                if(count == null){
                    count = 0;
                }
                map.put(table[y][x].getNumber(),count+1);

            } catch (ArrayIndexOutOfBoundsException e){
                System.out.print("err");
                return;
            }

            System.out.println(R + " is some");
            operation(startX, startY, x + xAdd, y + yAdd, xAdd, yAdd, R-1);
            System.out.println("new R" );
        }
    }

    private void attackQueen(int startX,int startY,int x,int y){
        int row=x;
        int col=y;


        if(!attack)
            attackOperation(startX, startY, row, col-1, 0, -1,R); //up
        if(!attack)
            attackOperation(startX, startY, row, col+1, 0, +1,R); //down
        if(!attack)
            attackOperation(startX, startY, row-1, col, -1, 0,R); //left
        if(!attack)
            attackOperation(startX, startY, row+1, col, +1, 0,R); //right
        if(!attack)
            attackOperation(startX, startY, row+1, col-1, +1, -1,R); //upRight
        if(!attack)
            attackOperation(startX, startY, row+1, col+1, +1, +1,R); //downRight
        if(!attack)
            attackOperation(startX, startY, row-1, col-1, -1, -1,R); //upLeft
        if(!attack)
            attackOperation(startX, startY, row-1, col+1, -1 , +1,R); //downLeft

    }

    private void attackOperation(int startX,int startY,int x,int y,int xAdd,int yAdd,int R){
        if ((x<0) || (y<0) || (y>=size) || (x >= size) ||(R == 0)) {
            return;
        }

        if(x == startX && y == startY){
            System.out.println("find \n\n\\n\n\n\n");
            tempK--;
            attack=true;
            return;
        }
        attackOperation(startX, startY, x + xAdd, y + yAdd, xAdd, yAdd, R-1);
        return;

    }

    public static void main(String[] args){
        PrintStream p=null;
        SearchQueen s = new SearchQueen();
        s.canAddQueen();
        int x;
         int y;


        try {
            p = new PrintStream("file.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int result=0;
        for (int i = 0; i < s.queenList.size() ; i++) {
        System.out.println(s.queenList.get(i).getResult());
            x=s.queenList.get(i).getX()+1;
            y=s.queenList.get(i).getY()+1;


            p.println(x + " " + y);
            result += s.queenList.get(i).getResult();
        }
        p.print(result);
        System.out.println("-----------");
        System.out.println(result);
        System.out.println("-----------");

        p.close();
        System.out.println("job done");
    }

}
