/*
package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.componentException.ComponentCantFindException;
import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.fasterxml.jackson.databind.node.LongNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ComponentRepository {

    private static Long id = 0L;
    private static final ComponentRepository componentRepository = new ComponentRepository();
    private static HashMap<Long, Component> componentDB = new HashMap<>();
    private static UserRepository userDB = UserRepository.getUserRepository();

    private ComponentRepository() {};

    public static ComponentRepository getComponentRepository() {return componentRepository;};

    public void clear() {
        id = 0L;
        componentDB.clear();
    }

    public long save(Component component) throws Exception {

        // 테스트를 위한 임시 user 삽입
        userDB.clear();
        User user = new User("hi", "hi", "hi", "hi");
        userDB.save(user);
        component.setUserIdx(1L);

        id++;
        component.setComponentId(id);
        componentDB.put(id, component);

        return id;
    }

    public List<Component> findComponentByProjectId(long projectId) {
        List<Component> components = new ArrayList<>();

        componentDB.forEach((key, value) -> {
            if (value.getProjectId() == projectId) {
                components.add(value);
            }
        });



        return components;
    }

    public long updateComponent(Long projectIdx, List<Component> components) throws ComponentCantFindException {
        if(!componentDB.containsKey(projectIdx))
            throw new ComponentCantFindException();

        componentDB.replace(projectIdx, (Component) components);
        return projectIdx;
    }

    public void deleteComponentById(long projectId) {
        List<Long> componentKey = new ArrayList<>();
        componentDB.forEach((key, value) -> {
            if (value.getProjectId() == projectId) {
                componentKey.add(key);
            }
        });

        for(Long key: componentKey)
            componentDB.remove(key);
    }


}
*/
