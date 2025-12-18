package alatoo.collabspace.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}