package pl.sda.mockito.database;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataServiceImplTest {

    private static final String DATA_VALUE = "value";

    private static final Data DATA =
            Data.builder().value(DATA_VALUE).build();

    @Mock
    private DatabaseConnection databaseConnection;

    @Spy
    private DataRepository dataRepository = new DataRepositoryImpl();

    @InjectMocks
    private DataServiceImpl dataService;

    @Test
    public void shouldAddDataForOpenedDatabaseConnection() {

        //given
        when(databaseConnection.isOpened()).thenReturn(true);

        //when
        Data actualData = dataService.add(DATA);

        //then
        Assertions.assertThat(actualData.getId()).isNotNull();
        Assertions.assertThat(actualData.getValue()).isEqualTo(DATA_VALUE);

        verify(databaseConnection, times(2)).isOpened();
        verify(databaseConnection, never()).open();
        verify(databaseConnection).close();
        verifyNoMoreInteractions(databaseConnection);

        verify(dataRepository).add(DATA);
        verifyNoMoreInteractions(dataRepository);
    }

}
