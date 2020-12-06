package pl.sda.mockito.database;

public interface DatabaseConnection {

    boolean isOpened();

    void open();

    boolean close();
}
