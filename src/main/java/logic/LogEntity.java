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
    private String clientid;
    private String userid;
    private String date;
    private String request;
    private String status;
    private String size;

    public LogEntity() {

    }

    public LogEntity(String ip, String clientid, String userid, String date, String request, String status, String size) {
        this.ip = ip;
        this.clientid = clientid;
        this.userid = userid;
        this.date = date;
        this.request = request;
        this.status = status;
        this.size = size;
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

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
