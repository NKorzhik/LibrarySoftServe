package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.dao.RequestDao;
import ua.softserve.dao.UserDao;
import ua.softserve.dto.request.RequestReadDto;
import ua.softserve.mapper.request.RequestReadMapper;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.enums.Status;
import ua.softserve.model.enums.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    private final RequestDao requestDao;
    private final UserDao userDao;
    private final BookDao bookDao;
    private final QuantityDao quantityDao;

    @Autowired
    public RequestService(RequestDao requestDao,
                          UserDao userDao,
                          BookDao bookDao,
                          QuantityDao quantityDao) {
        this.requestDao = requestDao;
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.quantityDao = quantityDao;
    }

    private long processRequest(long id, Status status) {
        HistoryOfRequest request = requestDao.getRequestById(id);
        requestDao.processRequest(
                Optional.of(request)
                        //возможно нужно замапить в дто перед присвоеним значений
                        .map(ofRequest -> {
                            ofRequest.setStatus(status);
                            ofRequest.setRequestProcessingDate(LocalDate.now());
                            return ofRequest;
                        }).orElseThrow()
        );
        return request.getBookId().getId();
    }

    public void addRequest(long bookId) {
        UserDetails userDetails  = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String userEmail = userDetails.getUsername();

        HistoryOfRequest request = new HistoryOfRequest();
        request.setBookId(bookDao.getBook(bookId));
        request.setUserId(userDao.findByEmail(userEmail)
                .orElseThrow());
        request.setDateOfIssue(LocalDate.now());
        request.setShouldBeReturn(LocalDate.now().plusMonths(3));

        long countOfBookCopies = quantityDao.getCountOfQuantityByBookId(bookId);
        request.setStatus(countOfBookCopies == 0 ? Status.NOT_AVAILABLE : Status.WAITING);

        requestDao.addRequest(request);
    }

    public void acceptRequest(long id) {
        long bookId = processRequest(id, Status.READING);
        long copyId = quantityDao.getFirstFreeCopyByBookId(bookId);
        quantityDao.changeTypeOfCopyById(copyId, Type.READING);
    }

    public void rejectRequest(long id) {
        processRequest(id, Status.REJECTED);
    }

    //ПОМЕНЯТЬ НА ДТО
    public List<RequestReadDto> getRequestedBooks(long id){
        return requestDao.getRequestedBooks(id).stream()
                .map(RequestReadMapper::mapToDto)
                .collect(Collectors.toList());
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
