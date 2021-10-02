package com.clases.security.usuarios.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Base64;
import java.util.stream.Stream;

@Service
public class ImageStoreService {



    //ruta base de las imagenes
    private final Path root = Paths.get("imagenes");

    public void init() {
        try {
            File directory = new File("imagenes");
            if (!directory.exists()){
                directory.mkdir();
            }

            System.out.println("inicializada ruta de imagenes");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al inicializar la carpeta "+root.toString());
        }
    }

    public void save(MultipartFile file,String name) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(name),StandardCopyOption.REPLACE_EXISTING );
        } catch (Exception e) {
            throw new RuntimeException("Error no se pudo guardar el archivo: " + e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Error al leer el archivo");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String base64(String filePath) {
        String base64File = "";
        File file = new File(filePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a file from file system
            byte fileData[] = new byte[(int) file.length()];
            imageInFile.read(fileData);
            base64File = Base64.getEncoder().encodeToString(fileData);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado " + e);
        } catch (IOException ioe) {
            System.out.println("Error no controlado " + ioe);
        }
        return base64File;
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar archivos");
        }
    }

}
