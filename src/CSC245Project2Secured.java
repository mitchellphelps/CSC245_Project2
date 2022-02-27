/*
    Author: Mitchell Phelps
    Date: February 27, 2022
    Summary: Program will print a list of emails in a text file.
 */

// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class definition
public class CSC245Project2Secured {

    public static void main(String[] args) throws IOException {

        // Canonicalize path name to check for proper file.
        File file = new File("C:\\CSC245Project2Secured\\src\\" + args[0]);
        String canonicalPath = file.getCanonicalPath();
        if (!canonicalPath.equals("C:\\CSC245Project2Secured\\src\\emails.txt")) {
            System.out.println("Invalid File");
            System.exit(0);
        }

        // Read the filename from the command line argument
        String filename = args[0];
        BufferedReader inputStream = null;

        String fileLine;

        try {
            // Create a new inputStream to read the file line by line.
            inputStream = new BufferedReader(new FileReader(filename));

            System.out.println("Email Addresses:");
            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {

                // Normalize output.
                fileLine = Normalizer.normalize(fileLine, Normalizer.Form.NFKC);

                // Validate output.
                Pattern pattern = Pattern.compile("@{2,}|[<>//$//?//%]");
                Matcher matcher = pattern.matcher(fileLine);
                // If an invalid output is found, skip it.
                if (matcher.find()) {
                    continue;
                } else {
                    System.out.println(fileLine);
                }
            }
        // Catch an IO exception if the file cannot open.
        } catch (IOException io) {
            // Error message. Does not reveal sensitive information.
            System.out.println("Invalid File");
        } finally {
            // Need another catch for closing
            // the streams
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException io) {
                // Error message. Does not reveal sensitive information.
                System.out.println("Invalid File");
            }

        }
    }

}
