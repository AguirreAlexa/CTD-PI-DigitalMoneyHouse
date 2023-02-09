package dh.pi.userservice.keycloak;

import dh.pi.userservice.entity.User;
import dh.pi.userservice.entity.utils.AccessKeycloak;
import dh.pi.userservice.exception.AlreadyExistsException;
import dh.pi.userservice.exception.BadRequestException;
import dh.pi.userservice.exception.AuthenticationException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Service
public class KeyCloakService {

    @Value("${d-h-m.keycloak.realm}")
    private String realm;

    @Value("${d-h-m.keycloak.serverUrl}")
    private String serverUrl;

    @Value("${d-h-m.keycloak.clientId}")
    private String clientId;

    @Value("${d-h-m.keycloak.clientSecret}")
    private String clientSecret;

    @Value("${d-h-m.keycloak.tokenEndpoint}")
    private String tokenEndpoint;

    @Autowired
    private KeyCloakConfiguration keyCloakConfiguration;

    public RealmResource getRealm() {
        return keyCloakConfiguration.getInstance().realm(realm);
    }

    public User createUser(User user) throws Exception {

        UserRepresentation userRepresentation = new UserRepresentation();
        Map<String, List<String>> attributes = new HashMap<>();

        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEmailVerified(false);
        attributes.put("phoneNumber", Collections.singletonList(String.valueOf(user.getPhoneNumber())));
        attributes.put("dni", Collections.singletonList(String.valueOf(user.getDni())));
        attributes.put("cvu", Collections.singletonList(String.valueOf(user.getCvu())));
        attributes.put("alias", Collections.singletonList(String.valueOf(user.getAlias())));
        userRepresentation.setAttributes(attributes);
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Collections.singletonList(newCredential(user.getPassword())));

        Response response = getRealm().users().create(userRepresentation);

        if (response.getStatus() == 409) {
            throw new AlreadyExistsException("username or email already used");
        }
        if (response.getStatus() >= 400) {
            throw new BadRequestException("Internal error has occurred, please try later");
        }

        userRepresentation.setId(CreatedResponseUtil.getCreatedId(response));
        User userSaved = User.toUser(userRepresentation);

        return userSaved;
    }

    private CredentialRepresentation newCredential(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(password);
        return credential;
    }

    private UserRepresentation updateUserRepresentation(UserRepresentation userRepresentation, User user) {
        if (user.getUsername() != null && userRepresentation.getUsername() != user.getUsername()) {
            userRepresentation.setUsername(user.getUsername());
        }
        if (user.getFirstName() != null && user.getFirstName() != userRepresentation.getFirstName()) {
            userRepresentation.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null && userRepresentation.getLastName() != user.getLastName()) {
            userRepresentation.setLastName(user.getLastName());
        }
        if (user.getEmail() != null && userRepresentation.getEmail() != user.getEmail()) {
            userRepresentation.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userRepresentation.setCredentials(Collections.singletonList(newCredential(user.getPassword())));
        }
        if (user.getPhoneNumber() != null && !userRepresentation.getAttributes().get("phoneNumber").equals(user.getPhoneNumber())){
            userRepresentation.getAttributes().put("phoneNumber", Collections.singletonList(user.getPhoneNumber()));
        }
        if (user.getDni() != null && !userRepresentation.getAttributes().get("dni").equals(user.getDni())) {
            userRepresentation.getAttributes().put("dni", Collections.singletonList(String.valueOf(user.getDni())));
        }
        return userRepresentation;
    }

    @SneakyThrows
    public User updateDataUser(User userSaved, User user) {
        UserResource userResource = getRealm().users().get(userSaved.getKeycloakId());

        UserRepresentation userRepresentationUpdated = updateUserRepresentation(userResource.toRepresentation(), user);
        User userUpdated = User.toUser(userRepresentationUpdated);

        if (userUpdated.getEmail().equals(user.getEmail())){
            throw new AlreadyExistsException("No se puede modificar el email");
        }else{
            getRealm().users().get(userSaved.getKeycloakId()).update(userRepresentationUpdated);
            return userUpdated;
        }
    }

    public String login(String email, String password) throws Exception {
        try{
            AccessKeycloak tokenAccess = null;

            Keycloak keycloakClient = null;
            TokenManager tokenManager = null;

            keycloakClient = Keycloak.getInstance(serverUrl, realm, email,
                    password, clientId, clientSecret);

            tokenManager = keycloakClient.tokenManager();

            tokenAccess = AccessKeycloak.builder()
                    .accessToken(tokenManager.getAccessTokenString())
                    .expiresIn(tokenManager.getAccessToken().getExpiresIn())
                    .refreshToken(tokenManager.refreshToken().getRefreshToken())
                    .scope(tokenManager.getAccessToken().getScope())
                    .build();

            return tokenAccess.getAccessToken();

        }catch (Exception e){
            throw new AuthenticationException("Contraseña incorrecta");
        }

    }

    public void logout(String userId) {
        getRealm().users().get(userId).logout();
    }
}
