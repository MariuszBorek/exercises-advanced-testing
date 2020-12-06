package pl.sda.mockito.database;

import java.util.List;

public interface DataRepository {

    Data add(final Data data);

    int size();
}
