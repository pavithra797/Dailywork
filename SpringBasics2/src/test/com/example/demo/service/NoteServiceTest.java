package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Order1;
import com.example.demo.entity.OrderLine;
import com.example.demo.repository.Order1Repository;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;

    @Mock
    private Order1Repository order1Repository;

    @Mock
    private PaymentService paymentService;

    @Mock
    private EmailService emailService;

    @Test
    void testMissingItemName() {
        Order1 order = new Order1();

        OrderLine line = new OrderLine();
        line.setItem("");
        line.setPrice(100);
        line.setQuantity(1);

        List<OrderLine> lines = new ArrayList<>();
        lines.add(line);

        order.setOrderLines(lines);

        assertThrows(IllegalArgumentException.class, () -> {
            noteService.addOrder(order);
        });
    }

    @Test
    void testPriceGreaterOrEqualZero() {
        Order1 order = new Order1();

        OrderLine line = new OrderLine();
        line.setItem("Pen");
        line.setPrice(-10);
        line.setQuantity(1);

        List<OrderLine> lines = new ArrayList<>();
        lines.add(line);

        order.setOrderLines(lines);

        assertThrows(IllegalArgumentException.class, () -> {
            noteService.addOrder(order);
        });
    }

    @Test
    void testZeroOrderLines() {
        Order1 order = new Order1();
        order.setOrderLines(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> {
            noteService.addOrder(order);
        });
    }

    @Test
    void testQuantityMinimumOne() {
        Order1 order = new Order1();

        OrderLine line = new OrderLine();
        line.setItem("Book");
        line.setPrice(100);
        line.setQuantity(0);

        List<OrderLine> lines = new ArrayList<>();
        lines.add(line);

        order.setOrderLines(lines);

        assertThrows(IllegalArgumentException.class, () -> {
            noteService.addOrder(order);
        });
    }

    @Test
    void testValidOrder() {
        Order1 order = new Order1();

        OrderLine line = new OrderLine();
        line.setItem("Book");
        line.setPrice(100);
        line.setQuantity(1);

        List<OrderLine> lines = new ArrayList<>();
        lines.add(line);

        order.setOrderLines(lines);

        Order1 savedOrder = new Order1();
        savedOrder.setId(1L);

        Mockito.when(order1Repository.save(Mockito.any(Order1.class))).thenReturn(savedOrder);

        Long result = noteService.addOrder(order);

        assertNotNull(result);
        assertEquals(1L, result);
    }
}