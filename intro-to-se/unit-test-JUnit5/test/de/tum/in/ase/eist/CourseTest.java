package de.tum.in.ase.eist;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

	@Test
    void testGetCourseTitle() {

        String name = "Mathematik";
        Course course = new Course(name);
        assertEquals(name, course.getTitle());
    }

	@Test
    void testNoAttendees() {
        Course course = new Course("A");
        int num = course.getNumberOfAttendees();
        assertEquals(num, 0);
    }
    @Test
    void testThreeAttendees() {
        Course course = new Course("W");
        course.addAttendee(new Student("A", null, null, null, null));
        course.addAttendee(new Student(null, "B", null, null, null));
        course.addAttendee(new Student(null, null, "C", null, null));
        int num = course.getNumberOfAttendees();
        assertEquals(num, 3);
    }
}
