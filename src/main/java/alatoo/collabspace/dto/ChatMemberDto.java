package alatoo.collabspace.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMemberDto {
    private Long id;
    private Long chatId;
    private Long userId;
}