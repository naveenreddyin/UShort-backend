package com.ums.umsbackend.domains;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Naveen on 20/07/2017.
 */
@Entity
public class Duration {

    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    private int timeDuration;

    protected Duration(){

    }

    public Duration(Long id, int timeDuration) {
        this.id = id;
        this.timeDuration = timeDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }
}
