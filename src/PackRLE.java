import java.io.*;
import org.kohsuke.args4j.*;
public class PackRLE {
    @Option(name = "-z",forbids = "-u", usage = "Packing")
    private boolean flag;


    @Option(name="-out", usage = "Output file")
    private File outputFile;

    @Argument(usage = "Input file", required = true)
    private File inputFile;

    public static void main(String[] args) {
        new PackRLE().launch(args);
    }
    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.exit(0);

        }

        /*if (packFlag) {
            flag = true;
        } else if (unpackFlag) {
            flag = false;
        } else {
            System.err.println("ERROR");
            System.exit(0);
        }*/
        Packer packer = new Packer(flag, outputFile, inputFile);
        try{
            packer.start();
        }catch(IOException e){
            System.err.println(e.getMessage());
            System.exit(0);

        }
    }
}
