package org.zx.tree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zx.tree.entity.Reflection;
import org.zx.tree.repository.ReflectionRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/reflections")
@CrossOrigin(origins = "*")
public class ReflectionController {

    @Autowired
    private ReflectionRepository reflectionRepository;

    @GetMapping
    public List<Reflection> getAllReflections() {
        return reflectionRepository.findAllByOrderByCreateTimeDesc();
    }

    @GetMapping("/published")
    public List<Reflection> getPublishedReflections() {
        return reflectionRepository.findByPublishedTrueOrderByCreateTimeDesc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reflection> getReflectionById(@PathVariable Long id) {
        return reflectionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reflection createReflection(@RequestBody Reflection reflection) {
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm:ss"));
        reflection.setPublishDate(currentDate);
        return reflectionRepository.save(reflection);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reflection> updateReflection(@PathVariable Long id, @RequestBody Reflection reflection) {
        return reflectionRepository.findById(id)
                .map(existing -> {
                    existing.setAuthorName(reflection.getAuthorName());
                    existing.setTitle(reflection.getTitle());
                    existing.setContent(reflection.getContent());
                    existing.setImageUrl(reflection.getImageUrl());
                    existing.setImageCaption(reflection.getImageCaption());
                    existing.setSecondImageUrl(reflection.getSecondImageUrl());
                    existing.setSecondImageCaption(reflection.getSecondImageCaption());
                    existing.setThirdImageUrl(reflection.getThirdImageUrl());
                    existing.setThirdImageCaption(reflection.getThirdImageCaption());
                    String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm:ss"));
                    existing.setPublishDate(currentDate);
                    existing.setHighlightQuote(reflection.getHighlightQuote());
                    existing.setPublished(reflection.getPublished());
                    return ResponseEntity.ok(reflectionRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReflection(@PathVariable Long id) {
        if (reflectionRepository.existsById(id)) {
            reflectionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}