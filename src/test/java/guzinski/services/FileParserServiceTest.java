package guzinski.services;

import guzinski.model.FileInputData;
import guzinski.service.FileParserService;
import guzinski.service.FileParserServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletionStage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class FileParserServiceTest {

    private FileParserService service;
    
    @Before
    public void setUp() throws Exception {
        service = new FileParserServiceImpl();
    }

    @Test
    public void testParseFile() throws Exception {
        var path = Path.of(getClass().getResource("/example1.dat").toURI());

        FileInputData parsedFile = service.readFile(path).toCompletableFuture().join();

        assertThat(parsedFile.getFileName(), equalTo("example1.dat"));
        assertThat(parsedFile.getCostumers(), hasSize(2));
        assertThat(parsedFile.getOrders(), hasSize(2));
        assertThat(parsedFile.getSellers(), hasSize(2));
        assertThat(parsedFile.getSellers().get(0).getName(), equalTo("Pedro"));
        assertThat(parsedFile.getSellers().get(0).getSalary(), equalTo(50000d));
        assertThat(parsedFile.getSellers().get(1).getName(), equalTo("Paulo"));
        assertThat(parsedFile.getSellers().get(1).getSalary(), equalTo(40000.99));
        assertThat(parsedFile.getCostumers().get(0).getName(), equalTo("Jose da Silva"));
        assertThat(parsedFile.getCostumers().get(1).getName(), equalTo("Eduardo Pereira"));
        assertThat(parsedFile.getCostumers().get(0).getBusinessArea(), equalTo("Rural"));
        assertThat(parsedFile.getCostumers().get(1).getBusinessArea(), equalTo("Rural"));
        assertThat(parsedFile.getOrders().get(0).getId(), equalTo("10"));
        assertThat(parsedFile.getOrders().get(1).getId(), equalTo("08"));
        assertThat(parsedFile.getOrders().get(0).getItems(), hasSize(3));
        assertThat(parsedFile.getOrders().get(1).getItems(), hasSize(3));

    }
}

