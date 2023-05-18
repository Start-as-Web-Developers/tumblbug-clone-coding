package com.example.tumblbugclone.service;

import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public long saveComponent(Component component) {
        return componentRepository.save(component);
    }

    public List<Component> readComponent(long productId) throws Exception {
        List<Component> componentList = componentRepository.findComponentByProductId(productId);
        for(Component component : componentList) {
            component.setProduct(null);
        }

        return componentList;
    }

    public long updateComponent(Component component) {
        return componentRepository.modify(component);
    }

    public void deleteComponent(long productId) throws Exception {
        List<Component> componentList = componentRepository.findComponentByProductId(productId);
        for(Component component : componentList) {
            componentRepository.delete(component.getComponentId());
        }

    }

    public void deleteComponentById(long componentId) throws Exception {
        componentRepository.delete(componentId);
    }
}
