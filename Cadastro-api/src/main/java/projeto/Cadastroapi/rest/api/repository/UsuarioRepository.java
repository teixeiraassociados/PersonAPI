package projeto.Cadastroapi.rest.api.repository;

import org.springframework.data.repository.CrudRepository;
import projeto.Cadastroapi.rest.api.model.UsuarioModel;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {

    public default String getName(int Id) {
        Optional<UsuarioModel> usuarioModel = this.findById(Id);
        return usuarioModel.map(UsuarioModel::getName).orElse(null);
    }

    public default Long getPhone(int Id) {
        Optional<UsuarioModel> usuarioModel = this.findById(Id);
        return usuarioModel.map(UsuarioModel::getPhone).orElse(null);
    }

    public default Long getCpf(int Id) {
        Optional<UsuarioModel> usuarioModel = this.findById(Id);
        return usuarioModel.map(UsuarioModel::getCpf).orElse(null);
    }

    public default Long getRg(int Id) {
        Optional<UsuarioModel> usuarioModel = this.findById(Id);
        return usuarioModel.map(UsuarioModel::getRg).orElse(null);
    }

    public default String getNacionality(int Id) {
        Optional<UsuarioModel> usuarioModel = this.findById(Id);
        return usuarioModel.map(UsuarioModel::getNacionality).orElse(null);
    }
    public default Long getAge(int Id) {
        Optional<UsuarioModel> usuarioModel = this.findById(Id);
        return usuarioModel.map(UsuarioModel::getAge).orElse(null);
    }


}
