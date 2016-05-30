/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class is a beans-class that describes a series.
 */
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder =
{
    "title", "release", "type", "poster", "runtime", "director", "plot", "imdbRating"
})

public class Series
{

    @XmlAttribute
    private String title;
    @XmlAttribute
    private String released;
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

    /**
     * @param  title String (Title of the series)
     * @param  released String (Date of released series)
     * @param  type String (type -> Series/Movie)
     * @param  poster String (url of the poster)
     * @param  runtime String (runtime of series in minutes)
     * @param  director String (name of director)
     * @param  plot String (plot of series)
     * @param  imdbRating double (imdb Rating from m.imdb.com
     */
    public Series(String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating)
    {
        this.title = title;
        this.released = released;
        this.type = type;
        this.poster = poster;
        this.runtime = runtime;
        this.director = director;
        this.plot = plot;
        this.imdbRating = imdbRating;
    }

    public Series()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getReleased()
    {
        return released;
    }

    public void setReleased(String released)
    {
        this.released = released;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPoster()
    {
        return poster;
    }

    public void setPoster(String poster)
    {
        this.poster = poster;
    }

    public String getRuntime()
    {
        return runtime;
    }

    public void setRuntime(String runtime)
    {
        this.runtime = runtime;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public String getPlot()
    {
        return plot;
    }

    public void setPlot(String plot)
    {
        this.plot = plot;
    }

    public double getImdbRating()
    {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating)
    {
        this.imdbRating = imdbRating;
    }

}
