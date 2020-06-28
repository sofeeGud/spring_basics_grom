package com.lesson6.hw.controller;

import org.springframework.stereotype.Controller;

import java.util.Collection;
@Controller
abstract class GeneralController<T> {
        String parseObjectList(Collection<T> list){
        StringBuilder sb = new StringBuilder();
        for(T t : list)
        sb.append(t.toString()).append("\n");
        return sb.toString();
        }
}
