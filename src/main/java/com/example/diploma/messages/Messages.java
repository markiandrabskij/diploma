package com.example.diploma.messages;

import org.springframework.stereotype.Component;

//@Configuration("messages.properties")
@Component
public class Messages {
    public String invalidTransparency = "transparency must be <= 1";
    public String allFieldsMustBeNotEmpty = "all fields must be not empty";

}
