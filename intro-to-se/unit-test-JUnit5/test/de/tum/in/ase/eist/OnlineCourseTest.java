package de.tum.in.ase.eist;

import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class OnlineCourseTest {

    @Test
    void testSetOnlineCourseUrlWithValidUrl() {
     OnlineCourse course = new OnlineCourse("AAA");

     try {
         URL t = new  URL("http://www.aaaa.com");
         course.setUrl("http://www.aaaa.com");
         assertEquals(t, course.getUrl());

     } catch (MalformedURLException e) {
         fail();
     }
    }
    @Test
    void testSetOnlineCourseUrlWithInvalidUrl() {
        OnlineCourse course = new OnlineCourse("AAA");
        assertThrows(MalformedURLException.class, () -> course.setUrl("fhsakljdajf"));
     }

}

