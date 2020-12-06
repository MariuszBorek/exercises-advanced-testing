package pl.sda.mockito.database;

import pl.sda.mockito.exception.SdaException;

public class DataServiceImpl implements DataService {

    private final DatabaseConnection databaseConnection;

    private final DataRepository dataRepository;

    public DataServiceImpl(final DatabaseConnection databaseConnection, DataRepository dataRepository) {
        this.databaseConnection = databaseConnection;
        this.dataRepository = dataRepository;
    }

    public Data add(final Data data) {
        if (!databaseConnection.isOpened()) {
            databaseConnection.open();
            if (!databaseConnection.isOpened()) {
                throw new SdaException("Unable to open connection to the database");
            }
        }

        Data addedData = dataRepository.add(data);

        if (databaseConnection.isOpened()) {
            databaseConnection.close();
        }

        return addedData;
    }
}
