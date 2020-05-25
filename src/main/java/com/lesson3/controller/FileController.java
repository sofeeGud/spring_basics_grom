package com.lesson3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson3.BadRequestException;
import com.lesson3.model.File;
import com.lesson3.model.Storage;
import com.lesson3.service.FileService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFile")
    public ResponseEntity<String> save(@RequestBody String json) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = mapper.readValue(json, File.class);
            return new ResponseEntity<>(fileService.save(file).toString(), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findFile/{id}")
    public ResponseEntity<String> findById(@PathVariable String id) {

        try {
            File file = fileService.findById(Long.parseLong(id));

            return new ResponseEntity<>(file.toString(), HttpStatus.OK);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateFile")
    public ResponseEntity<String> update(@RequestBody String json) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            File file = mapper.readValue(json, File.class);
            return new ResponseEntity<>(fileService.update(file).toString(), HttpStatus.OK);
        } catch (BadRequestException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteFile/{id}")
    public ResponseEntity<String> delete(@RequestParam(value = "storageId") long storageId,
                                         @RequestParam(value = "fileId") long fileId) {

        try {
            fileService.delete(storageId, fileId);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/putFile")
    public ResponseEntity<File> putFile(@RequestParam(value = "storage") Storage storage,
                                        @RequestParam(value = "file") File file) {

        try {

            return new ResponseEntity<>(fileService.put(file, storage), HttpStatus.OK);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam(value = "storageId") long storageId,
                                             @RequestParam(value = "fileId") long fileId) {
        try {

            fileService.delete(storageId, fileId);
            return new ResponseEntity<>("File was deleted from storage", HttpStatus.OK);
        } catch (BadRequestException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferAll")
    public ResponseEntity<String> transferAll(@RequestParam(value = "from") long stIdFrom,
                                              @RequestParam(value = "to") long stIdTo) {
        try {

            int count = fileService.transferAll(stIdFrom, stIdTo);

            return new ResponseEntity<>(count + " files was transferred successfully", HttpStatus.OK);
        } catch (BadRequestException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/transferFile")
    public ResponseEntity<String> transferFile(@RequestParam(value = "from") long stIdFrom,
                                              @RequestParam(value = "to") long stIdTo,
                                              @RequestParam(value = "fileId") long fileId) {
        try {

            fileService.transferFile(stIdTo, fileId);

            return new ResponseEntity<>("File was transferred successfully", HttpStatus.OK);
        } catch (BadRequestException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}