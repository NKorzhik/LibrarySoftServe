package ua.softserve.mapper;

import ua.softserve.dto.RequestReadDto;
import ua.softserve.model.HistoryOfRequest;

public class RequestReadMapper {
    public static HistoryOfRequest mapToDto(RequestReadDto requestDto) {
        HistoryOfRequest request = HistoryOfRequest.builder()
                .userId(UserCreateMapper.mapToModel(requestDto.getUserDto()))
                .bookId(BookReadUpdateMapper.mapToModel(requestDto.getBookDto()))
                .dateOfIssue(requestDto.getDateOfIssue())
                .shouldBeReturn(requestDto.getShouldBeReturn())
                .status(requestDto.getStatus())
                .build();
        return request;
    }
}
