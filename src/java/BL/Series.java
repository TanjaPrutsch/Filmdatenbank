/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author pruta_000
 */
public class Series extends Movie
{
    @XmlAttribute
    private int season;
    @XmlAttribute 
    private int episode; 
}
