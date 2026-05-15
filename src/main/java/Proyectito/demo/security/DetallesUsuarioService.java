package Proyectito.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import Proyectito.demo.model.UsuarioAuth;
import Proyectito.demo.repositories.UserAuthRepository;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    private final UserAuthRepository uar;

    public DetallesUsuarioService(UserAuthRepository uar){
        this.uar = uar;
    }

    @Override
        public UserDetails loadUserByUsername(String username) { // Es nombre del metodo loadUserByUsername es obligatorio
                                                             // porque: Es parte del contrato de Spring Security y
                                                             // Spring lo llama internamente

        UsuarioAuth usuario = uar.findByUser(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado sp"));

        return User.builder()
                .username(usuario.getUser()) // Establece el nombre de  usuario
                .password(usuario.getPass()) // Establece la contraseña (debería estar codificada)
                .roles(usuario.getRol().name())
                // Puedes asignar roles según
                // tu lógica
                .build();
    }

}
