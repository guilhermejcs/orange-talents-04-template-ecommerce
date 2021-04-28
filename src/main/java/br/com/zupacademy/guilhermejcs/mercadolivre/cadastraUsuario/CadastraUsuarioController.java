package br.com.zupacademy.guilhermejcs.mercadolivre.cadastraUsuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CadastraUsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/usuarios")
    @Transactional
    public String cria(@RequestBody @Valid NovoUsuarioResquest request){
        Usuario novoUsuario = request.toUsuario();
        manager.persist(novoUsuario);
        return novoUsuario.toString();
    }

}
