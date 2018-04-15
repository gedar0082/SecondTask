import java.io.*;

public class PackRLE {
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));

        int symbol = input.read();
        while (symbol != -1) {
            char c = (char) symbol;
            symbol = input.read();
            System.out.println(c);
        }
    }
}
