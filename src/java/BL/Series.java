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
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author pruta_000
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"title","year", "type", "poster", "runtime", "director", "plot", "imdbRating", "season" , "episode"})
public class Series extends Movie
{
    @XmlAttribute
    private int season;
    @XmlAttribute 
    private int episode; 

    public Series(String title, LocalDate year, String type, String poster, LocalTime runtime, String director, String story, double imdbRating, int season,
    int episode) {
        super(title, year, type, poster, runtime, director, story, imdbRating);
        this.season = season; 
        this.episode = episode; 
        
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }
    
    
}
