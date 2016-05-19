package logic;

import javax.persistence.*;

/**
 * Created by V3790147 on 5/16/2016.
 */


@Entity
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ip;
    private String date;
    private String request;
    private String response;
    private String bytesSent;
    private String browser;

    public LogEntity() {

    }

    public LogEntity(String ip, String date, String response, String bytesSent) {
        this.ip = ip;
        this.date = date;
        this.response = response;
        this.bytesSent = bytesSent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getbrowser() {
        return browser;
    }

    public void setbrowser(String browser) {
        this.browser = browser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getbytesSent() {
        return bytesSent;
    }

    public void setbytesSent(String bytesSent) {
        this.bytesSent = bytesSent;
    }

    @Override
    public String toString() {
        return "Log: " + String.valueOf(id) + " " + ip + " "+ date +  " "+ request + " " + response + " " + bytesSent ;
    }
}
