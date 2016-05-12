package logic;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.List;
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
                    extractFile(zipIn, filePath);
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
        }

    public void printContent(String fileName){
        File file = new File(fileName);
        List<String> lines=null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        }catch(IOException e){
            e.printStackTrace();
        }
        for(String line: lines){
            System.out.println(line);
            String keepAlex = CharMatcher.anyOf(“alex”).retainFrom(someOtherString);

        }
    }
}
