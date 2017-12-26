package com.ums.ushortbackend.domains;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "short_url")
public class ShortURL {
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "code", unique = true)
    private String code;

    private String httpURL;

    public ShortURL(LocalDateTime now, String code, String url, String prefix) {
        this.creationTime = now;
        this.code = code;
        this.httpURL = url;
        this.prefixURL = prefix;
    }

    public String getPrefixURL() {
        return prefixURL;
    }

    public void setPrefixURL(String prefixURL) {
        this.prefixURL = prefixURL;
    }

    private String prefixURL;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    @Column(name="count",columnDefinition = "int default 0")
    private int count = 0;


    public ShortURL() {
    }

    public ShortURL(Long id, LocalDateTime creationTime, String code, String httpURL, int count) {
        this.id = id;
        this.creationTime = creationTime;
        this.code = code;
        this.httpURL = httpURL;
        this.count = count;
    }
    public ShortURL(Long id, LocalDateTime creationTime, String code, String httpURL, String prefixURL) {
        this.id = id;
        this.creationTime = creationTime;
        this.code = code;
        this.httpURL = httpURL;
        this.prefixURL = prefixURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHttpURL() {
        return httpURL;
    }

    public void setHttpURL(String httpURL) {
        this.httpURL = httpURL;
    }
}
