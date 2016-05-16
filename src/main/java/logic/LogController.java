package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by V3790147 on 5/16/2016.
 */
@Controller
@RequestMapping(value="/")
public class LogController {

    @Autowired
    LogRepository logRepository;

    @RequestMapping(value="/log/{id}", method= RequestMethod.GET)

    public String findLogById(@PathVariable Long id, Model model){
        model.addAttribute("log", logRepository.findOne(id) );
        System.out.println("Repo value " + logRepository.findOne(id).getId());
        return "findLogById";
    }

    @RequestMapping(value="/logs",method= RequestMethod.GET)
    public String logsList(Model model) {
        model.addAttribute("logs", logRepository.findAll());
        return "findAll";
    }
}
