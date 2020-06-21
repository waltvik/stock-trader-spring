package com.codecool.stocktraderspring.controller;

import com.codecool.stocktraderspring.logger.Logger;
import com.codecool.stocktraderspring.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.InputMismatchException;

@RestController
@RequestMapping("/buy")
public class StockController {

    @Autowired
    private Logger logger;

    @Autowired
    private Trader trader;

    @GetMapping("/{stock}/{price}")
    public String getStock(@PathVariable("stock") String stock, @PathVariable("price") double price) {


        try {
            boolean purchased = trader.buy(stock, price);
            if (purchased) {
                logger.log("Purchased stock!");
                return "Purchased stock! " + price + stock;
            }
            else {
                logger.log("Couldn't buy the stock at that price.");
                return "Couldn't buy the stock at that price.";
            }
        } catch (Exception e) {
            logger.log("There was an error while attempting to buy the stock: " + e.getMessage());
            return "There was an error while attempting to buy the stock. :(";
        }

    }
}
