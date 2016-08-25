package org.freakz.hokan_ng_springboot.bot.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: petria
 * Date: 3/7/11
 * Time: 4:20 PM
 */
@Entity
@Table(name = "URL")
public class Url implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "URL", length = 1024)
    private String url;

    @Column(name = "URL_TITLE", length = 1024)
    private String urlTitle;

    @Column(name = "SHORT_URL", length = 1024)
    private String shortUrl;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "CHANNEL")
    private String channel;

    @Column(name = "CREATED")
    private Date created;

    @Column(name = "WANHA_COUNT")
    private long wanhaCount;

    public Url() {
        this.url = "";
        this.sender = "";
        this.channel = "";
        this.created = new Date();
        this.wanhaCount = 0;

    }

    public Url(String url, String sender, String channel, Date created) {
        this.url = url;
        this.sender = sender;
        this.channel = channel;
        this.created = created;
        this.wanhaCount = 0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date time) {
        this.created = time;
    }

    public long getId() {
        return id;
    }

    public long getWanhaCount() {
        return this.wanhaCount;
    }

    public void addWanhaCount(long delta) {
        this.wanhaCount += delta;
    }

    public void setWanhaCount(long wanhaCount) {
        this.wanhaCount = wanhaCount;
    }

    @Override
    public String toString() {
        return String.format("%s [w: %d]", this.url, this.wanhaCount);
    }

}
