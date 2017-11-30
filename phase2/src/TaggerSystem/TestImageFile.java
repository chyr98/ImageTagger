package TaggerSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ImageFileTest {

    @Test
    public void testNameOfEmptyImageFile() {
        ArrayList l = new ArrayList<ArrayList<Tag>>();
        ImageFile picture = new ImageFile("abc", l);
        assertEquals("abc", picture.getName());
    }

    @Test
    public void testNameOfImageFileWithTag() {
        Tag a = new Tag("@good");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l);
        assertEquals("abc@good", picture.getName());
    }

    @Test
    public void testAddingDifferentTag() {
        Tag a = new Tag("@good");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l);
        Tag b = new Tag("@csc");
        picture.addTag(b);
        assertEquals("abc@good@csc", picture.getName());
        assertTrue(picture.tags.size == 2);
    }

    @Test
    public void testAddingSameTag() {
        Tag a = new Tag("@good");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l);
        picture.addTag(Tag("@good"));
        assertEquals("abc@good", picture.getName());
        assertTrue(picture.tags.size == 1);
    }

    @Test
    public void testImageFileHasTag() {
        Tag a = new Tag("@good");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l);
        assertTrue(picture.hasTag(a));
    }

    @Test
    public void testImageFileDoesNotHasTag() {
        Tag a = new Tag("@good");
        Tag b = new Tag("@csc");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l);
        assertFalse(picture.hasTag(b));
    }

    @Test
    public void testGetCurrentTagList1() {
        Tag a = new Tag("@good");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l);
        ArrayList m = new ArrayList<Tag>([a]);
        assertEquals(m, picture.getCurrentTagList());
    }

    @Test
    public void testGetCurrentTagList2() {
        Tag a = new Tag("@good");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l);
        Tag b = new Tag("@csc");
        picture.addTag(b);
        ArrayList m = new ArrayList<Tag>([a, b]);
        assertTrue(picture.tags.size == 2);
        assertEquals([a, b], picture.getCurrentTagList());
    }

    @Test
    public void testGetCurrentTagList3() {
        Tag a = new Tag("@good");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a]]);
        ImageFile picture = new ImageFile("abc", l]);
        picture.addTag(a);
        ArrayList m = new ArrayList<Tag>([a]);
        assertTrue(picture.tags.size == 1);
        assertEquals(m, picture.getCurrentTagList());
    }

    @Test
    public void testDeleteTag() {
        Tag a = new Tag("@good");
        Tag b = new Tag("@csc");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a, b]]);
        ImageFile picture = new ImageFile("abc", l);
        picture.deleteTag(Tag("@csc"));
        ArrayList m = new ArrayList<Tag>([a]);
        assertTrue(picture.tags.size == 2);
        assertEquals(m, picture.getCurrentTagList());
    }

    @Test
    public void testGetAllTagLists() {
        Tag a = new Tag("@good");
        Tag b = new Tag("@csc");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a],[a,b]]);
        ImageFile picture = new ImageFile(name "abc", l);
        assertEquals(l, picture.getAllTagLists());
    }

    @Test
    public void testTwoImageFileAreEqual() {
        Tag a = new Tag("@good");
        Tag b = new Tag("@csc");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a], [a, b]]);
        ArrayList m = new ArrayList<ArrayList<Tag>>([[b], [a, b]]);
        ImageFile picture1 = new ImageFile(name "abc", l);
        ImageFile picture2 = new ImageFile(name "abc", m);
        assertTrue(picture1.equals(picture2));
    }

    @Test
    public void testTwoImageFileAreNotEqual() {
        Tag a = new Tag("@good");
        Tag b = new Tag("@csc");
        ArrayList l = new ArrayList<ArrayList<Tag>>([[a], [a, b]]);
        ArrayList m = new ArrayList<ArrayList<Tag>>([[a,b], [b]]);
        ImageFile picture1 = new ImageFile(name "abc", l);
        ImageFile picture2 = new ImageFile(name "abc", m);
        assertFalse(picture1.equals(picture2));
    }
}