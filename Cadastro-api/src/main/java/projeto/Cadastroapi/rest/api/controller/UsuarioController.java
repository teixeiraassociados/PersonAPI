package projeto.Cadastroapi.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.Cadastroapi.rest.api.model.UsuarioModel;
import projeto.Cadastroapi.rest.api.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // Endpoint para consultar um usuário por código
    @SuppressWarnings("rawtypes")
    @GetMapping(path = "/api/usuario/{codigo}")
    public ResponseEntity consultar(@PathVariable("codigo") Integer codigo) {
        return repository.findById(codigo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para obter o nome de um usuário por código
    @GetMapping(path = "/api/nome/{codigo}")
    public String getName(@PathVariable("codigo") Integer codigo) {
        return repository.getName(codigo);
    }

    // Endpoint para obter o telefone de um usuário por código
    @GetMapping(path = "/api/telefone/{codigo}")
    public Long getPhone(@PathVariable("codigo") Integer codigo) {
        return repository.getPhone(codigo);
    }

    // Endpoint para obter o CPF de um usuário por código
    @GetMapping(path = "/api/cpf/{codigo}")
    public Long getCpf(@PathVariable("codigo") Integer codigo) {
        return repository.getCpf(codigo);
    }

    // Endpoint para obter o RG de um usuário por código
    @GetMapping(path = "/api/rg/{codigo}")
    public Long getRg(@PathVariable("codigo") Integer codigo) {
        return repository.getRg(codigo);
    }

    // Endpoint para obter a nacionalidade de um usuário por código
    @GetMapping(path = "/api/nacionalidade/{codigo}")
    public String getNacionality(@PathVariable("codigo") Integer codigo) {
        return repository.getNacionality(codigo);
    }

    // Endpoint para obter a idade de um usuario por codigo
    @GetMapping(path = "/api/idade/{codigo}")
    public Long getAge(@PathVariable("codigo") Integer codigo){
        return repository.getAge((codigo));
    }

    // Endpoint para salvar um usuário
    @PostMapping(path = "/api/usuario/salvar")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
        return repository.save(usuario);
    }

    // Endpoint para obter todos os usuários
    @GetMapping(path = "/api/usuarios")
    public ResponseEntity<List<Map<String, Object>>> listarUsuarios(@RequestParam(required = false) List<String> atributos) {
        List<UsuarioModel> usuarios = (List<UsuarioModel>) repository.findAll();
        List<Map<String, Object>> resultados = usuarios.stream()
                .map(usuario -> criarDTO(usuario, atributos))
                .collect(Collectors.toList());

        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(resultados);
        }
    }

    // Método privado para criar um DTO com os atributos especificados
    private Map<String, Object> criarDTO(UsuarioModel usuario, List<String> atributos) {
        Map<String, Object> dto = new HashMap<>();
        if (atributos == null || atributos.isEmpty()) {
            dto.put("id", usuario.getCode());
            dto.put("nome", usuario.getName());
            dto.put("telefone", usuario.getPhone());
            dto.put("cpf", usuario.getCpf());
            dto.put("rg", usuario.getRg());
            dto.put("nacionalidade", usuario.getNacionality());
            // Adicione mais atributos conforme necessário
        } else {
            atributos.forEach(atributo -> {
                switch (atributo) {
                    case "id":
                        dto.put("id", usuario.getCode());
                        break;
                    case "nome":
                        dto.put("nome", usuario.getName());
                        break;
                    case "telefone":
                        dto.put("telefone", usuario.getPhone());
                        break;
                    case "cpf":
                        dto.put("cpf", usuario.getCpf());
                        break;
                    case "rg":
                        dto.put("rg", usuario.getRg());
                        break;
                    case "nacionalidade":
                        dto.put("nacionalidade", usuario.getNacionality());
                        break;
                    case "idade":
                        dto.put("idade", usuario.getAge());
                        break;
                    // Adicione mais casos conforme necessário
                }
            });
        }
        return dto;
    }


    // Endpoint para obter atributos específicos de um usuário por código
    @GetMapping(path = "/api/mostrar")
    public ResponseEntity<Map<String, Object>> getCode(@RequestParam("id") int id, @RequestParam("atributos") List<String> atributos) {
        UsuarioModel usuario = repository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        // Cria um mapa para armazenar os atributos solicitados e seus valores
        Map<String, Object> atributosUsuario = new HashMap<>();

        // Adiciona os atributos solicitados ao mapa
        for (String atributo : atributos) {
            switch (atributo) {
                case "nome":
                    atributosUsuario.put("nome", usuario.getName());
                    break;
                case "cpf":
                    atributosUsuario.put("cpf", usuario.getCpf());
                    break;
                case "rg":
                    atributosUsuario.put("rg", usuario.getRg());
                    break;
                case "telefone":
                    atributosUsuario.put("telefone", usuario.getPhone());
                    break;
                case "nacionalidade":
                    atributosUsuario.put("nacionalidade", usuario.getNacionality());
                    break;
                case "idade":
                    atributosUsuario.put("idade", usuario.getAge());
                    break;
                // Adicione mais casos conforme necessário
            }
        }

        return ResponseEntity.ok(atributosUsuario);
    }

}
