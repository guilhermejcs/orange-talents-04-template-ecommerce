package br.com.zupacademy.guilhermejcs.mercadolivre.adicionaOpiniao;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.UsuarioRepository;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class AdicionaOpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value ="/produtos/{id}/opiniao")
    @Transactional
    public String adicionaOpiniao(@RequestBody @Valid NovaOpiniaoRequest request, @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class,id);
        Usuario consumidor = usuarioRepository.findByEmail("guilhrme@deveficiente.com").get();
        Opiniao novaOpiniao = request.toModel(produto,consumidor);
        manager.persist(novaOpiniao);

        return novaOpiniao.toString();
    }
}
