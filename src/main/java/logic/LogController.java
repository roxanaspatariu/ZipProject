package logic;

import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * Created by V3790147 on 5/16/2016.
 */
@RestController
@RequestMapping(value = "/")
public class LogController {

    @Autowired
    LogRepository logRepository;

    @RequestMapping(value = "/log/{id}", method = RequestMethod.GET)

    public String findLogById(@PathVariable Long id, Model model) {
        model.addAttribute("log", logRepository.findOne(id));
        System.out.println("Repo value " + logRepository.findOne(id).getId());
        System.out.println("Log entry " + logRepository.findOne(id).toString());
        return "findLogById";
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public String logsList(Model model) {
        model.addAttribute("logs", logRepository.findAll());
        return "findAll";
    }


    @RequestMapping("/get")
    public
    @ResponseBody
    String getAll() {
        String result = "";

        for (LogEntity e : logRepository.findAll()) {
            result += e.toString();
            result += "/n";
        }

        return result;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("datafile") MultipartFile[] uploadFiles) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            for (MultipartFile file : uploadFiles) {
                Unzipper unzipper = new Unzipper(file.getInputStream(), logRepository);
                unzipper.getLogRepository();

                executor.execute(unzipper);
            }

            executor.shutdown();
            System.out.println("Finished all threads");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "You have successfully uploaded your file!";
    }

}
