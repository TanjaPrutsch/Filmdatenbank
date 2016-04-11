/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



/**
 *
 * @author pruta_000
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title","year", "type", "poster", "runtime", "director", "plot", "imdbRating"})

public class Movie {
    @XmlAttribute
    private String title;
    @XmlAttribute
    private String year;
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String poster; 
    @XmlAttribute
    private String runtime;
    @XmlAttribute
    private String director; 
    @XmlAttribute
    private String plot;
    @XmlAttribute
    private double imdbRating; 

    public Movie(String title, String year, String type, String poster, String runtime, String director, String plot, double imdbRating) {
        this.title = title;
        this.year = year;
        this.type = type;
        this.poster = poster;
        this.runtime = runtime;
        this.director = director;
        this.plot = plot;
        this.imdbRating = imdbRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
    
    
    
    
    
}
