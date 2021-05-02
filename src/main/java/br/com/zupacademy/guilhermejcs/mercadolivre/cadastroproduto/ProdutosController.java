package br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ProdutosController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private Uploader uploaderFake;

    @InitBinder(value = "novoProdutoRequest")
    private void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping(value = "/produtos")
    @Transactional
    public String criaProduto(@RequestBody @Valid NovoProdutoRequest request) {
        // simulando usuário logado
        Usuario dono = usuarioRepository.findByEmail("guilhrme3@deveficiente.com").get();
        Produto produto = request.toModel(manager, dono);

        manager.persist(produto);
        return produto.toString();
    }

    @PostMapping(value = "/produtos/{id}/imagens")
    @Transactional
    public String adicionaImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest request) {

        Usuario dono = usuarioRepository.findByEmail("guilhrme3@deveficiente.com").get();
        Produto produto = manager.find(Produto.class, id);

        if (!produto.pertenceAoUsuario(dono)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        manager.merge(produto);
        return produto.toString();
    }
}
