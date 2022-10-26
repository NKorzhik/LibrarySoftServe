package ua.softserve.mapper;

import ua.softserve.dto.RequestCreateDto;
import ua.softserve.model.HistoryOfRequest;

public class RequestCreateMapper {

    public static HistoryOfRequest mapToModel(RequestCreateDto requestDto) {
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
