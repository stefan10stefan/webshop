package com.controller;

import com.model.dto.ShopDTO;
import com.model.dto.UserDTO;
import com.service.ShopService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping(path="/{id}")
    public ShopDTO get(@PathVariable("id") Long id) {
        return shopService.get(id);
    }

    @GetMapping(path="/search")
    public List<ShopDTO> get(@RequestParam("term") String term) {
        return shopService.search(term);
    }

    @PostMapping(path="/")
    public ShopDTO add(@RequestBody ShopDTO user) {
        return  shopService.add(user);
    }

    @PutMapping(path="/")
    public ShopDTO edit(@RequestBody ShopDTO user) {
        return shopService.edit(user);
    }
}
