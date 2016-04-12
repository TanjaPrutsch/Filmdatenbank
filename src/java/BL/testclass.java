/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Dino Patarcec
 */
public class testclass
{

    public static void main(String[] args) throws MalformedURLException
    {

//        try
//        {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Movie.class);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            Movie m = (Movie) jaxbUnmarshaller.unmarshal(new URL("http://www.omdbapi.com/?t=casino+royale&y=&plot=short&r=xml"));
//            System.out.println(m.getTitle());
        Root r = JAXB.unmarshal("http://www.omdbapi.com/?t=casino+royale&y=&plot=short&r=xml", Root.class);
        System.out.println(r.getMovie().getTitle());
//        } catch (JAXBException ex)
//        {
//            Logger.getLogger(testclass.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
