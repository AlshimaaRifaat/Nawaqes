package com.ibsvalleyn.outlet.task;


public interface PaymentStatusRequestListener {

    void onErrorOccurred();
    void onPaymentStatusReceived(String paymentStatus);
}
