package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.userexception.ComponentCantFindException;
import com.example.tumblbugclone.Exception.userexception.ProjectCantFindException;
import com.example.tumblbugclone.model.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComponentRepository {

    private static Long id = 0L;
    private static final ComponentRepository componentRepository = new ComponentRepository();
    private static MultiValueMap<Long, Component> componentDB = new LinkedMultiValueMap<>();;

    private ComponentRepository() {};

    public static ComponentRepository getComponentRepository() {return  componentRepository; };

    public void clear() {
        id = 0L;
        componentDB.clear();
    }

    public long save(Component component) throws Exception {
        id++;
        component.setComponentId(id);
        componentDB.add(component.getProductId(), component);

        return id;
    }

    public List<Component> findComponentsById(Long projectIdx) {

        return componentDB.get(projectIdx);
    }

    public void deleteAllComponent(Long projectIdx) {
        componentDB.remove(projectIdx);
    }

    public long updateComponent(Long projectIdx, List<Component> components) throws ComponentCantFindException {
        if(!componentDB.containsKey(projectIdx))
            throw new ComponentCantFindException();

        componentDB.replace(projectIdx, components);
        return projectIdx;
    }


}
