package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.ChatDto;
import alatoo.collabspace. services.ChatService;
import lombok. RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org. springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService service;

    @PostMapping
    public ResponseEntity<ChatDto> create(@RequestBody ChatDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PostMapping("/private")
    public ResponseEntity<ChatDto> createPrivate(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        return ResponseEntity.ok(service. createPrivateChat(user1Id, user2Id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatDto> get(@PathVariable Long id) {
        return ResponseEntity. ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChatDto>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ChatDto>> listPaged(Pageable pageable) {
        return ResponseEntity.ok(service. listAllPaged(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}