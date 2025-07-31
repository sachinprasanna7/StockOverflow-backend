package com.practice.StockOverflowBackend.controllers;

import com.practice.StockOverflowBackend.entities.Portfolio;

import com.practice.StockOverflowBackend.services.PortfolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@Controller
@RequestMapping(path="/portfolio")
public class PortfolioController {
    @Autowired
    public PortfolioService portfolioService;

    @PostMapping(path="/addPortfolio")
    public @ResponseBody void addPortfolio (@RequestBody Portfolio portfolio) {
        System.out.println(portfolio);
        portfolioService.addPortfolio(portfolio);
    }

    @GetMapping(path="/getPortfolios")
    public @ResponseBody List<Portfolio> getAllCompany(){
        return portfolioService.getPortfolios();

    }
}
