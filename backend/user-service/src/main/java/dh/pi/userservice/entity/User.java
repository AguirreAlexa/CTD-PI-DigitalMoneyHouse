package dh.pi.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Preconditions.checkNotNull;


@ApiModel(description = "Detalles de usuario")
@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El campo firstName no puede estar vacio")
    private String firstName;

    @NotBlank(message = "El campo lastName no puede estar vacio")
    private String lastName;

    @NotBlank(message = "El campo dni no puede estar vacio")
    private String dni;

    @NotBlank(message = "El campo email no puede estar vacio")
    private String email;

    @NotBlank(message = "El campo phoneNumber no puede estar vacio")
    private String phoneNumber;

    @NotBlank(message = "El campo username no puede estar vacio")
    private String username;

    @NotBlank(message = "El campo password no puede estar vacio")
    private String password;

    @NotBlank(message = "El campo cvu no puede estar vacio")
    private String cvu;

    @NotBlank(message = "El campo alias no puede estar vacio")
    private String alias;

    @JsonIgnore
    private String keycloakId;


    public User(String firstName,String lastName, String dni, String email, String phoneNumber, String username, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dni = dni;
        this.email = email;
        this.username = username;
        this.password = password;
        this.alias = armarAlias();
        this.cvu = cadenaAleatoria(22);
    }


    /**
     * Genera cadena aleatoria que define el cvu
     * @param longitud: Cantidad de caracteres caracteristico del cvu
     */
    static String cadena;
    public static String cadenaAleatoria(int longitud) {
        cadena ="";
        String numeros = "1234567890";

        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, numeros.length() - 1);
            char caracterAleatorio = numeros.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    /**
     * Genera alias descriptivo para el usuario
     */
    static ArrayList<String> words = new ArrayList<>();
    static ArrayList<String> preAlias = new ArrayList<>();
    public static ArrayList<String> leerTexto(){
        words.clear();
        File arch;
        FileInputStream fis;
        try{
            arch = new File("user-service/src/main/resources/cvuWords.txt");
            System.out.println(arch.getAbsolutePath());
            fis = new FileInputStream(arch);
            Scanner in = new Scanner(fis);
            while(in.hasNextLine()){
                String line = in.nextLine();
                words.add(line);
            }
        }catch (Exception e){
            System.out.println("Error");
        }
        return words;
    }
    public static ArrayList<String> seleccionarPalabrasRandom() {
        preAlias.clear();
        if (words.size() > 0) {
            for (int i = 0; i < 3; i++) {
                Random rand = new Random();
                int n = rand.nextInt(words.size() - 1);
                preAlias.add(words.get(n));
            }
        }
        return preAlias;
    }
    public static String armarAlias(){
        leerTexto();
        seleccionarPalabrasRandom();
        String alias;
        alias = String.join(".", preAlias);
        return alias;
    }

    /**
     * Métodos requeridos para security
     */
    //@JsonDeserialize(using = CustomAuthorityDeserializer.class)
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Preconditions.checkArgument(username.length() > 1, "The username must have more than 1 character");
        this.username = checkNotNull(username);
    }
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Preconditions.checkArgument(password != null && !password.isEmpty());
        this.password = password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    //Otros metodos
    public static ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static <T> T fromJSON(String json, Class<T> type) throws JsonProcessingException {
        return objectMapper().readValue(json, type);
    }

    public static <T> T fromStringTo(String value, Class<T> type) {
        Object conversion = "";
        if (type == Integer.class) {
            conversion = Integer.parseInt(value);
        } else if (type == Float.class) {
            conversion = Float.parseFloat(value);
        } else if (type == Double.class) {
            conversion = Double.parseDouble(value);
        } else if (type == Boolean.class) {
            conversion = Boolean.parseBoolean(value);
        } else if (type == String.class) {
            conversion = value;
        } else {
            return type.cast(conversion);
        }
        return type.cast(conversion);
    }

    private static <T> T getAttribute(UserRepresentation userRepresentation, String property, Class<T> type) {
        Map<String, List<String>> attributes = userRepresentation.getAttributes();
        List<String> properties = attributes.get(property);
        String p = properties.get(0);
        return type.cast(fromStringTo(p, type));
    }

    public static User toUser(UserRepresentation userRepresentation) {
        User user = new User();
        user.setKeycloakId(userRepresentation.getId());
        user.setUsername(userRepresentation.getUsername());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setEmail(userRepresentation.getEmail());
        user.setDni(getAttribute(userRepresentation, "dni", String.class));
        user.setPhoneNumber(getAttribute(userRepresentation, "phoneNumber", String.class));
        user.setCvu(getAttribute(userRepresentation, "cvu", String.class));
        user.setAlias(getAttribute(userRepresentation, "alias", String.class));

        return user;
    }
}
