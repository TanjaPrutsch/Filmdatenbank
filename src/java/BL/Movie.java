/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;



/**
 *
 * @author pruta_000
 */
@XmlAccessorType(XmlAccessType.FIELD)


public class Movie {
    @XmlAttribute
    private String title;
    @XmlAttribute
    private LocalDate year;
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String poster; 
    @XmlAttribute
    private LocalTime untime;
    @XmlAttribute
    private String director; 
    @XmlAttribute
    private String story;
    @XmlAttribute
    private double imdbRating; 

    public Movie(String title, LocalDate year, String type, String poster, LocalTime untime, String director, String story, double imdbRating) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.poster = poster;
        this.untime = untime;
        this.director = director;
        this.story = story;
        this.imdbRating = imdbRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public LocalTime getUntime() {
        return untime;
    }

    public void setUntime(LocalTime untime) {
        this.untime = untime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
    
    
    
    
    
}