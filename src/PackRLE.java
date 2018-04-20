import java.io.*;
import org.kohsuke.args4j.*;
public class PackRLE {
    @Option(name = "-z", usage = "Packing")
    private boolean packFlag;

    @Option(name="-u", usage = "Unpacking")
    private boolean unpackFlag;

    @Option(name="-out", usage = "Output file")
    private String outputFile;

    @Argument(usage = "Input file", required = true)
    private String inputFile;

    public static void main(String[] args) {
        new PackRLE().launch(args);
    }
    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
        }
        Packer packer = new Packer(packFlag, unpackFlag, outputFile, inputFile);
        try{
            packer.start();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }


    }
}
