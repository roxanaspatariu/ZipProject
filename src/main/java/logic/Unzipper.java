package logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

/**
 * Created by V3790147 on 5/12/2016.
 */

public class Unzipper implements Runnable{

    InputStream inputStream;
    Service service;

    public Unzipper(InputStream inputStream, Service service) {
        this.inputStream = inputStream;
        this.service = service;
    }

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new GZIPInputStream(inputStream)));
            String lines = "";
            while ((lines = br.readLine()) != null) {


                StringTokenizer matcher = new StringTokenizer(lines, " \t");

                String ip = matcher.nextToken();

                matcher.nextToken();
                matcher.nextToken("[");
                String date = matcher.nextToken(" \t");
                matcher.nextToken("\"");
                String request = matcher.nextToken();
                matcher.nextToken(" \t");

                String response = matcher.nextToken(" \t");
                String bytes = matcher.nextToken(" \t");
                matcher.nextToken("\"");
                String referer = matcher.nextToken();
                matcher.nextToken();
//                String browser = matcher.nextToken();

                //System.out.println( ip + " " + date  + " " + request + " "  + response + " "  + bytes + " "  + browser );
                service.save(new LogEntity(ip, date, response, bytes));
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long difference = endTime - beginTime;
        System.out.println(Thread.currentThread().getName() + "lasted for " + String.valueOf(difference) + " sec");
    }


}