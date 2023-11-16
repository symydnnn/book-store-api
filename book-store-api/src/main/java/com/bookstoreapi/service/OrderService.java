package com.bookstoreapi.service;

import com.bookstoreapi.dao.BookDAO;
import com.bookstoreapi.dao.OrderDAO;
import com.bookstoreapi.dao.OrderRequest;
import com.bookstoreapi.dao.UserDAO;
import com.bookstoreapi.entity.Book;
import com.bookstoreapi.entity.Order;
import com.bookstoreapi.entity.OrderBook;
import com.bookstoreapi.entity.User;
import com.bookstoreapi.enums.MessageTypeEnums;
import com.bookstoreapi.exception.BookNotFoundException;
import com.bookstoreapi.exception.InsufficientStockException;
import com.bookstoreapi.exception.MinimumPriceForOrderException;
import com.bookstoreapi.exception.WrongRequestParametersException;
import com.bookstoreapi.repository.OrderRepository;
import com.bookstoreapi.security.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.security.auth.message.AuthException;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final IUserService userService;
    private final IBookService bookService;
    private final IOrderBookService orderBookService;
    private final ObjectMapper mapper;

    private final SecurityService securityService;


    @Override
    @Transactional
    public OrderDAO save(OrderRequest orderRequest) {
        try {

            String email = securityService.getCurrenUserEmail();

            if (email == null) {
                throw new AuthException();
            }

            if (ObjectUtils.isEmpty(orderRequest)) {
                throw new WrongRequestParametersException(MessageTypeEnums.WRONG_REQUEST_PARAMETERS.getMessage());
            }

            User user = userService.getByUserEmail(email);
            List<BookDAO> bookList = new ArrayList<>();

            BigDecimal totalPrice = BigDecimal.ZERO;

            Order order = orderRepository.save(Order.builder()
                    .user(user)
                    .totalPrice(totalPrice)
                    .orderDate(new Date())
                    .build());

            for (String isbn : orderRequest.getBookISBNList()) {
                if (isbn == null) {
                    throw new WrongRequestParametersException(MessageTypeEnums.WRONG_REQUEST_PARAMETERS.getMessage()
                            + " Book ISBN should be not empty.");
                }
                Book book = mapper.convertValue(bookService.getByISBN(isbn), Book.class);
                if (book.getStockQuantity() < 1) {
                    throw new InsufficientStockException(MessageTypeEnums.INSUFFICIENT_STOCK.getMessage() + " Book name is "
                            + book.getTitle() + " and ISBN is " + book.getISBN() + ".");
                }
                book.setStockQuantity(book.getStockQuantity() - 1);
                bookService.update(isbn, mapper.convertValue(book, BookDAO.class));

                OrderBook newOrderBook = orderBookService.save(OrderBook.builder()
                        .book(book)
                        .order(order)
                        .build());
                bookList.add(mapper.convertValue(newOrderBook.getBook(), BookDAO.class));
                totalPrice = totalPrice.add(book.getPrice());
            }

            if (ObjectUtils.isEmpty(bookList)) {
                throw new BookNotFoundException(MessageTypeEnums.BOOK_NOT_FOUND.getMessage() + " All List is empty.");
            }

            order.setTotalPrice(totalPrice);
            if (totalPrice.compareTo(BigDecimal.valueOf(0.25)) < 0) {
                throw new MinimumPriceForOrderException(MessageTypeEnums.MINIMUM_PRICE_FOR_ORDER.getMessage());
            }

            Order newOrder = orderRepository.save(mapper.convertValue(order, Order.class));
            log.info("OrderService - save : orderRequest info saved. Total Price : {}", order.getTotalPrice());

            return OrderDAO.builder().id(newOrder.getId())
                    .user(mapper.convertValue(user, UserDAO.class))
                    .listOfBooks(bookList)
                    .totalPrice(newOrder.getTotalPrice())
                    .orderDate(newOrder.getOrderDate())
                    .createdAt(newOrder.getCreatedAt())
                    .updatedAt(newOrder.getUpdatedAt())
                    .build();
        } catch (AuthException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDAO> getAllByUserId(long userId) {
        List<Order> orders = orderRepository.findOrdersByUserIdOrderByUpdatedAtDesc(userId);
        if (!ObjectUtils.isEmpty(orders)) {
            log.info("OrderService - getAllByUserId : Total {} size orders getting. ", orders.size());
            List<OrderDAO> orderDAOS = new ArrayList<>();
            for (Order order : orders) {
                UserDAO userDAO = mapper.convertValue(order.getUser(), UserDAO.class);
                userDAO.setPassword(null);

                List<BookDAO> bookDAOS = new ArrayList<>();

                for (OrderBook orderBook : order.getListOfBooks()) {
                    Book book = orderBook.getBook();
                    bookDAOS.add(BookDAO.builder()
                            .ISBN(book.getISBN())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .price(book.getPrice())
                            .build());
                }

                orderDAOS.add(OrderDAO.builder()
                        .id(order.getId())
                        .user(userDAO)
                        .listOfBooks(bookDAOS)
                        .totalPrice(order.getTotalPrice())
                        .orderDate(order.getOrderDate())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .build());
            }
            return orderDAOS;
        }
        return new ArrayList<>();
    }

    @Override
    public OrderDAO getById(long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (!ObjectUtils.isEmpty(order)) {
            log.info("OrderService - getById : Total Price : {} ", order.get().getTotalPrice());

            UserDAO userDAO = mapper.convertValue(order.get().getUser(), UserDAO.class);
            userDAO.setPassword(null);

            List<BookDAO> bookDAOS = new ArrayList<>();

            for (OrderBook orderBook : order.get().getListOfBooks()) {
                Book book = orderBook.getBook();
                bookDAOS.add(BookDAO.builder()
                        .ISBN(book.getISBN())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .price(book.getPrice())
                        .build());
            }

            return OrderDAO.builder()
                    .id(order.get().getId())
                    .user(userDAO)
                    .listOfBooks(bookDAOS)
                    .totalPrice(order.get().getTotalPrice())
                    .orderDate(order.get().getOrderDate())
                    .createdAt(order.get().getCreatedAt())
                    .updatedAt(order.get().getUpdatedAt())
                    .build();
        }
        return null;
    }


}
