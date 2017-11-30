package TaggerSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ImageFileTest {

    private Object ArrayList;

    @Test
    public void testNameOfEmptyImageFile() {
        Tag a = "";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        assertEquals("abc", picture.getName());
    }

    @Test
    public void testNameOfImageFileWithTag() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        assertEquals("abc@good", picture.getName());
    }

    @Test
    public void testAddingDifferentTag() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        Tag b = "@csc";
        picture.addTag(b);
        assertEquals("abc@good@csc", picture.getName());
        assertTrue(picture.tags.size == 2);
    }

    @Test
    public void testAddingSameTag() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        picture.addTag(Tag "@good");
        assertEquals("abc@good", picture.getName());
        assertTrue(picture.tags.size == 1);
    }

    @Test
    public void testImageFileHasTag() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        assertTrue(picture.hasTag(Tag "@good"));
    }

    @Test
    public void testImageFileDoesNotHasTag() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        assertFalse(picture.hasTag(Tag "@csc"));
    }

    @Test
    public void testGetCurrentTagList1() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        assertEquals([Tag "@good"], picture.getCurrentTagList());
    }

    @Test
    public void testGetCurrentTagList2() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        Tag b = "@csc";
        picture.addTag(b);
        assertTrue(picture.tags.size == 2);
        assertEquals([Tag "@good", Tag "@csc"], picture.getCurrentTagList());
    }

    @Test
    public void testGetCurrentTagList3() {
        Tag a = "@good";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a]]);
        picture.addTag(a);
        assertTrue(picture.tags.size == 1);
        assertEquals([Tag "@good"], picture.getCurrentTagList());
    }

    @Test
    public void testDeleteTag() {
        Tag a = "@good";
        Tag b = "@csc";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a, b]]);
        picture.deleteTag(Tag "@csc");
        assertTrue(picture.tags.size == 2);
        assertEquals([Tag "@good"], picture.getCurrentTagList());
    }

    @Test
    public void testGetAllTagLists() {
        Tag a = "@good";
        Tag b = "@csc";
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a], [a, b]]);
        assertEquals([[Tag "@good"], [Tag "@good", Tag "@csc"]], picture.getAllTagLists()):
    }

    @Test
    public void testTwoImageFileAreEqual() {
        Tag a = "@good";
        Tag b = "@csc";
        ImageFile picture1 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a], [a, b]]);
        ImageFile picture2 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[b], [a, b]]);
        assertTrue(picture1.equals(picture2));
    }

    @Test
    public void testTwoImageFileAreNotEqual() {
        Tag a = "@good";
        Tag b = "@csc";
        ImageFile picture1 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a], [a, b]]);
        ImageFile picture2 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[a, b], [b]]);
        assertFalse(picture1.equals(picture2));
    }
}