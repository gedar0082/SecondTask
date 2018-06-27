import java.io.*;

public class Packer {
    private boolean flag;
    private File outputFile;
    private File inputFile;
    BufferedWriter out;
    BufferedReader in;
    public Packer(boolean flag, File outputFile, File inputFile) {
        this.flag = flag;
        this.outputFile = outputFile;
        this.inputFile = inputFile;
    }

    public void start() throws IOException {
        in = new BufferedReader(new FileReader(inputFile));
        if (outputFile == null) {
            if(flag){
                out = new BufferedWriter(new FileWriter("packed" + inputFile));
            }else{
                out = new BufferedWriter(new FileWriter("unpacked" + inputFile));
            }
        } else {
            out = new BufferedWriter(new FileWriter(outputFile));
        }
        if (flag) {
            pack();
        } else if (!flag) {
            unpack();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void pack() throws IOException {
        int symbol = in.read();
        int saveSymbol;
        int count = 1;
        while (symbol != -1) {
            saveSymbol = symbol;
            symbol = in.read();
            if(saveSymbol == symbol){
                count++;
                if(count > 255){
                    out.write(count);
                    out.write(saveSymbol);
                    count = 0;
                }
            }
            else{
                out.write(count);
                out.write(saveSymbol);
                count = 1;
            }
        }
        in.close();
        out.close();
    }

    private void unpack() throws IOException {
        int symbol = in.read();
        int saveSymbol;
        while (symbol != -1){
            saveSymbol = symbol;
            symbol = in.read();
            for(int c = saveSymbol; c > 0; c--){
                out.append((char)symbol);
            }
            symbol = in.read();
        }
        in.close();
        out.close();
    }
}







