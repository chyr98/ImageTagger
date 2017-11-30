package TaggerSystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ImageFileTest {

    @Test
    public void testNameOfEmptyImageFile() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [[]]);
        assertEquals("abc", picture.getName());
    }

    @Test
    public void testNameOfImageFileWithTag() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        assertEquals("abc@good", picture.getName());
    }

    @Test
    public void testAddingDifferentTag() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        picture.addTag(Tag "@csc");
        assertEquals("abc@good@csc", picture.getName());
        assertTrue(picture.tags.size == 2);
    }

    @Test
    public void testAddingSameTag() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        picture.addTag(Tag "@good");
        assertEquals("abc@good", picture.getName());
        assertTrue(picture.tags.size == 1);
    }

    @Test
    public void testImageFileHasTag() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        assertTrue(picture.hasTag(Tag "@good"));
    }

    @Test
    public void testImageFileDoesNotHasTag() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        assertFalse(picture.hasTag(Tag "@csc"));
    }

    @Test
    public void testGetCurrentTagList1() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        assertEquals(["@good"], picture.getCurrentTagList());
    }

    @Test
    public void testGetCurrentTagList2() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        picture.addTag(Tag "@csc");
        assertTrue(picture.tags.size == 2);
        assertEquals(["@good", "@csc"], picture.getCurrentTagList());
    }

    @Test
    public void testGetCurrentTagList3() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"]]);
        picture.addTag(Tag "@good");
        assertTrue(picture.tags.size == 1);
        assertEquals(["@good"], picture.getCurrentTagList());
    }

    @Test
    public void testDeleteTag() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good", "@csc"]]);
        picture.deleteTag(Tag "@csc");
        assertTrue(picture.tags.size == 2);
        assertEquals(["@good"], picture.getCurrentTagList());
    }

    @Test
    public void testGetAllTagLists() {
        ImageFile picture = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"], ["@good", "@csc"]]);
        assertEquals([["@good"], ["@good", "@csc"]], picture.getAllTagLists()):
    }

    @Test
    public void testTwoImageFileAreEqual() {
        ImageFile picture1 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"], ["@good", "@csc"]]);
        ImageFile picture2 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@csc"], ["@good", "@csc"]]);
        assertTrue(picture1.equals(picture2));
    }

    @Test
    public void testTwoImageFileAreNotEqual() {
        ImageFile picture1 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good"], ["@good", "@csc"]]);
        ImageFile picture2 = new ImageFile(name "abc", ArrayList<ArrayList<Tag>> [["@good", "@csc"], ["@csc"]]);
        assertFalse(picture1.equals(picture2));
    }
}