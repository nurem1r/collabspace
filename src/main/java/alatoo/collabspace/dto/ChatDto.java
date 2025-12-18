package alatoo.collabspace.dto;

import alatoo.collabspace.entities.enums.ChatType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDto {
    private Long id;
    private ChatType type;
    private Long projectId;
    private LocalDateTime createdAt;
}