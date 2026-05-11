package Proyectito.demo.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Proyectito.demo.dto.UsuarioRegistradodto;
import Proyectito.demo.dto.Usuariodto;
import Proyectito.demo.exception.RecursoNoEncontrado;
import Proyectito.demo.mapper.UsuarioMapper;
import Proyectito.demo.model.Usuario;
import Proyectito.demo.model.UsuarioAuth;
import Proyectito.demo.repositories.UserAuthRepository;
import Proyectito.demo.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository useRep;
    
    private final UserAuthRepository uauthRep;

    private final UsuarioMapper userMapper;

    private final PasswordEncoder passwordEncoder;


    public UsuarioServiceImpl(UsuarioRepository useRep, UserAuthRepository uauthRep, UsuarioMapper userMapper, 
        PasswordEncoder passwordEncoder ){
            this.useRep = useRep;
            this.uauthRep = uauthRep;
            this.userMapper =  userMapper;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public Usuariodto create(Usuariodto usuariodto){
            Usuario usuario = userMapper.toUsuario(usuariodto);
            return userMapper.toDto(useRep.save(usuario));
        }

        @Override
        public List<Usuariodto> ListUsuarios(){
            return userMapper.todosList(useRep.findAll());
        }

        @Override
        public void delete(String id){
            if (!useRep.existsById(id)) {
                throw new RecursoNoEncontrado(id);
            }
            useRep.deleteById("Usuario no encontrado con id: "+id);
        }

        @Override
        public Usuariodto UsuarioByGmail(String correo){
            return useRep.findByCorreo(correo)
            .map(userMapper::toDto)
            .orElseThrow(()-> new RecursoNoEncontrado("No se encontro ningun usuario con el correo: "+correo));
        }

        @Transactional
        @Override
        public UsuarioRegistradodto crearUsuario(UsuarioRegistradodto dto) {
            Usuario perfil = Usuario.builder()
                .id(UUID.randomUUID().toString())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .correo(dto.getCorreo())
                .build();
        

        Usuario perfilUsuario = useRep.save(perfil);

        UsuarioAuth auth = new UsuarioAuth();
        auth.setId(perfilUsuario.getId());
        auth.setUser(dto.getUsuario());
        auth.setPass(passwordEncoder.encode(dto.getPassword()));
        auth.setRoles(dto.getRoles());

        uauthRep.save(auth);

        return dto;
        
        
    }
    @Transactional
        @Override
        public Usuariodto update(String id, Map<String, Object> campos){
         
            Usuario usuario = useRep.findById(id)
            .orElseThrow(()-> new RecursoNoEncontrado("No se encontre un usuario con el id: " + id));
            
            campos.forEach((campo, valor) -> {
                switch(campo){
                    case "nombre" -> usuario.setNombre((String) valor);
                    case "apellido" -> usuario.setApellido((String) valor);
                    case "correo " -> usuario.setCorreo((String) valor);
                }

            });
            return userMapper.toDto(useRep.save(usuario));
        }

}
