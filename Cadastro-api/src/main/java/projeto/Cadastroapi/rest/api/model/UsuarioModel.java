package projeto.Cadastroapi.rest.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "usuario")
public class UsuarioModel {

    @Id
    private Integer code;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 20)
    private Long phone;
    @Column(nullable = false, length = 11)
    private Long cpf;
    @Column(nullable = false, length = 9)
    private Long rg;
    @Column(nullable = false, length = 100)
    private String nacionality;
    @Column(nullable = false, length = 100)
    private Long age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getRg() {
        return rg;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        if (age > 0) {
            this.age = age;
        }
    }
}
