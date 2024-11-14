package com.pelicula.dto;

import java.time.LocalDate;
import java.util.List;

public record DCPInventoryRequest(String id,
                                  String movieId,
                                  String code,
                                  String status, // Enum value as String
                                  LocalDate receptionDate,
                                  String location,
                                  List<DCPHistoryRecord> history
) {
    public record DCPHistoryRecord(
            LocalDate date,
            String status,
            String description
    ) {
    }
}
