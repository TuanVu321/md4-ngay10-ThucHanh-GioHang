package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductCart;
import com.codegym.serviece.productServiece.IProductServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("listCart")
public class ProductController {
    @Autowired
    IProductServiece productServiece;
    @ModelAttribute("listCart")
    public List<ProductCart> listCart(){
      return new ArrayList<ProductCart>();
    }


    @GetMapping("/")
    public ModelAndView showListProduct(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/home");
        Page<Product> listProduct = productServiece.showAll(pageable);
        modelAndView.addObject("listProduct",listProduct);
        return modelAndView;
    }
    @GetMapping("/showProduct/{id}")
    public ModelAndView showProduct(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("product");
        Product product = productServiece.findById(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }
    @GetMapping("/addProduct/{id}")
    public ModelAndView addProduct(@PathVariable Long id,@ModelAttribute("listCart")List<ProductCart> listCart ){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        Product product = productServiece.findById(id);
        ProductCart productCart = new ProductCart();
        productCart.setId(product.getId());
        productCart.setName(product.getName());
        productCart.setPrice(product.getPrice());
        productCart.setQuantity((long)1);
        listCart.add(productCart);
        return modelAndView;
    }
    @GetMapping("/showList")
    public ModelAndView showProductBuy(@ModelAttribute("listCart")List<ProductCart> listCart ){
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("listCart",listCart);
        return modelAndView;
    }




//    @GetMapping("/buyProduct/{id}")
//    public ModelAndView showCart(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView();
//    }
}
