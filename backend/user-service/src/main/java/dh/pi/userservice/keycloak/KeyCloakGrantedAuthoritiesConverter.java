package dh.pi.userservice.keycloak;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class KeyCloakGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // ROLES
        Map<String, Map<String, List<String>>> resourcesAccess =
                (Map<String, Map<String, List<String>>>) source.getClaims().get("resource_access");
        if (resourcesAccess != null && !resourcesAccess.isEmpty()) {
            Map<String, List<String>> clientAccessRoles = resourcesAccess.get("users");
            authorities.addAll(clientAccessRoles != null ? extractRoles(clientAccessRoles) : List.of());
        }

        // SCOPES
        String scopes = (String) source.getClaims().get("scope");
        if (scopes != null) {
            authorities.addAll(extractScopes(scopes));
        }

        // GROUPS
        List<String> groups = (List<String>) source.getClaims().get("group");
        if (groups != null && !groups.isEmpty()) {
            authorities.addAll(extractGroups(groups));
        }

        return authorities;
    }

    private static Collection<GrantedAuthority> extractRoles(Map<String, List<String>> roles) {
        return  roles.get("roles")
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    private static Collection<GrantedAuthority> extractScopes(String scopes) {
        return Arrays.stream(scopes.split(" "))
                .map(scope -> new SimpleGrantedAuthority("SCOPE_" + scope))
                .collect(Collectors.toList());
    }

    private static Collection<GrantedAuthority> extractGroups(List<String> groups) {
        return groups.stream()
                .map(groupName -> groupName.substring(1))
                .map(groupName -> "GROUP_" + groupName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
