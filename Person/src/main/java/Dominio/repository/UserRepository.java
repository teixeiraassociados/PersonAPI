package Dominio.repository;

import Dominio.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel, Integer> {

    public default String getName(int Id) {
        Optional<UserModel> userModel = this.findById(Id);
        return userModel.map(UserModel::getName).orElse(null);
    }

    public default Long getPhone(int Id) {
        Optional<UserModel> userModel = this.findById(Id);
        return userModel.map(UserModel::getPhone).orElse(null);
    }

    public default Long getCpf(int Id) {
        Optional<UserModel> userModel = this.findById(Id);
        return userModel.map(UserModel::getCpf).orElse(null);
    }

    public default Long getRg(int Id) {
        Optional<UserModel> userModel = this.findById(Id);
        return userModel.map(UserModel::getRg).orElse(null);
    }

    public default String getNacionality(int Id) {
        Optional<UserModel> userModel = this.findById(Id);
        return userModel.map(UserModel::getNacionality).orElse(null);
    }

    public default Long getAge(int Id) {
        Optional<UserModel> userModel = this.findById(Id);
        return userModel.map(UserModel::getAge).orElse(null);
    }
}
