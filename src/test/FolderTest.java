package test;

import static org.junit.Assert.*;

import TaggerSystem.*;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class FolderTest {
    @Test
    public void addImage() throws Exception {
        Tag tag = new Tag("Sam");
        ArrayList ar = new ArrayList();
        ar.add(tag);
        TagManager tm = new TagManager(ar);
        tm.addTag(tag);
        ImageFile image = new ImageFile("IMG321", tm.getTagList(), new Folder("aFolder"));
        ArrayList folder = new ArrayList();
        ArrayList children = new ArrayList();
        Folder checking = new Folder("ame", folder, children);
        checking.addImage(image);
        ArrayList allimages = checking.getAllImages();
        if (allimages.contains(image)){
            System.out.println("it contains image");
        }
    }


  @Test
  public void deleteTag() throws Exception {
      SystemMain.fileManager=new FileManager(new Folder("A Folder"), "");
      ArrayList<ImageFile> images = new ArrayList<>();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("a"));
        images.add(new ImageFile("1.jpg", tags));
        images.add(new ImageFile("2.jpg", tags));
        images.add(new ImageFile("3.jpg", tags));
        Folder test = new Folder("aFolder", new ArrayList<>(), images);
        test.deleteTag(new Tag("a"));
        for (ImageFile img : images)
            assertFalse(img.hasTag(new Tag("a")));
  }

  @Test
  public void getFileWithTag() throws Exception {
      SystemMain.fileManager=new FileManager(new Folder("A Folder"), "");
      ArrayList<ImageFile> images = new ArrayList<>();
      ArrayList<Tag> tags = new ArrayList<>();
      tags.add(new Tag("a"));
      images.add(new ImageFile("1.jpg", tags));
      images.add(new ImageFile("2.jpg", tags));
      images.add(new ImageFile("3.jpg", tags));
      Folder test = new Folder("aFolder", new ArrayList<>(), images);
      assertEquals(test.getFileWithTag(new Tag("a")), images);
  }

  @Test
  public void getAllImages() throws Exception {
      SystemMain.fileManager=new FileManager(new Folder("A Folder"), "");
      ArrayList<ImageFile> images = new ArrayList<>();
      ArrayList<Tag> tags = new ArrayList<>();
      tags.add(new Tag("a"));
      images.add(new ImageFile("1.jpg", tags));
      images.add(new ImageFile("2.jpg", tags));
      images.add(new ImageFile("3.jpg", tags));
      Folder test = new Folder("aFolder", new ArrayList<>(), images);
      assertEquals(test.getAllImages(),images);
  }

  @Test
  public void getChildren() throws Exception {
      ArrayList<Folder> folders = new ArrayList<>();
      folders.add(new Folder("1"));
      folders.add(new Folder("2"));
      folders.add(new Folder("3"));
      Folder test = new Folder("aFolder", folders, new ArrayList<>());
      assertEquals(test.getChildren(),folders);
  }

  @Test
  public void getValue() throws Exception {
      ArrayList<ImageFile> images = new ArrayList<>();
      ArrayList<Tag> tags = new ArrayList<>();
      tags.add(new Tag("a"));
      images.add(new ImageFile("1.jpg", tags));
      images.add(new ImageFile("2.jpg", tags));
      images.add(new ImageFile("3.jpg", tags));
      Folder test = new Folder("aFolder", new ArrayList<>(), images);
      assertEquals(test.getValue(),images);
  }

}