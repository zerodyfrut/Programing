
package com.example.command;

import java.util.List;
import lombok.Data;

@Data
public class OrderCommand {
    private List<OrderItem> orderItems;
    private Address address;
}
