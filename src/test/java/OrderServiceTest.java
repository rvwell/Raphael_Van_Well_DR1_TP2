import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import org.example.OrderService;
import org.example.PaymentProcessor;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    private PaymentProcessor paymentProcessorMock;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        paymentProcessorMock = mock(PaymentProcessor.class);
        orderService = new OrderService(paymentProcessorMock);
    }

    @Test
    void testProcessOrder_PaymentApproved() {
        when(paymentProcessorMock.processPayment(100.0)).thenReturn(true);
        boolean result = orderService.processOrder(100.0);
        assertTrue(result, "Pedido confirmado");
        verify(paymentProcessorMock, times(1)).processPayment(100.0);
    }

    @Test
    void testProcessOrder_PaymentDeclined() {
        when(paymentProcessorMock.processPayment(50.0)).thenReturn(false);
        boolean result = orderService.processOrder(50.0);
        assertFalse(result, "Pedido recusado");
    }
}
