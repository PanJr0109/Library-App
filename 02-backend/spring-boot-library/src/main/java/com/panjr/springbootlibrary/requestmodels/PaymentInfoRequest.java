package com.panjr.springbootlibrary.requestmodels;

import lombok.Data;

@Data
public class PaymentInfoRequest {
    private int amount;
    private  String currency;
    private String receiptEmail;
}
