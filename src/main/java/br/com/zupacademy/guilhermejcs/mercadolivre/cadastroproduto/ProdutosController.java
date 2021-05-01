package br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ProdutosController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @InitBinder
    private void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping(value = "/produtos")
    @Transactional
    public String criaProduto(@RequestBody @Valid NovoProdutoRequest request){
        // simulando usuário logado
        Usuario dono = usuarioRepository.findByEmail("guilhrme3@deveficiente.com").get();
        Produto produto = request.toModel(manager, dono);

        manager.persist(produto);
        return produto.toString();
    }

}
