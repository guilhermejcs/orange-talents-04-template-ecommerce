package br.com.zupacademy.guilhermejcs.mercadolivre.detalheProduto;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/produtos/{id}")
    public DetalheProdutoView getMethodName(@PathVariable("id") Long id) {
        Produto produtoEscolhido = manager.find(Produto.class, id);
        return new DetalheProdutoView(produtoEscolhido);
    }
}
