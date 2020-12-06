package pl.sda.mockito.database;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataRepositoryImpl implements DataRepository {

    private final List<Data> dataList = new ArrayList<>();

    @Override
    public Data add(Data data) {
        if (data != null) {
            data.setId(UUID.randomUUID());
            dataList.add(data);
            return data;
        }

        throw new IllegalArgumentException("Data is null");
    }

    @Override
    public int size() {
        return dataList.size();
    }
}
