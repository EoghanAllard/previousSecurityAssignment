package com.example.previoussecurityassignment.controller;

import com.example.previoussecurityassignment.model.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.previoussecurityassignment.service.ProductService;

@Controller
@RequestMapping(value = {"/product","/product/"})
public class productController {

    @Autowired
    private ProductService productService;

    Product product = new Product();

    @RequestMapping("")
    public ModelAndView displayAllProducts() {return new ModelAndView("/allProducts", "productList",productService.getAllProducts());}

    @RequestMapping("/delete/{code}")
    public ModelAndView deleteProduct(@PathVariable("code") String code, ModelAndView mav){

        productService.deleteAProduct(code);
        mav.addObject("productList",productService.getAllProducts());
        mav.setViewName("allProducts");

        return mav;
    }

    @RequestMapping("/insert")
    public ModelAndView displayInsert(){return new ModelAndView("/addProduct", "aProduct", product);}

    @RequestMapping("/addProduct")
    public ModelAndView addProduct(@Valid @ModelAttribute("aProduct") Product p, BindingResult result){
        if (result.hasErrors()) {

            return new ModelAndView("/addProduct");
        }

        productService.addAProduct(p);

        return new ModelAndView("/allProducts", "productList",productService.getAllProducts());
    }
}
