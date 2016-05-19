package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by V3790147 on 5/16/2016.
 */
@RestController
@RequestMapping(value = "/")
public class LogController {

    @Autowired
    Service service;

    @RequestMapping(value = "/log/{id}", method = RequestMethod.GET)
    public String findLogById(@PathVariable Long id) {
       return service.findById(id).toString();
    }

    @RequestMapping(value = "/{ip}/print", method = RequestMethod.GET)
    public String findLogByIp(@PathVariable String ip) {
        String result = "<html><head></head><body>";
        for (LogEntity e : service.findByIp(ip)) {
            result += e.toString();
            result +="<br>";
        }
        result+="</body></html>";
        return result;
    }
    @RequestMapping(value = "/{ip}/bytes", method = RequestMethod.GET)
    public String findBytesByIp(@PathVariable String ip) {
        String result = "<html><head></head><body>";

            result +="Log with IP "+ ip + " has " +service.findBytesByIp(ip) + " bytes";

        result+="</body></html>";
        return result;
    }

    @RequestMapping("/get")
    public
    @ResponseBody
    String getAll() {
        String result = "<html><head></head><body>";
        for (LogEntity e : service.getAll()) {
            result += e.toString();
            result += "<br>";
        }
        result+="</body></html>";
        return result;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("datafile") MultipartFile[] uploadFiles) {
            try {
                ExecutorService executor = Executors.newFixedThreadPool(5);
                for (MultipartFile file : uploadFiles) {
                    Unzipper unzipper = new Unzipper(file.getInputStream(), service);
                    executor.execute(unzipper);
                }
                executor.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "You have successfully uploaded your file!";
        }
    }


