package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.RequestDao;
import ua.softserve.dao.UserDao;
import ua.softserve.dto.RequestReadDto;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Service
public class RequestService {

    private final RequestDao requestDao;
    private final UserDao userDao;
    private final BookDao bookDao;

    @Autowired
    public RequestService(RequestDao requestDao, UserDao userDao, BookDao bookDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    public void addRequest(RequestReadDto requestDto, long bookId) {
        UserDetails userDetails  = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String userEmail = userDetails.getUsername();

        /*requestDto.setUserDto(UserCreateMapper.mapToDto(
                userDao.findByEmail(userEmail).orElseThrow()));
        requestDto.setDateOfIssue(LocalDate.now());
        requestDto.setShouldBeReturn(LocalDate.now().plusMonths(3));
        requestDto.setStatus(Status.WAITING);*/
        HistoryOfRequest request = new HistoryOfRequest();
        request.setBookId(bookDao.getBook(bookId));
        request.setUserId(userDao.findByEmail(userEmail)
                .orElseThrow());
        request.setDateOfIssue(LocalDate.now());
        request.setShouldBeReturn(LocalDate.now().plusMonths(3));
        request.setStatus(Status.WAITING);
        requestDao.addRequest(request);
        //requestDao.addRequest(RequestCreateMapper.mapToModel(requestDto));
    }

    //ПОМЕНЯТЬ НА ДТО
    public List<HistoryOfRequest> getRequestedBooks(long id){
        return requestDao.getRequestedBooks(id);
    }

    //ПОМЕНЯТЬ НА ДТО
    public HistoryOfRequest getRequestById(long id){
        return requestDao.getRequestById(id);
    }

    public List<HistoryOfRequest> getBooksWithStatusWaiting(){
        return requestDao.getBooksWithStatusWaiting();
    }

    public void returnBookToLibrary(long requestId){
        requestDao.returnBookToLibrary(requestId);
    }
}
