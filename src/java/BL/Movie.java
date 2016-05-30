/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author pruta_000
 */
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder =
{
    "title", "released", "type", "poster", "runtime", "director", "plot", "imdbRating"
})

public class Movie
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
     * @param title String (Title of the movie)
     * @param released String (Date of released movie)
     * @param type String (type -> Series/Movie)
     * @param poster String (url of the poster)
     * @param runtime String (runtime of movie in minutes)
     * @param director String (name of director)
     * @param plot String (plot of movie)
     * @param imdbRating double (imdb Rating from m.imdb.com
     */
    public Movie(String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating)
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

    public Movie()
    {
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.title);
        hash = 89 * hash + Objects.hashCode(this.released);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.poster);
        hash = 89 * hash + Objects.hashCode(this.runtime);
        hash = 89 * hash + Objects.hashCode(this.director);
        hash = 89 * hash + Objects.hashCode(this.plot);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.imdbRating) ^ (Double.doubleToLongBits(this.imdbRating) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Movie other = (Movie) obj;
        if (!Objects.equals(this.title, other.title))
        {
            return false;
        }
        if (!Objects.equals(this.released, other.released))
        {
            return false;
        }
        if (!Objects.equals(this.type, other.type))
        {
            return false;
        }
        if (!Objects.equals(this.poster, other.poster))
        {
            return false;
        }
        if (!Objects.equals(this.runtime, other.runtime))
        {
            return false;
        }
        if (!Objects.equals(this.director, other.director))
        {
            return false;
        }
        if (!Objects.equals(this.plot, other.plot))
        {
            return false;
        }
        if (Double.doubleToLongBits(this.imdbRating) != Double.doubleToLongBits(other.imdbRating))
        {
            return false;
        }
        return true;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getRelease()
    {
        return released;
    }

    public void setRelease(String released)
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
