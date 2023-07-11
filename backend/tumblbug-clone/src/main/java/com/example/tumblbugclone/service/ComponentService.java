package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.componentException.ComponentCantModify;
import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.repository.ComponentRepository;
import com.example.tumblbugclone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository, ProductRepository productRepository) {
        this.componentRepository = componentRepository;
        this.productRepository = productRepository;
    }

    public long saveComponent(Component component, long productId, long userIdx) throws ComponentCantModify {

        Product product = productRepository.findProductById(productId);

        if(product.getProject().getUser().getUserIdx() != userIdx)
            throw new ComponentCantModify();

        component.setProduct(product);
        return componentRepository.save(component);
    }

    public List<Component> readComponent(long productId) throws Exception {
        List<Component> componentList = componentRepository.findComponentByProductId(productId);
        return componentList;
    }

    public long updateComponent(Component component, long productId, long userIdx) throws ComponentCantModify {
        Product product = productRepository.findProductById(productId);
        if(product.getProject().getUser().getUserIdx() != userIdx)
            throw new ComponentCantModify();

        component.setProduct(product);
        return componentRepository.modify(component);
    }

    public void deleteComponent(long productId) throws Exception {
        List<Component> componentList = componentRepository.findComponentByProductId(productId);
        for(Component component : componentList) {
            componentRepository.delete(component.getComponentId());
        }

    }

    public void deleteComponentById(long componentId, long productId, long userIdx) throws Exception {

        Product product = productRepository.findProductById(productId);
        if(product.getProject().getUser().getUserIdx() != userIdx)
            throw new ComponentCantModify();

        componentRepository.delete(componentId);
    }
}
