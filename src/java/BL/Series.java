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

/**
 *
 * @author pruta_000
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Series extends Movie
{
    @XmlAttribute
    private int season;
    @XmlAttribute 
    private int episode; 

    public Series(String title, LocalDate year, String type, String poster, LocalTime untime, String director, String story, double imdbRating, int season,
    int episode) {
        super(title, year, type, poster, untime, director, story, imdbRating);
        this.season = season; 
        this.episode = episode; 
        
    }
}
