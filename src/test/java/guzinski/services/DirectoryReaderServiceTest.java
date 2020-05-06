package guzinski.services;

import guzinski.service.DirectoryReaderService;
import guzinski.service.DirectoryReaderServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class DirectoryReaderServiceTest {

    private DirectoryReaderService service;

    @Before
    public void setUp() throws Exception {
        service = new DirectoryReaderServiceImpl();
    }

    @Test
    public void testReadDir() throws Exception {
        File resourcesDirectory = new File("src/test/resources");

        List<Path> read = service.read(resourcesDirectory.toPath().toString());
        MatcherAssert.assertThat(read, Matchers.hasSize(1));
        List<Path> read2 = service.read(resourcesDirectory.toPath().toString());
        MatcherAssert.assertThat(read2, Matchers.hasSize(0));
    }
}
