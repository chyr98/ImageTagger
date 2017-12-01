package test;

import TaggerSystem.FileDirectory;
import TaggerSystem.Folder;
import TaggerSystem.ImageFile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FolderTest {
    // instance variables where all Testings could share
    private Folder a, b;
    private String name;
    private ArrayList<Folder> children;
    private ArrayList<ImageFile> value;

    public Tag(String name) {
        this.name = name;
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        name = System.getProperty("user.home");
        a = new Folder(name, children, value);
        a.getFileWithTag(tag);
        b = new Folder(name, children, value);
        b.getFileWithTag(tag);

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void addImage() throws Exception {
    }

    @org.junit.Test
    public void deleteTag() throws Exception {
    }

    @org.junit.Test
    public void getFileWithTag() throws Exception {
    }

    @org.junit.Test
    public void getAllImages() throws Exception {
    }

    @org.junit.Test
    public void getChildren() throws Exception {
    }

    @org.junit.Test
    public void getValue() throws Exception {
    }

}