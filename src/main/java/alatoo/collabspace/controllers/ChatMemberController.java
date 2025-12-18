package alatoo.collabspace.controllers;

import alatoo. collabspace.dto.ChatMemberDto;
import alatoo.collabspace.services.ChatMemberService;
import lombok. RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org. springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-members")
@RequiredArgsConstructor
public class ChatMemberController {
    private final ChatMemberService service;

    @PostMapping
    public ResponseEntity<ChatMemberDto> add(@RequestBody ChatMemberDto dto) {
        return ResponseEntity. ok(service.add(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMemberDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<ChatMemberDto>> listByChat(@PathVariable Long chatId) {
        return ResponseEntity.ok(service.listByChat(chatId));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ChatMemberDto>> listPaged(Pageable pageable) {
        return ResponseEntity.ok(service.listAllPaged(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}