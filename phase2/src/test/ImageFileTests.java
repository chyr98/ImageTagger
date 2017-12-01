package test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import TaggerSystem.*;

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
    SystemMain.tagManager=new TagManager(new ArrayList<>());
    SystemMain.fileManager=new FileManager(new Folder("A Folder"), "");
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
  void testGetName(){

  }

  @AfterEach
  void tearDown() {

  }

  @AfterAll
  static void tearDownAll() {

  }
}
