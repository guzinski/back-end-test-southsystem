package guzinski;

import com.google.inject.ImplementedBy;

import java.io.IOException;

@ImplementedBy(ApplicationImpl.class)
public interface Application {

    void start();

}
