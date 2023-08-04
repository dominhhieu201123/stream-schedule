package dev.danvega.streamschedule.controller;

import dev.danvega.streamschedule.exception.LiveStreamNotFoundException;
import dev.danvega.streamschedule.model.LiveStream;
import dev.danvega.streamschedule.respository.LiveStreamRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/streams")
public class LiveStreamController {

    private final LiveStreamRespository repository;

    @Autowired
    public LiveStreamController(LiveStreamRespository repository) {
        this.repository = repository;
    }

    //GET http://localhost:8080/streams

    @GetMapping
    public List<LiveStream> findAll() {
        return repository.findAll();
    }

    //GET http://localhost:8080/streams/f51b34aa-6b65-4656-9c99-11413791e236

    @GetMapping("/{id}")
    public LiveStream findById(@PathVariable String id) throws LiveStreamNotFoundException {
        return repository.findById(id);
    }

    //POST http://localhost:8080/streams

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LiveStream create(@Valid @RequestBody LiveStream stream) {
        return repository.create(stream);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody LiveStream stream, @PathVariable String id) {
        repository.update(stream,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }



}
