package com.store.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class OrderRequest {
    @NotEmpty(message = "ISBN may not be empty")
    @Size(min = 13, max = 13, message = "ISBN must be 13 characters long")
    private String isbn;
    @Min(value = 1, message = "Quantity can't be less than 1")
    private int quantity;
}
