package ua.softserve.mapper.request;

import ua.softserve.dto.request.RequestReadDto;
import ua.softserve.mapper.book.BookReadUpdateMapper;
import ua.softserve.mapper.user.UserCreateMapper;
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
