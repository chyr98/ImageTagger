package test;

import TaggerSystem.Tag;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagTest {
    @Test
    public void getName() throws Exception {
        Tag tag = new Tag("Sam");
        String actual = "Sam";
        assertEquals(tag.getName(), actual);
    }

    @Test
    public void equals() throws Exception{
        Tag tag = new Tag("Sam");
        Tag tag2 = new Tag("Sam");
        assertTrue(tag.equals(tag2));
    }

}