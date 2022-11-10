package com.company.library_javaEE.service.book;

import com.company.library_javaEE.configs.ApplicationContextHolder;
import com.company.library_javaEE.dao.AbstractDAO;
import com.company.library_javaEE.dao.BookDAO;
import com.company.library_javaEE.domains.Book;
import com.company.library_javaEE.domains.Uploads;
import com.company.library_javaEE.dto.BookCreateDTO;
import com.company.library_javaEE.dto.BookUpdateDTO;
import com.company.library_javaEE.enums.BookStatus;
import com.company.library_javaEE.enums.Genre;
import com.company.library_javaEE.exceptions.NotFoundException;
import com.company.library_javaEE.service.file.FileStorageService;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/10/2022 12:38 AM (Thursday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class BookService extends AbstractDAO<BookDAO> {
    private static BookService bookService;
    private final FileStorageService fileStorageService = ApplicationContextHolder.getBean( FileStorageService.class);

    private BookService() {
        super(ApplicationContextHolder.getBean(BookDAO.class));
    }


    public void create(BookCreateDTO dto, Part book) {
        CompletableFuture<Uploads> coverUpload = CompletableFuture.supplyAsync(() ->
                fileStorageService.extractCover(book)
        );
        CompletableFuture<Uploads> bookUpload = CompletableFuture.supplyAsync(() ->
                fileStorageService.uploads (book)
        );
        try {
            dao.save( Book.builder()
                    .cover (coverUpload.get())
                    .name(dto.getName())
                    .file(bookUpload.get())
                    .author(dto.getAuthor())
                    .description(dto.getDescription())
                    .language(dto.getLanguage())
                    .genre(dto.getGenre())
                    .pageCount(dto.getPageCount())
                    .build());

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }


    public Book get(long id) {
        return null;
    }


    public void delete(long id) {
        Book book = this.get(id);

    }

    public void update(BookUpdateDTO dto) throws NotFoundException {
        Book book = dao.findById(dto.getId());
        if (book == null) {
            throw new NotFoundException("Book not found by id %s".formatted(dto.getId()));
        }
        if (Objects.nonNull(dto.getName())) {
            book.setName(dto.getName());
        }
        if (Objects.nonNull(dto.getDescription())) {
            book.setDescription(dto.getDescription());
        }
        if (Objects.nonNull(dto.getStatus())) {
            book.setStatus(dto.getStatus());
        }
        if (Objects.nonNull(dto.getDownloadCount())) {
            book.setDownloadCount(dto.getDownloadCount());
        }
        dao.update(book);
    }


    public List<Book> getAll() {
        return dao.findAll();
    }


    public List<Book> getAllByStatus(BookStatus pending) {
        if (pending == null) {
            throw new RuntimeException("Status can not be null");
        }
        return dao.findAllByStatus(pending);
    }


    public List<Book> getAll(String searchQuery, Integer page, Integer limit) {
        if (searchQuery == null)
            searchQuery = "";
        if (page == null)
            page = 1;
        if (limit == null)
            limit = 10;
        return dao.findAll(searchQuery, page, limit).get();

    }

    public static BookService getInstance() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }



    public Boolean hasNext(String search, Integer offset, Integer limit) {
        Integer size = dao.findNumberOfElementBySearch(search);
        return size > (offset * limit);
    }

    public Boolean hasNext(Genre genre, Integer offset, Integer limit) {
        Integer size = dao.findNumberOfElementByGenre(genre);
        return size > (offset * limit);
    }

    public Boolean hasPrevious(Integer offset) {
        return offset > 1;
    }

    public Integer totalPage(String search, Integer limit) {
        Integer size = dao.findNumberOfElementBySearch(search);
        return size / limit;
    }

    public Integer totalPage(Genre genre, Integer limit) {
        Integer size = dao.findNumberOfElementByGenre(genre);
        return size / limit;
    }

    public List<Book> getAllByGenre(Genre searchGenre, Integer page, Integer limit) {
        if (searchGenre == null)
            throw new RuntimeException("Genre Not supplied");
        if (page == null)
            page = 1;
        if (limit == null)
            limit = 10;
        return dao.findAll(searchGenre, page, limit).get();
    }


    public void updateDownloadCount(Long uploadFileId) {
        Book book = dao.findByUploadFileId(uploadFileId);
        book.setDownloadCount(book.getDownloadCount() + 1);
        dao.update(book);
    }
}
