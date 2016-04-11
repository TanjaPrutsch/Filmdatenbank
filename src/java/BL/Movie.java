/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.xml.bind.annotation.XmlAttribute;



/**
 *
 * @author pruta_000
 */
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
    
}
