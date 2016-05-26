package logic;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
    public @ResponseBody String findLogById(@PathVariable Long id) {
        LogEntity logEntity = service.findById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String result=null;
        try {
           result = objectMapper.writerWithView(Views.Normal.class).writeValueAsString(logEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/{ip}/print", method = RequestMethod.GET)
    public String findLogByIp(@PathVariable String ip) {
        String result = "<html><head></head><body>";
        for (LogEntity e : service.findByIp(ip)) {
            result += e.toString();
            result +="<br>";
        }
        result+="</body></html>";
        service.displayByIp(ip);
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


    /*@RequestMapping(value = "/entity/{id}")
    public ResponseEntity<LogEntity> get(@PathVariable Long id) {
        return new ResponseEntity<LogEntity>(service.findById(id), HttpStatus.OK);
    }
*/
    @RequestMapping(value = "/entity/{id}")
    @JsonView(Views.Normal.class)
    public LogEntity find(@PathVariable Long id){
        return service.findById(id);
    }

    @RequestMapping(value = "/entity/{ip}/print")
    public String display(@PathVariable String ip){
        //service.displayByIp(ip);
        return "hello";
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Normal.class)
    public List<LogEntity> find(){
        return service.findAll();
    }

    @RequestMapping(value = "/seeAll")
    public String findAll(){
        return "ipList";
    }

}


