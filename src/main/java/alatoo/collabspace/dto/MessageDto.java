package alatoo.collabspace.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private Long chatId;
    private Long senderId;
    private String content;
    private LocalDateTime createdAt;
}