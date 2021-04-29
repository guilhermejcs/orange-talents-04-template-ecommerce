package br.com.zupacademy.guilhermejcs.mercadolivre.cadastrocategorias;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CategoriasController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/categorias")
    @Transactional
    public String postMethodName(@RequestBody @Valid NovaCategoriaRequest request){
        Categoria categoria = request.toModel(manager);
        manager.persist(categoria);
        return categoria.toString();
    }

}
