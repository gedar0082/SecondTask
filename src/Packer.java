import java.io.*;

public class Packer {
    private boolean packFlag;
    private boolean unpackFlag;
    private String outputFile;
    private String inputFile;
    BufferedWriter out;
    BufferedReader in;
    public Packer(boolean packFlag, boolean unpackFlag, String outputFile, String inputFile) {
        this.packFlag = packFlag;
        this.unpackFlag = unpackFlag;
        this.outputFile = outputFile;
        this.inputFile = inputFile;
    }

    public void start() throws IOException {
        in = new BufferedReader(new FileReader(inputFile));
        if (outputFile == null) {
            out = new BufferedWriter(new FileWriter(inputFile + ".txt"));
        } else {
            out = new BufferedWriter(new FileWriter(outputFile));
        }
        if (packFlag && unpackFlag) throw new IllegalArgumentException();
        if (packFlag) {
            pack();
        } else if (unpackFlag) {
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
            if(saveSymbol == symbol && symbol > 32){
                count++;
            }
            else{
                if(count > 1){
                    out.append(count + "");
                    out.append((char)saveSymbol + "");
                    count = 1;
                }else{
                    out.append((char)saveSymbol + "");
                    count = 1;
                }
            }
        }
        in.close();
        out.close();
    }

    private void unpack() throws IOException {
        int symbol = in.read();
        int count = 0;
        while (symbol != -1){
            if(symbol >47 & symbol< 58){
                count = count * 10 + symbol - 48;
            }else{
                if(symbol < 33){
                    out.append((char)symbol);
                    count = 0;
                }else{
                    if(count == 0) count = 1;
                    for(int c = count; c >0; c--){
                        out.append((char)symbol);
                    }
                    count = 0;
                }
            }
            symbol = in.read();
        }
        in.close();
        out.close();
    }
}







