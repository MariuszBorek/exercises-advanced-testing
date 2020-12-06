package pl.sda.mockito.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Data {

    private UUID id;

    private String value;
}
