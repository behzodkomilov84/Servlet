package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Credentials {
    private String login;
    private String password;
}
