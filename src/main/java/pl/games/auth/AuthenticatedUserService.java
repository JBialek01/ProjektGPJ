package pl.games.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    public String getAuthenticatedUserId(@AuthenticationPrincipal OAuth2User user) {
        if (user != null) {
            return user.getAttribute("sub"); // Pobiera ID użytkownika z Google OAuth2
        }
        return null;
    }
}
