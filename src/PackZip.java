import java.io.*;

public class PackZip {


    public static void main(String[] args)throws Exception {
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        File out = new File("C:\\Users\\gedar\\IdeaProjects\\SecondTask");
        BufferedWriter output = new BufferedWriter(new FileWriter("out.txt"));
        int symbol = input.read();
        int count = 1;
        int oldSymbol = 0;
        while (symbol != -1) {
            oldSymbol = symbol;
            if(symbol == oldSymbol){
                count++;
            }else{
                output.append((char)symbol);
                count = 1;
            }
            symbol = input.read();
            System.out.println(symbol);
        }
        input.close();
        output.close();
    }


}







