package alatoo.collabspace.controllers;

import alatoo. collabspace.dto.MessageDto;
import alatoo.collabspace.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web. bind.annotation.*;

import java. util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestBody MessageDto dto) {
        return ResponseEntity.ok(service. create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageDto>> listByChat(@PathVariable Long chatId) {
        return ResponseEntity.ok(service.listByChat(chatId));
    }

    @GetMapping("/chat/{chatId}/paged")
    public ResponseEntity<Page<MessageDto>> listByChatPaged(@PathVariable Long chatId, Pageable pageable) {
        return ResponseEntity.ok(service. listByChatPaged(chatId, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity. noContent().build();
    }
}