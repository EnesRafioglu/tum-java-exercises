package de.tum.in.ase.eist;

import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {

    @TestSubject
    private Discussion discussion;
    @Mock
   private Course courseMock;
    @Mock
   private Comment commentMock;

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public Comment getCommentMock() {
        return commentMock;
    }

    public Course getCourseMock() {
        return courseMock;
    }

    public void setCommentMock(Comment commentMock) {
        this.commentMock = commentMock;
    }

    public void setCourseMock(Course courseMock) {
        this.courseMock = courseMock;
    }

    @Test
    void testComment() {

       expect(commentMock.save()).andReturn(true);
       replay(commentMock);
       int len = discussion.getNumberOfComments();
       discussion.addComment(commentMock);
       assertTrue(discussion.getComments().contains(commentMock));
       assertEquals(len + 1, discussion.getComments().size());
       verify(commentMock);


    }

    @Test
    void testCommentIfSavingFails() {

        expect(commentMock.save()).andReturn(false);
        replay(commentMock);
        int len = discussion.getNumberOfComments();
        discussion.addComment(commentMock);
        assertFalse(discussion.getComments().contains(commentMock));
        assertEquals(len, discussion.getComments().size());
        verify(commentMock);


    }

    @Test
    void testStartCourseDiscussion() {
        Person person = new Lecturer("A", "B", LocalDate.now());
        expect(courseMock.isDiscussionAllowed(person)).andReturn(true);
        replay(courseMock);
        boolean b = discussion.startCourseDiscussion(courseMock, person, "C");
        assertTrue(b);
        assertEquals(courseMock, discussion.getCourse());
        assertEquals("C", discussion.getTopic());
        verify(courseMock);

    }

    // TODO implement the tests

}
