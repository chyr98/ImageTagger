package test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import TaggerSystem.Folder;
import TaggerSystem.ImageFile;
import TaggerSystem.Tag;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ImageFileTests {

  @BeforeAll
  static void initAll() {

  }

  @BeforeEach
  void init() {
  }

  @Test
  void testAddTag(){
    ImageFile image = new ImageFile("A image", new ArrayList<Tag>());
    image.addTag(new Tag("A tag"));
    ArrayList<ArrayList<Tag>> test = new ArrayList<>();
    ArrayList<Tag> tag = new ArrayList<>();
    tag.add(new Tag("A tag"));
    test.add(new ArrayList<>());
    test.add(tag);
    assertEquals(image.getAllTagLists(), test);

  }

  @Test
  void testCopyTo() {
    Folder subFolder = new Folder("subFolder");
    Folder root = new Folder("root", new ArrayList<Folder>(Arrays.asList(subFolder)), new ArrayList<ImageFile>());
    ImageFile image1 = new ImageFile("image1", new ArrayList<Tag>(), root);
    ImageFile image2 = new ImageFile("image2", new ArrayList<Tag>(), subFolder);
    try {
      image1.copyTo(root);
      image1.copyTo(subFolder);
      File rootFile = root.toFile();
      File subFile = subFolder.toFile();
      assertArrayEquals(rootFile.list(), new String[] {"image1", "(copy)image1"});
      assertArrayEquals(subFile.list(), new String[] {"image2", "image1"});
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @AfterEach
  void tearDown() {

  }

  @AfterAll
  static void tearDownAll() {

  }
}
