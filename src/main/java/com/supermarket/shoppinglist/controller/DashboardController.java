package com.supermarket.shoppinglist.controller;
import com.supermarket.shoppinglist.service.DashboardService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
 private final DashboardService service;
 public DashboardController(DashboardService service){this.service=service;}
 @GetMapping("/summary") public Map<String,Long> summary(){return service.summary();}
}
