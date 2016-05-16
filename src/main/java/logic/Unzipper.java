package logic;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by V3790147 on 5/12/2016.
 */
public class Unzipper {

        private static final int BUFFER_SIZE = 4096;
        String destDirectory = ".";

        public void unzip(String zipFilePath) throws IOException {
            File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // if the entry is a file, extracts it
                    extractFile(zipIn, "log.txt");
                } else {
                    // if the entry is a directory, make the directory
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            zipIn.close();
        }
        private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
            bos.close();
            printContent(filePath);
            System.out.println("Entry file " + filePath );
        }

    public void printContent(String fileName){
        String logEntryPattern =
                "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
        File file = new File(fileName);
        List<String> lines=null;
        int index = 0;
        Iterable<String> result = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);

           /* Pattern p = Pattern.compile(logEntryPattern);

            Matcher matcher = p.matcher(lines.get(index));

            *//*if (!matcher.matches( ) ||

                    9 != matcher.groupCount( )) {

                System.err.println("Bad log entry (or problem with regex?):");

                return;

            }*//*

            System.out.println("IP Address: " + matcher.group(1));

            System.out.println("Date&Time: " + matcher.group(4));

            System.out.println("Request: " + matcher.group(5));

            System.out.println("Response: " + matcher.group(6));

            System.out.println("Bytes Sent: " + matcher.group(7));

            if (!matcher.group(8).equals("-"))

                System.out.println("Referer: " + matcher.group(8));

            System.out.println("Browser: " + matcher.group(9));*/

            System.out.println(lines.get(index));
            index++;
        }catch(IOException e){
        e.printStackTrace();
    }
    }
}
