package com.kun.security.dto;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 22:35
 */
public class FileInfo {
    
    private String name;
    private String path;
    
    public FileInfo() {
    }
    
    public FileInfo(String name, String path) {
        this.name = name;
        this.path = path;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    @Override
    public String toString() {
        return "FileInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
