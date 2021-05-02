package br.com.zupacademy.guilhermejcs.mercadolivre.adicionaPergunta;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.UsuarioRepository;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NovaPerguntaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Emails emails;

    @PostMapping(value ="/produtos/{id}/perguntas")
    @Transactional
    public String criaPergunta(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id){
        Produto produto = manager.find(Produto.class,id);
        Usuario interessada = usuarioRepository.findByEmail("guilhrme@deveficiente.com").get();
        Pergunta novaPergunta = request.toModel(interessada, produto);
        manager.persist(novaPergunta);

        emails.novaPergunta(novaPergunta);

        return novaPergunta.toString();
    }
}
