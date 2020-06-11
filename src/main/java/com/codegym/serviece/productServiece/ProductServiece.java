package com.codegym.serviece.productServiece;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiece implements IProductServiece{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> showAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product model) {
        productRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
